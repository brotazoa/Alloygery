package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.part.ToolBindingItem;
import amorphia.alloygery.content.tools.item.part.ToolHandleItem;
import amorphia.alloygery.content.tools.item.part.ToolPartItem;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import amorphia.alloygery.content.tools.registry.ToolItemRegistry;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;

public class SimpleToolRecipeShaped extends ShapedRecipe
{
	protected final Ingredient headPart;
	protected final Ingredient bindingPart;
	protected final Ingredient handlePart;

	public SimpleToolRecipeShaped(ShapedRecipe recipe, Ingredient headPart, Ingredient bindingPart, Ingredient handlePart)
	{
		super(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getOutput());
		this.headPart = headPart;
		this.bindingPart = bindingPart;
		this.handlePart = handlePart;
	}

	@Override
	public boolean isIgnoredInRecipeBook()
	{
		return false;
	}

	@Override
	public ItemStack getOutput()
	{
		return super.getOutput().getItem().getDefaultStack();
	}

	@Override
	public ItemStack craft(CraftingInventory craftingInventory)
	{
		ItemStack toolStack = super.getOutput().copy();
		if(toolStack == null || toolStack.isEmpty() || !(toolStack.getItem() instanceof IDynamicTool))
			return ItemStack.EMPTY;

		ItemStack bindingStack = ItemStack.EMPTY;
		ItemStack handleStack = ItemStack.EMPTY;

		for(int slot = 0; slot < craftingInventory.size(); slot++)
		{
			if(craftingInventory.getStack(slot) == null || craftingInventory.getStack(slot).isEmpty())
				continue;

			if(craftingInventory.getStack(slot).getItem() instanceof ToolBindingItem)
				bindingStack = craftingInventory.getStack(slot);

			if(craftingInventory.getStack(slot).getItem() instanceof ToolHandleItem)
				handleStack = craftingInventory.getStack(slot);
		}

		if(headPart.getMatchingStacks().length == 0)
			return ItemStack.EMPTY;

		Item head = headPart.getMatchingStacks()[0].getItem();
		Item binding = bindingStack.isEmpty() ? null : bindingStack.getItem();
		Item handle = handleStack.isEmpty() ? handlePart.isEmpty() ? ToolItemRegistry.ITEMS.get("wooden_handle").getDefaultStack().getItem() : handlePart.getMatchingStacks()[0].getItem() : handleStack.getItem();

		if(head == null)
			return ItemStack.EMPTY;

		ToolNBTHelper.addAlloygeryNBTToToolStack(toolStack, ToolNBTHelper.createToolNBTFromToolPartItems((ToolPartItem) head, (ToolPartItem) binding, (ToolPartItem) handle));
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
			Ingredient head = Ingredient.fromJson(jsonObject.get("substitute_head_part"));
			Ingredient binding = jsonObject.has("substitute_binding_part") ? Ingredient.fromJson(jsonObject.get("substitute_binding_part")) : Ingredient.EMPTY;
			Ingredient handle = jsonObject.has("substitute_handle_part") ? Ingredient.fromJson(jsonObject.get("substitute_handle_part")) : Ingredient.EMPTY;
			return new SimpleToolRecipeShaped(super.read(identifier, jsonObject), head, binding, handle);
		}

		@Override
		public ShapedRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new SimpleToolRecipeShaped(super.read(identifier, packetByteBuf), Ingredient.fromPacket(packetByteBuf), Ingredient.fromPacket(packetByteBuf), Ingredient.fromPacket(packetByteBuf));
		}

		@Override
		public void write(PacketByteBuf packetByteBuf, ShapedRecipe shapedRecipe)
		{
			super.write(packetByteBuf, shapedRecipe);
			((SimpleToolRecipeShaped) shapedRecipe).headPart.write(packetByteBuf);
			((SimpleToolRecipeShaped) shapedRecipe).bindingPart.write(packetByteBuf);
			((SimpleToolRecipeShaped) shapedRecipe).handlePart.write(packetByteBuf);
		}
	}
}
