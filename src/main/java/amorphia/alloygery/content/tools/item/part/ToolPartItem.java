package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ToolPartItem extends Item implements IToolPart
{
	protected AlloygeryToolMaterial material;
	protected ToolPartType toolPartType;

	public ToolPartItem(Settings settings, AlloygeryToolMaterial material, ToolPartType toolPartType)
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
	public AlloygeryToolMaterial getMaterial()
	{
		return this.material;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		writeTooltip(tooltip);
	}
}
