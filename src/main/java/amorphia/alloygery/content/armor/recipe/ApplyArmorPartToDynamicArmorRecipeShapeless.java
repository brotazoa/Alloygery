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
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

		return ApplyArmorPartToDynamicArmorRecipeSmithing.craft(baseArmorStack, armorPartStack, getOutput());
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
