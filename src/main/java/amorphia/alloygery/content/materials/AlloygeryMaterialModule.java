package amorphia.alloygery.content.materials;

import amorphia.alloygery.IAlloygeryModule;
import amorphia.alloygery.content.materials.data.AlloygeryMaterialDataLoader;
import amorphia.alloygery.content.materials.registry.AlloygeryMaterialNetworkEventRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class AlloygeryMaterialModule implements IAlloygeryModule
{
	@Override
	public void initialize()
	{
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(AlloygeryMaterialDataLoader.INSTANCE);

		AlloygeryMaterialNetworkEventRegistry.init();
	}

	@Override
	public void initializeClient()
	{
		AlloygeryMaterialNetworkEventRegistry.initClient();
	}
}
