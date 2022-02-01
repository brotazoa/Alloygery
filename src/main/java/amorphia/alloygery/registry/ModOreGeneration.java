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
	public static final ConfiguredFeature<?, ?> OVERWORLD_TIN_CONFIGURED_FEATURE = Feature.ORE.configure(
			new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
					ModBlocks.TIN_ORE.getDefaultState(),
					9
			)
	);

	public static final PlacedFeature OVERWORLD_TIN_ORE_PLACED_FEATURE = OVERWORLD_TIN_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(50),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())
	);

	public static final ConfiguredFeature<?, ?> NETHER_NICKEL_CONFIGURED_FEATURE = Feature.ORE.configure(
			new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_NETHER,
					ModBlocks.NICKLE_ORE.getDefaultState(),
					10
			)
	);

	public static final PlacedFeature NETHER_NICKEL_PLACED_FEATURE = NETHER_NICKEL_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(10),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())
	);

	public static final ConfiguredFeature<?, ?> END_TITANIUM_CONFIGURED_FEATURE = Feature.ORE.configure(
			new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE),
					ModBlocks.TITANIUM_ORE.getDefaultState(),
					6
			)
	);

	public static final PlacedFeature END_TITANIUM_PLACED_FEATURE = END_TITANIUM_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(10),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())
	);

	public static void register()
	{
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
