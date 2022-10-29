package amorphia.alloygery.content.machines.registry;

import amorphia.alloygery.Alloygery;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class MachineItemRegistry
{
	public static final Item ALLOY_KILN = new BlockItem(MachineBlockRegistry.ALLOY_KILN, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP));
	public static final Item BLAST_ALLOY_KILN = new BlockItem(MachineBlockRegistry.BLAST_ALLOY_KILN, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP));
	public static final Item SMITHING_ANVIL = new BlockItem(MachineBlockRegistry.SMITHING_ANVIL, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP));

	public static void init()
	{
		register("alloy_kiln", ALLOY_KILN);
		register("blast_alloy_kiln", BLAST_ALLOY_KILN);
		register("smithing_anvil", SMITHING_ANVIL);
	}

	private static void register(String path, Item item)
	{
		Registry.register(Registry.ITEM, Alloygery.identifier(path), item);
	}
}
