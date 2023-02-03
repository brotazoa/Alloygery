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

		for(Identifier id : manager.findResources("armor_materials", path -> path.endsWith(".json")))
		{
			Alloygery.LOGGER.info("Reading material from datapack: " + id.toString());

			try (InputStream is = manager.getResource(id).getInputStream())
			{
				Identifier trimmedId = new Identifier(id.getNamespace(), id.getPath().substring(0, id.getPath().length() - ".json".length()));

				InputStreamReader reader = new InputStreamReader(is);
				JsonObject jsonObject = JsonHelper.deserialize(reader);
				reader.close();

				if(!ResourceConditions.objectMatchesConditions(jsonObject))
				{
					Alloygery.LOGGER.info("Load conditions not met for " + id.toString());
					continue;
				}

				AlloygeryArmorMaterialDataHelper.getArmorMaterialDataFromJson(jsonObject).ifPresentOrElse(materialData -> AlloygeryArmorMaterialRegistry.load(trimmedId, materialData),
						() -> Alloygery.LOGGER.info("Could not validate resource " + id.toString() + ", it is either not an Alloygery Material, or is written using an unsupported data version."));
			}
			catch (IOException thrown)
			{
				Alloygery.LOGGER.error("Error occurred while loading material resource " + id.toString(), thrown);
			}
		}

		StringBuilder builder = new StringBuilder("Alloygery Armor Material Registry contains the following entries after reading datapacks: [").append('\n');
		AlloygeryArmorMaterialRegistry.forEach((identifier, material) -> builder.append('\t').append(identifier.toString()).append('\n'));
		builder.append("]");
		Alloygery.LOGGER.info(builder.toString());
	}
}
