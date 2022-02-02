package amorphia.alloygery.content.item;

import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.registry.ModItems;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ModToolMaterials implements ToolMaterial
{
	//@formatter:off
	COPPER(AlloygeryConfig.copperGear, Ingredient.ofItems(Items.COPPER_INGOT)),
	BRONZE(AlloygeryConfig.bronzeGear, Ingredient.ofItems(ModItems.BRONZE_INGOT)),
	IRON(AlloygeryConfig.ironGear, Ingredient.ofItems(Items.IRON_INGOT)),
	ANTANIUM(AlloygeryConfig.antaniumGear, Ingredient.ofItems(ModItems.ANTANIUM_INGOT)),
	STEEL(AlloygeryConfig.steelGear, Ingredient.ofItems(ModItems.STEEL_INGOT)),
	NICKEL(AlloygeryConfig.nickelGear, Ingredient.ofItems(ModItems.NICKEL_INGOT)),
	INVAR(AlloygeryConfig.invarGear, Ingredient.ofItems(ModItems.INVAR_INGOT)),
	CONSTANTAN(AlloygeryConfig.constantanGear, Ingredient.ofItems(ModItems.CONSTANTAN_INGOT)),
	CUPRONICKEL(AlloygeryConfig.cupronickelGear, Ingredient.ofItems(ModItems.CUPRONICKEL_INGOT)),
	TITANIUM(AlloygeryConfig.titaniumGear, Ingredient.ofItems(ModItems.TITANIUM_INGOT)),
	TITANIUM_GOLD(AlloygeryConfig.titaniumGoldGear, Ingredient.ofItems(ModItems.TITANIUM_GOLD_INGOT)),
	NITINOL(AlloygeryConfig.nitinolGear, Ingredient.ofItems(ModItems.NITINOL_INGOT)),
	;
	//@formatter:on

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Ingredient repairIngredient;

	ModToolMaterials(AlloygeryConfig.GearStatsConfigGroup config, Ingredient repairIngredient)
	{
		assert config != null;
		assert repairIngredient != null;

		this.level = config.miningLevel.getValue();
		this.uses = config.uses.getValue();
		this.speed = config.speed.getValue();
		this.damage = config.damage.getValue();
		this.enchantmentValue = config.enchantability.getValue();
		this.repairIngredient = repairIngredient;
	}

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
