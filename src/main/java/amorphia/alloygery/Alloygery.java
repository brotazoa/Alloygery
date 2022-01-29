package amorphia.alloygery;

import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.registry.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Alloygery implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "alloygery";

	public static AlloygeryConfig CONFIG = null;

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
		AutoConfig.register(AlloygeryConfig.class, JanksonConfigSerializer::new);
		AutoConfig.getConfigHolder(AlloygeryConfig.class).load();
		Alloygery.CONFIG = AutoConfig.getConfigHolder(AlloygeryConfig.class).getConfig();

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
