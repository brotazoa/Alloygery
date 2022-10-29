package amorphia.alloygery.content.machines.screen;

import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class AlloyKilnFuelSlot extends Slot
{
	public AlloyKilnFuelSlot(Inventory inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	@Override
	public boolean canInsert(ItemStack stack)
	{
		return AbstractFurnaceBlockEntity.canUseAsFuel(stack);
	}
}
