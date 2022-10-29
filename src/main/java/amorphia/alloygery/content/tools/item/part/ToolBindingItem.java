package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.material.ToolMaterial;
import net.minecraft.item.Item;

public class ToolBindingItem extends PartItem
{
	public ToolBindingItem(ToolMaterial material)
	{
		this(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material);
	}

	public ToolBindingItem(Settings settings, ToolMaterial material)
	{
		super(settings, material, ToolPartType.BINDING);
	}
}
