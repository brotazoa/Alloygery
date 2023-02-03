package amorphia.alloygery.content.armor.item;

import net.minecraft.entity.EquipmentSlot;

import java.util.Arrays;
import java.util.Locale;

public enum ArmorType
{
	HELMET(EquipmentSlot.HEAD),
	CHESTPLATE(EquipmentSlot.CHEST),
	LEGGINGS(EquipmentSlot.LEGS),
	BOOTS(EquipmentSlot.FEET),
	;

	public static final ArmorType[] VALUES_CACHE = ArmorType.values();

	public static ArmorType getByName(String name)
	{
		return Arrays.stream(VALUES_CACHE).filter(v -> v.getName().equals(name.toLowerCase(Locale.ROOT)) || v.getName().equals(name.toUpperCase(Locale.ROOT))).findFirst().orElseThrow();
	}

	private final EquipmentSlot slot;

	ArmorType(EquipmentSlot slot)
	{
		this.slot = slot;
	}

	public String getName()
	{
		return this.name().toLowerCase(Locale.ROOT);
	}

	public EquipmentSlot getEquipmentSlot()
	{
		return this.slot;
	}
}
