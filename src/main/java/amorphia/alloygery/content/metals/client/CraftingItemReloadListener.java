package amorphia.alloygery.content.metals.client;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.metals.block.TintedBlock;
import amorphia.alloygery.content.metals.item.CraftingItem;
import amorphia.alloygery.content.metals.item.TintedBlockItem;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class CraftingItemReloadListener implements SimpleSynchronousResourceReloadListener
{
	public static final CraftingItemReloadListener INSTANCE = new CraftingItemReloadListener();
	public static final Identifier CRAFTING_ITEM_RELOAD_LISTENER = Alloygery.identifier("crafting_item_reload_listener");

	private CraftingItemReloadListener() {} //no op

	@Override
	public Identifier getFabricId()
	{
		return CRAFTING_ITEM_RELOAD_LISTENER;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		CraftingItem.CRAFTING_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? item.getAlloygeryMaterial().getMaterialColor() : -1;
			}, item);
		});

		TintedBlock.TINTED_BLOCKS.forEach(block -> {
			ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
				return tintIndex == 0 ? block.getAlloygeryMaterial().getMaterialColor() : -1;
			}, block);
		});

		TintedBlockItem.TINTED_BLOCK_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? item.getAlloygeryMaterial().getMaterialColor() : -1;
			}, item);
		});
	}
}
