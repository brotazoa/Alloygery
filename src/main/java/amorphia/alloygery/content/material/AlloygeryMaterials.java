package amorphia.alloygery.content.material;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.item.ModMiningLevels;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;

public class AlloygeryMaterials
{
	public static final DefaultedRegistry<AlloygeryMaterial> ALLOYGERY_MATERIALS = FabricRegistryBuilder
			.createDefaulted(AlloygeryMaterial.class, Alloygery.identifier("materials"), Alloygery.identifier("materials/unknown"))
			.attribute(RegistryAttribute.SYNCED)
			.buildAndRegister();

	public static final AlloygeryMaterial UNKNOWN = registerMaterial("unknown", new AlloygeryMaterial("unknown"));

	public static final AlloygeryMaterial TIN;
	public static final AlloygeryMaterial COPPER;
	public static final AlloygeryMaterial BRONZE;
	public static final AlloygeryMaterial IRON;
	public static final AlloygeryMaterial GOLD;
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

	//vanilla wood materials
	public static final AlloygeryMaterial VANILLA_STICK;

	//upgrades
	public static final AlloygeryMaterial DIAMOND_UPGRADE;
	public static final AlloygeryMaterial EMERALD_UPGRADE;
	public static final AlloygeryMaterial NETHERITE_UPGRADE;

	public static void init(){}

	static AlloygeryMaterial registerMaterial(String name, AlloygeryMaterial material)
	{
		return register(Alloygery.identifier("materials/" + name), material);
	}

	public static AlloygeryMaterial register(Identifier identifier, AlloygeryMaterial material)
	{
		return Registry.register(ALLOYGERY_MATERIALS, identifier, material);
	}

	static
	{
		TIN = registerMaterial("tin", new AlloygeryMaterial.AlloygeryMaterialBuilder("tin")
				.category("metal")
				.color(14547455)
				.build());

		COPPER = registerMaterial("copper", new AlloygeryMaterial.AlloygeryMaterialBuilder("copper")
				.category("metal")
				.color(15433553)
				.level(ModMiningLevels.STONE)
				.head_durability(150)
				.speed(4.0f)
				.damage(2.0f)
				.tool_enchantability(10)
				.armor_durability(5)
				.helmet_armor(1)
				.chestplate_armor(3)
				.leggings_armor(2)
				.boots_armor(1)
				.armor_enchantability(15)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		BRONZE = registerMaterial("bronze", new AlloygeryMaterial.AlloygeryMaterialBuilder("bronze")
				.category("metal")
				.color(7556410)
				.level(ModMiningLevels.BRONZE)
				.head_durability(250)
				.speed(5.0f)
				.damage(2.0f)
				.tool_enchantability(15)
				.armor_enchantability(12)
				.armor_durability(15)
				.helmet_armor(2)
				.chestplate_armor(6)
				.leggings_armor(5)
				.boots_armor(2)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		//vanilla stats
		IRON = registerMaterial("iron", new AlloygeryMaterial.AlloygeryMaterialBuilder("iron")
				.category("metal")
				.level(ModMiningLevels.IRON)
				.color(15198183)
				.head_durability(250)
				.speed(6.0f)
				.damage(2.0f)
				.tool_enchantability(14)
				.armor_enchantability(9)
				.armor_durability(15)
				.helmet_armor(2)
				.chestplate_armor(6)
				.leggings_armor(5)
				.boots_armor(2)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		//vanilla stats
		GOLD = registerMaterial("gold", new AlloygeryMaterial.AlloygeryMaterialBuilder("gold")
				.category("metal")
				.color(16573743)
				.level(MiningLevels.WOOD)
				.head_durability(32)
				.speed(12.0f)
				.damage(0.0f)
				.tool_enchantability(22)
				.armor_enchantability(25)
				.armor_durability(7)
				.helmet_armor(2)
				.chestplate_armor(5)
				.leggings_armor(3)
				.boots_armor(1)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		ANTANIUM = registerMaterial("antanium", new AlloygeryMaterial.AlloygeryMaterialBuilder("antanium")
				.category("metal")
				.level(ModMiningLevels.BRONZE)
				.color(14329677)
				.head_durability(858)
				.speed(10.0f)
				.damage(1.0f)
				.tool_enchantability(20)
				.armor_enchantability(20)
				.armor_durability(20)
				.helmet_armor(2)
				.chestplate_armor(5)
				.leggings_armor(4)
				.boots_armor(2)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		DIAMOND = registerMaterial("diamond", new AlloygeryMaterial.AlloygeryMaterialBuilder("diamond")
				.category("gem")
				.level(ModMiningLevels.DIAMOND)
				.color(3402699)
				.head_durability(1561)
				.speed(8.0f)
				.damage(3.0f)
				.tool_enchantability(10)
				.armor_durability(33)
				.helmet_armor(3)
				.chestplate_armor(8)
				.leggings_armor(6)
				.boots_armor(3)
				.armor_enchantability(10)
				.toughness(2.0f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		STEEL = registerMaterial("steel", new AlloygeryMaterial.AlloygeryMaterialBuilder("steel")
				.category("metal")
				.level(ModMiningLevels.STEEL)
				.color(4408907)
				.head_durability(1600)
				.speed(8.0f)
				.damage(3.0f)
				.tool_enchantability(15)
				.armor_enchantability(11)
				.armor_durability(35)
				.helmet_armor(3)
				.chestplate_armor(8)
				.leggings_armor(6)
				.boots_armor(3)
				.toughness(2.0f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		NETHERITE = registerMaterial("netherite", new AlloygeryMaterial.AlloygeryMaterialBuilder("netherite")
				.category("metal")
				.color(5192766)
				.level(ModMiningLevels.NETHERITE)
				.head_durability(2031)
				.speed(9.0f)
				.damage(4.0f)
				.tool_enchantability(15)
				.armor_durability(37)
				.helmet_armor(3)
				.chestplate_armor(8)
				.leggings_armor(6)
				.boots_armor(3)
				.armor_enchantability(15)
				.toughness(3.0f)
				.knockback(0.1f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		NICKEL = registerMaterial("nickel", new AlloygeryMaterial.AlloygeryMaterialBuilder("nickel")
				.category("metal")
				.level(ModMiningLevels.NICKEL)
				.color(6314062)
				.head_durability(2121)
				.speed(9.0f)
				.damage(3.0f)
				.tool_enchantability(15)
				.armor_enchantability(12)
				.armor_durability(37)
				.helmet_armor(3)
				.chestplate_armor(8)
				.leggings_armor(6)
				.boots_armor(3)
				.toughness(3.0f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		INVAR = registerMaterial("invar", new AlloygeryMaterial.AlloygeryMaterialBuilder("invar")
				.category("metal")
				.level(ModMiningLevels.NICKEL)
				.color(10789019)
				.head_durability(2731)
				.speed(9.0f)
				.damage(4.0f)
				.tool_enchantability(15)
				.armor_enchantability(10)
				.armor_durability(45)
				.helmet_armor(3)
				.chestplate_armor(9)
				.leggings_armor(7)
				.boots_armor(3)
				.toughness(3.0f)
				.knockback(0.1f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		CONSTANTAN = registerMaterial("constantan", new AlloygeryMaterial.AlloygeryMaterialBuilder("constantan")
				.category("metal")
				.level(ModMiningLevels.NICKEL)
				.color(11558984)
				.head_durability(2221)
				.speed(9.0f)
				.damage(4.0f)
				.tool_enchantability(15)
				.armor_enchantability(15)
				.armor_durability(40)
				.helmet_armor(3)
				.chestplate_armor(8)
				.leggings_armor(6)
				.boots_armor(3)
				.toughness(3.0f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		CUPRONICKEL = registerMaterial("cupronickel", new AlloygeryMaterial.AlloygeryMaterialBuilder("cupronickel")
				.category("metal")
				.level(ModMiningLevels.NICKEL)
				.color(5257772)
				.head_durability(2471)
				.speed(9.0f)
				.damage(4.0f)
				.tool_enchantability(15)
				.armor_enchantability(15)
				.armor_durability(40)
				.helmet_armor(3)
				.chestplate_armor(9)
				.leggings_armor(6)
				.boots_armor(3)
				.toughness(3.0f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		TITANIUM = registerMaterial("titanium", new AlloygeryMaterial.AlloygeryMaterialBuilder("titanium")
				.category("metal")
				.level(ModMiningLevels.TITANIUM)
				.color(5990506)
				.head_durability(2892)
				.speed(10.0f)
				.damage(5.0f)
				.tool_enchantability(18)
				.armor_enchantability(18)
				.armor_durability(45)
				.helmet_armor(4)
				.chestplate_armor(9)
				.leggings_armor(7)
				.boots_armor(4)
				.toughness(2.0f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		TITANIUM_GOLD = registerMaterial("titanium_gold", new AlloygeryMaterial.AlloygeryMaterialBuilder("titanium_gold")
				.category("metal")
				.level(ModMiningLevels.TITANIUM)
				.color(13086590)
				.head_durability(3127)
				.speed(12.0f)
				.damage(5.0f)
				.tool_enchantability(25)
				.armor_enchantability(25)
				.armor_durability(47)
				.helmet_armor(4)
				.chestplate_armor(9)
				.leggings_armor(7)
				.boots_armor(4)
				.toughness(3.0f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		NITINOL = registerMaterial("nitinol", new AlloygeryMaterial.AlloygeryMaterialBuilder("nitinol")
				.category("metal")
				.level(ModMiningLevels.TITANIUM)
				.color(6185051)
				.head_durability(3362)
				.speed(10.0f)
				.damage(5.0f)
				.tool_enchantability(18)
				.armor_enchantability(18)
				.armor_durability(50)
				.helmet_armor(4)
				.chestplate_armor(9)
				.leggings_armor(7)
				.boots_armor(4)
				.toughness(3.0f)
				.knockback(0.1f)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		VANILLA_STICK = registerMaterial("wooden", new AlloygeryMaterial.AlloygeryMaterialBuilder("wooden")
				.category("wood")
				.color(6835742)
				.durability_multiplier(1.0f)
				.enchantability_multiplier(1.0f)
				.speed_multiplier(1.0f)
				.damage_multiplier(1.0f)
				.build());

		DIAMOND_UPGRADE = registerMaterial("diamond_upgraded", new AlloygeryMaterial.AlloygeryMaterialBuilder("diamond_upgraded")
				.category("upgrade")
				.level(1)
				.head_durability(500)
				.enchantability(-5)
				.build());

		EMERALD_UPGRADE = registerMaterial("emerald_upgraded", new AlloygeryMaterial.AlloygeryMaterialBuilder("emerald_upgraded")
				.category("upgrade")
				.level(0)
				.durability_multiplier(1.5f)
				.enchantability(5)
				.build());

		NETHERITE_UPGRADE = registerMaterial("netherite_upgraded", new AlloygeryMaterial.AlloygeryMaterialBuilder("netherite_upgraded")
				.category("upgrade")
				.level(0)
				.durability_multiplier(1.2f)
				.speed_multiplier(1.1f)
				.damage_multiplier(1.1f)
				.build());
	}
}
