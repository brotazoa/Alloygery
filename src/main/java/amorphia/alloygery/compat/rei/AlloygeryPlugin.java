package amorphia.alloygery.compat.rei;

import amorphia.alloygery.content.recipe.AlloyingRecipe;
import amorphia.alloygery.content.recipe.BlastAlloyingRecipe;
import amorphia.alloygery.content.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.registry.ModItems;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.BuiltinEntryTypes;
import me.shedaniel.rei.api.common.entry.type.EntryType;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;

public class AlloygeryPlugin implements REIClientPlugin
{
	public static final CategoryIdentifier<AlloyingDisplay> ALLOYING_CATEGORY = CategoryIdentifier.of(AlloyingRecipe.Type.ID);
	public static final CategoryIdentifier<BlastAlloyingDisplay> BLAST_ALLOYING_CATEGORY = CategoryIdentifier.of(BlastAlloyingRecipe.Type.ID);
	public static final CategoryIdentifier<SmithingAnvilDisplay> SMITHING_ANVIL_CATEGORY = CategoryIdentifier.of(SmithingAnvilRecipe.Type.ID);

	@Override
	public void registerCategories(CategoryRegistry registry)
	{
		registry.add(new AlloyingCategory());
		registry.add(new BlastAlloyingCategory());
		registry.add(new SmithingAnvilCategory());

		registry.addWorkstations(ALLOYING_CATEGORY, EntryStacks.of(ModItems.ALLOY_KILN));
		registry.addWorkstations(BLAST_ALLOYING_CATEGORY, EntryStacks.of(ModItems.BLAST_ALLOY_KILN));
		registry.addWorkstations(SMITHING_ANVIL_CATEGORY, EntryStacks.of(ModItems.SMITHING_ANVIL));
	}

	@Override
	public void registerDisplays(DisplayRegistry registry)
	{
		registry.registerFiller(AlloyingRecipe.class, AlloyingDisplay::new);
		registry.registerFiller(BlastAlloyingRecipe.class, BlastAlloyingDisplay::new);
		registry.registerFiller(SmithingAnvilRecipe.class, SmithingAnvilDisplay::new);
	}

	@Override
	public void registerEntries(EntryRegistry registry)
	{
		registry.removeEntry(EntryStacks.of(new ItemStack(ModItems.BLOCKS_TAB_ITEM)));
		registry.removeEntry(EntryStacks.of(new ItemStack(ModItems.MATERIALS_TAB_ITEM)));
		registry.removeEntry(EntryStacks.of(new ItemStack(ModItems.GEAR_TAB_ITEM)));
		registry.removeEntry(EntryStacks.of(new ItemStack(ModItems.PARTS_TAB_ITEM)));
	}
}
