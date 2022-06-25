package amorphia.alloygery.compat.patchouli;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class UpgradeSettingsProcessor implements IComponentProcessor
{
	private AlloygeryMaterial.ToolPartSettings partSettings;

	@Override
	public void setup(IVariableProvider iVariableProvider)
	{
		String materialId = iVariableProvider.get("material").asString();
		AlloygeryMaterial material = AlloygeryMaterial.getById(materialId);
		this.partSettings = material.tool_upgrade;
	}

	@Override
	public IVariable process(String key)
	{
		return switch (key)
		{
			case "durability" -> IVariable.wrap(partSettings.durability);
			case "durability_multiplier" -> IVariable.wrap(partSettings.durability_multiplier);
			case "mining_level" -> IVariable.wrap(partSettings.mining_level);
			case "mining_speed" -> IVariable.wrap(partSettings.mining_speed);
			case "mining_speed_multiplier" -> IVariable.wrap(partSettings.mining_speed_multiplier);
			case "attack_damage" -> IVariable.wrap(partSettings.attack_damage);
			case "attack_damage_multiplier" -> IVariable.wrap(partSettings.attack_damage_multiplier);
			case "attack_speed" -> IVariable.wrap(partSettings.attack_speed);
			case "attack_speed_multiplier" -> IVariable.wrap(partSettings.attack_speed_multiplier);
			case "enchantability" -> IVariable.wrap(partSettings.enchantability);
			case "enchantability_multiplier" -> IVariable.wrap(partSettings.enchantability_multiplier);
			case "luck" -> IVariable.wrap(partSettings.luck);
			case "luck_multiplier" -> IVariable.wrap(partSettings.luck_multiplier);
			default -> null;
		};
	}
}
