package amorphia.alloygery;

import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.content.armor.ArmorModule;
import amorphia.alloygery.content.armor.data.AlloygeryArmorMaterialDataHelper;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;
import amorphia.alloygery.content.machines.MachineModule;
import amorphia.alloygery.content.metals.MetalModule;
import amorphia.alloygery.content.tools.ToolModule;
import amorphia.alloygery.content.tools.data.AlloygeryToolMaterialDataHelper;
import amorphia.alloygery.content.tools.registry.AlloygeryToolMaterialRegistry;
import amorphia.alloygery.registry.ModAdvancements;
import amorphia.alloygery.registry.ModResourceConditions;
import amorphia.alloygery.registry.ModTags;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Alloygery implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "alloygery";
	public static final Logger LOGGER = LogManager.getLogger();

	public static Item ALLOYGERY_TAB_ITEM = Registry.register(Registry.ITEM, Alloygery.identifier("alloygery_tab_item"), new Item(new Item.Settings()));
	public static ItemGroup ALLOYGERY_TAB_GROUP = FabricItemGroupBuilder.create(Alloygery.identifier("alloygery_group")).icon(() -> new ItemStack(ALLOYGERY_TAB_ITEM)).build();

	public static final List<IAlloygeryModule> MODULES = Lists.newArrayList(new MachineModule(), new MetalModule(), new ToolModule(), new ArmorModule());

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

		ModAdvancements.init();

		MODULES.forEach(module -> {
			if(module.shouldInitialize())
				module.initialize();
		});

		//dumpToolMaterialFiles();
		//dumpArmorMaterialFiles();
	}

	public static Identifier identifier(String path)
	{
		return new Identifier(MOD_ID, path);
	}

	private static void dumpToolMaterialFiles()
	{
		AlloygeryToolMaterialRegistry.forEach((identifier, alloygeryToolMaterial) -> {

			Path path = FabricLoader.getInstance().getConfigDir().resolve(identifier.getNamespace() + "/tool_materials/" + identifier.getPath() + ".json");
			try
			{
				Files.createDirectories(path.getParent());
				BufferedWriter writer = Files.newBufferedWriter(path);
				writer.write(AlloygeryToolMaterialDataHelper.getJsonStringFromToolMaterial(alloygeryToolMaterial).orElse("{}"));
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}

	private static void dumpArmorMaterialFiles()
	{
		AlloygeryArmorMaterialRegistry.forEach((identifier, alloygeryArmorMaterial) -> {

			Path path = FabricLoader.getInstance().getConfigDir().resolve(identifier.getNamespace() + "/armor_materials/" + identifier.getPath() + ".json");
			try
			{
				Files.createDirectories(path.getParent());
				BufferedWriter writer = Files.newBufferedWriter(path);
				writer.write(AlloygeryArmorMaterialDataHelper.getJsonStringFromArmorMaterial(alloygeryArmorMaterial).orElse("{}"));
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}
}
