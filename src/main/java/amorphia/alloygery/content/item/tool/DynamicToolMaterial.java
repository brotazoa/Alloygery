package amorphia.alloygery.content.item.tool;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class DynamicToolMaterial implements ToolMaterial
{
	private final AlloygeryMaterial alloygeryMaterial;

	public DynamicToolMaterial(AlloygeryMaterial alloygeryMaterial)
	{
		this.alloygeryMaterial = alloygeryMaterial;
	}

	@Override
	public int getDurability()
	{
		return alloygeryMaterial.tool_base.durability;
	}

	@Override
	public float getMiningSpeedMultiplier()
	{
		return alloygeryMaterial.tool_base.mining_speed;
	}

	@Override
	public float getAttackDamage()
	{
		return alloygeryMaterial.tool_base.attack_damage;
	}

	@Override
	public int getMiningLevel()
	{
		return alloygeryMaterial.tool_base.mining_level;
	}

	@Override
	public int getEnchantability()
	{
		return alloygeryMaterial.tool_base.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return AlloygeryToolMaterialHelper.REPAIR_INGREDIENT_MAP.get(alloygeryMaterial);
	}
}
