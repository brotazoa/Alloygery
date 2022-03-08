package amorphia.alloygery.content.material;

import amorphia.alloygery.content.item.ModMiningLevels;
import com.google.gson.*;
import net.minecraft.util.Identifier;

import java.lang.reflect.Type;

public class AlloygeryMaterial
{
	public static final Gson GSON = new GsonBuilder().registerTypeAdapter(AlloygeryMaterial.class, new AlloygeryMaterialSerializer()).create();

	public String name;
	public String category = "misc";
	public int color = 16253176;

	public int level = -1;
	public int tool_enchantability = 0;
	public int armor_enchantability = 0;

	public int head_durability = 0;
	public int armor_durability = 0;

	public int helmet_armor = 0;
	public int chestplate_armor = 0;
	public int leggings_armor = 0;
	public int boots_armor = 0;

	public float speed = 0.0f;
	public float damage = 0.0f;

	public float toughness = 0.0f;
	public float knockback = 0.0f;

	AlloygeryMaterial(String name)
	{
		this.name = name;
	}

	public static AlloygeryMaterial getById(String id)
	{
		return AlloygeryMaterials.ALLOYGERY_MATERIALS.get(Identifier.tryParse(id));
	}

	public static AlloygeryMaterial merge(AlloygeryMaterial original, AlloygeryMaterial other)
	{
		if(original == null || other == null || original == other)
			return original;

		if(!original.name.equals(other.name))
			throw new IllegalArgumentException("Can not merge data for unlike materials");

		original.category = other.category;
		original.color = other.color;
		original.level = other.level;
		original.tool_enchantability = other.tool_enchantability;
		original.armor_enchantability = other.armor_enchantability;
		original.head_durability = other.head_durability;
		original.armor_durability = other.armor_durability;
		original.helmet_armor = other.helmet_armor;
		original.chestplate_armor = other.chestplate_armor;
		original.leggings_armor = other.leggings_armor;
		original.boots_armor = other.boots_armor;
		original.speed = other.speed;
		original.damage = other.damage;
		original.toughness = other.toughness;
		original.knockback = other.knockback;

		return original;
	}

	public static class AlloygeryMaterialSerializer implements JsonDeserializer<AlloygeryMaterial>, JsonSerializer<AlloygeryMaterial>
	{
		@Override
		public AlloygeryMaterial deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
		{
			JsonObject jsonObject = json.getAsJsonObject();

			if(!jsonObject.has("name"))
				throw new JsonParseException("missing name");

			AlloygeryMaterialBuilder builder = new AlloygeryMaterialBuilder(jsonObject.get("name").getAsString());

			if(jsonObject.has("category"))
				builder.category(jsonObject.get("category").getAsString());

			if(jsonObject.has("color"))
				builder.color(jsonObject.get("color").getAsInt());

			if(jsonObject.has("level"))
			{
				String levelAsString = jsonObject.get("level").getAsString();
				int levelFromString = ModMiningLevels.levelFromString(levelAsString);
				if(levelFromString != -2)
					builder.level(levelFromString);
				else
				{
					builder.level(jsonObject.get("level").getAsInt());
				}
			}

			if(jsonObject.has("enchantability"))
				builder.enchantability(jsonObject.get("enchantability").getAsInt());

			if(jsonObject.has("tool_enchantability"))
				builder.tool_enchantability(jsonObject.get("tool_enchantability").getAsInt());

			if(jsonObject.has("armor_enchantability"))
				builder.armor_enchantability(jsonObject.get("armor_enchantability").getAsInt());

			if(jsonObject.has("head_durability"))
				builder.head_durability(jsonObject.get("head_durability").getAsInt());

			if(jsonObject.has("armor_durability"))
				builder.armor_durability(jsonObject.get("armor_durability").getAsInt());

			if(jsonObject.has("helmet_armor"))
				builder.helmet_armor(jsonObject.get("helmet_armor").getAsInt());

			if(jsonObject.has("chestplate_armor"))
				builder.chestplate_armor(jsonObject.get("chestplate_armor").getAsInt());

			if(jsonObject.has("leggings_armor"))
				builder.leggings_armor(jsonObject.get("leggings_armor").getAsInt());

			if(jsonObject.has("boots_armor"))
				builder.boots_armor(jsonObject.get("boots_armor").getAsInt());

			if(jsonObject.has("speed"))
				builder.speed(jsonObject.get("speed").getAsFloat());

			if(jsonObject.has("damage"))
				builder.damage(jsonObject.get("damage").getAsFloat());

			if(jsonObject.has("toughness"))
				builder.toughness(jsonObject.get("toughness").getAsFloat());

			if(jsonObject.has("knockback"))
				builder.knockback(jsonObject.get("knockback").getAsFloat());

			return builder.build();
		}

		@Override
		public JsonElement serialize(AlloygeryMaterial material, Type typeOfT, JsonSerializationContext context)
		{
			JsonObject json = new JsonObject();

			json.addProperty("name", material.name);
			json.addProperty("category", material.category);
			json.addProperty("color", material.color);
			json.addProperty("level", material.level);
			json.addProperty("tool_enchantability", material.tool_enchantability);
			json.addProperty("armor_enchantability", material.armor_enchantability);
			json.addProperty("head_durability", material.head_durability);
			json.addProperty("armor_durability", material.armor_durability);
			json.addProperty("helmet_armor", material.helmet_armor);
			json.addProperty("chestplate_armor", material.chestplate_armor);
			json.addProperty("leggings_armor", material.leggings_armor);
			json.addProperty("boots_armor", material.boots_armor);
			json.addProperty("speed", material.speed);
			json.addProperty("damage", material.damage);
			json.addProperty("toughness", material.toughness);
			json.addProperty("knockback", material.knockback);

			return json;
		}
	}

	public static class AlloygeryMaterialBuilder
	{
		private final AlloygeryMaterial material;

		public AlloygeryMaterialBuilder(String name)
		{
			this.material = new AlloygeryMaterial(name);
		}

		public AlloygeryMaterialBuilder category(String category)
		{
			this.material.category = category;
			return this;
		}

		public AlloygeryMaterialBuilder color(int color)
		{
			this.material.color = color;
			return this;
		}

		public AlloygeryMaterialBuilder level(int level)
		{
			this.material.level = level;
			return this;
		}

		public AlloygeryMaterialBuilder enchantability(int enchantability)
		{
			this.material.tool_enchantability = enchantability;
			this.material.armor_enchantability = enchantability;
			return this;
		}

		public AlloygeryMaterialBuilder tool_enchantability(int tool_enchantability)
		{
			this.material.tool_enchantability = tool_enchantability;
			return this;
		}

		public AlloygeryMaterialBuilder armor_enchantability(int armor_enchantability)
		{
			this.material.armor_enchantability = armor_enchantability;
			return this;
		}

		public AlloygeryMaterialBuilder head_durability(int head_durability)
		{
			this.material.head_durability = head_durability;
			return this;
		}

		public AlloygeryMaterialBuilder armor_durability(int armor_durability)
		{
			this.material.armor_durability = armor_durability;
			return this;
		}

		public AlloygeryMaterialBuilder helmet_armor(int helmet_armor)
		{
			this.material.helmet_armor = helmet_armor;
			return this;
		}

		public AlloygeryMaterialBuilder chestplate_armor(int chestplate_armor)
		{
			this.material.chestplate_armor = chestplate_armor;
			return this;
		}

		public AlloygeryMaterialBuilder leggings_armor(int leggings_armor)
		{
			this.material.leggings_armor = leggings_armor;
			return this;
		}

		public AlloygeryMaterialBuilder boots_armor(int boots_armor)
		{
			this.material.boots_armor = boots_armor;
			return this;
		}

		public AlloygeryMaterialBuilder speed(float speed)
		{
			this.material.speed = speed;
			return this;
		}

		public AlloygeryMaterialBuilder damage(float damage)
		{
			this.material.damage = damage;
			return this;
		}

		public AlloygeryMaterialBuilder toughness(float toughness)
		{
			this.material.toughness = toughness;
			return this;
		}

		public AlloygeryMaterialBuilder knockback(float knockback)
		{
			this.material.knockback = knockback;
			return this;
		}

		public AlloygeryMaterial build()
		{
			return this.material;
		}
	}
}
