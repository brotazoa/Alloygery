package amorphia.alloygery.compat.vsas;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.IAlloygeryModule;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class VsasModule implements IAlloygeryModule
{
	@Override
	public boolean shouldInitialize()
	{
		return FabricLoader.getInstance().isModLoaded("vsas");
	}

	@Override
	public void initialize()
	{
		Alloygery.LOGGER.info("Loading Variant Sticks and Stuff module");

		FabricLoader.getInstance().getModContainer(Alloygery.MOD_ID).ifPresent(
				modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(Alloygery.identifier("vsas_compat"), modContainer, ResourcePackActivationType.ALWAYS_ENABLED)
		);

		VsasToolMaterials.init();
		VsasToolItemRegistry.init();
	}

	@Override
	public void initializeClient()
	{
		//nothing to do here
	}

	static Identifier identify(String path)
	{
		return new Identifier("vsas_compat", path);
	}
}
