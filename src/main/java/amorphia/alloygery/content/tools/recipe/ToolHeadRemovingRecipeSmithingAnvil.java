package amorphia.alloygery.content.tools.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class ToolHeadRemovingRecipeSmithingAnvil extends SmithingAnvilRecipe
{
	public ToolHeadRemovingRecipeSmithingAnvil(SmithingAnvilRecipe recipe)
	{
		super(recipe.getId(), recipe.getAddition(), recipe.getMaterial(), recipe.getMaterialCost(), recipe.getMinimumCraftingTier(), recipe.getOutput());
	}

	@Override
	public ItemStack craft(Inventory inventory)
	{
		ItemStack toolStack = inventory.getStack(1);
		if(toolStack == null || toolStack.isEmpty())
			return ItemStack.EMPTY;

		NbtCompound compound = toolStack.getOrCreateNbt();
		if(compound == null || compound.isEmpty() || !ToolNBTHelper.isAlloygeryDataNBT(compound))
			return ItemStack.EMPTY;

		NbtCompound toolCompound = ToolNBTHelper.getAlloygeryDataNBT(compound);
		if(!ToolNBTHelper.isToolNBT(toolCompound))
			return ItemStack.EMPTY;

		NbtCompound headCompound = ToolNBTHelper.getHeadPartNBTFromToolNBT(toolCompound);
		if (!ToolNBTHelper.isToolHeadPartNBT(headCompound))
			return ItemStack.EMPTY;

		ItemStack headStack = ToolNBTHelper.createToolPartItemStackFromNBT(headCompound);
		if(headStack == null || headStack.isEmpty())
			return ItemStack.EMPTY;

		EnchantmentHelper.set(EnchantmentHelper.get(toolStack), headStack);
		return headStack;
	}

	public static class Type implements RecipeType<ToolHeadRemovingRecipeSmithingAnvil>
	{
		public static final Identifier ID = Alloygery.identifier("alloygery_tool_head_removing_smithing_anvil");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends SmithingAnvilRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		private Serializer() {} //no op

		@Override
		public SmithingAnvilRecipe read(Identifier id, PacketByteBuf buf)
		{
			return new ToolHeadRemovingRecipeSmithingAnvil(super.read(id, buf));
		}

		@Override
		public SmithingAnvilRecipe read(Identifier id, JsonObject json)
		{
			return new ToolHeadRemovingRecipeSmithingAnvil(super.read(id, json));
		}
	}
}
