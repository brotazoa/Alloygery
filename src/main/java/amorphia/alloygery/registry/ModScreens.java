package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.screen.AlloyKilnScreen;
import amorphia.alloygery.content.screen.AlloyKilnScreenHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreens
{
	public static ScreenHandlerType<AlloyKilnScreenHandler> ALLOY_KILN_SCREEN_HANDLER_TYPE;

	public static void register()
	{
		ALLOY_KILN_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(Alloygery.identifier("kiln"), AlloyKilnScreenHandler::new);
	}

	public static void registerClient()
	{
		ScreenRegistry.register(ALLOY_KILN_SCREEN_HANDLER_TYPE, AlloyKilnScreen::new);
	}
}
