package amorphia.alloygery.content.metals.block;

import amorphia.alloygery.content.tools.material.ToolMaterial;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;

import java.util.List;

public class TintedBlock extends Block
{
	public static final List<TintedBlock> TINTED_BLOCKS = Lists.newArrayList();

	private final ToolMaterial alloygeryMaterial;

	public TintedBlock(ToolMaterial material, Settings settings)
	{
		super(settings);
		this.alloygeryMaterial = material;
		TINTED_BLOCKS.add(this);
	}

	public ToolMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
