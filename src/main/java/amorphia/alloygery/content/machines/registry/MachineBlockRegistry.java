package amorphia.alloygery.content.machines.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.machines.block.SmithingAnvilBlock;
import amorphia.alloygery.content.machines.block.AlloyKilnBlock;
import amorphia.alloygery.content.machines.block.BlastAlloyKilnBlock;
import amorphia.alloygery.content.machines.block.entity.AlloyKilnBlockEntity;
import amorphia.alloygery.content.machines.block.entity.BlastAlloyKilnBlockEntity;
import amorphia.alloygery.content.machines.block.entity.SmithingAnvilBlockEntity;
import amorphia.alloygery.content.machines.block.entity.SmithingAnvilBlockEntityRenderer;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class MachineBlockRegistry
{
	//alloy kilns
	public static final Block ALLOY_KILN = new AlloyKilnBlock();
	public static final Block BLAST_ALLOY_KILN = new BlastAlloyKilnBlock();

	//alloy kiln entities
	public static BlockEntityType<AlloyKilnBlockEntity> ALLOY_KILN_BLOCK_ENTITY;
	public static BlockEntityType<BlastAlloyKilnBlockEntity> BLAST_ALLOY_KILN_BLOCK_ENTITY;

	//smithing anvil
	public static final Block SMITHING_ANVIL = new SmithingAnvilBlock(FabricBlockSettings.of(Material.REPAIR_STATION, MapColor.IRON_GRAY).requiresTool().strength(5.0f, 1200.0f).sounds(BlockSoundGroup.ANVIL));

	public static BlockEntityType<SmithingAnvilBlockEntity> SMITHING_ANVIL_BLOCK_ENTITY;

	public static void init()
	{
		register("alloy_kiln", ALLOY_KILN);
		register("blast_alloy_kiln", BLAST_ALLOY_KILN);
		register("smithing_anvil", SMITHING_ANVIL);

		ALLOY_KILN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Alloygery.identifier("alloy_kiln_block_entity"),
				FabricBlockEntityTypeBuilder.create(AlloyKilnBlockEntity::new, ALLOY_KILN).build(null));

		BLAST_ALLOY_KILN_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Alloygery.identifier("blast_alloy_kiln_block_entity"),
				FabricBlockEntityTypeBuilder.create(BlastAlloyKilnBlockEntity::new, BLAST_ALLOY_KILN).build(null));

		SMITHING_ANVIL_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Alloygery.identifier("smithing_anvil_block_entity"),
				FabricBlockEntityTypeBuilder.create(SmithingAnvilBlockEntity::new, SMITHING_ANVIL).build(null));
	}

	public static void initClient()
	{
		BlockRenderLayerMap.INSTANCE.putBlock(ALLOY_KILN, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BLAST_ALLOY_KILN, RenderLayer.getCutout());

		BlockEntityRendererRegistry.register(SMITHING_ANVIL_BLOCK_ENTITY, SmithingAnvilBlockEntityRenderer::new);
	}

	private static void register(String path, Block block)
	{
		Registry.register(Registry.BLOCK, Alloygery.identifier(path), block);
	}
}
