package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;

public class ToolUpgradeRemovingRecipeShapeless extends CraftingToolDamagingRecipeShapeless
{
	public ToolUpgradeRemovingRecipeShapeless(ShapelessRecipe recipe)
	{
		super(recipe);
	}

	@Override
	public boolean isIgnoredInRecipeBook()
	{
		return true;
	}

	@Override
	public ItemStack craft(CraftingInventory craftingInventory)
	{
		ItemStack toolStack = null;
		for(int i = 0; i < craftingInventory.size(); ++i)
		{
			if(craftingInventory.getStack(i).getItem() instanceof IDynamicTool)
				toolStack = craftingInventory.getStack(i);
		}

		if(toolStack == null || toolStack.isEmpty())
			return ItemStack.EMPTY;

		ItemStack outputStack = super.getOutput().copy();
		if(outputStack == null || outputStack.isEmpty())
			return ItemStack.EMPTY;

		if(outputStack.getItem() instanceof IDynamicTool)
		{
			outputStack.setNbt(toolStack.getOrCreateNbt().copy());
			ToolNBTHelper.removeUpgradeNBTFromToolStackNBT(outputStack);
		}

		return outputStack;
	}

	public static class Type implements RecipeType<ToolUpgradeRemovingRecipeShapeless>
	{
		public static final Identifier ID = Alloygery.identifier("alloygery_tool_upgrade_removing_shapeless");
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
			return new ToolUpgradeRemovingRecipeShapeless(super.read(identifier, jsonObject));
		}

		@Override
		public ShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new ToolUpgradeRemovingRecipeShapeless(super.read(identifier, packetByteBuf));
		}
	}
}
