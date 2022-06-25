package amorphia.alloygery.compat.patchouli;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import org.jetbrains.annotations.NotNull;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class PartSettingsProcessor implements IComponentProcessor
{
	private AlloygeryMaterial.ToolPartSettings partSettings;

	@Override
	public void setup(IVariableProvider iVariableProvider)
	{
		String materialId = iVariableProvider.get("material").asString();
		String part = iVariableProvider.get("part_settings").asString();
		AlloygeryMaterial material = AlloygeryMaterial.getById(materialId);
		this.partSettings = part.equals("head") ? material.tool_base : part.equals("binding") ? material.tool_binding : part.equals("handle") ?
				material.tool_handle : part.equals("upgrade") ? material.tool_upgrade : material.tool_base;
	}

	@Override
	public IVariable process(@NotNull String key)
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
			case "values" -> IVariable.wrap(makeValueString());
			default -> null;
		};
	}

	private String makeValueString()
	{
		final String newLine = "$(br)";

		StringBuilder builder = new StringBuilder();
		if(partSettings.durability != 0)
			builder.append("Durability: ").append(partSettings.durability).append(newLine);
		if(partSettings.durability_multiplier != 1.0f)
			builder.append("Durability Multiplier: ").append(partSettings.durability_multiplier).append(newLine);
		if(partSettings.mining_level != 0)
			builder.append("Mining Level: ").append(partSettings.mining_level).append(newLine);
		if(partSettings.mining_speed != 0.0f)
			builder.append("Mining Speed: ").append(partSettings.mining_speed).append(newLine);
		if(partSettings.mining_speed_multiplier != 1.0f)
			builder.append("Mining Speed Multiplier: ").append(partSettings.mining_speed_multiplier).append(newLine);
		if(partSettings.attack_damage != 0.0f)
			builder.append("Attack Damage: ").append(partSettings.attack_damage).append(newLine);
		if(partSettings.attack_damage_multiplier != 1.0f)
			builder.append("Attack Damage Multiplier: ").append(partSettings.attack_damage_multiplier).append(newLine);
		if(partSettings.attack_speed != 0.0f)
			builder.append("Attack Speed: ").append(partSettings.attack_speed).append(newLine);
		if(partSettings.attack_speed_multiplier != 1.0f)
			builder.append("Attack Speed Multiplier: ").append(partSettings.attack_speed_multiplier).append(newLine);
		if(partSettings.enchantability != 0)
			builder.append("Enchantability: ").append(partSettings.enchantability).append(newLine);
		if(partSettings.enchantability_multiplier != 1.0f)
			builder.append("Enchantability Multiplier: ").append(partSettings.enchantability_multiplier).append(newLine);
		if(partSettings.luck != 0.0f)
			builder.append("Luck: ").append(partSettings.luck).append(newLine);
		if(partSettings.luck_multiplier != 1.0f)
			builder.append("Luck Multiplier: ").append(partSettings.luck_multiplier);
		return builder.toString();
	}
}
