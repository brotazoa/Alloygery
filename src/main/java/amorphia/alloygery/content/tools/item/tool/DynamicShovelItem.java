package amorphia.alloygery.content.tools.item.tool;

import amorphia.alloygery.content.tools.ToolDescriptionHelper;
import amorphia.alloygery.content.tools.ToolMaterialHelper;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.tools.material.ToolMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class DynamicShovelItem extends ShovelItem implements IDynamicTool
{
	public static final float ATTACK_DAMAGE = 1.5f;
	public static final float ATTACK_SPEED = -3.0f;

	public DynamicShovelItem(Settings settings)
	{
		super(EmptyToolMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
		TOOL_ITEMS.add(this);
	}

	@Override
	public ItemStack getDefaultStack()
	{
		ItemStack tool = new ItemStack(this);
		ToolNBTHelper.addAlloygeryNBTToToolStack(tool, ToolNBTHelper.createToolNBTFromMaterials(ToolMaterials.IRON, ToolMaterials.VANILLA_STICK, ToolType.SHOVEL));
		return tool;
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient)
	{
		return ToolMaterialHelper.getRepairIngredientForStack(stack).test(ingredient);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context)
	{
		ToolDescriptionHelper.writeToolDescription(tooltip, stack, context);
	}

	@Override
	public Text getName(ItemStack stack)
	{
		return ToolDescriptionHelper.getToolStackName(stack);
	}

	@Override
	public int getItemBarColor(ItemStack stack)
	{
		return IDynamicTool.getItemBarColor(stack);
	}

	@Override
	public int getItemBarStep(ItemStack stack)
	{
		return IDynamicTool.getItemBarStep(stack);
	}
}
