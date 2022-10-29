package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.content.tools.material.ToolMaterial;

public class ToolHeadItem extends PartItem implements IToolPartHead
{
	protected ToolType toolType;

	public ToolHeadItem(Settings settings, ToolMaterial material, ToolType toolType)
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
