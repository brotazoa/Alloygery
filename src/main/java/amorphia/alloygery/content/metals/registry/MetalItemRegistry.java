package amorphia.alloygery.content.metals.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.metals.client.CraftingItemModelBuilder;
import amorphia.alloygery.content.metals.item.CraftingHammer;
import amorphia.alloygery.content.metals.item.CraftingItem;
import amorphia.alloygery.content.metals.item.TintedBlockItem;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.EnumSet;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import static amorphia.alloygery.content.metals.registry.MetalItemRegistry.CraftingMaterialTypes.*;
import static amorphia.alloygery.content.metals.registry.MetalItemRegistry.CraftingMaterialVariantTypes.*;
import static amorphia.alloygery.content.metals.registry.MetalItemRegistry.CraftingToolTypes.*;
import static amorphia.alloygery.content.materials.AlloygeryMaterials.*;

public class MetalItemRegistry
{
	public static final Map<String, Item> ITEMS = Maps.newHashMap();

	//TODO: move all of these enum out of here
	enum CraftingMaterialTypes
	{
		RAW, NUGGET, INGOT, DOUBLE_INGOT, PLATE, HEAVY_PLATE, BLOCK, RAW_BLOCK;

		public static final EnumSet<CraftingMaterialTypes> CRAFTING_MATERIAL_TYPES = EnumSet.allOf(CraftingMaterialTypes.class);
	}

	enum CraftingMaterialVariantTypes
	{
		NORMAL, DULL, SHINY;

		public static final EnumSet<CraftingMaterialVariantTypes> CRAFTING_MATERIAL_VARIANT_TYPES = EnumSet.allOf(CraftingMaterialVariantTypes.class);

		public String getName()
		{
			return name().toLowerCase(Locale.ROOT);
		}
	}

	enum CraftingToolTypes
	{
		HAMMER;

		public static final EnumSet<CraftingToolTypes> CRAFTING_TOOL_TYPES = EnumSet.allOf(CraftingToolTypes.class);
	}

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
		makeCraftingMaterials(TIN, EnumSet.of(BLOCK), NORMAL);
		makeCraftingMaterials(BRONZE, EnumSet.of(BLOCK), NORMAL);
		makeCraftingMaterials(ANTANIUM, EnumSet.of(BLOCK), SHINY);
		makeCraftingMaterials(STEEL, EnumSet.of(BLOCK), NORMAL);
		makeCraftingMaterials(NICKEL, EnumSet.of(BLOCK), DULL);
		makeCraftingMaterials(INVAR, EnumSet.of(BLOCK), DULL);
		makeCraftingMaterials(CONSTANTAN, EnumSet.of(BLOCK), DULL);
		makeCraftingMaterials(CUPRONICKEL, EnumSet.of(BLOCK), DULL);
		makeCraftingMaterials(TITANIUM, EnumSet.of(BLOCK), NORMAL);
		makeCraftingMaterials(TITANIUM_GOLD, EnumSet.of(BLOCK), SHINY);
		makeCraftingMaterials(NITINOL, EnumSet.of(BLOCK), SHINY);

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
		makeCraftingTools(COPPER, EnumSet.of(HAMMER), 0);
		makeCraftingTools(BRONZE, EnumSet.of(HAMMER), 1);
		makeCraftingTools(IRON, EnumSet.of(HAMMER), 1);
		makeCraftingTools(GOLD, EnumSet.of(HAMMER), 0);
		makeCraftingTools(ANTANIUM, EnumSet.of(HAMMER), 1);
		makeCraftingTools(STEEL, EnumSet.of(HAMMER), 2);
		makeCraftingTools(NICKEL, EnumSet.of(HAMMER), 2);
		makeCraftingTools(INVAR, EnumSet.of(HAMMER), 3);
		makeCraftingTools(CONSTANTAN, EnumSet.of(HAMMER), 2);
		makeCraftingTools(CUPRONICKEL, EnumSet.of(HAMMER), 2);
		makeCraftingTools(TITANIUM, EnumSet.of(HAMMER), 3);
		makeCraftingTools(TITANIUM_GOLD, EnumSet.of(HAMMER), 3);
		makeCraftingTools(NITINOL, EnumSet.of(HAMMER), 4);
	}

	private static void makeCraftingMaterials(AlloygeryMaterial material, EnumSet<CraftingMaterialTypes> materialTypes, CraftingMaterialVariantTypes variant)
	{
		if(materialTypes.contains(CraftingMaterialTypes.RAW))
			registerGeneratedItem("raw_" + material.getMaterialName(), new CraftingItem(material), CraftingItemModelBuilder.createRawOreItemModelJson());

		if(materialTypes.contains(CraftingMaterialTypes.NUGGET))
			registerGeneratedItem(material.getMaterialName() + "_nugget", new CraftingItem(material), CraftingItemModelBuilder.createNuggetItemModelJson(variant.getName()));

		if(materialTypes.contains(CraftingMaterialTypes.INGOT))
			registerGeneratedItem(material.getMaterialName() + "_ingot", new CraftingItem(material), CraftingItemModelBuilder.createIngotItemModelJson(variant.getName()));

		if(materialTypes.contains(CraftingMaterialTypes.DOUBLE_INGOT))
			registerGeneratedItem(material.getMaterialName() + "_double_ingot", new CraftingItem(material), CraftingItemModelBuilder.createDoubleIngotItemModelJson(variant.getName()));

		if(materialTypes.contains(PLATE))
			registerGeneratedItem(material.getMaterialName() + "_plate", new CraftingItem(material), CraftingItemModelBuilder.createPlateItemModelJson(variant.getName()));

		if(materialTypes.contains(CraftingMaterialTypes.HEAVY_PLATE))
			registerGeneratedItem(material.getMaterialName() + "_heavy_plate", new CraftingItem(material), CraftingItemModelBuilder.createHeavyPlateItemModelJson(variant.getName()));

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
	}

	private static void makeCraftingTools(AlloygeryMaterial material, EnumSet<CraftingToolTypes> toolTypes, int tier)
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
