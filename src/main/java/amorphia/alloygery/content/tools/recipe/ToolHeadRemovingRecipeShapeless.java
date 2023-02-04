package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;

public class ToolHeadRemovingRecipeShapeless extends CraftingToolDamagingRecipeShapeless
{
	public ToolHeadRemovingRecipeShapeless(CraftingToolDamagingRecipeShapeless recipe)
	{
		super(recipe);
	}

	@Override
	public ItemStack craft(CraftingInventory craftingInventory)
	{
		ItemStack toolStack = null;
		for(int i = 0; i < craftingInventory.size(); i++)
		{
			if(craftingInventory.getStack(i).getItem() instanceof IDynamicTool || craftingInventory.getStack(i).getItem() instanceof ToolItem)
				toolStack = craftingInventory.getStack(i);
		}

		if(toolStack == null || toolStack.isEmpty())
			return ItemStack.EMPTY;

		ItemStack headStack = getOutput().copy();
		if(headStack == null || headStack.isEmpty())
			return ItemStack.EMPTY;

		EnchantmentHelper.set(EnchantmentHelper.get(toolStack), headStack);
		return headStack;
	}

	public static class Type implements RecipeType<ToolHeadRemovingRecipeShapeless>
	{
		public static final Identifier ID = Alloygery.identifier("alloygery_tool_head_removing_shapeless");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends CraftingToolDamagingRecipeShapeless.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		private Serializer() {} //no op

		@Override
		public ShapelessRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new ToolHeadRemovingRecipeShapeless((CraftingToolDamagingRecipeShapeless) super.read(identifier, jsonObject));
		}

		@Override
		public ShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new ToolHeadRemovingRecipeShapeless((CraftingToolDamagingRecipeShapeless) super.read(identifier, packetByteBuf));
		}
	}
}
