package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModStatistics
{
	public static final Identifier INTERACT_WITH_ALLOY_KILN = Alloygery.identifier("interact_with_alloy_kiln");
	public static final Identifier INTERACT_WITH_BLAST_ALLOY_KILN = Alloygery.identifier("interact_with_blast_alloy_kiln");

	public static void register()
	{
		Registry.register(Registry.CUSTOM_STAT, "interact_with_alloy_kiln", INTERACT_WITH_ALLOY_KILN);
		Registry.register(Registry.CUSTOM_STAT, "interact_with_blast_alloy_kiln", INTERACT_WITH_BLAST_ALLOY_KILN);

		Stats.CUSTOM.getOrCreateStat(INTERACT_WITH_ALLOY_KILN, StatFormatter.DEFAULT);
		Stats.CUSTOM.getOrCreateStat(INTERACT_WITH_BLAST_ALLOY_KILN, StatFormatter.DEFAULT);
	}
}
