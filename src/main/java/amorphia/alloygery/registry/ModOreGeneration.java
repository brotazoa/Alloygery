package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.*;

public class ModOreGeneration
{
	public static void register()
	{
		ConfiguredFeature<?, ?> OVERWORLD_TIN_CONFIGURED_FEATURE = Feature.ORE.configure(
				new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
						ModBlocks.ALLOYGERY_BLOCKS.get("tin_ore").getDefaultState(),
						(int) AlloygeryConfig.tinOre.size.getValue()
				)
		);

		PlacedFeature OVERWORLD_TIN_ORE_PLACED_FEATURE = OVERWORLD_TIN_CONFIGURED_FEATURE.withPlacement(
				CountPlacementModifier.of((int) AlloygeryConfig.tinOre.number.getValue()),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())
		);

		if (AlloygeryConfig.tinOre.shouldGenerate.getValue())
		{
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Alloygery.identifier("overworld_tin"), OVERWORLD_TIN_CONFIGURED_FEATURE);
			Registry.register(BuiltinRegistries.PLACED_FEATURE, Alloygery.identifier("overworld_tin"), OVERWORLD_TIN_ORE_PLACED_FEATURE);
			BiomeModifications.addFeature(
					BiomeSelectors.foundInOverworld(),
					GenerationStep.Feature.UNDERGROUND_ORES,
					RegistryKey.of(Registry.PLACED_FEATURE_KEY, Alloygery.identifier("overworld_tin"))
			);
		}

		ConfiguredFeature<?, ?> NETHER_NICKEL_CONFIGURED_FEATURE = Feature.ORE.configure(
				new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_NETHER,
						ModBlocks.ALLOYGERY_BLOCKS.get("nickel_ore").getDefaultState(),
						(int) AlloygeryConfig.nickelOre.size.getValue()
				)
		);

		PlacedFeature NETHER_NICKEL_PLACED_FEATURE = NETHER_NICKEL_CONFIGURED_FEATURE.withPlacement(
				CountPlacementModifier.of((int) AlloygeryConfig.nickelOre.number.getValue()),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())
		);

		if (AlloygeryConfig.nickelOre.shouldGenerate.getValue())
		{
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Alloygery.identifier("nether_nickel"), NETHER_NICKEL_CONFIGURED_FEATURE);
			Registry.register(BuiltinRegistries.PLACED_FEATURE, Alloygery.identifier("nether_nickel"), NETHER_NICKEL_PLACED_FEATURE);
			BiomeModifications.addFeature(
					BiomeSelectors.foundInTheNether(),
					GenerationStep.Feature.UNDERGROUND_ORES,
					RegistryKey.of(Registry.PLACED_FEATURE_KEY, Alloygery.identifier("nether_nickel"))
			);
		}

		ConfiguredFeature<?, ?> END_TITANIUM_CONFIGURED_FEATURE = Feature.ORE.configure(
				new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE),
						ModBlocks.ALLOYGERY_BLOCKS.get("titanium_ore").getDefaultState(),
						(int) AlloygeryConfig.titaniumOre.size.getValue()
				)
		);

		PlacedFeature END_TITANIUM_PLACED_FEATURE = END_TITANIUM_CONFIGURED_FEATURE.withPlacement(
				CountPlacementModifier.of((int) AlloygeryConfig.titaniumOre.number.getValue()),
				SquarePlacementModifier.of(),
				HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())
		);

		if (AlloygeryConfig.titaniumOre.shouldGenerate.getValue())
		{
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, Alloygery.identifier("end_titanium"), END_TITANIUM_CONFIGURED_FEATURE);
			Registry.register(BuiltinRegistries.PLACED_FEATURE, Alloygery.identifier("end_titanium"), END_TITANIUM_PLACED_FEATURE);
			BiomeModifications.addFeature(
					BiomeSelectors.foundInTheEnd(),
					GenerationStep.Feature.UNDERGROUND_ORES,
					RegistryKey.of(Registry.PLACED_FEATURE_KEY, Alloygery.identifier("end_titanium"))
			);
		}
	}
}
