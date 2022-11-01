package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolMaterialHelper;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.part.ToolPartItem;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import amorphia.alloygery.content.tools.mixin.SmithingRecipeAccessor;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;

public class ToolUpgradeRecipeSmithing extends SmithingRecipe
{
	public ToolUpgradeRecipeSmithing(SmithingRecipeAccessor recipe)
	{
		super(recipe.alloygery_getId(), recipe.alloygery_getBase(), recipe.alloygery_getAddition(), recipe.alloygery_getResult());
	}

	@Override
	public boolean isIgnoredInRecipeBook()
	{
		return true;
	}

	@Override
	public ItemStack craft(Inventory inventory)
	{
		ItemStack baseStack = inventory.getStack(0);
		ItemStack additionStack = inventory.getStack(1);

		if(baseStack == null || baseStack.isEmpty() || !(baseStack.getItem() instanceof IDynamicTool))
			return ItemStack.EMPTY;

		ItemStack toolStack = super.craft(inventory);
		if(toolStack == null || toolStack.isEmpty() || !(toolStack.getItem() instanceof IDynamicTool))
			return ItemStack.EMPTY;

		if(additionStack != null && !additionStack.isEmpty() && additionStack.getItem() instanceof  ToolPartItem toolPartItem)
		{
			if(ToolMaterialHelper.getHeadMaterial(toolStack).equals(toolPartItem.getMaterial()))
				return ItemStack.EMPTY;

			ToolNBTHelper.modifyToolStackNBTWithUpgradePartItem(toolStack, toolPartItem);
		}

		return toolStack;
	}

	public static class Type implements RecipeType<ToolUpgradeRecipeSmithing>
	{
		public static final Identifier ID = Alloygery.identifier("alloygery_tool_upgrade_smithing");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends SmithingRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		private Serializer() {} //no op

		@Override
		public SmithingRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new ToolUpgradeRecipeSmithing((SmithingRecipeAccessor) super.read(identifier, jsonObject));
		}

		@Override
		public SmithingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new ToolUpgradeRecipeSmithing((SmithingRecipeAccessor) super.read(identifier, packetByteBuf));
		}
	}
}
