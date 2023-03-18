package amorphia.alloygery.content.tools;

import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import amorphia.alloygery.content.tools.registry.AlloygeryToolMaterialRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class ToolMaterialHelper
{
	public static AlloygeryToolMaterial getMaterialFromIdentifier(Identifier identifier)
	{
		return AlloygeryToolMaterialRegistry.get(identifier);
	}

	public static Ingredient getRepairIngredientForStack(ItemStack tool)
	{
		return getRepairIngredient(getHeadMaterial(tool));
	}

	public static Ingredient getRepairIngredient(AlloygeryToolMaterial material)
	{
		return material.getRepairIngredient();
	}

	public static AlloygeryToolMaterial getHeadMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getHeadPartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static AlloygeryToolMaterial getBindingMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getBindingPartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static AlloygeryToolMaterial getHandleMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getHandlePartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}

	public static AlloygeryToolMaterial getUpgradeMaterial(ItemStack tool)
	{
		return getMaterialFromIdentifier(ToolNBTHelper.getMaterialIdentifierFromToolPartNBT(
				ToolNBTHelper.getUpgradePartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool))));
	}
}
