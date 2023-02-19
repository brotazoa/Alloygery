package amorphia.alloygery.content.metals.block;

import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

import java.util.List;

public class TintedStairsBlock extends StairsBlock
{
	public static final List<TintedStairsBlock> BLOCKS = Lists.newArrayList();

	protected final AlloygeryToolMaterial material;

	public TintedStairsBlock(AlloygeryToolMaterial material, BlockState baseBlockState, Settings settings)
	{
		super(baseBlockState, settings);
		this.material = material;
		BLOCKS.add(this);
	}

	public AlloygeryToolMaterial getMaterial()
	{
		return material;
	}
}
