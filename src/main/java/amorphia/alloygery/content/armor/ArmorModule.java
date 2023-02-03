package amorphia.alloygery.content.armor;

import amorphia.alloygery.IAlloygeryModule;
import amorphia.alloygery.content.armor.client.ArmorClientReloadListener;
import amorphia.alloygery.content.armor.client.ArmorPartClientReloadListener;
import amorphia.alloygery.content.armor.client.ArmorRenderLibRenderers;
import amorphia.alloygery.content.armor.data.AlloygeryArmorMaterialDataLoader;
import amorphia.alloygery.content.armor.registry.ArmorItemRegistry;
import amorphia.alloygery.content.armor.registry.ArmorNetworkEventRegistry;
import amorphia.alloygery.content.armor.registry.ArmorRecipeRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class ArmorModule implements IAlloygeryModule
{
	@Override
	public void initialize()
	{
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(AlloygeryArmorMaterialDataLoader.INSTANCE);

		ArmorNetworkEventRegistry.init();

		ArmorItemRegistry.init();
		ArmorRecipeRegistry.init();
	}

	@Override
	public void initializeClient()
	{
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(ArmorClientReloadListener.INSTANCE);
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(ArmorPartClientReloadListener.INSTANCE);

		ArmorRenderLibRenderers.initClient();
	}
}
