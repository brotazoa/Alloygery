package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

import java.util.List;

public class AlloygeryTintedBlockItem extends BlockItem implements IMaterialItem
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
