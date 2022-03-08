package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryArmorItem extends ArmorItem implements IMaterialItem
{
	public static final List<AlloygeryArmorItem> ARMOR_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public AlloygeryArmorItem(AlloygeryMaterial material, EquipmentSlot slot, Item.Settings settings)
	{
		super(new DynamicArmorMaterial(material), slot, settings);
		this.alloygeryMaterial = material;
		ARMOR_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
