package amorphia.alloygery.content.tools.item.part;

import java.util.Arrays;
import java.util.Locale;

public enum ToolType
{
	AXE,
	HOE,
	PICKAXE,
	SHOVEL,
	SWORD,
	;

	public static final ToolType[] VALUES_CACHE = ToolType.values();

	public static ToolType getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(value -> value.getName().equals(name.toLowerCase(Locale.ROOT)) || value.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
