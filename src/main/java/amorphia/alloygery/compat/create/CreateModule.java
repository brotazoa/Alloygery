package amorphia.alloygery.compat.create;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.IAlloygeryModule;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class CreateModule implements IAlloygeryModule
{
	@Override
	public boolean shouldInitialize()
	{
		return FabricLoader.getInstance().isModLoaded("create");
	}

	@Override
	public void initialize()
	{
		Alloygery.LOGGER.info("loading create module");

		FabricLoader.getInstance().getModContainer(Alloygery.MOD_ID).ifPresent(
				modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(Alloygery.identifier("create_compat"), modContainer, ResourcePackActivationType.ALWAYS_ENABLED)
		);

		//register optional create recipe tweaks pack
		FabricLoader.getInstance().getModContainer(Alloygery.MOD_ID).ifPresent(
				modContainer -> ResourceManagerHelper.registerBuiltinResourcePack(Alloygery.identifier("create_recipe_tweaks"), modContainer, ResourcePackActivationType.NORMAL)
		);

		CreateToolMaterials.init();
		CreateToolItemRegistry.init();
		CreateMetalItemRegistry.init();
		CreateArmorMaterials.init();
		CreateArmorItemRegistry.init();
	}

	@Override
	public void initializeClient()
	{
		//nothing to do here
	}

	static Identifier identify(String path)
	{
		return new Identifier("create_compat", path);
	}
}
