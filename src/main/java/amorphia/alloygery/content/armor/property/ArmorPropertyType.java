package amorphia.alloygery.content.armor.property;

import java.util.Arrays;
import java.util.Locale;

public enum ArmorPropertyType
{
	//order maters for tooltips
	DURABILITY,
	ARMOR,
	TOUGHNESS,
	MOBILITY,
	ENCUMBRANCE,
	ENCHANTABILITY,
	KNOCKBACK_RESISTANCE,
	FIREPROOF,
	PIGLIN_LOVED,
	;

	public static final ArmorPropertyType[] VALUES_CACHE = ArmorPropertyType.values();

	public static ArmorPropertyType getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(v -> v.getName().equals(name.toLowerCase(Locale.ROOT)) || v.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
