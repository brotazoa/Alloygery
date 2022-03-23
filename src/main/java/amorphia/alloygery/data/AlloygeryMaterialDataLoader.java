package amorphia.alloygery.data;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import com.google.gson.stream.JsonReader;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AlloygeryMaterialDataLoader implements SimpleSynchronousResourceReloadListener
{
	public static final Identifier ALLOYGERY_MATERIAL_LOADER = Alloygery.identifier("material_loader");

	@Override
	public Identifier getFabricId()
	{
		return ALLOYGERY_MATERIAL_LOADER;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		for (Identifier id : manager.findResources("materials", path -> path.endsWith(".json")))
		{
			Alloygery.LOGGER.info("Reading material from datapack: " + id);

			try (InputStream is = manager.getResource(id).getInputStream())
			{
				Identifier trimmedIdentifier = new Identifier(id.getNamespace(), id.getPath().substring(0, id.getPath().length() - ".json".length()));

				AlloygeryMaterial material = AlloygeryMaterial.GSON.fromJson(new JsonReader(new InputStreamReader(is)), AlloygeryMaterial.class);
				AlloygeryMaterial registeredMaterial = AlloygeryMaterials.ALLOYGERY_MATERIALS.get(trimmedIdentifier);

				if (registeredMaterial != AlloygeryMaterials.UNKNOWN && material != null)
				{
					//try merge
					AlloygeryMaterial.merge(registeredMaterial, material);
				}
			}
			catch (IOException thrown)
			{
				Alloygery.LOGGER.error("Error occurred while loading material resource " + id, thrown);
			}
		}
	}
}
