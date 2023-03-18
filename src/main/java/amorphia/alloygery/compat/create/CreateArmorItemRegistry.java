package amorphia.alloygery.compat.create;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.client.ArmorPartModelBuilder;
import amorphia.alloygery.content.armor.item.ArmorPartItem;
import amorphia.alloygery.content.armor.item.ArmorType;
import amorphia.alloygery.content.armor.item.DyeableArmorPartItem;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import com.google.common.collect.Maps;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.function.Supplier;

public class CreateArmorItemRegistry
{
	public static final Map<String, Item> ITEMS = Maps.newLinkedHashMap();

	static void init()
	{
		createArmorPartItemSet(CreateArmorMaterials.ANDESITE_ALLOY_PLATE);
		createArmorPartItemSet(CreateArmorMaterials.ADNESITE_ALLOY_HEAVY_PLATE);
		createArmorPartItemSet(CreateArmorMaterials.BRASS_PLATE);
	}

	private static void createArmorPartItemSet(AlloygeryArmorMaterial material)
	{
		//helmet
		registerGeneratedItem(
				material.getMaterialName() + "_helmet_part",
				material.isDyeable() ?
						new DyeableArmorPartItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, material.getLayer(), ArmorType.HELMET) :
						new ArmorPartItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, material.getLayer(), ArmorType.HELMET),
				ArmorPartModelBuilder.createArmorPartItemModelJson(ArmorType.HELMET, material)
		);

		//chestplate
		registerGeneratedItem(
				material.getMaterialName() + "_chestplate_part",
				material.isDyeable() ?
						new DyeableArmorPartItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, material.getLayer(), ArmorType.CHESTPLATE) :
						new ArmorPartItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, material.getLayer(), ArmorType.CHESTPLATE),
				ArmorPartModelBuilder.createArmorPartItemModelJson(ArmorType.CHESTPLATE, material)
		);

		//leggings
		registerGeneratedItem(
				material.getMaterialName() + "_leggings_part",
				material.isDyeable() ?
						new DyeableArmorPartItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, material.getLayer(), ArmorType.LEGGINGS) :
						new ArmorPartItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, material.getLayer(), ArmorType.LEGGINGS),
				ArmorPartModelBuilder.createArmorPartItemModelJson(ArmorType.LEGGINGS, material)
		);

		//boots
		registerGeneratedItem(
				material.getMaterialName() + "_boots_part",
				material.isDyeable() ?
						new DyeableArmorPartItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, material.getLayer(), ArmorType.BOOTS) :
						new ArmorPartItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material, material.getLayer(), ArmorType.BOOTS),
				ArmorPartModelBuilder.createArmorPartItemModelJson(ArmorType.BOOTS, material)
		);
	}

	private static Item registerGeneratedItem(String path, Item item, Supplier<String> jsonModelSupplier)
	{
		Identifier identifier = CreateModule.identify("item/" + path);
		ArmorPartModelBuilder.register(identifier, jsonModelSupplier);
		return register(path, item);
	}

	private static Item register(String path, Item item)
	{
		ITEMS.put(path, item);
		return Registry.register(Registry.ITEM, CreateModule.identify(path), item);
	}
}
