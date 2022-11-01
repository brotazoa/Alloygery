package amorphia.alloygery.content.tools;

import amorphia.alloygery.content.materials.AlloygeryMaterial;
import amorphia.alloygery.content.materials.registry.AlloygeryMaterialRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class ToolMaterialHelper
{
	public static AlloygeryMaterial getMaterialFromIdentifier(Identifier identifier)
	{
		return AlloygeryMaterialRegistry.get(identifier);
	}

	public static Ingredient getRepairIngredientForStack(ItemStack tool)
	{
		return getRepairIngredient(getHeadMaterial(tool));
	}

	public static Ingredient getRepairIngredient(AlloygeryMaterial material)
	{
		return material.getRepairIngredient();
	}

	public static AlloygeryMaterial getHeadMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getHeadPartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static AlloygeryMaterial getBindingMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getBindingPartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static AlloygeryMaterial getHandleMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getHandlePartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static AlloygeryMaterial getUpgradeMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getUpgradePartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}
}
