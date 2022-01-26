package amorphia.alloygery.content.block.entity;

import amorphia.alloygery.content.block.AbstractAlloyKilnBlock;
import amorphia.alloygery.content.recipe.AbstractAlloyingRecipe;
import amorphia.alloygery.content.screen.AlloyKilnScreenHandler;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.*;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractAlloyKilnBlockEntity extends LockableContainerBlockEntity implements SidedInventory, RecipeInputProvider, RecipeUnlocker
{
	public static final int[] TOP_SLOTS = new int[] { 0, 1, 2, 3 };
	public static final int[] SIDE_SLOTS = new int[] { 4 };
	public static final int[] BOTTOM_SLOTS = new int[] { 5 };

	public static final int FUEL_SLOT = 4;
	public static final int OUTPUT_SLOT = 5;

	protected DefaultedList<ItemStack> inventory;
	protected int litTime;
	protected int totalLitTime;
	protected int smeltingTime;
	protected int totalSmeltingTime;
	protected final PropertyDelegate propertyDelegate;

	private final Object2IntOpenHashMap<Identifier> recipesUsed = new Object2IntOpenHashMap<>();
	private final RecipeType<? extends AbstractAlloyingRecipe> recipeType;

	protected AbstractAlloyKilnBlockEntity(BlockEntityType<?> blockEntityType, RecipeType<? extends AbstractAlloyingRecipe> recipeType, BlockPos blockPos, BlockState blockState)
	{
		super(blockEntityType, blockPos, blockState);
		this.recipeType = recipeType;
		this.inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);
		this.propertyDelegate = new PropertyDelegate() {
			@Override
			public int get(int index)
			{
				return switch (index)
						{
							case 0 -> AbstractAlloyKilnBlockEntity.this.litTime;
							case 1 -> AbstractAlloyKilnBlockEntity.this.totalLitTime;
							case 2 -> AbstractAlloyKilnBlockEntity.this.smeltingTime;
							case 3 -> AbstractAlloyKilnBlockEntity.this.totalSmeltingTime;
							default -> 0;
						};
			}

			@Override
			public void set(int index, int value)
			{
				switch (index)
				{
					case 0 -> AbstractAlloyKilnBlockEntity.this.litTime = value;
					case 1 -> AbstractAlloyKilnBlockEntity.this.totalLitTime = value;
					case 2 -> AbstractAlloyKilnBlockEntity.this.smeltingTime = value;
					case 3 -> AbstractAlloyKilnBlockEntity.this.totalSmeltingTime = value;
				}
			}

			@Override
			public int size()
			{
				return 4;
			}
		};
	}

	@Override
	public void readNbt(NbtCompound nbt)
	{
		super.readNbt(nbt);
		this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
		Inventories.readNbt(nbt, this.inventory);
		this.litTime = nbt.getInt("LitTime");
		this.totalLitTime = nbt.getInt("TotalLitTime");
		this.smeltingTime = nbt.getInt("SmeltingTime");
		this.totalSmeltingTime = nbt.getInt("TotalSmeltingTime");
		markDirty();
	}

	@Override
	protected void writeNbt(NbtCompound nbt)
	{
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, this.inventory);
		nbt.putInt("LitTime", this.litTime);
		nbt.putInt("TotalLitTime", this.totalLitTime);
		nbt.putInt("SmeltingTime", this.smeltingTime);
		nbt.putInt("TotalSmeltingTime", this.totalSmeltingTime);
	}

	@Override
	protected Text getContainerName()
	{
		return null;
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory)
	{
		return new AlloyKilnScreenHandler(syncId, playerInventory, this, propertyDelegate);
	}

	@Override
	public int[] getAvailableSlots(Direction side)
	{
		return switch (side)
				{
					case DOWN -> BOTTOM_SLOTS;
					case UP -> TOP_SLOTS;
					default -> SIDE_SLOTS;
				};
	}

	@Override
	public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir)
	{
		if(slot == 5)
			return false;
		else if(slot != 4)
			return true;
		else
		{
			ItemStack itemStack = this.inventory.get(4);
			return AbstractFurnaceBlockEntity.canUseAsFuel(stack) || stack.isOf(Items.BUCKET) && !itemStack.isOf(Items.BUCKET);
		}
	}

	@Override
	public boolean canExtract(int slot, ItemStack stack, Direction dir)
	{
		if(dir == Direction.DOWN && slot == 5)
			return stack.isOf(Items.WATER_BUCKET) || stack.isOf(Items.BUCKET);
		else return true;
	}

	@Override
	public int size()
	{
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty()
	{
		return this.inventory.stream().allMatch(ItemStack::isEmpty);
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
	public void provideRecipeInputs(RecipeMatcher finder)
	{
		for (ItemStack itemStack : this.inventory)
		{
			finder.addInput(itemStack);
		}
	}

	public void removeIngredient(Ingredient ingredient)
	{
		for(int i = 0; i<4; i++)
		{
			ItemStack itemStack = this.getStack(i);
			if (!itemStack.isEmpty() && ingredient.test(itemStack))
			{
				itemStack.decrement(1);
				this.setStack(i, itemStack.isEmpty() ? ItemStack.EMPTY : itemStack);
				break;
			}
		}
	}

	@Override
	public void clear()
	{
		this.inventory.clear();
	}

	@Override
	public void setLastRecipe(@Nullable Recipe<?> recipe)
	{
		if (recipe != null)
		{
			Identifier identifier = recipe.getId();
			this.recipesUsed.addTo(identifier, 1);
		}
	}

	@Nullable
	@Override
	public Recipe<?> getLastRecipe()
	{
		return null;
	}

	public void dropExperience(ServerPlayerEntity playerEntity, ServerWorld world, Vec3d position)
	{
		List<Recipe<?>> recipes = new ArrayList<>();
		for (Object2IntMap.Entry<Identifier> entry : this.recipesUsed.object2IntEntrySet())
		{
			world.getRecipeManager().get(entry.getKey()).ifPresent((recipe -> {
				recipes.add(recipe);
				float exp = (float) entry.getIntValue() * ((AbstractAlloyingRecipe) recipe).getExperience();
				int expInt = MathHelper.floor(exp);
				float random = MathHelper.fractionalPart(exp);
				if(random != 0.0f && Math.random() < random)
					expInt++;

				ExperienceOrbEntity.spawn(world, position, expInt);
			}));

			if(playerEntity != null) playerEntity.unlockRecipes(recipes);
			this.recipesUsed.clear();
		}
	}

	public static void tick(World world, BlockPos pos, BlockState state, AbstractAlloyKilnBlockEntity blockEntity)
	{
		boolean wasLit = blockEntity.litTime > 0;
		boolean changed = false;
		blockEntity.litTime = blockEntity.litTime > 0 ? blockEntity.litTime - 1 : blockEntity.litTime;

		ItemStack fuel = blockEntity.inventory.get(FUEL_SLOT);
		if (blockEntity.litTime > 0 || !fuel.isEmpty())
		{
			Optional<AbstractAlloyingRecipe> recipe = world.getRecipeManager().getFirstMatch((RecipeType<AbstractAlloyingRecipe>) blockEntity.recipeType, blockEntity, world);
			if (recipe.isPresent())
			{
				blockEntity.totalSmeltingTime = recipe.get().getSmeltingTime();
				if(blockEntity.litTime <= 0 && canSmelt(blockEntity, recipe.get(), world))
				{
					blockEntity.litTime = AbstractFurnaceBlockEntity.createFuelTimeMap().getOrDefault(fuel.getItem(), 0);
					blockEntity.totalLitTime = blockEntity.litTime;
					if (blockEntity.litTime > 0)
					{
						changed = true;
						if (fuel.isOf(Items.LAVA_BUCKET))
						{
							blockEntity.inventory.set(FUEL_SLOT, new ItemStack(Items.BUCKET, 1));
						}
						else if (!fuel.isEmpty())
						{
							fuel.decrement(1);
							if(fuel.isEmpty())
								blockEntity.inventory.set(FUEL_SLOT, ItemStack.EMPTY);
						}
					}
				}

				if (blockEntity.litTime > 0 && canSmelt(blockEntity, recipe.get(), world))
				{
					blockEntity.smeltingTime++;
					if (blockEntity.smeltingTime == blockEntity.totalSmeltingTime)
					{
						changed = true;
						blockEntity.smeltingTime = 0;
						if (canSmelt(blockEntity, recipe.get(), world))
						{
							recipe.get().getIngredients().forEach(blockEntity::removeIngredient);
							ItemStack outputSlot = blockEntity.getStack(OUTPUT_SLOT);
							ItemStack result = recipe.get().craft(blockEntity);
							if (outputSlot.isEmpty())
							{
								blockEntity.setStack(OUTPUT_SLOT, result);
							}
							else
							{
								outputSlot.increment(result.getCount());
							}
							blockEntity.setLastRecipe(recipe.get());
						}
					}
				}
				else
				{
					blockEntity.smeltingTime = 0;
				}
			}
			else
			{
				blockEntity.smeltingTime = 0;
			}
		}
		else if (blockEntity.litTime <= 0 && blockEntity.smeltingTime > 0)
		{
			blockEntity.smeltingTime = MathHelper.clamp(blockEntity.smeltingTime - 2, 0, blockEntity.smeltingTime);
		}

		if (wasLit != (blockEntity.litTime > 0))
		{
			changed = true;
			state = state.with(AbstractAlloyKilnBlock.LIT, blockEntity.litTime > 0);
			assert blockEntity.world != null;
			blockEntity.world.setBlockState(blockEntity.pos, state, 3);
		}

		if(changed)
			blockEntity.markDirty();
	}

	public static boolean canSmelt(AbstractAlloyKilnBlockEntity blockEntity, AbstractAlloyingRecipe recipe, World world)
	{
		ItemStack actual = blockEntity.getStack(OUTPUT_SLOT);
		ItemStack expected = recipe.getOutput();
		return recipe.matches(blockEntity, world) && (actual.isEmpty() || (actual.isItemEqual(expected) && (actual.getCount() + expected.getCount() <= actual.getMaxCount())));
	}
}
