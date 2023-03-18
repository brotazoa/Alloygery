package amorphia.alloygery.content.metals.item;

import java.util.Arrays;
import java.util.Locale;

public enum CraftingMaterialItemTypes
{
	RAW,
	NUGGET,
	INGOT,
	DOUBLE_INGOT,
	PLATE,
	HEAVY_PLATE,
	BLOCK,
	STAIR,
	SLAB,
	SLOPE,
	FENCE,
	FENCE_GATE,
	RAW_BLOCK,
	;

	public static final CraftingMaterialItemTypes[] VALUES_CACHE = CraftingMaterialItemTypes.values();

	public static CraftingMaterialItemTypes getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(value -> value.getName().equals(name.toLowerCase(Locale.ROOT)) || value.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
