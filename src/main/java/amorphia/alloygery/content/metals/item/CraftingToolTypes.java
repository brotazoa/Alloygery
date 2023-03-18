package amorphia.alloygery.content.metals.item;

import java.util.Arrays;
import java.util.Locale;

public enum CraftingToolTypes
{
	HAMMER,
	CHISEL,
	KNIFE,
	SAW,
	WRENCH,
	;

	public static CraftingToolTypes[] VALUES_CACHE = CraftingToolTypes.values();

	public static CraftingToolTypes getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(value -> value.getName().equals(name.toLowerCase(Locale.ROOT)) || value.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
