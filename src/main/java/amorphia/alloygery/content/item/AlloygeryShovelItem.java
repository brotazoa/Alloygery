package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryShovelItem extends ShovelItem
{
	public static final List<AlloygeryShovelItem> SHOVEL_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public AlloygeryShovelItem(AlloygeryMaterial material)
	{
		super(new DynamicToolMaterial(material), 1.5f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
		this.alloygeryMaterial = material;
		SHOVEL_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
