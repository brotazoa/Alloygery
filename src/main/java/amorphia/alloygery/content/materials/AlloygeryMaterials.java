package amorphia.alloygery.content.materials;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import amorphia.alloygery.content.materials.registry.AlloygeryMaterialRegistry;
import net.minecraft.util.Identifier;

public class AlloygeryMaterials
{
	public static final AlloygeryMaterial UNKNOWN = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("unknown").build());
	public static final AlloygeryMaterial HIDDEN = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("hidden").color(0).build());

	public static final AlloygeryMaterial TIN;
	public static final AlloygeryMaterial COPPER;
	public static final AlloygeryMaterial BRONZE;
	public static final AlloygeryMaterial IRON;
	public static final AlloygeryMaterial GOLD;
	public static final AlloygeryMaterial EMERALD;
	public static final AlloygeryMaterial ANTANIUM;
	public static final AlloygeryMaterial DIAMOND;
	public static final AlloygeryMaterial STEEL;
	public static final AlloygeryMaterial NETHERITE;
	public static final AlloygeryMaterial NICKEL;
	public static final AlloygeryMaterial INVAR;
	public static final AlloygeryMaterial CONSTANTAN;
	public static final AlloygeryMaterial CUPRONICKEL;
	public static final AlloygeryMaterial TITANIUM;
	public static final AlloygeryMaterial TITANIUM_GOLD;
	public static final AlloygeryMaterial NITINOL;

	public static final AlloygeryMaterial VANILLA_STICK;
	public static final AlloygeryMaterial LEATHER;

	private static AlloygeryMaterial register(AlloygeryMaterial material)
	{
		return register(Alloygery.identifier("materials/" + material.getMaterialName()), material);
	}

	private static AlloygeryMaterial register(Identifier identifier, AlloygeryMaterial material)
	{
		return AlloygeryMaterialRegistry.register(identifier, material);
	}

	static
	{
		TIN = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("tin")
				.color(14547455)
				.repairIngredientFromTag("c:tin_ingots")
				.build());

		COPPER = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("copper")
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

		BRONZE = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("bronze")
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

		IRON = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("iron")
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

		GOLD = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("gold")
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

		EMERALD = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("emerald")
				.color(1564002)
				.repairIngredientFromTag("c:emeralds")
				.toolProperty().forPart(ToolPartType.UPGRADE).operation(ToolPropertyOperation.ADDITION)
					.property(ToolPropertyType.ENCHANTABILITY, 5).next()
					.operation(ToolPropertyOperation.MULTIPLY_TOTAL)
					.property(ToolPropertyType.DURABILITY, 1.5f).build()
				.build());

		ANTANIUM = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("antanium")
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

		DIAMOND = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("diamond")
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

		STEEL = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("steel")
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

		NETHERITE = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("netherite")
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

		NICKEL = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("nickel")
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

		INVAR = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("invar")
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

		CONSTANTAN = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("constantan")
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

		CUPRONICKEL = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("cupronickel")
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

		TITANIUM = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("titanium")
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

		TITANIUM_GOLD = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("titanium_gold")
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

		NITINOL = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("nitinol")
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



		VANILLA_STICK = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("wooden")
				.color(6835742)
				.repairIngredientFromTag("minecraft:planks")
				.build());

		LEATHER = register(new AlloygeryMaterial.AlloygeryMaterialBuilder("leather")
				.color(14117699)
				.repairIngredientFromTag("c:leathers")
				.build());
	}
}
