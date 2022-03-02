package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryPickaxeItem extends PickaxeItem
{
	public static final List<AlloygeryPickaxeItem> PICKAXE_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public AlloygeryPickaxeItem(AlloygeryMaterial material, Item.Settings settings)
	{
		super(new DynamicToolMaterial(material), 1, -2.8f, settings);
		this.alloygeryMaterial = material;
		PICKAXE_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
