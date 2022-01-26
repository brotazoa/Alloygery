package amorphia.alloygery.content.recipe;

import amorphia.alloygery.content.block.entity.AbstractAlloyKilnBlockEntity;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractAlloyingRecipe implements Recipe<AbstractAlloyKilnBlockEntity>
{
	private final Identifier id;
	private final String group;
	private final List<Ingredient> ingredients;
	private final ItemStack output;
	private final float experience;
	private final int smeltingTime;

	public AbstractAlloyingRecipe(Identifier id, String group, List<Ingredient> inputs, ItemStack output, int smeltingTime, float experience)
	{
		this.id = id;
		this.group = group;
		this.ingredients = inputs;
		this.output = output;
		this.experience = experience;
		this.smeltingTime = smeltingTime;
	}

	@Override
	public boolean matches(AbstractAlloyKilnBlockEntity inventory, World world)
	{
		//get a flat list of required items with quantity
		List<ItemStack> inputItems = IntStream.range(0, 4)
				.mapToObj(i -> inventory.getStack(i).copy())
				.filter(itemStack -> !itemStack.isEmpty())
				.collect(Collectors.groupingBy(ItemStack::getItem, Collectors.summingInt(ItemStack::getCount)))
				.entrySet().stream()
				.map(pair -> new ItemStack(pair.getKey(), pair.getValue()))
				.toList();

		//no input items
		if(inputItems.isEmpty())
			return false;

		//check to see if there are enough total ingredients to satisfy recipe
		List<Ingredient> ingredients = new ArrayList<>(this.ingredients);
		Iterator<Ingredient> ingredientIterator = ingredients.iterator();
		while (ingredientIterator.hasNext())
		{
			Ingredient ingredient = ingredientIterator.next();
			for (ItemStack input : inputItems)
			{
				if (!input.isEmpty() && ingredient.test(input))
				{
					input.decrement(1);
					ingredientIterator.remove();
					break;
				}
			}
		}

		//missing inputs
		if(!ingredients.isEmpty())
			return false;

		//test to see if there are extra inputs not in the recipe
		for (ItemStack input : inputItems)
		{
			if(!input.isEmpty() && this.ingredients.stream().noneMatch(ingredient -> ingredient.test(input)))
				return false;
		}

		//inputs match recipe
		return true;
	}

	@Override
	public DefaultedList<Ingredient> getIngredients()
	{
		return DefaultedList.copyOf(Ingredient.EMPTY, ingredients.toArray(new Ingredient[0]));
	}

	@Override
	public ItemStack craft(AbstractAlloyKilnBlockEntity inventory)
	{
		return getOutput();
	}

	@Override
	public boolean fits(int width, int height)
	{
		return width * height >= this.ingredients.size();
	}

	@Override
	public ItemStack getOutput()
	{
		return output.copy();
	}

	@Override
	public Identifier getId()
	{
		return id;
	}

	@Override
	public String getGroup()
	{
		return group;
	}

	public float getExperience()
	{
		return experience;
	}

	public int getSmeltingTime()
	{
		return smeltingTime;
	}

	protected static abstract class Serializer implements RecipeSerializer<AbstractAlloyingRecipe>
	{
		@Override
		public void write(PacketByteBuf buf, AbstractAlloyingRecipe recipe)
		{
			buf.writeString(recipe.getGroup());
			buf.writeCollection(recipe.getIngredients(), ((packetByteBuf, ingredient) -> ingredient.write(packetByteBuf)));
			buf.writeItemStack(recipe.getOutput());
			buf.writeVarInt(recipe.getSmeltingTime());
			buf.writeFloat(recipe.getExperience());
		}

		protected ItemStack getItemStack(JsonObject json)
		{
			final var item = JsonHelper.getItem(json, "id");
			final var count = JsonHelper.getInt(json, "count", 1);
			return new ItemStack(item, count);
		}
	}
}
