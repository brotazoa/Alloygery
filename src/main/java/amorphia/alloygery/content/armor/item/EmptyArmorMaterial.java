package amorphia.alloygery.content.armor.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class EmptyArmorMaterial implements ArmorMaterial
{
	public static final EmptyArmorMaterial INSTANCE = new EmptyArmorMaterial();

	private EmptyArmorMaterial() {} //no op

	@Override
	public int getDurability(EquipmentSlot slot)
	{
		return 1;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot)
	{
		return 0;
	}

	@Override
	public int getEnchantability()
	{
		return 0;
	}

	@Override
	public SoundEvent getEquipSound()
	{
		return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return Ingredient.EMPTY;
	}

	@Override
	public String getName()
	{
		return "empty";
	}

	@Override
	public float getToughness()
	{
		return 0;
	}

	@Override
	public float getKnockbackResistance()
	{
		return 0;
	}
}
