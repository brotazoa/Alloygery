package amorphia.alloygery.content.metals.client;

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

	public static Supplier<String> createIngotItemModelJson(String variantString)
	{
		return () -> createCraftingItemModelJson("ingot_" + variantString);
	}

	public static Supplier<String> createDoubleIngotItemModelJson(String variantString)
	{
		return () -> createCraftingItemModelJson("double_ingot_" + variantString);
	}

	public static Supplier<String> createNuggetItemModelJson(String variantString)
	{
		return () -> createCraftingItemModelJson("nugget_" + variantString);
	}

	public static Supplier<String> createPlateItemModelJson(String variantString)
	{
		return () -> createCraftingItemModelJson("plate_" + variantString);
	}

	public static Supplier<String> createHeavyPlateItemModelJson(String variantString)
	{
		return () -> createCraftingItemModelJson("heavy_plate_" + variantString);
	}

	public static Supplier<String> createBlockItemModelJson(String parentBlockName)
	{
		return () -> "{\"parent\": \"alloygery:block/" + parentBlockName + "\"}";
	}

	public static Supplier<String> createMetalBlockModelJson()
	{
		return createMetalBlockModelJson("normal");
	}

	public static Supplier<String> createMetalBlockModelJson(String variantString)
	{
		return () -> "{\"parent\": \"alloygery:block/metal_block_" + variantString + "_template\"}";
	}

	public static String createRawOreBlockModelJson()
	{
		return "{\"parent\": \"alloygery:block/raw_ore_block_template\"}";
	}
}
