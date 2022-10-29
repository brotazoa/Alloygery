package amorphia.alloygery.content.tools.data.packet;

import amorphia.alloygery.content.tools.data.IToolMaterialData;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.material.ToolMaterial;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import com.google.gson.*;
import net.minecraft.recipe.Ingredient;

import java.lang.reflect.Type;

public class ToolMaterialDataPacket implements IToolMaterialData
{
	private ToolMaterial dataHolder = null;

	@Override
	public ToolMaterial apply(ToolMaterial material)
	{
		return ToolMaterial.ToolMaterialMerger.override(material, dataHolder);
	}

	public static class Serializer implements JsonSerializer<ToolMaterial>, JsonDeserializer<ToolMaterialDataPacket>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public JsonElement serialize(ToolMaterial material, Type type, JsonSerializationContext jsonSerializationContext)
		{
			JsonObject json = new JsonObject();

			json.addProperty("alloygery_packet", "tool_material");

			json.addProperty("name", material.getMaterialName());
			json.addProperty("color", material.getMaterialColor());
			json.add("repair_ingredient", material.getRepairIngredient().toJson());

			JsonArray properties = new JsonArray();
			material.getProperties().forEach(property -> {
				JsonObject propertyObject = new JsonObject();
				propertyObject.addProperty("part_type", property.partType().getName());
				propertyObject.addProperty("property_type", property.type().getName());
				propertyObject.addProperty("operation", property.operation().getName());
				propertyObject.addProperty("value", property.value());
				properties.add(propertyObject);
			});

			json.add("properties", properties);

			return json;
		}

		@Override
		public ToolMaterialDataPacket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
				throws JsonParseException
		{
			JsonObject json = jsonElement.getAsJsonObject();

			if(!json.has("alloygery_packet") || !json.get("alloygery_packet").getAsString().equals("tool_material"))
				throw new JsonParseException("not a tool material packet");

			if(!json.has("name"))
				throw new JsonParseException("missing name");

			ToolMaterial.ToolMaterialBuilder builder = new ToolMaterial.ToolMaterialBuilder(json.get("name").getAsString());

			if(json.has("color"))
				builder.color(json.get("color").getAsInt());

			if(json.has("repair_ingredient") && json.get("repair_ingredient").isJsonObject())
				builder.repairIngredientFromIngredient(Ingredient.fromJson(json.get("repair_ingredient").getAsJsonObject()));

			if (json.has("properties") && json.get("properties").isJsonArray())
			{
				JsonArray propertiesArray = json.get("properties").getAsJsonArray();
				propertiesArray.forEach(propertyJson -> {
					JsonObject propertyObject = propertyJson.getAsJsonObject();
					builder.toolProperty()
							.forPart(ToolPartType.getByName(propertyObject.get("part_type").getAsString()))
							.property(ToolPropertyType.getByName(propertyObject.get("property_type").getAsString()))
							.operation(ToolPropertyOperation.getByName(propertyObject.get("operation").getAsString()))
							.value(propertyObject.get("value").getAsFloat())
							.build();
				});
			}

			ToolMaterialDataPacket packet = new ToolMaterialDataPacket();
			packet.dataHolder = builder.build();

			return packet;
		}
	}
}
