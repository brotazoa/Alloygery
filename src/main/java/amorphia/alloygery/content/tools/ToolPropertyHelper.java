package amorphia.alloygery.content.tools;

import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.tools.item.tool.*;
import amorphia.alloygery.content.tools.property.ToolProperty;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ToolPropertyHelper
{
	public static List<ToolProperty> getPropertiesForTool(ItemStack tool)
	{
		List<ToolProperty> toolProperties = Lists.newArrayList();

		if (tool.getItem() instanceof IDynamicTool dynamicTool)
		{
			addBaseStatsByTool(dynamicTool.getToolType(), toolProperties);
		}

		toolProperties.addAll(ToolMaterialHelper.getHeadMaterial(tool).getToolPropertiesByPart(ToolPartType.HEAD));
		toolProperties.addAll(ToolMaterialHelper.getBindingMaterial(tool).getToolPropertiesByPart(ToolPartType.BINDING));
		toolProperties.addAll(ToolMaterialHelper.getHandleMaterial(tool).getToolPropertiesByPart(ToolPartType.HANDLE));
		toolProperties.addAll(ToolMaterialHelper.getUpgradeMaterial(tool).getToolPropertiesByPart(ToolPartType.UPGRADE));
		return toolProperties;
	}

	private static void addBaseStatsByTool(ToolType toolType, List<ToolProperty> toolProperties)
	{
		switch (toolType)
		{
			case AXE -> {
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_DAMAGE, ToolPropertyOperation.BASE, DynamicAxeItem.ATTACK_DAMAGE));
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_SPEED, ToolPropertyOperation.BASE, DynamicAxeItem.ATTACK_SPEED));
			}
			case HOE -> {
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_DAMAGE, ToolPropertyOperation.BASE, DynamicHoeItem.ATTACK_DAMAGE));
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_SPEED, ToolPropertyOperation.BASE, DynamicHoeItem.ATTACK_SPEED));
			}
			case PICKAXE -> {
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_DAMAGE, ToolPropertyOperation.BASE, DynamicPickaxeItem.ATTACK_DAMAGE));
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_SPEED, ToolPropertyOperation.BASE, DynamicPickaxeItem.ATTACK_SPEED));
			}
			case SHOVEL -> {
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_DAMAGE, ToolPropertyOperation.BASE, DynamicShovelItem.ATTACK_DAMAGE));
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_SPEED, ToolPropertyOperation.BASE, DynamicShovelItem.ATTACK_SPEED));
			}
			case SWORD -> {
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_DAMAGE, ToolPropertyOperation.BASE, DynamicSwordItem.ATTACK_DAMAGE));
				toolProperties.add(new ToolProperty(ToolPartType.HEAD, ToolPropertyType.ATTACK_SPEED, ToolPropertyOperation.BASE, DynamicSwordItem.ATTACK_SPEED));
			}
		}
	}

	public static float computePropertyValue(List<ToolProperty> toolProperties, ToolPropertyType propertyType)
	{
		//TODO: seems like trash, probably is trash, revisit.
		List<ToolProperty> propertiesOfType = toolProperties.stream().filter(p -> p.type().equals(propertyType)).toList();
		List<ToolProperty> baseProperties = propertiesOfType.stream().filter(p -> p.operation().equals(ToolPropertyOperation.BASE)).toList();
		List<ToolProperty> additionProperties = propertiesOfType.stream().filter(p -> p.operation().equals(ToolPropertyOperation.ADDITION)).toList();
		List<ToolProperty> multiplyBaseProperties = propertiesOfType.stream().filter(p -> p.operation().equals(ToolPropertyOperation.MULTIPLY_BASE)).toList();
		List<ToolProperty> multiplyTotalProperties = propertiesOfType.stream().filter(p -> p.operation().equals(ToolPropertyOperation.MULTIPLY_TOTAL)).toList();

		float base = 0.0f;
		for (ToolProperty property : baseProperties)
			base += property.value();

		float addition = 0.0f;
		for (ToolProperty property : additionProperties)
			addition += property.value();

		float multiplyBase = 0.0f;
		final float absBase = Math.abs(base);
		for(ToolProperty property : multiplyBaseProperties)
			multiplyBase += absBase * property.value() - absBase;

		final float baseTotal = base + addition + multiplyBase;
		final float absBaseTotal = Math.abs(baseTotal);
		float multiplyTotal = 0.0f;
		for(ToolProperty property : multiplyTotalProperties)
			multiplyTotal += absBaseTotal * property.value() - absBaseTotal;

		return baseTotal + multiplyTotal;
	}

	public static int getMiningLevel(ItemStack tool)
	{
		final float propertyValue = computePropertyValue(getPropertiesForTool(tool), ToolPropertyType.MINING_LEVEL);
		return Math.max(-1, (int) Math.floor(propertyValue));
	}

	public static int getMaxDurability(ItemStack tool)
	{
		final float propertyValue = computePropertyValue(getPropertiesForTool(tool), ToolPropertyType.DURABILITY);
		return Math.max(0, (int) Math.floor(propertyValue));
	}

	public static int getEnchantability(ItemStack tool)
	{
		final float propertyValue = computePropertyValue(getPropertiesForTool(tool), ToolPropertyType.ENCHANTABILITY);
		return Math.max(0, (int) Math.floor(propertyValue));
	}

	public static float getMiningSpeed(ItemStack tool)
	{
		final float propertyValue = computePropertyValue(getPropertiesForTool(tool), ToolPropertyType.MINING_SPEED);
		return Math.max(1.0f, propertyValue);
	}

	public static float getAttackSpeed(ItemStack tool)
	{
		final float propertyValue = computePropertyValue(getPropertiesForTool(tool), ToolPropertyType.ATTACK_SPEED);
		return Math.max(-3.990f, propertyValue);
	}

	public static float getAttackDamage(ItemStack tool)
	{
		final float propertyValue = computePropertyValue(getPropertiesForTool(tool), ToolPropertyType.ATTACK_DAMAGE);
		return Math.max(0.0f, propertyValue);
	}

	public static boolean isFireproof(ItemStack tool)
	{
		final float propertyValue = computePropertyValue(getPropertiesForTool(tool), ToolPropertyType.FIREPROOF);
		return propertyValue > 0;
	}

	public static boolean isPiglinLoved(ItemStack tool)
	{
		final float propertyValue = computePropertyValue(getPropertiesForTool(tool), ToolPropertyType.PIGLIN_LOVED);
		return propertyValue > 0;
	}
}
