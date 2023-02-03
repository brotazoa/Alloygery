package amorphia.alloygery.content.armor;

import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.IArmorPart;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials;
import amorphia.alloygery.content.armor.property.ArmorProperty;
import amorphia.alloygery.content.armor.property.ArmorPropertyOperation;
import amorphia.alloygery.content.armor.property.ArmorPropertyType;
import com.google.common.collect.Maps;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArmorDescriptionHelper
{
	private static final Map<ArmorPropertyType, ArmorTooltipPropertyWriter> ARMOR_SIMPLE_PROPERTY_WRITERS = Maps.newEnumMap(ArmorPropertyType.class);
	private static final Map<ArmorPropertyType, ArmorTooltipPropertyWriter> ARMOR_COMPLEX_PROPERTY_WRITERS = Maps.newEnumMap(ArmorPropertyType.class);
	private static final Map<ArmorPropertyType, ArmorPartTooltipPropertyWriter> ARMOR_PART_PROPERTY_WRITERS = Maps.newEnumMap(ArmorPropertyType.class);

	public static Text getArmorStackName(ItemStack armor)
	{
		if (armor.getItem() instanceof IDynamicArmor dynamicArmor && ArmorNBTHelper.isAlloygeryDataNBT(armor.getNbt()))
		{
			AlloygeryArmorMaterial baseMaterial;
			AlloygeryArmorMaterial plateMaterial;
			AlloygeryArmorMaterial upgradeMaterial;

			baseMaterial = ArmorMaterialHelper.getMaterialForLayer(armor, ArmorLayer.BASE);
			plateMaterial = ArmorMaterialHelper.getMaterialForLayer(armor, ArmorLayer.PLATE);
			upgradeMaterial = ArmorMaterialHelper.getMaterialForLayer(armor, ArmorLayer.UPGRADE);

			MutableText armorBaseName = translatable("item.alloygery." + baseMaterial.getMaterialName() + "_" + dynamicArmor.getArmorType().getName());

			if (plateMaterial != AlloygeryArmorMaterials.UNKNOWN && plateMaterial != AlloygeryArmorMaterials.HIDDEN)
			{
				armorBaseName = translatable("item.alloygery." + plateMaterial.getMaterialName() + "_" + dynamicArmor.getArmorType().getName());
			}

			MutableText armorName = armorBaseName;

			if (upgradeMaterial != AlloygeryArmorMaterials.UNKNOWN && upgradeMaterial != AlloygeryArmorMaterials.HIDDEN)
			{
				MutableText upgradeName = translatable("tooltip.alloygery.armor_upgrade." + upgradeMaterial.getMaterialName());
				armorName = upgradeName.append(literal(" - ")).append(armorBaseName);
			}

			return armorName;
		}
		else
		{
			return armor.getItem().getName();
		}
	}

	public static void writeArmorDescription(List<Text> tooltip, ItemStack armor, TooltipContext context)
	{
		ItemStack armorStackCopy = ArmorNBTHelper.isAlloygeryDataNBT(armor.getNbt()) ? armor.copy() : armor.getItem().getDefaultStack().copy();

		if(armorStackCopy.getItem() instanceof IDynamicArmor)
		{
			if(Screen.hasShiftDown())
			{
				writeComplexArmorDescription(tooltip, armorStackCopy);
			}
			else
			{
				writeShiftPrompt(tooltip);
				writeSimpleArmorDescription(tooltip, armorStackCopy);
			}
		}
		else
		{
			writeNoNBTArmorDescription(tooltip, armorStackCopy);
		}
	}

	public static void writeArmorPartDescription(List<Text> tooltip, ItemStack armorPartStack, IArmorPart armorPart)
	{
		if (Screen.hasShiftDown())
		{
			if(armorPart.getArmorLayer() == ArmorLayer.UPGRADE)
			{
				writeUpgradePrompt(tooltip);
			}
			else
			{
				writeCraftPrompt(tooltip);
			}
			writeArmorPartProperties(tooltip, armorPartStack, armorPart);
		}
		else
		{
			writeShiftPrompt(tooltip);
		}
	}

	private static void writeSimpleArmorDescription(List<Text> tooltip, ItemStack armor)
	{
		for(ArmorPropertyType propertyType : ArmorPropertyType.VALUES_CACHE)
		{
			Optional.ofNullable(ARMOR_SIMPLE_PROPERTY_WRITERS.get(propertyType)).ifPresent(writer -> writer.write(tooltip, armor));
		}
	}

	private static void writeComplexArmorDescription(List<Text> tooltip, ItemStack armor)
	{
		for(ArmorPropertyType propertyType : ArmorPropertyType.VALUES_CACHE)
		{
			Optional.ofNullable(ARMOR_COMPLEX_PROPERTY_WRITERS.get(propertyType)).ifPresent(writer -> writer.write(tooltip, armor));
		}
	}

	private static void writeArmorPartProperties(List<Text> tooltip, ItemStack armorPartStack, IArmorPart armorPart)
	{
		for(ArmorPropertyType propertyType : ArmorPropertyType.VALUES_CACHE)
		{
			Optional.ofNullable(ARMOR_PART_PROPERTY_WRITERS.get(propertyType)).ifPresent(writer -> writer.write(tooltip, armorPartStack, armorPart));
		}
	}

	private static void default_armor_part_property_writer(List<Text> tooltip, IArmorPart armorPart, ArmorPropertyType propertyType, String translationKeyPart)
	{
		List<ArmorProperty> properties = armorPart.getArmorProperties().stream().filter(p -> p.type().equals(propertyType)).sorted(Comparator.comparing(ArmorProperty::operation)).toList();
		if (properties.isEmpty()) return;

		tooltip.add(translatable("tooltip.alloygery.info." + translationKeyPart).formatted(Formatting.WHITE));

		properties.forEach(property -> {
			final String key = (property.operation() == ArmorPropertyOperation.BASE || property.operation() == ArmorPropertyOperation.MULTIPLY_BASE) ?
					"tooltip.alloygery.info.base_" + translationKeyPart :
					"tooltip.alloygery.info.total_" + translationKeyPart;

			final boolean multiplier = property.operation() == ArmorPropertyOperation.MULTIPLY_BASE || property.operation() == ArmorPropertyOperation.MULTIPLY_TOTAL;

			final MutableText label = literal(indent()).append(translatable(key)).append(literal(multiplier ? " * " : " + ")).formatted(Formatting.GRAY);
			final MutableText value = literal(String.format("%.2f", property.value())).formatted(multiplier ?
					property.value() < 1.0f ? Formatting.DARK_RED : Formatting.DARK_GREEN :
					property.value() < 0.0f ? Formatting.DARK_RED : Formatting.DARK_GREEN);

			tooltip.add(label.append(value));
		});
	}

	private static void default_armor_part_property_writer_with_slot_multiplier(List<Text> tooltip, ItemStack armorPartStack, IArmorPart armorPart, ArmorPropertyType propertyType, String translationKeyPart)
	{
		List<ArmorProperty> properties = armorPart.getArmorProperties().stream().filter(p -> p.type().equals(propertyType)).sorted(Comparator.comparing(ArmorProperty::operation)).toList();
		if(properties.isEmpty())
			return;

		tooltip.add(translatable("tooltip.alloygery.info." + translationKeyPart).formatted(Formatting.WHITE));

		properties.forEach(property -> {
			final String key = property.operation() == ArmorPropertyOperation.MULTIPLY_BASE || property.operation() == ArmorPropertyOperation.BASE ? "tooltip.alloygery.info.base_" + translationKeyPart : "tooltip.alloygery.info.total_" + translationKeyPart;
			final boolean multiplier = property.operation() == ArmorPropertyOperation.MULTIPLY_BASE || property.operation() == ArmorPropertyOperation.MULTIPLY_TOTAL;
			final float value = multiplier ? property.value() : property.value() * ArmorPropertyHelper.getSlotMultiplier(armorPartStack);

			MutableText label = literal(indent()).append(translatable(key)).append(literal(multiplier ? " * " : " + ")).formatted(Formatting.GRAY);
			MutableText valueText = literal(String.format("%.2f", value)).formatted(multiplier ?
					value < 1.0f ? Formatting.DARK_RED : Formatting.DARK_GREEN :
					value < 0.0f ? Formatting.DARK_RED : Formatting.DARK_GREEN);

			tooltip.add(label.append(valueText));
		});
	}

	private static void writeNoNBTArmorDescription(List<Text> tooltip, ItemStack armor)
	{
		if (armor != null && !armor.isEmpty() && armor.getItem() instanceof IDynamicArmor dynamicArmor)
		{
			tooltip.add(translatable("tooltip.alloygery.info.no_nbt_description." + dynamicArmor.getArmorType().getName()));
		}
	}

	private static void writeCraftPrompt(List<Text> tooltip)
	{
		tooltip.add(translatable("tooltip.alloygery.when_used_to_craft_armor"));
	}

	private static void writeUpgradePrompt(List<Text> tooltip)
	{
		tooltip.add(translatable("tooltip.alloygery.when_used_to_upgrade_armor"));
	}

	private static void writeShiftPrompt(List<Text> tooltip)
	{
		tooltip.add(translatable("tooltip.alloygery.shift_for_info").formatted(Formatting.DARK_GRAY));
	}

	private static void writeCtrlPrompt(List<Text> tooltip)
	{
		tooltip.add(translatable("tooltip.alloygery.ctrl_for_details").formatted(Formatting.DARK_GRAY));
	}

	private static MutableText translatable(String translationKey)
	{
		return Text.translatable(translationKey);
	}

	private static MutableText literal(String text)
	{
		return Text.literal(text);
	}

	private static String indent()
	{
		return "  ";
	}

	interface ArmorTooltipPropertyWriter
	{
		void write(List<Text> tooltip, ItemStack armor);
	}

	interface ArmorPartTooltipPropertyWriter
	{
		void write(List<Text> tooltip, ItemStack armorPartStack, IArmorPart armorPart);
	}

	static
	{
		ARMOR_SIMPLE_PROPERTY_WRITERS.put(ArmorPropertyType.FIREPROOF, (tooltip, armor) -> {
			if(ArmorPropertyHelper.isFireproof(armor))
				tooltip.add(translatable("tooltip.alloygery.info.fireproof").formatted(Formatting.DARK_RED));
		});

		ARMOR_SIMPLE_PROPERTY_WRITERS.put(ArmorPropertyType.PIGLIN_LOVED, (tooltip, armor) -> {
			if(ArmorPropertyHelper.isPiglinLoved(armor))
				tooltip.add(translatable("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
		});

		ARMOR_COMPLEX_PROPERTY_WRITERS.put(ArmorPropertyType.DURABILITY, (tooltip, armor) ->
				tooltip.add(translatable("tooltip.alloygery.info.durability").append(literal(": " + ArmorPropertyHelper.getMaxDurability(armor))).formatted(Formatting.GRAY)));
		ARMOR_COMPLEX_PROPERTY_WRITERS.put(ArmorPropertyType.ARMOR, (tooltip, armor) ->
				tooltip.add(translatable("tooltip.alloygery.info.armor").append(literal(": " + ArmorPropertyHelper.getProtectionAmount(armor))).formatted(Formatting.GRAY)));
		ARMOR_COMPLEX_PROPERTY_WRITERS.put(ArmorPropertyType.TOUGHNESS, (tooltip, armor) ->
				tooltip.add(translatable("tooltip.alloygery.info.toughness").append(literal(": " + String.format("%.2f", ArmorPropertyHelper.getToughnessAmount(armor)))).formatted(Formatting.GRAY)));
		ARMOR_COMPLEX_PROPERTY_WRITERS.put(ArmorPropertyType.MOBILITY, (tooltip, armor) ->
				tooltip.add(translatable("tooltip.alloygery.info.mobility").append(literal(": " + String.format("%.2f", ArmorPropertyHelper.getMobility(armor)))).formatted(Formatting.GRAY)));
		ARMOR_COMPLEX_PROPERTY_WRITERS.put(ArmorPropertyType.ENCHANTABILITY, (tooltip, armor) ->
				tooltip.add(translatable("tooltip.alloygery.info.enchantability").append(literal(": " + ArmorPropertyHelper.getEnchantability(armor))).formatted(Formatting.GRAY)));
		ARMOR_COMPLEX_PROPERTY_WRITERS.put(ArmorPropertyType.KNOCKBACK_RESISTANCE, (tooltip, armor) ->
				tooltip.add(translatable("tooltip.alloygery.info.knockback_resistance").append(literal(": " + String.format("%.2f", + ArmorPropertyHelper.getKnockbackResistance(armor)))).formatted(Formatting.GRAY)));

		ARMOR_COMPLEX_PROPERTY_WRITERS.put(ArmorPropertyType.FIREPROOF, (tooltip, armor) -> {
			if(ArmorPropertyHelper.isFireproof(armor))
				tooltip.add(translatable("tooltip.alloygery.info.fireproof").formatted(Formatting.DARK_RED));
		});

		ARMOR_COMPLEX_PROPERTY_WRITERS.put(ArmorPropertyType.PIGLIN_LOVED, (tooltip, armor) -> {
			if(ArmorPropertyHelper.isPiglinLoved(armor))
				tooltip.add(translatable("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
		});

		ARMOR_PART_PROPERTY_WRITERS.put(ArmorPropertyType.DURABILITY, (tooltip, armorPartStack, armorPart) -> {
			final List<ArmorProperty> properties = armorPart.getArmorProperties().stream().filter(p -> p.type().equals(ArmorPropertyType.DURABILITY)).toList();
			if(properties.isEmpty())
				return;

			tooltip.add(translatable("tooltip.alloygery.info.durability").formatted(Formatting.WHITE));

			properties.forEach(property -> {
				final String key = property.operation() == ArmorPropertyOperation.BASE || property.operation() == ArmorPropertyOperation.MULTIPLY_BASE ? "tooltip.alloygery.info.base_durability" : "tooltip.alloygery.info.total_durability";
				final boolean multiplier = property.operation() == ArmorPropertyOperation.MULTIPLY_BASE || property.operation() == ArmorPropertyOperation.MULTIPLY_TOTAL;
				final float value = multiplier ? property.value() : property.value() * ArmorPropertyHelper.getDurabilityMultiplier(armorPartStack);

				MutableText label = translatable(key).append(literal(multiplier ? " * " : " + ").formatted(Formatting.GRAY));
				MutableText valueText = literal(String.format("%.2f", value)).formatted(multiplier ?
						value < 1.0f ? Formatting.DARK_RED : Formatting.DARK_GREEN :
						value < 0.0f ? Formatting.DARK_RED : Formatting.DARK_GREEN);

				tooltip.add(label.append(valueText));
			});
		});

		ARMOR_PART_PROPERTY_WRITERS.put(ArmorPropertyType.ARMOR, (tooltip, armorPartStack, armorPart) -> default_armor_part_property_writer_with_slot_multiplier(tooltip, armorPartStack, armorPart, ArmorPropertyType.ARMOR, "armor"));
		ARMOR_PART_PROPERTY_WRITERS.put(ArmorPropertyType.TOUGHNESS, (tooltip, armorPartStack, armorPart) -> default_armor_part_property_writer(tooltip, armorPart, ArmorPropertyType.TOUGHNESS, "toughness"));
		ARMOR_PART_PROPERTY_WRITERS.put(ArmorPropertyType.MOBILITY, (tooltip, armorPartStack, armorPart) -> default_armor_part_property_writer_with_slot_multiplier(tooltip, armorPartStack, armorPart, ArmorPropertyType.MOBILITY, "mobility"));
		ARMOR_PART_PROPERTY_WRITERS.put(ArmorPropertyType.ENCHANTABILITY, (tooltip, armorPartStack, armorPart) -> default_armor_part_property_writer(tooltip, armorPart, ArmorPropertyType.ENCHANTABILITY, "enchantability"));
		ARMOR_PART_PROPERTY_WRITERS.put(ArmorPropertyType.KNOCKBACK_RESISTANCE, (tooltip, armorPartStack, armorPart) -> default_armor_part_property_writer(tooltip, armorPart, ArmorPropertyType.KNOCKBACK_RESISTANCE, "knockback_resistance"));

		ARMOR_PART_PROPERTY_WRITERS.put(ArmorPropertyType.FIREPROOF, (tooltip, armorPartStack, armorPart) -> {
			List<ArmorProperty> properties = armorPart.getArmorProperties().stream().filter(p -> p.type().equals(ArmorPropertyType.FIREPROOF)).toList();
			if(properties.isEmpty())
				return;

			final float value = ArmorPropertyHelper.computeSimplePropertyValue(properties, ArmorPropertyType.FIREPROOF);
			if(value <= 0.0f)
				return;

			tooltip.add(translatable("tooltip.alloygery.info.fireproof").formatted(Formatting.DARK_RED));
		});

		ARMOR_PART_PROPERTY_WRITERS.put(ArmorPropertyType.PIGLIN_LOVED, (tooltip, armorPartStack, armorPart) -> {
			List<ArmorProperty> properties = armorPart.getArmorProperties().stream().filter(p -> p.type().equals(ArmorPropertyType.PIGLIN_LOVED)).toList();
			if (properties.isEmpty())
				return;

			final float value = ArmorPropertyHelper.computeSimplePropertyValue(properties, ArmorPropertyType.PIGLIN_LOVED);
			if (value <= 0.0f)
				return;

			tooltip.add(translatable("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
		});
	}
}
