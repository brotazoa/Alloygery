package amorphia.alloygery;

import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.config.AlloygeryConfigSerializer;
import amorphia.alloygery.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Alloygery implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "alloygery";

	public static final String NBT_KEY = "AlloygeryProperties";
	public static final String DIAMOND_TIPPED_KEY = "DiamondTipped";
	public static final String EMERALD_EMBOSSED_KEY = "EmeraldEmbossed";
	public static final String NETHERITE_PLATTED = "NetheritePlatted";

	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient()
	{
		ModScreens.registerClient();
	}

	@Override
	public void onInitialize()
	{
		AlloygeryConfig.loadFromFile();

		ModResourceConditions.register();

		ModBlocks.register();
		ModItems.register();
		ModOreGeneration.register();
		ModRecipes.register();
		ModScreens.register();
		ModStatistics.register();
	}

	public static Identifier identifier(String path)
	{
		return new Identifier(MOD_ID, path);
	}
}
