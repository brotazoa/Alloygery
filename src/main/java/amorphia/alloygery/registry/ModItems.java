package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.item.*;
import amorphia.alloygery.content.item.part.AlloygeryToolBinding;
import amorphia.alloygery.content.item.part.AlloygeryToolHandle;
import amorphia.alloygery.content.item.part.AlloygeryToolHead;
import amorphia.alloygery.content.item.tool.*;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.data.AlloygeryGeneratedModelBuilder;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
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

	public static final Set<AlloygeryMaterial> HAS_VANILLA_CRAFTING_MATERIALS = Set.of(
			AlloygeryMaterials.COPPER,
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

	static void makeRawOreItem(AlloygeryMaterial material)
	{
		putGeneratedItem("raw_" + material.name, new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> AlloygeryGeneratedModelBuilder.createMaterialItemModelJson("raw_ore"));
	}

	static void makeRawOreBlockItem(AlloygeryMaterial material)
	{
		Block block = ModBlocks.ALLOYGERY_BLOCKS.get("raw_" + material.name + "_block");
		if (block != null)
		{
			putGeneratedItem("raw_" + material.name + "_block",
					new AlloygeryTintedBlockItem(material, block, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS)),
					() -> AlloygeryGeneratedModelBuilder.createBlockItemModelJson("raw_" + material.name + "_block"));
		}
	}

	static void makeMetalBlockItem(AlloygeryMaterial material)
	{
		Block block = ModBlocks.ALLOYGERY_BLOCKS.get(material.name + "_block");
		if (block != null)
		{
			putGeneratedItem(material.name + "_block", new AlloygeryTintedBlockItem(material, block, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS)),
					() -> AlloygeryGeneratedModelBuilder.createBlockItemModelJson(material.name + "_block"));
		}
	}

	static void makeCraftingMaterials(AlloygeryMaterial material)
	{
		if(!HAS_VANILLA_CRAFTING_MATERIALS.contains(material))
		{
			putGeneratedItem(material.name + "_ingot", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> AlloygeryGeneratedModelBuilder.createMaterialItemModelJson("ingot"));
			putGeneratedItem(material.name + "_nugget", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> AlloygeryGeneratedModelBuilder.createMaterialItemModelJson("nugget"));
		}

		if(material.name.equals("copper")) //vanilla parity is vanilla parity
			putGeneratedItem(material.name + "_nugget", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> AlloygeryGeneratedModelBuilder.createMaterialItemModelJson("nugget"));

		putGeneratedItem(material.name + "_double_ingot", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> AlloygeryGeneratedModelBuilder.createMaterialItemModelJson("double_ingot"));
		putGeneratedItem(material.name + "_plate", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> AlloygeryGeneratedModelBuilder.createMaterialItemModelJson("plate"));
		putGeneratedItem(material.name + "_heavy_plate", new AlloygeryCraftingItem(material, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS)), () -> AlloygeryGeneratedModelBuilder.createMaterialItemModelJson("heavy_plate"));
	}

	static void makeToolParts()
	{
		putGeneratedItem("alloygery_axe_head", new AlloygeryToolHead("axe_head", new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
				() -> AlloygeryGeneratedModelBuilder.createPartItemModelJson("axe_head"));

		putGeneratedItem("alloygery_hoe_head", new AlloygeryToolHead("hoe_head", new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
				() -> AlloygeryGeneratedModelBuilder.createPartItemModelJson("hoe_head"));

		putGeneratedItem("alloygery_pickaxe_head", new AlloygeryToolHead("pickaxe_head", new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
			() -> AlloygeryGeneratedModelBuilder.createPartItemModelJson("pickaxe_head"));

		putGeneratedItem("alloygery_shovel_head", new AlloygeryToolHead("shovel_head", new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
				() -> AlloygeryGeneratedModelBuilder.createPartItemModelJson("shovel_head"));

		putGeneratedItem("alloygery_sword_blade", new AlloygeryToolHead("sword_blade", new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
				() -> AlloygeryGeneratedModelBuilder.createPartItemModelJson("sword_blade"));

		putGeneratedItem("alloygery_binding", new AlloygeryToolBinding(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
				() -> AlloygeryGeneratedModelBuilder.createPartItemModelJson("binding"));

		putGeneratedItem("alloygery_sword_guard", new AlloygeryToolBinding("sword_guard", new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
				() -> AlloygeryGeneratedModelBuilder.createPartItemModelJson("sword_guard"));

		putGeneratedItem("alloygery_handle", new AlloygeryToolHandle(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS)),
				() -> AlloygeryGeneratedModelBuilder.createPartItemModelJson("handle"));
	}

	static void makeTools()
	{
		//formatter:off

		ALLOYGERY_ITEMS.put("alloygery_axe", new AlloygeryAxeItem(AlloygeryAxeItem.ATTACK_DAMAGE, AlloygeryAxeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("alloygery_hoe", new AlloygeryHoeItem(AlloygeryHoeItem.ATTACK_DAMAGE, AlloygeryHoeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("alloygery_pickaxe", new AlloygeryPickaxeItem(AlloygeryPickaxeItem.ATTACK_DAMAGE, AlloygeryPickaxeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("alloygery_shovel", new AlloygeryShovelItem(AlloygeryShovelItem.ATTACK_DAMAGE, AlloygeryShovelItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));
		ALLOYGERY_ITEMS.put("alloygery_sword", new AlloygerySwordItem(AlloygerySwordItem.ATTACK_DAMAGE, AlloygerySwordItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_GROUP_GEAR)));

		ALLOYGERY_ITEMS.put("tipped_alloygery_axe", new AlloygeryAxeItem(AlloygeryAxeItem.ATTACK_DAMAGE, AlloygeryAxeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("tipped_alloygery_hoe", new AlloygeryHoeItem(AlloygeryHoeItem.ATTACK_DAMAGE, AlloygeryHoeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("tipped_alloygery_pickaxe", new AlloygeryPickaxeItem(AlloygeryPickaxeItem.ATTACK_DAMAGE, AlloygeryPickaxeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("tipped_alloygery_shovel", new AlloygeryShovelItem(AlloygeryShovelItem.ATTACK_DAMAGE, AlloygeryShovelItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("tipped_alloygery_sword", new AlloygerySwordItem(AlloygerySwordItem.ATTACK_DAMAGE, AlloygerySwordItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));

		ALLOYGERY_ITEMS.put("embossed_alloygery_axe", new AlloygeryAxeItem(AlloygeryAxeItem.ATTACK_DAMAGE, AlloygeryAxeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("embossed_alloygery_hoe", new AlloygeryHoeItem(AlloygeryHoeItem.ATTACK_DAMAGE, AlloygeryHoeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("embossed_alloygery_pickaxe", new AlloygeryPickaxeItem(AlloygeryPickaxeItem.ATTACK_DAMAGE, AlloygeryPickaxeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("embossed_alloygery_shovel", new AlloygeryShovelItem(AlloygeryShovelItem.ATTACK_DAMAGE, AlloygeryShovelItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("embossed_alloygery_sword", new AlloygerySwordItem(AlloygerySwordItem.ATTACK_DAMAGE, AlloygerySwordItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));

		ALLOYGERY_ITEMS.put("plated_alloygery_axe", new AlloygeryAxeItem(AlloygeryAxeItem.ATTACK_DAMAGE, AlloygeryAxeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("plated_alloygery_hoe", new AlloygeryHoeItem(AlloygeryHoeItem.ATTACK_DAMAGE, AlloygeryHoeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("plated_alloygery_pickaxe", new AlloygeryPickaxeItem(AlloygeryPickaxeItem.ATTACK_DAMAGE, AlloygeryPickaxeItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("plated_alloygery_shovel", new AlloygeryShovelItem(AlloygeryShovelItem.ATTACK_DAMAGE, AlloygeryShovelItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));
		ALLOYGERY_ITEMS.put("plated_alloygery_sword", new AlloygerySwordItem(AlloygerySwordItem.ATTACK_DAMAGE, AlloygerySwordItem.ATTACK_SPEED, new Item.Settings().maxCount(1).maxDamage(1)));

		//formatter:on
	}

	static void makeArmor(AlloygeryMaterial material)
	{
		if (TOUGH_ARMOR_VARIANT.contains(material))
		{
			putGeneratedItem(material.name + "_helmet", new AlloygeryArmorItem(material, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> AlloygeryGeneratedModelBuilder.createArmorItemModelJson("tough_helmet"));
			putGeneratedItem(material.name + "_chestplate", new AlloygeryArmorItem(material, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> AlloygeryGeneratedModelBuilder.createArmorItemModelJson("tough_chestplate"));
			putGeneratedItem(material.name + "_leggings", new AlloygeryArmorItem(material, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> AlloygeryGeneratedModelBuilder.createArmorItemModelJson("tough_leggings"));
			putGeneratedItem(material.name + "_boots", new AlloygeryArmorItem(material, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> AlloygeryGeneratedModelBuilder.createArmorItemModelJson("tough_boots"));
		}
		else
		{
			putGeneratedItem(material.name + "_helmet", new AlloygeryArmorItem(material, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> AlloygeryGeneratedModelBuilder.createArmorItemModelJson("helmet"));
			putGeneratedItem(material.name + "_chestplate", new AlloygeryArmorItem(material, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> AlloygeryGeneratedModelBuilder.createArmorItemModelJson("chestplate"));
			putGeneratedItem(material.name + "_leggings", new AlloygeryArmorItem(material, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> AlloygeryGeneratedModelBuilder.createArmorItemModelJson("leggings"));
			putGeneratedItem(material.name + "_boots", new AlloygeryArmorItem(material, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR)), () -> AlloygeryGeneratedModelBuilder.createArmorItemModelJson("boots"));
		}
	}

	static Item putGeneratedItem(String path, Item item, Supplier<String> generatedModelJsonSupplier)
	{
		Identifier identifier = Alloygery.identifier("item/" + path);

		AlloygeryGeneratedModelBuilder.register(identifier, generatedModelJsonSupplier);

		ALLOYGERY_ITEMS.put(path, item);

		return item;
	}

	public static void register()
	{
		ModBlocks.RAW_ORE_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeRawOreBlockItem);
		ModBlocks.RAW_ORE_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeRawOreItem);
		ModBlocks.METAL_BLOCKS_FOR_MATERIAL.forEach(ModItems::makeMetalBlockItem);

		MAKE_CRAFTING_MATERIALS_FOR_MATERIAL.forEach(ModItems::makeCraftingMaterials);

		makeToolParts();
		makeTools();

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
	}

	private static void register(String path, Item item)
	{
		Registry.register(Registry.ITEM, Alloygery.identifier(path), item);
	}
}
