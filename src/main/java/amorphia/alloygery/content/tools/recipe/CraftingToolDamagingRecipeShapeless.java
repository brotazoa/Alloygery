package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.machines.registry.MachineTagRegistry;
import com.google.gson.JsonObject;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class CraftingToolDamagingRecipeShapeless extends ShapelessRecipe
{
	public CraftingToolDamagingRecipeShapeless(ShapelessRecipe recipe)
	{
		super(recipe.getId(), recipe.getGroup(), recipe.getOutput(), recipe.getIngredients());
	}

	@Override
	public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory)
	{
		DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

		for(int i = 0; i < defaultedList.size(); i++)
		{
			ItemStack stack = inventory.getStack(i).copy();
			if(stack != null && !stack.isEmpty() && stack.isIn(MachineTagRegistry.CRAFTING_TOOLS))
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

	public static class Type implements RecipeType<CraftingToolDamagingRecipeShapeless>
	{
		public static final Identifier ID = Alloygery.identifier("crafting_tool_damaging_shapeless");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends ShapelessRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		protected Serializer() {}

		@Override
		public ShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new CraftingToolDamagingRecipeShapeless(super.read(identifier, packetByteBuf));
		}

		@Override
		public ShapelessRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new CraftingToolDamagingRecipeShapeless(super.read(identifier, jsonObject));
		}
	}
}
