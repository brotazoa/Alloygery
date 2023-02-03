package amorphia.alloygery.content.machines.registry;

import amorphia.alloygery.content.machines.recipe.AlloyingRecipe;
import amorphia.alloygery.content.machines.recipe.BlastAlloyingRecipe;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilCopyEnchantmentsRecipe;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

public class MachineRecipeRegistry
{
	public static RecipeType<AlloyingRecipe> ALLOYING_RECIPE_TYPE;
	public static RecipeType<BlastAlloyingRecipe> BLAST_ALLOYING_RECIPE_TYPE;
	public static RecipeType<SmithingAnvilRecipe> SMITHING_ANVIL_RECIPE_TYPE;
	public static RecipeType<SmithingAnvilCopyEnchantmentsRecipe> SMITHING_ANVIL_COPY_ENCHANTMENTS_RECIPE_TYPE;

	public static void init()
	{
		ALLOYING_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, AlloyingRecipe.Type.ID, AlloyingRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, AlloyingRecipe.Type.ID, AlloyingRecipe.Serializer.INSTANCE);

		BLAST_ALLOYING_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, BlastAlloyingRecipe.Type.ID, BlastAlloyingRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, BlastAlloyingRecipe.Type.ID, BlastAlloyingRecipe.Serializer.INSTANCE);

		SMITHING_ANVIL_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, SmithingAnvilRecipe.Type.ID, SmithingAnvilRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, SmithingAnvilRecipe.Type.ID, SmithingAnvilRecipe.Serializer.INSTANCE);

		SMITHING_ANVIL_COPY_ENCHANTMENTS_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, SmithingAnvilCopyEnchantmentsRecipe.Type.ID, SmithingAnvilCopyEnchantmentsRecipe.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, SmithingAnvilCopyEnchantmentsRecipe.Type.ID, SmithingAnvilCopyEnchantmentsRecipe.Serializer.INSTANCE);
	}
}
