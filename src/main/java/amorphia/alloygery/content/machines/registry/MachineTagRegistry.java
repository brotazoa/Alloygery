package amorphia.alloygery.content.machines.registry;

import amorphia.alloygery.Alloygery;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class MachineTagRegistry
{
	public static TagKey<Item> CRAFTING_TOOLS;

	public static void init()
	{
		CRAFTING_TOOLS = TagKey.of(Registry.ITEM_KEY, Alloygery.identifier("tools/crafting_tools"));
	}
}
