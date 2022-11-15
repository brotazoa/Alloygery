package amorphia.alloygery.content.materials.data.v1;

import amorphia.alloygery.ModMiningLevels;
import amorphia.alloygery.content.materials.data.IAlloygeryMaterialData;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import com.google.gson.*;
import net.minecraft.recipe.Ingredient;

import java.lang.reflect.Type;

public class AlloygeryMaterialDataV1 implements IAlloygeryMaterialData
{
	private boolean replace = false;
	private AlloygeryMaterial dataHolder = null;

	@Override
	public AlloygeryMaterial apply(AlloygeryMaterial material)
	{
		return this.replace ? AlloygeryMaterial.AlloygeryMaterialMerger.override(material, dataHolder) : AlloygeryMaterial.AlloygeryMaterialMerger.merge(material, dataHolder);
	}

	public static class Serializer implements JsonDeserializer<AlloygeryMaterialDataV1>
	{
		@Override
		public AlloygeryMaterialDataV1 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
				throws JsonParseException
		{
			JsonObject json = jsonElement.getAsJsonObject();

			if(!json.has("name"))
				throw new JsonParseException("missing name");

			AlloygeryMaterial.AlloygeryMaterialBuilder builder = new AlloygeryMaterial.AlloygeryMaterialBuilder(json.get("name").getAsString());

			if(json.has("color"))
				builder.color(json.get("color").getAsInt());

			if(json.has("repair_ingredient") && json.get("repair_ingredient").isJsonObject())
				builder.repairIngredientFromIngredient(Ingredient.fromJson(json.get("repair_ingredient").getAsJsonObject()));

			if (json.has("tool_base"))
				deserializeToolPartSetting(json.get("tool_base"), builder, ToolPartType.HEAD, jsonDeserializationContext);

			if (json.has("tool_binding"))
				deserializeToolPartSetting(json.get("tool_binding"), builder, ToolPartType.BINDING, jsonDeserializationContext);

			if(json.has("tool_handle"))
				deserializeToolPartSetting(json.get("tool_handle"), builder, ToolPartType.HANDLE, jsonDeserializationContext);

			if(json.has("tool_upgrade"))
				deserializeToolPartSetting(json.get("tool_upgrade"), builder, ToolPartType.UPGRADE, jsonDeserializationContext);

			AlloygeryMaterialDataV1 dataV1 = new AlloygeryMaterialDataV1();

			if(json.has("replace"))
				dataV1.replace = json.get("replace").getAsBoolean();

			dataV1.dataHolder = builder.build();

			return dataV1;
		}

		private void deserializeToolPartSetting(JsonElement jsonElement, AlloygeryMaterial.AlloygeryMaterialBuilder builder, ToolPartType partType, JsonDeserializationContext context)
		{
			JsonObject json = jsonElement.getAsJsonObject();

			if (json.has("mining_level"))
			{
				String levelAsString = json.get("mining_level").getAsString();
				int levelFromString = ModMiningLevels.levelFromString(levelAsString);
				int level = levelFromString == -2 ? json.get("mining_level").getAsInt() : levelFromString;

				builder.toolProperty()
					   .property(ToolPropertyType.MINING_LEVEL)
					   .forPart(partType)
					   .operation(partType == ToolPartType.HEAD ? ToolPropertyOperation.BASE : ToolPropertyOperation.ADDITION)
					   .value(level)
					   .build();
			}

			if (json.has("durability"))
			{
				int durability = json.get("durability").getAsInt();
				builder.toolProperty()
					   .property(ToolPropertyType.DURABILITY)
					   .forPart(partType)
					   .operation(partType == ToolPartType.HEAD ? ToolPropertyOperation.BASE : ToolPropertyOperation.ADDITION)
					   .value(durability)
					   .build();
			}

			if (json.has("durability_multiplier"))
			{
				float durability = json.get("durability_multiplier").getAsFloat();
				builder.toolProperty()
					   .property(ToolPropertyType.DURABILITY)
					   .forPart(partType)
					   .operation(partType == ToolPartType.UPGRADE ? ToolPropertyOperation.MULTIPLY_TOTAL : ToolPropertyOperation.MULTIPLY_BASE)
					   .value(durability)
					   .build();
			}

			if(json.has("enchantability"))
			{
				int enchantability = json.get("enchantability").getAsInt();
				builder.toolProperty()
					   .property(ToolPropertyType.ENCHANTABILITY)
					   .forPart(partType)
					   .operation(partType == ToolPartType.HEAD ? ToolPropertyOperation.BASE : ToolPropertyOperation.ADDITION)
					   .value(enchantability)
					   .build();
			}

			if (json.has("enchantability_multiplier"))
			{
				float enchantability = json.get("enchantability_multiplier").getAsFloat();
				builder.toolProperty()
					   .property(ToolPropertyType.ENCHANTABILITY)
					   .forPart(partType)
					   .operation(partType == ToolPartType.UPGRADE ? ToolPropertyOperation.MULTIPLY_TOTAL : ToolPropertyOperation.MULTIPLY_BASE)
					   .value(enchantability)
					   .build();
			}

			if (json.has("mining_speed"))
			{
				float speed = json.get("mining_speed").getAsFloat();
				builder.toolProperty()
					   .property(ToolPropertyType.MINING_SPEED)
					   .forPart(partType)
						.operation(partType == ToolPartType.HEAD ? ToolPropertyOperation.BASE : ToolPropertyOperation.ADDITION)
						.value(speed)
						.build();
			}

			if (json.has("mining_speed_multiplier"))
			{
				float speed = json.get("mining_speed_multiplier").getAsFloat();
				builder.toolProperty()
						.property(ToolPropertyType.MINING_SPEED)
						.forPart(partType)
						.operation(partType == ToolPartType.UPGRADE ? ToolPropertyOperation.MULTIPLY_TOTAL : ToolPropertyOperation.MULTIPLY_BASE)
						.value(speed)
						.build();
			}

			if (json.has("attack_speed"))
			{
				float speed = json.get("attack_speed").getAsFloat();
				builder.toolProperty()
						.property(ToolPropertyType.ATTACK_SPEED)
						.forPart(partType)
						.operation(partType == ToolPartType.HEAD ? ToolPropertyOperation.BASE : ToolPropertyOperation.ADDITION)
						.value(speed)
						.build();
			}

			if (json.has("attack_speed_multiplier"))
			{
				float speed = json.get("attack_speed_multiplier").getAsFloat();
				builder.toolProperty()
						.property(ToolPropertyType.ATTACK_SPEED)
						.forPart(partType)
						.operation(partType == ToolPartType.UPGRADE ? ToolPropertyOperation.MULTIPLY_TOTAL : ToolPropertyOperation.MULTIPLY_BASE)
						.value(speed)
						.build();
			}

			if (json.has("attack_damage"))
			{
				float damage = json.get("attack_damage").getAsFloat();
				builder.toolProperty()
						.property(ToolPropertyType.ATTACK_DAMAGE)
						.forPart(partType)
						.operation(partType == ToolPartType.HEAD ? ToolPropertyOperation.BASE : ToolPropertyOperation.ADDITION)
						.value(damage)
						.build();
			}

			if (json.has("attack_damage_multiplier"))
			{
				float damage = json.get("attack_damage_multiplier").getAsFloat();
				builder.toolProperty()
						.property(ToolPropertyType.ATTACK_DAMAGE)
						.forPart(partType)
						.operation(partType == ToolPartType.UPGRADE ? ToolPropertyOperation.MULTIPLY_TOTAL : ToolPropertyOperation.MULTIPLY_BASE)
						.value(damage)
						.build();
			}

			if (json.has("fireproof"))
			{
				boolean fireproof = json.get("fireproof").getAsBoolean();
				builder.toolProperty()
						.property(ToolPropertyType.FIREPROOF)
						.forPart(partType)
						.operation(ToolPropertyOperation.ADDITION)
						.value(fireproof ? 1 : 0)
						.build();
			}

			if (json.has("piglin_loved"))
			{
				boolean loved = json.get("piglin_loved").getAsBoolean();
				builder.toolProperty()
					   .property(ToolPropertyType.PIGLIN_LOVED)
					   .forPart(partType)
					   .operation(ToolPropertyOperation.ADDITION)
					   .value(loved ? 1 : 0)
					   .build();
			}
		}
	}
}
