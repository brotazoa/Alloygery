package amorphia.alloygery.registry;

import amorphia.alloygery.content.recipe.AlloyingRecipe;
import amorphia.alloygery.content.recipe.BlastAlloyingRecipe;
import net.minecraft.util.registry.Registry;

public class ModRecipes
{
	public static void register()
	{
		Registry.register(Registry.RECIPE_TYPE, AlloyingRecipe.Type.ID, AlloyingRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, AlloyingRecipe.Type.ID, AlloyingRecipe.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, BlastAlloyingRecipe.Type.ID, BlastAlloyingRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, BlastAlloyingRecipe.Type.ID, BlastAlloyingRecipe.Serializer.INSTANCE);
	}
}
