package amorphia.alloygery.content.tools.item.part.head;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.item.part.ToolHeadItem;
import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import net.minecraft.item.Item;

public class AxeHeadItem extends ToolHeadItem
{
	public AxeHeadItem(AlloygeryMaterial material)
	{
		this(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material);
	}

	public AxeHeadItem(Settings settings, AlloygeryMaterial material)
	{
		super(settings, material, ToolType.AXE);
	}
}
