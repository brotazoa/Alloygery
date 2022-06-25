package amorphia.alloygery.content.block.entity;

import amorphia.alloygery.content.recipe.BlastAlloyingRecipe;
import amorphia.alloygery.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class BlastAlloyKilnBlockEntity extends AbstractAlloyKilnBlockEntity
{
	public BlastAlloyKilnBlockEntity(BlockPos blockPos, BlockState blockState)
	{
		super(ModBlocks.BLAST_ALLOY_KILN_BLOCK_ENTITY, BlastAlloyingRecipe.Type.INSTANCE, blockPos, blockState);
	}

	@Override
	public Text getDisplayName()
	{
		return Text.translatable("container.alloygery.blast_alloy_kiln");
	}
}
