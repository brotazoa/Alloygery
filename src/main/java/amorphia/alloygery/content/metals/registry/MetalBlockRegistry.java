package amorphia.alloygery.content.metals.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.metals.block.CraftingMaterialBlockTypes;
import amorphia.alloygery.content.metals.block.TintedBlock;
import amorphia.alloygery.content.metals.block.TintedSlabBlock;
import amorphia.alloygery.content.metals.block.TintedStairsBlock;
import amorphia.alloygery.content.metals.client.CraftingItemModelBuilder;
import amorphia.alloygery.content.metals.item.CraftingMaterialVariantTypes;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import static amorphia.alloygery.content.metals.block.CraftingMaterialBlockTypes.*;
import static amorphia.alloygery.content.tools.material.AlloygeryToolMaterials.*;

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

		makeBlocksForMaterial(TIN, EnumSet.of(BLOCK, STAIR, SLAB, RAW), CraftingMaterialVariantTypes.NORMAL);
		makeBlocksForMaterial(BRONZE, EnumSet.of(BLOCK, STAIR, SLAB), CraftingMaterialVariantTypes.NORMAL);
		makeBlocksForMaterial(ANTANIUM, EnumSet.of(BLOCK, STAIR, SLAB), CraftingMaterialVariantTypes.SHINY);
		makeBlocksForMaterial(STEEL, EnumSet.of(BLOCK, STAIR, SLAB, SLOPE, FENCE, FENCE_GATE), CraftingMaterialVariantTypes.NORMAL);
		makeBlocksForMaterial(NICKEL, EnumSet.of(BLOCK, STAIR, SLAB, RAW), CraftingMaterialVariantTypes.DULL);
		makeBlocksForMaterial(INVAR, EnumSet.of(BLOCK, STAIR, SLAB), CraftingMaterialVariantTypes.DULL);
		makeBlocksForMaterial(CONSTANTAN, EnumSet.of(BLOCK, STAIR, SLAB), CraftingMaterialVariantTypes.DULL);
		makeBlocksForMaterial(CUPRONICKEL, EnumSet.of(BLOCK, STAIR, SLAB), CraftingMaterialVariantTypes.DULL);
		makeBlocksForMaterial(TITANIUM, EnumSet.of(BLOCK, STAIR, SLAB, RAW), CraftingMaterialVariantTypes.NORMAL);
		makeBlocksForMaterial(TITANIUM_GOLD, EnumSet.of(BLOCK, STAIR, SLAB), CraftingMaterialVariantTypes.SHINY);
		makeBlocksForMaterial(NITINOL, EnumSet.of(BLOCK, STAIR, SLAB), CraftingMaterialVariantTypes.SHINY);
	}

	private static void makeBlocksForMaterial(AlloygeryToolMaterial material, EnumSet<CraftingMaterialBlockTypes> blockTypes, CraftingMaterialVariantTypes variantType)
	{
		if (blockTypes.contains(BLOCK))
		{
			registerGeneratedBlock(
					material.getMaterialName() + "_block",
					new TintedBlock(material, FabricBlockSettings.of(Material.METAL).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)),
					CraftingItemModelBuilder.createMetalBlockModelJson(variantType)
			);
		}

		if (blockTypes.contains(STAIR))
		{
			Block parent = BLOCKS.get(material.getMaterialName() + "_block");
			if(parent == null) throw new IllegalStateException("Stair block requires parent block state");

			registerGeneratedBlock(
					material.getMaterialName() + "_stairs",
					new TintedStairsBlock(material, parent.getDefaultState(), FabricBlockSettings.copyOf(parent)),
					CraftingItemModelBuilder.createMetalStairsBlockModelJson(variantType)
			);
		}

		if (blockTypes.contains(SLAB))
		{
			Block parent = BLOCKS.get(material.getMaterialName() + "_block");
			if(parent == null) throw new IllegalStateException("Slab block requires parent block state");

			registerGeneratedBlock(
					material.getMaterialName() + "_slab",
					new TintedSlabBlock(material, FabricBlockSettings.copyOf(parent)),
					CraftingItemModelBuilder.createMetalSlabBlockModelJson(variantType)
			);
		}

		if (blockTypes.contains(SLOPE))
		{
			Block parent = BLOCKS.get(material.getMaterialName() + "_block");
			if(parent == null) throw new IllegalStateException("Slope block requires parent block state");

			registerGeneratedBlock(
					material.getMaterialName() + "_slope",
					new TintedStairsBlock(material, parent.getDefaultState(), FabricBlockSettings.copyOf(parent)),
					CraftingItemModelBuilder.createMetalSlopeBlockModelJson(variantType)
			);
		}

		if (blockTypes.contains(FENCE))
		{

		}

		if (blockTypes.contains(FENCE_GATE))
		{

		}

		if (blockTypes.contains(RAW))
		{
			registerGeneratedBlock(
					"raw_" + material.getMaterialName() + "_block",
					new TintedBlock(material, FabricBlockSettings.of(Material.STONE).requiresTool().strength(5.0f, 6.0f)),
					CraftingItemModelBuilder.createRawOreBlockModelJson()
			);
		}
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
