package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import net.minecraft.item.Item;

public class ToolBindingItem extends ToolPartItem
{
	public ToolBindingItem(AlloygeryMaterial material)
	{
		this(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material);
	}

	public ToolBindingItem(Settings settings, AlloygeryMaterial material)
	{
		super(settings, material, ToolPartType.BINDING);
	}
}
