package amorphia.alloygery.content.armor.item;

import amorphia.alloygery.content.armor.property.ArmorProperty;
import amorphia.alloygery.content.materials.AlloygeryMaterial;

import java.util.List;

public interface IArmorPart
{
	ArmorPartType getArmorPartType();

	AlloygeryMaterial getAlloygeryMaterial();
}
