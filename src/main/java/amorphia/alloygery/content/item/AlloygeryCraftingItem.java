package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.item.Item;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryCraftingItem extends Item
{
	public static final List<AlloygeryCraftingItem> CRAFTING_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial material;

	public AlloygeryCraftingItem(AlloygeryMaterial material)
	{
		super(new Item.Settings().group(Alloygery.ALLOYGERY_GROUP_MATERIALS));
		this.material = material;
		CRAFTING_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return material;
	}
}
