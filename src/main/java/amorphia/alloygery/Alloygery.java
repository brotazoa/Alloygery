package amorphia.alloygery;

import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Alloygery implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "alloygery";

	public static AlloygeryConfig CONFIG = null;

	public static ItemGroup ALLOYGERY_GROUP_BLOCKS = FabricItemGroupBuilder.create(Alloygery.identifier("blocks")).icon(() -> new ItemStack(ModItems.BLAST_ALLOY_KILN)).build();
	public static ItemGroup ALLOYGERY_GROUP_MATERIALS = FabricItemGroupBuilder.create(Alloygery.identifier("materials")).icon(() -> new ItemStack(ModItems.BRONZE_INGOT)).build();
	public static ItemGroup ALLOYGERY_GROUP_GEAR = FabricItemGroupBuilder.create(Alloygery.identifier("gear")).icon(() -> new ItemStack(ModItems.BRONZE_CHESTPLATE)).build();
	public static ItemGroup ALLOYGERY_GROUP_PARTS = FabricItemGroupBuilder.create(Alloygery.identifier("parts")).icon(() -> new ItemStack(ModItems.BRONZE_AXE_HEAD)).build();

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
