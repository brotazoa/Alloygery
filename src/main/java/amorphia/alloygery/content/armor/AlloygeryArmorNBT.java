package amorphia.alloygery.content.armor;

import java.util.Arrays;
import java.util.Locale;

public enum AlloygeryArmorNBT
{
	ALLOYGERY_NBT_IDENTIFIER,
	ALLOYGERY_NBT_VERSION_IDENTIFIER,

	UNKNOWN_NBT_ERROR,

	TYPE,
	ARMOR_IDENTIFIER,
	ARMOR_LAYER_IDENTIFIER,
	ARMOR_ITEM_IDENTIFIER,
	MATERIAL_IDENTIFIER,

	ARMOR_LAYER_TYPE_IDENTIFIER,
	ARMOR_LAYER_BASE,
	ARMOR_LAYER_PLATE,
	ARMOR_LAYER_UPGRADE,

	ARMOR_TYPE_IDENTIFIER,
	ARMOR_TYPE_HELMET,
	ARMOR_TYPE_CHESTPLATE,
	ARMOR_TYPE_LEGGINGS,
	ARMOR_TYPE_BOOTS,

	ARMOR_DYE_COLOR,
	;

	public static final AlloygeryArmorNBT[] VALUES_CACHE = AlloygeryArmorNBT.values();

	public static AlloygeryArmorNBT getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(v -> v.getName().equals(name.toLowerCase(Locale.ROOT)) || v.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElse(UNKNOWN_NBT_ERROR);
	}

	public String getName()
	{
		return name().toLowerCase(Locale.ROOT);
	}
}
