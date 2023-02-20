package amorphia.alloygery.compat.create;

import amorphia.alloygery.compat.create.CreateModule;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import amorphia.alloygery.content.tools.registry.AlloygeryToolMaterialRegistry;

public class CreateToolMaterials
{
	public static AlloygeryToolMaterial ZINC;
	public static AlloygeryToolMaterial ANDESITE_ALLOY;
	public static AlloygeryToolMaterial BRASS;
	public static AlloygeryToolMaterial REFINED_RADIANCE;
	public static AlloygeryToolMaterial SHADOW_STEEL;

	private static AlloygeryToolMaterial register(AlloygeryToolMaterial material)
	{
		return AlloygeryToolMaterialRegistry.register(CreateModule.identify("tool_materials/" + material.getMaterialName()), material);
	}

	public static void init()
	{
		ZINC = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("zinc")
				.color(15790570)
				.repairIngredientFromTag("c:ingots/zinc")
				.build()
		);

		ANDESITE_ALLOY = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("andesite_alloy")
				.color(13224634)
				.repairIngredientFromTag("create_compat:andesite_alloy_ingots")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
				.property(ToolPropertyType.MINING_LEVEL, 1).next()
				.property(ToolPropertyType.DURABILITY, 600).next()
				.property(ToolPropertyType.ENCHANTABILITY, 8).next()
				.property(ToolPropertyType.MINING_SPEED, 5.0f).next()
				.property(ToolPropertyType.ATTACK_DAMAGE, 1.5f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
				.property(ToolPropertyType.MINING_SPEED, -0.5f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.MINING_SPEED, 0.9f).build()
				.build()
		);

		BRASS = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("brass")
				.color(16501864)
				.repairIngredientFromTag("c:ingots/brass")
				.toolProperty().forPart(ToolPartType.HEAD).operation(ToolPropertyOperation.BASE)
				.property(ToolPropertyType.MINING_LEVEL, 2).next()
				.property(ToolPropertyType.DURABILITY, 200).next()
				.property(ToolPropertyType.ENCHANTABILITY, 17.0f).next()
				.property(ToolPropertyType.MINING_SPEED, 7.0f).next()
				.property(ToolPropertyType.ATTACK_DAMAGE, 2.5f).build()
				.toolProperty().forPart(ToolPartType.BINDING).operation(ToolPropertyOperation.ADDITION)
				.property(ToolPropertyType.DURABILITY, -50).next()
				.property(ToolPropertyType.MINING_SPEED, -0.5f).build()
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).next()
				.property(ToolPropertyType.MINING_SPEED, 0.8f).next()
				.property(ToolPropertyType.ATTACK_DAMAGE, 0.8f).build()
				.build()
		);

		REFINED_RADIANCE = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("refined_radiance")
				.color(16777206)
				.repairIngredientFromTag("create_compat:refined_radiance_ingots")
				.upgradeIngredientFromTag("create_compat:refined_radiance_ingots")
				.toolProperty().forPart(ToolPartType.UPGRADE).operation(ToolPropertyOperation.ADDITION)
				.property(ToolPropertyType.DURABILITY, 1000).next()
				.property(ToolPropertyType.FIREPROOF, 2.0f).next()
				.operation(ToolPropertyOperation.MULTIPLY_TOTAL)
				.property(ToolPropertyType.MINING_SPEED, 5.0f).next()
				.property(ToolPropertyType.ATTACK_DAMAGE, 0.1f).build()
				.build()
		);

		SHADOW_STEEL = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("shadow_steel")
				.color(3682636)
				.repairIngredientFromTag("create_compat:shadow_steel_ingots")
				.upgradeIngredientFromTag("create_compat:shadow_steel_ingots")
				.toolProperty().forPart(ToolPartType.UPGRADE).operation(ToolPropertyOperation.ADDITION)
				.property(ToolPropertyType.DURABILITY, 1000).next()
				.property(ToolPropertyType.FIREPROOF, 2.0f).next()
				.operation(ToolPropertyOperation.MULTIPLY_TOTAL)
				.property(ToolPropertyType.MINING_SPEED, 0.1f).next()
				.property(ToolPropertyType.ATTACK_DAMAGE, 5.0f).build()
				.build()
		);
	}
}
