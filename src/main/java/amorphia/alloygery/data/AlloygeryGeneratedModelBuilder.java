package amorphia.alloygery.data;

import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class AlloygeryGeneratedModelBuilder
{
	private static final Map<Identifier, Supplier<String>> MODEL_SUPPLIER_FOR_IDENTIFIER = Maps.newIdentityHashMap();

	public static void register(Identifier identifier, Supplier<String> modelSupplier)
	{
		MODEL_SUPPLIER_FOR_IDENTIFIER.put(identifier, modelSupplier);
	}

	public static Optional<Map.Entry<Identifier, Supplier<String>>> getModelSupplierForIdentifier(Identifier identifier)
	{
		return MODEL_SUPPLIER_FOR_IDENTIFIER.entrySet().stream().filter(e -> e.getKey().equals(identifier)).findFirst();
	}

	public static String createPartItemModelJson(String partType)
	{
		return "{\"parent\": \"alloygery:item/part_" + partType + "_template\"}";
	}

	public static String createMaterialItemModelJson(String materialType)
	{
		return "{\"parent\": \"alloygery:item/material_" + materialType + "_template\"}";
	}

	public static String createBlockItemModelJson(String parentBlock)
	{
		return "{\"parent\": \"alloygery:block/" + parentBlock + "\"}";
	}

	public static String createOreBlockModelJson()
	{
		return "{\"parent\": \"alloygery:block/ore_block_template\"}";
	}

	public static String createDeepslateOreBlockModelJson()
	{
		return "{\"parent\": \"alloygery:block/deepslate_ore_block_template\"}";
	}

	public static String createMetalBlockModelJson()
	{
		return "{\"parent\": \"alloygery:block/metal_block_template\"}";
	}

	public static String createRawOreBlockModelJson()
	{
		return "{\"parent\": \"alloygery:block/raw_ore_block_template\"}";
	}

	public static String createToolItemModelJson(String parentModel)
	{
		return "{\"parent\": \"alloygery:item/" + parentModel + "\"}";
	}

	public static String createArmorItemModelJson(String armorType)
	{
		return "{\"parent\": \"alloygery:item/alloygery_" + armorType +"\"}";
	}
}
