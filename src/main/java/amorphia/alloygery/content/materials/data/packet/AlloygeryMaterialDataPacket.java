package amorphia.alloygery.content.materials.data.packet;

import amorphia.alloygery.content.materials.data.IAlloygeryMaterialData;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import com.google.gson.*;
import net.minecraft.recipe.Ingredient;

import java.lang.reflect.Type;

public class AlloygeryMaterialDataPacket implements IAlloygeryMaterialData
{
	private AlloygeryMaterial dataHolder = null;

	@Override
	public AlloygeryMaterial apply(AlloygeryMaterial material)
	{
		return AlloygeryMaterial.AlloygeryMaterialMerger.override(material, dataHolder);
	}

	public static class Serializer implements JsonSerializer<AlloygeryMaterial>, JsonDeserializer<AlloygeryMaterialDataPacket>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public JsonElement serialize(AlloygeryMaterial material, Type type, JsonSerializationContext jsonSerializationContext)
		{
			JsonObject json = new JsonObject();

			json.addProperty("alloygery_packet", "alloygery_material");

			json.addProperty("name", material.getMaterialName());
			json.addProperty("color", material.getMaterialColor());
			json.add("repair_ingredient", material.getRepairIngredient().toJson());

			JsonArray properties = new JsonArray();
			material.getToolProperties().forEach(property -> {
				JsonObject propertyObject = new JsonObject();
				propertyObject.addProperty("part_type", property.partType().getName());
				propertyObject.addProperty("property_type", property.type().getName());
				propertyObject.addProperty("operation", property.operation().getName());
				propertyObject.addProperty("value", property.value());
				properties.add(propertyObject);
			});

			json.add("tool_properties", properties);

			return json;
		}

		@Override
		public AlloygeryMaterialDataPacket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
				throws JsonParseException
		{
			JsonObject json = jsonElement.getAsJsonObject();

			if(!json.has("alloygery_packet") || !json.get("alloygery_packet").getAsString().equals("alloygery_material"))
				throw new JsonParseException("not a material packet");

			if(!json.has("name"))
				throw new JsonParseException("missing name");

			AlloygeryMaterial.AlloygeryMaterialBuilder builder = new AlloygeryMaterial.AlloygeryMaterialBuilder(json.get("name").getAsString());

			if(json.has("color"))
				builder.color(json.get("color").getAsInt());

			if(json.has("repair_ingredient") && json.get("repair_ingredient").isJsonObject())
				builder.repairIngredientFromIngredient(Ingredient.fromJson(json.get("repair_ingredient").getAsJsonObject()));

			if (json.has("tool_properties") && json.get("tool_properties").isJsonArray())
			{
				JsonArray propertiesArray = json.get("tool_properties").getAsJsonArray();
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

			AlloygeryMaterialDataPacket packet = new AlloygeryMaterialDataPacket();
			packet.dataHolder = builder.build();

			return packet;
		}
	}
}
