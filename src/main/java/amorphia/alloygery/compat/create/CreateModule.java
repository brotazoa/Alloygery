package amorphia.alloygery.compat.create;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.IAlloygeryModule;
import net.minecraft.util.Identifier;

public class CreateModule implements IAlloygeryModule
{
	@Override
	public void initialize()
	{
		Alloygery.LOGGER.info("loading create module");

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

	public static Identifier identify(String path)
	{
		return new Identifier("create_compat", path);
	}
}
