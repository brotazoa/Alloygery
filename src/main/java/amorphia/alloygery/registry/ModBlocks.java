package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.block.AlloyKilnBlock;
import amorphia.alloygery.content.block.BlastAlloyKilnBlock;
import amorphia.alloygery.content.block.entity.AlloyKilnBlockEntity;
import amorphia.alloygery.content.block.entity.BlastAlloyKilnBlockEntity;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.Tag;
import net.minecraft.tag.TagGroupLoader;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

public class ModBlocks
{
	//ore blocks
	public static final Block TIN_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 3.0f));
	public static final Block DEEPSLATE_TIN_ORE = new OreBlock(FabricBlockSettings.copy(TIN_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5f, 3.0f).sounds(BlockSoundGroup.DEEPSLATE));
	public static final Block NICKLE_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 9.0f));
	public static final Block TITANIUM_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 12.0f));

	//raw ore blocks
	public static final Block RAW_TIN_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f));
	public static final Block RAW_NICKLE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f));
	public static final Block RAW_TITANIUM_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f));

	//metal blocks
	public static final Block TIN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.COPPER));
	public static final Block BRONZE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.BROWN).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.COPPER));
	public static final Block ANTANIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.GOLD).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final Block NICKLE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final Block INVAR_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final Block CONSTANTAN_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.BROWN).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final Block CUPRONICKEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final Block TITANIUM_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final Block TITANIUM_GOLD_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.GOLD).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));
	public static final Block NITINOL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).mapColor(MapColor.IRON_GRAY).requiresTool().strength(5.0f, 6.0f).sounds(BlockSoundGroup.METAL));

	//alloy kilns
	public static final Block ALLOY_KILN = new AlloyKilnBlock();
	public static final Block BLAST_ALLOY_KILN = new BlastAlloyKilnBlock();

	//alloy kiln entities
	public static BlockEntityType<AlloyKilnBlockEntity> ALLOY_KILN_BLOCK_ENTITY;
	public static BlockEntityType<BlastAlloyKilnBlockEntity> BLAST_ALLOY_KILN_BLOCK_ENTITY;

	public static void register()
	{
		//ore blocks
		register("tin_ore", TIN_ORE);
		register("deepslate_tin_ore", DEEPSLATE_TIN_ORE);
		register("nickel_ore", NICKLE_ORE);
		register("titanium_ore", TITANIUM_ORE);

		//raw ore blocks
		register("raw_tin_block", RAW_TIN_BLOCK);
		register("raw_nickel_block", RAW_NICKLE_BLOCK);
		register("raw_titanium_block", RAW_TITANIUM_BLOCK);

		//metal blocks
		register("tin_block", TIN_BLOCK);
		register("bronze_block", BRONZE_BLOCK);
		register("antanium_block", ANTANIUM_BLOCK);
		register("steel_block", STEEL_BLOCK);
		register("nickel_block", NICKLE_BLOCK);
		register("invar_block", INVAR_BLOCK);
		register("constantan_block", CONSTANTAN_BLOCK);
		register("cupronickel_block", CUPRONICKEL_BLOCK);
		register("titanium_block", TITANIUM_BLOCK);
		register("titanium_gold_block", TITANIUM_GOLD_BLOCK);
		register("nitinol_block", NITINOL_BLOCK);

		//alloy kilns
		register("alloy_kiln", ALLOY_KILN);
		register("blast_alloy_kiln", BLAST_ALLOY_KILN);

		//alloy kiln entities
		ALLOY_KILN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Alloygery.identifier("alloy_kiln_block_entity"),
				FabricBlockEntityTypeBuilder.create(AlloyKilnBlockEntity::new, ALLOY_KILN).build(null));
		BLAST_ALLOY_KILN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Alloygery.identifier("blast_alloy_kiln_block_entity"),
				FabricBlockEntityTypeBuilder.create(BlastAlloyKilnBlockEntity::new, BLAST_ALLOY_KILN).build(null));

//		//tool level block tags
//		TagFactory.BLOCK.create(new Identifier("fabric", "needs_tool_level_2"));
//		TagFactory.BLOCK.create(new Identifier("fabric", "needs_tool_level_3"));
//		TagFactory.BLOCK.create(new Identifier("fabric", "needs_tool_level_4"));
//		TagFactory.BLOCK.create(new Identifier("fabric", "needs_tool_level_5"));
	}

	private static void register(String path, Block block)
	{
		Registry.register(Registry.BLOCK, Alloygery.identifier(path), block);
	}

	private static void override(String path, Block block)
	{
		Registry.register(Registry.BLOCK, new Identifier("minecraft", path), block);
	}
}
