package amorphia.alloygery.content.tools;

import amorphia.alloygery.content.tools.material.ToolMaterial;
import amorphia.alloygery.content.tools.registry.ToolMaterialRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class ToolMaterialHelper
{
	public static ToolMaterial getMaterialFromIdentifier(Identifier identifier)
	{
		return ToolMaterialRegistry.get(identifier);
	}

	public static Ingredient getRepairIngredientForStack(ItemStack tool)
	{
		return getRepairIngredient(getHeadMaterial(tool));
	}

	public static Ingredient getRepairIngredient(ToolMaterial material)
	{
		return material.getRepairIngredient();
	}

	public static ToolMaterial getHeadMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getHeadPartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static ToolMaterial getBindingMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getBindingPartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static ToolMaterial getHandleMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getHandlePartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static ToolMaterial getUpgradeMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getUpgradePartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}
}
