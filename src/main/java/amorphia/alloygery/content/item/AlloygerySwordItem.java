package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygerySwordItem extends SwordItem
{
	public static final List<AlloygerySwordItem> SWORD_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public AlloygerySwordItem(AlloygeryMaterial material, Item.Settings settings)
	{
		super(new DynamicToolMaterial(material), 3, -2.4f, settings);
		this.alloygeryMaterial = material;
		SWORD_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
