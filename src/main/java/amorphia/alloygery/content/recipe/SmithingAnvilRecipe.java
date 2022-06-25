package amorphia.alloygery.content.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.registry.ModBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import de.siphalor.nbtcrafting.api.RecipeUtil;
import de.siphalor.nbtcrafting.api.nbt.NbtUtil;
import de.siphalor.nbtcrafting.dollar.Dollar;
import de.siphalor.nbtcrafting.dollar.DollarParser;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class SmithingAnvilRecipe implements Recipe<Inventory>
{
	protected final Ingredient hammer;
	protected final Ingredient material;
	protected final int materialCount;
	protected final ItemStack output;
	protected final Identifier id;
	protected final String group;
	protected final Dollar[] resultDollars;

	public SmithingAnvilRecipe(Identifier id, String group, Ingredient hammer, Ingredient material, int materialCount, ItemStack output)
	{
		this.id = id;
		this.group = group;
		this.hammer = hammer;
		this.material = material;
		this.materialCount = materialCount;
		this.output = output;
		this.resultDollars = DollarParser.extractDollars(output.getNbt(), false);
	}

	@Override
	public boolean matches(Inventory inventory, World world)
	{
		return this.hammer.test(inventory.getStack(0)) && this.material.test(inventory.getStack(1));
	}

	public boolean canAfford(Inventory inventory, World world)
	{
		return this.matches(inventory, world) && inventory.getStack(1).getCount() >= materialCount;
	}

	@Override
	public ItemStack craft(Inventory inventory)
	{
		return RecipeUtil.applyDollars(output.copy(), resultDollars, buildDollarReference(inventory));
	}

	@Override
	public DefaultedList<Ingredient> getIngredients()
	{
		return DefaultedList.copyOf(Ingredient.EMPTY, hammer, material);
	}

	@Override
	public DefaultedList<ItemStack> getRemainder(Inventory inventory)
	{
		DefaultedList<ItemStack> stacks = DefaultedList.ofSize(2, ItemStack.EMPTY);
		Map<String, Object> reference = new HashMap<>();
		reference.put("hammer", NbtUtil.getTagOrEmpty(inventory.getStack(0)));
		reference.put("material", NbtUtil.getTagOrEmpty(inventory.getStack(1)));
		stacks.set(0, RecipeUtil.getRemainder(inventory.getStack(0), hammer, reference));
		stacks.set(1, RecipeUtil.getRemainder(inventory.getStack(1), material, reference));
		return stacks;
	}

	public Map<String, Object> buildDollarReference(Inventory inventory)
	{
		return ImmutableMap.of(
				"hammer", NbtUtil.getTagOrEmpty(inventory.getStack(0)),
				"material", NbtUtil.getTagOrEmpty(inventory.getStack(1)));
	}

	@Override
	public boolean fits(int width, int height)
	{
		return width * height >= 2;
	}

	@Override
	public ItemStack getOutput()
	{
		return this.output;
	}

	@Override
	public Identifier getId()
	{
		return this.id;
	}

	@Override
	public String getGroup()
	{
		return this.group;
	}

	public int getMaterialCost()
	{
		return this.materialCount;
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack(ModBlocks.SMITHING_ANVIL);
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

	public static class Type implements RecipeType<SmithingAnvilRecipe>
	{
		private Type(){}

		public static final Identifier ID = Alloygery.identifier("smithing_anvil");
		public static final Type INSTANCE = new Type();
	}

	public static class Serializer implements RecipeSerializer<SmithingAnvilRecipe>
	{
		private Serializer(){}

		public static Serializer INSTANCE = new Serializer();

		@Override
		public SmithingAnvilRecipe read(Identifier id, JsonObject json)
		{
			final String group = JsonHelper.getString(json, "group", "");
			Ingredient hammer = Ingredient.fromJson(json.get("hammer"));
			Ingredient material = Ingredient.fromJson(json.get("material"));
			final int count = JsonHelper.getInt(json,"material_cost", 1);
			ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));
			return new SmithingAnvilRecipe(id, group, hammer, material, count, output);
		}

		@Override
		public SmithingAnvilRecipe read(Identifier id, PacketByteBuf buf)
		{
			final String group = buf.readString();
			Ingredient hammer = Ingredient.fromPacket(buf);
			Ingredient material = Ingredient.fromPacket(buf);
			final int count = buf.readInt();
			ItemStack output = buf.readItemStack();
			return new SmithingAnvilRecipe(id, group, hammer, material, count, output);
		}

		@Override
		public void write(PacketByteBuf buf, SmithingAnvilRecipe recipe)
		{
			buf.writeString(recipe.group);
			recipe.hammer.write(buf);
			recipe.material.write(buf);
			buf.writeInt(recipe.materialCount);
			buf.writeItemStack(recipe.output);
		}
	}
}
