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

		for (Identifier id : manager.findResources("materials", path -> path.endsWith(".json")))
		{
			Alloygery.LOGGER.info("Reading material from datapack: " + id);

			try (InputStream is = manager.getResource(id).getInputStream())
			{
				Identifier trimmedIdentifier = new Identifier(id.getNamespace(), id.getPath().substring(0, id.getPath().length() - ".json".length()));

				InputStreamReader reader = new InputStreamReader(is);
				JsonObject json = JsonHelper.deserialize(reader);
				reader.close();

				if(!ResourceConditions.objectMatchesConditions(json))
				{
					Alloygery.LOGGER.info("Load conditions failed, skipping: " + id);
					continue;
				}

				//validate
				if(!AlloygeryMaterial.validate(json))
				{
					Alloygery.LOGGER.info("Not an Alloygery Material, or data version unsupported: " + id);
					continue;
				}

				AlloygeryMaterial material = AlloygeryMaterial.GSON.fromJson(json, AlloygeryMaterial.class);
				AlloygeryMaterialData materialData = AlloygeryMaterialData.fromAlloygeryMaterial(material);
				AlloygeryMaterialRegistry.load(trimmedIdentifier, materialData);
			}
			catch (IOException thrown)
			{
				Alloygery.LOGGER.error("Error occurred while loading material resource " + id, thrown);
			}
		}
	}
}
