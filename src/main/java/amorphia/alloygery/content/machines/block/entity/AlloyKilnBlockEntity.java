package amorphia.alloygery.content.machines.block.entity;

import amorphia.alloygery.content.machines.recipe.AlloyingRecipe;
import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class AlloyKilnBlockEntity extends AbstractAlloyKilnBlockEntity
{
	public AlloyKilnBlockEntity(BlockPos blockPos, BlockState blockState)
	{
		super(MachineBlockRegistry.ALLOY_KILN_BLOCK_ENTITY, AlloyingRecipe.Type.INSTANCE, blockPos, blockState);
	}

	@Override
	public Text getDisplayName()
	{
		return Text.translatable("container.alloygery.alloy_kiln");
	}
}
