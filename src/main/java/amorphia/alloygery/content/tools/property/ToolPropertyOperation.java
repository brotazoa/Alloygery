package amorphia.alloygery.content.tools.property;

import java.util.Arrays;
import java.util.Locale;

public enum ToolPropertyOperation
{
	//order matters for tooltips
	BASE,
	ADDITION,
	MULTIPLY_BASE,
	MULTIPLY_TOTAL;

	public static final ToolPropertyOperation[] VALUES_CACHE = ToolPropertyOperation.values();

	public static ToolPropertyOperation getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(value -> value.getName().equals(name)).findFirst().orElseThrow();
	}

	public String getName()
	{
		return name().toLowerCase(Locale.ROOT);
	}
}
