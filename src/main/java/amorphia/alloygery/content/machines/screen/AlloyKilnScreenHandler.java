package amorphia.alloygery.content.machines.screen;

import amorphia.alloygery.content.machines.registry.MachineScreenRegistry;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class AlloyKilnScreenHandler extends ScreenHandler
{
	private final Inventory kilnInventory;
	private final PropertyDelegate propertyDelegate;

	public AlloyKilnScreenHandler(int syncId, PlayerInventory playerInventory)
	{
		this(syncId, playerInventory, new SimpleInventory(6), new ArrayPropertyDelegate(4));
	}

	public AlloyKilnScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate)
	{
		super(MachineScreenRegistry.ALLOY_KILN_SCREEN_HANDLER_TYPE, syncId);
		this.kilnInventory = inventory;
		this.propertyDelegate = propertyDelegate;
		this.addProperties(propertyDelegate);

		checkDataCount(propertyDelegate, 4);
		checkSize(inventory, 6);

		inventory.onOpen(playerInventory.player);

		//input slots
		for (int inputRow = 0; inputRow < 2; inputRow++)
		{
			for (int inputColumn = 0; inputColumn < 2; inputColumn++)
			{
				this.addSlot(new Slot(inventory, inputColumn + inputRow * 2, 38 + inputColumn * 18, 17 + inputRow * 18));
			}
		}

		//fuel slot
		this.addSlot(new AlloyKilnFuelSlot(inventory, 4, 47, 71));
		//result slot
		this.addSlot(new AlloyKilnOutputSlot(inventory, playerInventory.player, 5, 118, 26));

		//player inventory
		for (int playerRow = 0; playerRow < 3; playerRow++)
		{
			for (int playerColumn = 0; playerColumn < 9; playerColumn++)
			{
				this.addSlot(new Slot(playerInventory, playerColumn + playerRow * 9 + 9, 8 + playerColumn * 18, 102 + playerRow * 18));
			}
		}

		//player hot bar
		for(int barColumn = 0; barColumn < 9; barColumn++)
		{
			this.addSlot(new Slot(playerInventory, barColumn, 8 + barColumn * 18, 160));
		}
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int index)
	{
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasStack())
		{
			ItemStack slotStack = slot.getStack();
			itemStack = slotStack.copy();

			if (index == 5)
			{
				if (!this.insertItem(slotStack, 6, 42, true))
				{
					return ItemStack.EMPTY;
				}
				slot.onQuickTransfer(slotStack, itemStack);
			}
			else if (index > 5)
			{
				if (AbstractFurnaceBlockEntity.canUseAsFuel(slotStack))
				{
					if (!this.insertItem(slotStack, 4, 5, false))
					{
						return ItemStack.EMPTY;
					}
				}
				else
				{
					if (!this.insertItem(slotStack, 0, 4, false))
					{
						return ItemStack.EMPTY;
					}
				}
			}
			else
			{
				if (!this.insertItem(slotStack, 6, 42, false))
				{
					return ItemStack.EMPTY;
				}
			}

			if (slotStack.isEmpty())
			{
				slot.setStack(ItemStack.EMPTY);
			}
			else
			{
				slot.markDirty();
			}

			if (slotStack.getCount() == itemStack.getCount())
			{
				return ItemStack.EMPTY;
			}

			slot.onTakeItem(player, slotStack);
		}
		return itemStack;
	}

	@Override
	public boolean canUse(PlayerEntity player)
	{
		return this.kilnInventory.canPlayerUse(player);
	}

	public int litTime()
	{
		return this.propertyDelegate.get(1) == 0 ? 0 : this.propertyDelegate.get(0) * 13 / this.propertyDelegate.get(1);
	}

	public int smeltingTime()
	{
		int i = this.propertyDelegate.get(2);
		int j = this.propertyDelegate.get(3);
		return j != 0 && i != 0 ? i * 24 / j : 0;
	}
}
