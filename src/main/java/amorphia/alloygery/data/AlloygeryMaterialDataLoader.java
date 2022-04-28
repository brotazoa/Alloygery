package amorphia.alloygery.data;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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

				AlloygeryMaterial material = AlloygeryMaterial.GSON.fromJson(json, AlloygeryMaterial.class);
				AlloygeryMaterial registeredMaterial = AlloygeryMaterials.ALLOYGERY_MATERIALS.getOrDefault(trimmedIdentifier, AlloygeryMaterials.UNKNOWN);

				if(material == null)
				{
					Alloygery.LOGGER.warn("Null material from loading " + id);
					continue;
				}

				if (registeredMaterial != AlloygeryMaterials.UNKNOWN)
				{
					//try merge
					AlloygeryMaterial.merge(registeredMaterial, material);
				}
				else
				{
					//register new
					AlloygeryMaterials.register(trimmedIdentifier, material);
				}
			}
			catch (IOException thrown)
			{
				Alloygery.LOGGER.error("Error occurred while loading material resource " + id, thrown);
			}
		}
	}
}
