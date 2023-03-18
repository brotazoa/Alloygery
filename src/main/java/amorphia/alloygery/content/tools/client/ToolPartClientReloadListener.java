package amorphia.alloygery.content.tools.client;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.item.part.IToolPart;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class ToolPartClientReloadListener implements SimpleSynchronousResourceReloadListener
{
	public static final ToolPartClientReloadListener INSTANCE = new ToolPartClientReloadListener();
	public static final Identifier ID = Alloygery.identifier("tool_part_reload_listener");

	private ToolPartClientReloadListener(){} //no op

	@Override
	public Identifier getFabricId()
	{
		return ToolPartClientReloadListener.ID;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		IToolPart.TOOL_PART_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? item.getMaterial().getMaterialColor() : -1;
			}, item);
		});
	}
}
