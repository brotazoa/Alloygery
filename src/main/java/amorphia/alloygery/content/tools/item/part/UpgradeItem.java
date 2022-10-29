package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.material.ToolMaterial;

public class UpgradeItem extends PartItem
{
	public UpgradeItem(ToolMaterial material)
	{
		this(new Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, ToolPartType.UPGRADE);
	}

	public UpgradeItem(Settings settings, ToolMaterial material, ToolPartType toolPartType)
	{
		super(settings, material, toolPartType);
	}
}
