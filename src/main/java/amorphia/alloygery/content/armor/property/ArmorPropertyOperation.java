package amorphia.alloygery.content.armor.property;

import java.util.Arrays;
import java.util.Locale;

public enum ArmorPropertyOperation
{
	//order matters for tooltips
	BASE,
	ADDITION,
	MULTIPLY_BASE,
	MULTIPLY_TOTAL;

	public static final ArmorPropertyOperation[] VALUES_CACHE = ArmorPropertyOperation.values();

	public static ArmorPropertyOperation getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(v -> v.getName().equals(name.toUpperCase(Locale.ROOT)) || v.getName().equals(name.toLowerCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return name().toLowerCase(Locale.ROOT);
	}
}
