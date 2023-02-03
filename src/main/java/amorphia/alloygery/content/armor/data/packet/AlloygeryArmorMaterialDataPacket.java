package amorphia.alloygery.content.armor.data.packet;

import amorphia.alloygery.content.armor.data.IAlloygeryArmorMaterialData;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.property.ArmorPropertyOperation;
import amorphia.alloygery.content.armor.property.ArmorPropertyType;
import com.google.gson.*;
import net.minecraft.recipe.Ingredient;

import java.lang.reflect.Type;

public class AlloygeryArmorMaterialDataPacket implements IAlloygeryArmorMaterialData
{
	private AlloygeryArmorMaterial dataHolder = null;

	@Override
	public AlloygeryArmorMaterial apply(AlloygeryArmorMaterial material)
	{
		return AlloygeryArmorMaterial.AlloygeryArmorMaterialMerger.override(material, dataHolder);
	}

	public static class Serializer implements JsonSerializer<AlloygeryArmorMaterial>, JsonDeserializer<AlloygeryArmorMaterialDataPacket>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public JsonElement serialize(AlloygeryArmorMaterial material, Type type, JsonSerializationContext jsonSerializationContext)
		{
			JsonObject json = new JsonObject();

			json.addProperty("alloygery_packet", "alloygery_armor_material");

			json.addProperty("name", material.getMaterialName());
			json.addProperty("color", material.getMaterialColor());

			json.addProperty("layer", material.getLayer().getName());

			json.add("repair_ingredient", material.getRepairIngredient().toJson());

			JsonArray properties = new JsonArray();
			material.getArmorProperties().forEach(property -> {
				JsonObject propertyJson = new JsonObject();
				propertyJson.addProperty("layer", property.layer().getName());
				propertyJson.addProperty("type", property.type().getName());
				propertyJson.addProperty("operation", property.operation().getName());
				propertyJson.addProperty("value", property.value());
				properties.add(propertyJson);
			});
			json.add("armor_properties", properties);

			return json;
		}

		@Override
		public AlloygeryArmorMaterialDataPacket deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
				throws JsonParseException
		{
			JsonObject json = jsonElement.getAsJsonObject();

			if(!json.has("alloygery_packet") || !json.get("alloygery_packet").getAsString().equals("alloygery_armor_material"))
				throw new JsonParseException("Not a material packet");

			if(!json.has("name"))
				throw new JsonParseException("Missing name property");

			AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder builder = new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder(json.get("name").getAsString());

			if(json.has("color"))
				builder.color(json.get("color").getAsInt());

			if(json.has("layer"))
				builder.layer(ArmorLayer.getByName(json.get("layer").getAsString()));

			if(json.has("repair_ingredient") && json.get("repair_ingredient").isJsonObject())
				builder.repairIngredientFromIngredient(Ingredient.fromJson(json.get("repair_ingredient").getAsJsonObject()));

			if (json.has("armor_properties") && json.get("armor_properties").isJsonArray())
			{
				JsonArray properties = json.getAsJsonArray("armor_properties");
				properties.forEach(propertyJson -> {
					JsonObject propertyObject = propertyJson.getAsJsonObject();
					builder.armorProperty()
							.forLayer(ArmorLayer.getByName(propertyObject.get("layer").getAsString()))
							.property(ArmorPropertyType.getByName(propertyObject.get("type").getAsString()))
							.operation(ArmorPropertyOperation.getByName(propertyObject.get("operation").getAsString()))
							.value(propertyObject.get("value").getAsFloat())
							.build();
				});
			}

			AlloygeryArmorMaterialDataPacket packet = new AlloygeryArmorMaterialDataPacket();
			packet.dataHolder = builder.build();

			return packet;
		}
	}
}
