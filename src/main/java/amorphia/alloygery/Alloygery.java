package amorphia.alloygery;

import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.content.machines.MachineModule;
import amorphia.alloygery.content.metals.MetalModule;
import amorphia.alloygery.content.tools.ToolModule;
import amorphia.alloygery.registry.ModResourceConditions;
import amorphia.alloygery.registry.ModTags;
import com.google.common.collect.Lists;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Alloygery implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "alloygery";
	public static final Logger LOGGER = LogManager.getLogger();

	public static final Set<Integer> ALLOYGERY_DATA_VERSIONS = Set.of(1);

	public static Item ALLOYGERY_TAB_ITEM = Registry.register(Registry.ITEM, Alloygery.identifier("alloygery_tab_item"), new Item(new Item.Settings()));
	public static ItemGroup ALLOYGERY_TAB_GROUP = FabricItemGroupBuilder.create(Alloygery.identifier("alloygery_group")).icon(() -> new ItemStack(ALLOYGERY_TAB_ITEM)).build();

	public static final List<IAlloygeryModule> MODULES = Lists.newArrayList(new MachineModule(), new MetalModule(), new ToolModule());

	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient()
	{
		MODULES.forEach(module -> {
			if(module.shouldInitialize())
				module.initializeClient();
		});
	}

	@Override
	public void onInitialize()
	{
		AlloygeryConfig.loadFromFile();

		ModResourceConditions.register();

		ModTags.register();

		MODULES.forEach(module -> {
			if(module.shouldInitialize())
				module.initialize();
		});
	}

	public static Identifier identifier(String path)
	{
		return new Identifier(MOD_ID, path);
	}
}
