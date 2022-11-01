package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.content.materials.AlloygeryMaterial;

public class ToolHeadItem extends ToolPartItem implements IToolPartHead
{
	protected ToolType toolType;

	public ToolHeadItem(Settings settings, AlloygeryMaterial material, ToolType toolType)
	{
		super(settings, material, ToolPartType.HEAD);
		this.toolType = toolType;
	}

	@Override
	public ToolType getToolType()
	{
		return this.toolType;
	}
}
