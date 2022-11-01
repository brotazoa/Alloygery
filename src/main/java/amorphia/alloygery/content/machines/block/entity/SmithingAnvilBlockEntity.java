package amorphia.alloygery.content.machines.block.entity;

import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import amorphia.alloygery.content.machines.screen.SmithingAnvilScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SmithingAnvilBlockEntity extends LockableContainerBlockEntity
{
	protected DefaultedList<ItemStack> inventory;

	public SmithingAnvilBlockEntity(BlockPos blockPos, BlockState blockState)
	{
		super(MachineBlockRegistry.SMITHING_ANVIL_BLOCK_ENTITY, blockPos, blockState);
		this.inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
	}

	@Override
	protected Text getContainerName()
	{
		return Text.translatable("container.alloygery.smithing_anvil");
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory)
	{
		return new SmithingAnvilScreenHandler(syncId, playerInventory, this, ScreenHandlerContext.create(world, pos));
	}

	@Override
	public void readNbt(NbtCompound nbt)
	{
		super.readNbt(nbt);
		this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
		Inventories.readNbt(nbt, this.inventory);
		markDirty();
	}

	@Override
	protected void writeNbt(NbtCompound nbt)
	{
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, this.inventory);
	}

	@Nullable
	@Override
	public Packet<ClientPlayPacketListener> toUpdatePacket()
	{
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt()
	{
		return createNbt();
	}

	@Override
	public int size()
	{
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty()
	{
		return this.inventory.isEmpty();
	}

	@Override
	public ItemStack getStack(int slot)
	{
		return this.inventory.get(slot);
	}

	@Override
	public ItemStack removeStack(int slot, int amount)
	{
		return Inventories.splitStack(this.inventory, slot, amount);
	}

	@Override
	public ItemStack removeStack(int slot)
	{
		return Inventories.removeStack(this.inventory, slot);
	}

	@Override
	public void setStack(int slot, ItemStack stack)
	{
		this.inventory.set(slot, stack);
		if(stack.getCount() > this.getMaxCountPerStack())
			stack.setCount(this.getMaxCountPerStack());
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player)
	{
		return (this.world != null && this.world.getBlockEntity(this.pos) == this)
				&& player.squaredDistanceTo(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
	}

	@Override
	public void clear()
	{
		this.inventory.clear();
	}

	public DefaultedList<ItemStack> getInventory()
	{
		return inventory;
	}

	public void setInventory(DefaultedList<ItemStack> inventory)
	{
		this.inventory = inventory;
	}

	public void updateListeners()
	{
		this.world.updateListeners(this.pos, this.getCachedState(), this.world.getBlockState(this.pos), Block.NOTIFY_LISTENERS);
	}
}
