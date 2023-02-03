package amorphia.alloygery.content.armor.item;

import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;

public interface IDyeableItemWithDefaultColor extends DyeableItem
{
	@Override
	default int getColor(ItemStack stack)
	{
		return DyeableItem.super.hasColor(stack) ? DyeableItem.super.getColor(stack) : IDyeableItemWithDefaultColor.this.getDefaultColor(stack);
	}

	default int getDefaultColor(ItemStack stack)
	{
		return AlloygeryArmorMaterials.UNKNOWN.getMaterialColor();
	}
}
