package amorphia.alloygery.content.metals.item;

import amorphia.alloygery.content.tools.material.ToolMaterial;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

import java.util.List;

public class TintedBlockItem extends BlockItem
{
	public static final List<TintedBlockItem> TINTED_BLOCK_ITEMS = Lists.newArrayList();

	private final ToolMaterial alloygeryMaterial;

	public TintedBlockItem(ToolMaterial material, Block block, Settings settings)
	{
		super(block, settings);
		this.alloygeryMaterial = material;
		TINTED_BLOCK_ITEMS.add(this);
	}

	public ToolMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
