package amorphia.alloygery.content.tools.registry;

import amorphia.alloygery.content.tools.recipe.*;
import net.minecraft.util.registry.Registry;

public class ToolRecipeRegistry
{
	public static void init()
	{
		Registry.register(Registry.RECIPE_TYPE, ToolRecipeShaped.Type.ID, ToolRecipeShaped.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, ToolRecipeShaped.Type.ID, ToolRecipeShaped.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, SimpleToolRecipeShaped.Type.ID, SimpleToolRecipeShaped.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, SimpleToolRecipeShaped.Type.ID, SimpleToolRecipeShaped.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, ToolUpgradeRecipeSmithing.Type.ID, ToolUpgradeRecipeSmithing.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, ToolUpgradeRecipeSmithing.Type.ID, ToolUpgradeRecipeSmithing.Serializer.INSTANCE);

//		Registry.register(Registry.RECIPE_TYPE, ToolUpgradeRemovingRecipeSmithing.Type.ID, ToolUpgradeRemovingRecipeSmithing.Type.INSTANCE);
//		Registry.register(Registry.RECIPE_SERIALIZER, ToolUpgradeRemovingRecipeSmithing.Type.ID, ToolUpgradeRemovingRecipeSmithing.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, CraftingToolDamagingRecipeShapeless.Type.ID, CraftingToolDamagingRecipeShapeless.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, CraftingToolDamagingRecipeShapeless.Type.ID, CraftingToolDamagingRecipeShapeless.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, ToolUpgradeRemovingRecipeShapeless.Type.ID, ToolUpgradeRemovingRecipeShapeless.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, ToolUpgradeRemovingRecipeShapeless.Type.ID, ToolUpgradeRemovingRecipeShapeless.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, ToolHeadRemovingRecipeShapeless.Type.ID, ToolHeadRemovingRecipeShapeless.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, ToolHeadRemovingRecipeShapeless.Type.ID, ToolHeadRemovingRecipeShapeless.Serializer.INSTANCE);
	}
}
