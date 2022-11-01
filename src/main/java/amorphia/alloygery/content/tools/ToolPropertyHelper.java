package amorphia.alloygery.content.tools;

import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.property.ToolProperty;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import net.minecraft.item.ItemStack;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class ToolPropertyHelper
{
	public static List<ToolProperty> getPropertiesForTool(ItemStack tool)
	{
		List<ToolProperty> toolProperties = Lists.newArrayList();
		toolProperties.addAll(ToolMaterialHelper.getHeadMaterial(tool).getToolPropertiesByPart(ToolPartType.HEAD));
		toolProperties.addAll(ToolMaterialHelper.getBindingMaterial(tool).getToolPropertiesByPart(ToolPartType.BINDING));
		toolProperties.addAll(ToolMaterialHelper.getHandleMaterial(tool).getToolPropertiesByPart(ToolPartType.HANDLE));
		toolProperties.addAll(ToolMaterialHelper.getUpgradeMaterial(tool).getToolPropertiesByPart(ToolPartType.UPGRADE));
		return toolProperties;
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
		for (ToolProperty property : multiplyBaseProperties)
			multiplyBase += base <= 0.0f ? 1.0f : base * property.value();

		final float baseTotal = base + addition + multiplyBase;
		float multiplyTotal = 0.0f;
		for (ToolProperty property : multiplyTotalProperties)
			multiplyTotal += baseTotal <= 0.0f ? 1.0f : baseTotal * property.value();

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
		return Math.max(-3.9f, propertyValue);
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
