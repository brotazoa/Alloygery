package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.screen.AlloyKilnScreen;
import amorphia.alloygery.content.screen.AlloyKilnScreenHandler;
import amorphia.alloygery.content.screen.SmithingAnvilScreen;
import amorphia.alloygery.content.screen.SmithingAnvilScreenHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;

public class ModScreens
{
	public static ScreenHandlerType<AlloyKilnScreenHandler> ALLOY_KILN_SCREEN_HANDLER_TYPE;
	public static ScreenHandlerType<SmithingAnvilScreenHandler> SMITHING_ANVIL_SCREEN_HANDLER_TYPE;

	public static void register()
	{
		ALLOY_KILN_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(Alloygery.identifier("kiln"), AlloyKilnScreenHandler::new);
		SMITHING_ANVIL_SCREEN_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(Alloygery.identifier("smithing_anvil"), SmithingAnvilScreenHandler::new);
	}

	public static void registerClient()
	{
		ScreenRegistry.register(ALLOY_KILN_SCREEN_HANDLER_TYPE, AlloyKilnScreen::new);
		ScreenRegistry.register(SMITHING_ANVIL_SCREEN_HANDLER_TYPE, SmithingAnvilScreen::new);
	}
}
