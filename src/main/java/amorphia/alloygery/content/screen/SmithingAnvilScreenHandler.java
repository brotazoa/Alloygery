package amorphia.alloygery.content.screen;

import amorphia.alloygery.content.item.BaseCraftingToolItem;
import amorphia.alloygery.content.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.registry.ModBlocks;
import amorphia.alloygery.registry.ModScreens;
import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.List;

public class SmithingAnvilScreenHandler extends ScreenHandler
{
	private final ScreenHandlerContext context;
	private final Property selectedRecipe;
	private final World world;

	private List<SmithingAnvilRecipe> availableRecipes;
	private ItemStack hammerStack;
	private ItemStack materialStack;
	private int materialCost;

	Runnable contentsChangedListener;
	long lastTakeTime;
	final Slot hammerSlot;
	final Slot materialSlot;
	final Slot outputSlot;

	public final Inventory input;
	final CraftingResultInventory output;

	public SmithingAnvilScreenHandler(int syncId, PlayerInventory playerInventory)
	{
		this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
	}

	public SmithingAnvilScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
	{
		super(ModScreens.SMITHING_ANVIL_SCREEN_HANDLER_TYPE, syncId);
		this.context = context;
		this.world = playerInventory.player.world;
		this.selectedRecipe = Property.create();
		this.availableRecipes = Lists.newArrayList();
		this.hammerStack = ItemStack.EMPTY;
		this.materialStack = ItemStack.EMPTY;
		this.materialCost = 1;
		this.contentsChangedListener = () -> {};

		this.input = new SimpleInventory(2){
			@Override
			public void markDirty()
			{
				SmithingAnvilScreenHandler.this.onContentChanged(input);
				super.markDirty();
			}
		};
		this.output = new CraftingResultInventory();

		this.hammerSlot = this.addSlot(new SmithingAnvilHammerSlot(this.input, 0, 20, 15));
		this.materialSlot = this.addSlot(new Slot(this.input, 1, 20, 53));
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

				SmithingAnvilScreenHandler.this.hammerSlot.getStack().damage(2, player.getRandom(), player instanceof ServerPlayerEntity  serverPlayer ? serverPlayer : null);
				SmithingAnvilScreenHandler.this.materialSlot.takeStack(SmithingAnvilScreenHandler.this.materialCost);

				context.run((world1, pos) -> {
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
		return canUse(this.context, player, ModBlocks.SMITHING_ANVIL);
	}

	@Override
	public boolean onButtonClick(PlayerEntity player, int id)
	{
		if(this.isInBounds(id))
		{
			this.selectedRecipe.set(id);
			this.populateResult();
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

	@Override
	public void onContentChanged(Inventory inventory)
	{
		ItemStack toolStackInInventory = inventory.getStack(0);
		ItemStack materialStackInInventory = inventory.getStack(1);

		if (!materialStackInInventory.isOf(this.materialStack.getItem()) || !toolStackInInventory.isOf(this.hammerStack.getItem()))
		{
			SmithingAnvilScreenHandler.this.contentsChangedListener.run();

			this.materialStack = materialStackInInventory.copy();
			this.hammerStack = toolStackInInventory.copy();

			this.availableRecipes.clear();
			this.selectedRecipe.set(-1);
			this.outputSlot.setStack(ItemStack.EMPTY);
			this.materialCost = 1;
			if (!this.materialStack.isEmpty() && !this.hammerStack.isEmpty())
			{
				this.availableRecipes = this.world.getRecipeManager().getAllMatches(SmithingAnvilRecipe.Type.INSTANCE, inventory, this.world);
			}
		}

		populateResult();
	}

	void populateResult()
	{
		if (!this.availableRecipes.isEmpty() && this.isInBounds(this.selectedRecipe.get()) && this.availableRecipes.get(this.selectedRecipe.get()).canAfford(this.input, this.world))
		{
			SmithingAnvilRecipe recipe = this.availableRecipes.get(this.selectedRecipe.get());
			this.output.setLastRecipe(recipe);

			ItemStack outputStack = recipe.craft(this.input);
			this.materialCost = recipe.getMaterialCost();
			this.outputSlot.setStack(outputStack);
		}
		else
		{
			this.outputSlot.setStack(ItemStack.EMPTY);
		}
		this.sendContentUpdates();
	}

	@Override
	public ScreenHandlerType<?> getType()
	{
		return ModScreens.SMITHING_ANVIL_SCREEN_HANDLER_TYPE;
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
			else if (slotStack.getItem() instanceof BaseCraftingToolItem) // inventory to hammer slot
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
		this.output.removeStack(1);
		this.context.run((world1, pos) -> {
			this.dropInventory(player, this.input);
		});
	}
}
