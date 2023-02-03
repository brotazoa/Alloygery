package amorphia.alloygery.content.armor.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.ArmorNBTHelper;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;

public class BaseArmorRecipeShaped extends ShapedRecipe
{
	public BaseArmorRecipeShaped(ShapedRecipe recipe)
	{
		super(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getOutput());
	}

	//apply nbt data
	@Override
	public ItemStack getOutput()
	{
		ItemStack outputStack = super.getOutput();
		if(outputStack.getItem() instanceof IDynamicArmor dynamicArmor)
			ArmorNBTHelper.addAlloygeryNBTToArmorStack(outputStack, ArmorNBTHelper.createArmorNBT(dynamicArmor.getDefaultBaseMaterial()));

		return outputStack;
	}

	public static class Type implements RecipeType<BaseArmorRecipeShaped>
	{
		public static final Identifier ID = Alloygery.identifier("base_armor_shaped");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends ShapedRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		protected Serializer() {}

		@Override
		public ShapedRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			return new BaseArmorRecipeShaped(super.read(identifier, jsonObject));
		}

		@Override
		public ShapedRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new BaseArmorRecipeShaped(super.read(identifier, packetByteBuf));
		}
	}
}
