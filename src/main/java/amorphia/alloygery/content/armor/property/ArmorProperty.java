package amorphia.alloygery.content.armor.property;

import amorphia.alloygery.content.armor.item.ArmorLayer;

public record ArmorProperty(ArmorLayer layer, ArmorPropertyType type, ArmorPropertyOperation operation, float value) {}
