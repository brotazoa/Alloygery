package amorphia.alloygery.compat.create;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.metals.client.CraftingItemModelBuilder;
import amorphia.alloygery.content.metals.item.CraftingItem;
import amorphia.alloygery.content.metals.item.CraftingMaterialItemTypes;
import amorphia.alloygery.content.metals.item.CraftingMaterialVariantTypes;
import amorphia.alloygery.content.metals.item.TintedBlockItem;
import amorphia.alloygery.content.metals.registry.MetalBlockRegistry;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.EnumSet;
import java.util.Map;
import java.util.function.Supplier;

import static amorphia.alloygery.content.metals.item.CraftingMaterialItemTypes.*;
import static amorphia.alloygery.content.metals.item.CraftingMaterialVariantTypes.*;

public class CreateMetalItemRegistry
{
	public static final Map<String, Item> ITEMS = Maps.newLinkedHashMap();

	public static void init()
	{
		makeCraftingMaterials(CreateToolMaterials.ZINC, EnumSet.of(DOUBLE_INGOT, PLATE, HEAVY_PLATE), DULL);
		makeCraftingMaterials(CreateToolMaterials.ANDESITE_ALLOY, EnumSet.of(DOUBLE_INGOT, PLATE, HEAVY_PLATE), DULL);
		makeCraftingMaterials(CreateToolMaterials.BRASS, EnumSet.of(DOUBLE_INGOT, PLATE, HEAVY_PLATE), SHINY);
	}

	private static void makeCraftingMaterials(AlloygeryToolMaterial material, EnumSet<CraftingMaterialItemTypes> materialTypes, CraftingMaterialVariantTypes variant)
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
				registerGeneratedItem(material.getMaterialName() + "_block", new TintedBlockItem(material, parent, new Item.Settings().group(
						Alloygery.ALLOYGERY_TAB_GROUP)), CraftingItemModelBuilder.createBlockItemModelJson(material.getMaterialName() + "_block"));
		}

		if(materialTypes.contains(RAW_BLOCK))
		{
			Block parent = MetalBlockRegistry.BLOCKS.get("raw_" + material.getMaterialName() + "_block");
			if(parent != null)
				registerGeneratedItem("raw_" + material.getMaterialName() + "_block", new TintedBlockItem(material, parent, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)), CraftingItemModelBuilder.createBlockItemModelJson("raw_" + material.getMaterialName() + "_block"));
		}
	}

	private static Item registerGeneratedItem(String path, Item item, Supplier<String> jsonModelSupplier)
	{
		Identifier identifier = CreateModule.identify("item/" + path);
		CraftingItemModelBuilder.register(identifier, jsonModelSupplier);
		return register(path, item);
	}

	private static Item register(String path, Item item)
	{
		ITEMS.put(path, item);
		return Registry.register(Registry.ITEM, CreateModule.identify(path), item);
	}
}
