package amorphia.alloygery.content.armor;

import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.IArmorPart;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.armor.property.ArmorProperty;
import amorphia.alloygery.content.armor.property.ArmorPropertyOperation;
import amorphia.alloygery.content.armor.property.ArmorPropertyType;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ArmorPropertyHelper
{
	public static final int[] DURABILITY_BY_SLOT_MULTIPLIERS = new int[] { 13, 15, 16, 11 }; //boots, leggings, chest, helmet
	public static final float[] ARMOR_BY_SLOT_MULTIPLIERS = new float[] { 0.10f, 0.25f, 0.5f, 0.15f }; //boots, leggings, chest, helmet

	public static List<ArmorProperty> getPropertiesForArmor(ItemStack armorStack)
	{
		List<ArmorProperty> properties = Lists.newArrayList();
		properties.addAll(ArmorMaterialHelper.getMaterialForLayer(armorStack, ArmorLayer.BASE).getArmorPropertiesByLayer(ArmorLayer.BASE));
		properties.addAll(ArmorMaterialHelper.getMaterialForLayer(armorStack, ArmorLayer.PLATE).getArmorPropertiesByLayer(ArmorLayer.PLATE));
		properties.addAll(ArmorMaterialHelper.getMaterialForLayer(armorStack, ArmorLayer.UPGRADE).getArmorPropertiesByLayer(ArmorLayer.UPGRADE));
		return properties;
	}

	public static float computeSimplePropertyValue(List<ArmorProperty> properties, ArmorPropertyType propertyType)
	{
		List<ArmorProperty> propertiesOfType = properties.stream().filter(p -> p.type().equals(propertyType)).toList();
		List<ArmorProperty> baseProperties = propertiesOfType.stream().filter(p -> p.operation().equals(ArmorPropertyOperation.BASE)).toList();
		List<ArmorProperty> additionProperties = propertiesOfType.stream().filter(p -> p.operation().equals(ArmorPropertyOperation.ADDITION)).toList();
		List<ArmorProperty> multiplyBaseProperties = propertiesOfType.stream().filter(p -> p.operation().equals(ArmorPropertyOperation.MULTIPLY_BASE)).toList();
		List<ArmorProperty> multiplyTotalProperties = propertiesOfType.stream().filter(p -> p.operation().equals(ArmorPropertyOperation.MULTIPLY_TOTAL)).toList();

		float base = 0.0f;
		for(ArmorProperty property : baseProperties)
			base += property.value();

		float addition = 0.0f;
		for(ArmorProperty property : additionProperties)
			addition += property.value();

		final float absBase = Math.abs(base);
		float multiplyBase = 0.0f;
		for (ArmorProperty property : multiplyBaseProperties)
			multiplyBase += absBase * property.value() - absBase;

		final float baseTotal = base + addition + multiplyBase;
		final float absBaseTotal = Math.abs(baseTotal);
		float multiplyTotal = 0.0f;
		for (ArmorProperty property : multiplyTotalProperties)
			multiplyTotal += absBaseTotal * property.value() - absBaseTotal;

		return baseTotal + multiplyTotal;
	}

	public static float getSlotMultiplier(ItemStack stack)
	{
		if(stack.getItem() instanceof IDynamicArmor armor)
			return ARMOR_BY_SLOT_MULTIPLIERS[armor.getArmorType().getEquipmentSlot().getEntitySlotId()];
		else if(stack.getItem() instanceof IArmorPart armorPart)
			return ARMOR_BY_SLOT_MULTIPLIERS[armorPart.getArmorType().getEquipmentSlot().getEntitySlotId()];
		else return 1.0f;
	}

	public static float getDurabilityMultiplier(ItemStack stack)
	{
		if(stack.getItem() instanceof IDynamicArmor armor)
			return DURABILITY_BY_SLOT_MULTIPLIERS[armor.getArmorType().getEquipmentSlot().getEntitySlotId()];
		else if(stack.getItem() instanceof IArmorPart armorPart)
			return DURABILITY_BY_SLOT_MULTIPLIERS[armorPart.getArmorType().getEquipmentSlot().getEntitySlotId()];
		else return 1.0f;
	}

	public static int getMaxDurability(ItemStack armorStack)
	{
		final float durabilityValue = computeSimplePropertyValue(getPropertiesForArmor(armorStack), ArmorPropertyType.DURABILITY);
		return Math.max(0, Math.round(durabilityValue * getDurabilityMultiplier(armorStack)));
	}

	public static int getProtectionAmount(ItemStack armorStack)
	{
		//properties cache
		final List<ArmorProperty> properties = getPropertiesForArmor(armorStack);

		//armor value not modified by equipment slot
		final float armorValue = computeSimplePropertyValue(properties, ArmorPropertyType.ARMOR);
		//armor value multiplied by equipment slot multiplier
		final float slotArmorValue = armorValue * getSlotMultiplier(armorStack);

		return Math.max(0, (int) Math.ceil(slotArmorValue));
	}

	public static float getToughnessAmount(ItemStack armorStack)
	{
		final List<ArmorProperty> properties = getPropertiesForArmor(armorStack);

		final float toughnessValue = computeSimplePropertyValue(properties, ArmorPropertyType.TOUGHNESS);

		return Math.max(0.0f, toughnessValue);
	}

	public static float getMobility(ItemStack armorStack)
	{
		final List<ArmorProperty> properties = getPropertiesForArmor(armorStack);

		final float mobilityValue = computeSimplePropertyValue(properties, ArmorPropertyType.MOBILITY);
		return mobilityValue * getSlotMultiplier(armorStack);
	}

	public static float getMovementSpeedMultiplier(ItemStack armorStack)
	{
		final float mobilityValue = getMobility(armorStack);
		//convert to percent
		return mobilityValue * 0.01f;
	}

	public static int getEnchantability(ItemStack armorStack)
	{
		final float enchantabilityValue = computeSimplePropertyValue(getPropertiesForArmor(armorStack), ArmorPropertyType.ENCHANTABILITY);
		return Math.max(0, Math.round(enchantabilityValue));
	}

	public static float getKnockbackResistance(ItemStack armorStack)
	{
		final float knockbackValue = computeSimplePropertyValue(getPropertiesForArmor(armorStack), ArmorPropertyType.KNOCKBACK_RESISTANCE);
		final float knockbackSlotValue = knockbackValue * getSlotMultiplier(armorStack);
		final float knockbackModifier = knockbackSlotValue * 0.01f; //convert to percent

		return Math.max(0.0f, Math.min(1.0f, knockbackModifier)); //clamp between 0.0 - 1.0
	}

	public static boolean isFireproof(ItemStack armorStack)
	{
		final float fireproofValue = computeSimplePropertyValue(getPropertiesForArmor(armorStack), ArmorPropertyType.FIREPROOF);
		return fireproofValue > 0;
	}

	public static boolean isPiglinLoved(ItemStack armorStack)
	{
		final float piglinLovedValue = computeSimplePropertyValue(getPropertiesForArmor(armorStack), ArmorPropertyType.PIGLIN_LOVED);
		return piglinLovedValue > 0;
	}
}
