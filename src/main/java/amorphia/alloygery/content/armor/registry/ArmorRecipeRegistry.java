package amorphia.alloygery.content.armor.registry;

import amorphia.alloygery.content.armor.recipe.ApplyArmorPartToDynamicArmorRecipeShapeless;
import amorphia.alloygery.content.armor.recipe.BaseArmorRecipeShaped;
import net.minecraft.util.registry.Registry;

public class ArmorRecipeRegistry
{
	public static void init()
	{
		Registry.register(Registry.RECIPE_TYPE, BaseArmorRecipeShaped.Type.ID, BaseArmorRecipeShaped.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, BaseArmorRecipeShaped.Type.ID, BaseArmorRecipeShaped.Serializer.INSTANCE);

		Registry.register(Registry.RECIPE_TYPE, ApplyArmorPartToDynamicArmorRecipeShapeless.Type.ID, ApplyArmorPartToDynamicArmorRecipeShapeless.Type.INSTANCE);
		Registry.register(Registry.RECIPE_SERIALIZER, ApplyArmorPartToDynamicArmorRecipeShapeless.Type.ID, ApplyArmorPartToDynamicArmorRecipeShapeless.Serializer.INSTANCE);
	}
}
