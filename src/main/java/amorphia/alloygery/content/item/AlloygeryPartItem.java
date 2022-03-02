package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.item.Item;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryPartItem extends Item
{
	public static final List<AlloygeryPartItem> PART_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial material;

	public AlloygeryPartItem(AlloygeryMaterial material, Item.Settings settings)
	{
		super(settings);
		this.material = material;
		PART_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return material;
	}
}
