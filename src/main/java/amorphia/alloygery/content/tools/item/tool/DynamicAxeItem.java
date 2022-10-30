package amorphia.alloygery.content.tools.item.tool;

import amorphia.alloygery.content.tools.ToolDescriptionHelper;
import amorphia.alloygery.content.tools.ToolMaterialHelper;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.tools.item.part.ToolUpgradeType;
import amorphia.alloygery.content.tools.material.ToolMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class DynamicAxeItem extends AxeItem implements IDynamicTool
{
	public static final float ATTACK_DAMAGE = 5.0f;
	public static final float ATTACK_SPEED = -3.0f;

	protected final ToolUpgradeType upgradeType;

	public DynamicAxeItem()
	{
		this(ToolUpgradeType.NONE);
	}

	public DynamicAxeItem(ToolUpgradeType upgradeType)
	{
		this(new Settings(), upgradeType);
	}

	public DynamicAxeItem(Settings settings, ToolUpgradeType upgradeType)
	{
		super(EmptyToolMaterial.INSTANCE, ATTACK_DAMAGE, ATTACK_SPEED, settings);
		this.upgradeType = upgradeType;
		TOOL_ITEMS.add(this);
	}

	@Override
	public ToolUpgradeType getToolUpgradeType()
	{
		return this.upgradeType;
	}

	@Override
	public ToolType getToolType()
	{
		return ToolType.AXE;
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
