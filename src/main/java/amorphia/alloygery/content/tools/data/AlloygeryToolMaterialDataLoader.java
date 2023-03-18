package amorphia.alloygery.content.tools.data;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.registry.AlloygeryToolMaterialRegistry;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AlloygeryToolMaterialDataLoader implements SimpleSynchronousResourceReloadListener
{
	public static final AlloygeryToolMaterialDataLoader INSTANCE = new AlloygeryToolMaterialDataLoader();
	public static final Identifier ID = Alloygery.identifier("alloygery_tool_material_data_loader");

	private AlloygeryToolMaterialDataLoader(){} //no op

	@Override
	public Identifier getFabricId()
	{
		return ID;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		AlloygeryToolMaterialRegistry.resetToRegisteredValues();

		for(Identifier id : manager.findResources("tool_materials", path -> path.endsWith(".json")))
		{
			Alloygery.LOGGER.info("Reading material from datapack: " + id.toString());

			try (InputStream is = manager.getResource(id).getInputStream())
			{
				Identifier trimmedId = new Identifier(id.getNamespace(), id.getPath().substring(0, id.getPath().length() - ".json".length()));

				InputStreamReader reader = new InputStreamReader(is);
				JsonObject jsonObject = JsonHelper.deserialize(reader);
				reader.close();

				if (!ResourceConditions.objectMatchesConditions(jsonObject))
				{
					Alloygery.LOGGER.info("Load conditions not met for " + id.toString());
					continue;
				}

				AlloygeryToolMaterialDataHelper.getToolMaterialDataFromJson(jsonObject).ifPresentOrElse(data -> AlloygeryToolMaterialRegistry.load(trimmedId, data),
						() -> Alloygery.LOGGER.info("Could not validate resource " + id.toString() + ", it is either not an Alloygery Material, or is written using an unsupported data version."));
			}
			catch (IOException thrown)
			{
				Alloygery.LOGGER.error("Error occurred while loading material resource " + id.toString(), thrown);
			}
		}

		StringBuilder builder = new StringBuilder("Alloygery Tool Material Registry contains the following entries after reading datapacks: [").append('\n');
		AlloygeryToolMaterialRegistry.forEach((identifier, material) -> builder.append('\t').append(identifier).append('\n'));
		builder.append("]");
		Alloygery.LOGGER.info(builder.toString());
	}
}
