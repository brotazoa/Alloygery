package amorphia.alloygery.compat.emi;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.machines.recipe.AlloyingRecipe;
import amorphia.alloygery.content.machines.recipe.BlastAlloyingRecipe;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import amorphia.alloygery.content.machines.registry.MachineRecipeRegistry;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;

public class AlloygeryEmiPlugin implements EmiPlugin
{
	public static final EmiStack ALLOY_KILN = EmiStack.of(MachineBlockRegistry.ALLOY_KILN);
	public static final EmiStack BLAST_ALLOY_KILN = EmiStack.of(MachineBlockRegistry.BLAST_ALLOY_KILN);
	public static final EmiStack SMITHING_ANVIL = EmiStack.of(MachineBlockRegistry.SMITHING_ANVIL);

	public static final EmiRecipeCategory ALLOYING_CATEGORY = new EmiRecipeCategory(Alloygery.identifier("emi_alloying_category"), ALLOY_KILN);
	public static final EmiRecipeCategory BLAST_ALLOYING_CATEGORY = new EmiRecipeCategory(Alloygery.identifier("emi_blast_alloying_category"), BLAST_ALLOY_KILN);
	public static final EmiRecipeCategory SMITHING_ANVIL_CATEGORY = new EmiRecipeCategory(Alloygery.identifier("emi_smithing_anvil_category"), SMITHING_ANVIL);

	@Override
	public void register(EmiRegistry registry)
	{
		registry.addCategory(ALLOYING_CATEGORY);
		registry.addCategory(BLAST_ALLOYING_CATEGORY);
		registry.addCategory(SMITHING_ANVIL_CATEGORY);

		registry.addWorkstation(ALLOYING_CATEGORY, ALLOY_KILN);
		registry.addWorkstation(BLAST_ALLOYING_CATEGORY, BLAST_ALLOY_KILN);
		registry.addWorkstation(SMITHING_ANVIL_CATEGORY, SMITHING_ANVIL);

		for(AlloyingRecipe recipe : registry.getRecipeManager().listAllOfType(MachineRecipeRegistry.ALLOYING_RECIPE_TYPE))
		{
			registry.addRecipe(new AlloyingEmiRecipe(recipe));
		}

		for(BlastAlloyingRecipe recipe : registry.getRecipeManager().listAllOfType(MachineRecipeRegistry.BLAST_ALLOYING_RECIPE_TYPE))
		{
			registry.addRecipe(new BlastAlloyingEmiRecipe(recipe));
		}

		for(SmithingAnvilRecipe recipe : registry.getRecipeManager().listAllOfType(MachineRecipeRegistry.SMITHING_ANVIL_RECIPE_TYPE))
		{
			registry.addRecipe(new SmithingAnvilEmiRecipe(recipe));
		}

		final boolean showTools = AlloygeryConfig.showDynamicToolsInRecipeViewer.getValue();
		if(!showTools)
			registry.removeEmiStacks(entryStack -> entryStack.getItemStack().getItem() instanceof IDynamicTool && entryStack.getItemStack().getItem().getGroup() == null);

		final boolean showArmors = AlloygeryConfig.showDynamicArmorsInRecipeViewer.getValue();
		if(!showArmors)
			registry.removeEmiStacks(entryStack -> entryStack.getItemStack().getItem() instanceof IDynamicArmor && entryStack.getItemStack().getItem().getGroup() == null);
	}
}
