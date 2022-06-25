package amorphia.alloygery.content.screen;

import amorphia.alloygery.content.item.BaseCraftingToolItem;
import amorphia.alloygery.registry.ModTags;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class SmithingAnvilHammerSlot extends Slot
{
	public SmithingAnvilHammerSlot(Inventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack)
	{
		return stack.isIn(ModTags.CRAFTING_TOOLS);
	}
}
