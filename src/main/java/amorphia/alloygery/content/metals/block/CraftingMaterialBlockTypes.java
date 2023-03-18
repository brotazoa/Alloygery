package amorphia.alloygery.content.metals.block;

import java.util.Arrays;
import java.util.Locale;

public enum CraftingMaterialBlockTypes
{
	BLOCK,
	STAIR,
	SLOPE,
	SLAB,
	FENCE,
	FENCE_GATE,
	RAW,
	;

	public static final CraftingMaterialBlockTypes[] VALUES_CACHE = CraftingMaterialBlockTypes.values();

	public static CraftingMaterialBlockTypes getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(value -> value.getName().equals(name.toLowerCase(Locale.ROOT)) || value.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
