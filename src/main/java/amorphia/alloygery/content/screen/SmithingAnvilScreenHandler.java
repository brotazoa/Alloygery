package amorphia.alloygery.content.screen;

import amorphia.alloygery.content.item.BaseCraftingItem;
import amorphia.alloygery.content.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.registry.ModBlocks;
import amorphia.alloygery.registry.ModScreens;
import de.siphalor.nbtcrafting.api.RecipeUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Optional;

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
		this.contentsChangedListener = () -> {};

		this.input = new SimpleInventory(2){
			@Override
			public void markDirty()
			{
				SmithingAnvilScreenHandler.this.onContentChanged(input);
				SmithingAnvilScreenHandler.this.contentsChangedListener.run();
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

				//this might break
				Optional<SmithingAnvilRecipe> match = player.world.getRecipeManager().getFirstMatch(SmithingAnvilRecipe.Type.INSTANCE, input, player.world);
				DefaultedList<ItemStack> remainders = match.map(smithingAnvilRecipe -> smithingAnvilRecipe.getRemainder(input)).orElse(null);

				final int lastRecipe = selectedRecipe.get();

				SmithingAnvilScreenHandler.this.hammerSlot.takeStack(1);
				SmithingAnvilScreenHandler.this.materialSlot.takeStack(1);

				if (remainders != null)
				{
					context.run((world1, pos) -> {
						RecipeUtil.putRemainders(remainders, input, world1, pos);
					});
				}

				hammerStack = SmithingAnvilScreenHandler.this.hammerSlot.getStack();
				materialStack = SmithingAnvilScreenHandler.this.materialSlot.getStack();

				if (!hammerStack.isEmpty() && !materialStack.isEmpty())
				{
					selectedRecipe.set(lastRecipe);
					SmithingAnvilScreenHandler.this.populateResult();
				}

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

	public boolean canCraft()
	{
		return this.hammerSlot.hasStack() && this.materialSlot.hasStack() && !this.availableRecipes.isEmpty();
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
		ItemStack itemStack = this.materialSlot.getStack();
		ItemStack hStack = this.hammerSlot.getStack();

		if (!itemStack.isOf(this.materialStack.getItem()) || !hStack.isOf(this.hammerStack.getItem()))
		{
			this.materialStack = itemStack.copy();
			this.hammerStack = hStack.copy();

			this.availableRecipes.clear();
			this.selectedRecipe.set(-1);
			this.outputSlot.setStack(ItemStack.EMPTY);
			if (!materialStack.isEmpty() && !hammerStack.isEmpty())
			{
				this.availableRecipes = this.world.getRecipeManager().getAllMatches(SmithingAnvilRecipe.Type.INSTANCE, inventory, this.world);
			}
		}
	}

	void populateResult()
	{
		if (!this.availableRecipes.isEmpty() && this.isInBounds(this.selectedRecipe.get()))
		{
			SmithingAnvilRecipe recipe = this.availableRecipes.get(this.selectedRecipe.get());
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
			else if (slotStack.getItem() instanceof BaseCraftingItem) // inventory to hammer slot
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
