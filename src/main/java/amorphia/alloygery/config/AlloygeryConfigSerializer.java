package amorphia.alloygery.config;

import amorphia.alloygery.Alloygery;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.JsonHelper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class AlloygeryConfigSerializer
{
	public static void serialize(ConfigGroup root)
	{
		JsonObject json = new JsonObject();
		serializeGroup(json, root);

		Path configPath = FabricLoader.getInstance().getConfigDir().resolve(Alloygery.MOD_ID + ".json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try
		{
			Files.createDirectories(configPath.getParent());
			BufferedWriter writer = Files.newBufferedWriter(configPath);
			gson.toJson(json, writer);
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace(); //dirty consume
		}
	}

	public static void deserializeInto(ConfigGroup group)
	{
		Path configPath = FabricLoader.getInstance().getConfigDir().resolve(Alloygery.MOD_ID + ".json");

		if (Files.exists(configPath))
		{
			try
			{
				BufferedReader reader = Files.newBufferedReader(configPath);
				JsonObject json = JsonHelper.deserialize(reader);
				reader.close();

				deserializeGroup(group, json);
			}
			catch (IOException e)
			{
				e.printStackTrace(); //dirty consume
			}
		}
		else
		{
			//save initial config file
			AlloygeryConfigSerializer.serialize(group);
		}
	}

	private static void serializeGroup(JsonObject root, ConfigGroup group)
	{
		group.forEach((name, value) -> {
			if (value.getType() == ConfigValue.Type.GROUP)
			{
				JsonObject groupObject = new JsonObject();
				ConfigGroup groupValue = value.getValue();
				serializeGroup(groupObject, groupValue);
				root.add(name, groupObject);
			}
			else if (value.getType() == ConfigValue.Type.BOOLEAN)
			{
				final boolean booleanValue = value.getValue();
				root.addProperty(name, booleanValue);
			}
			else if (value.getType() == ConfigValue.Type.FLOAT)
			{
				final float floatValue = value.getValue();
				root.addProperty(name, floatValue);
			}
			else if (value.getType() == ConfigValue.Type.INTEGER)
			{
				final int intValue = value.getValue();
				root.addProperty(name, intValue);
			}
		});
	}

	private static void deserializeGroup(ConfigGroup group, JsonObject json)
	{
		for (Map.Entry<String, JsonElement> element : json.entrySet())
		{
			ConfigValue value = group.get(element.getKey());
			if(value == null) continue;

			if (value.getType() == ConfigValue.Type.GROUP && element.getValue().isJsonObject())
			{
				ConfigGroup groupValue = value.getValue();
				JsonObject object = element.getValue().getAsJsonObject();
				deserializeGroup(groupValue, object);
			}
			else if (value.getType() == ConfigValue.Type.BOOLEAN && element.getValue().isJsonPrimitive())
			{
				boolean booleanValue = element.getValue().getAsBoolean();
				value.set(booleanValue);
			}
			else if (value.getType() == ConfigValue.Type.FLOAT && element.getValue().isJsonPrimitive())
			{
				float floatValue = element.getValue().getAsFloat();
				value.set(floatValue);
			}
			else if (value.getType() == ConfigValue.Type.INTEGER && element.getValue().isJsonPrimitive())
			{
				int intValue = element.getValue().getAsInt();
				value.set(intValue);
			}
		}
	}
}
