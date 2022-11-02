package amorphia.alloygery.registry;

import amorphia.alloygery.advancement.BlockBrokenCriterion;
import net.minecraft.advancement.criterion.Criteria;

public class ModAdvancements
{
	public static final BlockBrokenCriterion BLOCK_BROKEN_CRITERION = Criteria.register(new BlockBrokenCriterion());

	public static void init() {}
}
