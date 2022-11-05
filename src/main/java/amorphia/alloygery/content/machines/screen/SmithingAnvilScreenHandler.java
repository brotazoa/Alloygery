package amorphia.alloygery.content.machines.screen;

import amorphia.alloygery.content.machines.block.entity.SmithingAnvilBlockEntity;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import amorphia.alloygery.content.machines.registry.MachineScreenRegistry;
import amorphia.alloygery.registry.ModTags;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class SmithingAnvilScreenHandler extends ScreenHandler
{
	private final ScreenHandlerContext context;
	private final Property selectedRecipe;
	private final World world;

	private List<SmithingAnvilRecipe> availableRecipes;
	private ItemStack hammerStack;
	private ItemStack materialStack;

	Runnable contentsChangedListener;
	long lastTakeTime;
	final Slot hammerSlot;
	final Slot materialSlot;
	final Slot outputSlot;

	public final Inventory input;
	final CraftingResultInventory output;

	public SmithingAnvilScreenHandler(int syncId, PlayerInventory playerInventory)
	{
		this(syncId, playerInventory, new SimpleInventory(2), ScreenHandlerContext.EMPTY);
	}

	public SmithingAnvilScreenHandler(int syncId, PlayerInventory playerInventory, Inventory blockInventory, ScreenHandlerContext context)
	{
		super(MachineScreenRegistry.SMITHING_ANVIL_SCREEN_HANDLER_TYPE, syncId);

		checkSize(blockInventory, 2);

		this.context = context;
		this.world = playerInventory.player.world;
		this.selectedRecipe = Property.create();
		this.selectedRecipe.set(-1);

		this.availableRecipes = Lists.newArrayList();
		this.hammerStack = blockInventory.getStack(0).copy();
		this.materialStack = blockInventory.getStack(1).copy();
		this.contentsChangedListener = () -> {};
		this.input = blockInventory;
		this.output = new CraftingResultInventory();

		input.onOpen(playerInventory.player);

		this.hammerSlot = this.addSlot(new Slot(SmithingAnvilScreenHandler.this.input, 0, 20, 15){
			@Override
			public void markDirty()
			{
				SmithingAnvilScreenHandler.this.onHammerSlotChanged(SmithingAnvilScreenHandler.this.input);
				super.markDirty();
			}
		});
		this.materialSlot = this.addSlot(new Slot(SmithingAnvilScreenHandler.this.input, 1, 20, 53){
			@Override
			public void markDirty()
			{
				SmithingAnvilScreenHandler.this.onMaterialSlotChanged(SmithingAnvilScreenHandler.this.input);
				super.markDirty();
			}
		});
		this.outputSlot = this.addSlot(new Slot(this.output, 2, 143, 33) {
			@Override
			public boolean canInsert(ItemStack stack)
			{
				return false;
			}

			@Override
			public void onTakeItem(PlayerEntity player, ItemStack stack)
			{
				stack.onCraft(player.world, player, stack.getCount());
				SmithingAnvilScreenHandler.this.output.unlockLastRecipe(player);

				if(isInBounds(getSelectedRecipe()) && getAvailableRecipes().get(getSelectedRecipe()) != null)
					getAvailableRecipes().get(getSelectedRecipe()).onCraft(SmithingAnvilScreenHandler.this.input);

				SmithingAnvilScreenHandler.this.onHammerSlotChanged(SmithingAnvilScreenHandler.this.input);
				SmithingAnvilScreenHandler.this.onMaterialSlotChanged(SmithingAnvilScreenHandler.this.input);

				SmithingAnvilScreenHandler.this.updateResult();

				SmithingAnvilScreenHandler.this.context.run((world1, pos) -> {
					long l = world1.getTime();
					if(SmithingAnvilScreenHandler.this.lastTakeTime != l)
					{
						world1.playSound(null, pos, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
						SmithingAnvilScreenHandler.this.lastTakeTime = l;
					}
				});

				super.onTakeItem(player, stack);
			}
		});

		int i;
		for(i = 0; i < 3; ++i) {
			for(int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for(i = 0; i < 9; ++i) {
			this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
		}

		this.addProperty(this.selectedRecipe);

		this.updateInput(this.input);
	}

	public int getSelectedRecipe()
	{
		return this.selectedRecipe.get();
	}

	public List<SmithingAnvilRecipe> getAvailableRecipes()
	{
		return this.availableRecipes;
	}

	public int getAvailableRecipeCount()
	{
		return this.availableRecipes.size();
	}

	public boolean suitableCraftingTier()
	{
		return suitableCraftingTier(getSelectedRecipe());
	}

	public boolean suitableCraftingTier(int recipeIndex)
	{
		if(!isInBounds(recipeIndex))
			return false;

		return getAvailableRecipes().get(recipeIndex).suitableCraftingTier(this.input, this.world);
	}

	public boolean canAffordRecipe()
	{
		return canAffordRecipe(getSelectedRecipe());
	}

	public boolean canAffordRecipe(int recipeIndex)
	{
		if(!isInBounds(recipeIndex))
			return false;

		return getAvailableRecipes().get(recipeIndex).canAfford(this.input, this.world);
	}

	public boolean canCraft()
	{
		return !this.availableRecipes.isEmpty();
	}

	@Override
	public boolean canUse(PlayerEntity player)
	{
		return canUse(this.context, player, MachineBlockRegistry.SMITHING_ANVIL);
	}

	@Override
	public boolean onButtonClick(PlayerEntity player, int id)
	{
		if(this.isInBounds(id))
		{
			this.selectedRecipe.set(id);
			this.updateResult();
		}
		return true;
	}

	private boolean isInBounds(int id)
	{
		return id >= 0 && id < this.availableRecipes.size();
	}

	public void setContentsChangedListener(Runnable listener)
	{
		this.contentsChangedListener = listener;
	}

	public void onHammerSlotChanged(Inventory inventory)
	{
		ItemStack toolStack = this.hammerSlot.getStack();
		if(!toolStack.isOf(this.hammerStack.getItem()))
		{
			this.hammerStack = toolStack.copy();
			this.updateInput(inventory);
		}
	}

	public void onMaterialSlotChanged(Inventory inventory)
	{
		ItemStack materialStack = this.materialSlot.getStack();
		if (!materialStack.isOf(this.materialStack.getItem()))
		{
			this.materialStack = materialStack.copy();
			this.updateInput(inventory);
		}
	}

	void updateInput(Inventory inventory)
	{
		this.availableRecipes.clear();
		this.selectedRecipe.set(-1);
		this.outputSlot.setStack(ItemStack.EMPTY);
		this.contentsChangedListener.run();

		this.context.run((world1, blockPos) -> {
			if(Objects.requireNonNull(world1.getBlockEntity(blockPos)) instanceof SmithingAnvilBlockEntity smithingAnvilBlockEntity)
				smithingAnvilBlockEntity.updateListeners();
		});

		if(!inventory.isEmpty())
			this.availableRecipes = this.world.getRecipeManager().getAllMatches(SmithingAnvilRecipe.Type.INSTANCE, inventory, this.world);

		this.sendContentUpdates();
	}

	void updateResult()
	{
		if(!this.availableRecipes.isEmpty() && this.isInBounds(getSelectedRecipe()) && canAffordRecipe() && suitableCraftingTier())
		{
			SmithingAnvilRecipe recipe = this.availableRecipes.get(getSelectedRecipe());
			this.output.setLastRecipe(recipe);
			this.outputSlot.setStack(recipe.craft(this.input));
		}
		else
		{
			this.outputSlot.setStack(ItemStack.EMPTY);

		}

		this.sendContentUpdates();
	}

	@Override
	public boolean canInsertIntoSlot(Slot slot)
	{
		return slot.inventory != this.output && super.canInsertIntoSlot(slot);
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int index)
	{
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasStack())
		{
			ItemStack slotStack = slot.getStack();
			Item slotItem = slotStack.getItem();
			newStack = slotStack.copy();
			if (index == 2) // output slot to inventory
			{
				slotItem.onCraft(slotStack, player.world, player);
				if (!this.insertItem(slotStack, 3, slots.size(), true))
				{
					return ItemStack.EMPTY;
				}
				slot.onQuickTransfer(slotStack, newStack);
			}
			else if (index <= 1) // hammer slot or material slot to inventory
			{
				if (!this.insertItem(slotStack, 3, slots.size(), false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (slotStack.isIn(ModTags.CRAFTING_TOOLS)) // inventory to hammer slot
			{
				if (!this.insertItem(slotStack, 0, 1, false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.insertItem(slotStack, 1, 2, false)) // inventory to material slot
			{
				return ItemStack.EMPTY;
			}
			else if (index < this.slots.size() - 1) // inventory to hot bar
			{
				if (!this.insertItem(slotStack, this.slots.size() - 9, this.slots.size(), false))
				{
					return ItemStack.EMPTY;
				}
			}
			else if (!this.insertItem(slotStack, 3, this.slots.size() - 9, false)) // hot bar to inventory
			{
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty())
			{
				slot.setStack(ItemStack.EMPTY);
			}

			slot.markDirty();
			if (slotStack.getCount() == newStack.getCount())
			{
				return ItemStack.EMPTY;
			}

			slot.onTakeItem(player, slotStack);
			this.sendContentUpdates();
		}

		return newStack;
	}

	@Override
	public void close(PlayerEntity player)
	{
		super.close(player);
		this.input.onClose(player);
		this.output.removeStack(2);
		this.updateInput(this.input);
	}
}
