package amorphia.alloygery.content.metals.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class MetalOreGen
{
	public static final List<OreFeatureConfig.Target> TIN_ORES;

	public static void init()
	{
		if (AlloygeryConfig.tinOre.shouldGenerate.getValue())
		{
			RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> TIN_ORE_CONFIG = ConfiguredFeatures.register(Alloygery.MOD_ID + ":ore_tin",
					Feature.ORE, new OreFeatureConfig(TIN_ORES, (int) AlloygeryConfig.tinOre.size.getValue()));

			RegistryEntry<PlacedFeature> TIN_ORE_PLACEMENT = PlacedFeatures.register(Alloygery.MOD_ID + ":ore_tin", TIN_ORE_CONFIG,
					List.of(CountPlacementModifier.of((int) AlloygeryConfig.tinOre.number.getValue()), SquarePlacementModifier.of(),
							HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));

			BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, TIN_ORE_PLACEMENT.getKey().get());
		}

		if (AlloygeryConfig.nickelOre.shouldGenerate.getValue())
		{
			RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> NICKEL_ORE_CONFIG = ConfiguredFeatures.register(Alloygery.MOD_ID + ":ore_nickel",
					Feature.ORE, new OreFeatureConfig(OreConfiguredFeatures.BASE_STONE_NETHER, MetalBlockRegistry.NICKEL_ORE.getDefaultState(),
							(int) AlloygeryConfig.nickelOre.size.getValue()));

			RegistryEntry<PlacedFeature> NICKEL_ORE_PLACEMENT = PlacedFeatures.register(Alloygery.MOD_ID + ":ore_nickel", NICKEL_ORE_CONFIG,
					List.of(CountPlacementModifier.of((int) AlloygeryConfig.nickelOre.number.getValue()), SquarePlacementModifier.of(),
							HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())));

			BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, NICKEL_ORE_PLACEMENT.getKey().get());
		}

		if (AlloygeryConfig.titaniumOre.shouldGenerate.getValue())
		{
			RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> TITANIUM_ORE_CONFIG = ConfiguredFeatures.register(
					Alloygery.MOD_ID + ":ore_titanium", Feature.ORE,
					new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE), MetalBlockRegistry.TITANIUM_ORE.getDefaultState(),
							(int) AlloygeryConfig.titaniumOre.size.getValue()));

			RegistryEntry<PlacedFeature> TITANIUM_ORE_PLACEMENT = PlacedFeatures.register(Alloygery.MOD_ID + ":ore_titanium", TITANIUM_ORE_CONFIG,
					List.of(CountPlacementModifier.of((int) AlloygeryConfig.titaniumOre.number.getValue()), SquarePlacementModifier.of(),
							HeightRangePlacementModifier.trapezoid(YOffset.getBottom(), YOffset.getTop())));

			BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, TITANIUM_ORE_PLACEMENT.getKey().get());
		}
	}

	static
	{
		TIN_ORES = List.of(
				OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, MetalBlockRegistry.TIN_ORE.getDefaultState()),
				OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, MetalBlockRegistry.DEEPSLATE_TIN_ORE.getDefaultState())
		);
	}
}
