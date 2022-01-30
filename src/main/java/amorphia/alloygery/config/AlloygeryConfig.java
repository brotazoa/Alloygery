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
	@Comment("Enable diamond upgrade at the smithing table")
	public boolean enableDiamondUpgrade = true;

	@ConfigEntry.Gui.NoTooltip
	@Comment("Enable emerald upgrade at the smithing table")
	public boolean enableEmeraldUpgrade = true;

	@ConfigEntry.Gui.NoTooltip
	@Comment("Enable netherite upgrade at the smithing table")
	public boolean enableNetheriteUpgrade = true;

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Tin ore generation")
	public TinOreGeneration tinOre = new TinOreGeneration();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Nickel ore generation")
	public NickelOreGeneration nickelOre = new NickelOreGeneration();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Titanium ore generation")
	public TitaniumOreGeneration titaniumOre = new TitaniumOreGeneration();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Copper gear stats")
	public CopperGearStats copperGear = new CopperGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Bronze gear stats")
	public BronzeGearStats bronzeGear = new BronzeGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Iron gear stats")
	public IronGearStats ironGear = new IronGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Antanium gear stats")
	public AntaniumGearStats antaniumGear = new AntaniumGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Steel gear stats")
	public SteelGearStats steelGear = new SteelGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Nickel gear stats")
	public NickelGearStats nickelGear = new NickelGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Invar gear stats")
	public InvarGearStats invarGear = new InvarGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Constantan gear stats")
	public ConstantanGearStats constantanGear = new ConstantanGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Cupronickel gear stats")
	public CupronickelGearStats cupronickelGear = new CupronickelGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Titanium gear stats")
	public TitaniumGearStats titaniumGear = new TitaniumGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Titanium Gold gear stats")
	public TitaniumGoldGearStats titaniumGoldGear = new TitaniumGoldGearStats();

	@ConfigEntry.Gui.NoTooltip
	@ConfigEntry.Gui.CollapsibleObject
	@Comment("Nitinol gear stats")
	public NitinolGearStats nitinolGear = new NitinolGearStats();

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

					case "enableDiamondUpgrade" -> enableDiamondUpgrade;
					case "enableEmeraldUpgrade" -> enableEmeraldUpgrade;
					case "enableNetheriteUpgrade" -> enableNetheriteUpgrade;

					case "tinOre" -> tinOre.getBooleanValue(parts[1], defaultValue);
					case "nickelOre" -> nickelOre.getBooleanValue(parts[1], defaultValue);
					case "titaniumOre" -> titaniumOre.getBooleanValue(parts[1], defaultValue);

					case "copperGear" -> copperGear.getBooleanValue(parts[1], defaultValue);
					case "bronzeGear" -> bronzeGear.getBooleanValue(parts[1], defaultValue);
					case "ironGear" -> ironGear.getBooleanValue(parts[1], defaultValue);
					case "antaniumGear" -> antaniumGear.getBooleanValue(parts[1], defaultValue);
					case "steelGear" -> steelGear.getBooleanValue(parts[1], defaultValue);
					case "nickelGear" -> nickelGear.getBooleanValue(parts[1], defaultValue);
					case "invarGear" -> invarGear.getBooleanValue(parts[1], defaultValue);
					case "constantanGear" -> constantanGear.getBooleanValue(parts[1], defaultValue);
					case "cupronickelGear" -> cupronickelGear.getBooleanValue(parts[1], defaultValue);
					case "titaniumGear" -> titaniumGear.getBooleanValue(parts[1], defaultValue);
					case "titaniumGoldGear" -> titaniumGoldGear.getBooleanValue(parts[1], defaultValue);
					case "nitinolGear" -> nitinolGear.getBooleanValue(parts[1], defaultValue);

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
					case "tinOre" -> tinOre.getFloatValue(parts[1], defaultValue);
					case "nickelOre" -> nickelOre.getFloatValue(parts[1], defaultValue);
					case "titaniumOre" -> titaniumOre.getFloatValue(parts[1], defaultValue);

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

	interface IOreGeneration
	{
		boolean shouldGenerate();
		int getSize();
		int getNumber();

		default boolean getBooleanValue(String key, boolean defaultValue)
		{
			return key.equals("shouldGenerate") ? shouldGenerate() : defaultValue;
		}

		default int getIntegerValue(String key, int defaultValue)
		{
			return switch (key)
					{
						case "size" -> getSize();
						case "number" -> getNumber();

						default -> defaultValue;
					};
		}

		default float getFloatValue(String key, float defaultValue)
		{
			return defaultValue;
		}
	}

	interface IGearStats
	{
		boolean isEnable();
		int getMiningLevel();
		int getUses();
		float getSpeed();
		float getDamage();
		int getEnchantability();
		int getDurabilityMultiplier();
		int getHelmetArmor();
		int getChestplateArmor();
		int getLeggingsArmor();
		int getBootsArmor();
		float getToughness();
		float getKnockback();

		default boolean getBooleanValue(String key, boolean defaultValue)
		{
			return key.equals("enable") ? isEnable() : defaultValue;
		}

		default int getIntegerValue(String key, int defaultValue)
		{
			return switch (key)
					{
						case "miningLevel" -> getMiningLevel();
						case "uses" -> getUses();
						case "enchantability" -> getEnchantability();
						case "durabilityMultiplier" -> getDurabilityMultiplier();
						case "helmetArmor" -> getHelmetArmor();
						case "chestplateArmor" -> getChestplateArmor();
						case "leggingsArmor" -> getLeggingsArmor();
						case "bootsArmor" -> getBootsArmor();
						default -> defaultValue;
					};
		}

		default float getFloatValue(String key, float defaultValue)
		{
			return switch (key)
					{
						case "speed" -> getSpeed();
						case "damage" -> getDamage();
						case "toughness" -> getToughness();
						case "knockback" -> getKnockback();
						default -> defaultValue;
					};
		}
	}

	public static class TinOreGeneration implements IOreGeneration
	{
		@ConfigEntry.Gui.NoTooltip
		@Comment("Generate ore")
		public boolean shouldGenerate = true;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Average size of patch.")
		public int size = 9;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Average number of patches per chunk.")
		public int number = 50;

		@Override
		public boolean shouldGenerate()
		{
			return shouldGenerate;
		}

		@Override
		public int getSize()
		{
			return size;
		}

		@Override
		public int getNumber()
		{
			return number;
		}
	}

	public static class NickelOreGeneration implements IOreGeneration
	{
		@ConfigEntry.Gui.NoTooltip
		@Comment("Generate ore")
		public boolean shouldGenerate = true;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Average size of patch.")
		public int size = 8;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Average number of patches per chunk.")
		public int number = 10;

		@Override
		public boolean shouldGenerate()
		{
			return shouldGenerate;
		}

		@Override
		public int getSize()
		{
			return size;
		}

		@Override
		public int getNumber()
		{
			return number;
		}
	}

	public static class TitaniumOreGeneration implements IOreGeneration
	{
		@ConfigEntry.Gui.NoTooltip
		@Comment("Generate ore")
		public boolean shouldGenerate = true;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Average size of patch.")
		public int size = 6;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Average number of patches per chunk.")
		public int number = 10;

		@Override
		public boolean shouldGenerate()
		{
			return shouldGenerate;
		}

		@Override
		public int getSize()
		{
			return size;
		}

		@Override
		public int getNumber()
		{
			return number;
		}
	}

	public static class CopperGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.STONE;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 190;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 4.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 1.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 5;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 10;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 1;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 4;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 1;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 0.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class BronzeGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.BRONZE;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 250;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 5.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 2.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 14;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 15;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 2;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 6;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 5;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 2;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 0.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class IronGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.IRON;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 700;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 6.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 2.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 14;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 15;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 2;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 6;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 5;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 2;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 0.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class AntaniumGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.BRONZE;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 858;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 10.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 1.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 20;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 20;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 2;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 5;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 4;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 2;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 0.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class SteelGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.STEEL;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 1561;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 8.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 3.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 10;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 33;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 8;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 6;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 2.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class NickelGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.NICKEL;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 2031;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 9.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 4.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 15;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 37;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 8;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 6;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 2.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.1f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class InvarGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.NICKEL;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 2731;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 9.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 4.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 15;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 50;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 9;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 7;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 3.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.5f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class ConstantanGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.NICKEL;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 2221;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 9.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 4.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 15;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 40;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 8;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 6;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 2.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class CupronickelGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.NICKEL;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 2471;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 9.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 4.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 15;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 45;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 8;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 6;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 3;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 2.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class TitaniumGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.TITANIUM;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 2892;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 10.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 5.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 18;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 53;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 4;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 9;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 7;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 4;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 3.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class TitaniumGoldGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.TITANIUM;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 3127;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 12.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 5.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 28;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 57;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 4;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 9;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 7;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 4;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 3.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.0f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}

	public static class NitinolGearStats implements IGearStats
	{
		//enable
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enable Gear")
		public boolean enable = true;
		//tool stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining level.")
		public int miningLevel = ModMiningLevels.TITANIUM;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability of tools.")
		public int uses = 3362;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Mining and attack speed.")
		public float speed = 10.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Base attack damage.")
		public float damage = 5.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Enchantability")
		public int enchantability = 18;

		//armor stats
		@ConfigEntry.Gui.NoTooltip
		@Comment("Durability multiplier for armor.")
		public int durabilityMultiplier = 62;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by helmet.")
		public int helmetArmor = 4;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by chestplate.")
		public int chestplateArmor = 9;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by leggings.")
		public int leggingsArmor = 7;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Armor points provided by boots.")
		public int bootsArmor = 4;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Toughness points provided by each piece of armor.")
		public float toughness = 3.0f;
		@ConfigEntry.Gui.NoTooltip
		@Comment("Knockback resistance provided by each piece of armor.")
		public float knockback = 0.1f;

		@Override
		public boolean isEnable()
		{
			return enable;
		}

		@Override
		public int getMiningLevel()
		{
			return miningLevel;
		}

		@Override
		public int getUses()
		{
			return uses;
		}

		@Override
		public float getSpeed()
		{
			return speed;
		}

		@Override
		public float getDamage()
		{
			return damage;
		}

		@Override
		public int getEnchantability()
		{
			return enchantability;
		}

		@Override
		public int getDurabilityMultiplier()
		{
			return durabilityMultiplier;
		}

		@Override
		public int getHelmetArmor()
		{
			return helmetArmor;
		}

		@Override
		public int getChestplateArmor()
		{
			return chestplateArmor;
		}

		@Override
		public int getLeggingsArmor()
		{
			return leggingsArmor;
		}

		@Override
		public int getBootsArmor()
		{
			return bootsArmor;
		}

		@Override
		public float getToughness()
		{
			return toughness;
		}

		@Override
		public float getKnockback()
		{
			return knockback;
		}
	}
}
