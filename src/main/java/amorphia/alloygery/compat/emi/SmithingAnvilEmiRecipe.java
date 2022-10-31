package amorphia.alloygery.compat.emi;

import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.content.machines.screen.SmithingAnvilScreen;
import com.google.common.collect.Lists;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SmithingAnvilEmiRecipe implements EmiRecipe
{
	private final Identifier id;
	private final List<EmiIngredient> inputs;
	private final List<EmiStack> output;

	public SmithingAnvilEmiRecipe(SmithingAnvilRecipe recipe)
	{
		this.id = recipe.getId();
		inputs = List.of(EmiIngredient.of(recipe.getIngredients().get(0)), EmiIngredient.of(recipe.getIngredients().get(1)));
		output = List.of(EmiStack.of(recipe.getOutput()));
	}

	@Override
	public EmiRecipeCategory getCategory()
	{
		return AlloygeryEmiPlugin.SMITHING_ANVIL_CATEGORY;
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
		return 75;
	}

	@Override
	public int getDisplayHeight()
	{
		return 56;
	}

	@Override
	public void addWidgets(WidgetHolder widgets)
	{
		widgets.addSlot(inputs.get(0), 0, 0);
		widgets.addSlot(inputs.get(1), 0, 38);

		widgets.addTexture(SmithingAnvilScreen.TEXTURE, 0, 18, 16, 20, 20, 32, 16, 20, 256, 256);

		widgets.addTexture(EmiTexture.EMPTY_ARROW, 20, 19);

		widgets.addSlot(output.get(0), 57, 19).recipeContext(this);
	}
}
