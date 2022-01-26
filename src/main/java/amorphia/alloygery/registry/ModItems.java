package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.item.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModItems
{
	// @formatter:off
	private static final Map<String, Item> register = new HashMap<>();
	private static final Map<Item, Item> overrides = new HashMap<>();

	public static ItemGroup ALLOYGERY_GROUP;

	//ore block items
	public static final Item TIN_ORE = register("tin_ore", new BlockItem(ModBlocks.TIN_ORE, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item DEEPSLATE_TIN_ORE = register("deepslate_tin_ore", new BlockItem(ModBlocks.DEEPSLATE_TIN_ORE, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_ORE = register("nickel_ore", new BlockItem(ModBlocks.NICKLE_ORE, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_ORE = register("titanium_ore", new BlockItem(ModBlocks.TITANIUM_ORE, new Item.Settings().group(ALLOYGERY_GROUP)));

	//raw ore block items
	public static final Item RAW_TIN_BLOCK = register("raw_tin_block", new BlockItem(ModBlocks.RAW_TIN_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item RAW_NICKEL_BLOCK = register("raw_nickel_block", new BlockItem(ModBlocks.RAW_NICKLE_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item RAW_TITANIUM_BLOCK = register("raw_titanium_block", new BlockItem(ModBlocks.RAW_TITANIUM_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));

	//metal block items
	public static final Item TIN_BLOCK = register("tin_block", new BlockItem(ModBlocks.TIN_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_BLOCK = register("bronze_block", new BlockItem(ModBlocks.BRONZE_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_BLOCK = register("antanium_block", new BlockItem(ModBlocks.ANTANIUM_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_BLOCK = register("steel_block", new BlockItem(ModBlocks.STEEL_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_BLOCK = register("nickel_block", new BlockItem(ModBlocks.NICKLE_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_BLOCK = register("invar_block", new BlockItem(ModBlocks.INVAR_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_BLOCK = register("constantan_block", new BlockItem(ModBlocks.CONSTANTAN_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICEL_BLOCK = register("cupronickel_block", new BlockItem(ModBlocks.CUPRONICKEL_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_BLOCK = register("titanium_block", new BlockItem(ModBlocks.TITANIUM_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_BLOCK = register("titanium_gold_block", new BlockItem(ModBlocks.TITANIUM_GOLD_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_BLOCK = register("nitinol_block", new BlockItem(ModBlocks.NITINOL_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP)));

	//raw ore items
	public static final Item RAW_TIN = register("raw_tin", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item RAW_NICKEL = register("raw_nickel", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item RAW_TITANIUM = register("raw_titanium", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));

	//ingot items
	public static final Item TIN_INGOT = register("tin_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_INGOT = register("bronze_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_INGOT = register("antanium_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_INGOT = register("steel_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_INGOT = register("nickel_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_INGOT = register("invar_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_INGOT = register("constantan_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICKEL_INGOT = register("cupronickel_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_INGOT = register("titanium_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_INGOT = register("titanium_gold_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_INGOT = register("nitinol_ingot", new Item(new Item.Settings().group(ALLOYGERY_GROUP)));

	//copper tools
	public static final Item COPPER_AXE = register("copper_axe", new ModAxeItem(ModToolMaterials.COPPER, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item COPPER_HOE = register("copper_hoe", new ModHoeItem(ModToolMaterials.COPPER, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item COPPER_PICKAXE = register("copper_pickaxe", new ModPickaxeItem(ModToolMaterials.COPPER, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item COPPER_SHOVEL = register("copper_shovel", new ShovelItem(ModToolMaterials.COPPER, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item COPPER_SWORD = register("copper_sword", new ModSwordItem(ModToolMaterials.COPPER, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//bronze tools
	public static final Item BRONZE_AXE = register("bronze_axe", new ModAxeItem(ModToolMaterials.BRONZE, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_HOE = register("bronze_hoe", new ModHoeItem(ModToolMaterials.BRONZE, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_PICKAXE = register("bronze_pickaxe", new ModPickaxeItem(ModToolMaterials.BRONZE, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_SHOVEL = register("bronze_shovel", new ShovelItem(ModToolMaterials.BRONZE, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_SWORD = register("bronze_sword", new ModSwordItem(ModToolMaterials.BRONZE, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//iron tools
	public static final Item IRON_AXE = override(Items.IRON_AXE, new ModAxeItem(ModToolMaterials.IRON, 5.0f, -3.0f, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final Item IRON_HOE = override(Items.IRON_HOE, new ModHoeItem(ModToolMaterials.IRON, -2, -2.8f, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final Item IRON_PICKAXE = override(Items.IRON_PICKAXE, new ModPickaxeItem(ModToolMaterials.IRON, 1, -2.8f, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final Item IRON_SHOVEL = override(Items.IRON_SHOVEL, new ShovelItem(ModToolMaterials.IRON, 1.5f, -3.0f, new Item.Settings().group(ItemGroup.TOOLS)));
	public static final Item IRON_SWORD = override(Items.IRON_SWORD, new ModSwordItem(ModToolMaterials.IRON, 3, -2.4f, new Item.Settings().group(ItemGroup.COMBAT)));

	//antanium tools
	public static final Item ANTANIUM_AXE = register("antanium_axe", new ModAxeItem(ModToolMaterials.ANTANIUM, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_HOE = register("antanium_hoe", new ModHoeItem(ModToolMaterials.ANTANIUM, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_PICKAXE = register("antanium_pickaxe", new ModPickaxeItem(ModToolMaterials.ANTANIUM, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_SHOVEL = register("antanium_shovel", new ShovelItem(ModToolMaterials.ANTANIUM, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_SWORD = register("antanium_sword", new ModSwordItem(ModToolMaterials.ANTANIUM, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//steel tools
	public static final Item STEEL_AXE = register("steel_axe", new ModAxeItem(ModToolMaterials.STEEL, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_HOE = register("steel_hoe", new ModHoeItem(ModToolMaterials.STEEL, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_PICKAXE = register("steel_pickaxe", new ModPickaxeItem(ModToolMaterials.STEEL, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_SHOVEL = register("steel_shovel", new ShovelItem(ModToolMaterials.STEEL, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_SWORD = register("steel_sword", new ModSwordItem(ModToolMaterials.STEEL, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//nickel tools
	public static final Item NICKEL_AXE = register("nickel_axe", new ModAxeItem(ModToolMaterials.NICKEL, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_HOE = register("nickel_hoe", new ModHoeItem(ModToolMaterials.NICKEL, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_PICKAXE = register("nickel_pickaxe", new ModPickaxeItem(ModToolMaterials.NICKEL, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_SHOVEL = register("nickel_shovel", new ShovelItem(ModToolMaterials.NICKEL, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_SWORD = register("nickel_sword", new ModSwordItem(ModToolMaterials.NICKEL, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//invar tools
	public static final Item INVAR_AXE = register("invar_axe", new ModAxeItem(ModToolMaterials.INVAR, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_HOE = register("invar_hoe", new ModHoeItem(ModToolMaterials.INVAR, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_PICKAXE = register("invar_pickaxe", new ModPickaxeItem(ModToolMaterials.INVAR, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_SHOVEL = register("invar_shovel", new ShovelItem(ModToolMaterials.INVAR, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_SWORD = register("invar_sword", new ModSwordItem(ModToolMaterials.INVAR, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//constantan tools
	public static final Item CONSTANTAN_AXE = register("constantan_axe", new ModAxeItem(ModToolMaterials.CONSTANTAN, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_HOE = register("constantan_hoe", new ModHoeItem(ModToolMaterials.CONSTANTAN, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_PICKAXE = register("constantan_pickaxe", new ModPickaxeItem(ModToolMaterials.CONSTANTAN, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_SHOVEL = register("constantan_shovel", new ShovelItem(ModToolMaterials.CONSTANTAN, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_SWORD = register("constantan_sword", new ModSwordItem(ModToolMaterials.CONSTANTAN, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//cupronickel tools
	public static final Item CUPRONICKEL_AXE = register("cupronickel_axe", new ModAxeItem(ModToolMaterials.CUPRONICKEL, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICKEL_HOE = register("cupronickel_hoe", new ModHoeItem(ModToolMaterials.CUPRONICKEL, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICKEL_PICKAXE = register("cupronickel_pickaxe", new ModPickaxeItem(ModToolMaterials.CUPRONICKEL, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICKEL_SHOVEL = register("cupronickel_shovel", new ShovelItem(ModToolMaterials.CUPRONICKEL, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICKEL_SWORD = register("cupronickel_sword", new ModSwordItem(ModToolMaterials.CUPRONICKEL, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//titanium tools
	public static final Item TITANIUM_AXE = register("titanium_axe", new ModAxeItem(ModToolMaterials.TITANIUM, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_HOE = register("titanium_hoe", new ModHoeItem(ModToolMaterials.TITANIUM, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_PICKAXE = register("titanium_pickaxe", new ModPickaxeItem(ModToolMaterials.TITANIUM, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_SHOVEL = register("titanium_shovel", new ShovelItem(ModToolMaterials.TITANIUM, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_SWORD = register("titanium_sword", new ModSwordItem(ModToolMaterials.TITANIUM, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//titanium_gold tools
	public static final Item TITANIUM_GOLD_AXE = register("titanium_gold_axe", new ModAxeItem(ModToolMaterials.TITANIUM_GOLD, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_HOE = register("titanium_gold_hoe", new ModHoeItem(ModToolMaterials.TITANIUM_GOLD, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_PICKAXE = register("titanium_gold_pickaxe", new ModPickaxeItem(ModToolMaterials.TITANIUM_GOLD, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_SHOVEL = register("titanium_gold_shovel", new ShovelItem(ModToolMaterials.TITANIUM_GOLD, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_SWORD = register("titanium_gold_sword", new ModSwordItem(ModToolMaterials.TITANIUM_GOLD, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//nitinol tools
	public static final Item NITINOL_AXE = register("nitinol_axe", new ModAxeItem(ModToolMaterials.NITINOL, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_HOE = register("nitinol_hoe", new ModHoeItem(ModToolMaterials.NITINOL, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_PICKAXE = register("nitinol_pickaxe", new ModPickaxeItem(ModToolMaterials.NITINOL, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_SHOVEL = register("nitinol_shovel", new ShovelItem(ModToolMaterials.NITINOL, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_SWORD = register("nitinol_sword", new ModSwordItem(ModToolMaterials.NITINOL, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP)));

	//copper armor
	public static final Item COPPER_HELMET = register("copper_helmet", new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item COPPER_CHESTPLATE = register("copper_chestplate", new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item COPPER_LEGGINGS = register("copper_leggings", new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item COPPER_BOOTS = register("copper_boots", new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//antanium armor
	public static final Item ANTANIUM_HELMET = register("antanium_helmet", new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_CHESTPLATE = register("antanium_chestplate", new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_LEGGINGS = register("antanium_leggings", new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item ANTANIUM_BOOTS = register("antanium_boots", new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//bronze armor
	public static final Item BRONZE_HELMET = register("bronze_helmet", new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_CHESTPLATE = register("bronze_chestplate", new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_LEGGINGS = register("bronze_leggings", new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BRONZE_BOOTS = register("bronze_boots", new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//steel armor
	public static final Item STEEL_HELMET = register("steel_helmet", new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_CHESTPLATE = register("steel_chestplate", new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_LEGGINGS = register("steel_leggings", new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item STEEL_BOOTS = register("steel_boots", new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//nickel armor
	public static final Item NICKEL_HELMET = register("nickel_helmet", new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_CHESTPLATE = register("nickel_chestplate", new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_LEGGINGS = register("nickel_leggings", new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NICKEL_BOOTS = register("nickel_boots", new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//invar armor
	public static final Item INVAR_HELMET = register("invar_helmet", new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_CHESTPLATE = register("invar_chestplate", new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_LEGGINGS = register("invar_leggings", new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item INVAR_BOOTS = register("invar_boots", new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//constantan armor
	public static final Item CONSTANTAN_HELMET = register("constantan_helmet", new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_CHESTPLATE = register("constantan_chestplate", new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_LEGGINGS = register("constantan_leggings", new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CONSTANTAN_BOOTS = register("constantan_boots", new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//cupronickel armor
	public static final Item CUPRONICKEL_HELMET = register("cupronickel_helmet", new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICKEL_CHESTPLATE = register("cupronickel_chestplate", new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICKEL_LEGGINGS = register("cupronickel_leggings", new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item CUPRONICKEL_BOOTS = register("cupronickel_boots", new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//titanium armor
	public static final Item TITANIUM_HELMET = register("titanium_helmet", new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_CHESTPLATE = register("titanium_chestplate", new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_LEGGINGS = register("titanium_leggings", new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_BOOTS = register("titanium_boots", new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//titanium_gold armor
	public static final Item TITANIUM_GOLD_HELMET = register("titanium_gold_helmet", new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_CHESTPLATE = register("titanium_gold_chestplate", new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_LEGGINGS = register("titanium_gold_leggings", new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item TITANIUM_GOLD_BOOTS = register("titanium_gold_boots", new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//nitinol armor
	public static final Item NITINOL_HELMET = register("nitinol_helmet", new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_CHESTPLATE = register("nitinol_chestplate", new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_LEGGINGS = register("nitinol_leggings", new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item NITINOL_BOOTS = register("nitinol_boots", new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP)));

	//alloy kiln block items
	public static final Item ALLOY_KILN = register("alloy_kiln", new BlockItem(ModBlocks.ALLOY_KILN, new Item.Settings().group(ALLOYGERY_GROUP)));
	public static final Item BLAST_ALLOY_KILN = register("blast_alloy_kiln", new BlockItem(ModBlocks.BLAST_ALLOY_KILN, new Item.Settings().group(ALLOYGERY_GROUP)));
	// @formatter:on

	public static void register()
	{
		setupGroupOrder();

		register.forEach((path, item) -> Registry.register(Registry.ITEM, Alloygery.identifier(path), item));

		overrides.forEach((override, item) -> {
			//wizardry
			Identifier mcId = Registry.ITEM.getId(override);
			Item i = Registry.ITEM.get(mcId);
			int id = Registry.ITEM.getRawId(i);

			Registry.ITEM.set(id, RegistryKey.of(Registry.ITEM.getKey(), mcId), item, Registry.ITEM.getLifecycle());
		});
	}

	private static Item register(String path, Item item)
	{
		register.put(path, item);
		return item;
	}

	private static Item override(Item override, Item item)
	{
		overrides.put(override, item);
		return item;
	}

	private static void setupGroupOrder()
	{
		// @formatter:off
		List<ItemStack> groupList = List.of(
				new ItemStack(ALLOY_KILN),
				new ItemStack(BLAST_ALLOY_KILN),
				new ItemStack(TIN_ORE),
				new ItemStack(DEEPSLATE_TIN_ORE),
				new ItemStack(NICKEL_ORE),
				new ItemStack(TITANIUM_ORE),
				new ItemStack(RAW_TIN_BLOCK),
				new ItemStack(RAW_NICKEL_BLOCK),
				new ItemStack(RAW_TITANIUM_BLOCK),
				new ItemStack(TIN_BLOCK),
				new ItemStack(BRONZE_BLOCK),
				new ItemStack(ANTANIUM_BLOCK),
				new ItemStack(STEEL_BLOCK),
				new ItemStack(NICKEL_BLOCK),
				new ItemStack(INVAR_BLOCK),
				new ItemStack(CONSTANTAN_BLOCK),
				new ItemStack(CUPRONICEL_BLOCK),
				new ItemStack(TITANIUM_BLOCK),
				new ItemStack(TITANIUM_GOLD_BLOCK),
				new ItemStack(NITINOL_BLOCK),
				new ItemStack(RAW_TIN),
				new ItemStack(RAW_NICKEL),
				new ItemStack(RAW_TITANIUM),
				new ItemStack(TIN_INGOT),
				new ItemStack(BRONZE_INGOT),
				new ItemStack(ANTANIUM_INGOT),
				new ItemStack(STEEL_INGOT),
				new ItemStack(NICKEL_INGOT),
				new ItemStack(INVAR_INGOT),
				new ItemStack(CONSTANTAN_INGOT),
				new ItemStack(CUPRONICKEL_INGOT),
				new ItemStack(TITANIUM_INGOT),
				new ItemStack(TITANIUM_GOLD_INGOT),
				new ItemStack(NITINOL_INGOT),
				new ItemStack(COPPER_AXE),
				new ItemStack(COPPER_HOE),
				new ItemStack(COPPER_PICKAXE),
				new ItemStack(COPPER_SHOVEL),
				new ItemStack(COPPER_SWORD),
				new ItemStack(BRONZE_AXE),
				new ItemStack(BRONZE_HOE),
				new ItemStack(BRONZE_PICKAXE),
				new ItemStack(BRONZE_SHOVEL),
				new ItemStack(BRONZE_SWORD),
				new ItemStack(ANTANIUM_AXE),
				new ItemStack(ANTANIUM_HOE),
				new ItemStack(ANTANIUM_PICKAXE),
				new ItemStack(ANTANIUM_SHOVEL),
				new ItemStack(ANTANIUM_SWORD),
				new ItemStack(STEEL_AXE),
				new ItemStack(STEEL_HOE),
				new ItemStack(STEEL_PICKAXE),
				new ItemStack(STEEL_SHOVEL),
				new ItemStack(STEEL_SWORD),
				new ItemStack(NICKEL_AXE),
				new ItemStack(NICKEL_HOE),
				new ItemStack(NICKEL_PICKAXE),
				new ItemStack(NICKEL_SHOVEL),
				new ItemStack(NICKEL_SWORD),
				new ItemStack(INVAR_AXE),
				new ItemStack(INVAR_HOE),
				new ItemStack(INVAR_PICKAXE),
				new ItemStack(INVAR_SHOVEL),
				new ItemStack(INVAR_SWORD),
				new ItemStack(CONSTANTAN_AXE),
				new ItemStack(CONSTANTAN_HOE),
				new ItemStack(CONSTANTAN_PICKAXE),
				new ItemStack(CONSTANTAN_SHOVEL),
				new ItemStack(CONSTANTAN_SWORD),
				new ItemStack(CUPRONICKEL_AXE),
				new ItemStack(CUPRONICKEL_HOE),
				new ItemStack(CUPRONICKEL_PICKAXE),
				new ItemStack(CUPRONICKEL_SHOVEL),
				new ItemStack(CUPRONICKEL_SWORD),
				new ItemStack(TITANIUM_AXE),
				new ItemStack(TITANIUM_HOE),
				new ItemStack(TITANIUM_PICKAXE),
				new ItemStack(TITANIUM_SHOVEL),
				new ItemStack(TITANIUM_SWORD),
				new ItemStack(TITANIUM_GOLD_AXE),
				new ItemStack(TITANIUM_GOLD_HOE),
				new ItemStack(TITANIUM_GOLD_PICKAXE),
				new ItemStack(TITANIUM_GOLD_SHOVEL),
				new ItemStack(TITANIUM_GOLD_SWORD),
				new ItemStack(NITINOL_AXE),
				new ItemStack(NITINOL_HOE),
				new ItemStack(NITINOL_PICKAXE),
				new ItemStack(NITINOL_SHOVEL),
				new ItemStack(NITINOL_SWORD),
				new ItemStack(COPPER_HELMET),
				new ItemStack(COPPER_CHESTPLATE),
				new ItemStack(COPPER_LEGGINGS),
				new ItemStack(COPPER_BOOTS),
				new ItemStack(BRONZE_HELMET),
				new ItemStack(BRONZE_CHESTPLATE),
				new ItemStack(BRONZE_LEGGINGS),
				new ItemStack(BRONZE_BOOTS),
				new ItemStack(ANTANIUM_HELMET),
				new ItemStack(ANTANIUM_CHESTPLATE),
				new ItemStack(ANTANIUM_LEGGINGS),
				new ItemStack(ANTANIUM_BOOTS),
				new ItemStack(STEEL_HELMET),
				new ItemStack(STEEL_CHESTPLATE),
				new ItemStack(STEEL_LEGGINGS),
				new ItemStack(STEEL_BOOTS),
				new ItemStack(NICKEL_HELMET),
				new ItemStack(NICKEL_CHESTPLATE),
				new ItemStack(NICKEL_LEGGINGS),
				new ItemStack(NICKEL_BOOTS),
				new ItemStack(CONSTANTAN_HELMET),
				new ItemStack(CONSTANTAN_CHESTPLATE),
				new ItemStack(CONSTANTAN_LEGGINGS),
				new ItemStack(CONSTANTAN_BOOTS),
				new ItemStack(CUPRONICKEL_HELMET),
				new ItemStack(CUPRONICKEL_CHESTPLATE),
				new ItemStack(CUPRONICKEL_LEGGINGS),
				new ItemStack(CUPRONICKEL_BOOTS),
				new ItemStack(TITANIUM_HELMET),
				new ItemStack(TITANIUM_CHESTPLATE),
				new ItemStack(TITANIUM_LEGGINGS),
				new ItemStack(TITANIUM_BOOTS),
				new ItemStack(TITANIUM_GOLD_HELMET),
				new ItemStack(TITANIUM_GOLD_CHESTPLATE),
				new ItemStack(TITANIUM_GOLD_LEGGINGS),
				new ItemStack(TITANIUM_GOLD_BOOTS),
				new ItemStack(NITINOL_HELMET),
				new ItemStack(NITINOL_CHESTPLATE),
				new ItemStack(NITINOL_LEGGINGS),
				new ItemStack(NITINOL_BOOTS)
		);

		// @formatter:on

		ALLOYGERY_GROUP = FabricItemGroupBuilder.create(Alloygery.identifier("alloygery"))
												.icon(() -> new ItemStack(ModItems.BRONZE_INGOT))
												.appendItems(stacks -> stacks.addAll(groupList))
												.build();
	}
}
