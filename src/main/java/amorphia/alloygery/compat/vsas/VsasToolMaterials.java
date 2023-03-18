package amorphia.alloygery.compat.vsas;

import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import amorphia.alloygery.content.tools.registry.AlloygeryToolMaterialRegistry;

public class VsasToolMaterials
{
	public static AlloygeryToolMaterial ACACIA;
	public static AlloygeryToolMaterial BIRCH;
	public static AlloygeryToolMaterial CRIMSON;
	public static AlloygeryToolMaterial DARK_OAK;
	public static AlloygeryToolMaterial JUNGLE;
	public static AlloygeryToolMaterial OAK;
	public static AlloygeryToolMaterial SPRUCE;
	public static AlloygeryToolMaterial WARPED;

	private static AlloygeryToolMaterial register(AlloygeryToolMaterial material)
	{
		return AlloygeryToolMaterialRegistry.register(VsasModule.identify("tool_materials/" + material.getMaterialName()), material);
	}

	static void init()
	{
		ACACIA = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("acacia")
				.color(12215095)
				.repairIngredientFromTag("minecraft:planks")
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).build()
				.build()
		);

		BIRCH = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("birch")
				.color(14139781)
				.repairIngredientFromTag("minecraft:planks")
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).build()
				.build()
		);

		CRIMSON = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("crimson")
				.color(8272470)
				.repairIngredientFromTag("minecraft:planks")
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).build()
				.build()
		);

		DARK_OAK = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("dark_oak")
				.color(5190168)
				.repairIngredientFromTag("minecraft:planks")
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).build()
				.build()
		);

		JUNGLE = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("jungle")
				.color(12093284)
				.repairIngredientFromTag("minecraft:planks")
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).build()
				.build()
		);

		OAK = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("oak")
				.color(12096607)
				.repairIngredientFromTag("minecraft:planks")
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).build()
				.build()
		);

		SPRUCE = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("spruce")
				.color(8544570)
				.repairIngredientFromTag("minecraft:planks")
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).build()
				.build()
		);

		WARPED = register(new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder("warped")
				.color(3769218)
				.repairIngredientFromTag("minecraft:planks")
				.toolProperty().forPart(ToolPartType.HANDLE).operation(ToolPropertyOperation.MULTIPLY_BASE)
				.property(ToolPropertyType.DURABILITY, 0.8f).build()
				.build()
		);
	}
}
