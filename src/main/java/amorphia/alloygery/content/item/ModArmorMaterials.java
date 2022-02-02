package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.registry.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public enum ModArmorMaterials implements ArmorMaterial
{
	COPPER("copper", AlloygeryConfig.copperGear, Ingredient.ofItems(Items.COPPER_INGOT)),
	BRONZE("bronze", AlloygeryConfig.bronzeGear, Ingredient.ofItems(ModItems.BRONZE_INGOT)),
	IRON("iron", AlloygeryConfig.ironGear, Ingredient.ofItems(Items.IRON_INGOT)),
	ANTANIUM("antanium", AlloygeryConfig.antaniumGear, Ingredient.ofItems(ModItems.ANTANIUM_INGOT)),
	STEEL("steel", AlloygeryConfig.steelGear, Ingredient.ofItems(ModItems.STEEL_INGOT)),
	NICKEL("nickel", AlloygeryConfig.nickelGear, Ingredient.ofItems(ModItems.NICKEL_INGOT)),
	INVAR("invar", AlloygeryConfig.invarGear, Ingredient.ofItems(ModItems.INVAR_INGOT)),
	CONSTANTAN("constantan", AlloygeryConfig.constantanGear, Ingredient.ofItems(ModItems.CONSTANTAN_INGOT)),
	CUPRONICKEL("cupronickel", AlloygeryConfig.cupronickelGear, Ingredient.ofItems(ModItems.CUPRONICKEL_INGOT)),
	TITANIUM("titanium", AlloygeryConfig.titaniumGear, Ingredient.ofItems(ModItems.TITANIUM_INGOT)),
	TITANIUM_GOLD("titanium_gold", AlloygeryConfig.titaniumGoldGear, Ingredient.ofItems(ModItems.TITANIUM_GOLD_INGOT)),
	NITINOL("nitinol", AlloygeryConfig.nitinolGear, Ingredient.ofItems(ModItems.NITINOL_INGOT)),
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

	ModArmorMaterials(String name, AlloygeryConfig.GearStatsConfigGroup config, Ingredient repairIngredient)
	{
		this.name = name;
		this.durabilityMultiplier = config.durabilityMultiplier.getValue();
		this.protectionAmounts = new int[]{
				config.bootsArmor.getValue(),
				config.leggingsArmor.getValue(),
				config.chestplateArmor.getValue(),
				config.helmetArmor.getValue()
		};
		this.enchantability = config.enchantability.getValue();
		this.equipSound = SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
		this.toughness = config.toughness.getValue();
		this.knockbackResistance = config.knockback.getValue();
		this.repairIngredient = repairIngredient;
	}

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
