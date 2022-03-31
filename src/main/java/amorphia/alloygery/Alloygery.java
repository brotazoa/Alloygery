package amorphia.alloygery;

import amorphia.alloygery.config.AlloygeryConfig;
import amorphia.alloygery.content.client.AlloygeryColorProviderReloadListener;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.data.AlloygeryMaterialDataLoader;
import amorphia.alloygery.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Alloygery implements ModInitializer, ClientModInitializer
{
	public static final String MOD_ID = "alloygery";
	public static final Logger LOGGER = LogManager.getLogger();

	public static ItemGroup ALLOYGERY_GROUP_BLOCKS = FabricItemGroupBuilder.create(Alloygery.identifier("blocks")).icon(() -> new ItemStack(ModItems.BLOCKS_TAB_ITEM)).build();
	public static ItemGroup ALLOYGERY_GROUP_MATERIALS = FabricItemGroupBuilder.create(Alloygery.identifier("materials")).icon(() -> new ItemStack(ModItems.MATERIALS_TAB_ITEM)).build();
	public static ItemGroup ALLOYGERY_GROUP_GEAR = FabricItemGroupBuilder.create(Alloygery.identifier("gear")).icon(() -> new ItemStack(ModItems.GEAR_TAB_ITEM)).build();
	public static ItemGroup ALLOYGERY_GROUP_PARTS = FabricItemGroupBuilder.create(Alloygery.identifier("parts")).icon(() -> new ItemStack(ModItems.PARTS_TAB_ITEM)).build();

	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient()
	{
		ModScreens.registerClient();

		ModNetworking.registerClient();

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(AlloygeryColorProviderReloadListener.INSTANCE);
	}

	@Override
	public void onInitialize()
	{
		AlloygeryConfig.loadFromFile();

		ModResourceConditions.register();

		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(AlloygeryMaterialDataLoader.INSTANCE);

		AlloygeryMaterials.init();

		ModBlocks.register();
		ModItems.register();
		ModOreGeneration.register();
		ModRecipes.register();
		ModScreens.register();
		ModStatistics.register();

		ModNetworking.register();

		//FIXME: remove this
		//dumpMaterialData();
	}

	public static Identifier identifier(String path)
	{
		return new Identifier(MOD_ID, path);
	}

	//FIXME: remove this
	//this is just for development purposes, so I don't have to handwrite the files
	private static void dumpMaterialData()
	{
		AlloygeryMaterials.ALLOYGERY_MATERIALS.forEach(material -> {
			Identifier id = AlloygeryMaterials.ALLOYGERY_MATERIALS.getId(material);

			Path materialPath = FabricLoader.getInstance().getConfigDir().resolve(id.getNamespace() + "/" + id.getPath() + ".json");
			try
			{
				Files.createDirectories(materialPath.getParent());
				BufferedWriter writer = Files.newBufferedWriter(materialPath);
				AlloygeryMaterial.GSON.toJson(material, writer);
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}
}
