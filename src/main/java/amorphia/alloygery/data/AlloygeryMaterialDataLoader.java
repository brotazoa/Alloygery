package amorphia.alloygery.data;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.*;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AlloygeryMaterialDataLoader implements SimpleSynchronousResourceReloadListener
{
	public static final AlloygeryMaterialDataLoader INSTANCE = new AlloygeryMaterialDataLoader();

	public static final Identifier ALLOYGERY_MATERIAL_LOADER = Alloygery.identifier("material_loader");

	private AlloygeryMaterialDataLoader() {}

	@Override
	public Identifier getFabricId()
	{
		return ALLOYGERY_MATERIAL_LOADER;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		//clear repair ingredient map
		AlloygeryToolMaterialHelper.REPAIR_INGREDIENT_MAP.clear();
		AlloygeryMaterialRegistry.resetToRegisteredValues();

		manager.findResources("materials", path -> path.getPath().endsWith(".json")).forEach((identifier, resource) -> {

			Alloygery.LOGGER.info("Reading material from datapack: " + identifier);

			try (InputStream is = resource.getInputStream())
			{
				//trim .json off identifier
				Identifier trimmedIdentifier = new Identifier(identifier.getNamespace(), identifier.getPath().substring(0, identifier.getPath().length() - ".json".length()));

				//read json
				InputStreamReader reader = new InputStreamReader(is);
				JsonObject json = JsonHelper.deserialize(reader);
				reader.close();

				//check resource load conditions
				if (!ResourceConditions.objectMatchesConditions(json))
				{
					Alloygery.LOGGER.info("Load conditions failed, skipping: " + identifier);
					return;
				}

				//load material
				AlloygeryMaterial material = AlloygeryMaterial.GSON.fromJson(json, AlloygeryMaterial.class);
				AlloygeryMaterialData materialData = AlloygeryMaterialData.fromAlloygeryMaterial(material);
				AlloygeryMaterialRegistry.load(trimmedIdentifier, materialData);
			}
			catch (IOException thrown)
			{
				Alloygery.LOGGER.error("Error occurred while loading material resource " + identifier, thrown);
			}
		});
	}
}
