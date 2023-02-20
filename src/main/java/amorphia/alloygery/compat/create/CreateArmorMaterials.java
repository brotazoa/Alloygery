package amorphia.alloygery.compat.create;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.ArmorType;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.property.ArmorPropertyOperation;
import amorphia.alloygery.content.armor.property.ArmorPropertyType;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;

public class CreateArmorMaterials
{
	public static AlloygeryArmorMaterial ANDESITE_ALLOY_PLATE;
	public static AlloygeryArmorMaterial ADNESITE_ALLOY_HEAVY_PLATE;
	public static AlloygeryArmorMaterial BRASS_PLATE;

	private static AlloygeryArmorMaterial register(AlloygeryArmorMaterial material)
	{
		return AlloygeryArmorMaterialRegistry.register(CreateModule.identify("armor_materials/" + material.getMaterialName()), material);
	}

	public static void init()
	{
		final AlloygeryArmorMaterial STATIC_HALF_PLATE_TEMPLATE = new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder("half_plate_template")
				.layer(ArmorLayer.PLATE)

				.itemTexture(ArmorType.HELMET, Alloygery.identifier("armor/parts/helmet/helmet_half_plate"))
				.itemTexture(ArmorType.CHESTPLATE, Alloygery.identifier("armor/parts/chestplate/chestplate_half_plate"))
				.itemTexture(ArmorType.LEGGINGS, Alloygery.identifier("armor/parts/leggings/leggings_half_plate"))
				.itemTexture(ArmorType.BOOTS, Alloygery.identifier("armor/parts/boots/boots_half_plate"))

				.modelTexture(ArmorType.HELMET, Alloygery.identifier("textures/armor/model/helmet/helmet_half_plate.png"))
				.modelTexture(ArmorType.CHESTPLATE, Alloygery.identifier("textures/armor/model/chestplate/chestplate_half_plate.png"))
				.modelTexture(ArmorType.LEGGINGS, Alloygery.identifier("textures/armor/model/leggings/leggings_half_plate.png"))
				.modelTexture(ArmorType.BOOTS, Alloygery.identifier("textures/armor/model/boots/boots_half_plate.png"))
				.build();

		final AlloygeryArmorMaterial STATIC_FULL_PLATE_TEMPLATE = new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder("full_plate_template")
				.layer(ArmorLayer.PLATE)

				.itemTexture(ArmorType.HELMET, Alloygery.identifier("armor/parts/helmet/helmet_full_plate"))
				.itemTexture(ArmorType.CHESTPLATE, Alloygery.identifier("armor/parts/chestplate/chestplate_full_plate"))
				.itemTexture(ArmorType.LEGGINGS, Alloygery.identifier("armor/parts/leggings/leggings_full_plate"))
				.itemTexture(ArmorType.BOOTS, Alloygery.identifier("armor/parts/boots/boots_full_plate"))

				.modelTexture(ArmorType.HELMET, Alloygery.identifier("textures/armor/model/helmet/helmet_full_plate.png"))
				.modelTexture(ArmorType.CHESTPLATE, Alloygery.identifier("textures/armor/model/chestplate/chestplate_full_plate.png"))
				.modelTexture(ArmorType.LEGGINGS, Alloygery.identifier("textures/armor/model/leggings/leggings_full_plate.png"))
				.modelTexture(ArmorType.BOOTS, Alloygery.identifier("textures/armor/model/boots/boots_full_plate.png"))
				.build();

		ANDESITE_ALLOY_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("andesite_alloy_plate")
				.color(13224634)
				.repairIngredientFromTag("create_compat:andesite_alloy_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 13.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 4.5f)
				.build()

				.build()
		);

		ADNESITE_ALLOY_HEAVY_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("andesite_alloy_heavy_plate")
				.color(13224634)
				.repairIngredientFromTag("create_compat:andesite_alloy_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 13.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 7.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -4.5f)
				.build()

				.build()
		);

		BRASS_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("brass_plate")
				.color(16501864)
				.repairIngredientFromTag("c:ingots/brass")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 14.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 5.0f)
				.build()

				.build()
		);
	}
}
