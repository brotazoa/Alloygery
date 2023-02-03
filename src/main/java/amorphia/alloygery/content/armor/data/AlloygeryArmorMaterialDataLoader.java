package amorphia.alloygery.content.armor.data;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AlloygeryArmorMaterialDataLoader implements SimpleSynchronousResourceReloadListener
{
	public static final AlloygeryArmorMaterialDataLoader INSTANCE = new AlloygeryArmorMaterialDataLoader();
	public static final Identifier ID = Alloygery.identifier("alloygery_armor_material_data_loader");

	@Override
	public Identifier getFabricId()
	{
		return ID;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		AlloygeryArmorMaterialRegistry.resetToRegisteredValues();

		manager.findResources("armor_materials", id -> id.getPath().endsWith(".json")).forEach((identifier, resource) -> {
			Alloygery.LOGGER.info("Reading material from datapack: " +identifier.toString());

			try(InputStream is = resource.getInputStream())
			{
				Identifier trimmedIdentifier = new Identifier(identifier.getNamespace(), identifier.getPath().substring(0, identifier.getPath().length() - ".json".length()));
				InputStreamReader reader = new InputStreamReader(is);
				JsonObject jsonObject = JsonHelper.deserialize(reader);
				reader.close();

				if (!ResourceConditions.objectMatchesConditions(jsonObject))
				{
					Alloygery.LOGGER.info("Load conditions not met for " + identifier.toString());
					return;
				}

				AlloygeryArmorMaterialDataHelper.getArmorMaterialDataFromJson(jsonObject).ifPresentOrElse(data -> AlloygeryArmorMaterialRegistry.load(trimmedIdentifier, data),
						() -> Alloygery.LOGGER.info("Could not validate resource " + identifier.toString() + ", it is either not an Alloygery Material, or is written using an unsupported data version."));
			}
			catch (IOException thrown)
			{
				Alloygery.LOGGER.error("Error occurred while loading material resource " + identifier.toString(), thrown);
			}
		});

		StringBuilder builder = new StringBuilder("Alloygery Armor Material Registry contains the following entries after reading datapacks: [").append('\n');
		AlloygeryArmorMaterialRegistry.forEach((identifier, material) -> builder.append('\t').append(identifier.toString()).append('\n'));
		builder.append("]");
		Alloygery.LOGGER.info(builder.toString());
	}
}
