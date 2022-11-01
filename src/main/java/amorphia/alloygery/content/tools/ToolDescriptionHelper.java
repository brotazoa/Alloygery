package amorphia.alloygery.content.tools;

import amorphia.alloygery.content.tools.item.part.IToolPart;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.tools.item.tool.*;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import amorphia.alloygery.content.materials.AlloygeryMaterials;
import amorphia.alloygery.content.tools.property.ToolProperty;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import com.google.common.collect.Maps;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ToolDescriptionHelper
{
	private static final Map<ToolPropertyType, ToolTooltipPropertyWriter> TOOL_SIMPLE_PROPERTY_WRITERS = Maps.newEnumMap(ToolPropertyType.class);
	private static final Map<ToolPropertyType, ToolTooltipPropertyWriter> TOOL_COMPLEX_PROPERTY_WRITERS = Maps.newEnumMap(ToolPropertyType.class);
	private static final Map<ToolPartType, ToolTooltipPropertyWriter> TOOL_PARTS_LIST_WRITERS = Maps.newEnumMap(ToolPartType.class);

	private static final Map<ToolPropertyType, ToolPartTooltipPropertyWriter> TOOL_PART_PROPERTY_WRITERS = Maps.newEnumMap(ToolPropertyType.class);

	public static Text getToolStackName(ItemStack tool)
	{
		if(!(tool.getItem() instanceof IDynamicTool) || !ToolNBTHelper.isAlloygeryDataNBT(tool.getNbt()))
			return tool.getItem().getName();

		AlloygeryMaterial headMaterial = ToolMaterialHelper.getHeadMaterial(tool);
		AlloygeryMaterial upgradeMaterial = ToolMaterialHelper.getUpgradeMaterial(tool);

		ToolType toolType = ToolNBTHelper.getToolTypeFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool));

		MutableText toolName = translatable("item.alloygery." + headMaterial.getMaterialName() + "_" + toolType.getName());
		MutableText upgradeName = translatable("tooltip.alloygery.upgrade." + upgradeMaterial.getMaterialName());

		return upgradeMaterial == AlloygeryMaterials.UNKNOWN ? toolName : upgradeName.append(literal(" - ")).append(toolName);
	}

	public static void writeToolDescription(List<Text> tooltip, ItemStack tool, TooltipContext context)
	{
		if(ToolNBTHelper.isAlloygeryDataNBT(tool.getNbt()))
		{
			if (Screen.hasShiftDown())
			{
				if (context.isAdvanced())
				{
					writePartsList(tooltip, tool);
				}

				writeComplexToolDescription(tooltip, tool);
			}
			else
			{
				writeShiftPrompt(tooltip);
				writeSimpleToolDescription(tooltip, tool);
			}
		}
		else
		{
			writeNoNBTToolDescription(tooltip, tool);
		}
	}

	private static void writeNoNBTToolDescription(List<Text> tooltip, ItemStack tool)
	{
		if (tool != null && !tool.isEmpty() && tool.getItem() instanceof IDynamicTool dynamicTool)
		{
			tooltip.add(translatable("tooltip.alloygery.info.no_nbt_description." + dynamicTool.getToolType().getName()));
		}
	}

	private static void writeSimpleToolDescription(List<Text> tooltip, ItemStack tool)
	{
		for(ToolPropertyType type : ToolPropertyType.VALUES_CACHE)
		{
			Optional.ofNullable(TOOL_SIMPLE_PROPERTY_WRITERS.get(type)).ifPresent(writer -> writer.write(tooltip, tool));
		}
	}

	private static void writeComplexToolDescription(List<Text> tooltip, ItemStack tool)
	{
		for(ToolPropertyType type : ToolPropertyType.VALUES_CACHE)
		{
			Optional.ofNullable(TOOL_COMPLEX_PROPERTY_WRITERS.get(type)).ifPresent(writer -> writer.write(tooltip, tool));
		}
	}

	private static void writePartsList(List<Text> tooltip, ItemStack tool)
	{
		tooltip.add(translatable("tooltip.alloygery.info.parts_list"));

		for(ToolPartType type : ToolPartType.values())
		{
			Optional.ofNullable(TOOL_PARTS_LIST_WRITERS.get(type)).ifPresent(writer -> writer.write(tooltip, tool));
		}

		tooltip.add(literal(""));
	}

	public static void writeToolPartDescription(List<Text> tooltip, IToolPart toolPart)
	{
		if (Screen.hasShiftDown())
		{
			if (toolPart.getToolPartType() == ToolPartType.UPGRADE)
			{
				writeUpgradePrompt(tooltip);
			}
			else
			{
				writeCraftPrompt(tooltip);
			}
			writeToolPartProperties(tooltip, toolPart);
		}
		else
		{
			writeShiftPrompt(tooltip);
		}
	}

	private static void writeToolPartProperties(List<Text> tooltip, IToolPart part)
	{
		for (ToolPropertyType toolPropertyType : ToolPropertyType.VALUES_CACHE)
		{
			Optional.ofNullable(TOOL_PART_PROPERTY_WRITERS.get(toolPropertyType)).ifPresent(writer -> writer.write(tooltip, part));
		}
	}

	private static void writeCraftPrompt(List<Text> tooltip)
	{
		tooltip.add(translatable("tooltip.alloygery.when_used_in_craft"));
	}

	private static void writeUpgradePrompt(List<Text> tooltip)
	{
		tooltip.add(translatable("tooltip.alloygery.when_used_to_upgrade"));
	}

	private static void writeShiftPrompt(List<Text> tooltip)
	{
		tooltip.add(translatable("tooltip.alloygery.shift_for_info").formatted(Formatting.DARK_GRAY));
	}

	private static void writeCtrlPrompt(List<Text> tooltip)
	{
		tooltip.add(translatable("tooltip.alloygery.ctrl_for_details").formatted(Formatting.DARK_GRAY));
	}

	private static TranslatableText translatable(String translationKey)
	{
		return new TranslatableText(translationKey);
	}

	private static LiteralText literal(String text)
	{
		return new LiteralText(text);
	}

	interface ToolTooltipPropertyWriter
	{
		void write(List<Text> tooltip, ItemStack tool);
	}

	interface ToolPartTooltipPropertyWriter
	{
		void write(List<Text> tooltip, IToolPart toolPart);
	}

	private static void default_tool_part_property_writer(List<Text> tooltip, IToolPart toolPart, ToolPropertyType propertyType, String translationKeyPart)
	{
		List<ToolProperty> properties = toolPart.getToolPartProperties().stream().filter(p -> p.type().equals(propertyType)).sorted(Comparator.comparing(ToolProperty::operation)).toList();
		if(properties.isEmpty())
			return;

		tooltip.add(translatable("tooltip.alloygery.info." + translationKeyPart).formatted(Formatting.WHITE));

		properties.forEach(property -> {
			final String key = (property.operation() == ToolPropertyOperation.BASE || property.operation() == ToolPropertyOperation.MULTIPLY_BASE) ?
					"tooltip.alloygery.info.base_" + translationKeyPart :
					"tooltip.alloygery.info.total_" + translationKeyPart;
			final boolean multiplier = property.operation() == ToolPropertyOperation.MULTIPLY_BASE || property.operation() == ToolPropertyOperation.MULTIPLY_TOTAL;

			final MutableText label = literal("  ").append(translatable(key)).append(literal(multiplier ? " * " : " + ")).formatted(Formatting.GRAY);
			final MutableText value = literal(String.format("%.2f", property.value())).formatted(
					multiplier ? property.value() < 1.0f ? Formatting.DARK_RED : Formatting.DARK_GREEN : property.value() < 0.0f ? Formatting.DARK_RED : Formatting.DARK_GREEN
			);
			tooltip.add(label.append(value));
		});
	}

	static
	{
		TOOL_SIMPLE_PROPERTY_WRITERS.put(ToolPropertyType.FIREPROOF, (tooltip, tool) -> {
			if(ToolPropertyHelper.isFireproof(tool))
				tooltip.add(translatable("tooltip.alloygery.info.fireproof").formatted(Formatting.DARK_RED));
		});
		TOOL_SIMPLE_PROPERTY_WRITERS.put(ToolPropertyType.PIGLIN_LOVED, (tooltip, tool) -> {
			if(ToolPropertyHelper.isPiglinLoved(tool))
				tooltip.add(translatable("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
		});

		TOOL_COMPLEX_PROPERTY_WRITERS.put(ToolPropertyType.ATTACK_DAMAGE, (tooltip, tool) -> tooltip.add(translatable("tooltip.alloygery.info.attack_damage").append(literal(": " + String.format("%.2f", ToolPropertyHelper.getAttackDamage(tool)))).formatted(Formatting.GRAY)));
		TOOL_COMPLEX_PROPERTY_WRITERS.put(ToolPropertyType.ATTACK_SPEED, (tooltip, tool) -> tooltip.add(translatable("tooltip.alloygery.info.attack_speed").append(literal(": " + String.format("%.2f", ToolPropertyHelper.getAttackSpeed(tool)))).formatted(Formatting.GRAY)));
		TOOL_COMPLEX_PROPERTY_WRITERS.put(ToolPropertyType.DURABILITY, (tooltip, tool) -> tooltip.add(translatable("tooltip.alloygery.info.durability").append(literal(": " + ToolPropertyHelper.getMaxDurability(tool))).formatted(Formatting.GRAY)));
		TOOL_COMPLEX_PROPERTY_WRITERS.put(ToolPropertyType.ENCHANTABILITY, (tooltip, tool) -> tooltip.add(translatable("tooltip.alloygery.info.enchantability").append(literal(": " + ToolPropertyHelper.getEnchantability(tool))).formatted(Formatting.GRAY)));
		TOOL_COMPLEX_PROPERTY_WRITERS.put(ToolPropertyType.MINING_LEVEL, (tooltip, tool) -> tooltip.add(translatable("tooltip.alloygery.info.mining_level").append(literal(": " + ToolPropertyHelper.getMiningLevel(tool))).formatted(Formatting.GRAY)));
		TOOL_COMPLEX_PROPERTY_WRITERS.put(ToolPropertyType.MINING_SPEED, (tooltip, tool) -> tooltip.add(translatable("tooltip.alloygery.info.mining_speed").append(literal(": " + String.format("%.2f", ToolPropertyHelper.getMiningSpeed(tool)))).formatted(Formatting.GRAY)));
		TOOL_COMPLEX_PROPERTY_WRITERS.put(ToolPropertyType.FIREPROOF, (tooltip, tool) -> {
			if(ToolPropertyHelper.isFireproof(tool))
				tooltip.add(translatable("tooltip.alloygery.info.fireproof").formatted(Formatting.DARK_RED));
		});
		TOOL_COMPLEX_PROPERTY_WRITERS.put(ToolPropertyType.PIGLIN_LOVED, (tooltip, tool) -> {
			if(ToolPropertyHelper.isPiglinLoved(tool))
				tooltip.add(translatable("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
		});

		TOOL_PARTS_LIST_WRITERS.put(ToolPartType.HEAD, (tooltip, tool) -> {
			ItemStack stack = ToolNBTHelper.createToolPartItemStackFromNBT(ToolNBTHelper.getHeadPartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool)));
			tooltip.add(literal("  ").append(translatable("tooltip.alloygery.info.head_part")).append(literal(": ")).append(translatable(stack.getTranslationKey())).formatted(Formatting.GRAY));
		});
		TOOL_PARTS_LIST_WRITERS.put(ToolPartType.BINDING, (tooltip, tool) -> {
			ItemStack stack = ToolNBTHelper.createToolPartItemStackFromNBT(ToolNBTHelper.getBindingPartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool)));
			if(stack == null || stack.isEmpty())
				return;

			tooltip.add(literal("  ").append(translatable("tooltip.alloygery.info.binding_part")).append(literal(": ")).append(translatable(stack.getTranslationKey())).formatted(Formatting.GRAY));
		});
		TOOL_PARTS_LIST_WRITERS.put(ToolPartType.HANDLE, (tooltip, tool) -> {
			ItemStack stack = ToolNBTHelper.createToolPartItemStackFromNBT(ToolNBTHelper.getHandlePartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool)));
			tooltip.add(literal("  ").append(translatable("tooltip.alloygery.info.handle_part")).append(literal(": ")).append(translatable(stack.getTranslationKey())).formatted(Formatting.GRAY));
		});
		TOOL_PARTS_LIST_WRITERS.put(ToolPartType.UPGRADE, (tooltip, tool) -> {
			ItemStack stack = ToolNBTHelper.createToolPartItemStackFromNBT(ToolNBTHelper.getUpgradePartNBTFromToolNBT(ToolNBTHelper.getAlloygeryDataNBTFromItemStack(tool)));
			if(stack == null || stack.isEmpty())
				return;

			tooltip.add(literal("  ").append(translatable("tooltip.alloygery.info.upgrade_part")).append(literal(": ")).append(translatable(stack.getTranslationKey())).formatted(Formatting.GRAY));
		});


		TOOL_PART_PROPERTY_WRITERS.put(ToolPropertyType.ATTACK_DAMAGE, (tooltip, toolPart) -> default_tool_part_property_writer(tooltip, toolPart, ToolPropertyType.ATTACK_DAMAGE, "attack_damage"));
		TOOL_PART_PROPERTY_WRITERS.put(ToolPropertyType.ATTACK_SPEED, (tooltip, toolPart) -> default_tool_part_property_writer(tooltip, toolPart, ToolPropertyType.ATTACK_SPEED, "attack_speed"));
		TOOL_PART_PROPERTY_WRITERS.put(ToolPropertyType.DURABILITY, (tooltip, toolPart) -> default_tool_part_property_writer(tooltip, toolPart, ToolPropertyType.DURABILITY, "durability"));
		TOOL_PART_PROPERTY_WRITERS.put(ToolPropertyType.ENCHANTABILITY, (tooltip, toolPart) -> default_tool_part_property_writer(tooltip, toolPart, ToolPropertyType.ENCHANTABILITY, "enchantability"));
		TOOL_PART_PROPERTY_WRITERS.put(ToolPropertyType.MINING_LEVEL, (tooltip, toolPart) -> default_tool_part_property_writer(tooltip, toolPart, ToolPropertyType.MINING_LEVEL, "mining_level"));
		TOOL_PART_PROPERTY_WRITERS.put(ToolPropertyType.MINING_SPEED, (tooltip, toolPart) -> default_tool_part_property_writer(tooltip, toolPart, ToolPropertyType.MINING_SPEED, "mining_speed"));

		TOOL_PART_PROPERTY_WRITERS.put(ToolPropertyType.FIREPROOF, (tooltip, toolPart) -> {
			List<ToolProperty> properties = toolPart.getToolPartProperties().stream().filter(p -> p.type().equals(ToolPropertyType.FIREPROOF)).toList();
			if(properties.isEmpty())
				return;

			final float value = ToolPropertyHelper.computePropertyValue(properties, ToolPropertyType.FIREPROOF);
			if(value <= 0.0f)
				return;

			tooltip.add(translatable("tooltip.alloygery.info.fireproof").formatted(Formatting.DARK_RED));
		});

		TOOL_PART_PROPERTY_WRITERS.put(ToolPropertyType.PIGLIN_LOVED, (tooltip, toolPart) -> {
			List<ToolProperty> properties = toolPart.getToolPartProperties().stream().filter(p -> p.type().equals(ToolPropertyType.PIGLIN_LOVED)).toList();
			if(properties.isEmpty())
				return;

			final float value = ToolPropertyHelper.computePropertyValue(properties, ToolPropertyType.PIGLIN_LOVED);
			if(value <= 0.0f)
				return;

			tooltip.add(translatable("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
		});
	}
}
