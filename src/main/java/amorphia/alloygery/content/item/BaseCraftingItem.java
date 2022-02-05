package amorphia.alloygery.content.item;

import net.minecraft.item.Item;

public class BaseCraftingItem extends Item
{
	public BaseCraftingItem(Settings settings)
	{
		super(settings);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}
}
