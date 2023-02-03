package amorphia.alloygery.config;

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

	public static final ConfigValue disableVanillaWoodenToolCrafting;
	public static final ConfigValue disableVanillaDiamondCrafting;
	public static final ConfigValue disableVanillaIronCrafting;

	public static final ConfigValue showDynamicToolsInRecipeViewer;
	public static final ConfigValue showDynamicArmorsInRecipeViewer;

	public static final OreGenerationConfigGroup tinOre;
	public static final OreGenerationConfigGroup nickelOre;
	public static final OreGenerationConfigGroup titaniumOre;

	static
	{
		//@formatter:off
		disableVanillaWoodenToolCrafting = ALLOYGERY_CONFIG.register("disable_wooden_tool_crafting", new ConfigValue(false));
		disableVanillaDiamondCrafting = ALLOYGERY_CONFIG.register("disable_diamond_crafting", new ConfigValue(true));
		disableVanillaIronCrafting = ALLOYGERY_CONFIG.register("disable_iron_crafting", new ConfigValue(true));

		showDynamicToolsInRecipeViewer = ALLOYGERY_CONFIG.register("show_tools_in_recipe_viewers", new ConfigValue(false));
		showDynamicArmorsInRecipeViewer = ALLOYGERY_CONFIG.register("show_armors_in_recipe_viewers", new ConfigValue(false));

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
}
