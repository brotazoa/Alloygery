package amorphia.alloygery.config;

import amorphia.alloygery.content.item.ModMiningLevels;

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

	//@formatter:off
	public static final ConfigValue disableDiamondEquipment;
	public static final ConfigValue makeDiamondGearIneffective;
	public static final ConfigValue disableNetheriteEquipment;
	public static final ConfigValue makeNetheriteGearIneffective;
	public static final ConfigValue enableDiamondUpgrade;
	public static final ConfigValue enableEmeraldUpgrade;
	public static final ConfigValue enableNetheriteUpgrade;

	public static final OreGenerationConfigGroup tinOre;
	public static final OreGenerationConfigGroup nickelOre;
	public static final OreGenerationConfigGroup titaniumOre;

	public static final GearStatsConfigGroup copperGear;
	public static final GearStatsConfigGroup bronzeGear;
	public static final GearStatsConfigGroup ironGear;
	public static final GearStatsConfigGroup antaniumGear;
	public static final GearStatsConfigGroup steelGear;
	public static final GearStatsConfigGroup nickelGear;
	public static final GearStatsConfigGroup invarGear;
	public static final GearStatsConfigGroup constantanGear;
	public static final GearStatsConfigGroup cupronickelGear;
	public static final GearStatsConfigGroup titaniumGear;
	public static final GearStatsConfigGroup titaniumGoldGear;
	public static final GearStatsConfigGroup nitinolGear;

	//@formatter:on

	static
	{
		//@formatter:off
		disableDiamondEquipment = ALLOYGERY_CONFIG.register("disable_diamond_gear", new ConfigValue(true));
		makeDiamondGearIneffective = ALLOYGERY_CONFIG.register("make_diamond_gear_ineffective", new ConfigValue(true));
		disableNetheriteEquipment = ALLOYGERY_CONFIG.register("disable_netherite_gear", new ConfigValue(true));
		makeNetheriteGearIneffective = ALLOYGERY_CONFIG.register("make_netherite_gear_ineffective", new ConfigValue(true));
		enableDiamondUpgrade = ALLOYGERY_CONFIG.register("enable_diamond_upgrade", new ConfigValue(true));
		enableEmeraldUpgrade = ALLOYGERY_CONFIG.register("enable_emerald_upgrade", new ConfigValue(true));
		enableNetheriteUpgrade = ALLOYGERY_CONFIG.register("enable_netherite_upgrade", new ConfigValue(true));

		tinOre = ALLOYGERY_CONFIG.register("tin_ore", new OreGenerationConfigGroup(true, 9, 50));
		nickelOre = ALLOYGERY_CONFIG.register("nickel_ore", new OreGenerationConfigGroup(true, 8, 10));
		titaniumOre = ALLOYGERY_CONFIG.register("titanium_ore", new OreGenerationConfigGroup(true, 6, 10));

		copperGear = ALLOYGERY_CONFIG.register("copper_gear", new GearStatsConfigGroup(true, ModMiningLevels.STONE, 190, 4.0f, 1.0f, 5, 10, 1, 4, 3, 1, 0.0f, 0.0f));
		bronzeGear = ALLOYGERY_CONFIG.register("bronze_gear", new GearStatsConfigGroup(true, ModMiningLevels.BRONZE, 250, 5.0f, 2.0f, 14, 15, 2, 6, 5, 2, 0.0f, 0.0f));
		ironGear = ALLOYGERY_CONFIG.register("iron_gear", new GearStatsConfigGroup(true, ModMiningLevels.IRON, 700, 6.0f, 2.0f, 14, 15, 2, 6, 5, 2, 0.0f, 0.0f));
		antaniumGear = ALLOYGERY_CONFIG.register("antanium_gear", new GearStatsConfigGroup(true, ModMiningLevels.BRONZE, 858, 10.0f, 1.0f, 20, 20, 2, 5, 4, 2, 0.0f, 0.0f));
		steelGear = ALLOYGERY_CONFIG.register("steel_gear", new GearStatsConfigGroup(true, ModMiningLevels.STEEL, 1461, 8.0f, 3.0f, 10, 33, 3, 8, 6, 3, 2.0f, 0.0f));
		nickelGear = ALLOYGERY_CONFIG.register("nickel_gear", new GearStatsConfigGroup(true, ModMiningLevels.NICKEL, 2031, 9.0f, 4.0f, 15, 37, 3, 8, 6, 3, 2.0f, 0.1f));
		invarGear = ALLOYGERY_CONFIG.register("invar_gear", new GearStatsConfigGroup(true, ModMiningLevels.NICKEL, 2731, 9.0f, 4.0f, 15, 50, 3, 9, 7, 3, 3.0f, 0.5f));
		constantanGear = ALLOYGERY_CONFIG.register("constantan_gear", new GearStatsConfigGroup(true, ModMiningLevels.NICKEL, 2221, 9.0f, 4.0f, 15, 40, 3, 8, 6, 3, 2.0f, 0.0f));
		cupronickelGear = ALLOYGERY_CONFIG.register("cupronickel_gear", new GearStatsConfigGroup(true, ModMiningLevels.NICKEL, 2471, 9.0f, 4.0f, 15, 45, 3, 8, 6, 3, 2.0f, 0.0f));
		titaniumGear = ALLOYGERY_CONFIG.register("titanium_gear", new GearStatsConfigGroup(true, ModMiningLevels.TITANIUM, 2892, 10.0f, 5.0f, 18, 53, 4, 9, 7, 4, 3.0f, 0.0f));
		titaniumGoldGear = ALLOYGERY_CONFIG.register("titanium_gold_gear", new GearStatsConfigGroup(true, ModMiningLevels.TITANIUM, 3127, 12.0f, 5.0f, 28, 57, 4, 9, 7, 4, 3.0f, 0.0f));
		nitinolGear = ALLOYGERY_CONFIG.register("nitinol_gear", new GearStatsConfigGroup(true, ModMiningLevels.TITANIUM, 3362, 10.0f, 5.0f, 18, 62, 4, 9, 7, 4, 3.0f, 0.1f));
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

		public GearStatsConfigGroup(boolean enable, int miningLevel, int uses, float speed, float damage, int enchantability,
				int durabilityMultiplier, int helmetArmor, int chestplateArmor, int leggingsArmor, int bootsArmor, float toughness, float knockback)
		{
			this.enable = register("enable", new ConfigValue(enable));
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
