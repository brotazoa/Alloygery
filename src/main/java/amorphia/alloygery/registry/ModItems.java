package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.item.*;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.data.GeneratedModelBuilder;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.*;
import java.util.function.Supplier;

public class ModItems
{
	public static final List<AlloygeryMaterial> MAKE_CRAFTING_MATERIALS_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.TIN,
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD,
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

	public static final List<AlloygeryMaterial> MAKE_TOOL_HEADS_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD,
			AlloygeryMaterials.ANTANIUM,
			AlloygeryMaterials.DIAMOND,
			AlloygeryMaterials.STEEL,
			AlloygeryMaterials.NETHERITE,
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.INVAR,
			AlloygeryMaterials.CONSTANTAN,
			AlloygeryMaterials.CUPRONICKEL,
			AlloygeryMaterials.TITANIUM,
			AlloygeryMaterials.TITANIUM_GOLD,
			AlloygeryMaterials.NITINOL
	);

	public static final Set<AlloygeryMaterial> MAKE_BINDINGS_FOR_MATERIAL = Set.of(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD,
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

	public static final Set<AlloygeryMaterial> MAKE_HANDLES_FOR_MATERIAL = Set.of(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD,
			AlloygeryMaterials.ANTANIUM,
			AlloygeryMaterials.STEEL,
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.INVAR,
			AlloygeryMaterials.CONSTANTAN,
			AlloygeryMaterials.CUPRONICKEL,
			AlloygeryMaterials.TITANIUM,
			AlloygeryMaterials.TITANIUM_GOLD,
			AlloygeryMaterials.NITINOL,
			AlloygeryMaterials.VANILLA_STICK
	);

	public static final List<AlloygeryMaterial> MAKE_TOOL_PARTS_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD,
			AlloygeryMaterials.ANTANIUM,
			AlloygeryMaterials.DIAMOND,
			AlloygeryMaterials.STEEL,
			AlloygeryMaterials.NETHERITE,
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.INVAR,
			AlloygeryMaterials.CONSTANTAN,
			AlloygeryMaterials.CUPRONICKEL,
			AlloygeryMaterials.TITANIUM,
			AlloygeryMaterials.TITANIUM_GOLD,
			AlloygeryMaterials.NITINOL,
			AlloygeryMaterials.VANILLA_STICK
	);

	public static final Set<AlloygeryMaterial> HAS_VANILLA_CRAFTING_MATERIALS = Set.of(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD
	);

	public static final Set<AlloygeryMaterial> HAS_VANILLA_TOOLS = Set.of(
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD
	);

	public static final Set<AlloygeryMaterial> HAS_VANILLA_ARMORS = Set.of(
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD
	);

	public static final List<AlloygeryMaterial> MAKE_ARMOR_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.COPPER,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.IRON,
			AlloygeryMaterials.GOLD,
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

	//ore block items
	public static final Item TIN_ORE = new BlockItem(ModBlocks.TIN_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item DEEPSLATE_TIN_ORE = new BlockItem(ModBlocks.DEEPSLATE_TIN_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item NICKEL_ORE = new BlockItem(ModBlocks.NICKEL_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item TITANIUM_ORE = new BlockItem(ModBlocks.TITANIUM_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	//alloy kiln block items
	public static final Item ALLOY_KILN = new BlockItem(ModBlocks.ALLOY_KILN, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item BLAST_ALLOY_KILN = new BlockItem(ModBlocks.BLAST_ALLOY_KILN, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	//crafting tools
	public static final Item ANVIL_HAMMER = new BaseCraftingToolItem(new Item.Settings().maxDamage(250).group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item SMITHING_HAMMER = new BaseCraftingToolItem(new Item.Settings().maxDamage(1600).group(Alloygery.ALLOYGERY_GROUP_PARTS));

	//smithing anvil
	public static final Item SMITHING_ANVIL = new BlockItem(ModBlocks.SMITHING_ANVIL, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	// @formatter:on

	static void makeRawOreItem(AlloygeryMaterial material)
	{
		putGeneratedItem("raw_" + material.name, new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> GeneratedModelBuilder.createMaterialItemModelJson("raw_ore"));
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
			Item ingot = putGeneratedItem(material.name + "_ingot", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> GeneratedModelBuilder.createMaterialItemModelJson("ingot"));
			AlloygeryToolMaterialHelper.REPAIR_INGREDIENT_MAP.put(material, Ingredient.ofItems(ingot));

			putGeneratedItem(material.name + "_nugget", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> GeneratedModelBuilder.createMaterialItemModelJson("nugget"));
		}

		if(material.name.equals("copper")) //vanilla parity is vanilla parity
			putGeneratedItem(material.name + "_nugget", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> GeneratedModelBuilder.createMaterialItemModelJson("nugget"));

		putGeneratedItem(material.name + "_double_ingot", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> GeneratedModelBuilder.createMaterialItemModelJson("double_ingot"));
		putGeneratedItem(material.name + "_plate", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> GeneratedModelBuilder.createMaterialItemModelJson("plate"));
		putGeneratedItem(material.name + "_heavy_plate", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> GeneratedModelBuilder.createMaterialItemModelJson("heavy_plate"));
	}

	static void makeToolParts(AlloygeryMaterial material)
	{
		if(MAKE_TOOL_HEADS_FOR_MATERIAL.contains(material))
		{
			putGeneratedItem(material.name + "_axe_head", new AlloygeryPartItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
					() -> GeneratedModelBuilder.createPartItemModelJson("axe_head"));
			putGeneratedItem(material.name + "_hoe_head", new AlloygeryPartItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
					() -> GeneratedModelBuilder.createPartItemModelJson("hoe_head"));
			putGeneratedItem(material.name + "_pickaxe_head", new AlloygeryPartItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
					() -> GeneratedModelBuilder.createPartItemModelJson("pickaxe_head"));
			putGeneratedItem(material.name + "_shovel_head", new AlloygeryPartItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
					() -> GeneratedModelBuilder.createPartItemModelJson("shovel_head"));
			putGeneratedItem(material.name + "_sword_blade", new AlloygeryPartItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
					() -> GeneratedModelBuilder.createPartItemModelJson("sword_blade"));
		}

		if (MAKE_HANDLES_FOR_MATERIAL.contains(material))
		{
			putGeneratedItem(material.name + "_handle", new AlloygeryPartItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
					() -> GeneratedModelBuilder.createPartItemModelJson("handle"));
		}

		if(MAKE_BINDINGS_FOR_MATERIAL.contains(material))
		{
			putGeneratedItem(material.name + "_binding", new AlloygeryPartItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
					() -> GeneratedModelBuilder.createPartItemModelJson("binding"));
			putGeneratedItem(material.name + "_sword_guard", new AlloygeryPartItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
					() -> GeneratedModelBuilder.createPartItemModelJson("sword_guard"));
		}
	}

	static void makeArmor(AlloygeryMaterial material)
	{
//		//don't make armor items for vanilla
//		if(HAS_VANILLA_ARMORS.contains(material))
//			return;

		if (TOUGH_ARMOR_VARIANT.contains(material))
		{
			putGeneratedItem(material.name + "_helmet", new AlloygeryArmorItem(material, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createArmorItemModelJson("tough_helmet"));
			putGeneratedItem(material.name + "_chestplate", new AlloygeryArmorItem(material, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createArmorItemModelJson("tough_chestplate"));
			putGeneratedItem(material.name + "_leggings", new AlloygeryArmorItem(material, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createArmorItemModelJson("tough_leggings"));
			putGeneratedItem(material.name + "_boots", new AlloygeryArmorItem(material, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createArmorItemModelJson("tough_boots"));
		}
		else
		{
			putGeneratedItem(material.name + "_helmet", new AlloygeryArmorItem(material, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createArmorItemModelJson("helmet"));
			putGeneratedItem(material.name + "_chestplate", new AlloygeryArmorItem(material, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createArmorItemModelJson("chestplate"));
			putGeneratedItem(material.name + "_leggings", new AlloygeryArmorItem(material, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createArmorItemModelJson("leggings"));
			putGeneratedItem(material.name + "_boots", new AlloygeryArmorItem(material, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createArmorItemModelJson("boots"));
		}
	}

	static void makeTools(AlloygeryMaterial material)
	{
		//don't make tool items for vanilla
		if(HAS_VANILLA_TOOLS.contains(material))
			return;

		putGeneratedItem(material.name + "_axe", new AlloygeryAxeItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> GeneratedModelBuilder.createToolItemModelJson("alloygery_axe"));
		putGeneratedItem(material.name + "_hoe", new AlloygeryHoeItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), GeneratedModelBuilder::createHoeItemModelJson);
		putGeneratedItem(material.name + "_pickaxe", new AlloygeryPickaxeItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), GeneratedModelBuilder::createPickaxeItemModelJson);
		putGeneratedItem(material.name + "_shovel", new AlloygeryShovelItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), GeneratedModelBuilder::createShovelItemModelJson);
		putGeneratedItem(material.name + "_sword", new AlloygerySwordItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), GeneratedModelBuilder::createSwordItemModelJson);
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
		ModBlocks.RAW_ORE_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeRawOreBlockItem);
		ModBlocks.RAW_ORE_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeRawOreItem);
		ModBlocks.METAL_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeMetalBlockItem);
		MAKE_CRAFTING_MATERIALS_FOR_MATERIAL.forEach(ModItems::makeCraftingMaterials);
		MAKE_TOOL_PARTS_FOR_MATERIAL.forEach(ModItems::makeToolParts);

		ALLOYGERY_ITEMS.put("alloygery_axe", new AlloygeryAxeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("diamond_upgraded_alloygery_axe", new AlloygeryAxeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("emerald_upgraded_alloygery_axe", new AlloygeryAxeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("netherite_upgraded_alloygery_axe", new AlloygeryAxeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));

		ALLOYGERY_ITEMS.put("alloygery_hoe", new AlloygeryHoeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("diamond_upgraded_alloygery_hoe", new AlloygeryHoeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("emerald_upgraded_alloygery_hoe", new AlloygeryHoeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("netherite_upgraded_alloygery_hoe", new AlloygeryHoeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));

		ALLOYGERY_ITEMS.put("alloygery_pickaxe", new AlloygeryPickaxeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("diamond_upgraded_alloygery_pickaxe", new AlloygeryPickaxeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("emerald_upgraded_alloygery_pickaxe", new AlloygeryPickaxeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("netherite_upgraded_alloygery_pickaxe", new AlloygeryPickaxeItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));

		ALLOYGERY_ITEMS.put("alloygery_shovel", new AlloygeryShovelItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("diamond_upgraded_alloygery_shovel", new AlloygeryShovelItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("emerald_upgraded_alloygery_shovel", new AlloygeryShovelItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("netherite_upgraded_alloygery_shovel", new AlloygeryShovelItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));

		ALLOYGERY_ITEMS.put("alloygery_sword", new AlloygerySwordItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("diamond_upgraded_alloygery_sword", new AlloygerySwordItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("emerald_upgraded_alloygery_sword", new AlloygerySwordItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("netherite_upgraded_alloygery_sword", new AlloygerySwordItem(AlloygeryMaterials.UNKNOWN, new Item.Settings().maxCount(1).maxDamage(1)));

		//MAKE_TOOL_PARTS_FOR_MATERIAL.forEach(ModItems::makeTools);
		MAKE_ARMOR_FOR_MATERIAL.forEach(ModItems::makeArmor);

		//kilns
		register("alloy_kiln", ALLOY_KILN);
		register("blast_alloy_kiln", BLAST_ALLOY_KILN);

		//smithing anvil
		register("smithing_anvil", SMITHING_ANVIL);

		//ore blocks
		register("tin_ore", TIN_ORE);
		register("deepslate_tin_ore", DEEPSLATE_TIN_ORE);
		register("nickel_ore", NICKEL_ORE);
		register("titanium_ore", TITANIUM_ORE);

		//crafting tools
		register("anvil_hammer", ANVIL_HAMMER);
		register("smithing_hammer", SMITHING_HAMMER);

		ALLOYGERY_ITEMS.forEach(ModItems::register);

		register("blocks_tab_item", BLOCKS_TAB_ITEM);
		register("materials_tab_item", MATERIALS_TAB_ITEM);
		register("gear_tab_item", GEAR_TAB_ITEM);
		register("parts_tab_item", PARTS_TAB_ITEM);

		AlloygeryToolMaterialHelper.REPAIR_INGREDIENT_MAP.put(AlloygeryMaterials.IRON, Ingredient.ofItems(Items.IRON_INGOT));
		AlloygeryToolMaterialHelper.REPAIR_INGREDIENT_MAP.put(AlloygeryMaterials.GOLD, Ingredient.ofItems(Items.GOLD_INGOT));
	}

	private static void register(String path, Item item)
	{
		Registry.register(Registry.ITEM, Alloygery.identifier(path), item);
	}
}
