package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;

public class ToolUpgradePartItem extends ToolPartItem
{
	public ToolUpgradePartItem(AlloygeryToolMaterial material)
	{
		this(new Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, ToolPartType.UPGRADE);
	}

	public ToolUpgradePartItem(Settings settings, AlloygeryToolMaterial material, ToolPartType toolPartType)
	{
		super(settings, material, toolPartType);
	}
}
