package amorphia.alloygery.content.metals.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.metals.client.CraftingItemModelBuilder;
import amorphia.alloygery.content.metals.item.*;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.EnumSet;
import java.util.Map;
import java.util.function.Supplier;

import static amorphia.alloygery.content.metals.item.CraftingMaterialItemTypes.*;
import static amorphia.alloygery.content.metals.item.CraftingMaterialVariantTypes.*;
import static amorphia.alloygery.content.metals.item.CraftingToolTypes.*;
import static amorphia.alloygery.content.tools.material.AlloygeryToolMaterials.*;

public class MetalItemRegistry
{
	public static final Map<String, Item> ITEMS = Maps.newHashMap();

	public static void init()
	{
		//ore blocks
		register("tin_ore", new BlockItem(MetalBlockRegistry.TIN_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)));
		register("deepslate_tin_ore", new BlockItem(MetalBlockRegistry.DEEPSLATE_TIN_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)));
		register("nickel_ore", new BlockItem(MetalBlockRegistry.NICKEL_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)));
		register("titanium_ore", new BlockItem(MetalBlockRegistry.TITANIUM_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)));

		//raw ore blocks
		makeCraftingMaterials(TIN, EnumSet.of(RAW_BLOCK), DULL);
		makeCraftingMaterials(NICKEL, EnumSet.of(RAW_BLOCK), DULL);
		makeCraftingMaterials(TITANIUM, EnumSet.of(RAW_BLOCK), NORMAL);

		//metal blocks
		makeCraftingMaterials(TIN, EnumSet.of(BLOCK, STAIR, SLAB), NORMAL);
		makeCraftingMaterials(BRONZE, EnumSet.of(BLOCK, STAIR, SLAB), NORMAL);
		makeCraftingMaterials(ANTANIUM, EnumSet.of(BLOCK, STAIR, SLAB), SHINY);
		makeCraftingMaterials(STEEL, EnumSet.of(BLOCK, STAIR, SLAB, FENCE, FENCE_GATE), NORMAL);
		makeCraftingMaterials(NICKEL, EnumSet.of(BLOCK, STAIR, SLAB), DULL);
		makeCraftingMaterials(INVAR, EnumSet.of(BLOCK, STAIR, SLAB), DULL);
		makeCraftingMaterials(CONSTANTAN, EnumSet.of(BLOCK, STAIR, SLAB), DULL);
		makeCraftingMaterials(CUPRONICKEL, EnumSet.of(BLOCK, STAIR, SLAB), DULL);
		makeCraftingMaterials(TITANIUM, EnumSet.of(BLOCK, STAIR, SLAB), NORMAL);
		makeCraftingMaterials(TITANIUM_GOLD, EnumSet.of(BLOCK, STAIR, SLAB), SHINY);
		makeCraftingMaterials(NITINOL, EnumSet.of(BLOCK, STAIR, SLAB), SHINY);

		//metal items
		makeCraftingMaterials(TIN, EnumSet.of(RAW, NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), DULL);
		makeCraftingMaterials(COPPER, EnumSet.of(NUGGET, DOUBLE_INGOT, PLATE, HEAVY_PLATE), NORMAL);
		makeCraftingMaterials(BRONZE, EnumSet.of(NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), DULL);
		makeCraftingMaterials(IRON, EnumSet.of(DOUBLE_INGOT, PLATE, HEAVY_PLATE), NORMAL);
		makeCraftingMaterials(GOLD, EnumSet.of(DOUBLE_INGOT, PLATE, HEAVY_PLATE), SHINY);
		makeCraftingMaterials(ANTANIUM, EnumSet.of(NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), SHINY);
		makeCraftingMaterials(STEEL, EnumSet.of(NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), NORMAL);
		makeCraftingMaterials(NICKEL, EnumSet.of(RAW, NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), DULL);
		makeCraftingMaterials(INVAR, EnumSet.of(NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), DULL);
		makeCraftingMaterials(CONSTANTAN, EnumSet.of(NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), DULL);
		makeCraftingMaterials(CUPRONICKEL, EnumSet.of(NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), DULL);
		makeCraftingMaterials(TITANIUM, EnumSet.of(RAW, NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), NORMAL);
		makeCraftingMaterials(TITANIUM_GOLD, EnumSet.of(NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), SHINY);
		makeCraftingMaterials(NITINOL, EnumSet.of(NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE), SHINY);

		//crafting tools
//		makeCraftingTools(COPPER, EnumSet.of(HAMMER), 0);
//		makeCraftingTools(BRONZE, EnumSet.of(HAMMER), 1);
		makeCraftingTools(IRON, EnumSet.of(HAMMER), 1);
//		makeCraftingTools(GOLD, EnumSet.of(HAMMER), 0);
//		makeCraftingTools(ANTANIUM, EnumSet.of(HAMMER), 1);
		makeCraftingTools(STEEL, EnumSet.of(HAMMER), 2);
//		makeCraftingTools(NICKEL, EnumSet.of(HAMMER), 2);
		makeCraftingTools(INVAR, EnumSet.of(HAMMER), 3);
//		makeCraftingTools(CONSTANTAN, EnumSet.of(HAMMER), 2);
//		makeCraftingTools(CUPRONICKEL, EnumSet.of(HAMMER), 2);
		makeCraftingTools(TITANIUM, EnumSet.of(HAMMER), 3);
//		makeCraftingTools(TITANIUM_GOLD, EnumSet.of(HAMMER), 3);
//		makeCraftingTools(NITINOL, EnumSet.of(HAMMER), 4);
	}

	private static void makeCraftingMaterials(AlloygeryToolMaterial material, EnumSet<CraftingMaterialItemTypes> materialTypes, amorphia.alloygery.content.metals.item.CraftingMaterialVariantTypes variant)
	{
		if(materialTypes.contains(RAW))
			registerGeneratedItem("raw_" + material.getMaterialName(), new CraftingItem(material), CraftingItemModelBuilder.createRawOreItemModelJson());

		if(materialTypes.contains(NUGGET))
			registerGeneratedItem(material.getMaterialName() + "_nugget", new CraftingItem(material), CraftingItemModelBuilder.createNuggetItemModelJson(variant));

		if(materialTypes.contains(INGOT))
			registerGeneratedItem(material.getMaterialName() + "_ingot", new CraftingItem(material), CraftingItemModelBuilder.createIngotItemModelJson(variant));

		if(materialTypes.contains(DOUBLE_INGOT))
			registerGeneratedItem(material.getMaterialName() + "_double_ingot", new CraftingItem(material), CraftingItemModelBuilder.createDoubleIngotItemModelJson(variant));

		if(materialTypes.contains(PLATE))
			registerGeneratedItem(material.getMaterialName() + "_plate", new CraftingItem(material), CraftingItemModelBuilder.createPlateItemModelJson(variant));

		if(materialTypes.contains(HEAVY_PLATE))
			registerGeneratedItem(material.getMaterialName() + "_heavy_plate", new CraftingItem(material), CraftingItemModelBuilder.createHeavyPlateItemModelJson(variant));

		if(materialTypes.contains(BLOCK))
		{
			Block parent = MetalBlockRegistry.BLOCKS.get(material.getMaterialName() + "_block");
			if(parent != null)
				registerGeneratedItem(material.getMaterialName() + "_block", new TintedBlockItem(material, parent, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)), CraftingItemModelBuilder.createBlockItemModelJson(material.getMaterialName() + "_block"));
		}

		if(materialTypes.contains(RAW_BLOCK))
		{
			Block parent = MetalBlockRegistry.BLOCKS.get("raw_" + material.getMaterialName() + "_block");
			if(parent != null)
				registerGeneratedItem("raw_" + material.getMaterialName() + "_block", new TintedBlockItem(material, parent, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)), CraftingItemModelBuilder.createBlockItemModelJson("raw_" + material.getMaterialName() + "_block"));
		}

		if (materialTypes.contains(STAIR))
		{
			Block parent = MetalBlockRegistry.BLOCKS.get(material.getMaterialName() + "_stairs");
			if(parent != null)
			{
				registerGeneratedItem(
						material.getMaterialName() + "_stairs",
						new TintedBlockItem(material, parent, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)),
						CraftingItemModelBuilder.createBlockItemModelJson(material.getMaterialName() + "_stairs")
				);
			}
		}

		if (materialTypes.contains(SLAB))
		{
			Block parent = MetalBlockRegistry.BLOCKS.get(material.getMaterialName() + "_slab");
			if(parent != null)
			{
				registerGeneratedItem(
						material.getMaterialName() + "_slab",
						new TintedBlockItem(material, parent, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)),
						CraftingItemModelBuilder.createBlockItemModelJson(material.getMaterialName() + "_slab")
				);
			}
		}

		if (materialTypes.contains(SLOPE))
		{
			Block parent = MetalBlockRegistry.BLOCKS.get(material.getMaterialName() + "_slope");
			if(parent != null)
			{
				registerGeneratedItem(
						material.getMaterialName() + "_slope",
						new TintedBlockItem(material, parent, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)),
						CraftingItemModelBuilder.createBlockItemModelJson(material.getMaterialName() + "_slope")
				);
			}
		}

		if (materialTypes.contains(FENCE))
		{

		}

		if (materialTypes.contains(FENCE_GATE))
		{

		}
	}

	private static void makeCraftingTools(AlloygeryToolMaterial material, EnumSet<CraftingToolTypes> toolTypes, int tier)
	{
		if(toolTypes.contains(HAMMER))
			registerGeneratedItem(material.getMaterialName() + "_crafting_hammer", new CraftingHammer(material, tier), CraftingItemModelBuilder.createCraftingHammerItemModelJson());
	}

	private static Item registerGeneratedItem(String path, Item item, Supplier<String> modelJsonSupplier)
	{
		Identifier identifier = Alloygery.identifier("item/" + path);
		CraftingItemModelBuilder.register(identifier, modelJsonSupplier);
		return register(path, item);
	}

	private static Item register(String path, Item item)
	{
		ITEMS.put(path, item);
		return Registry.register(Registry.ITEM, Alloygery.identifier(path), item);
	}
}
