package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.content.tools.material.ToolMaterial;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class PartItem extends Item implements IToolPart
{
	protected ToolMaterial material;
	protected ToolPartType toolPartType;

	public PartItem(Settings settings, ToolMaterial material, ToolPartType toolPartType)
	{
		super(settings);
		this.material = material;
		this.toolPartType = toolPartType;

		IToolPart.TOOL_PART_ITEMS.add(this);
	}

	@Override
	public ToolPartType getToolPartType()
	{
		return this.toolPartType;
	}

	@Override
	public ToolMaterial getMaterial()
	{
		return this.material;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		writeTooltip(tooltip);
	}
}
