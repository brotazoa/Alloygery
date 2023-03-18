package amorphia.alloygery.content.armor;

import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.IArmorPart;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public class ArmorMaterialHelper
{
	public static AlloygeryArmorMaterial getMaterialFromIdentifier(Identifier identifier)
	{
		return AlloygeryArmorMaterialRegistry.get(identifier);
	}

	public static Ingredient getRepairIngredient(AlloygeryArmorMaterial material)
	{
		return material.getRepairIngredient();
	}

	public static Ingredient getRepairIngredientForStack(ItemStack armorStack)
	{
		return hasPlateLayer(armorStack) ? getRepairIngredient(getMaterialForLayer(armorStack, ArmorLayer.PLATE)) : getRepairIngredient(getMaterialForLayer(armorStack, ArmorLayer.BASE));
	}

	public static AlloygeryArmorMaterial getMaterialForLayer(ItemStack armorStack, ArmorLayer layer)
	{
		if (armorStack.getItem() instanceof IArmorPart armorPart)
		{
			return armorPart.getArmorLayer().equals(layer) ? armorPart.getArmorMaterial() : AlloygeryArmorMaterials.HIDDEN;
		}

		if (armorStack.getItem() instanceof IDynamicArmor dynamicArmor && !ArmorNBTHelper.isAlloygeryDataNBT(armorStack.getNbt()))
		{
			return switch (layer)
			{
				case BASE -> dynamicArmor.getDefaultBaseMaterial();
				case PLATE -> dynamicArmor.getDefaultPlateMaterial();
				case UPGRADE -> dynamicArmor.getDefaultUpgradeMaterial();
				default -> AlloygeryArmorMaterials.HIDDEN;
			};
		}

		return getMaterialFromIdentifier(ArmorNBTHelper.getMaterialIdentifierFromArmorLayer(armorStack, layer));
	}

	public static void setLayerColor(ItemStack armorStack, ArmorLayer layer, int color)
	{
		ArmorNBTHelper.setArmorLayerColor(armorStack, layer, color);
	}

	public static void removeLayerColor(ItemStack armorStack, ArmorLayer layer)
	{
		ArmorNBTHelper.removeArmorLayerColor(armorStack, layer);
	}

	public static int getLayerColor(ItemStack armorStack, ArmorLayer layer)
	{
		return isLayerColored(armorStack, layer) ? ArmorNBTHelper.getLayerColor(armorStack, layer) :
				armorStack.getItem() instanceof DyeableItem dyeableItem ? dyeableItem.getColor(armorStack) :
						getMaterialForLayer(armorStack, layer).getMaterialColor();
	}

	public static boolean isLayerColored(ItemStack armorStack, ArmorLayer layer)
	{
		return ArmorNBTHelper.armorLayerHasColor(armorStack, layer);
	}

	public static boolean hasPlateLayer(ItemStack armorStack)
	{
		return ArmorNBTHelper.hasArmorLayer(armorStack, ArmorLayer.PLATE);
	}

	public static boolean hasUpgradeLayer(ItemStack armorStack)
	{
		return ArmorNBTHelper.hasArmorLayer(armorStack, ArmorLayer.UPGRADE);
	}
}
