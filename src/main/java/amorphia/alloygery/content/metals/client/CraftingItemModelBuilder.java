package amorphia.alloygery.content.metals.client;

import amorphia.alloygery.content.metals.item.CraftingMaterialVariantTypes;
import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class CraftingItemModelBuilder
{
	private static final Map<Identifier, Supplier<String>> MODEL_SUPPLIER_FOR_IDENDIFIER = Maps.newIdentityHashMap();

	public static void register(Identifier identifier, Supplier<String> modelSupplier)
	{
		MODEL_SUPPLIER_FOR_IDENDIFIER.put(identifier, modelSupplier);
	}

	public static Optional<Map.Entry<Identifier, Supplier<String>>> getModelSupplierForIdentifier(Identifier identifier)
	{
		return MODEL_SUPPLIER_FOR_IDENDIFIER.entrySet().stream().filter(entry -> entry.getKey().equals(identifier)).findFirst();
	}

	public static String createCraftingItemModelJson(String craftingItemTypeString)
	{
		return "{\"parent\": \"alloygery:item/material_" + craftingItemTypeString + "_template\"}";
	}

	public static Supplier<String> createCraftingHammerItemModelJson()
	{
		return () -> "{\"parent\": \"alloygery:item/crafting_hammer_template\"}";
	}

	public static Supplier<String> createRawOreItemModelJson()
	{
		return () -> createCraftingItemModelJson("raw_ore");
	}

	public static Supplier<String> createIngotItemModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> createCraftingItemModelJson("ingot_" + variantType.getName());
	}

	public static Supplier<String> createDoubleIngotItemModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> createCraftingItemModelJson("double_ingot_" + variantType.getName());
	}

	public static Supplier<String> createNuggetItemModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> createCraftingItemModelJson("nugget_" + variantType.getName());
	}

	public static Supplier<String> createPlateItemModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> createCraftingItemModelJson("plate_" + variantType.getName());
	}

	public static Supplier<String> createHeavyPlateItemModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> createCraftingItemModelJson("heavy_plate_" + variantType.getName());
	}

	public static Supplier<String> createBlockItemModelJson(String parentBlockName)
	{
		return () -> "{\"parent\": \"alloygery:block/" + parentBlockName + "\"}";
	}

	public static Supplier<String> createMetalBlockModelJson()
	{
		return createMetalBlockModelJson(CraftingMaterialVariantTypes.NORMAL);
	}

	public static Supplier<String> createMetalBlockModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> "{\"parent\": \"alloygery:block/metal_block_" + variantType.getName() + "_template\"}";
	}

	public static Supplier<String> createMetalStairsBlockModelJson()
	{
		return createMetalStairsBlockModelJson(CraftingMaterialVariantTypes.NORMAL);
	}

	public static Supplier<String> createMetalStairsBlockModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> "{\"parent\": \"alloygery:block/metal_" + variantType.getName() + "_stairs\"}";
	}

	public static Supplier<String> createMetalSlopeBlockModelJson()
	{
		return createMetalSlopeBlockModelJson(CraftingMaterialVariantTypes.NORMAL);
	}

	public static Supplier<String> createMetalSlopeBlockModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> "{\"parent\": \"alloygery:block/metal_" + variantType.getName() + "_slope\"}";
	}

	public static Supplier<String> createMetalSlabBlockModelJson()
	{
		return createMetalSlabBlockModelJson(CraftingMaterialVariantTypes.NORMAL);
	}

	public static Supplier<String> createMetalSlabBlockModelJson(CraftingMaterialVariantTypes variantType)
	{
		return () -> "{\"parent\": \"alloygery:block/metal_" + variantType.getName() + "_slab\"}";
	}

	public static Supplier<String> createRawOreBlockModelJson()
	{
		return () -> "{\"parent\": \"alloygery:block/raw_ore_block_template\"}";
	}
}
