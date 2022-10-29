package amorphia.alloygery.content.machines;

import amorphia.alloygery.IAlloygeryModule;
import amorphia.alloygery.content.machines.registry.*;

public class MachineModule implements IAlloygeryModule
{
	@Override
	public void initialize()
	{
		MachineBlockRegistry.init();
		MachineItemRegistry.init();
		MachineRecipeRegistry.init();
		MachineScreenRegistry.init();
		MachineStatisticRegistry.init();
	}

	@Override
	public void initializeClient()
	{
		MachineBlockRegistry.initClient();
		MachineScreenRegistry.initClient();
	}
}
