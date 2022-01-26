package amorphia.alloygery.content.block.entity;

import amorphia.alloygery.content.recipe.AlloyingRecipe;
import amorphia.alloygery.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;

public class AlloyKilnBlockEntity extends AbstractAlloyKilnBlockEntity
{
	public AlloyKilnBlockEntity(BlockPos blockPos, BlockState blockState)
	{
		super(ModBlocks.ALLOY_KILN_BLOCK_ENTITY, AlloyingRecipe.Type.INSTANCE, blockPos, blockState);
	}

	@Override
	public Text getDisplayName()
	{
		return new TranslatableText("container.alloygery.alloy_kiln");
	}
}
