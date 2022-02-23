package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.content.item.*;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.data.GeneratedModelBuilder;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.util.*;
import java.util.function.Supplier;

public class ModItems
{
	public static final List<AlloygeryMaterial> MAKE_CRAFTING_MATERIALS_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.TIN,
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON_OVERRIDE,
			AlloygeryMaterials.GOLD_OVERRIDE,
			AlloygeryMaterials.ANTANIUM,
			AlloygeryMaterials.STEEL,
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.INVAR,
			AlloygeryMaterials.CONSTANTAN,
			AlloygeryMaterials.CUPRONICKEL,
			AlloygeryMaterials.TITANIUM,
			AlloygeryMaterials.TITANIUM_GOLD,
			AlloygeryMaterials.NITINOL
	);

	public static final List<AlloygeryMaterial> MAKE_TOOL_PARTS_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON_OVERRIDE,
			AlloygeryMaterials.GOLD_OVERRIDE,
			AlloygeryMaterials.ANTANIUM,
			AlloygeryMaterials.STEEL,
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.INVAR,
			AlloygeryMaterials.CONSTANTAN,
			AlloygeryMaterials.CUPRONICKEL,
			AlloygeryMaterials.TITANIUM,
			AlloygeryMaterials.TITANIUM_GOLD,
			AlloygeryMaterials.NITINOL
	);

	public static final Set<AlloygeryMaterial> HAS_VANILLA_CRAFTING_MATERIALS = Set.of(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.IRON_OVERRIDE,
			AlloygeryMaterials.GOLD_OVERRIDE
	);

	public static final Set<AlloygeryMaterial> OVERRIDE_VANILLA_TOOLS = Set.of(
			AlloygeryMaterials.IRON_OVERRIDE,
			AlloygeryMaterials.GOLD_OVERRIDE
	);

	public static final List<AlloygeryMaterial> MAKE_ARMOR_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON_OVERRIDE,
			AlloygeryMaterials.GOLD_OVERRIDE,
			AlloygeryMaterials.ANTANIUM,
			AlloygeryMaterials.STEEL,
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.INVAR,
			AlloygeryMaterials.CONSTANTAN,
			AlloygeryMaterials.CUPRONICKEL,
			AlloygeryMaterials.TITANIUM,
			AlloygeryMaterials.TITANIUM_GOLD,
			AlloygeryMaterials.NITINOL
	);

	public static final Set<AlloygeryMaterial> TOUGH_ARMOR_VARIANT = Set.of(
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.INVAR,
			AlloygeryMaterials.CONSTANTAN,
			AlloygeryMaterials.CUPRONICKEL,
			AlloygeryMaterials.TITANIUM,
			AlloygeryMaterials.TITANIUM_GOLD,
			AlloygeryMaterials.NITINOL
	);

	public static Map<String, Item> ALLOYGERY_ITEMS = new LinkedHashMap<>();

	// @formatter:off
	//creative tab items
	public static final Item BLOCKS_TAB_ITEM = new Item(new Item.Settings());
	public static final Item MATERIALS_TAB_ITEM = new Item(new Item.Settings());
	public static final Item GEAR_TAB_ITEM = new Item(new Item.Settings());
	public static final Item PARTS_TAB_ITEM = new Item(new Item.Settings());

	//alloy kiln block items
	public static final Item ALLOY_KILN = new BlockItem(ModBlocks.ALLOY_KILN, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item BLAST_ALLOY_KILN = new BlockItem(ModBlocks.BLAST_ALLOY_KILN, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	//crafting tools
	public static final Item ANVIL_HAMMER = new BaseCraftingToolItem(new Item.Settings().maxDamage(AlloygeryConfig.ironGear.uses.getValue()).group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item SMITHING_HAMMER = new BaseCraftingToolItem(new Item.Settings().maxDamage(AlloygeryConfig.steelGear.uses.getValue()).group(Alloygery.ALLOYGERY_GROUP_PARTS));

	//smithing anvil
	public static final Item SMITHING_ANVIL = new BlockItem(ModBlocks.SMITHING_ANVIL, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	// @formatter:on

	static void makeOreBlockItem(AlloygeryMaterial material)
	{
		Block block = ModBlocks.ALLOYGERY_BLOCKS.get(material.name + "_ore");
		if (block != null)
		{
			putGeneratedItem(material.name + "_ore", new BlockItem(block, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS)),
					() -> GeneratedModelBuilder.createBlockItemModelJson(material.name + "_ore"));
		}

		if (ModBlocks.DEEPSLATE_ORE_VARIANTS.contains(material))
		{
			Block deepslateBlock = ModBlocks.ALLOYGERY_BLOCKS.get("deepslate_" + material.name + "_ore");
			if (deepslateBlock != null)
			{
				putGeneratedItem("deepslate_" + material.name + "_ore", new BlockItem(deepslateBlock, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS)),
						() -> GeneratedModelBuilder.createBlockItemModelJson("deepslate_" + material.name + "_ore"));
			}
		}
	}

	static void makeRawOreItem(AlloygeryMaterial material)
	{
		putGeneratedItem("raw_" + material.name, new AlloygeryCraftingItem(material), () -> GeneratedModelBuilder.createMaterialItemModelJson("raw_ore"));
	}

	static void makeRawOreBlockItem(AlloygeryMaterial material)
	{
		Block block = ModBlocks.ALLOYGERY_BLOCKS.get("raw_" + material.name + "_block");
		if (block != null)
		{
			putGeneratedItem("raw_" + material.name + "_block",
					new AlloygeryTintedBlockItem(material, block, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS)),
					() -> GeneratedModelBuilder.createBlockItemModelJson("raw_" + material.name + "_block"));
		}
	}

	static void makeMetalBlockItem(AlloygeryMaterial material)
	{
		Block block = ModBlocks.ALLOYGERY_BLOCKS.get(material.name + "_block");
		if (block != null)
		{
			putGeneratedItem(material.name + "_block", new AlloygeryTintedBlockItem(material, block, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS)),
					() -> GeneratedModelBuilder.createBlockItemModelJson(material.name + "_block"));
		}
	}

	static void makeCraftingMaterials(AlloygeryMaterial material)
	{
		if(!HAS_VANILLA_CRAFTING_MATERIALS.contains(material))
		{
			Item ingot = putGeneratedItem(material.name + "_ingot", new AlloygeryCraftingItem(material), () -> GeneratedModelBuilder.createMaterialItemModelJson("ingot"));
			AlloygeryMaterialHelper.REPAIR_INGREDIENT_MAP.put(material, Ingredient.ofItems(ingot));
		}

		putGeneratedItem(material.name + "_double_ingot", new AlloygeryCraftingItem(material), () -> GeneratedModelBuilder.createMaterialItemModelJson("double_ingot"));
		putGeneratedItem(material.name + "_plate", new AlloygeryCraftingItem(material), () -> GeneratedModelBuilder.createMaterialItemModelJson("plate"));
		putGeneratedItem(material.name + "_heavy_plate", new AlloygeryCraftingItem(material), () -> GeneratedModelBuilder.createMaterialItemModelJson("heavy_plate"));
	}

	static void makeToolParts(AlloygeryMaterial material)
	{
		putGeneratedItem(material.name + "_axe_head", new AlloygeryPartItem(material), () -> GeneratedModelBuilder.createPartItemModelJson("axe_head"));
		putGeneratedItem(material.name + "_hoe_head", new AlloygeryPartItem(material), () -> GeneratedModelBuilder.createPartItemModelJson("hoe_head"));
		putGeneratedItem(material.name + "_pickaxe_head", new AlloygeryPartItem(material), () -> GeneratedModelBuilder.createPartItemModelJson("pickaxe_head"));
		putGeneratedItem(material.name + "_shovel_head", new AlloygeryPartItem(material), () -> GeneratedModelBuilder.createPartItemModelJson("shovel_head"));
		putGeneratedItem(material.name + "_sword_blade", new AlloygeryPartItem(material), () -> GeneratedModelBuilder.createPartItemModelJson("sword_blade"));
	}

	static void makeArmor(AlloygeryMaterial material)
	{
		if (TOUGH_ARMOR_VARIANT.contains(material))
		{
			putGeneratedItem(material.name + "_helmet", new AlloygeryArmorItem(material, EquipmentSlot.HEAD), () -> GeneratedModelBuilder.createArmorItemModelJson("tough_helmet"));
			putGeneratedItem(material.name + "_chestplate", new AlloygeryArmorItem(material, EquipmentSlot.CHEST), () -> GeneratedModelBuilder.createArmorItemModelJson("tough_chestplate"));
			putGeneratedItem(material.name + "_leggings", new AlloygeryArmorItem(material, EquipmentSlot.LEGS), () -> GeneratedModelBuilder.createArmorItemModelJson("tough_leggings"));
			putGeneratedItem(material.name + "_boots", new AlloygeryArmorItem(material, EquipmentSlot.FEET), () -> GeneratedModelBuilder.createArmorItemModelJson("tough_boots"));
		}
		else
		{
			putGeneratedItem(material.name + "_helmet", new AlloygeryArmorItem(material, EquipmentSlot.HEAD), () -> GeneratedModelBuilder.createArmorItemModelJson("helmet"));
			putGeneratedItem(material.name + "_chestplate", new AlloygeryArmorItem(material, EquipmentSlot.CHEST), () -> GeneratedModelBuilder.createArmorItemModelJson("chestplate"));
			putGeneratedItem(material.name + "_leggings", new AlloygeryArmorItem(material, EquipmentSlot.LEGS), () -> GeneratedModelBuilder.createArmorItemModelJson("leggings"));
			putGeneratedItem(material.name + "_boots", new AlloygeryArmorItem(material, EquipmentSlot.FEET), () -> GeneratedModelBuilder.createArmorItemModelJson("boots"));
		}
	}

	static void makeTools(AlloygeryMaterial material)
	{
		putGeneratedItem(material.name + "_axe", new AlloygeryAxeItem(material), GeneratedModelBuilder::createAxeItemModelJson);
		putGeneratedItem(material.name + "_hoe", new AlloygeryHoeItem(material), GeneratedModelBuilder::createHoeItemModelJson);
		putGeneratedItem(material.name + "_pickaxe", new AlloygeryPickaxeItem(material), GeneratedModelBuilder::createPickaxeItemModelJson);
		putGeneratedItem(material.name + "_shovel", new AlloygeryShovelItem(material), GeneratedModelBuilder::createShovelItemModelJson);
		putGeneratedItem(material.name + "_sword", new AlloygerySwordItem(material), GeneratedModelBuilder::createSwordItemModelJson);
	}

	static Item putGeneratedItem(String path, Item item, Supplier<String> generatedModelJsonSupplier)
	{
		Identifier identifier = Alloygery.identifier("item/" + path);
		GeneratedModelBuilder.MODEL_SUPPLIER_FOR_IDENTIFIER.put(identifier, generatedModelJsonSupplier.get());
		ALLOYGERY_ITEMS.put(path, item);
		return item;
	}

	public static void register()
	{
		ModBlocks.ORE_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeOreBlockItem);
		ModBlocks.RAW_ORE_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeRawOreBlockItem);
		ModBlocks.RAW_ORE_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeRawOreItem);
		ModBlocks.METAL_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeMetalBlockItem);
		MAKE_CRAFTING_MATERIALS_FOR_MATERIAL.forEach(ModItems::makeCraftingMaterials);
		MAKE_TOOL_PARTS_FOR_MATERIAL.forEach(ModItems::makeToolParts);
		MAKE_TOOL_PARTS_FOR_MATERIAL.forEach(ModItems::makeTools);
		MAKE_ARMOR_FOR_MATERIAL.forEach(ModItems::makeArmor);

		//kilns
		register("alloy_kiln", ALLOY_KILN);
		register("blast_alloy_kiln", BLAST_ALLOY_KILN);

		//smithing anvil
		register("smithing_anvil", SMITHING_ANVIL);

		//crafting tools
		register("anvil_hammer", ANVIL_HAMMER);
		register("smithing_hammer", SMITHING_HAMMER);

		ALLOYGERY_ITEMS.forEach(ModItems::register);

		register("blocks_tab_item", BLOCKS_TAB_ITEM);
		register("materials_tab_item", MATERIALS_TAB_ITEM);
		register("gear_tab_item", GEAR_TAB_ITEM);
		register("parts_tab_item", PARTS_TAB_ITEM);

		AlloygeryMaterialHelper.REPAIR_INGREDIENT_MAP.put(AlloygeryMaterials.IRON_OVERRIDE, Ingredient.ofItems(Items.IRON_INGOT));
		AlloygeryMaterialHelper.REPAIR_INGREDIENT_MAP.put(AlloygeryMaterials.GOLD_OVERRIDE, Ingredient.ofItems(Items.GOLD_INGOT));
	}

	private static void register(String path, Item item)
	{
		Registry.register(Registry.ITEM, Alloygery.identifier(path), item);
	}

	private static void override(Item override, Item item)
	{
		Identifier mcIdent = Registry.ITEM.getId(override);
		Item mcItem = Registry.ITEM.get(mcIdent);
		int id = Registry.ITEM.getRawId(mcItem);

		Registry.ITEM.set(id, RegistryKey.of(Registry.ITEM.getKey(), mcIdent), item, Registry.ITEM.getLifecycle());
	}
}
