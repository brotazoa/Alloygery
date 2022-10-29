package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.AlloygeryNBT;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.part.PartItem;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;

public class SimpleToolRecipeShaped extends ShapedRecipe
{
	protected final Ingredient headPart;
	protected final Ingredient handlePart;

	public SimpleToolRecipeShaped(ShapedRecipe recipe, Ingredient headPart, Ingredient handlePart)
	{
		super(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getOutput());
		this.headPart = headPart;
		this.handlePart = handlePart;
	}

	@Override
	public boolean isIgnoredInRecipeBook()
	{
		return true;
	}

	@Override
	public ItemStack getOutput()
	{
		return ToolNBTHelper.addAlloygeryNBTToToolStack(super.getOutput().copy(), ToolNBTHelper.createToolNBTFromToolPartItems((PartItem) headPart.getMatchingStacks()[0].getItem(), (PartItem) handlePart.getMatchingStacks()[0].getItem()));
	}

	@Override
	public ItemStack craft(CraftingInventory craftingInventory)
	{
		ItemStack toolStack = super.getOutput().copy();
		if(toolStack == null || toolStack.isEmpty() || !(toolStack.getItem() instanceof IDynamicTool))
			return ItemStack.EMPTY;

		//TODO: move to nbt helper
		toolStack.getOrCreateNbt().put(AlloygeryNBT.ALLOYGERY_NBT_IDENTIFIER.getName(), ToolNBTHelper.createToolNBTFromToolPartItems((PartItem) headPart.getMatchingStacks()[0].getItem(), (PartItem) handlePart.getMatchingStacks()[0].getItem()));
		return toolStack;
	}

	public static class Type implements RecipeType<SimpleToolRecipeShaped>
	{
		public static final Identifier ID = Alloygery.identifier("alloygery_simple_tool_shaped");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends ShapedRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		private Serializer() {} //no op

		@Override
		public ShapedRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new SimpleToolRecipeShaped(super.read(identifier, jsonObject), Ingredient.fromJson(jsonObject.get("substitute_head_part")), Ingredient.fromJson(jsonObject.get("substitute_handle_part")));
		}

		@Override
		public ShapedRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new SimpleToolRecipeShaped(super.read(identifier, packetByteBuf), Ingredient.fromPacket(packetByteBuf), Ingredient.fromPacket(packetByteBuf));
		}

		@Override
		public void write(PacketByteBuf packetByteBuf, ShapedRecipe shapedRecipe)
		{
			super.write(packetByteBuf, shapedRecipe);
			((SimpleToolRecipeShaped) shapedRecipe).headPart.write(packetByteBuf);
			((SimpleToolRecipeShaped) shapedRecipe).handlePart.write(packetByteBuf);
		}
	}
}
