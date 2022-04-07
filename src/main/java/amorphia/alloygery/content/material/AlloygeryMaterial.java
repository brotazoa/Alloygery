package amorphia.alloygery.content.material;

import amorphia.alloygery.content.item.ModMiningLevels;
import com.google.gson.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.lang.reflect.Type;

public class AlloygeryMaterial
{
	public static final Gson GSON = new GsonBuilder().registerTypeAdapter(AlloygeryMaterial.class, new AlloygeryMaterialSerializer()).setPrettyPrinting().create();

	public String name;
	public String category = "misc";
	public int color = 16253176;

	public boolean make_tool_heads = false;
	public boolean make_tool_bindings = false;
	public boolean make_sword_guards = false;
	public boolean make_tool_handles = false;

	public JsonElement repair_ingredient = null;

	public ToolPartSettings tool_base = new ToolPartSettings();
	public ToolPartSettings tool_binding = new ToolPartSettings();
	public ToolPartSettings tool_handle = new ToolPartSettings();
	public ToolPartSettings tool_upgrade = new ToolPartSettings();

	public ArmorPartSettings armor = new ArmorPartSettings();

	AlloygeryMaterial(String name)
	{
		this.name = name;
	}

	public static AlloygeryMaterial getById(String id)
	{
		return AlloygeryMaterials.ALLOYGERY_MATERIALS.getOrDefault(Identifier.tryParse(id), AlloygeryMaterials.UNKNOWN);
	}

	public static AlloygeryMaterial merge(AlloygeryMaterial original, AlloygeryMaterial other)
	{
		if(original == null || other == null || original == other)
			return original;

		if(!original.name.equals(other.name))
			throw new IllegalArgumentException("Can not merge data for unlike materials");

		original.category = other.category;
		original.color = other.color;

		original.make_tool_heads = other.make_tool_heads;
		original.make_tool_bindings = other.make_tool_bindings;
		original.make_sword_guards = other.make_sword_guards;
		original.make_tool_handles = other.make_tool_handles;

		original.repair_ingredient = other.repair_ingredient;

		original.tool_base.merge(other.tool_base);
		original.tool_binding.merge(other.tool_binding);
		original.tool_handle.merge(other.tool_handle);
		original.tool_upgrade.merge(other.tool_upgrade);

		original.armor.merge(other.armor);

		return original;
	}

	public static class AlloygeryMaterialSerializer implements JsonDeserializer<AlloygeryMaterial>, JsonSerializer<AlloygeryMaterial>
	{
		@Override
		public AlloygeryMaterial deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
		{
			JsonObject jsonObject = json.getAsJsonObject();

			//meta
			if(!jsonObject.has("name"))
				throw new JsonParseException("missing name");

			AlloygeryMaterialBuilder builder = new AlloygeryMaterialBuilder(jsonObject.get("name").getAsString());

			if(jsonObject.has("category"))
				builder.category(jsonObject.get("category").getAsString());

			if(jsonObject.has("color"))
				builder.color(jsonObject.get("color").getAsInt());

			if(jsonObject.has("make_tool_heads"))
				builder.make_tool_heads(jsonObject.get("make_tool_heads").getAsBoolean());

			if(jsonObject.has("make_tool_bindings"))
				builder.make_tool_bindings(jsonObject.get("make_tool_bindings").getAsBoolean());

			if(jsonObject.has("make_sword_guards"))
				builder.make_sword_guards(jsonObject.get("make_sword_guards").getAsBoolean());

			if(jsonObject.has("make_tool_handles"))
				builder.make_tool_handles(jsonObject.get("make_tool_handles").getAsBoolean());

			if(jsonObject.has("repair_ingredient") && jsonObject.get("repair_ingredient").isJsonObject())
			{
				builder.repair_ingredient(jsonObject.get("repair_ingredient").getAsJsonObject());
			}
			else
			{
				builder.repair_ingredient(null);
			}

			if(jsonObject.has("tool_base"))
			{
				builder.tool_base(ToolPartSettings.Serializer.deserialize(jsonObject.get("tool_base").getAsJsonObject()));
			}

			if(jsonObject.has("tool_binding"))
			{
				builder.tool_binding(ToolPartSettings.Serializer.deserialize(jsonObject.get("tool_binding").getAsJsonObject()));
			}

			if(jsonObject.has("tool_handle"))
			{
				builder.tool_handle(ToolPartSettings.Serializer.deserialize(jsonObject.get("tool_handle").getAsJsonObject()));
			}

			if(jsonObject.has("tool_upgrade"))
			{
				builder.tool_upgrade(ToolPartSettings.Serializer.deserialize(jsonObject.get("tool_upgrade").getAsJsonObject()));
			}

			if(jsonObject.has("armor"))
			{
				builder.armor(ArmorPartSettings.Serializer.deserialize(jsonObject.get("armor").getAsJsonObject()));
			}

			return builder.build();
		}

		@Override
		public JsonElement serialize(AlloygeryMaterial material, Type typeOfT, JsonSerializationContext context)
		{
			JsonObject json = new JsonObject();

			json.addProperty("name", material.name);
			json.addProperty("category", material.category);
			json.addProperty("color", material.color);

			json.addProperty("make_tool_heads", material.make_tool_heads);
			json.addProperty("make_tool_bindings", material.make_tool_bindings);
			json.addProperty("make_sword_guards", material.make_sword_guards);
			json.addProperty("make_tool_handles", material.make_tool_handles);

			json.add("repair_ingredient", material.repair_ingredient == null ? JsonNull.INSTANCE : material.repair_ingredient);

			json.add("tool_base", ToolPartSettings.Serializer.serialize(material.tool_base));
			json.add("tool_binding", ToolPartSettings.Serializer.serialize(material.tool_binding));
			json.add("tool_handle", ToolPartSettings.Serializer.serialize(material.tool_handle));
			json.add("tool_upgrade", ToolPartSettings.Serializer.serialize(material.tool_upgrade));

			json.add("armor", ArmorPartSettings.Serializer.serialize(material.armor));

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

		public AlloygeryMaterialBuilder make_tool_heads(boolean make_tool_heads)
		{
			this.material.make_tool_heads = make_tool_heads;
			return this;
		}

		public AlloygeryMaterialBuilder make_tool_bindings(boolean make_tool_bindings)
		{
			this.material.make_tool_bindings = make_tool_bindings;
			return this;
		}

		public AlloygeryMaterialBuilder make_sword_guards(boolean make_sword_guards)
		{
			this.material.make_sword_guards = make_sword_guards;
			return this;
		}

		public AlloygeryMaterialBuilder make_tool_handles(boolean make_tool_handles)
		{
			this.material.make_tool_handles = make_tool_handles;
			return this;
		}

		public AlloygeryMaterialBuilder repair_ingredient_tag(String tagIdentifier)
		{
			JsonObject tag = new JsonObject();
			tag.addProperty("tag", tagIdentifier);
			return repair_ingredient(tag);
		}

		public AlloygeryMaterialBuilder repair_ingredient_item(String itemIdentifier)
		{
			JsonObject item = new JsonObject();
			item.addProperty("item", itemIdentifier);
			return repair_ingredient(item);
		}

		public AlloygeryMaterialBuilder repair_ingredient(JsonElement element)
		{
			this.material.repair_ingredient = element;
			return this;
		}

		public AlloygeryMaterialBuilder tool_base(ToolPartSettings tool_base)
		{
			this.material.tool_base = tool_base;
			return this;
		}

		public AlloygeryMaterialBuilder tool_binding(ToolPartSettings tool_binding)
		{
			this.material.tool_binding = tool_binding;
			return this;
		}

		public AlloygeryMaterialBuilder tool_handle(ToolPartSettings tool_handle)
		{
			this.material.tool_handle = tool_handle;
			return this;
		}

		public AlloygeryMaterialBuilder tool_upgrade(ToolPartSettings tool_upgrade)
		{
			this.material.tool_upgrade = tool_upgrade;
			return this;
		}

		public AlloygeryMaterialBuilder armor(ArmorPartSettings armor)
		{
			this.material.armor = armor;
			return this;
		}

		public AlloygeryMaterial build()
		{
			return this.material;
		}
	}

	public static class ToolPartSettings
	{
		//base
		public int mining_level = 0;
		public int durability = 0;
		public int enchantability = 0;
		public float mining_speed = 0.0f;
		public float attack_speed = 0.0f;
		public float attack_damage = 0.0f;
		public float luck = 0.0f;
		public float crit_chance = 0.0f;

		//multiplier
		public float durability_multiplier = 1.0f;
		public float enchantability_multiplier = 1.0f;
		public float mining_speed_multiplier = 1.0f;
		public float attack_speed_multiplier = 1.0f;
		public float attack_damage_multiplier = 1.0f;
		public float luck_multiplier = 1.0f;
		public float crit_chance_multiplier = 1.0f;

		//
		public boolean fireproof = false;
		public boolean piglin_loved = false;

		public void merge(ToolPartSettings other)
		{
			this.mining_level = other.mining_level;
			this.durability = other.durability;
			this.enchantability = other.enchantability;
			this.mining_speed = other.mining_speed;
			this.attack_speed = other.attack_speed;
			this.attack_damage = other.attack_damage;
			this.luck = other.luck;

			this.durability_multiplier = other.durability_multiplier;
			this.enchantability_multiplier = other.enchantability_multiplier;
			this.mining_speed_multiplier = other.mining_speed_multiplier;
			this.attack_speed_multiplier = other.attack_speed_multiplier;
			this.attack_damage_multiplier = other.attack_damage_multiplier;
			this.luck_multiplier = other.luck_multiplier;

			this.fireproof = other.fireproof;
			this.piglin_loved = other.piglin_loved;
		}

		public static class Serializer
		{
			public static ToolPartSettings deserialize(JsonObject jsonObject)
			{
				ToolPartSettingsBuilder builder = new ToolPartSettingsBuilder();

				if(jsonObject.has("mining_level"))
				{
					String levelAsString = jsonObject.get("mining_level").getAsString();
					int levelFromString = ModMiningLevels.levelFromString(levelAsString);
					if(levelFromString != -2)
						builder.mining_level(levelFromString);
					else
					{
						builder.mining_level(jsonObject.get("mining_level").getAsInt());
					}
				}

				if(jsonObject.has("durability"))
					builder.durability(jsonObject.get("durability").getAsInt());

				if(jsonObject.has("enchantability"))
					builder.enchantability(jsonObject.get("enchantability").getAsInt());

				if(jsonObject.has("mining_speed"))
					builder.mining_speed(jsonObject.get("mining_speed").getAsFloat());

				if(jsonObject.has("attack_speed"))
					builder.attack_speed(jsonObject.get("attack_speed").getAsFloat());

				if(jsonObject.has("attack_damage"))
					builder.attack_damage(jsonObject.get("attack_damage").getAsFloat());

				if(jsonObject.has("luck"))
					builder.luck(jsonObject.get("luck").getAsFloat());

				if(jsonObject.has("durability_multiplier"))
					builder.durability_multiplier(jsonObject.get("durability_multiplier").getAsFloat());

				if(jsonObject.has("enchantability_multiplier"))
					builder.enchantability_multiplier(jsonObject.get("enchantability_multiplier").getAsFloat());

				if(jsonObject.has("mining_speed_multiplier"))
					builder.mining_speed_multiplier(jsonObject.get("mining_speed_multiplier").getAsFloat());

				if(jsonObject.has("attack_speed_multiplier"))
					builder.attack_speed_multiplier(jsonObject.get("attack_speed_multiplier").getAsFloat());

				if(jsonObject.has("attack_damage_multiplier"))
					builder.attack_damage_multiplier(jsonObject.get("attack_damage_multiplier").getAsFloat());

				if(jsonObject.has("luck_multiplier"))
					builder.luck_multiplier(jsonObject.get("luck_multiplier").getAsFloat());

				if(jsonObject.has("fireproof"))
					builder.fireproof(jsonObject.get("fireproof").getAsBoolean());

				if(jsonObject.has("piglin_loved"))
					builder.piglin_loved(jsonObject.get("piglin_loved").getAsBoolean());

				return builder.build();
			}

			public static JsonElement serialize(ToolPartSettings settings)
			{
				JsonObject jsonObject = new JsonObject();

				jsonObject.addProperty("mining_level", settings.mining_level);
				jsonObject.addProperty("durability", settings.durability);
				jsonObject.addProperty("enchantability", settings.enchantability);
				jsonObject.addProperty("mining_speed", settings.mining_speed);
				jsonObject.addProperty("attack_speed", settings.attack_speed);
				jsonObject.addProperty("attack_damage", settings.attack_damage);
				jsonObject.addProperty("luck", settings.luck);
				jsonObject.addProperty("durability_multiplier", settings.durability_multiplier);
				jsonObject.addProperty("enchantability_multiplier", settings.enchantability_multiplier);
				jsonObject.addProperty("mining_speed_multiplier", settings.mining_speed_multiplier);
				jsonObject.addProperty("attack_speed_multiplier", settings.attack_speed_multiplier);
				jsonObject.addProperty("attack_damage_multiplier", settings.attack_damage_multiplier);
				jsonObject.addProperty("luck_multiplier", settings.luck_multiplier);
				jsonObject.addProperty("fireproof", settings.fireproof);
				jsonObject.addProperty("piglin_loved", settings.piglin_loved);

				return jsonObject;
			}
		}

		public static class ToolPartSettingsBuilder
		{
			private final ToolPartSettings settings;

			public ToolPartSettingsBuilder()
			{
				this.settings = new ToolPartSettings();
			}

			public ToolPartSettingsBuilder mining_level(int level)
			{
				this.settings.mining_level = level;
				return this;
			}

			public ToolPartSettingsBuilder durability(int durability)
			{
				this.settings.durability = durability;
				return this;
			}

			public ToolPartSettingsBuilder enchantability(int enchantability)
			{
				this.settings.enchantability = enchantability;
				return this;
			}

			public ToolPartSettingsBuilder mining_speed(float mining_speed)
			{
				this.settings.mining_speed = mining_speed;
				return this;
			}

			public ToolPartSettingsBuilder attack_speed(float attack_speed)
			{
				this.settings.attack_speed = attack_speed;
				return this;
			}

			public ToolPartSettingsBuilder attack_damage(float attack_damage)
			{
				this.settings.attack_damage = attack_damage;
				return this;
			}

			public ToolPartSettingsBuilder luck(float luck)
			{
				this.settings.luck = luck;
				return this;
			}

			public ToolPartSettingsBuilder durability_multiplier(float durability_multiplier)
			{
				this.settings.durability_multiplier = durability_multiplier;
				return this;
			}

			public ToolPartSettingsBuilder enchantability_multiplier(float enchantability_multiplier)
			{
				this.settings.enchantability_multiplier = enchantability_multiplier;
				return this;
			}

			public ToolPartSettingsBuilder mining_speed_multiplier(float mining_speed_multiplier)
			{
				this.settings.mining_speed_multiplier = mining_speed_multiplier;
				return this;
			}

			public ToolPartSettingsBuilder attack_speed_multiplier(float attack_speed_multiplier)
			{
				this.settings.attack_speed_multiplier = attack_speed_multiplier;
				return this;
			}

			public ToolPartSettingsBuilder attack_damage_multiplier(float attack_damage_multiplier)
			{
				this.settings.attack_damage_multiplier = attack_damage_multiplier;
				return this;
			}

			public ToolPartSettingsBuilder luck_multiplier(float luck_multiplier)
			{
				this.settings.luck_multiplier = luck_multiplier;
				return this;
			}

			public ToolPartSettingsBuilder fireproof(boolean fireproof)
			{
				this.settings.fireproof = fireproof;
				return this;
			}

			public ToolPartSettingsBuilder piglin_loved(boolean piglin_loved)
			{
				this.settings.piglin_loved = piglin_loved;
				return this;
			}

			public ToolPartSettings build()
			{
				return this.settings;
			}
		}
	}

	public static class ArmorPartSettings
	{
		public int durability = 0;
		public int helmet = 0;
		public int chestplate = 0;
		public int leggings = 0;
		public int boots = 0;
		public int enchantability = 0;
		public float toughness = 0.0f;
		public float knockback_resistance = 0.0f;

		public boolean fireproof = false;
		public boolean piglin_loved = false;
		public boolean freeze_immune = false;
		public boolean quiet = false;

		public void merge(ArmorPartSettings other)
		{
			this.durability = other.durability;
			this.helmet = other.helmet;
			this.chestplate = other.chestplate;
			this.leggings = other.leggings;
			this.boots = other.boots;
			this.enchantability = other.enchantability;
			this.toughness = other.toughness;
			this.knockback_resistance = other.knockback_resistance;

			this.fireproof = other.fireproof;
			this.piglin_loved = other.piglin_loved;
			this.freeze_immune = other.freeze_immune;
		}

		public static class ArmorPartSettingsBuilder
		{
			private final ArmorPartSettings settings;

			public ArmorPartSettingsBuilder()
			{
				this.settings = new ArmorPartSettings();
			}

			public ArmorPartSettingsBuilder durability(int durability)
			{
				this.settings.durability = durability;
				return this;
			}

			public ArmorPartSettingsBuilder helmet(int helmet)
			{
				this.settings.helmet = helmet;
				return this;
			}

			public ArmorPartSettingsBuilder chestplate(int chestplate)
			{
				this.settings.chestplate = chestplate;
				return this;
			}

			public ArmorPartSettingsBuilder leggings(int leggings)
			{
				this.settings.leggings = leggings;
				return this;
			}

			public ArmorPartSettingsBuilder boots(int boots)
			{
				this.settings.boots = boots;
				return this;
			}

			public ArmorPartSettingsBuilder enchantability(int enchantability)
			{
				this.settings.enchantability = enchantability;
				return this;
			}

			public ArmorPartSettingsBuilder toughness(float toughness)
			{
				this.settings.toughness = toughness;
				return this;
			}

			public ArmorPartSettingsBuilder knockback_resistance(float knockback_resistance)
			{
				this.settings.knockback_resistance = knockback_resistance;
				return this;
			}

			public ArmorPartSettingsBuilder fireproof(boolean fireproof)
			{
				this.settings.fireproof = fireproof;
				return this;
			}

			public ArmorPartSettingsBuilder piglin_loved(boolean piglin_loved)
			{
				this.settings.piglin_loved = piglin_loved;
				return this;
			}

			public ArmorPartSettingsBuilder freeze_immune(boolean freeze_immune)
			{
				this.settings.freeze_immune = freeze_immune;
				return this;
			}

			public ArmorPartSettings build()
			{
				return this.settings;
			}
		}

		public static class Serializer
		{
			public static ArmorPartSettings deserialize(JsonObject jsonObject)
			{
				ArmorPartSettingsBuilder builder = new ArmorPartSettingsBuilder();

				if(jsonObject.has("durability"))
					builder.durability(jsonObject.get("durability").getAsInt());

				if(jsonObject.has("helmet"))
					builder.helmet(jsonObject.get("helmet").getAsInt());

				if(jsonObject.has("chestplate"))
					builder.chestplate(jsonObject.get("chestplate").getAsInt());

				if(jsonObject.has("leggings"))
					builder.leggings(jsonObject.get("leggings").getAsInt());

				if(jsonObject.has("boots"))
					builder.boots(jsonObject.get("boots").getAsInt());

				if(jsonObject.has("enchantability"))
					builder.enchantability(jsonObject.get("enchantability").getAsInt());

				if(jsonObject.has("toughness"))
					builder.toughness(jsonObject.get("toughness").getAsFloat());

				if(jsonObject.has("knockback_resistance"))
					builder.knockback_resistance(jsonObject.get("knockback_resistance").getAsFloat());

				if(jsonObject.has("fireproof"))
					builder.fireproof(jsonObject.get("fireproof").getAsBoolean());

				if(jsonObject.has("piglin_loved"))
					builder.piglin_loved(jsonObject.get("piglin_loved").getAsBoolean());

				if(jsonObject.has("freeze_immune"))
					builder.freeze_immune(jsonObject.get("freeze_immune").getAsBoolean());

				return builder.build();
			}

			public static JsonElement serialize(ArmorPartSettings settings)
			{
				JsonObject jsonObject = new JsonObject();

				jsonObject.addProperty("durability", settings.durability);
				jsonObject.addProperty("helmet", settings.helmet);
				jsonObject.addProperty("chestplate", settings.chestplate);
				jsonObject.addProperty("leggings", settings.leggings);
				jsonObject.addProperty("boots", settings.boots);
				jsonObject.addProperty("enchantability", settings.enchantability);
				jsonObject.addProperty("toughness", settings.toughness);
				jsonObject.addProperty("knockback_resistance", settings.knockback_resistance);
				jsonObject.addProperty("fireproof", settings.fireproof);
				jsonObject.addProperty("piglin_loved", settings.piglin_loved);
				jsonObject.addProperty("freeze_immune", settings.freeze_immune);

				return jsonObject;
			}
		}
	}
}
