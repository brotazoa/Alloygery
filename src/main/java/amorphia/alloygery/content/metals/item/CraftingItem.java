package amorphia.alloygery.content.metals.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import com.google.common.collect.Lists;
import net.minecraft.item.Item;

import java.util.List;

public class CraftingItem extends Item
{
	public static final List<CraftingItem> CRAFTING_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial material;

	public CraftingItem(AlloygeryMaterial material)
	{
		this(material, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP));
	}

	public CraftingItem(AlloygeryMaterial material, Item.Settings settings)
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
