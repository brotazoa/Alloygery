package amorphia.alloygery.content.item;

import net.minecraft.item.Item;

public class BaseCraftingToolItem extends Item
{
	public BaseCraftingToolItem(Settings settings)
	{
		super(settings);
	}

	@Override
	public boolean isDamageable()
	{
		return true;
	}
}
