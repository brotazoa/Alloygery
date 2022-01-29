package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.registry.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum ModArmorMaterials implements ArmorMaterial
{
	COPPER("copper", Alloygery.CONFIG.copperGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.copperGear.bootsArmor, Alloygery.CONFIG.copperGear.leggingsArmor, Alloygery.CONFIG.copperGear.chestplateArmor, Alloygery.CONFIG.copperGear.helmetArmor }, Alloygery.CONFIG.copperGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.copperGear.toughness, Alloygery.CONFIG.copperGear.knockback, Ingredient.ofItems(Items.COPPER_INGOT)),
	BRONZE("bronze", Alloygery.CONFIG.bronzeGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.bronzeGear.bootsArmor, Alloygery.CONFIG.bronzeGear.leggingsArmor, Alloygery.CONFIG.bronzeGear.chestplateArmor, Alloygery.CONFIG.bronzeGear.helmetArmor }, Alloygery.CONFIG.bronzeGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.bronzeGear.toughness, Alloygery.CONFIG.bronzeGear.knockback, Ingredient.ofItems(ModItems.BRONZE_INGOT)),
	IRON("iron", Alloygery.CONFIG.ironGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.ironGear.bootsArmor, Alloygery.CONFIG.ironGear.leggingsArmor, Alloygery.CONFIG.ironGear.chestplateArmor, Alloygery.CONFIG.ironGear.helmetArmor }, Alloygery.CONFIG.ironGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.ironGear.toughness, Alloygery.CONFIG.ironGear.knockback, Ingredient.ofItems(Items.IRON_INGOT)),
	ANTANIUM("antanium", Alloygery.CONFIG.antaniumGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.antaniumGear.bootsArmor, Alloygery.CONFIG.antaniumGear.leggingsArmor, Alloygery.CONFIG.antaniumGear.chestplateArmor, Alloygery.CONFIG.antaniumGear.helmetArmor }, Alloygery.CONFIG.antaniumGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.antaniumGear.toughness, Alloygery.CONFIG.antaniumGear.knockback, Ingredient.ofItems(ModItems.ANTANIUM_INGOT)),
	STEEL("steel", Alloygery.CONFIG.steelGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.steelGear.bootsArmor, Alloygery.CONFIG.steelGear.leggingsArmor, Alloygery.CONFIG.steelGear.chestplateArmor, Alloygery.CONFIG.steelGear.helmetArmor }, Alloygery.CONFIG.steelGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.steelGear.toughness, Alloygery.CONFIG.steelGear.knockback, Ingredient.ofItems(ModItems.STEEL_INGOT)),
	NICKEL("nickel", Alloygery.CONFIG.nickelGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.nickelGear.bootsArmor, Alloygery.CONFIG.nickelGear.leggingsArmor, Alloygery.CONFIG.nickelGear.chestplateArmor, Alloygery.CONFIG.nickelGear.helmetArmor }, Alloygery.CONFIG.nickelGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.nickelGear.toughness, Alloygery.CONFIG.nickelGear.knockback, Ingredient.ofItems(ModItems.NICKEL_INGOT)),
	INVAR("invar", Alloygery.CONFIG.invarGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.invarGear.bootsArmor, Alloygery.CONFIG.invarGear.leggingsArmor, Alloygery.CONFIG.invarGear.chestplateArmor, Alloygery.CONFIG.invarGear.helmetArmor }, Alloygery.CONFIG.invarGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.invarGear.toughness, Alloygery.CONFIG.invarGear.knockback, Ingredient.ofItems(ModItems.INVAR_INGOT)),
	CONSTANTAN("constantan", Alloygery.CONFIG.constantanGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.constantanGear.bootsArmor, Alloygery.CONFIG.constantanGear.leggingsArmor, Alloygery.CONFIG.constantanGear.chestplateArmor, Alloygery.CONFIG.constantanGear.helmetArmor }, Alloygery.CONFIG.constantanGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.constantanGear.toughness, Alloygery.CONFIG.constantanGear.knockback, Ingredient.ofItems(ModItems.CONSTANTAN_INGOT)),
	CUPRONICKEL("cupronickel", Alloygery.CONFIG.cupronickelGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.cupronickelGear.bootsArmor, Alloygery.CONFIG.cupronickelGear.leggingsArmor, Alloygery.CONFIG.cupronickelGear.chestplateArmor, Alloygery.CONFIG.cupronickelGear.helmetArmor }, Alloygery.CONFIG.cupronickelGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.cupronickelGear.toughness, Alloygery.CONFIG.cupronickelGear.knockback, Ingredient.ofItems(ModItems.CUPRONICKEL_INGOT)),
	TITANIUM("titanium", Alloygery.CONFIG.titaniumGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.titaniumGear.bootsArmor, Alloygery.CONFIG.titaniumGear.leggingsArmor, Alloygery.CONFIG.titaniumGear.chestplateArmor, Alloygery.CONFIG.titaniumGear.helmetArmor }, Alloygery.CONFIG.titaniumGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.titaniumGear.toughness, Alloygery.CONFIG.titaniumGear.knockback, Ingredient.ofItems(ModItems.TITANIUM_INGOT)),
	TITANIUM_GOLD("titanium_gold", Alloygery.CONFIG.titaniumGoldGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.titaniumGoldGear.bootsArmor, Alloygery.CONFIG.titaniumGoldGear.leggingsArmor, Alloygery.CONFIG.titaniumGoldGear.chestplateArmor, Alloygery.CONFIG.titaniumGoldGear.helmetArmor }, Alloygery.CONFIG.titaniumGoldGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.titaniumGoldGear.toughness, Alloygery.CONFIG.titaniumGoldGear.knockback, Ingredient.ofItems(ModItems.TITANIUM_GOLD_INGOT)),
	NITINOL("nitinol", Alloygery.CONFIG.nitinolGear.durabilityMultiplier, new int[] { Alloygery.CONFIG.nitinolGear.bootsArmor, Alloygery.CONFIG.nitinolGear.leggingsArmor, Alloygery.CONFIG.nitinolGear.chestplateArmor, Alloygery.CONFIG.nitinolGear.helmetArmor }, Alloygery.CONFIG.nitinolGear.enchantability, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, Alloygery.CONFIG.nitinolGear.toughness, Alloygery.CONFIG.nitinolGear.knockback, Ingredient.ofItems(ModItems.NITINOL_INGOT)),
	;

	private static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 }; //boots, pants, tunic, cap

	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Ingredient repairIngredient;

	ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness,
			float knockbackResistance, Ingredient repairIngredient)
	{
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.protectionAmounts = protectionAmounts;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient;
	}

	@Override
	public int getDurability(EquipmentSlot slot)
	{
		return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot)
	{
		return this.protectionAmounts[slot.getEntitySlotId()];
	}

	@Override
	public int getEnchantability()
	{
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound()
	{
		return this.equipSound;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return this.repairIngredient;
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public float getToughness()
	{
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance()
	{
		return this.knockbackResistance;
	}
}
