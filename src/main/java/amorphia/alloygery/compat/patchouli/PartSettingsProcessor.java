package amorphia.alloygery.compat.patchouli;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class PartSettingsProcessor implements IComponentProcessor
{
	private AlloygeryMaterial.ToolPartSettings partSettings;
	private boolean multiplier;

	@Override
	public void setup(IVariableProvider iVariableProvider)
	{
		String materialId = iVariableProvider.get("material").asString();
		String part = iVariableProvider.get("part_settings").asString();
		AlloygeryMaterial material = AlloygeryMaterial.getById(materialId);
		this.partSettings = part.equals("head") ? material.tool_base : part.equals("binding") ? material.tool_binding : part.equals("handle") ?
				material.tool_handle : part.equals("upgrade") ? material.tool_upgrade : material.tool_base;
		this.multiplier = iVariableProvider.get("modifier").asString().equals("multiplies");
	}

	@Override
	public IVariable process(@NotNull String key)
	{
		return switch (key)
		{
			case "durability" -> multiplier ? IVariable.wrap(partSettings.durability_multiplier) : IVariable.wrap(partSettings.durability);
			case "mining_level" -> multiplier ? null : IVariable.wrap(partSettings.mining_level);
			case "mining_speed" -> multiplier ? IVariable.wrap(partSettings.mining_speed_multiplier) : IVariable.wrap(partSettings.mining_speed);
			case "attack_damage" -> multiplier ? IVariable.wrap(partSettings.attack_damage_multiplier) : IVariable.wrap(partSettings.attack_damage);
			case "attack_speed" -> multiplier ? IVariable.wrap(partSettings.attack_speed_multiplier) : IVariable.wrap(partSettings.attack_speed);
			case "enchantability" -> multiplier ? IVariable.wrap(partSettings.enchantability_multiplier) : IVariable.wrap(partSettings.enchantability);
			case "luck" -> multiplier ? IVariable.wrap(partSettings.luck_multiplier) : IVariable.wrap(partSettings.luck);
			default -> null;
		};
	}
}
