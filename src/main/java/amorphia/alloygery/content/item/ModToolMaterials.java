package amorphia.alloygery.content.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.registry.ModItems;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public enum ModToolMaterials implements ToolMaterial
{
	COPPER(Alloygery.CONFIG.copperGear.miningLevel, Alloygery.CONFIG.copperGear.uses, Alloygery.CONFIG.copperGear.speed, Alloygery.CONFIG.copperGear.damage, Alloygery.CONFIG.copperGear.enchantability, Ingredient.ofItems(Items.COPPER_INGOT)),
	BRONZE(Alloygery.CONFIG.bronzeGear.miningLevel, Alloygery.CONFIG.bronzeGear.uses, Alloygery.CONFIG.bronzeGear.speed, Alloygery.CONFIG.bronzeGear.damage, Alloygery.CONFIG.bronzeGear.enchantability, Ingredient.ofItems(ModItems.BRONZE_INGOT)),
	IRON(Alloygery.CONFIG.ironGear.miningLevel, Alloygery.CONFIG.ironGear.uses, Alloygery.CONFIG.ironGear.speed, Alloygery.CONFIG.ironGear.damage, Alloygery.CONFIG.ironGear.enchantability, Ingredient.ofItems(Items.IRON_INGOT)),
	ANTANIUM(Alloygery.CONFIG.antaniumGear.miningLevel, Alloygery.CONFIG.antaniumGear.uses, Alloygery.CONFIG.antaniumGear.speed, Alloygery.CONFIG.antaniumGear.damage, Alloygery.CONFIG.antaniumGear.enchantability, Ingredient.ofItems(ModItems.ANTANIUM_INGOT)),
	STEEL(Alloygery.CONFIG.steelGear.miningLevel, Alloygery.CONFIG.steelGear.uses, Alloygery.CONFIG.steelGear.speed, Alloygery.CONFIG.steelGear.damage, Alloygery.CONFIG.steelGear.enchantability, Ingredient.ofItems(ModItems.STEEL_INGOT)),
	NICKEL(Alloygery.CONFIG.nickelGear.miningLevel, Alloygery.CONFIG.nickelGear.uses, Alloygery.CONFIG.nickelGear.speed, Alloygery.CONFIG.nickelGear.damage, Alloygery.CONFIG.nickelGear.enchantability, Ingredient.ofItems(ModItems.NICKEL_INGOT)),
	INVAR(Alloygery.CONFIG.invarGear.miningLevel, Alloygery.CONFIG.invarGear.uses, Alloygery.CONFIG.invarGear.speed, Alloygery.CONFIG.invarGear.damage, Alloygery.CONFIG.invarGear.enchantability, Ingredient.ofItems(ModItems.INVAR_INGOT)),
	CONSTANTAN(Alloygery.CONFIG.constantanGear.miningLevel, Alloygery.CONFIG.constantanGear.uses, Alloygery.CONFIG.constantanGear.speed, Alloygery.CONFIG.constantanGear.damage, Alloygery.CONFIG.constantanGear.enchantability, Ingredient.ofItems(ModItems.CONSTANTAN_INGOT)),
	CUPRONICKEL(Alloygery.CONFIG.cupronickelGear.miningLevel, Alloygery.CONFIG.cupronickelGear.uses, Alloygery.CONFIG.cupronickelGear.speed, Alloygery.CONFIG.cupronickelGear.damage, Alloygery.CONFIG.cupronickelGear.enchantability, Ingredient.ofItems(ModItems.CUPRONICKEL_INGOT)),
	TITANIUM(Alloygery.CONFIG.titaniumGear.miningLevel, Alloygery.CONFIG.titaniumGear.uses, Alloygery.CONFIG.titaniumGear.speed, Alloygery.CONFIG.titaniumGear.damage, Alloygery.CONFIG.titaniumGear.enchantability, Ingredient.ofItems(ModItems.TITANIUM_INGOT)),
	TITANIUM_GOLD(Alloygery.CONFIG.titaniumGoldGear.miningLevel, Alloygery.CONFIG.titaniumGoldGear.uses, Alloygery.CONFIG.titaniumGoldGear.speed, Alloygery.CONFIG.titaniumGoldGear.damage, Alloygery.CONFIG.titaniumGoldGear.enchantability, Ingredient.ofItems(ModItems.TITANIUM_GOLD_INGOT)),
	NITINOL(Alloygery.CONFIG.nitinolGear.miningLevel, Alloygery.CONFIG.nitinolGear.uses, Alloygery.CONFIG.nitinolGear.speed, Alloygery.CONFIG.nitinolGear.damage, Alloygery.CONFIG.nitinolGear.enchantability, Ingredient.ofItems(ModItems.NITINOL_INGOT)),
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
