package amorphia.alloygery.content.machines.block;

import amorphia.alloygery.content.machines.block.entity.SmithingAnvilBlockEntity;
import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import amorphia.alloygery.content.machines.registry.MachineStatisticRegistry;
import amorphia.alloygery.content.machines.screen.SmithingAnvilScreenHandler;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class SmithingAnvilBlock extends BlockWithEntity implements Waterloggable
{
	private static final Text TITLE = new TranslatableText("container.alloygery.smithing_anvil");
	public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

	private static final VoxelShape X_BASE;
	private static final VoxelShape Z_BASE;
	private static final VoxelShape X_STEP;
	private static final VoxelShape Z_STEP;
	private static final VoxelShape X_MIDDLE;
	private static final VoxelShape Z_MIDDLE;
	private static final VoxelShape X_TOP;
	private static final VoxelShape Z_TOP;
	private static final VoxelShape X_AXIS_SHAPE;
	private static final VoxelShape Z_AXIS_SHAPE;

	public SmithingAnvilBlock(Settings settings)
	{
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
	}

	public BlockState getPlacementState(ItemPlacementContext ctx)
	{
		return this.getDefaultState().with(FACING, ctx.getPlayerFacing().rotateYClockwise()).with(WATERLOGGED, ctx.getWorld().getFluidState(ctx.getBlockPos()).getFluid() == Fluids.WATER);
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
	{
		if (itemStack.hasCustomName())
		{
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof SmithingAnvilBlockEntity smithingAnvilBlockEntity)
			{
				smithingAnvilBlockEntity.setCustomName(itemStack.getName());
			}
		}
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved)
	{
		if (state.getBlock() != newState.getBlock())
		{
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof SmithingAnvilBlockEntity smithingAnvilBlockEntity)
			{
				ItemScatterer.spawn(world, pos, smithingAnvilBlockEntity);
			}
		}
		super.onStateReplaced(state, world, pos, newState, moved);
	}

	public BlockState rotate(BlockState state, BlockRotation rotation)
	{
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state)
	{
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos,
			BlockPos neighborPos)
	{
		if (state.get(WATERLOGGED))
		{
			world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
	{
		Direction direction = state.get(FACING);
		return direction.getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
	}

	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
	{
		if (world.isClient)
		{
			return ActionResult.SUCCESS;
		}
		else
		{
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof SmithingAnvilBlockEntity smithingAnvilBlockEntity)
			{
				player.openHandledScreen(smithingAnvilBlockEntity);
				player.incrementStat(MachineStatisticRegistry.INTERACT_WITH_SMITHING_ANVIL);
			}

			return ActionResult.CONSUME;
		}
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
	{
		return new SmithingAnvilBlockEntity(pos, state);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}

	static
	{
		Z_BASE = Block.createCuboidShape(3.0, 0.0, 1.0, 13.0, 1.0, 15.0);
		X_BASE = Block.createCuboidShape(1.0, 0.0, 3.0, 15.0, 1.0, 13.0);
		Z_STEP = Block.createCuboidShape(4.0, 1.0, 2.0, 12.0, 2.0, 14.0);
		X_STEP = Block.createCuboidShape(2.0, 1.0, 4.0, 14.0, 2.0, 12.0);
		Z_MIDDLE = Block.createCuboidShape(6.0, 2.0, 3.0, 10.0, 5.0, 13.0);
		X_MIDDLE = Block.createCuboidShape(3.0, 2.0, 6.0, 13.0, 5.0, 10.0);
		Z_TOP = Block.createCuboidShape(5.0, 5.0, 1.0, 11.0, 9.0, 15.0);
		X_TOP = Block.createCuboidShape(1.0, 5.0, 5.0, 15.0, 9.0, 11.0);

		X_AXIS_SHAPE = VoxelShapes.union(X_BASE, X_STEP, X_MIDDLE, X_TOP);
		Z_AXIS_SHAPE = VoxelShapes.union(Z_BASE, Z_STEP, Z_MIDDLE, Z_TOP);
	}
}
