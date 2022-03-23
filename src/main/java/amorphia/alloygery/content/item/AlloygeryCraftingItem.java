package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.item.Item;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryCraftingItem extends Item implements IMaterialItem
{
	public static final List<AlloygeryCraftingItem> CRAFTING_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial material;

	public AlloygeryCraftingItem(AlloygeryMaterial material, Item.Settings settings)
	{
		super(settings);
		this.material = material;
		CRAFTING_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return material;
	}
}
