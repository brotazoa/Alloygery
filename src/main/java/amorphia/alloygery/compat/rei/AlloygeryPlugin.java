package amorphia.alloygery.compat.rei;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.machines.recipe.AlloyingRecipe;
import amorphia.alloygery.content.machines.recipe.BlastAlloyingRecipe;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import amorphia.alloygery.content.tools.item.part.ToolUpgradeType;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.entry.EntryRegistry;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
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

		registry.addWorkstations(ALLOYING_CATEGORY, EntryStacks.of(MachineBlockRegistry.ALLOY_KILN));
		registry.addWorkstations(BLAST_ALLOYING_CATEGORY, EntryStacks.of(MachineBlockRegistry.BLAST_ALLOY_KILN));
		registry.addWorkstations(SMITHING_ANVIL_CATEGORY, EntryStacks.of(MachineBlockRegistry.SMITHING_ANVIL));
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
		registry.removeEntry(EntryStacks.of(new ItemStack(Alloygery.ALLOYGERY_TAB_ITEM)));

		final boolean showTools = AlloygeryConfig.showDynamicToolsInRecipeViewer.getValue();
		if(!showTools)
			registry.removeEntryIf(entryStack -> entryStack.getValue() instanceof ItemStack itemStack && itemStack.getItem() instanceof IDynamicTool);

		final boolean showArmors = AlloygeryConfig.showDynamicArmorsInRecipeViewer.getValue();
		if(!showArmors)
			registry.removeEntryIf(entryStack -> entryStack.getValue() instanceof ItemStack itemStack && itemStack.getItem() instanceof IDynamicArmor && itemStack.getItem().getGroup() == null);
	}
}
