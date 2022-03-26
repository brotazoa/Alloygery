package amorphia.alloygery.config;

import amorphia.alloygery.content.item.ModMiningLevels;
import net.fabricmc.yarn.constants.MiningLevels;

public class AlloygeryConfig
{
	protected static final ConfigGroup ALLOYGERY_CONFIG = new ConfigGroup();

	public static ConfigValue getConfigValue(String path)
	{
		ConfigValue value = new ConfigValue();

		ConfigGroup group = ALLOYGERY_CONFIG;
		for (String part : path.split("\\."))
		{
			if(group != null && group.containsKey(part))
			{
				ConfigValue partValue = group.get(part);
				if (partValue.getType() == ConfigValue.Type.GROUP)
				{
					group = partValue.getValue();
				}
				else
				{
					value = partValue;
				}
			}
		}

		return value;
	}

	public static void loadFromFile()
	{
		AlloygeryConfigSerializer.deserializeInto(ALLOYGERY_CONFIG);
	}

	public static final ConfigValue enableDiamondUpgrade;
	public static final ConfigValue enableEmeraldUpgrade;
	public static final ConfigValue enableNetheriteUpgrade;

	public static final ConfigValue disableVanillaDiamondCrafting;
	public static final ConfigValue disableVanillaIronCrafting;

	public static final OreGenerationConfigGroup tinOre;
	public static final OreGenerationConfigGroup nickelOre;
	public static final OreGenerationConfigGroup titaniumOre;

	static
	{
		//@formatter:off
		enableDiamondUpgrade = ALLOYGERY_CONFIG.register("enable_diamond_upgrade", new ConfigValue(true));
		enableEmeraldUpgrade = ALLOYGERY_CONFIG.register("enable_emerald_upgrade", new ConfigValue(true));
		enableNetheriteUpgrade = ALLOYGERY_CONFIG.register("enable_netherite_upgrade", new ConfigValue(true));

		disableVanillaDiamondCrafting = ALLOYGERY_CONFIG.register("disable_diamond_crafting", new ConfigValue(true));
		disableVanillaIronCrafting = ALLOYGERY_CONFIG.register("disable_iron_crafting", new ConfigValue(true));

		tinOre = ALLOYGERY_CONFIG.register("tin_ore", new OreGenerationConfigGroup(true, 9, 50));
		nickelOre = ALLOYGERY_CONFIG.register("nickel_ore", new OreGenerationConfigGroup(true, 8, 10));
		titaniumOre = ALLOYGERY_CONFIG.register("titanium_ore", new OreGenerationConfigGroup(true, 6, 10));
		//@formatter:on
	}

	public static class OreGenerationConfigGroup extends ConfigGroup
	{
		public final ConfigValue shouldGenerate;
		public final ConfigValue size;
		public final ConfigValue number;

		public OreGenerationConfigGroup(boolean shouldGenerate, int size, int number)
		{
			this.shouldGenerate = register("should_generate", new ConfigValue(shouldGenerate));
			this.size = register("size", new ConfigValue(size));
			this.number = register("number", new ConfigValue(number));
		}
	}

	public static class GearStatsConfigGroup extends ConfigGroup
	{
		public final ConfigValue enable;
		public final ConfigValue enableSimpleRecipes;
		public final ConfigValue miningLevel;
		public final ConfigValue uses;
		public final ConfigValue speed;
		public final ConfigValue damage;
		public final ConfigValue enchantability;
		public final ConfigValue durabilityMultiplier;
		public final ConfigValue helmetArmor;
		public final ConfigValue chestplateArmor;
		public final ConfigValue leggingsArmor;
		public final ConfigValue bootsArmor;
		public final ConfigValue toughness;
		public final ConfigValue knockback;

		public GearStatsConfigGroup(boolean enable, boolean enableSimple, int miningLevel, int uses, float speed, float damage, int enchantability,
				int durabilityMultiplier, int helmetArmor, int chestplateArmor, int leggingsArmor, int bootsArmor, float toughness, float knockback)
		{
			this.enable = register("enable", new ConfigValue(enable));
			this.enableSimpleRecipes = register("enable_simple_recipes", new ConfigValue(enableSimple));
			this.miningLevel = register("mining_level", new ConfigValue(miningLevel));
			this.uses = register("uses", new ConfigValue(uses));
			this.speed = register("speed", new ConfigValue(speed));
			this.damage = register("damage", new ConfigValue(damage));
			this.enchantability = register("enchantability", new ConfigValue(enchantability));
			this.durabilityMultiplier = register("durability_multiplier", new ConfigValue(durabilityMultiplier));
			this.helmetArmor = register("helmer_armor", new ConfigValue(helmetArmor));
			this.chestplateArmor = register("chestplate_armor", new ConfigValue(chestplateArmor));
			this.leggingsArmor = register("leggings_armor", new ConfigValue(leggingsArmor));
			this.bootsArmor = register("boots_armor", new ConfigValue(bootsArmor));
			this.toughness = register("toughness", new ConfigValue(toughness));
			this.knockback = register("knockback", new ConfigValue(knockback));
		}
	}
}
