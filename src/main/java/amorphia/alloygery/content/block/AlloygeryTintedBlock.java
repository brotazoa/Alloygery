package amorphia.alloygery.content.block;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;

public class AlloygeryTintedBlock extends Block
{
	public static final List<AlloygeryTintedBlock> TINTED_BLOCKS = Lists.newArrayList();

	private final AlloygeryMaterial alloygeryMaterial;

	public AlloygeryTintedBlock(AlloygeryMaterial material, Settings settings)
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
