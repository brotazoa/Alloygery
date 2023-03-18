package amorphia.alloygery.content.tools.item.tool;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class EmptyToolMaterial implements ToolMaterial
{
	public static final EmptyToolMaterial INSTANCE = new EmptyToolMaterial();

	private EmptyToolMaterial(){} //no op

	@Override
	public int getDurability()
	{
		return 1;
	}

	@Override
	public float getMiningSpeedMultiplier()
	{
		return 1.0f;
	}

	@Override
	public float getAttackDamage()
	{
		return 0;
	}

	@Override
	public int getMiningLevel()
	{
		return -1;
	}

	@Override
	public int getEnchantability()
	{
		return 0;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return Ingredient.EMPTY;
	}
}
