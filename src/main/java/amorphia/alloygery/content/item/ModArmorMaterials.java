package amorphia.alloygery.content.item;

import amorphia.alloygery.registry.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum ModArmorMaterials implements ArmorMaterial
{
	COPPER("copper", 10, new int[] { 1, 3, 4, 1 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, 0.0f, Ingredient.ofItems(Items.COPPER_INGOT)),
	BRONZE("bronze", 15, new int[] { 2, 5, 6, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, 0.0f, Ingredient.ofItems(ModItems.BRONZE_INGOT)),
	ANTANIUM("antanium", 20, new int[] { 2, 4, 5, 2 }, 25, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, 0.0f, Ingredient.ofItems(ModItems.ANTANIUM_INGOT)),
	STEEL("steel", 33, new int[] { 3, 6, 8, 3 }, 10, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0f, 0.0f, Ingredient.ofItems(ModItems.STEEL_INGOT)),
	NICKEL("nickel", 37, new int[] { 3, 6, 8, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0f, 0.1f, Ingredient.ofItems(ModItems.NICKEL_INGOT)),
	INVAR("invar", 50, new int[] { 3, 7, 9, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0f, 0.5f, Ingredient.ofItems(ModItems.INVAR_INGOT)),
	CONSTANTAN("constantan", 40, new int[] { 3, 6, 8, 3 }, 18, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0f, 0.0f, Ingredient.ofItems(ModItems.CONSTANTAN_INGOT)),
	CUPRONICKEL("cupronickel", 45, new int[] { 3, 7, 9, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 2.0f, 0.0f, Ingredient.ofItems(ModItems.CUPRONICKEL_INGOT)),
	TITANIUM("titanium", 53, new int[] { 4, 7, 9, 4 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0f, 0.0f, Ingredient.ofItems(ModItems.TITANIUM_INGOT)),
	TITANIUM_GOLD("titanium_gold", 57, new int[] { 4, 7, 9, 4 }, 20, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0f, 0.0f, Ingredient.ofItems(ModItems.TITANIUM_GOLD_INGOT)),
	NITINOL("nitinol", 62, new int[] { 4, 7, 9, 4 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 3.0f, 0.1f, Ingredient.ofItems(ModItems.NITINOL_INGOT)),
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
