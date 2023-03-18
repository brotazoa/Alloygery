package amorphia.alloygery.content.tools;

import java.util.Arrays;
import java.util.Locale;

public enum AlloygeryToolNBT
{
	ALLOYGERY_NBT_IDENTIFIER,
	ALLOYGERY_NBT_VERSION_IDENTIFIER,

	UNKNOWN_NBT_ERROR,

	TYPE,
	MATERIAL_IDENTIFIER,
	TOOL_PART_IDENTIFIER,
	TOOL_PART_ITEM_IDENTIFIER,
	TOOL_IDENTIFIER,
	TOOL_ITEM_IDENTIFIER,

	TOOL_PART_TYPE_IDENTIFIER,
	TOOL_PART_HEAD,
	TOOL_PART_BINDING,
	TOOL_PART_HANDLE,
	TOOL_PART_UPGRADE,

	TOOL_TYPE_IDENTIFIER,
	TOOL_TYPE_AXE,
	TOOL_TYPE_HOE,
	TOOL_TYPE_PICKAXE,
	TOOL_TYPE_SHOVEL,
	TOOL_TYPE_SWORD,
	;

	public static final AlloygeryToolNBT[] VALUES_CACHE = AlloygeryToolNBT.values();

	public static AlloygeryToolNBT getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(v -> v.getName().equals(name.toLowerCase(Locale.ROOT)) || v.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElse(UNKNOWN_NBT_ERROR);
	}

	public String getName()
	{
		return name().toLowerCase(Locale.ROOT);
	}
}
