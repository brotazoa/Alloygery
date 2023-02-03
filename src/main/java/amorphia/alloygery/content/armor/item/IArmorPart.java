package amorphia.alloygery.content.armor.item;

import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.property.ArmorProperty;
import com.google.common.collect.Lists;

import java.util.List;

public interface IArmorPart
{
	List<ArmorPartItem> ARMOR_PART_ITEMS = Lists.newArrayList();

	ArmorLayer getArmorLayer();

	ArmorType getArmorType();

	AlloygeryArmorMaterial getArmorMaterial();

	default List<ArmorProperty> getArmorProperties()
	{
		return getArmorMaterial().getArmorPropertiesByLayer(getArmorLayer());
	}
}
