package amorphia.alloygery.content.machines.recipe;

import amorphia.alloygery.Alloygery;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class SmithingAnvilCopyEnchantmentsRecipe extends SmithingAnvilRecipe
{
	public SmithingAnvilCopyEnchantmentsRecipe(SmithingAnvilRecipe recipe)
	{
		super(recipe.getId(), recipe.getAddition(), recipe.getMaterial(), recipe.getMaterialCost(), recipe.getMinimumCraftingTier(), recipe.getOutput());
	}

	@Override
	public ItemStack craft(Inventory inventory)
	{
		ItemStack output = super.craft(inventory);

		ItemStack material = inventory.getStack(1).copy();

		EnchantmentHelper.set(EnchantmentHelper.get(material), output);

		return output;
	}

	public static class Type implements RecipeType<SmithingAnvilCopyEnchantmentsRecipe>
	{
		public static final Identifier ID = Alloygery.identifier("smithing_anvil_copy_enchantments");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends SmithingAnvilRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		protected Serializer() {} //protected no op

		@Override
		public SmithingAnvilRecipe read(Identifier id, JsonObject json)
		{
			return new SmithingAnvilCopyEnchantmentsRecipe(super.read(id, json));
		}

		@Override
		public SmithingAnvilRecipe read(Identifier id, PacketByteBuf buf)
		{
			return new SmithingAnvilCopyEnchantmentsRecipe(super.read(id, buf));
		}
	}
}
