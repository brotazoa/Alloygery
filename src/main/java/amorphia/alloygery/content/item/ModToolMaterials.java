package amorphia.alloygery.content.item;

import amorphia.alloygery.registry.ModItems;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ModToolMaterials implements ToolMaterial
{
	COPPER(ModMiningLevels.STONE, 190, 4.0f, 1.0f, 5, Ingredient.ofItems(Items.COPPER_INGOT)),
	BRONZE(ModMiningLevels.BRONZE, 250, 5.0f, 2.0f, 14, Ingredient.ofItems(ModItems.BRONZE_INGOT)),
	IRON(ModMiningLevels.IRON, 700, 6.0f, 2.0f, 14, Ingredient.ofItems(Items.IRON_INGOT)),
	ANTANIUM(ModMiningLevels.BRONZE, 858, 10.0f, 1.0f, 20, Ingredient.ofItems(ModItems.ANTANIUM_INGOT)),
	STEEL(ModMiningLevels.STEEL, 1561, 8.0f, 3.0f, 10, Ingredient.ofItems(ModItems.STEEL_INGOT)),
	NICKEL(ModMiningLevels.NICKEL, 2031, 9.0f, 4.0f, 15, Ingredient.ofItems(ModItems.NICKEL_INGOT)),
	INVAR(ModMiningLevels.NICKEL, 2731, 9.0f, 4.0f, 15, Ingredient.ofItems(ModItems.INVAR_INGOT)),
	CONSTANTAN(ModMiningLevels.NICKEL, 2221, 9.0f, 4.0f, 15, Ingredient.ofItems(ModItems.CONSTANTAN_INGOT)),
	CUPRONICKEL(ModMiningLevels.NICKEL, 2471, 9.0f, 4.0f, 15, Ingredient.ofItems(ModItems.CUPRONICKEL_INGOT)),
	TITANIUM(ModMiningLevels.TITANIUM, 2892, 10.0f, 5.0f, 18, Ingredient.ofItems(ModItems.TITANIUM_INGOT)),
	TITANIUM_GOLD(ModMiningLevels.NICKEL, 3127, 12.0f, 5.0f, 25, Ingredient.ofItems(ModItems.TITANIUM_GOLD_INGOT)),
	NITINOL(ModMiningLevels.TITANIUM, 3362, 10.0f, 5.0f, 18, Ingredient.ofItems(ModItems.NITINOL_INGOT)),
	;

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Ingredient repairIngredient;

	ModToolMaterials(int level, int uses, float speed, float damage, int enchantmentValue, Ingredient repairIngredient)
	{
		this.level = level;
		this.uses = uses;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = repairIngredient;
	}

	@Override
	public int getDurability()
	{
		return this.uses;
	}

	@Override
	public float getMiningSpeedMultiplier()
	{
		return this.speed;
	}

	@Override
	public float getAttackDamage()
	{
		return this.damage;
	}

	@Override
	public int getMiningLevel()
	{
		return this.level;
	}

	@Override
	public int getEnchantability()
	{
		return this.enchantmentValue;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return this.repairIngredient;
	}
}
