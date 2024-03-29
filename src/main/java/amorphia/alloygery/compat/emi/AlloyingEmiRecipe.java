package amorphia.alloygery.compat.emi;

import amorphia.alloygery.content.machines.recipe.AbstractAlloyingRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;

public class AlloyingEmiRecipe extends AbstractAlloyingEmiRecipe
{
	public AlloyingEmiRecipe(AbstractAlloyingRecipe recipe)
	{
		super(recipe);
	}

	@Override
	public EmiRecipeCategory getCategory()
	{
		return AlloygeryEmiPlugin.ALLOYING_CATEGORY;
	}
}
