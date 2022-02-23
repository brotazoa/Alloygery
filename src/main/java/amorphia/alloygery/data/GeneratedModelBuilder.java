package amorphia.alloygery.data;

import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;

import java.util.Map;

public class GeneratedModelBuilder
{
	public static final Map<Identifier, String> MODEL_SUPPLIER_FOR_IDENTIFIER = Maps.newIdentityHashMap();

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

	public static String createAxeItemModelJson()
	{
		return "{\"parent\": \"alloygery:item/alloygery_axe\"}";
	}

	public static String createHoeItemModelJson()
	{
		return "{\"parent\": \"alloygery:item/alloygery_hoe\"}";
	}

	public static String createPickaxeItemModelJson()
	{
		return "{\"parent\": \"alloygery:item/alloygery_pickaxe\"}";
	}

	public static String createShovelItemModelJson()
	{
		return "{\"parent\": \"alloygery:item/alloygery_shovel\"}";
	}

	public static String createSwordItemModelJson()
	{
		return "{\"parent\": \"alloygery:item/alloygery_sword\"}";
	}

	public static String createArmorItemModelJson(String armorType)
	{
		return "{\"parent\": \"alloygery:item/alloygery_" + armorType +"\"}";
	}
}
