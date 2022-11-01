package amorphia.alloygery.content.armor.property;

import amorphia.alloygery.content.armor.item.ArmorPartType;

public record ArmorProperty(ArmorPartType partType, ArmorPropertyType type, ArmorPropertyOperation operation, float value) {}
