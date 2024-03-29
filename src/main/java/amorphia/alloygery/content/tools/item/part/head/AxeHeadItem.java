package amorphia.alloygery.content.tools.item.part.head;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.item.part.ToolHeadItem;
import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import net.minecraft.item.Item;

public class AxeHeadItem extends ToolHeadItem
{
	public AxeHeadItem(AlloygeryToolMaterial material)
	{
		this(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material);
	}

	public AxeHeadItem(Settings settings, AlloygeryToolMaterial material)
	{
		super(settings, material, ToolType.AXE);
	}
}
