package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryAxeItem extends AxeItem
{
	public static final List<AlloygeryAxeItem> AXE_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public AlloygeryAxeItem(AlloygeryMaterial material)
	{
		super(new DynamicToolMaterial(material), 5.0f, -3.0f, new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_GEAR));
		this.alloygeryMaterial = material;
		AXE_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
