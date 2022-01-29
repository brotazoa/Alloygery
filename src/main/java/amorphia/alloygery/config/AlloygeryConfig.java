package amorphia.alloygery.config;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.item.ModMiningLevels;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Alloygery.MOD_ID)
public class AlloygeryConfig implements ConfigData
{
	@ConfigEntry.Gui.NoTooltip
	@Comment("Remove vanilla crafting recipes for diamond tools and armor.")
	public boolean disableDiamondEquipment = true;
	@ConfigEntry.Gui.NoTooltip
	@Comment("Make diamond tools and armor ineffective.")
	public boolean makeDiamondGearIneffective = true;

	@ConfigEntry.Gui.NoTooltip
	@Comment("Remove vanilla crafting recipes for netherite tools and armor.")
	public boolean disableNetheriteEquipment = true;
	@ConfigEntry.Gui.NoTooltip
	@Comment("Make netherite tools and armor ineffective.")
	public boolean makeNetheriteGearIneffective = true;

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Tin ore generation")
	public oreGeneration tinOre = new oreGeneration(9, 50);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Nickel ore generation")
	public oreGeneration nickelOre = new oreGeneration(8, 10);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Titanium ore generation")
	public oreGeneration titaniumOre = new oreGeneration(6, 10);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Copper gear stats")
	public gearStats copperGear = new gearStats(ModMiningLevels.STONE, 190, 4.0f, 1.0f, 5, 10, 1, 4, 3, 1, 0.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Bronze gear stats")
	public gearStats bronzeGear = new gearStats(ModMiningLevels.BRONZE, 250, 5.0f, 2.0f, 14, 15, 2, 6, 5, 2, 0.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Iron gear stats")
	public gearStats ironGear = new gearStats(ModMiningLevels.IRON, 700, 6.0f, 2.0f, 14, 15, 2, 6, 5, 2, 0.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Antanium gear stats")
	public gearStats antaniumGear = new gearStats(ModMiningLevels.STONE, 858, 10.0f, 1.0f, 20, 20, 2, 5, 4, 2, 0.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Steel gear stats")
	public gearStats steelGear = new gearStats(ModMiningLevels.STEEL, 1561, 8.0f, 3.0f, 10, 33, 3, 8, 6, 3, 2.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Nickel gear stats")
	public gearStats nickelGear = new gearStats(ModMiningLevels.NICKEL, 2031, 9.0f, 4.0f, 15, 37, 3, 8, 6, 3, 2.0f, 0.1f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Invar gear stats")
	public gearStats invarGear = new gearStats(ModMiningLevels.NICKEL, 2731, 9.0f, 4.0f, 15, 50, 3, 9, 7, 3, 3.0f, 0.5f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Constantan gear stats")
	public gearStats constantanGear = new gearStats(ModMiningLevels.NICKEL, 2221, 9.0f, 4.0f, 15, 40, 3, 8, 6, 3, 2.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Cupronickel gear stats")
	public gearStats cupronickelGear = new gearStats(ModMiningLevels.NICKEL, 2471, 9.0f, 4.0f, 15, 45, 3, 9, 7, 3, 2.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Titanium gear stats")
	public gearStats titaniumGear = new gearStats(ModMiningLevels.TITANIUM, 2892, 10.0f, 5.0f, 18, 53, 4, 9, 7, 4, 3.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Titanium Gold gear stats")
	public gearStats titaniumGoldGear = new gearStats(ModMiningLevels.TITANIUM, 3127, 12.0f, 5.0f, 25, 57, 4, 9, 7, 4, 3.0f, 0.0f);

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Nitinol gear stats")
	public gearStats nitinolGear = new gearStats(ModMiningLevels.TITANIUM, 3362, 10.0f, 5.0f, 18, 62, 4, 9, 7, 4, 3.0f, 0.1f);

	//this is an unmaintainable abomination
	//it is better than reflection imo
	public boolean getBooleanValue(String configKey, boolean defaultValue)
	{
		String[] parts = configKey.split("\\.");
		if(parts.length > 2)
			return defaultValue;

		return switch (parts[0])
		{
			case "disableDiamondEquipment" -> disableDiamondEquipment;
			case "makeDiamondGearIneffective" -> makeDiamondGearIneffective;
			case "disableNetheriteEquipment" -> disableNetheriteEquipment;
			case "makeNetheriteGearIneffective" -> makeNetheriteGearIneffective;

			case "tinOre" -> tinOre.getBooleanValue(parts[1], defaultValue);
			case "nickelOre" -> nickelOre.getBooleanValue(parts[1], defaultValue);
			case "titaniumOre" -> titaniumOre.getBooleanValue(parts[1], defaultValue);

			default -> defaultValue;
		};
	}

	//this is also an abomination
	public int getIntegerValue(String configKey, int defaultValue)
	{
		String[] parts = configKey.split("\\.");
		if(parts.length > 2)
			return defaultValue;

		return switch (parts[0])
		{
			case "tinOre" -> tinOre.getIntegerValue(parts[1], defaultValue);
			case "nickelOre" -> nickelOre.getIntegerValue(parts[1], defaultValue);
			case "titaniumOre" -> titaniumOre.getIntegerValue(parts[1], defaultValue);

			case "copperGear" -> copperGear.getIntegerValue(parts[1], defaultValue);
			case "bronzeGear" -> bronzeGear.getIntegerValue(parts[1], defaultValue);
			case "ironGear" -> ironGear.getIntegerValue(parts[1], defaultValue);
			case "antaniumGear" -> antaniumGear.getIntegerValue(parts[1], defaultValue);
			case "steelGear" -> steelGear.getIntegerValue(parts[1], defaultValue);
			case "nickelGear" -> nickelGear.getIntegerValue(parts[1], defaultValue);
			case "invarGear" -> invarGear.getIntegerValue(parts[1], defaultValue);
			case "constantanGear" -> constantanGear.getIntegerValue(parts[1], defaultValue);
			case "cupronickelGear" -> cupronickelGear.getIntegerValue(parts[1], defaultValue);
			case "titaniumGear" -> titaniumGear.getIntegerValue(parts[1], defaultValue);
			case "titaniumGoldGear" -> titaniumGoldGear.getIntegerValue(parts[1], defaultValue);
			case "nitinolGear" -> nitinolGear.getIntegerValue(parts[1], defaultValue);

			default -> defaultValue;
		};
	}

	//oh look! an abomination
	//but this is what we have to do until fabric has a proper config api of its own
	public float getFloatValue(String configKey, float defaultValue)
	{
		String[] parts = configKey.split("\\.");
		if(parts.length > 2)
			return defaultValue;

		return switch (parts[0])
		{
			case "copperGear" -> copperGear.getFloatValue(parts[1], defaultValue);
			case "bronzeGear" -> bronzeGear.getFloatValue(parts[1], defaultValue);
			case "ironGear" -> ironGear.getFloatValue(parts[1], defaultValue);
			case "antaniumGear" -> antaniumGear.getFloatValue(parts[1], defaultValue);
			case "steelGear" -> steelGear.getFloatValue(parts[1], defaultValue);
			case "nickelGear" -> nickelGear.getFloatValue(parts[1], defaultValue);
			case "invarGear" -> invarGear.getFloatValue(parts[1], defaultValue);
			case "constantanGear" -> constantanGear.getFloatValue(parts[1], defaultValue);
			case "cupronickelGear" -> cupronickelGear.getFloatValue(parts[1], defaultValue);
			case "titaniumGear" -> titaniumGear.getFloatValue(parts[1], defaultValue);
			case "titaniumGoldGear" -> titaniumGoldGear.getFloatValue(parts[1], defaultValue);
			case "nitinolGear" -> nitinolGear.getFloatValue(parts[1], defaultValue);

			default -> defaultValue;
		};
	}

	public static class oreGeneration
	{
		@ConfigEntry.Gui.NoTooltip
		@Comment("Generate ore")
		public boolean shouldGenerate = true;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Average size of patch.")
		public int size;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Average number of patches per chunk.")
		public int number;

		oreGeneration(int size, int number)
		{
			this.size = size;
			this.number = number;
		}

		public boolean getBooleanValue(String key, boolean defaultValue)
		{
			return key.equals("shouldGenerate") ? shouldGenerate : defaultValue;
		}

		public int getIntegerValue(String key, int defaultValue)
		{
			return switch (key)
			{
				case "size" -> size;
				case "number" -> number;

				default -> defaultValue;
			};
		}
	}

	public static class gearStats
	{
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback;

		gearStats(int miningLevel, int uses, float speed, float damage, int enchantability, int durabilityMultiplier, int helmetArmor,
				int chestplateArmor, int leggingsArmor, int bootsArmor, float toughness, float knockback)
		{
			this.miningLevel = miningLevel;
			this.uses = uses;
			this.speed = speed;
			this.damage = damage;
			this.enchantability = enchantability;
			this.durabilityMultiplier = durabilityMultiplier;
			this.helmetArmor = helmetArmor;
			this.chestplateArmor = chestplateArmor;
			this.leggingsArmor = leggingsArmor;
			this.bootsArmor = bootsArmor;
			this.toughness = toughness;
			this.knockback = knockback;
		}

		public int getIntegerValue(String key, int defaultValue)
		{
			return switch (key)
			{
				case "miningLevel" -> miningLevel;
				case "uses" -> uses;
				case "enchantability" -> enchantability;
				case "durabilityMultiplier" -> durabilityMultiplier;
				case "helmetArmor" -> helmetArmor;
				case "chestplateArmor" -> chestplateArmor;
				case "leggingsArmor" -> leggingsArmor;
				case "bootsArmor" -> bootsArmor;
				default -> defaultValue;
			};
		}

		public float getFloatValue(String key, float defaultValue)
		{
			return switch (key)
			{
				case "speed" -> speed;
				case "damage" -> damage;
				case "toughness" -> toughness;
				case "knockback" -> knockback;
				default -> defaultValue;
			};
		}
	}
}
