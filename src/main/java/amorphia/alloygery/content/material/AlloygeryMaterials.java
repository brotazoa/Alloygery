package amorphia.alloygery.content.material;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.item.ModMiningLevels;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AlloygeryMaterials
{
//	public static final DefaultedRegistry<AlloygeryMaterial> ALLOYGERY_MATERIALS = FabricRegistryBuilder
//			.createDefaulted(AlloygeryMaterial.class, Alloygery.identifier("materials"), Alloygery.identifier("materials/unknown"))
//			.attribute(RegistryAttribute.SYNCED)
//			.buildAndRegister();

	public static final BiMap<Identifier, AlloygeryMaterial> ALLOYGERY_MATERIALS = HashBiMap.create();

	public static final AlloygeryMaterial UNKNOWN = registerMaterial("unknown", new AlloygeryMaterial("unknown"));

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

	public static void init(){}

	static AlloygeryMaterial registerMaterial(String name, AlloygeryMaterial material)
	{
		return register(Alloygery.identifier("materials/" + name), material);
	}

	public static AlloygeryMaterial register(Identifier identifier, AlloygeryMaterial material)
	{
		ALLOYGERY_MATERIALS.put(identifier, material);
		return material;
	}

	static
	{
		TIN = registerMaterial("tin", new AlloygeryMaterial.AlloygeryMaterialBuilder("tin")
				.category("metal")
				.color(14547455)
				.repair_ingredient("alloygery:tin_ingot")
				.build());

		COPPER = registerMaterial("copper", new AlloygeryMaterial.AlloygeryMaterialBuilder("copper")
				.category("metal")
				.color(15433553)
				.repair_ingredient("minecraft:copper_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.STONE)
						.durability(150)
						.mining_speed(4.0f)
						.attack_damage(2.0f)
						.enchantability(10)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(5)
						.helmet(1)
						.chestplate(3)
						.leggings(2)
						.boots(1)
						.enchantability(15)
						.build())
				.build());

		BRONZE = registerMaterial("bronze", new AlloygeryMaterial.AlloygeryMaterialBuilder("bronze")
				.category("metal")
				.color(7556410)
				.repair_ingredient("alloygery:bronze_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.BRONZE)
						.durability(250)
						.mining_speed(5.0f)
						.attack_damage(2.0f)
						.enchantability(15)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(15)
						.helmet(2)
						.chestplate(6)
						.leggings(5)
						.boots(2)
						.enchantability(12)
						.build())
				.build());

		//vanilla stats
		IRON = registerMaterial("iron", new AlloygeryMaterial.AlloygeryMaterialBuilder("iron")
				.category("metal")
				.color(15198183)
				.repair_ingredient("minecraft:iron_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.IRON)
						.durability(250)
						.mining_speed(6.0f)
						.attack_damage(2.0f)
						.enchantability(14)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(15)
						.helmet(2)
						.chestplate(6)
						.leggings(5)
						.boots(2)
						.enchantability(9)
						.build())
				.build());

		//vanilla stats
		GOLD = registerMaterial("gold", new AlloygeryMaterial.AlloygeryMaterialBuilder("gold")
				.category("metal")
				.color(16573743)
				.repair_ingredient("minecraft:gold_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.WOOD)
						.durability(32)
						.mining_speed(12.0f)
						.attack_damage(0.0f)
						.enchantability(22)
						.piglin_loved(true)
						.build())
				.tool_upgrade(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.attack_damage_multiplier(0.5f)
						.attack_speed_multiplier(1.2f)
						.mining_speed_multiplier(1.5f)
						.enchantability_multiplier(1.8f)
						.piglin_loved(true)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(7)
						.helmet(2)
						.chestplate(5)
						.leggings(3)
						.boots(1)
						.enchantability(25)
						.piglin_loved(true)
						.build())
				.build());

		EMERALD = registerMaterial("emerald", new AlloygeryMaterial.AlloygeryMaterialBuilder("emerald")
				.category("gem")
				.color(1564002)
				.repair_ingredient("minecraft:emerald")
				.tool_upgrade(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.durability_multiplier(1.5f)
						.enchantability(5)
						.build())
				.build());

		ANTANIUM = registerMaterial("antanium", new AlloygeryMaterial.AlloygeryMaterialBuilder("antanium")
				.category("metal")
				.color(14329677)
				.repair_ingredient("alloygery:antanium_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.STONE)
						.durability(858)
						.mining_speed(10.0f)
						.attack_damage(1.0f)
						.enchantability(20)
						.piglin_loved(true)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(20)
						.helmet(2)
						.chestplate(5)
						.leggings(4)
						.boots(2)
						.enchantability(20)
						.piglin_loved(true)
						.build())
				.build());

		DIAMOND = registerMaterial("diamond", new AlloygeryMaterial.AlloygeryMaterialBuilder("diamond")
				.category("gem")
				.color(3402699)
				.repair_ingredient("minecraft:diamond")
				.make_tool_heads(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.DIAMOND)
						.durability(1561)
						.mining_speed(8.0f)
						.attack_damage(3.0f)
						.enchantability(10)
						.build())
				.tool_upgrade(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(1)
						.durability(500)
						.enchantability(-5)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(33)
						.helmet(3)
						.chestplate(8)
						.leggings(6)
						.boots(3)
						.enchantability(10)
						.toughness(2.0f)
						.build())
				.build());

		STEEL = registerMaterial("steel", new AlloygeryMaterial.AlloygeryMaterialBuilder("steel")
				.category("metal")
				.color(4408907)
				.repair_ingredient("alloygery:steel_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.STEEL)
						.durability(1600)
						.mining_speed(8.0f)
						.attack_damage(3.0f)
						.enchantability(15)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(35)
						.helmet(3)
						.chestplate(8)
						.leggings(6)
						.boots(3)
						.enchantability(11)
						.toughness(2.0f)
						.build())
				.build());

		NETHERITE = registerMaterial("netherite", new AlloygeryMaterial.AlloygeryMaterialBuilder("netherite")
				.category("metal")
				.color(5192766)
				.repair_ingredient("minecraft:netherite_ingot")
				.make_tool_heads(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.NETHERITE)
						.durability(2031)
						.mining_speed(9.0f)
						.attack_damage(4.0f)
						.enchantability(15)
						.fireproof(true)
						.build())
				.tool_upgrade(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.durability_multiplier(1.2f)
						.mining_speed_multiplier(1.1f)
						.attack_damage_multiplier(1.1f)
						.fireproof(true)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(37)
						.helmet(3)
						.chestplate(8)
						.leggings(6)
						.boots(3)
						.enchantability(15)
						.toughness(3.0f)
						.knockback_resistance(0.1f)
						.fireproof(true)
						.build())
				.build());

		NICKEL = registerMaterial("nickel", new AlloygeryMaterial.AlloygeryMaterialBuilder("nickel")
				.category("metal")
				.color(6314062)
				.repair_ingredient("alloygery:nickel_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.NICKEL)
						.durability(2121)
						.mining_speed(9.0f)
						.attack_damage(3.0f)
						.enchantability(15)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(37)
						.helmet(3)
						.chestplate(8)
						.leggings(6)
						.boots(3)
						.enchantability(12)
						.toughness(3.0f)
						.build())
				.build());

		INVAR = registerMaterial("invar", new AlloygeryMaterial.AlloygeryMaterialBuilder("invar")
				.category("metal")
				.color(10789019)
				.repair_ingredient("alloygery:invar_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.NICKEL)
						.durability(2737)
						.mining_speed(9.0f)
						.attack_damage(4.0f)
						.enchantability(15)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(45)
						.helmet(3)
						.chestplate(9)
						.leggings(7)
						.boots(3)
						.enchantability(10)
						.toughness(3.0f)
						.knockback_resistance(0.1f)
						.build())
				.build());

		CONSTANTAN = registerMaterial("constantan", new AlloygeryMaterial.AlloygeryMaterialBuilder("constantan")
				.category("metal")
				.color(11558984)
				.repair_ingredient("alloygery:constantan_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.NICKEL)
						.durability(2221)
						.mining_speed(9.0f)
						.attack_damage(4.0f)
						.enchantability(15)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(40)
						.helmet(3)
						.chestplate(8)
						.leggings(6)
						.boots(3)
						.enchantability(15)
						.toughness(3.0f)
						.build())
				.build());

		CUPRONICKEL = registerMaterial("cupronickel", new AlloygeryMaterial.AlloygeryMaterialBuilder("cupronickel")
				.category("metal")
				.color(5257772)
				.repair_ingredient("alloygery:cupronickel_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.NICKEL)
						.durability(2474)
						.mining_speed(9.0f)
						.attack_damage(4.0f)
						.enchantability(15)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(40)
						.helmet(3)
						.chestplate(9)
						.leggings(6)
						.boots(3)
						.enchantability(15)
						.toughness(3.0f)
						.build())
				.build());

		TITANIUM = registerMaterial("titanium", new AlloygeryMaterial.AlloygeryMaterialBuilder("titanium")
				.category("metal")
				.color(5990506)
				.repair_ingredient("alloygery:titanium_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.TITANIUM)
						.durability(2892)
						.mining_speed(10.0f)
						.attack_damage(5.0f)
						.enchantability(18)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(45)
						.helmet(4)
						.chestplate(9)
						.leggings(7)
						.boots(4)
						.enchantability(18)
						.toughness(2.0f)
						.build())
				.build());

		TITANIUM_GOLD = registerMaterial("titanium_gold", new AlloygeryMaterial.AlloygeryMaterialBuilder("titanium_gold")
				.category("metal")
				.color(13086590)
				.repair_ingredient("alloygery:titanium_gold_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.TITANIUM)
						.durability(3127)
						.mining_speed(12.0f)
						.attack_damage(5.0f)
						.enchantability(25)
						.piglin_loved(true)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(47)
						.helmet(4)
						.chestplate(9)
						.leggings(7)
						.boots(4)
						.enchantability(25)
						.toughness(3.0f)
						.piglin_loved(true)
						.build())
				.build());

		NITINOL = registerMaterial("nitinol", new AlloygeryMaterial.AlloygeryMaterialBuilder("nitinol")
				.category("metal")
				.color(6185051)
				.repair_ingredient("alloygery:nitinol_ingot")
				.make_tool_heads(true)
				.make_tool_bindings(true)
				.make_sword_guards(true)
				.make_tool_handles(true)
				.tool_base(new AlloygeryMaterial.ToolPartSettings.ToolPartSettingsBuilder()
						.mining_level(ModMiningLevels.TITANIUM)
						.durability(3362)
						.mining_speed(10.0f)
						.attack_damage(5.0f)
						.enchantability(18)
						.build())
				.armor(new AlloygeryMaterial.ArmorPartSettings.ArmorPartSettingsBuilder()
						.durability(50)
						.helmet(4)
						.chestplate(9)
						.leggings(7)
						.boots(4)
						.enchantability(18)
						.toughness(3.0f)
						.knockback_resistance(0.1f)
						.build())
				.build());

		VANILLA_STICK = registerMaterial("wooden", new AlloygeryMaterial.AlloygeryMaterialBuilder("wooden")
				.category("wood")
				.color(6835742)
				.make_tool_handles(true)
				.build());

		LEATHER = registerMaterial("leather", new AlloygeryMaterial.AlloygeryMaterialBuilder("leather")
				.category("hide")
				.color(14117699)
				.make_tool_bindings(true)
				.build());
	}
}
