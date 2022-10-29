package amorphia.alloygery.content.tools.material;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import amorphia.alloygery.content.tools.registry.ToolMaterialRegistry;
import net.minecraft.util.Identifier;

public class ToolMaterials
{
	public static final ToolMaterial UNKNOWN = register(new ToolMaterial.ToolMaterialBuilder("unknown").build());
	public static final ToolMaterial HIDDEN = register(new ToolMaterial.ToolMaterialBuilder("hidden").color(0).build());

	public static final ToolMaterial TIN;
	public static final ToolMaterial COPPER;
	public static final ToolMaterial BRONZE;
	public static final ToolMaterial IRON;
	public static final ToolMaterial GOLD;
	public static final ToolMaterial EMERALD;
	public static final ToolMaterial ANTANIUM;
	public static final ToolMaterial DIAMOND;
	public static final ToolMaterial STEEL;
	public static final ToolMaterial NETHERITE;
	public static final ToolMaterial NICKEL;
	public static final ToolMaterial INVAR;
	public static final ToolMaterial CONSTANTAN;
	public static final ToolMaterial CUPRONICKEL;
	public static final ToolMaterial TITANIUM;
	public static final ToolMaterial TITANIUM_GOLD;
	public static final ToolMaterial NITINOL;

	public static final ToolMaterial VANILLA_STICK;
	public static final ToolMaterial LEATHER;

	private static ToolMaterial register(ToolMaterial material)
	{
		return register(Alloygery.identifier("materials/" + material.getMaterialName()), material);
	}

	private static ToolMaterial register(Identifier identifier, ToolMaterial material)
	{
		return ToolMaterialRegistry.register(identifier, material);
	}

	static
	{
		TIN = register(new ToolMaterial.ToolMaterialBuilder("tin")
				.color(14547455)
				.repairIngredientFromTag("c:tin_ingots")
				.build());

		COPPER = register(new ToolMaterial.ToolMaterialBuilder("copper")
				.color(15433553)
				.repairIngredientFromTag("c:copper_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 1).next()
					.property(ToolPropertyType.DURABILITY, 150).next()
					.property(ToolPropertyType.ENCHANTABILITY, 10).next()
					.property(ToolPropertyType.MINING_SPEED, 4.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 1.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.DURABILITY, -100).next()
					.property(ToolPropertyType.ENCHANTABILITY, 2).next()
					.property(ToolPropertyType.MINING_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, -1.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY, 0.7f).next()
					.property(ToolPropertyType.MINING_SPEED, 0.6f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.8f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 0.4f).build()
				.build());

		BRONZE = register(new ToolMaterial.ToolMaterialBuilder("bronze")
				.color(7556410)
				.repairIngredientFromTag("c:bronze_ingots")
				.toolProperty().forPart(ToolPartType.HEAD)
					.operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL)
					.value(2).next()
					.property(ToolPropertyType.DURABILITY)
					.value(250).next()
					.property(ToolPropertyType.ENCHANTABILITY)
					.value(15).next()
					.property(ToolPropertyType.MINING_SPEED)
					.value(5.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED)
					.value(0.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE)
					.value(2.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING)
					.operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.DURABILITY)
					.value(-50).next()
					.property(ToolPropertyType.ENCHANTABILITY)
					.value(5).build()
				.toolProperty().forPart(ToolPartType.HANDLE)
					.operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY)
					.value(0.9f).next()
					.property(ToolPropertyType.MINING_SPEED)
					.value(0.8f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE)
					.value(0.8f).build()
				.build());

		IRON = register(new ToolMaterial.ToolMaterialBuilder("iron")
				.color(15198183)
				.repairIngredientFromTag("c:iron_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 2).next()
					.property(ToolPropertyType.DURABILITY, 700).next()
					.property(ToolPropertyType.ENCHANTABILITY, 14).next()
					.property(ToolPropertyType.MINING_SPEED, 6.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 2.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.ENCHANTABILITY, 5).next()
					.property(ToolPropertyType.MINING_SPEED, 1.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY, 1.1f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 1.1f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 0.9f).build()
				.build());

		GOLD = register(new ToolMaterial.ToolMaterialBuilder("gold")
				.color(16573743)
				.repairIngredientFromTag("c:gold_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 0).next()
					.property(ToolPropertyType.DURABILITY, 32).next()
					.property(ToolPropertyType.ENCHANTABILITY, 22).next()
					.property(ToolPropertyType.MINING_SPEED, 12.0f).next()
					.property(ToolPropertyType.PIGLIN_LOVED, 1.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.MINING_LEVEL, -1).next()
					.property(ToolPropertyType.DURABILITY, -200).next()
					.property(ToolPropertyType.ENCHANTABILITY, 10).next()
					.property(ToolPropertyType.MINING_SPEED, 5.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, -3.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY, 0.5f).next()
					.property(ToolPropertyType.ENCHANTABILITY, 1.4f).next()
					.property(ToolPropertyType.MINING_SPEED, 1.5f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.8f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 0.3f).build()
				.toolProperty().forPart(ToolPartType.UPGRADE).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.MINING_LEVEL, -1).next()
					.property(ToolPropertyType.PIGLIN_LOVED, 1).next()
					.operation(ToolPropertyOperation.MULTIPLY_TOTAL)
					.property(ToolPropertyType.DURABILITY, 0.7f).next()
					.property(ToolPropertyType.ENCHANTABILITY, 1.8f).next()
					.property(ToolPropertyType.MINING_SPEED, 1.5f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 1.2f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 0.5f).build()
				.build());

		EMERALD = register(new ToolMaterial.ToolMaterialBuilder("emerald")
				.color(1564002)
				.repairIngredientFromTag("c:emeralds")
				.toolProperty().forPart(ToolPartType.UPGRADE).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.ENCHANTABILITY, 5).next()
					.operation(ToolPropertyOperation.MULTIPLY_TOTAL)
					.property(ToolPropertyType.DURABILITY, 1.5f).build()
				.build());

		ANTANIUM = register(new ToolMaterial.ToolMaterialBuilder("antanium")
				.color(14329677)
				.repairIngredientFromTag("c:antanium_ingots")
				.toolProperty().forPart(ToolPartType.HEAD)
					.operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL)
					.value(1).next()
					.property(ToolPropertyType.DURABILITY)
					.value(1000).next()
					.property(ToolPropertyType.ENCHANTABILITY)
					.value(20).next()
					.property(ToolPropertyType.MINING_SPEED)
					.value(10.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED)
					.value(0.8f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE)
					.value(1.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING)
					.operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.ENCHANTABILITY)
					.value(8)
					.next()
					.property(ToolPropertyType.MINING_SPEED)
					.value(4.0f)
					.next()
					.property(ToolPropertyType.ATTACK_DAMAGE)
					.value(-1.0f)
					.build()
				.toolProperty().forPart(ToolPartType.HANDLE)
					.operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.ENCHANTABILITY)
					.value(1.2f)
					.next()
					.property(ToolPropertyType.MINING_SPEED)
					.value(1.4f)
					.next()
					.property(ToolPropertyType.ATTACK_SPEED)
					.value(0.9f)
					.next()
					.property(ToolPropertyType.ATTACK_DAMAGE)
					.value(0.7f)
					.build()
				.build());

		DIAMOND = register(new ToolMaterial.ToolMaterialBuilder("diamond")
				.color(3402699)
				.repairIngredientFromTag("c:diamonds")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 3).next()
					.property(ToolPropertyType.DURABILITY, 1561).next()
					.property(ToolPropertyType.MINING_SPEED, 8.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 3.0f).build()
				.toolProperty().forPart(ToolPartType.UPGRADE).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.MINING_LEVEL, 1).next()
					.property(ToolPropertyType.DURABILITY, 500).next()
					.property(ToolPropertyType.ENCHANTABILITY, -5).build()
				.build());

		STEEL = register(new ToolMaterial.ToolMaterialBuilder("steel")
				.color(4408907)
				.repairIngredientFromTag("c:steel_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 3).next()
					.property(ToolPropertyType.DURABILITY, 2892).next()
					.property(ToolPropertyType.ENCHANTABILITY, 8).next()
					.property(ToolPropertyType.MINING_SPEED, 8.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -0.5f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 4.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.DURABILITY, 400).next()
					.property(ToolPropertyType.ENCHANTABILITY, -5).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 1.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY, 1.5f).next()
					.property(ToolPropertyType.ENCHANTABILITY, 0.9f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.9f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 1.1f).build()
				.build());

		NETHERITE = register(new ToolMaterial.ToolMaterialBuilder("netherite")
				.color(5192766)
				.repairIngredientFromTag("c:netherite_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 4).next()
					.property(ToolPropertyType.DURABILITY, 2031).next()
					.property(ToolPropertyType.ENCHANTABILITY, 15).next()
					.property(ToolPropertyType.MINING_SPEED, 9.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 4.0f).next()
					.property(ToolPropertyType.FIREPROOF, 1).build()
				.toolProperty().forPart(ToolPartType.UPGRADE).operation(ToolPropertyOperation.MULTIPLY_TOTAL)
					.property(ToolPropertyType.DURABILITY, 1.5f).next()
					.property(ToolPropertyType.MINING_SPEED, 1.1f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 1.3f).next().operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.FIREPROOF, 1).build()
				.build());

		NICKEL = register(new ToolMaterial.ToolMaterialBuilder("nickel")
				.color(6314062)
				.repairIngredientFromTag("c:nickel_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 4).next()
					.property(ToolPropertyType.DURABILITY, 1784).next()
					.property(ToolPropertyType.ENCHANTABILITY, 5).next()
					.property(ToolPropertyType.MINING_SPEED, 8.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 4.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.DURABILITY, 50).next()
					.property(ToolPropertyType.ENCHANTABILITY, -1).next()
					.property(ToolPropertyType.MINING_SPEED, -2.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 2.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY, 0.9f).next()
					.property(ToolPropertyType.ENCHANTABILITY, 0.7f).next()
					.property(ToolPropertyType.MINING_SPEED, 0.7f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.7f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 1.2f).build()
				.build());

		INVAR = register(new ToolMaterial.ToolMaterialBuilder("invar")
				.color(10789019)
				.repairIngredientFromTag("c:invar_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 4).next()
					.property(ToolPropertyType.DURABILITY, 3127).next()
					.property(ToolPropertyType.ENCHANTABILITY, 5).next()
					.property(ToolPropertyType.MINING_SPEED, 7.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 7.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.DURABILITY, 300).next()
					.property(ToolPropertyType.ENCHANTABILITY, -5).next()
					.property(ToolPropertyType.MINING_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 4.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY, 1.3f).next()
					.property(ToolPropertyType.ENCHANTABILITY, 0.6f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.7f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 1.4f).build()
				.build());

		CONSTANTAN = register(new ToolMaterial.ToolMaterialBuilder("constantan")
				.color(11558984)
				.repairIngredientFromTag("c:constantan_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 4).next()
					.property(ToolPropertyType.DURABILITY, 2031).next()
					.property(ToolPropertyType.ENCHANTABILITY, 8).next()
					.property(ToolPropertyType.MINING_SPEED, 5.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -0.2f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 3.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.DURABILITY, 55).next()
					.property(ToolPropertyType.MINING_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 2.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.MINING_SPEED, 0.8f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.7f).build()
				.build());

		CUPRONICKEL = register(new ToolMaterial.ToolMaterialBuilder("cupronickel")
				.color(5257772)
				.repairIngredientFromTag("c:cupronickel_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 4).next()
					.property(ToolPropertyType.DURABILITY, 2121).next()
					.property(ToolPropertyType.ENCHANTABILITY, 8).next()
					.property(ToolPropertyType.MINING_SPEED, 6.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -0.2f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 5.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.DURABILITY, 75).next()
					.property(ToolPropertyType.MINING_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, -1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 2.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY, 0.9f).next()
					.property(ToolPropertyType.MINING_SPEED, 0.7f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 0.8f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 1.1f).build()
				.build());

		TITANIUM = register(new ToolMaterial.ToolMaterialBuilder("titanium")
				.color(5990506)
				.repairIngredientFromTag("c:titanium_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 5).next()
					.property(ToolPropertyType.DURABILITY, 2221).next()
					.property(ToolPropertyType.ENCHANTABILITY, 10).next()
					.property(ToolPropertyType.MINING_SPEED, 9.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 2.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 3.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.ATTACK_SPEED, 2.5f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.ATTACK_SPEED, 1.3f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 0.9f).build()
				.build());

		TITANIUM_GOLD = register(new ToolMaterial.ToolMaterialBuilder("titanium_gold")
				.color(13086590)
				.repairIngredientFromTag("c:titanium_gold_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 4).next()
					.property(ToolPropertyType.DURABILITY, 1600).next()
					.property(ToolPropertyType.ENCHANTABILITY, 25).next()
					.property(ToolPropertyType.MINING_SPEED, 14.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 2.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 2.0f).next()
					.property(ToolPropertyType.PIGLIN_LOVED, 1.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.DURABILITY, -75).next()
					.property(ToolPropertyType.ENCHANTABILITY, 12).next()
					.property(ToolPropertyType.MINING_SPEED, 3.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 2.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.DURABILITY, 0.9f).next()
					.property(ToolPropertyType.ENCHANTABILITY, 1.2f).next()
					.property(ToolPropertyType.MINING_SPEED, 1.4f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 1.2f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 0.7f).build()
				.build());

		NITINOL = register(new ToolMaterial.ToolMaterialBuilder("nitinol")
				.color(6185051)
				.repairIngredientFromTag("c:nitinol_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
					.property(ToolPropertyType.MINING_LEVEL, 5).next()
					.property(ToolPropertyType.DURABILITY, 2471).next()
					.property(ToolPropertyType.ENCHANTABILITY, 10).next()
					.property(ToolPropertyType.MINING_SPEED, 9.0f).next()
					.property(ToolPropertyType.ATTACK_SPEED, 1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 4.0f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.ATTACK_SPEED, 1.0f).next()
					.property(ToolPropertyType.ATTACK_DAMAGE, 1.0f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
					.property(ToolPropertyType.ATTACK_SPEED, 1.1f).build()
				.build());



		VANILLA_STICK = register(new ToolMaterial.ToolMaterialBuilder("wooden")
				.color(6835742)
				.repairIngredientFromTag("minecraft:planks")
				.build());

		LEATHER = register(new ToolMaterial.ToolMaterialBuilder("leather")
				.color(14117699)
				.repairIngredientFromTag("c:leathers")
				.build());
	}
}
