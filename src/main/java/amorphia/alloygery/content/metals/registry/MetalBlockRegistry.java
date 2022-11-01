package amorphia.alloygery.content.metals.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.metals.block.TintedBlock;
import amorphia.alloygery.content.metals.client.CraftingItemModelBuilder;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import static amorphia.alloygery.content.materials.AlloygeryMaterials.*;

public class MetalBlockRegistry
{
	public static final Map<String, Block> BLOCKS = new LinkedHashMap<>();

	//ore blocks must be static for ore generation
	public static final Block TIN_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 3.0f));
	public static final Block DEEPSLATE_TIN_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4.5f, 3.0f));
	public static final Block NICKEL_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 9.0f));
	public static final Block TITANIUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 12.0f));

	public static void init()
	{
		register("tin_ore", TIN_ORE);
		register("deepslate_tin_ore", DEEPSLATE_TIN_ORE);
		register("nickel_ore", NICKEL_ORE);
		register("titanium_ore", TITANIUM_ORE);

		makeRawOreBlock(TIN);
		makeRawOreBlock(NICKEL);
		makeRawOreBlock(TITANIUM);

		makeMetalBlock(TIN, MetalItemRegistry.CraftingMaterialVariantTypes.NORMAL);
		makeMetalBlock(BRONZE, MetalItemRegistry.CraftingMaterialVariantTypes.NORMAL);
		makeMetalBlock(ANTANIUM, MetalItemRegistry.CraftingMaterialVariantTypes.SHINY);
		makeMetalBlock(STEEL, MetalItemRegistry.CraftingMaterialVariantTypes.NORMAL);
		makeMetalBlock(NICKEL, MetalItemRegistry.CraftingMaterialVariantTypes.DULL);
		makeMetalBlock(INVAR, MetalItemRegistry.CraftingMaterialVariantTypes.DULL);
		makeMetalBlock(CONSTANTAN, MetalItemRegistry.CraftingMaterialVariantTypes.DULL);
		makeMetalBlock(CUPRONICKEL, MetalItemRegistry.CraftingMaterialVariantTypes.DULL);
		makeMetalBlock(TITANIUM, MetalItemRegistry.CraftingMaterialVariantTypes.NORMAL);
		makeMetalBlock(TITANIUM_GOLD, MetalItemRegistry.CraftingMaterialVariantTypes.SHINY);
		makeMetalBlock(NITINOL, MetalItemRegistry.CraftingMaterialVariantTypes.SHINY);
	}

	private static void makeMetalBlock(AlloygeryMaterial material)
	{
		makeMetalBlock(material, MetalItemRegistry.CraftingMaterialVariantTypes.NORMAL);
	}

	private static void makeMetalBlock(AlloygeryMaterial material, MetalItemRegistry.CraftingMaterialVariantTypes type)
	{
		registerGeneratedBlock(material.getMaterialName() + "_block", new TintedBlock(material, FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)), CraftingItemModelBuilder.createMetalBlockModelJson(type.getName()));
	}

	private static void makeRawOreBlock(AlloygeryMaterial material)
	{
		registerGeneratedBlock("raw_" + material.getMaterialName() + "_block", new TintedBlock(material, FabricBlockSettings.of(Material.STONE).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f)), CraftingItemModelBuilder::createRawOreBlockModelJson);
	}

	private static void registerGeneratedBlock(String path, Block block, Supplier<String> modelJsonSupplier)
	{
		Identifier identifier = Alloygery.identifier("block/" + path);
		CraftingItemModelBuilder.register(identifier, modelJsonSupplier);
		register(path, block);
	}

	private static void register(String path, Block block)
	{
		BLOCKS.put(path, block);
		Registry.register(Registry.BLOCK, Alloygery.identifier(path), block);
	}
}
