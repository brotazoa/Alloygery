package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.metals.item.CraftingTool;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import amorphia.alloygery.content.tools.mixin.SmithingRecipeAccessor;
import amorphia.alloygery.registry.ModTags;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class ToolUpgradeRemovingRecipeSmithing extends SmithingRecipe
{
	public ToolUpgradeRemovingRecipeSmithing(SmithingRecipeAccessor recipe)
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

		if(additionStack == null || additionStack.isEmpty() || !(additionStack.getItem() instanceof CraftingTool))
			return ItemStack.EMPTY;

		ItemStack toolStack = super.craft(inventory);
		ToolNBTHelper.removeUpgradeNBTFromToolStackNBT(toolStack);
		return toolStack;
	}

	@Override
	public DefaultedList<ItemStack> getRemainder(Inventory inventory)
	{
		DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

		for(int i = 0; i < defaultedList.size(); i++)
		{
			ItemStack stack = inventory.getStack(i).copy();
			if(stack != null && !stack.isEmpty() && stack.isIn(ModTags.CRAFTING_TOOLS))
			{
				if (stack.isDamageable())
				{
					stack.setDamage(stack.getDamage() + 1);
					if(stack.getDamage() > stack.getMaxDamage())
						stack = ItemStack.EMPTY;

				}

				defaultedList.set(i, stack);
			}
		}

		return defaultedList;
	}

	public static class Type implements RecipeType<ToolUpgradeRemovingRecipeSmithing>
	{
		public static final Identifier ID = Alloygery.identifier("alloygery_tool_upgrade_removing_smithing");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends SmithingRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		private Serializer() {} //no op

		@Override
		public SmithingRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new ToolUpgradeRemovingRecipeSmithing((SmithingRecipeAccessor) super.read(identifier, packetByteBuf));
		}

		@Override
		public SmithingRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new ToolUpgradeRemovingRecipeSmithing((SmithingRecipeAccessor) super.read(identifier, jsonObject));
		}
	}
}
