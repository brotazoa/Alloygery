package amorphia.alloygery.content.metals.block;

import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import com.google.common.collect.Lists;
import net.minecraft.block.SlabBlock;

import java.util.List;

public class TintedSlabBlock extends SlabBlock
{
	public static final List<TintedSlabBlock> BLOCKS = Lists.newArrayList();

	protected final AlloygeryToolMaterial material;

	public TintedSlabBlock(AlloygeryToolMaterial material, Settings settings)
	{
		super(settings);
		this.material = material;
		BLOCKS.add(this);
	}

	public AlloygeryToolMaterial getMaterial()
	{
		return material;
	}
}
