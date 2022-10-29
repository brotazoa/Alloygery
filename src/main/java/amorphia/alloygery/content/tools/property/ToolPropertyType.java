package amorphia.alloygery.content.tools.property;

import java.util.Arrays;
import java.util.Locale;

public enum ToolPropertyType
{
	//order matters for tooltips
	DURABILITY,
	MINING_LEVEL,
	MINING_SPEED,
	ATTACK_DAMAGE,
	ATTACK_SPEED,
	ENCHANTABILITY,
	FIREPROOF,
	PIGLIN_LOVED;

	public static final ToolPropertyType[] VALUES_CACHE = ToolPropertyType.values();

	public static ToolPropertyType getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(value -> value.getName().equals(name)).findFirst().orElseThrow();
	}

	public String getName()
	{
		return name().toLowerCase(Locale.ROOT);
	}
}
