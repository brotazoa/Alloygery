package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialHelper;
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
		return alloygeryMaterial.head_durability;
	}

	@Override
	public float getMiningSpeedMultiplier()
	{
		return alloygeryMaterial.speed;
	}

	@Override
	public float getAttackDamage()
	{
		return alloygeryMaterial.damage;
	}

	@Override
	public int getMiningLevel()
	{
		return alloygeryMaterial.level;
	}

	@Override
	public int getEnchantability()
	{
		return alloygeryMaterial.tool_enchantability;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return AlloygeryMaterialHelper.REPAIR_INGREDIENT_MAP.get(alloygeryMaterial);
	}
}
