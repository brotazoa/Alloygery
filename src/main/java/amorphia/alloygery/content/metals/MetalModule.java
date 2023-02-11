package amorphia.alloygery.content.metals;

import amorphia.alloygery.IAlloygeryModule;
import amorphia.alloygery.content.metals.client.CraftingItemReloadListener;
import amorphia.alloygery.content.metals.registry.MetalBlockRegistry;
import amorphia.alloygery.content.metals.registry.MetalItemRegistry;
import amorphia.alloygery.content.metals.registry.MetalLootTableRegistry;
import amorphia.alloygery.content.metals.registry.MetalOreGen;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class MetalModule implements IAlloygeryModule
{
	@Override
	public void initialize()
	{
		MetalBlockRegistry.init();
		MetalItemRegistry.init();
		MetalOreGen.init();
		MetalLootTableRegistry.init();
	}

	@Override
	public void initializeClient()
	{
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(CraftingItemReloadListener.INSTANCE);
	}
}
