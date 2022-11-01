package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.materials.AlloygeryMaterial;

public class ToolUpgradePartItem extends ToolPartItem
{
	public ToolUpgradePartItem(AlloygeryMaterial material)
	{
		this(new Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, ToolPartType.UPGRADE);
	}

	public ToolUpgradePartItem(Settings settings, AlloygeryMaterial material, ToolPartType toolPartType)
	{
		super(settings, material, toolPartType);
	}
}
