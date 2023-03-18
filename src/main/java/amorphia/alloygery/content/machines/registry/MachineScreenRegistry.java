package amorphia.alloygery.content.machines.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.machines.screen.AlloyKilnScreen;
import amorphia.alloygery.content.machines.screen.AlloyKilnScreenHandler;
import amorphia.alloygery.content.machines.screen.SmithingAnvilScreen;
import amorphia.alloygery.content.machines.screen.SmithingAnvilScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

public class MachineScreenRegistry
{
	public static ScreenHandlerType<AlloyKilnScreenHandler> ALLOY_KILN_SCREEN_HANDLER_TYPE;
	public static ScreenHandlerType<SmithingAnvilScreenHandler> SMITHING_ANVIL_SCREEN_HANDLER_TYPE;

	public static void init()
	{
		ALLOY_KILN_SCREEN_HANDLER_TYPE = Registry.register(Registry.SCREEN_HANDLER, Alloygery.identifier("kiln"), new ScreenHandlerType<>(AlloyKilnScreenHandler::new));
		SMITHING_ANVIL_SCREEN_HANDLER_TYPE = Registry.register(Registry.SCREEN_HANDLER, Alloygery.identifier("smithing_anvil"), new ScreenHandlerType<>(SmithingAnvilScreenHandler::new));
	}

	public static void initClient()
	{
		HandledScreens.register(ALLOY_KILN_SCREEN_HANDLER_TYPE, AlloyKilnScreen::new);
		HandledScreens.register(SMITHING_ANVIL_SCREEN_HANDLER_TYPE, SmithingAnvilScreen::new);
	}
}
