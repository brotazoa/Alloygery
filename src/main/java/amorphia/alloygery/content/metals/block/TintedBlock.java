package amorphia.alloygery.content.metals.block;

import amorphia.alloygery.content.materials.AlloygeryMaterial;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;

import java.util.List;

public class TintedBlock extends Block
{
	public static final List<TintedBlock> TINTED_BLOCKS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public TintedBlock(AlloygeryMaterial material, Settings settings)
	{
		super(settings);
		this.alloygeryMaterial = material;
		TINTED_BLOCKS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}
}
