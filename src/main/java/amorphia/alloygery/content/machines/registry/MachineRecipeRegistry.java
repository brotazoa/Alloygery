package amorphia.alloygery.content.machines.registry;

import amorphia.alloygery.content.machines.recipe.AlloyingRecipe;
import amorphia.alloygery.content.machines.recipe.BlastAlloyingRecipe;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import net.minecraft.util.registry.Registry;

public class MachineRecipeRegistry
{
	public static void init()
	{
		Registry.register(Registry.RECIPE_TYPE, AlloyingRecipe.Type.ID, AlloyingRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, AlloyingRecipe.Type.ID, AlloyingRecipe.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, BlastAlloyingRecipe.Type.ID, BlastAlloyingRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, BlastAlloyingRecipe.Type.ID, BlastAlloyingRecipe.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, SmithingAnvilRecipe.Type.ID, SmithingAnvilRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, SmithingAnvilRecipe.Type.ID, SmithingAnvilRecipe.Serializer.INSTANCE);
	}
}
