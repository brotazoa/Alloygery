package amorphia.alloygery.content.machines.recipe;

import amorphia.alloygery.Alloygery;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.ArrayList;
import java.util.List;

public class BlastAlloyingRecipe extends AbstractAlloyingRecipe
{
	public BlastAlloyingRecipe(Identifier id, String group, List<Ingredient> inputs, ItemStack output, int smeltingTime, float experience)
	{
		super(id, group, inputs, output, smeltingTime, experience);
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return Serializer.INSTANCE;
	}

	@Override
	public RecipeType<?> getType()
	{
		return Type.INSTANCE;
	}

	public static class Type implements RecipeType<BlastAlloyingRecipe>
	{
		private Type() {}

		public static final Identifier ID = Alloygery.identifier("blast_alloying");
		public static final Type INSTANCE = new Type();
	}

	public static class Serializer extends AbstractAlloyingRecipe.Serializer
	{
		private Serializer() {}

		public static final Serializer INSTANCE = new Serializer();

		@Override
		public AbstractAlloyingRecipe read(Identifier id, JsonObject json)
		{
			final String group = JsonHelper.getString(json, "group", "");
			final var inputs = new ArrayList<Ingredient>();
			JsonHelper.getArray(json, "inputs").forEach(jsonElement -> inputs.add(Ingredient.fromJson(jsonElement)));
			if(inputs.isEmpty()) throw new JsonSyntaxException("Inputs can not be empty");

			final var output = getItemStack(JsonHelper.getObject(json, "output"));

			final int smeltingTime = JsonHelper.getInt(json, "smelting_time");
			final float experience = JsonHelper.getFloat(json, "experience", 0.0f);

			return new BlastAlloyingRecipe(id, group, inputs, output, smeltingTime, experience);
		}

		@Override
		public AbstractAlloyingRecipe read(Identifier id, PacketByteBuf buf)
		{
			final String group = buf.readString(32767);
			final var inputs = buf.readCollection(value -> new ArrayList<>(), Ingredient::fromPacket);
			final var output = buf.readItemStack();
			final int smeltingTime = buf.readVarInt();
			final float experience = buf.readFloat();

			return new BlastAlloyingRecipe(id, group, inputs, output, smeltingTime, experience);
		}
	}
}
