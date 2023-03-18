package amorphia.alloygery.content.metals.item;

import java.util.Arrays;
import java.util.Locale;

public enum CraftingMaterialVariantTypes
{
	DULL,
	NORMAL,
	SHINY,
	;
	
	public static final CraftingMaterialVariantTypes[] VALUES_CACHE = CraftingMaterialVariantTypes.values();
	
	public static CraftingMaterialVariantTypes getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(value -> value.getName().equals(name.toLowerCase(Locale.ROOT)) || value.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}
	
	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
