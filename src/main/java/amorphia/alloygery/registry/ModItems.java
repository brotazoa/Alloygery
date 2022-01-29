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
	public static ItemGroup ALLOYGERY_GROUP = FabricItemGroupBuilder.create(Alloygery.identifier("alloygery")).icon(() -> new ItemStack(ModItems.BRONZE_INGOT)).build();

	//ore block items
	public static final Item TIN_ORE = new BlockItem(ModBlocks.TIN_ORE, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item DEEPSLATE_TIN_ORE = new BlockItem(ModBlocks.DEEPSLATE_TIN_ORE, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_ORE = new BlockItem(ModBlocks.NICKLE_ORE, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_ORE = new BlockItem(ModBlocks.TITANIUM_ORE, new Item.Settings().group(ALLOYGERY_GROUP));

	//raw ore block items
	public static final Item RAW_TIN_BLOCK = new BlockItem(ModBlocks.RAW_TIN_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item RAW_NICKEL_BLOCK = new BlockItem(ModBlocks.RAW_NICKLE_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item RAW_TITANIUM_BLOCK = new BlockItem(ModBlocks.RAW_TITANIUM_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));

	//metal block items
	public static final Item TIN_BLOCK = new BlockItem(ModBlocks.TIN_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_BLOCK = new BlockItem(ModBlocks.BRONZE_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_BLOCK = new BlockItem(ModBlocks.ANTANIUM_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_BLOCK = new BlockItem(ModBlocks.STEEL_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_BLOCK = new BlockItem(ModBlocks.NICKLE_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_BLOCK = new BlockItem(ModBlocks.INVAR_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_BLOCK = new BlockItem(ModBlocks.CONSTANTAN_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICEL_BLOCK = new BlockItem(ModBlocks.CUPRONICKEL_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_BLOCK = new BlockItem(ModBlocks.TITANIUM_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_BLOCK = new BlockItem(ModBlocks.TITANIUM_GOLD_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_BLOCK = new BlockItem(ModBlocks.NITINOL_BLOCK, new Item.Settings().group(ALLOYGERY_GROUP));

	//raw ore items
	public static final Item RAW_TIN = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item RAW_NICKEL = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item RAW_TITANIUM = new Item(new Item.Settings().group(ALLOYGERY_GROUP));

	//ingot items
	public static final Item TIN_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICKEL_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_INGOT = new Item(new Item.Settings().group(ALLOYGERY_GROUP));

	//copper tools
	public static final Item COPPER_AXE = new ModAxeItem(ModToolMaterials.COPPER, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item COPPER_HOE = new ModHoeItem(ModToolMaterials.COPPER, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item COPPER_PICKAXE = new ModPickaxeItem(ModToolMaterials.COPPER, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item COPPER_SHOVEL = new ShovelItem(ModToolMaterials.COPPER, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item COPPER_SWORD = new ModSwordItem(ModToolMaterials.COPPER, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//bronze tools
	public static final Item BRONZE_AXE = new ModAxeItem(ModToolMaterials.BRONZE, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_HOE = new ModHoeItem(ModToolMaterials.BRONZE, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_PICKAXE = new ModPickaxeItem(ModToolMaterials.BRONZE, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_SHOVEL = new ShovelItem(ModToolMaterials.BRONZE, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_SWORD = new ModSwordItem(ModToolMaterials.BRONZE, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//iron tools
	public static final Item IRON_AXE = new ModAxeItem(ModToolMaterials.IRON, 5.0f, -3.0f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_HOE = new ModHoeItem(ModToolMaterials.IRON, -2, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_PICKAXE = new ModPickaxeItem(ModToolMaterials.IRON, 1, -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_SHOVEL = new ShovelItem(ModToolMaterials.IRON, 1.5f, -3.0f, new Item.Settings().group(ItemGroup.TOOLS));
	public static final Item IRON_SWORD = new ModSwordItem(ModToolMaterials.IRON, 3, -2.4f, new Item.Settings().group(ItemGroup.COMBAT));

	//antanium tools
	public static final Item ANTANIUM_AXE = new ModAxeItem(ModToolMaterials.ANTANIUM, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_HOE = new ModHoeItem(ModToolMaterials.ANTANIUM, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_PICKAXE = new ModPickaxeItem(ModToolMaterials.ANTANIUM, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_SHOVEL = new ShovelItem(ModToolMaterials.ANTANIUM, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_SWORD = new ModSwordItem(ModToolMaterials.ANTANIUM, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//steel tools
	public static final Item STEEL_AXE = new ModAxeItem(ModToolMaterials.STEEL, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_HOE = new ModHoeItem(ModToolMaterials.STEEL, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_PICKAXE = new ModPickaxeItem(ModToolMaterials.STEEL, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_SHOVEL = new ShovelItem(ModToolMaterials.STEEL, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_SWORD = new ModSwordItem(ModToolMaterials.STEEL, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//nickel tools
	public static final Item NICKEL_AXE = new ModAxeItem(ModToolMaterials.NICKEL, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_HOE = new ModHoeItem(ModToolMaterials.NICKEL, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_PICKAXE = new ModPickaxeItem(ModToolMaterials.NICKEL, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_SHOVEL = new ShovelItem(ModToolMaterials.NICKEL, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_SWORD = new ModSwordItem(ModToolMaterials.NICKEL, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//invar tools
	public static final Item INVAR_AXE = new ModAxeItem(ModToolMaterials.INVAR, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_HOE = new ModHoeItem(ModToolMaterials.INVAR, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_PICKAXE = new ModPickaxeItem(ModToolMaterials.INVAR, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_SHOVEL = new ShovelItem(ModToolMaterials.INVAR, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_SWORD = new ModSwordItem(ModToolMaterials.INVAR, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//constantan tools
	public static final Item CONSTANTAN_AXE = new ModAxeItem(ModToolMaterials.CONSTANTAN, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_HOE = new ModHoeItem(ModToolMaterials.CONSTANTAN, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_PICKAXE = new ModPickaxeItem(ModToolMaterials.CONSTANTAN, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_SHOVEL = new ShovelItem(ModToolMaterials.CONSTANTAN, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_SWORD = new ModSwordItem(ModToolMaterials.CONSTANTAN, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//cupronickel tools
	public static final Item CUPRONICKEL_AXE = new ModAxeItem(ModToolMaterials.CUPRONICKEL, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICKEL_HOE = new ModHoeItem(ModToolMaterials.CUPRONICKEL, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICKEL_PICKAXE = new ModPickaxeItem(ModToolMaterials.CUPRONICKEL, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICKEL_SHOVEL = new ShovelItem(ModToolMaterials.CUPRONICKEL, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICKEL_SWORD = new ModSwordItem(ModToolMaterials.CUPRONICKEL, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//titanium tools
	public static final Item TITANIUM_AXE = new ModAxeItem(ModToolMaterials.TITANIUM, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_HOE = new ModHoeItem(ModToolMaterials.TITANIUM, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_PICKAXE = new ModPickaxeItem(ModToolMaterials.TITANIUM, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_SHOVEL = new ShovelItem(ModToolMaterials.TITANIUM, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_SWORD = new ModSwordItem(ModToolMaterials.TITANIUM, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//titanium_gold tools
	public static final Item TITANIUM_GOLD_AXE = new ModAxeItem(ModToolMaterials.TITANIUM_GOLD, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_HOE = new ModHoeItem(ModToolMaterials.TITANIUM_GOLD, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_PICKAXE = new ModPickaxeItem(ModToolMaterials.TITANIUM_GOLD, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_SHOVEL = new ShovelItem(ModToolMaterials.TITANIUM_GOLD, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_SWORD = new ModSwordItem(ModToolMaterials.TITANIUM_GOLD, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//nitinol tools
	public static final Item NITINOL_AXE = new ModAxeItem(ModToolMaterials.NITINOL, 5.0f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_HOE = new ModHoeItem(ModToolMaterials.NITINOL, -2, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_PICKAXE = new ModPickaxeItem(ModToolMaterials.NITINOL, 1, -2.8f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_SHOVEL = new ShovelItem(ModToolMaterials.NITINOL, 1.5f, -3.0f, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_SWORD = new ModSwordItem(ModToolMaterials.NITINOL, 3, -2.4f, new Item.Settings().group(ALLOYGERY_GROUP));

	//copper armor
	public static final Item COPPER_HELMET = new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item COPPER_CHESTPLATE = new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item COPPER_LEGGINGS = new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item COPPER_BOOTS = new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//antanium armor
	public static final Item ANTANIUM_HELMET = new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_CHESTPLATE = new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_LEGGINGS = new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item ANTANIUM_BOOTS = new ArmorItem(ModArmorMaterials.ANTANIUM, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//bronze armor
	public static final Item BRONZE_HELMET = new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_CHESTPLATE = new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_LEGGINGS = new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BRONZE_BOOTS = new ArmorItem(ModArmorMaterials.BRONZE, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//iron armor overrides
	public static final Item IRON_HELMET = new ArmorItem(ModArmorMaterials.IRON, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item IRON_CHESTPLATE = new ArmorItem(ModArmorMaterials.IRON, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item IRON_LEGGINGS = new ArmorItem(ModArmorMaterials.IRON, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
	public static final Item IRON_BOOTS = new ArmorItem(ModArmorMaterials.IRON, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));

	//steel armor
	public static final Item STEEL_HELMET = new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_CHESTPLATE = new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_LEGGINGS = new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item STEEL_BOOTS = new ArmorItem(ModArmorMaterials.STEEL, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//nickel armor
	public static final Item NICKEL_HELMET = new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_CHESTPLATE = new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_LEGGINGS = new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NICKEL_BOOTS = new ArmorItem(ModArmorMaterials.NICKEL, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//invar armor
	public static final Item INVAR_HELMET = new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_CHESTPLATE = new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_LEGGINGS = new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item INVAR_BOOTS = new ArmorItem(ModArmorMaterials.INVAR, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//constantan armor
	public static final Item CONSTANTAN_HELMET = new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_CHESTPLATE = new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_LEGGINGS = new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CONSTANTAN_BOOTS = new ArmorItem(ModArmorMaterials.CONSTANTAN, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//cupronickel armor
	public static final Item CUPRONICKEL_HELMET = new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICKEL_CHESTPLATE = new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICKEL_LEGGINGS = new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item CUPRONICKEL_BOOTS = new ArmorItem(ModArmorMaterials.CUPRONICKEL, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//titanium armor
	public static final Item TITANIUM_HELMET = new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_CHESTPLATE = new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_LEGGINGS = new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_BOOTS = new ArmorItem(ModArmorMaterials.TITANIUM, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//titanium_gold armor
	public static final Item TITANIUM_GOLD_HELMET = new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_CHESTPLATE = new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_LEGGINGS = new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item TITANIUM_GOLD_BOOTS = new ArmorItem(ModArmorMaterials.TITANIUM_GOLD, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//nitinol armor
	public static final Item NITINOL_HELMET = new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.HEAD, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_CHESTPLATE = new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.CHEST, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_LEGGINGS = new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.LEGS, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item NITINOL_BOOTS = new ArmorItem(ModArmorMaterials.NITINOL, EquipmentSlot.FEET, new Item.Settings().group(ALLOYGERY_GROUP));

	//alloy kiln block items
	public static final Item ALLOY_KILN = new BlockItem(ModBlocks.ALLOY_KILN, new Item.Settings().group(ALLOYGERY_GROUP));
	public static final Item BLAST_ALLOY_KILN = new BlockItem(ModBlocks.BLAST_ALLOY_KILN, new Item.Settings().group(ALLOYGERY_GROUP));

	// @formatter:on

	public static void register()
	{
		//kilns
		register("alloy_kiln", ALLOY_KILN);
		register("blast_alloy_kiln", BLAST_ALLOY_KILN);

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

		//ingots
		register("tin_ingot", TIN_INGOT);
		register("bronze_ingot", BRONZE_INGOT);
		register("antanium_ingot", ANTANIUM_INGOT);
		register("steel_ingot", STEEL_INGOT);
		register("nickel_ingot", NICKEL_INGOT);
		register("invar_ingot", INVAR_INGOT);
		register("constantan_ingot", CONSTANTAN_INGOT);
		register("cupronickel_ingot", CUPRONICKEL_INGOT);
		register("titanium_ingot", TITANIUM_INGOT);
		register("titanium_gold_ingot", TITANIUM_GOLD_INGOT);
		register("nitinol_ingot", NITINOL_INGOT);

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

		//ineffective diamond gear overrides
		if (Alloygery.CONFIG != null && Alloygery.CONFIG.makeDiamondGearIneffective)
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
		if (Alloygery.CONFIG != null && Alloygery.CONFIG.makeNetheriteGearIneffective)
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
