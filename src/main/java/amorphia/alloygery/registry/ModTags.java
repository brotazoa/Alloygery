package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class ModTags
{
	public static TagKey<Item> CRAFTING_TOOLS;

	public static void register()
	{
		CRAFTING_TOOLS = TagKey.of(Registry.ITEM_KEY, Alloygery.identifier("tools/crafting_tools"));
	}
}
