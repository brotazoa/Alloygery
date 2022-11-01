package amorphia.alloygery.content.tools.item.part;

import java.util.Arrays;
import java.util.Locale;

public enum ToolUpgradeType
{
	EMBOSSED,
	PLATED,
	TIPPED,
	NONE,
	;

	public static final ToolUpgradeType[] VALUES_CACHE = ToolUpgradeType.values();

	public static ToolUpgradeType getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(value -> value.getName().equals(name.toUpperCase(Locale.ROOT)) || value.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}
}
