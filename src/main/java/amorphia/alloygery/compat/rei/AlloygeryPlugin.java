package amorphia.alloygery.compat.rei;

import amorphia.alloygery.content.recipe.AlloyingRecipe;
import amorphia.alloygery.content.recipe.BlastAlloyingRecipe;
import amorphia.alloygery.registry.ModItems;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class AlloygeryPlugin implements REIClientPlugin
{
	public static final CategoryIdentifier<AlloyingDisplay> ALLOYING_CATEGORY = CategoryIdentifier.of(AlloyingRecipe.Type.ID);
	public static final CategoryIdentifier<BlastAlloyingDisplay> BLAST_ALLOYING_CATEGORY = CategoryIdentifier.of(BlastAlloyingRecipe.Type.ID);

	@Override
	public void registerCategories(CategoryRegistry registry)
	{
		registry.add(new AlloyingCategory());
		registry.add(new BlastAlloyingCategory());

		registry.addWorkstations(ALLOYING_CATEGORY, EntryStacks.of(ModItems.ALLOY_KILN));
		registry.addWorkstations(BLAST_ALLOYING_CATEGORY, EntryStacks.of(ModItems.BLAST_ALLOY_KILN));
	}

	@Override
	public void registerDisplays(DisplayRegistry registry)
	{
		registry.registerFiller(AlloyingRecipe.class, AlloyingDisplay::new);
		registry.registerFiller(BlastAlloyingRecipe.class, BlastAlloyingDisplay::new);
	}
}
