package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
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
	//ore block items
	public static final Item TIN_ORE = new BlockItem(ModBlocks.TIN_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item DEEPSLATE_TIN_ORE = new BlockItem(ModBlocks.DEEPSLATE_TIN_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item NICKEL_ORE = new BlockItem(ModBlocks.NICKLE_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item TITANIUM_ORE = new BlockItem(ModBlocks.TITANIUM_ORE, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	//raw ore block items
	public static final Item RAW_TIN_BLOCK = new BlockItem(ModBlocks.RAW_TIN_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item RAW_NICKEL_BLOCK = new BlockItem(ModBlocks.RAW_NICKLE_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item RAW_TITANIUM_BLOCK = new BlockItem(ModBlocks.RAW_TITANIUM_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	//metal block items
	public static final Item TIN_BLOCK = new BlockItem(ModBlocks.TIN_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item BRONZE_BLOCK = new BlockItem(ModBlocks.BRONZE_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item ANTANIUM_BLOCK = new BlockItem(ModBlocks.ANTANIUM_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item STEEL_BLOCK = new BlockItem(ModBlocks.STEEL_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item NICKEL_BLOCK = new BlockItem(ModBlocks.NICKLE_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item INVAR_BLOCK = new BlockItem(ModBlocks.INVAR_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item CONSTANTAN_BLOCK = new BlockItem(ModBlocks.CONSTANTAN_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item CUPRONICEL_BLOCK = new BlockItem(ModBlocks.CUPRONICKEL_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item TITANIUM_BLOCK = new BlockItem(ModBlocks.TITANIUM_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item TITANIUM_GOLD_BLOCK = new BlockItem(ModBlocks.TITANIUM_GOLD_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item NITINOL_BLOCK = new BlockItem(ModBlocks.NITINOL_BLOCK, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	//raw ore items
	public static final Item RAW_TIN = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item RAW_NICKEL = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item RAW_TITANIUM = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	//ingot items
	public static final Item TIN_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item BRONZE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item ANTANIUM_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item STEEL_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item NICKEL_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item INVAR_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item CONSTANTAN_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item CUPRONICKEL_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item TITANIUM_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item TITANIUM_GOLD_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item NITINOL_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	//copper tools
	public static final Item COPPER_AXE = new ModAxeItem(ModToolMaterials.COPPER, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item COPPER_HOE = new ModHoeItem(ModToolMaterials.COPPER, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item COPPER_PICKAXE = new ModPickaxeItem(ModToolMaterials.COPPER, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item COPPER_SHOVEL = new ShovelItem(ModToolMaterials.COPPER, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item COPPER_SWORD = new ModSwordItem(ModToolMaterials.COPPER, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//bronze tools
	public static final Item BRONZE_AXE = new ModAxeItem(ModToolMaterials.BRONZE, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item BRONZE_HOE = new ModHoeItem(ModToolMaterials.BRONZE, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item BRONZE_PICKAXE = new ModPickaxeItem(ModToolMaterials.BRONZE, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item BRONZE_SHOVEL = new ShovelItem(ModToolMaterials.BRONZE, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item BRONZE_SWORD = new ModSwordItem(ModToolMaterials.BRONZE, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//iron tools
	public static final Item IRON_AXE = new ModAxeItem(ModToolMaterials.IRON, 5.0f, -3.0f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_HOE = new ModHoeItem(ModToolMaterials.IRON, -2, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_PICKAXE = new ModPickaxeItem(ModToolMaterials.IRON, 1, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_SHOVEL = new ShovelItem(ModToolMaterials.IRON, 1.5f, -3.0f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_SWORD = new ModSwordItem(ModToolMaterials.IRON, 3, -2.4f, new Item.Settings().group(ItemGroup.COMBAT));

	//gold tools
	public static final Item GOLD_AXE = new ModAxeItem(ModToolMaterials.GOLD, 5.0f, -3.0f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLD_HOE = new ModHoeItem(ModToolMaterials.GOLD, -2, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLD_PICKAXE = new ModPickaxeItem(ModToolMaterials.GOLD, 1, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLD_SHOVEL = new ShovelItem(ModToolMaterials.GOLD, 1.5f, -3.0f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item GOLD_SWORD = new ModSwordItem(ModToolMaterials.GOLD, 3, -2.4f, new Item.Settings().group(ItemGroup.COMBAT));

	//antanium tools
	public static final Item ANTANIUM_AXE = new ModAxeItem(ModToolMaterials.ANTANIUM, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item ANTANIUM_HOE = new ModHoeItem(ModToolMaterials.ANTANIUM, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item ANTANIUM_PICKAXE = new ModPickaxeItem(ModToolMaterials.ANTANIUM, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item ANTANIUM_SHOVEL = new ShovelItem(ModToolMaterials.ANTANIUM, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item ANTANIUM_SWORD = new ModSwordItem(ModToolMaterials.ANTANIUM, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//steel tools
	public static final Item STEEL_AXE = new ModAxeItem(ModToolMaterials.STEEL, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item STEEL_HOE = new ModHoeItem(ModToolMaterials.STEEL, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item STEEL_PICKAXE = new ModPickaxeItem(ModToolMaterials.STEEL, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item STEEL_SHOVEL = new ShovelItem(ModToolMaterials.STEEL, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item STEEL_SWORD = new ModSwordItem(ModToolMaterials.STEEL, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//nickel tools
	public static final Item NICKEL_AXE = new ModAxeItem(ModToolMaterials.NICKEL, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NICKEL_HOE = new ModHoeItem(ModToolMaterials.NICKEL, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NICKEL_PICKAXE = new ModPickaxeItem(ModToolMaterials.NICKEL, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NICKEL_SHOVEL = new ShovelItem(ModToolMaterials.NICKEL, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NICKEL_SWORD = new ModSwordItem(ModToolMaterials.NICKEL, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//invar tools
	public static final Item INVAR_AXE = new ModAxeItem(ModToolMaterials.INVAR, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item INVAR_HOE = new ModHoeItem(ModToolMaterials.INVAR, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item INVAR_PICKAXE = new ModPickaxeItem(ModToolMaterials.INVAR, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item INVAR_SHOVEL = new ShovelItem(ModToolMaterials.INVAR, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item INVAR_SWORD = new ModSwordItem(ModToolMaterials.INVAR, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//constantan tools
	public static final Item CONSTANTAN_AXE = new ModAxeItem(ModToolMaterials.CONSTANTAN, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CONSTANTAN_HOE = new ModHoeItem(ModToolMaterials.CONSTANTAN, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CONSTANTAN_PICKAXE = new ModPickaxeItem(ModToolMaterials.CONSTANTAN, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CONSTANTAN_SHOVEL = new ShovelItem(ModToolMaterials.CONSTANTAN, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CONSTANTAN_SWORD = new ModSwordItem(ModToolMaterials.CONSTANTAN, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//cupronickel tools
	public static final Item CUPRONICKEL_AXE = new ModAxeItem(ModToolMaterials.CUPRONICKEL, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CUPRONICKEL_HOE = new ModHoeItem(ModToolMaterials.CUPRONICKEL, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CUPRONICKEL_PICKAXE = new ModPickaxeItem(ModToolMaterials.CUPRONICKEL, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CUPRONICKEL_SHOVEL = new ShovelItem(ModToolMaterials.CUPRONICKEL, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CUPRONICKEL_SWORD = new ModSwordItem(ModToolMaterials.CUPRONICKEL, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//titanium tools
	public static final Item TITANIUM_AXE = new ModAxeItem(ModToolMaterials.TITANIUM, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_HOE = new ModHoeItem(ModToolMaterials.TITANIUM, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_PICKAXE = new ModPickaxeItem(ModToolMaterials.TITANIUM, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_SHOVEL = new ShovelItem(ModToolMaterials.TITANIUM, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_SWORD = new ModSwordItem(ModToolMaterials.TITANIUM, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//titanium_gold tools
	public static final Item TITANIUM_GOLD_AXE = new ModAxeItem(ModToolMaterials.TITANIUM_GOLD, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_GOLD_HOE = new ModHoeItem(ModToolMaterials.TITANIUM_GOLD, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_GOLD_PICKAXE = new ModPickaxeItem(ModToolMaterials.TITANIUM_GOLD, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_GOLD_SHOVEL = new ShovelItem(ModToolMaterials.TITANIUM_GOLD, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_GOLD_SWORD = new ModSwordItem(ModToolMaterials.TITANIUM_GOLD, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//nitinol tools
	public static final Item NITINOL_AXE = new ModAxeItem(ModToolMaterials.NITINOL, 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NITINOL_HOE = new ModHoeItem(ModToolMaterials.NITINOL, -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NITINOL_PICKAXE = new ModPickaxeItem(ModToolMaterials.NITINOL, 1, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NITINOL_SHOVEL = new ShovelItem(ModToolMaterials.NITINOL, 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NITINOL_SWORD = new ModSwordItem(ModToolMaterials.NITINOL, 3, -2.4f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//copper armor
	public static final Item COPPER_HELMET = new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item COPPER_CHESTPLATE = new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item COPPER_LEGGINGS = new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item COPPER_BOOTS = new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//antanium armor
	public static final Item ANTANIUM_HELMET = new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item ANTANIUM_CHESTPLATE = new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item ANTANIUM_LEGGINGS = new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item ANTANIUM_BOOTS = new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//bronze armor
	public static final Item BRONZE_HELMET = new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item BRONZE_CHESTPLATE = new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item BRONZE_LEGGINGS = new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item BRONZE_BOOTS = new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//iron armor overrides
	public static final Item IRON_HELMET = new ArmorItem(ModArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item IRON_CHESTPLATE = new ArmorItem(ModArmorMaterials.IRON, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item IRON_LEGGINGS = new ArmorItem(ModArmorMaterials.IRON, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item IRON_BOOTS = new ArmorItem(ModArmorMaterials.IRON, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

	//steel armor
	public static final Item STEEL_HELMET = new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item STEEL_CHESTPLATE = new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item STEEL_LEGGINGS = new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item STEEL_BOOTS = new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//nickel armor
	public static final Item NICKEL_HELMET = new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NICKEL_CHESTPLATE = new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NICKEL_LEGGINGS = new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NICKEL_BOOTS = new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//invar armor
	public static final Item INVAR_HELMET = new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item INVAR_CHESTPLATE = new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item INVAR_LEGGINGS = new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item INVAR_BOOTS = new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//constantan armor
	public static final Item CONSTANTAN_HELMET = new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CONSTANTAN_CHESTPLATE = new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CONSTANTAN_LEGGINGS = new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CONSTANTAN_BOOTS = new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//cupronickel armor
	public static final Item CUPRONICKEL_HELMET = new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CUPRONICKEL_CHESTPLATE = new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CUPRONICKEL_LEGGINGS = new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item CUPRONICKEL_BOOTS = new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//titanium armor
	public static final Item TITANIUM_HELMET = new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_CHESTPLATE = new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_LEGGINGS = new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_BOOTS = new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//titanium_gold armor
	public static final Item TITANIUM_GOLD_HELMET = new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_GOLD_CHESTPLATE = new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_GOLD_LEGGINGS = new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item TITANIUM_GOLD_BOOTS = new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//nitinol armor
	public static final Item NITINOL_HELMET = new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.HEAD, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NITINOL_CHESTPLATE = new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.CHEST, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NITINOL_LEGGINGS = new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.LEGS, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
	public static final Item NITINOL_BOOTS = new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.FEET, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));

	//alloy kiln block items
	public static final Item ALLOY_KILN = new BlockItem(ModBlocks.ALLOY_KILN, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));
	public static final Item BLAST_ALLOY_KILN = new BlockItem(ModBlocks.BLAST_ALLOY_KILN, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	//extra materials
	public static final Item TIN_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item TIN_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item TIN_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item COPPER_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item COPPER_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item COPPER_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item BRONZE_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item BRONZE_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item BRONZE_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item IRON_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item IRON_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item IRON_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item GOLD_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item GOLD_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item GOLD_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item ANTANIUM_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item ANTANIUM_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item ANTANIUM_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item STEEL_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item STEEL_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item STEEL_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item NICKEL_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item NICKEL_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item NICKEL_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item INVAR_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item INVAR_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item INVAR_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item CONSTANTAN_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item CONSTANTAN_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item CONSTANTAN_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item CUPRONICKEL_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item CUPRONICKEL_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item CUPRONICKEL_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item TITANIUM_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item TITANIUM_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item TITANIUM_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item TITANIUM_GOLD_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item TITANIUM_GOLD_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item TITANIUM_GOLD_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	public static final Item NITINOL_DOUBLE_INGOT = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item NITINOL_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
	public static final Item NITINOL_HEAVY_PLATE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));

	//tool heads
	public static final Item COPPER_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item COPPER_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item COPPER_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item COPPER_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item COPPER_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item BRONZE_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item BRONZE_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item BRONZE_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item BRONZE_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item BRONZE_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item IRON_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item IRON_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item IRON_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item IRON_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item IRON_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item GOLD_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item GOLD_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item GOLD_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item GOLD_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item GOLD_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item ANTANIUM_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item ANTANIUM_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item ANTANIUM_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item ANTANIUM_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item ANTANIUM_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item STEEL_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item STEEL_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item STEEL_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item STEEL_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item STEEL_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item NICKEL_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item NICKEL_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item NICKEL_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item NICKEL_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item NICKEL_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item INVAR_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item INVAR_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item INVAR_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item INVAR_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item INVAR_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item CONSTANTAN_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item CONSTANTAN_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item CONSTANTAN_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item CONSTANTAN_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item CONSTANTAN_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item CUPRONICKEL_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item CUPRONICKEL_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item CUPRONICKEL_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item CUPRONICKEL_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item CUPRONICKEL_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item TITANIUM_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item TITANIUM_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item TITANIUM_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item TITANIUM_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item TITANIUM_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item TITANIUM_GOLD_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item TITANIUM_GOLD_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item TITANIUM_GOLD_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item TITANIUM_GOLD_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item TITANIUM_GOLD_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	public static final Item NITINOL_AXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item NITINOL_HOE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item NITINOL_PICKAXE_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item NITINOL_SHOVEL_HEAD = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item NITINOL_SWORD_BLADE = new Item(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_PARTS));

	//crafting tools
	public static final Item ANVIL_HAMMER = new BaseCraftingItem(new Item.Settings().maxDamage(AlloygeryConfig.ironGear.uses.getValue()).group(Alloygery.ALLOYGERY_GROUP_PARTS));
	public static final Item SMITHING_HAMMER = new BaseCraftingItem(new Item.Settings().maxDamage(AlloygeryConfig.steelGear.uses.getValue()).group(Alloygery.ALLOYGERY_GROUP_PARTS));

	//smithing anvil
	public static final Item SMITHING_ANVIL = new BlockItem(ModBlocks.SMITHING_ANVIL, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_BLOCKS));

	// @formatter:on

	public static void register()
	{
		//kilns
		register("alloy_kiln", ALLOY_KILN);
		register("blast_alloy_kiln", BLAST_ALLOY_KILN);

		//smithing anvil
		register("smithing_anvil", SMITHING_ANVIL);

		//ores
		register("tin_ore", TIN_ORE);
		register("deepslate_tin_ore", DEEPSLATE_TIN_ORE);
		register("nickel_ore", NICKEL_ORE);
		register("titanium_ore", TITANIUM_ORE);

		//raw ore blocks
		register("raw_tin_block", RAW_TIN_BLOCK);
		register("raw_nickel_block", RAW_NICKEL_BLOCK);
		register("raw_titanium_block", RAW_TITANIUM_BLOCK);

		//metal blocks
		register("tin_block", TIN_BLOCK);
		register("bronze_block", BRONZE_BLOCK);
		register("antanium_block", ANTANIUM_BLOCK);
		register("steel_block", STEEL_BLOCK);
		register("nickel_block", NICKEL_BLOCK);
		register("invar_block", INVAR_BLOCK);
		register("constantan_block", CONSTANTAN_BLOCK);
		register("cupronickel_block", CUPRONICEL_BLOCK);
		register("titanium_block", TITANIUM_BLOCK);
		register("titanium_gold_block", TITANIUM_GOLD_BLOCK);
		register("nitinol_block", NITINOL_BLOCK);

		//raw ores
		register("raw_tin", RAW_TIN);
		register("raw_nickel", RAW_NICKEL);
		register("raw_titanium", RAW_TITANIUM);

		//materials
		register("tin_ingot", TIN_INGOT);
		register("tin_double_ingot", TIN_DOUBLE_INGOT);
		register("tin_plate", TIN_PLATE);
		register("tin_heavy_plate", TIN_HEAVY_PLATE);

		register("copper_double_ingot", COPPER_DOUBLE_INGOT);
		register("copper_plate", COPPER_PLATE);
		register("copper_heavy_plate", COPPER_HEAVY_PLATE);

		register("bronze_ingot", BRONZE_INGOT);
		register("bronze_double_ingot", BRONZE_DOUBLE_INGOT);
		register("bronze_plate", BRONZE_PLATE);
		register("bronze_heavy_plate", BRONZE_HEAVY_PLATE);

		register("iron_double_ingot", IRON_DOUBLE_INGOT);
		register("iron_plate", IRON_PLATE);
		register("iron_heavy_plate", IRON_HEAVY_PLATE);

		register("gold_double_ingot", GOLD_DOUBLE_INGOT);
		register("gold_plate", GOLD_PLATE);
		register("gold_heavy_plate", GOLD_HEAVY_PLATE);

		register("antanium_ingot", ANTANIUM_INGOT);
		register("antanium_double_ingot", ANTANIUM_DOUBLE_INGOT);
		register("antanium_plate", ANTANIUM_PLATE);
		register("antanium_heavy_plate", ANTANIUM_HEAVY_PLATE);

		register("steel_ingot", STEEL_INGOT);
		register("steel_double_ingot", STEEL_DOUBLE_INGOT);
		register("steel_plate", STEEL_PLATE);
		register("steel_heavy_plate", STEEL_HEAVY_PLATE);

		register("nickel_ingot", NICKEL_INGOT);
		register("nickel_double_ingot", NICKEL_DOUBLE_INGOT);
		register("nickel_plate", NICKEL_PLATE);
		register("nickel_heavy_plate", NICKEL_HEAVY_PLATE);

		register("invar_ingot", INVAR_INGOT);
		register("invar_double_ingot", INVAR_DOUBLE_INGOT);
		register("invar_plate", INVAR_PLATE);
		register("invar_heavy_plate", INVAR_HEAVY_PLATE);

		register("constantan_ingot", CONSTANTAN_INGOT);
		register("constantan_double_ingot", CONSTANTAN_DOUBLE_INGOT);
		register("constantan_plate", CONSTANTAN_PLATE);
		register("constantan_heavy_plate", CONSTANTAN_HEAVY_PLATE);

		register("cupronickel_ingot", CUPRONICKEL_INGOT);
		register("cupronickel_double_ingot", CUPRONICKEL_DOUBLE_INGOT);
		register("cupronickel_plate", CUPRONICKEL_PLATE);
		register("cupronickel_heavy_plate", CUPRONICKEL_HEAVY_PLATE);

		register("titanium_ingot", TITANIUM_INGOT);
		register("titanium_double_ingot", TITANIUM_DOUBLE_INGOT);
		register("titanium_plate", TITANIUM_PLATE);
		register("titanium_heavy_plate", TITANIUM_HEAVY_PLATE);

		register("titanium_gold_ingot", TITANIUM_GOLD_INGOT);
		register("titanium_gold_double_ingot", TITANIUM_GOLD_DOUBLE_INGOT);
		register("titanium_gold_plate", TITANIUM_GOLD_PLATE);
		register("titanium_gold_heavy_plate", TITANIUM_GOLD_HEAVY_PLATE);

		register("nitinol_ingot", NITINOL_INGOT);
		register("nitinol_double_ingot", NITINOL_DOUBLE_INGOT);
		register("nitinol_plate", NITINOL_PLATE);
		register("nitinol_heavy_plate", NITINOL_HEAVY_PLATE);


		//copper tools and armor
		register("copper_axe", COPPER_AXE);
		register("copper_hoe", COPPER_HOE);
		register("copper_pickaxe", COPPER_PICKAXE);
		register("copper_shovel", COPPER_SHOVEL);
		register("copper_sword", COPPER_SWORD);
		register("copper_helmet", COPPER_HELMET);
		register("copper_chestplate", COPPER_CHESTPLATE);
		register("copper_leggings", COPPER_LEGGINGS);
		register("copper_boots", COPPER_BOOTS);

		//bronze tools and armor
		register("bronze_axe", BRONZE_AXE);
		register("bronze_hoe", BRONZE_HOE);
		register("bronze_pickaxe", BRONZE_PICKAXE);
		register("bronze_shovel", BRONZE_SHOVEL);
		register("bronze_sword", BRONZE_SWORD);
		register("bronze_helmet", BRONZE_HELMET);
		register("bronze_chestplate", BRONZE_CHESTPLATE);
		register("bronze_leggings", BRONZE_LEGGINGS);
		register("bronze_boots", BRONZE_BOOTS);

		//antanium tools and armor
		register("antanium_axe", ANTANIUM_AXE);
		register("antanium_hoe", ANTANIUM_HOE);
		register("antanium_pickaxe", ANTANIUM_PICKAXE);
		register("antanium_shovel", ANTANIUM_SHOVEL);
		register("antanium_sword", ANTANIUM_SWORD);
		register("antanium_helmet", ANTANIUM_HELMET);
		register("antanium_chestplate", ANTANIUM_CHESTPLATE);
		register("antanium_leggings", ANTANIUM_LEGGINGS);
		register("antanium_boots", ANTANIUM_BOOTS);

		//steel tools and armor
		register("steel_axe", STEEL_AXE);
		register("steel_hoe", STEEL_HOE);
		register("steel_pickaxe", STEEL_PICKAXE);
		register("steel_shovel", STEEL_SHOVEL);
		register("steel_sword", STEEL_SWORD);
		register("steel_helmet", STEEL_HELMET);
		register("steel_chestplate", STEEL_CHESTPLATE);
		register("steel_leggings", STEEL_LEGGINGS);
		register("steel_boots", STEEL_BOOTS);

		//nickel tools and armor
		register("nickel_axe", NICKEL_AXE);
		register("nickel_hoe", NICKEL_HOE);
		register("nickel_pickaxe", NICKEL_PICKAXE);
		register("nickel_shovel", NICKEL_SHOVEL);
		register("nickel_sword", NICKEL_SWORD);
		register("nickel_helmet", NICKEL_HELMET);
		register("nickel_chestplate", NICKEL_CHESTPLATE);
		register("nickel_leggings", NICKEL_LEGGINGS);
		register("nickel_boots", NICKEL_BOOTS);

		//invar tools and armor
		register("invar_axe", INVAR_AXE);
		register("invar_hoe", INVAR_HOE);
		register("invar_pickaxe", INVAR_PICKAXE);
		register("invar_shovel", INVAR_SHOVEL);
		register("invar_sword", INVAR_SWORD);
		register("invar_helmet", INVAR_HELMET);
		register("invar_chestplate", INVAR_CHESTPLATE);
		register("invar_leggings", INVAR_LEGGINGS);
		register("invar_boots", INVAR_BOOTS);

		//constantan tools and armor
		register("constantan_axe", CONSTANTAN_AXE);
		register("constantan_hoe", CONSTANTAN_HOE);
		register("constantan_pickaxe", CONSTANTAN_PICKAXE);
		register("constantan_shovel", CONSTANTAN_SHOVEL);
		register("constantan_sword", CONSTANTAN_SWORD);
		register("constantan_helmet", CONSTANTAN_HELMET);
		register("constantan_chestplate", CONSTANTAN_CHESTPLATE);
		register("constantan_leggings", CONSTANTAN_LEGGINGS);
		register("constantan_boots", CONSTANTAN_BOOTS);

		//cupronickel tools and armor
		register("cupronickel_axe", CUPRONICKEL_AXE);
		register("cupronickel_hoe", CUPRONICKEL_HOE);
		register("cupronickel_pickaxe", CUPRONICKEL_PICKAXE);
		register("cupronickel_shovel", CUPRONICKEL_SHOVEL);
		register("cupronickel_sword", CUPRONICKEL_SWORD);
		register("cupronickel_helmet", CUPRONICKEL_HELMET);
		register("cupronickel_chestplate", CUPRONICKEL_CHESTPLATE);
		register("cupronickel_leggings", CUPRONICKEL_LEGGINGS);
		register("cupronickel_boots", CUPRONICKEL_BOOTS);

		//titanium tools and armor
		register("titanium_axe", TITANIUM_AXE);
		register("titanium_hoe", TITANIUM_HOE);
		register("titanium_pickaxe", TITANIUM_PICKAXE);
		register("titanium_shovel", TITANIUM_SHOVEL);
		register("titanium_sword", TITANIUM_SWORD);
		register("titanium_helmet", TITANIUM_HELMET);
		register("titanium_chestplate", TITANIUM_CHESTPLATE);
		register("titanium_leggings", TITANIUM_LEGGINGS);
		register("titanium_boots", TITANIUM_BOOTS);

		//titanium_gold tools and armor
		register("titanium_gold_axe", TITANIUM_GOLD_AXE);
		register("titanium_gold_hoe", TITANIUM_GOLD_HOE);
		register("titanium_gold_pickaxe", TITANIUM_GOLD_PICKAXE);
		register("titanium_gold_shovel", TITANIUM_GOLD_SHOVEL);
		register("titanium_gold_sword", TITANIUM_GOLD_SWORD);
		register("titanium_gold_helmet", TITANIUM_GOLD_HELMET);
		register("titanium_gold_chestplate", TITANIUM_GOLD_CHESTPLATE);
		register("titanium_gold_leggings", TITANIUM_GOLD_LEGGINGS);
		register("titanium_gold_boots", TITANIUM_GOLD_BOOTS);

		//nitinol tools and armor
		register("nitinol_axe", NITINOL_AXE);
		register("nitinol_hoe", NITINOL_HOE);
		register("nitinol_pickaxe", NITINOL_PICKAXE);
		register("nitinol_shovel", NITINOL_SHOVEL);
		register("nitinol_sword", NITINOL_SWORD);
		register("nitinol_helmet", NITINOL_HELMET);
		register("nitinol_chestplate", NITINOL_CHESTPLATE);
		register("nitinol_leggings", NITINOL_LEGGINGS);
		register("nitinol_boots", NITINOL_BOOTS);

		//crafting tools
		register("anvil_hammer", ANVIL_HAMMER);
		register("smithing_hammer", SMITHING_HAMMER);

		//copper parts
		register("copper_axe_head", COPPER_AXE_HEAD);
		register("copper_hoe_head", COPPER_HOE_HEAD);
		register("copper_pickaxe_head", COPPER_PICKAXE_HEAD);
		register("copper_shovel_head", COPPER_SHOVEL_HEAD);
		register("copper_sword_blade", COPPER_SWORD_BLADE);

		//bronze parts
		register("bronze_axe_head", BRONZE_AXE_HEAD);
		register("bronze_hoe_head", BRONZE_HOE_HEAD);
		register("bronze_pickaxe_head", BRONZE_PICKAXE_HEAD);
		register("bronze_shovel_head", BRONZE_SHOVEL_HEAD);
		register("bronze_sword_blade", BRONZE_SWORD_BLADE);

		//iron parts
		register("iron_axe_head", IRON_AXE_HEAD);
		register("iron_hoe_head", IRON_HOE_HEAD);
		register("iron_pickaxe_head", IRON_PICKAXE_HEAD);
		register("iron_shovel_head", IRON_SHOVEL_HEAD);
		register("iron_sword_blade", IRON_SWORD_BLADE);

		//gold parts
		register("gold_axe_head", GOLD_AXE_HEAD);
		register("gold_hoe_head", GOLD_HOE_HEAD);
		register("gold_pickaxe_head", GOLD_PICKAXE_HEAD);
		register("gold_shovel_head", GOLD_SHOVEL_HEAD);
		register("gold_sword_blade", GOLD_SWORD_BLADE);

		//antanium parts
		register("antanium_axe_head", ANTANIUM_AXE_HEAD);
		register("antanium_hoe_head", ANTANIUM_HOE_HEAD);
		register("antanium_pickaxe_head", ANTANIUM_PICKAXE_HEAD);
		register("antanium_shovel_head", ANTANIUM_SHOVEL_HEAD);
		register("antanium_sword_blade", ANTANIUM_SWORD_BLADE);

		//steel parts
		register("steel_axe_head", STEEL_AXE_HEAD);
		register("steel_hoe_head", STEEL_HOE_HEAD);
		register("steel_pickaxe_head", STEEL_PICKAXE_HEAD);
		register("steel_shovel_head", STEEL_SHOVEL_HEAD);
		register("steel_sword_blade", STEEL_SWORD_BLADE);

		//nickel parts
		register("nickel_axe_head", NICKEL_AXE_HEAD);
		register("nickel_hoe_head", NICKEL_HOE_HEAD);
		register("nickel_pickaxe_head", NICKEL_PICKAXE_HEAD);
		register("nickel_shovel_head", NICKEL_SHOVEL_HEAD);
		register("nickel_sword_blade", NICKEL_SWORD_BLADE);

		//invar parts
		register("invar_axe_head", INVAR_AXE_HEAD);
		register("invar_hoe_head", INVAR_HOE_HEAD);
		register("invar_pickaxe_head", INVAR_PICKAXE_HEAD);
		register("invar_shovel_head", INVAR_SHOVEL_HEAD);
		register("invar_sword_blade", INVAR_SWORD_BLADE);

		//constantan parts
		register("constantan_axe_head", CONSTANTAN_AXE_HEAD);
		register("constantan_hoe_head", CONSTANTAN_HOE_HEAD);
		register("constantan_pickaxe_head", CONSTANTAN_PICKAXE_HEAD);
		register("constantan_shovel_head", CONSTANTAN_SHOVEL_HEAD);
		register("constantan_sword_blade", CONSTANTAN_SWORD_BLADE);

		//cupronickel parts
		register("cupronickel_axe_head", CUPRONICKEL_AXE_HEAD);
		register("cupronickel_hoe_head", CUPRONICKEL_HOE_HEAD);
		register("cupronickel_pickaxe_head", CUPRONICKEL_PICKAXE_HEAD);
		register("cupronickel_shovel_head", CUPRONICKEL_SHOVEL_HEAD);
		register("cupronickel_sword_blade", CUPRONICKEL_SWORD_BLADE);

		//titanium parts
		register("titanium_axe_head", TITANIUM_AXE_HEAD);
		register("titanium_hoe_head", TITANIUM_HOE_HEAD);
		register("titanium_pickaxe_head", TITANIUM_PICKAXE_HEAD);
		register("titanium_shovel_head", TITANIUM_SHOVEL_HEAD);
		register("titanium_sword_blade", TITANIUM_SWORD_BLADE);

		//titanium_gold parts
		register("titanium_gold_axe_head", TITANIUM_GOLD_AXE_HEAD);
		register("titanium_gold_hoe_head", TITANIUM_GOLD_HOE_HEAD);
		register("titanium_gold_pickaxe_head", TITANIUM_GOLD_PICKAXE_HEAD);
		register("titanium_gold_shovel_head", TITANIUM_GOLD_SHOVEL_HEAD);
		register("titanium_gold_sword_blade", TITANIUM_GOLD_SWORD_BLADE);

		//nitinol parts
		register("nitinol_axe_head", NITINOL_AXE_HEAD);
		register("nitinol_hoe_head", NITINOL_HOE_HEAD);
		register("nitinol_pickaxe_head", NITINOL_PICKAXE_HEAD);
		register("nitinol_shovel_head", NITINOL_SHOVEL_HEAD);
		register("nitinol_sword_blade", NITINOL_SWORD_BLADE);

		if (AlloygeryConfig.ironGear.enable.getValue())
		{
			//iron tool overrides
			override(Items.IRON_AXE, IRON_AXE);
			override(Items.IRON_HOE, IRON_HOE);
			override(Items.IRON_PICKAXE, IRON_PICKAXE);
			override(Items.IRON_SHOVEL, IRON_SHOVEL);
			override(Items.IRON_SWORD, IRON_SWORD);

			//iron armor overrides
			override(Items.IRON_HELMET, IRON_HELMET);
			override(Items.IRON_CHESTPLATE, IRON_CHESTPLATE);
			override(Items.IRON_LEGGINGS, IRON_LEGGINGS);
			override(Items.IRON_BOOTS, IRON_BOOTS);
		}

		if (AlloygeryConfig.goldGear.enable.getValue())
		{
			//gold tool overrides
			override(Items.GOLDEN_AXE, GOLD_AXE);
			override(Items.GOLDEN_HOE, GOLD_HOE);
			override(Items.GOLDEN_PICKAXE, GOLD_PICKAXE);
			override(Items.GOLDEN_SHOVEL, GOLD_SHOVEL);
			override(Items.GOLDEN_SWORD, GOLD_SWORD);
		}

		//ineffective diamond gear overrides
		if (AlloygeryConfig.makeNetheriteGearIneffective.getValue())
		{
			override(Items.DIAMOND_AXE, new Item(new Item.Settings().group(ItemGroup.TOOLS)));
			override(Items.DIAMOND_HOE, new Item(new Item.Settings().group(ItemGroup.TOOLS)));
			override(Items.DIAMOND_PICKAXE, new Item(new Item.Settings().group(ItemGroup.TOOLS)));
			override(Items.DIAMOND_SHOVEL, new Item(new Item.Settings().group(ItemGroup.TOOLS)));
			override(Items.DIAMOND_SWORD, new Item(new Item.Settings().group(ItemGroup.COMBAT)));

			override(Items.DIAMOND_HELMET, new Item(new Item.Settings().group(ItemGroup.COMBAT)));
			override(Items.DIAMOND_CHESTPLATE, new Item(new Item.Settings().group(ItemGroup.COMBAT)));
			override(Items.DIAMOND_LEGGINGS, new Item(new Item.Settings().group(ItemGroup.COMBAT)));
			override(Items.DIAMOND_BOOTS, new Item(new Item.Settings().group(ItemGroup.COMBAT)));
		}

		//ineffective netherite gear overrides
		if (AlloygeryConfig.makeNetheriteGearIneffective.getValue())
		{
			override(Items.NETHERITE_AXE, new Item(new Item.Settings().group(ItemGroup.TOOLS)));
			override(Items.NETHERITE_HOE, new Item(new Item.Settings().group(ItemGroup.TOOLS)));
			override(Items.NETHERITE_PICKAXE, new Item(new Item.Settings().group(ItemGroup.TOOLS)));
			override(Items.NETHERITE_SHOVEL, new Item(new Item.Settings().group(ItemGroup.TOOLS)));
			override(Items.NETHERITE_SWORD, new Item(new Item.Settings().group(ItemGroup.COMBAT)));

			override(Items.NETHERITE_HELMET, new Item(new Item.Settings().group(ItemGroup.COMBAT)));
			override(Items.NETHERITE_CHESTPLATE, new Item(new Item.Settings().group(ItemGroup.COMBAT)));
			override(Items.NETHERITE_LEGGINGS, new Item(new Item.Settings().group(ItemGroup.COMBAT)));
			override(Items.NETHERITE_BOOTS, new Item(new Item.Settings().group(ItemGroup.COMBAT)));
		}
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
