package amorphia.alloygery.content.armor.item;

import amorphia.alloygery.content.armor.ArmorMaterialHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class DyeableDynamicArmorItem extends DynamicArmorItem implements IDyeableItemWithDefaultColor
{
	public DyeableDynamicArmorItem(ArmorType type)
	{
		this(type, new Settings());
	}

	public DyeableDynamicArmorItem(ArmorType type, Settings settings)
	{
		super(type, settings);
	}

	@Override
	public void setColor(ItemStack stack, int color)
	{
		IDyeableItemWithDefaultColor.super.setColor(stack, color);
		ArmorMaterialHelper.setLayerColor(stack, ArmorLayer.BASE, color);
	}

	@Override
	public void removeColor(ItemStack stack)
	{
		IDyeableItemWithDefaultColor.super.removeColor(stack);
		ArmorMaterialHelper.removeLayerColor(stack, ArmorLayer.BASE);
	}

	@Override
	public int getDefaultColor(ItemStack stack)
	{
		return getDefaultBaseMaterial().getMaterialColor();
	}
}
