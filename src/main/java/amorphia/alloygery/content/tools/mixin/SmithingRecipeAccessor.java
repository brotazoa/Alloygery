package amorphia.alloygery.content.tools.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SmithingRecipe.class)
public interface SmithingRecipeAccessor
{
	@Accessor("base")
	Ingredient alloygery_getBase();

	@Accessor("addition")
	Ingredient alloygery_getAddition();

	@Accessor("result")
	ItemStack alloygery_getResult();

	@Accessor("id")
	Identifier alloygery_getId();
}
