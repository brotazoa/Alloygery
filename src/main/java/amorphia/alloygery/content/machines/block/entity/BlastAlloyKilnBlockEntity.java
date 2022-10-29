package amorphia.alloygery.content.machines.block.entity;

import amorphia.alloygery.content.machines.recipe.BlastAlloyingRecipe;
import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;

public class BlastAlloyKilnBlockEntity extends AbstractAlloyKilnBlockEntity
{
	public BlastAlloyKilnBlockEntity(BlockPos blockPos, BlockState blockState)
	{
		super(MachineBlockRegistry.BLAST_ALLOY_KILN_BLOCK_ENTITY, BlastAlloyingRecipe.Type.INSTANCE, blockPos, blockState);
	}

	@Override
	public Text getDisplayName()
	{
		return new TranslatableText("container.alloygery.blast_alloy_kiln");
	}
}
