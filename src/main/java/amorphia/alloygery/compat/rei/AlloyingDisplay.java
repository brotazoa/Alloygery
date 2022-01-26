package amorphia.alloygery.compat.rei;

import amorphia.alloygery.content.recipe.AlloyingRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.Collections;
import java.util.List;

public class AlloyingDisplay implements Display
{
	private final List<EntryIngredient> inputs;
	private final EntryIngredient output;

	public AlloyingDisplay(AlloyingRecipe recipe)
	{
		inputs = recipe.getIngredients().stream().map(EntryIngredients::ofIngredient).toList();
		output = EntryIngredients.of(recipe.getOutput());
	}

	@Override
	public List<EntryIngredient> getInputEntries()
	{
		return inputs;
	}

	@Override
	public List<EntryIngredient> getOutputEntries()
	{
		return Collections.singletonList(output);
	}

	@Override
	public CategoryIdentifier<?> getCategoryIdentifier()
	{
		return AlloygeryPlugin.ALLOYING_CATEGORY;
	}
}
