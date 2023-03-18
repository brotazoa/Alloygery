package amorphia.alloygery.content.machines.screen;

import amorphia.alloygery.content.machines.block.entity.AbstractAlloyKilnBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;

public class AlloyKilnOutputSlot extends Slot
{
	private final PlayerEntity playerEntity;

	public AlloyKilnOutputSlot(Inventory inventory, PlayerEntity playerEntity, int index, int x, int y)
	{
		super(inventory, index, x, y);
		this.playerEntity = playerEntity;
	}

	@Override
	public boolean canInsert(ItemStack stack)
	{
		return false;
	}

	@Override
	public void onTakeItem(PlayerEntity player, ItemStack stack)
	{
		super.onTakeItem(player, stack);
	}

	@Override
	protected void onCrafted(ItemStack stack)
	{
		super.onCrafted(stack);
		if (this.playerEntity instanceof ServerPlayerEntity serverPlayer && this.inventory instanceof AbstractAlloyKilnBlockEntity blockEntity)
		{
			blockEntity.dropExperience(serverPlayer, serverPlayer.getWorld(), serverPlayer.getTrackedPosition());
		}
	}

	@Override
	public void onQuickTransfer(ItemStack newItem, ItemStack original)
	{
		super.onQuickTransfer(newItem, original);
		this.onCrafted(newItem);
	}
}
