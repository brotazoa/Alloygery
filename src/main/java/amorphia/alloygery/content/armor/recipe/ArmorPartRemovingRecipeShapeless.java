package amorphia.alloygery.content.armor.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.ArmorMaterialHelper;
import amorphia.alloygery.content.armor.ArmorNBTHelper;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials;
import amorphia.alloygery.content.tools.recipe.CraftingToolDamagingRecipeShapeless;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;

public class ArmorPartRemovingRecipeShapeless extends CraftingToolDamagingRecipeShapeless
{
	public ArmorPartRemovingRecipeShapeless(CraftingToolDamagingRecipeShapeless recipe)
	{
		super(recipe);
	}

	@Override
	public ItemStack craft(CraftingInventory craftingInventory)
	{
		ItemStack baseStack = null;
		for(int slot = 0; slot < craftingInventory.size(); slot++)
		{
			if(craftingInventory.getStack(slot).getItem() instanceof IDynamicArmor)
				baseStack = craftingInventory.getStack(slot);
		}

		if(baseStack == null || baseStack.isEmpty())
			return ItemStack.EMPTY;

		ItemStack armorStack = getOutput().copy();
		if(armorStack == null || armorStack.isEmpty())
			return ItemStack.EMPTY;

		AlloygeryArmorMaterial baseMaterial = ArmorMaterialHelper.getMaterialForLayer(baseStack, ArmorLayer.BASE);
		AlloygeryArmorMaterial plateMaterial = ArmorMaterialHelper.getMaterialForLayer(baseStack, ArmorLayer.PLATE);
		AlloygeryArmorMaterial upgradeMaterial = ArmorMaterialHelper.getMaterialForLayer(baseStack, ArmorLayer.UPGRADE);

		if (upgradeMaterial != AlloygeryArmorMaterials.UNKNOWN && upgradeMaterial != AlloygeryArmorMaterials.HIDDEN)
		{
			ArmorNBTHelper.addAlloygeryNBTToArmorStack(armorStack, ArmorNBTHelper.createArmorNBT(baseMaterial, plateMaterial));

			EnchantmentHelper.set(EnchantmentHelper.get(baseStack), armorStack);

			return armorStack;
		}
		else if (plateMaterial != AlloygeryArmorMaterials.UNKNOWN && plateMaterial != AlloygeryArmorMaterials.HIDDEN)
		{
			ArmorNBTHelper.addAlloygeryNBTToArmorStack(armorStack, ArmorNBTHelper.createArmorNBT(baseMaterial));

			EnchantmentHelper.set(EnchantmentHelper.get(baseStack), armorStack);

			return armorStack;
		}

		return ItemStack.EMPTY;
	}

	public static class Type implements RecipeType<ArmorPartRemovingRecipeShapeless>
	{
		public static final Identifier ID = Alloygery.identifier("armor_part_removing_shapeless");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends CraftingToolDamagingRecipeShapeless.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		protected Serializer() {} //protected no op

		@Override
		public ShapelessRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new ArmorPartRemovingRecipeShapeless((CraftingToolDamagingRecipeShapeless) super.read(identifier, jsonObject));
		}

		@Override
		public ShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new ArmorPartRemovingRecipeShapeless((CraftingToolDamagingRecipeShapeless) super.read(identifier, packetByteBuf));
		}
	}
}
