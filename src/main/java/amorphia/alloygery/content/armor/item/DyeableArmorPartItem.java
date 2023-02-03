package amorphia.alloygery.content.armor.item;

import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import net.minecraft.item.ItemStack;

public class DyeableArmorPartItem extends ArmorPartItem implements IDyeableItemWithDefaultColor
{
	public DyeableArmorPartItem(Settings settings, AlloygeryArmorMaterial armorMaterial, ArmorLayer layer, ArmorType type)
	{
		super(settings, armorMaterial, layer, type);
	}

	@Override
	public int getDefaultColor(ItemStack stack)
	{
		return getArmorMaterial().getMaterialColor();
	}
}
