package amorphia.alloygery.compat.emi;

import amorphia.alloygery.content.machines.recipe.AbstractAlloyingRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;

public class BlastAlloyingEmiRecipe extends AbstractAlloyingEmiRecipe
{
	public BlastAlloyingEmiRecipe(AbstractAlloyingRecipe recipe)
	{
		super(recipe);
	}

	@Override
	public EmiRecipeCategory getCategory()
	{
		return AlloygeryEmiPlugin.BLAST_ALLOYING_CATEGORY;
	}
}
