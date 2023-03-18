package amorphia.alloygery.compat.vsas;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.client.ToolModelBuilder;
import amorphia.alloygery.content.tools.item.part.ToolHandleItem;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import com.google.common.collect.Maps;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.function.Supplier;

public class VsasToolItemRegistry
{
	public static final Map<String, Item> ITEMS = Maps.newLinkedHashMap();

	static void init()
	{
		makeHandleForMaterial(VsasToolMaterials.ACACIA);
		makeHandleForMaterial(VsasToolMaterials.BIRCH);
		makeHandleForMaterial(VsasToolMaterials.CRIMSON);
		makeHandleForMaterial(VsasToolMaterials.DARK_OAK);
		makeHandleForMaterial(VsasToolMaterials.JUNGLE);
		makeHandleForMaterial(VsasToolMaterials.OAK);
		makeHandleForMaterial(VsasToolMaterials.SPRUCE);
		makeHandleForMaterial(VsasToolMaterials.WARPED);
	}

	private static void makeHandleForMaterial(AlloygeryToolMaterial material)
	{
		registerGeneratedItem(material.getMaterialName() + "_handle",
				new ToolHandleItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
				ToolModelBuilder.createToolHandleItemModelJson()
		);
	}

	private static Item registerGeneratedItem(String path, Item item, Supplier<String> jsonModelSupplier)
	{
		Identifier identifier = VsasModule.identify("item/" + path);
		ToolModelBuilder.register(identifier, jsonModelSupplier);
		return register(path, item);
	}

	private static Item register(String path, Item item)
	{
		ITEMS.put(path, item);
		return Registry.register(Registry.ITEM, VsasModule.identify(path), item);
	}
}
