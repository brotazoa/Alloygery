package amorphia.alloygery.content.armor.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.ArmorNBTHelper;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.IArmorPart;
import amorphia.alloygery.content.armor.item.IDyeableItemWithDefaultColor;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Set;

public class ApplyArmorPartToDynamicArmorRecipeShapeless extends ShapelessRecipe
{
	public ApplyArmorPartToDynamicArmorRecipeShapeless(ShapelessRecipe recipe)
	{
		super(recipe.getId(), recipe.getGroup(), recipe.getOutput(), recipe.getIngredients());
	}

	@Override
	public ItemStack craft(CraftingInventory craftingInventory)
	{
		ItemStack baseArmorStack = null;
		ItemStack armorPartStack = null;

		for(int slot = 0; slot < craftingInventory.size(); slot++)
		{
			if(craftingInventory.getStack(slot).getItem() instanceof IDynamicArmor)
			{
				baseArmorStack = craftingInventory.getStack(slot);
			}

			if(craftingInventory.getStack(slot).getItem() instanceof  IArmorPart)
			{
				armorPartStack = craftingInventory.getStack(slot);
			}
		}

		if(baseArmorStack == null || baseArmorStack.isEmpty() || armorPartStack == null || armorPartStack.isEmpty())
		{
			return ItemStack.EMPTY;
		}

		ItemStack armorStack = super.getOutput().copy();
		if(armorStack == null || armorStack.isEmpty())
		{
			return ItemStack.EMPTY;
		}

		if (armorStack.getItem() instanceof IDynamicArmor dynamicArmor && baseArmorStack.getItem() instanceof IDynamicArmor dynamicArmorBase && armorPartStack.getItem() instanceof IArmorPart armorPart)
		{
			//can't apply a base armor part (they shouldn't exist)
			if(armorPart.getArmorLayer() == ArmorLayer.BASE)
			{
				return ItemStack.EMPTY;
			}

			//base armor wasn't crafted, came from creative menu
			if(!baseArmorStack.hasNbt() || !ArmorNBTHelper.isArmorNBT(ArmorNBTHelper.getAlloygeryDataNBT(baseArmorStack)))
			{
				ArmorNBTHelper.addAlloygeryNBTToArmorStack(baseArmorStack, ArmorNBTHelper.createArmorNBT(dynamicArmorBase.getDefaultBaseMaterial()));
			}

			//apply armor layer, only if the armor layer is absent
			if (armorPart.getArmorLayer() == ArmorLayer.PLATE)
			{
				if(ArmorNBTHelper.hasArmorLayer(baseArmorStack, ArmorLayer.PLATE))
				{
					return ItemStack.EMPTY;
				}

				AlloygeryArmorMaterial baseMaterial = AlloygeryArmorMaterialRegistry.get(ArmorNBTHelper.getMaterialIdentifierFromArmorLayer(baseArmorStack, ArmorLayer.BASE));
				AlloygeryArmorMaterial armorMaterial = armorPart.getArmorMaterial();

				ArmorNBTHelper.addAlloygeryNBTToArmorStack(armorStack, ArmorNBTHelper.createArmorNBT(baseMaterial, armorMaterial));

				if(ArmorNBTHelper.armorLayerHasColor(baseArmorStack, ArmorLayer.BASE))
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.BASE, ArmorNBTHelper.getLayerColor(baseArmorStack, ArmorLayer.BASE));

				if(armorPart instanceof IDyeableItemWithDefaultColor dyeableArmorPart)
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.PLATE, dyeableArmorPart.getColor(armorPartStack));
			}
			//apply upgrade layer, only if the upgrade layer is absent, and the armor layer is present
			else if (armorPart.getArmorLayer() == ArmorLayer.UPGRADE)
			{
				if(ArmorNBTHelper.hasArmorLayer(baseArmorStack, ArmorLayer.UPGRADE))
				{
					return ItemStack.EMPTY;
				}

				if(!ArmorNBTHelper.hasArmorLayer(baseArmorStack, ArmorLayer.PLATE))
				{
					return ItemStack.EMPTY;
				}

				AlloygeryArmorMaterial baseMaterial = AlloygeryArmorMaterialRegistry.get(ArmorNBTHelper.getMaterialIdentifierFromArmorLayer(baseArmorStack, ArmorLayer.BASE));
				AlloygeryArmorMaterial armorMaterial = AlloygeryArmorMaterialRegistry.get(ArmorNBTHelper.getMaterialIdentifierFromArmorLayer(baseArmorStack, ArmorLayer.PLATE));
				AlloygeryArmorMaterial upgradeMaterial = armorPart.getArmorMaterial();

				ArmorNBTHelper.addAlloygeryNBTToArmorStack(armorStack, ArmorNBTHelper.createArmorNBT(baseMaterial, armorMaterial, upgradeMaterial));

				if(ArmorNBTHelper.armorLayerHasColor(baseArmorStack, ArmorLayer.BASE))
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.BASE, ArmorNBTHelper.getLayerColor(baseArmorStack, ArmorLayer.BASE));

				if(ArmorNBTHelper.armorLayerHasColor(baseArmorStack, ArmorLayer.PLATE))
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.PLATE, ArmorNBTHelper.getLayerColor(baseArmorStack, ArmorLayer.PLATE));

				if(armorPart instanceof IDyeableItemWithDefaultColor dyeableArmorPart)
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.UPGRADE, dyeableArmorPart.getColor(armorPartStack));
			}

			//copy enchantments from base to armor
			Map<Enchantment, Integer> enchantmentsMap = mergeEnchantments(baseArmorStack, armorPartStack);
			EnchantmentHelper.set(enchantmentsMap, armorStack);
		}

		return armorStack;
	}

	//TODO: make this a utility
	private static Map<Enchantment, Integer> mergeEnchantments(ItemStack base, ItemStack addition)
	{
		Map<Enchantment, Integer> baseEnchantmentsMap = EnchantmentHelper.get(base);
		Map<Enchantment, Integer> additionEnchantmentsMap = EnchantmentHelper.get(addition);

		Map<Enchantment, Integer> mergedEnchantmentsMap = Maps.newHashMap(baseEnchantmentsMap);

		Set<Enchantment> additionEnchantments = additionEnchantmentsMap.keySet();

		for(Enchantment enchantment : additionEnchantments)
		{
			if(baseEnchantmentsMap.containsKey(enchantment))
			{
				int baseLevel = baseEnchantmentsMap.get(enchantment);
				int mergeLevel = additionEnchantmentsMap.get(enchantment);
				if (baseLevel == mergeLevel)
				{
					mergedEnchantmentsMap.put(enchantment, baseLevel + 1);
				}
				else
				{
					mergedEnchantmentsMap.put(enchantment, Math.max(baseLevel, mergeLevel));
				}
			}
			else if(EnchantmentHelper.isCompatible(mergedEnchantmentsMap.keySet(), enchantment))
			{
				mergedEnchantmentsMap.put(enchantment, additionEnchantmentsMap.get(enchantment));
			}
		}

		return mergedEnchantmentsMap;
	}

	public static class Type implements RecipeType<ApplyArmorPartToDynamicArmorRecipeShapeless>
	{
		public static final Identifier ID = Alloygery.identifier("apply_armor_part_to_armor_shapeless");
		public static final Type INSTANCE = new Type();

		private Type(){} //no op
	}

	public static class Serializer extends ShapelessRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		protected Serializer() {}

		@Override
		public ShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new ApplyArmorPartToDynamicArmorRecipeShapeless(super.read(identifier, packetByteBuf));
		}

		@Override
		public ShapelessRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new ApplyArmorPartToDynamicArmorRecipeShapeless(super.read(identifier, jsonObject));
		}
	}
}
