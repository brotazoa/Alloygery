package amorphia.alloygery.content.material;

public class AlloygeryMaterialData
{
	private AlloygeryMaterialDataProcessor processor = null;

	public static AlloygeryMaterial apply(AlloygeryMaterial material, AlloygeryMaterialData data)
	{
		if (material != null && data != null && data.processor != null)
		{
			data.processor.process(material);
		}

		return material;
	}

	public static AlloygeryMaterialData fromAlloygeryMaterial(AlloygeryMaterial material)
	{
		AlloygeryMaterialData data = new AlloygeryMaterialData();

		if (material != null)
		{
			data.processor = AlloygeryMaterialData.createMaterialProcessor(material);
		}

		return data;
	}

	private static AlloygeryMaterialDataProcessor createMaterialProcessor(AlloygeryMaterial original)
	{
		return original != null ? material -> {
			material.name = original.name;
			material.category = original.category;
			material.color = original.color;
			material.make_tool_heads = original.make_tool_heads;
			material.make_tool_bindings = original.make_tool_bindings;
			material.make_sword_guards = original.make_sword_guards;
			material.make_tool_handles = original.make_tool_handles;
			material.repair_ingredient = original.repair_ingredient;
			processToolPartSettingsObject(material.tool_base, original.tool_base);
			processToolPartSettingsObject(material.tool_binding, original.tool_binding);
			processToolPartSettingsObject(material.tool_handle, original.tool_handle);
			processToolPartSettingsObject(material.tool_upgrade, original.tool_upgrade);
			processArmorPartSettingsObject(material.armor, original.armor);
		} : null;
	}

	private static void processToolPartSettingsObject(AlloygeryMaterial.ToolPartSettings toolPartSettings, AlloygeryMaterial.ToolPartSettings other)
	{
		toolPartSettings.mining_level = other.mining_level;
		toolPartSettings.durability = other.durability;
		toolPartSettings.enchantability = other.enchantability;
		toolPartSettings.mining_speed = other.mining_speed;
		toolPartSettings.attack_speed = other.attack_speed;
		toolPartSettings.attack_damage = other.attack_damage;
		toolPartSettings.luck = other.luck;
		toolPartSettings.durability_multiplier = other.durability_multiplier;
		toolPartSettings.enchantability_multiplier = other.enchantability_multiplier;
		toolPartSettings.mining_speed_multiplier = other.mining_speed_multiplier;
		toolPartSettings.attack_speed_multiplier = other.attack_speed_multiplier;
		toolPartSettings.attack_damage_multiplier = other.attack_damage_multiplier;
		toolPartSettings.luck_multiplier = other.luck_multiplier;
		toolPartSettings.fireproof = other.fireproof;
		toolPartSettings.piglin_loved = other.piglin_loved;
	}

	private static void processArmorPartSettingsObject(AlloygeryMaterial.ArmorPartSettings armorPartSettings, AlloygeryMaterial.ArmorPartSettings other)
	{
		armorPartSettings.durability = other.durability;
		armorPartSettings.helmet = other.helmet;
		armorPartSettings.chestplate = other.chestplate;
		armorPartSettings.leggings = other.leggings;
		armorPartSettings.boots = other.boots;
		armorPartSettings.enchantability = other.enchantability;
		armorPartSettings.toughness = other.toughness;
		armorPartSettings.knockback_resistance = other.knockback_resistance;
		armorPartSettings.fireproof = other.fireproof;
		armorPartSettings.piglin_loved = other.piglin_loved;
		armorPartSettings.freeze_immune = other.freeze_immune;
	}

	private interface AlloygeryMaterialDataProcessor
	{
		void process(AlloygeryMaterial material);
	}
}
