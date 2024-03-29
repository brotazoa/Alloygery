package amorphia.alloygery.content.armor.registry;

import amorphia.alloygery.content.armor.recipe.ApplyArmorPartToDynamicArmorRecipeShapeless;
import amorphia.alloygery.content.armor.recipe.ApplyArmorPartToDynamicArmorRecipeSmithing;
import amorphia.alloygery.content.armor.recipe.ArmorPartRemovingRecipeShapeless;
import amorphia.alloygery.content.armor.recipe.BaseArmorRecipeShaped;
import amorphia.alloygery.content.tools.recipe.CraftingToolDamagingRecipeShapeless;
import net.minecraft.util.registry.Registry;

public class ArmorRecipeRegistry
{
	public static void init()
	{
		Registry.register(Registry.RECIPE_TYPE, BaseArmorRecipeShaped.Type.ID, BaseArmorRecipeShaped.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, BaseArmorRecipeShaped.Type.ID, BaseArmorRecipeShaped.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, ApplyArmorPartToDynamicArmorRecipeShapeless.Type.ID, ApplyArmorPartToDynamicArmorRecipeShapeless.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, ApplyArmorPartToDynamicArmorRecipeShapeless.Type.ID, ApplyArmorPartToDynamicArmorRecipeShapeless.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, ApplyArmorPartToDynamicArmorRecipeSmithing.Type.ID, ApplyArmorPartToDynamicArmorRecipeSmithing.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, ApplyArmorPartToDynamicArmorRecipeSmithing.Type.ID, ApplyArmorPartToDynamicArmorRecipeSmithing.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, ArmorPartRemovingRecipeShapeless.Type.ID, ArmorPartRemovingRecipeShapeless.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, ArmorPartRemovingRecipeShapeless.Type.ID, ArmorPartRemovingRecipeShapeless.Serializer.INSTANCE);
	}
}
