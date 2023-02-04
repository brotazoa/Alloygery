package amorphia.alloygery.content.armor.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.ArmorNBTHelper;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.IArmorPart;
import amorphia.alloygery.content.armor.item.IDyeableItemWithDefaultColor;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;
import amorphia.alloygery.content.armor.registry.ArmorItemRegistry;
import amorphia.alloygery.content.tools.mixin.SmithingRecipeAccessor;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Set;

public class ApplyArmorPartToDynamicArmorRecipeSmithing extends SmithingRecipe
{
	public ApplyArmorPartToDynamicArmorRecipeSmithing(SmithingRecipeAccessor recipe)
	{
		super(recipe.alloygery_getId(), recipe.alloygery_getBase(), recipe.alloygery_getAddition(), recipe.alloygery_getResult());
	}

	@Override
	public ItemStack craft(Inventory inventory)
	{
		return ApplyArmorPartToDynamicArmorRecipeSmithing.craft(inventory.getStack(0), inventory.getStack(1), getOutput());
	}

	protected static ItemStack craft(ItemStack base, ItemStack addition, ItemStack output)
	{
		ItemStack baseStack = base == null ? ItemStack.EMPTY : base.copy();
		ItemStack additionStack = addition == null ? ItemStack.EMPTY : addition.copy();

		//check for vanilla chain armors
		//TODO: revisit this
		if(baseStack.getItem() == Items.CHAINMAIL_HELMET)
			baseStack = ArmorItemRegistry.ITEMS.get("dynamic_chain_helmet").getDefaultStack().copy();
		if(baseStack.getItem() == Items.CHAINMAIL_CHESTPLATE)
			baseStack = ArmorItemRegistry.ITEMS.get("dynamic_chain_chestplate").getDefaultStack().copy();
		if(baseStack.getItem() == Items.CHAINMAIL_LEGGINGS)
			baseStack = ArmorItemRegistry.ITEMS.get("dynamic_chain_leggings").getDefaultStack().copy();
		if(baseStack.getItem() == Items.CHAINMAIL_BOOTS)
			baseStack = ArmorItemRegistry.ITEMS.get("dynamic_chain_boots").getDefaultStack().copy();
		//check for vanilla leather armors
		if(baseStack.getItem() == Items.LEATHER_HELMET)
			baseStack = ArmorItemRegistry.ITEMS.get("dynamic_leather_helmet").getDefaultStack().copy();
		if(baseStack.getItem() == Items.LEATHER_CHESTPLATE)
			baseStack = ArmorItemRegistry.ITEMS.get("dynamic_leather_chestplate").getDefaultStack().copy();
		if(baseStack.getItem() == Items.LEATHER_LEGGINGS)
			baseStack = ArmorItemRegistry.ITEMS.get("dynamic_leather_leggings").getDefaultStack().copy();
		if(baseStack.getItem() == Items.LEATHER_BOOTS)
			baseStack = ArmorItemRegistry.ITEMS.get("dynamic_leather_boots").getDefaultStack().copy();

		if(baseStack == null || baseStack.isEmpty() || additionStack == null || additionStack.isEmpty())
			return ItemStack.EMPTY;

		ItemStack armorStack = output.copy();
		if(armorStack == null || armorStack.isEmpty())
			return ItemStack.EMPTY;

		armorStack.setNbt(baseStack.getNbt());

		if (armorStack.getItem() instanceof IDynamicArmor dynamicArmor && baseStack.getItem() instanceof IDynamicArmor dynamicArmorBase && additionStack.getItem() instanceof IArmorPart armorPart)
		{
			if(armorPart.getArmorLayer() == ArmorLayer.BASE)
				return ItemStack.EMPTY;

			if(!baseStack.hasNbt() || !ArmorNBTHelper.isArmorNBT(ArmorNBTHelper.getAlloygeryDataNBT(baseStack)))
				ArmorNBTHelper.addAlloygeryNBTToArmorStack(baseStack, ArmorNBTHelper.createArmorNBT(dynamicArmorBase.getDefaultBaseMaterial()));

			if (armorPart.getArmorLayer() == ArmorLayer.PLATE)
			{
				if(ArmorNBTHelper.hasArmorLayer(baseStack, ArmorLayer.PLATE))
					return ItemStack.EMPTY;

				AlloygeryArmorMaterial baseMaterial = AlloygeryArmorMaterialRegistry.get(ArmorNBTHelper.getMaterialIdentifierFromArmorLayer(baseStack, ArmorLayer.BASE));
				AlloygeryArmorMaterial plateMaterial = armorPart.getArmorMaterial();

				ArmorNBTHelper.addAlloygeryNBTToArmorStack(armorStack, ArmorNBTHelper.createArmorNBT(baseMaterial, plateMaterial));

				if(ArmorNBTHelper.armorLayerHasColor(baseStack, ArmorLayer.BASE))
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.BASE, ArmorNBTHelper.getLayerColor(baseStack, ArmorLayer.BASE));

				if(armorPart instanceof IDyeableItemWithDefaultColor dyeableItem)
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.PLATE, dyeableItem.getColor(additionStack));
			}
			else if (armorPart.getArmorLayer() == ArmorLayer.UPGRADE)
			{
				if(ArmorNBTHelper.hasArmorLayer(baseStack, ArmorLayer.UPGRADE))
					return ItemStack.EMPTY;

				if(!ArmorNBTHelper.hasArmorLayer(baseStack, ArmorLayer.PLATE))
					return ItemStack.EMPTY;

				AlloygeryArmorMaterial baseMaterial = AlloygeryArmorMaterialRegistry.get(ArmorNBTHelper.getMaterialIdentifierFromArmorLayer(baseStack, ArmorLayer.BASE));
				AlloygeryArmorMaterial plateMaterial = AlloygeryArmorMaterialRegistry.get(ArmorNBTHelper.getMaterialIdentifierFromArmorLayer(baseStack, ArmorLayer.PLATE));
				AlloygeryArmorMaterial upgradeMaterial = armorPart.getArmorMaterial();

				ArmorNBTHelper.addAlloygeryNBTToArmorStack(armorStack, ArmorNBTHelper.createArmorNBT(baseMaterial, plateMaterial, upgradeMaterial));

				if(ArmorNBTHelper.armorLayerHasColor(baseStack, ArmorLayer.BASE))
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.BASE, ArmorNBTHelper.getLayerColor(baseStack, ArmorLayer.BASE));

				if(ArmorNBTHelper.armorLayerHasColor(baseStack, ArmorLayer.PLATE))
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.PLATE, ArmorNBTHelper.getLayerColor(baseStack, ArmorLayer.PLATE));

				if(armorPart instanceof IDyeableItemWithDefaultColor dyeableItem)
					ArmorNBTHelper.setArmorLayerColor(armorStack, ArmorLayer.UPGRADE, dyeableItem.getColor(additionStack));
			}

			EnchantmentHelper.set(mergeEnchantments(baseStack, additionStack), armorStack);
		}

		return armorStack;
	}

	protected static Map<Enchantment, Integer> mergeEnchantments(ItemStack base, ItemStack addition)
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

	public static class Type implements RecipeType<ApplyArmorPartToDynamicArmorRecipeSmithing>
	{
		public static final Identifier ID = Alloygery.identifier("apply_armor_part_to_armor_smithing");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends SmithingRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		protected Serializer(){} //protected no op

		@Override
		public SmithingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new ApplyArmorPartToDynamicArmorRecipeSmithing((SmithingRecipeAccessor) super.read(identifier, packetByteBuf));
		}

		@Override
		public SmithingRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new ApplyArmorPartToDynamicArmorRecipeSmithing((SmithingRecipeAccessor) super.read(identifier, jsonObject));
		}
	}
}
