package amorphia.alloygery.content.metals.block;

import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;

import java.util.List;

public class TintedBlock extends Block
{
	public static final List<TintedBlock> TINTED_BLOCKS = Lists.newArrayList();

	private final AlloygeryToolMaterial alloygeryToolMaterial;

	public TintedBlock(AlloygeryToolMaterial material, Settings settings)
	{
		super(settings);
		this.alloygeryToolMaterial = material;
		TINTED_BLOCKS.add(this);
	}

	public AlloygeryToolMaterial getAlloygeryMaterial()
	{
		return alloygeryToolMaterial;
	}
}
