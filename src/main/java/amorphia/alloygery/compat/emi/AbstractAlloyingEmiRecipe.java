package amorphia.alloygery.compat.emi;

import amorphia.alloygery.content.machines.recipe.AbstractAlloyingRecipe;
import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractAlloyingEmiRecipe implements EmiRecipe
{
	private final Identifier id;
	private final List<EmiIngredient> inputs;
	private final List<EmiStack> output;

	public AbstractAlloyingEmiRecipe(AbstractAlloyingRecipe recipe)
	{
		this.id = recipe.getId();
		this.inputs = Lists.newArrayList();
		recipe.getIngredients().forEach(ingredient -> inputs.add(EmiIngredient.of(ingredient)));
		this.output = Lists.newArrayList(EmiStack.of(recipe.getOutput()));
	}

	@Override
	public @Nullable Identifier getId()
	{
		return this.id;
	}

	@Override
	public List<EmiIngredient> getInputs()
	{
		return this.inputs;
	}

	@Override
	public List<EmiStack> getOutputs()
	{
		return this.output;
	}

	@Override
	public int getDisplayWidth()
	{
		return 108;
	}

	@Override
	public int getDisplayHeight()
	{
		return 36;
	}

	@Override
	public void addWidgets(WidgetHolder widgets)
	{
		widgets.addSlot(inputs.get(0), 0, 0);
		widgets.addSlot(inputs.size() > 1 ? inputs.get(1) : EmiIngredient.of(Ingredient.EMPTY), 18, 0);
		widgets.addSlot(inputs.size() > 2 ? inputs.get(2) : EmiIngredient.of(Ingredient.EMPTY), 0, 18);
		widgets.addSlot(inputs.size() > 3 ? inputs.get(3) : EmiIngredient.of(Ingredient.EMPTY), 18, 18);

		widgets.addTexture(EmiTexture.EMPTY_ARROW, 54, 8);

		widgets.addSlot(output.get(0), 90, 8).recipeContext(this);
	}
}
