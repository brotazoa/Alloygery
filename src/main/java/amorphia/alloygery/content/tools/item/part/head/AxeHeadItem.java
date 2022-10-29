package amorphia.alloygery.content.tools.item.part.head;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.item.part.ToolHeadItem;
import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.tools.material.ToolMaterial;
import net.minecraft.item.Item;

public class AxeHeadItem extends ToolHeadItem
{
	public AxeHeadItem(ToolMaterial material)
	{
		this(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material);
	}

	public AxeHeadItem(Settings settings, ToolMaterial material)
	{
		super(settings, material, ToolType.AXE);
	}
}
