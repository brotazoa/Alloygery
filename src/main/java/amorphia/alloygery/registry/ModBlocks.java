package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.block.AlloyKilnBlock;
import amorphia.alloygery.content.block.AlloygeryTintedBlock;
import amorphia.alloygery.content.block.BlastAlloyKilnBlock;
import amorphia.alloygery.content.block.SmithingAnvilBlock;
import amorphia.alloygery.content.block.entity.AlloyKilnBlockEntity;
import amorphia.alloygery.content.block.entity.BlastAlloyKilnBlockEntity;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.data.AlloygeryGeneratedModelBuilder;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks
{
	public static final List<AlloygeryMaterial> RAW_ORE_BLOCKS_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.TIN,
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.TITANIUM
	);

	public static final List<AlloygeryMaterial> METAL_BLOCKS_FOR_MATERIAL = Lists.newArrayList(
			AlloygeryMaterials.TIN,
			AlloygeryMaterials.BRONZE,
			AlloygeryMaterials.ANTANIUM,
			AlloygeryMaterials.STEEL,
			AlloygeryMaterials.NICKEL,
			AlloygeryMaterials.INVAR,
			AlloygeryMaterials.CONSTANTAN,
			AlloygeryMaterials.CUPRONICKEL,
			AlloygeryMaterials.TITANIUM,
			AlloygeryMaterials.TITANIUM_GOLD,
			AlloygeryMaterials.NITINOL
	);

	public static final Map<String, Block> ALLOYGERY_BLOCKS = new LinkedHashMap<>();

	//@formatter:off

	//ore blocks
	public static final Block TIN_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 3.0f));
	public static final Block DEEPSLATE_TIN_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4.5f, 3.0f));
	public static final Block NICKEL_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 9.0f));
	public static final Block TITANIUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 12.0f));

	//alloy kilns
	public static final Block ALLOY_KILN = new AlloyKilnBlock();
	public static final Block BLAST_ALLOY_KILN = new BlastAlloyKilnBlock();

	//alloy kiln entities
	public static BlockEntityType<AlloyKilnBlockEntity> ALLOY_KILN_BLOCK_ENTITY;
	public static BlockEntityType<BlastAlloyKilnBlockEntity> BLAST_ALLOY_KILN_BLOCK_ENTITY;

	//smithing anvil
	public static final Block SMITHING_ANVIL = new SmithingAnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 1200.0f).sounds(BlockSoundGroup.ANVIL));

	//@formatter:on

	static void makeRawOreBlock(AlloygeryMaterial material)
	{
		putGeneratedBlock("raw_" + material.name + "_block",
				new AlloygeryTintedBlock(material, FabricBlockSettings.of(Material.STONE).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f)),
				AlloygeryGeneratedModelBuilder::createRawOreBlockModelJson);
	}

	static void makeMetalBlock(AlloygeryMaterial material)
	{
		putGeneratedBlock(material.name + "_block",
				new AlloygeryTintedBlock(material, FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL)),
				AlloygeryGeneratedModelBuilder::createMetalBlockModelJson);
	}

	static void putGeneratedBlock(String path, Block block, Supplier<String> modelJsonSupplier)
	{
		Identifier identifier = Alloygery.identifier("block/" + path);
		AlloygeryGeneratedModelBuilder.register(identifier, modelJsonSupplier);
		ALLOYGERY_BLOCKS.put(path, block);
	}

	public static void register()
	{
		RAW_ORE_BLOCKS_FOR_MATERIAL.forEach(ModBlocks::makeRawOreBlock);
		METAL_BLOCKS_FOR_MATERIAL.forEach(ModBlocks::makeMetalBlock);

		ALLOYGERY_BLOCKS.forEach(ModBlocks::register);

		//ore blocks
		register("tin_ore", TIN_ORE);
		register("deepslate_tin_ore", DEEPSLATE_TIN_ORE);
		register("nickel_ore", NICKEL_ORE);
		register("titanium_ore", TITANIUM_ORE);

		//alloy kilns
		register("alloy_kiln", ALLOY_KILN);
		register("blast_alloy_kiln", BLAST_ALLOY_KILN);

		//alloy kiln entities
		ALLOY_KILN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Alloygery.identifier("alloy_kiln_block_entity"),
				FabricBlockEntityTypeBuilder.create(AlloyKilnBlockEntity::new, ALLOY_KILN).build(null));
		BLAST_ALLOY_KILN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Alloygery.identifier("blast_alloy_kiln_block_entity"),
				FabricBlockEntityTypeBuilder.create(BlastAlloyKilnBlockEntity::new, BLAST_ALLOY_KILN).build(null));

		//smithing anvil
		register("smithing_anvil", SMITHING_ANVIL);
	}

	private static void register(String path, Block block)
	{
		Registry.register(Registry.BLOCK, Alloygery.identifier(path), block);
	}
}
