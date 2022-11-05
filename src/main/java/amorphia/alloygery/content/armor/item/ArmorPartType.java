package amorphia.alloygery.content.armor.item;

import java.util.Arrays;
import java.util.Locale;

public enum ArmorPartType
{
	BASE, //chain, leather, wool, backing layer
	LIGHT, //brigadine
	HEAVY, //plate / scale
	UPGRADE,
	;

	public static final ArmorPartType[] VALUES_CACHE = ArmorPartType.values();

	public static ArmorPartType getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(v -> v.getName().equals(name.toLowerCase(Locale.ROOT)) || v.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
