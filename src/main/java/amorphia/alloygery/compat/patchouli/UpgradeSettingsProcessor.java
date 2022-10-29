package amorphia.alloygery.compat.patchouli;

import amorphia.alloygery.content.tools.ToolPropertyHelper;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.material.ToolMaterial;
import amorphia.alloygery.content.tools.property.ToolProperty;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import amorphia.alloygery.content.tools.registry.ToolMaterialRegistry;
import com.google.common.collect.Lists;
import net.minecraft.util.Identifier;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.List;

public class UpgradeSettingsProcessor implements IComponentProcessor
{
	private final List<ToolProperty> toolPropertiesByPart = Lists.newArrayList();

	@Override
	public void setup(IVariableProvider iVariableProvider)
	{
		String materialId = iVariableProvider.get("material").asString();
		ToolMaterial material = ToolMaterialRegistry.get(Identifier.tryParse(materialId));
		toolPropertiesByPart.addAll(material.getPropertiesByPart(ToolPartType.UPGRADE));
	}

	@Override
	public IVariable process(String key)
	{
		return switch (key)
		{
			case "durability" -> IVariable.wrap(ToolPropertyHelper.computePropertyValue(toolPropertiesByPart, ToolPropertyType.DURABILITY));
			//case "durability_multiplier" -> IVariable.wrap(partSettings.durability_multiplier);
			case "mining_level" -> IVariable.wrap(ToolPropertyHelper.computePropertyValue(toolPropertiesByPart, ToolPropertyType.MINING_LEVEL));
			case "mining_speed" -> IVariable.wrap(ToolPropertyHelper.computePropertyValue(toolPropertiesByPart, ToolPropertyType.MINING_SPEED));
			//case "mining_speed_multiplier" -> IVariable.wrap(partSettings.mining_speed_multiplier);
			case "attack_damage" -> IVariable.wrap(ToolPropertyHelper.computePropertyValue(toolPropertiesByPart, ToolPropertyType.ATTACK_DAMAGE));
			//case "attack_damage_multiplier" -> IVariable.wrap(partSettings.attack_damage_multiplier);
			case "attack_speed" -> IVariable.wrap(ToolPropertyHelper.computePropertyValue(toolPropertiesByPart, ToolPropertyType.ATTACK_SPEED));
			//case "attack_speed_multiplier" -> IVariable.wrap(partSettings.attack_speed_multiplier);
			case "enchantability" -> IVariable.wrap(ToolPropertyHelper.computePropertyValue(toolPropertiesByPart, ToolPropertyType.ENCHANTABILITY));
			//case "enchantability_multiplier" -> IVariable.wrap(partSettings.enchantability_multiplier);
			//case "luck" -> IVariable.wrap(partSettings.luck);
			//case "luck_multiplier" -> IVariable.wrap(partSettings.luck_multiplier);
			default -> null;
		};
	}
}
