package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class AlloygeryTintedBlockItem extends BlockItem
{
	public static final List<AlloygeryTintedBlockItem> TINTED_BLOCK_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public AlloygeryTintedBlockItem(AlloygeryMaterial material, Block block, Settings settings)
	{
		super(block, settings);
		this.alloygeryMaterial = material;
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}