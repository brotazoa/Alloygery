package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class ToolUpgradeRemovingRecipeSmithingAnvil extends SmithingAnvilRecipe
{
	public ToolUpgradeRemovingRecipeSmithingAnvil(SmithingAnvilRecipe recipe)
	{
		super(recipe.getId(), recipe.getAddition(), recipe.getMaterial(), recipe.getMaterialCost(), recipe.getMinimumCraftingTier(), recipe.getOutput());
	}

	@Override
	public ItemStack craft(Inventory inventory)
	{
		ItemStack toolStack = inventory.getStack(1);
		if(toolStack == null || toolStack.isEmpty())
			return ItemStack.EMPTY;

		ItemStack outputStack = super.getOutput().copy();
		if(outputStack == null || outputStack.isEmpty())
			return ItemStack.EMPTY;

		if (outputStack.getItem() instanceof IDynamicTool)
		{
			outputStack.setNbt(toolStack.getOrCreateNbt().copy());
			ToolNBTHelper.removeUpgradeNBTFromToolStackNBT(outputStack);
		}

		return outputStack;
	}

	public static class Type implements RecipeType<ToolUpgradeRemovingRecipeSmithingAnvil>
	{
		public static final Identifier ID = Alloygery.identifier("alloygery_tool_upgrade_removing_smithing_anvil");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends SmithingAnvilRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		private Serializer() {} //no op

		@Override
		public SmithingAnvilRecipe read(Identifier id, JsonObject json)
		{
			return new ToolUpgradeRemovingRecipeSmithingAnvil(super.read(id, json));
		}

		@Override
		public SmithingAnvilRecipe read(Identifier id, PacketByteBuf buf)
		{
			return new ToolUpgradeRemovingRecipeSmithingAnvil(super.read(id, buf));
		}
	}
}
