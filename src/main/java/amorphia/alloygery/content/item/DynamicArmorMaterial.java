package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class DynamicArmorMaterial implements ArmorMaterial
{
	private static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 }; //boots, pants, tunic, cap

	private final AlloygeryMaterial alloygeryMaterial;

	public DynamicArmorMaterial(AlloygeryMaterial material)
	{
		this.alloygeryMaterial = material;
	}

	@Override
	public int getDurability(EquipmentSlot slot)
	{
		return BASE_DURABILITY[slot.getEntitySlotId()] * alloygeryMaterial.armor.durability;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot)
	{
		return switch (slot)
		{
			case HEAD -> alloygeryMaterial.armor.helmet;
			case CHEST -> alloygeryMaterial.armor.chestplate;
			case LEGS -> alloygeryMaterial.armor.leggings;
			case FEET -> alloygeryMaterial.armor.boots;
			default -> 0;
		};
	}

	@Override
	public int getEnchantability()
	{
		return alloygeryMaterial.armor.enchantability;
	}

	@Override
	public SoundEvent getEquipSound()
	{
		return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return AlloygeryToolMaterialHelper.getRepairIngredient(alloygeryMaterial);
	}

	@Override
	public String getName()
	{
		return alloygeryMaterial.name;
	}

	@Override
	public float getToughness()
	{
		return alloygeryMaterial.armor.toughness;
	}

	@Override
	public float getKnockbackResistance()
	{
		return alloygeryMaterial.armor.knockback_resistance;
	}
}
