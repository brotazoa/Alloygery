package amorphia.alloygery.content.armor.material;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.ArmorType;
import amorphia.alloygery.content.armor.property.ArmorPropertyOperation;
import amorphia.alloygery.content.armor.property.ArmorPropertyType;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;
import amorphia.alloygery.content.metals.MaterialColor;
import net.minecraft.util.Identifier;

public class AlloygeryArmorMaterials
{
	public static final AlloygeryArmorMaterial UNKNOWN = register(new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder("unknown").build());
	public static final AlloygeryArmorMaterial HIDDEN = register(new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder("hidden").color(0).build());

	public static final AlloygeryArmorMaterial WOOL_BASE;

	public static final AlloygeryArmorMaterial LEATHER_BASE;

	public static final AlloygeryArmorMaterial IRON_CHAIN_BASE;
	public static final AlloygeryArmorMaterial GOLD_CHAIN_BASE;
	public static final AlloygeryArmorMaterial ANTANIUM_CHAIN_BASE;
	public static final AlloygeryArmorMaterial STEEL_CHAIN_BASE;
	public static final AlloygeryArmorMaterial NETHERITE_CHAIN_BASE;
	public static final AlloygeryArmorMaterial NICKEL_CHAIN_BASE;
	public static final AlloygeryArmorMaterial INVAR_CHAIN_BASE;
	public static final AlloygeryArmorMaterial CONSTANTAN_CHAIN_BASE;
	public static final AlloygeryArmorMaterial CUPRONICKEL_CHAIN_BASE;
	public static final AlloygeryArmorMaterial TITANIUM_CHAIN_BASE;
	public static final AlloygeryArmorMaterial TITANIUM_GOLD_CHAIN_BASE;
	public static final AlloygeryArmorMaterial NITINOL_CHAIN_BASE;

	public static final AlloygeryArmorMaterial LEATHER_HALF_PLATE;
	public static final AlloygeryArmorMaterial LEATHER_FULL_PLATE;

	public static final AlloygeryArmorMaterial COPPER_HALF_PLATE;

	public static final AlloygeryArmorMaterial BRONZE_HALF_PLATE;

	public static final AlloygeryArmorMaterial IRON_HALF_PLATE;
	public static final AlloygeryArmorMaterial IRON_FULL_PLATE;

	public static final AlloygeryArmorMaterial GOLD_HALF_PLATE;

	public static final AlloygeryArmorMaterial ANTANIUM_HALF_PLATE;
	public static final AlloygeryArmorMaterial ANTANIUM_FULL_PLATE;

	public static final AlloygeryArmorMaterial DIAMOND_HALF_PLATE;
	public static final AlloygeryArmorMaterial DIAMOND_FULL_PLATE;

	public static final AlloygeryArmorMaterial STEEL_HALF_PLATE;
	public static final AlloygeryArmorMaterial STEEL_FULL_PLATE;

	public static final AlloygeryArmorMaterial NETHERITE_HALF_PLATE;
	public static final AlloygeryArmorMaterial NETHERITE_FULL_PLATE;

	public static final AlloygeryArmorMaterial NICKEL_HALF_PLATE;
	public static final AlloygeryArmorMaterial NICKEL_FULL_PLATE;

	public static final AlloygeryArmorMaterial INVAR_HALF_PLATE;
	public static final AlloygeryArmorMaterial INVAR_FULL_PLATE;

	public static final AlloygeryArmorMaterial CONSTANTAN_HALF_PLATE;
	public static final AlloygeryArmorMaterial CONSTANTAN_FULL_PLATE;

	public static final AlloygeryArmorMaterial CUPRONICKEL_HALF_PLATE;
	public static final AlloygeryArmorMaterial CUPRONICKEL_FULL_PLATE;

	public static final AlloygeryArmorMaterial TITANIUM_HALF_PLATE;
	public static final AlloygeryArmorMaterial TITANIUM_FULL_PLATE;

	public static final AlloygeryArmorMaterial TITANIUM_GOLD_HALF_PLATE;
	public static final AlloygeryArmorMaterial TITANIUM_GOLD_FULL_PLATE;

	public static final AlloygeryArmorMaterial NITINOL_HALF_PLATE;
	public static final AlloygeryArmorMaterial NITINOL_FULL_PLATE;

//	public static final AlloygeryArmorMaterial NETHERITE_REINFORCED_UPGRADE;
//	public static final AlloygeryArmorMaterial GOLD_GUILDED_UPGRADE;

	private static AlloygeryArmorMaterial register(AlloygeryArmorMaterial material)
	{
		return register(Alloygery.identifier("armor_materials/" + material.getMaterialName()), material);
	}

	private static AlloygeryArmorMaterial register(Identifier identifier, AlloygeryArmorMaterial material)
	{
		return AlloygeryArmorMaterialRegistry.register(identifier, material);
	}

	static
	{
		final AlloygeryArmorMaterial STATIC_CHAIN_BASE_TEMPLATE = new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder("chain_base_template")
				.layer(ArmorLayer.BASE)

				.itemTexture(ArmorType.HELMET, Alloygery.identifier("armor/parts/helmet/helmet_chainmail"))
				.itemTexture(ArmorType.CHESTPLATE, Alloygery.identifier("armor/parts/chestplate/chestplate_chainmail"))
				.itemTexture(ArmorType.LEGGINGS, Alloygery.identifier("armor/parts/leggings/leggings_chainmail"))
				.itemTexture(ArmorType.BOOTS, Alloygery.identifier("armor/parts/boots/boots_chainmail"))

				.modelTexture(ArmorType.HELMET, Alloygery.identifier("textures/armor/model/helmet/helmet_chainmail.png"))
				.modelTexture(ArmorType.CHESTPLATE, Alloygery.identifier("textures/armor/model/chestplate/chestplate_chainmail.png"))
				.modelTexture(ArmorType.LEGGINGS, Alloygery.identifier("textures/armor/model/leggings/leggings_chainmail.png"))
				.modelTexture(ArmorType.BOOTS, Alloygery.identifier("textures/armor/model/boots/boots_chainmail.png"))
				.build();

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

		//base

		WOOL_BASE = register(new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder("wool")
				.color(MaterialColor.WHITE_WOOL.getColor())
				.layer(ArmorLayer.BASE)
				.repairIngredientFromTag("minecraft:wool")
				.dyeable(true)

				.itemTexture(ArmorType.HELMET, Alloygery.identifier("armor/parts/helmet/helmet_wool"))
				.itemTexture(ArmorType.CHESTPLATE, Alloygery.identifier("armor/parts/chestplate/chestplate_wool"))
				.itemTexture(ArmorType.LEGGINGS, Alloygery.identifier("armor/parts/leggings/leggings_wool"))
				.itemTexture(ArmorType.BOOTS, Alloygery.identifier("armor/parts/boots/boots_wool"))

				.modelTexture(ArmorType.HELMET, Alloygery.identifier("textures/armor/model/helmet/helmet_leather.png"))
				.modelTexture(ArmorType.CHESTPLATE, Alloygery.identifier("textures/armor/model/chestplate/chestplate_leather.png"))
				.modelTexture(ArmorType.LEGGINGS, Alloygery.identifier("textures/armor/model/leggings/leggings_leather.png"))
				.modelTexture(ArmorType.BOOTS, Alloygery.identifier("textures/armor/model/boots/boots_leather.png"))

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 5f)
				.makeProperty(ArmorPropertyType.ARMOR, 0.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 20.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 20.0f)
				.build()

				.build());

		LEATHER_BASE = register(new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder("leather")
				.color(MaterialColor.LEATHER.getColor())
				.layer(ArmorLayer.BASE)
				.repairIngredientFromTag("c:leathers")
				.dyeable(true)

				.itemTexture(ArmorType.HELMET, Alloygery.identifier("armor/parts/helmet/helmet_leather"))
				.itemTexture(ArmorType.CHESTPLATE, Alloygery.identifier("armor/parts/chestplate/chestplate_leather"))
				.itemTexture(ArmorType.LEGGINGS, Alloygery.identifier("armor/parts/leggings/leggings_leather"))
				.itemTexture(ArmorType.BOOTS, Alloygery.identifier("armor/parts/boots/boots_leather"))

				.modelTexture(ArmorType.HELMET, Alloygery.identifier("textures/armor/model/helmet/helmet_leather.png"))
				.modelTexture(ArmorType.CHESTPLATE, Alloygery.identifier("textures/armor/model/chestplate/chestplate_leather.png"))
				.modelTexture(ArmorType.LEGGINGS, Alloygery.identifier("textures/armor/model/leggings/leggings_leather.png"))
				.modelTexture(ArmorType.BOOTS, Alloygery.identifier("textures/armor/model/boots/boots_leather.png"))

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 5.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 4.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 15.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 10.0f)
				.build()

				.build());

		//chain base

		IRON_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("iron_chain")
				.color(MaterialColor.IRON.getColor())
				.repairIngredientFromTag("c:iron_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 15.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 7.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 10.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 5.0f)
				.build()

				.build()
		);

		GOLD_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("gold_chain")
				.color(MaterialColor.GOLD.getColor())
				.repairIngredientFromTag("c:gold_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 7.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 5.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 25.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 0.0f)
				.makeProperty(ArmorPropertyType.PIGLIN_LOVED, 1.0f)
				.build()

				.build()
		);

		ANTANIUM_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("antanium_chain")
				.color(MaterialColor.ANTANIUM.getColor())
				.repairIngredientFromTag("c:antanium_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 20.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 8.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 20.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 4.0f)
				.makeProperty(ArmorPropertyType.PIGLIN_LOVED, 1.0f)
				.build()

				.build()
		);

		STEEL_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("steel_chain")
				.color(MaterialColor.STEEL.getColor())
				.repairIngredientFromTag("c:steel_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 35.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 10.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 1.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 8.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 0.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 10.0f)
				.build()

				.build()
		);

		NETHERITE_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("netherite_chain")
				.color(MaterialColor.NETHERITE.getColor())
				.repairIngredientFromTag("c:netherite_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 40.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 14.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 2.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 15.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 0.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 20.0f)
				.makeProperty(ArmorPropertyType.FIREPROOF, 1.0f)
				.build()

				.build()
		);

		NICKEL_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("nickel_chain")
				.color(MaterialColor.NICKEL.getColor())
				.repairIngredientFromTag("c:nickel_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 37.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 12.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 10.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 0.0f)
				.build()

				.build()
		);

		INVAR_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("invar_chain")
				.color(MaterialColor.INVAR.getColor())
				.repairIngredientFromTag("c:invar_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 14.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 2.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 8.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -5.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 20.0f)
				.build()

				.build()
		);

		CONSTANTAN_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("constantan_chain")
				.color(MaterialColor.CONSTANTAN.getColor())
				.repairIngredientFromTag("c:constantan_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 38.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 10.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 12.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 0.0f)
				.build()

				.build()
		);

		CUPRONICKEL_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("cupronickel_chain")
				.color(MaterialColor.CUPRONICKEL.getColor())
				.repairIngredientFromTag("c:cupronickel_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 40.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 10.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 10.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 0.0f)
				.build()

				.build()
		);

		TITANIUM_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("titanium_chain")
				.color(MaterialColor.TITANIUM.getColor())
				.repairIngredientFromTag("c:titanium_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 14.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 18.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 10.0f)
				.build()

				.build()
		);

		TITANIUM_GOLD_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("titanium_gold_chain")
				.color(MaterialColor.TITANIUM_GOLD.getColor())
				.repairIngredientFromTag("c:titanium_gold_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 12.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 25.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 5.0f)
				.makeProperty(ArmorPropertyType.PIGLIN_LOVED, 1.0f)
				.build()

				.build()
		);

		NITINOL_CHAIN_BASE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_CHAIN_BASE_TEMPLATE)
				.name("nitinol_chain")
				.color(MaterialColor.NITINOL.getColor())
				.repairIngredientFromTag("c:nitinol_ingots")

				.armorProperty().forLayer(ArmorLayer.BASE).operation(ArmorPropertyOperation.BASE)
				.makeProperty(ArmorPropertyType.DURABILITY, 50.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 14.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 18.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 10.0f)
				.build()

				.build()
		);

		//half plate

		LEATHER_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("leather_half_plate")
				.color(MaterialColor.LEATHER.getColor())
				.repairIngredientFromTag("c:leathers")
				.dyeable(true)

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 5.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 4.0f)
				.build()

				.build()
		);

		COPPER_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("copper_half_plate")
				.color(MaterialColor.COPPER.getColor())
				.repairIngredientFromTag("c:copper_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 10.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 5.0f)
				.build()

				.build()
		);

		BRONZE_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("bronze_half_plate")
				.color(MaterialColor.BRONZE.getColor())
				.repairIngredientFromTag("c:bronze_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 15.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 7.0f)
				.build()

				.build()
		);

		IRON_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("iron_half_plate")
				.color(MaterialColor.IRON.getColor())
				.repairIngredientFromTag("c:iron_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 15.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 7.0f)
				.build()

				.build()
		);

		GOLD_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("gold_half_plate")
				.color(MaterialColor.GOLD.getColor())
				.repairIngredientFromTag("c:gold_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 7.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 5.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 12.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -2.0f)
				.makeProperty(ArmorPropertyType.PIGLIN_LOVED, 1.0f)
				.build()

				.build()
		);

		ANTANIUM_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("antanium_half_plate")
				.color(MaterialColor.ANTANIUM.getColor())
				.repairIngredientFromTag("c:antanium_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 20.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 8.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 10.0f)
				.makeProperty(ArmorPropertyType.PIGLIN_LOVED, 1.0f)
				.build()

				.build()
		);

		DIAMOND_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("diamond_half_plate")
				.color(MaterialColor.DIAMOND.getColor())
				.repairIngredientFromTag("c:diamond_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 35.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 10.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 1.0f)
				.build()

				.build()
		);

		STEEL_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("steel_half_plate")
				.color(MaterialColor.STEEL.getColor())
				.repairIngredientFromTag("c:steel_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 35.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 10.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 1.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 10.0f)
				.build()

				.build()
		);

		NETHERITE_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("netherite_half_plate")
				.color(MaterialColor.NETHERITE.getColor())
				.repairIngredientFromTag("c:netherite_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 40.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 14.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 2.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 20.0f)
				.makeProperty(ArmorPropertyType.FIREPROOF, 1.0f)
				.build()

				.build()
		);

		NICKEL_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("nickel_half_plate")
				.color(MaterialColor.NICKEL.getColor())
				.repairIngredientFromTag("c:nickel_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 37.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 12.0f)
				.build()

				.build()
		);

		INVAR_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("invar_half_plate")
				.color(MaterialColor.INVAR.getColor())
				.repairIngredientFromTag("c:invar_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 14.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 1.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 20.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -5.0f)
				.build()

				.build()
		);

		CONSTANTAN_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("constantan_half_plate")
				.color(MaterialColor.CONSTANTAN.getColor())
				.repairIngredientFromTag("c:constantan_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 38.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 10.0f)
				.build()

				.build()
		);

		CUPRONICKEL_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("cupronickel_half_plate")
				.color(MaterialColor.CUPRONICKEL.getColor())
				.repairIngredientFromTag("c:cupronickel_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 40.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 10.0f)
				.build()

				.build()
		);

		TITANIUM_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("titanium_half_plate")
				.color(MaterialColor.TITANIUM.getColor())
				.repairIngredientFromTag("c:titanium_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 14.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 2.0f)
				.build()

				.build()
		);

		TITANIUM_GOLD_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("titanium_gold_half_plate")
				.color(MaterialColor.TITANIUM_GOLD.getColor())
				.repairIngredientFromTag("c:titanium_gold_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 12.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 10.0f)
				.makeProperty(ArmorPropertyType.PIGLIN_LOVED, 1.0f)
				.build()

				.build()
		);

		NITINOL_HALF_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_HALF_PLATE_TEMPLATE)
				.name("nitinol_half_plate")
				.color(MaterialColor.NITINOL.getColor())
				.repairIngredientFromTag("c:nitinol_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 50.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 14.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, 2.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 8.0f)
				.build()

				.build()
		);

		//full plate

		LEATHER_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("leather_full_plate")
				.color(MaterialColor.LEATHER.getColor())
				.repairIngredientFromTag("c:leathers")
				.dyeable(true)

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 5.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 8.0f)
				.build()

				.build()
		);

		IRON_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("iron_full_plate")
				.color(MaterialColor.IRON.getColor())
				.repairIngredientFromTag("c:iron_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 15.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 10.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -5.0f)
				.build()

				.build()
		);

		ANTANIUM_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("antanium_full_plate")
				.color(MaterialColor.ANTANIUM.getColor())
				.repairIngredientFromTag("c:antanium_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 20.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 12.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 20.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -5.0f)
				.build()

				.build()
		);

		DIAMOND_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("diamond_full_plate")
				.color(MaterialColor.DIAMOND.getColor())
				.repairIngredientFromTag("c:diamond_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 35.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 20.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 2.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -8.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 10.0f)
				.build()

				.build()
		);

		STEEL_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("steel_full_plate")
				.color(MaterialColor.STEEL.getColor())
				.repairIngredientFromTag("c:steel_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 35.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 20.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 2.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -8.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 20.0f)
				.build()

				.build()
		);

		NETHERITE_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("netherite_full_plate")
				.color(MaterialColor.NETHERITE.getColor())
				.repairIngredientFromTag("c:netherite_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 40.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 28.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 4.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -10.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 40.0f)
				.makeProperty(ArmorPropertyType.FIREPROOF, 1.0f)
				.build()

				.build()
		);

		NICKEL_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("nickel_full_plate")
				.color(MaterialColor.NICKEL.getColor())
				.repairIngredientFromTag("c:nickel_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 37.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 22.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -10.0f)
				.build()

				.build()
		);

		INVAR_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("invar_full_plate")
				.color(MaterialColor.INVAR.getColor())
				.repairIngredientFromTag("c:invar_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 28.0f)
				.makeProperty(ArmorPropertyType.TOUGHNESS, 4.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -20.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 40.0f)
				.build()

				.build()
		);

		CONSTANTAN_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("constantan_full_plate")
				.color(MaterialColor.CONSTANTAN.getColor())
				.repairIngredientFromTag("c:constantan_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 38.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 20.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -10.0f)
				.build()

				.build()
		);

		CUPRONICKEL_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("cupronickel_full_plate")
				.color(MaterialColor.CUPRONICKEL.getColor())
				.repairIngredientFromTag("c:cupronickel_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 40.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 20.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -10.0f)
				.build()

				.build()
		);

		TITANIUM_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("titanium_full_plate")
				.color(MaterialColor.TITANIUM.getColor())
				.repairIngredientFromTag("c:titanium_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 28.0f)
				.build()

				.build()
		);

		TITANIUM_GOLD_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("titanium_gold_full_plate")
				.color(MaterialColor.TITANIUM_GOLD.getColor())
				.repairIngredientFromTag("c:titanium_gold_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 45.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 22.0f)
				.makeProperty(ArmorPropertyType.ENCHANTABILITY, 20.0f)
				.makeProperty(ArmorPropertyType.MOBILITY, -2.0f)
				.build()

				.build()
		);

		NITINOL_FULL_PLATE = register(AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder
				.fromMaterial(STATIC_FULL_PLATE_TEMPLATE)
				.name("nitinol_full_plate")
				.color(MaterialColor.NITINOL.getColor())
				.repairIngredientFromTag("c:nitinol_ingots")

				.armorProperty().forLayer(ArmorLayer.PLATE).operation(ArmorPropertyOperation.ADDITION)
				.makeProperty(ArmorPropertyType.DURABILITY, 50.0f)
				.makeProperty(ArmorPropertyType.ARMOR, 28.0f)
				.makeProperty(ArmorPropertyType.KNOCKBACK_RESISTANCE, 10.0f)
				.build()

				.build()
		);

		//upgrades
	}
}
