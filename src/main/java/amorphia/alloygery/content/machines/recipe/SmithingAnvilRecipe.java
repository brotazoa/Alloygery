package amorphia.alloygery.content.machines.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import amorphia.alloygery.content.metals.item.CraftingTool;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;


public class SmithingAnvilRecipe implements Recipe<Inventory>
{
	protected final Ingredient addition;
	protected final Ingredient material;
	protected final int materialCount;
	protected final int minimumCraftingTier;
	protected final ItemStack output;
	protected final Identifier id;

	public SmithingAnvilRecipe(Identifier id, Ingredient addition, Ingredient material, int materialCount, int minimumCraftingTier, ItemStack output)
	{
		this.id = id;
		this.addition = addition;
		this.material = material;
		this.materialCount = materialCount;
		this.minimumCraftingTier = minimumCraftingTier;
		this.output = output;
	}

	@Override
	public boolean matches(Inventory inventory, World world)
	{
		return !inventory.getStack(0).isEmpty() && this.addition.test(inventory.getStack(0)) && !inventory.getStack(1).isEmpty() && this.material.test(inventory.getStack(1));
	}

	public boolean canAfford(Inventory inventory, World world)
	{
		return this.matches(inventory, world) && inventory.getStack(1).getCount() >= materialCount;
	}

	public boolean suitableCraftingTier(Inventory inventory, World world)
	{
		if(this.minimumCraftingTier == -1)
			return true;

		if(!this.matches(inventory, world))
			return false;

		ItemStack toolStack = inventory.getStack(0);
		if(toolStack.getItem() instanceof CraftingTool toolItem)
		{
			return toolItem.getTier() >= this.minimumCraftingTier;
		}

		return false;
	}

	@Override
	public ItemStack craft(Inventory inventory)
	{
		return output.copy();
	}

	public void onCraft(Inventory inventory)
	{
		ItemStack additionStack = inventory.getStack(0);
		if(additionStack != null && !additionStack.isEmpty() && additionStack.isDamageable())
		{
			additionStack.setDamage(additionStack.getDamage() + 1);
			if(additionStack.getDamage() > additionStack.getMaxDamage())
				inventory.setStack(0, ItemStack.EMPTY);
		}
		inventory.getStack(1).decrement(this.materialCount);
	}

	@Override
	public DefaultedList<Ingredient> getIngredients()
	{
		return DefaultedList.copyOf(Ingredient.EMPTY, addition, material);
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

	public Ingredient getAddition()
	{
		return addition;
	}

	public Ingredient getMaterial()
	{
		return material;
	}

	public int getMinimumCraftingTier()
	{
		return minimumCraftingTier;
	}

	public int getMaterialCost()
	{
		return this.materialCount;
	}

	@Override
	public ItemStack createIcon()
	{
		return new ItemStack(MachineBlockRegistry.SMITHING_ANVIL);
	}

	@Override
	public boolean isIgnoredInRecipeBook()
	{
		return true;
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
		protected Serializer(){}

		public static Serializer INSTANCE = new Serializer();

		@Override
		public SmithingAnvilRecipe read(Identifier id, JsonObject json)
		{
			Ingredient hammer = Ingredient.fromJson(json.get("crafting_tool"));
			Ingredient material = Ingredient.fromJson(json.get("material"));

			if(hammer.isEmpty()) throw new JsonSyntaxException("Addition ingredient is empty");
			if(material.isEmpty()) throw new JsonSyntaxException("Material ingredient is empty");

			final int count = JsonHelper.getInt(json,"material_cost", 1);
			final int tier = JsonHelper.getInt(json, "minimum_crafting_tier", -1);
			ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));

			if(output.isEmpty()) throw new JsonSyntaxException("Result stack is empty");

			return new SmithingAnvilRecipe(id, hammer, material, count, tier, output);
		}

		@Override
		public SmithingAnvilRecipe read(Identifier id, PacketByteBuf buf)
		{
			Ingredient craftingTool = Ingredient.fromPacket(buf);
			Ingredient material = Ingredient.fromPacket(buf);
			final int count = buf.readInt();
			final int tier = buf.readInt();
			ItemStack output = buf.readItemStack();
			return new SmithingAnvilRecipe(id, craftingTool, material, count, tier, output);
		}

		@Override
		public void write(PacketByteBuf buf, SmithingAnvilRecipe recipe)
		{
			recipe.addition.write(buf);
			recipe.material.write(buf);
			buf.writeInt(recipe.materialCount);
			buf.writeInt(recipe.minimumCraftingTier);
			buf.writeItemStack(recipe.output);
		}
	}
}
