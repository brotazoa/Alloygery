package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryHoeItem extends HoeItem
{
	public static final List<AlloygeryHoeItem> HOE_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public AlloygeryHoeItem(AlloygeryMaterial material)
	{
		super(new DynamicToolMaterial(material), -2, -2.8f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
		this.alloygeryMaterial = material;
		HOE_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
