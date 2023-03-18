package amorphia.alloygery.content.armor.item;

import java.util.Arrays;
import java.util.Locale;

public enum ArmorLayer
{
	BASE, //chain, leather, wool
	PLATE, //half plate, full plate
	UPGRADE, //reinforced, guilded, lightened
	;

	public static final ArmorLayer[] VALUES_CACHE = ArmorLayer.values();

	public static ArmorLayer getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(v -> v.getName().equals(name.toLowerCase(Locale.ROOT)) || v.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
