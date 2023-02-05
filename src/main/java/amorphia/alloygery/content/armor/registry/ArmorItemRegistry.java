package amorphia.alloygery.content.armor.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.ArmorNBTHelper;
import amorphia.alloygery.content.armor.client.ArmorPartModelBuilder;
import amorphia.alloygery.content.armor.item.*;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import static amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials.*;

public class ArmorItemRegistry
{
	public static final Map<String, Item> ITEMS = new LinkedHashMap<>();

	public static void init()
	{
		makeArmorItems();
		makeCraftingItems();
	}

	private static void makeArmorItems()
	{
		createBaseArmorSet(LEATHER_BASE);
		createBaseArmorSet(WOOL_BASE);
		createBaseArmorSet(CHAIN_BASE);

		createArmorSetWithBase("leather_half_plate", LEATHER_BASE, IRON_HALF_PLATE);
		createArmorSetWithBase("leather_full_plate", LEATHER_BASE, IRON_FULL_PLATE);
		createArmorSetWithBase("wool_half_plate", WOOL_BASE, IRON_HALF_PLATE);
		createArmorSetWithBase("wool_full_plate", WOOL_BASE, IRON_FULL_PLATE);
		createArmorSetWithBase("chain_half_plate", CHAIN_BASE, IRON_HALF_PLATE);
		createArmorSetWithBase("chain_full_plate", CHAIN_BASE, IRON_FULL_PLATE);
	}

	private static void makeCraftingItems()
	{
		createArmorPartItemSet(COPPER_HALF_PLATE);

		createArmorPartItemSet(BRONZE_HALF_PLATE);

		createArmorPartItemSet(IRON_HALF_PLATE);
		createArmorPartItemSet(IRON_FULL_PLATE);

		createArmorPartItemSet(GOLD_HALF_PLATE);

		createArmorPartItemSet(ANTANIUM_HALF_PLATE);
		createArmorPartItemSet(ANTANIUM_FULL_PLATE);

		createArmorPartItemSet(DIAMOND_HALF_PLATE);
		createArmorPartItemSet(DIAMOND_FULL_PLATE);

		createArmorPartItemSet(STEEL_HALF_PLATE);
		createArmorPartItemSet(STEEL_FULL_PLATE);

		createArmorPartItemSet(NETHERITE_HALF_PLATE);
		createArmorPartItemSet(NETHERITE_FULL_PLATE);

		createArmorPartItemSet(NICKEL_HALF_PLATE);
		createArmorPartItemSet(NICKEL_FULL_PLATE);

		createArmorPartItemSet(INVAR_HALF_PLATE);
		createArmorPartItemSet(INVAR_FULL_PLATE);

		createArmorPartItemSet(CONSTANTAN_HALF_PLATE);
		createArmorPartItemSet(CONSTANTAN_FULL_PLATE);

		createArmorPartItemSet(CUPRONICKEL_HALF_PLATE);
		createArmorPartItemSet(CUPRONICKEL_FULL_PLATE);

		createArmorPartItemSet(TITANIUM_HALF_PLATE);
		createArmorPartItemSet(TITANIUM_FULL_PLATE);

		createArmorPartItemSet(TITANIUM_GOLD_HALF_PLATE);
		createArmorPartItemSet(TITANIUM_GOLD_FULL_PLATE);

		createArmorPartItemSet(NITINOL_HALF_PLATE);
		createArmorPartItemSet(NITINOL_FULL_PLATE);
	}

	private static void createBaseArmorSet(AlloygeryArmorMaterial baseMaterial)
	{
		//helmet
		registerGeneratedItem(
				"dynamic_" + baseMaterial.getMaterialName() + "_helmet",
				baseMaterial.isDyeable() ?
						new DyeableDynamicArmorItem(ArmorType.HELMET, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)){
							@Override
							public ItemStack getDefaultStack()
							{
								ItemStack stack = super.getDefaultStack();
								ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial));
								return stack;
							}

							@Override
							public AlloygeryArmorMaterial getDefaultBaseMaterial()
							{
								return baseMaterial;
							}
						} :
						new DynamicArmorItem(ArmorType.HELMET, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)){
							@Override
							public ItemStack getDefaultStack()
							{
								ItemStack stack = super.getDefaultStack();
								ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial));
								return stack;
							}

							@Override
							public AlloygeryArmorMaterial getDefaultBaseMaterial()
							{
								return baseMaterial;
							}
						},
				ArmorPartModelBuilder.createArmorItemModelJson(ArmorType.HELMET, baseMaterial, HIDDEN, HIDDEN)
		);

		//chestplate
		registerGeneratedItem(
				"dynamic_" + baseMaterial.getMaterialName() + "_chestplate",
				baseMaterial.isDyeable() ?
						new DyeableDynamicArmorItem(ArmorType.CHESTPLATE, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)){
							@Override
							public ItemStack getDefaultStack()
							{
								ItemStack stack = super.getDefaultStack();
								ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial));
								return stack;
							}

							@Override
							public AlloygeryArmorMaterial getDefaultBaseMaterial()
							{
								return baseMaterial;
							}
						} :
						new DynamicArmorItem(ArmorType.CHESTPLATE, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)){
							@Override
							public ItemStack getDefaultStack()
							{
								ItemStack stack = super.getDefaultStack();
								ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial));
								return stack;
							}

							@Override
							public AlloygeryArmorMaterial getDefaultBaseMaterial()
							{
								return baseMaterial;
							}
						},
				ArmorPartModelBuilder.createArmorItemModelJson(ArmorType.CHESTPLATE, baseMaterial, HIDDEN, HIDDEN)
		);

		//leggings
		registerGeneratedItem(
				"dynamic_" + baseMaterial.getMaterialName() + "_leggings",
				baseMaterial.isDyeable() ?
						new DyeableDynamicArmorItem(ArmorType.LEGGINGS, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)){
							@Override
							public ItemStack getDefaultStack()
							{
								ItemStack stack = super.getDefaultStack();
								ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial));
								return stack;
							}

							@Override
							public AlloygeryArmorMaterial getDefaultBaseMaterial()
							{
								return baseMaterial;
							}
						} :
						new DynamicArmorItem(ArmorType.LEGGINGS, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)){
							@Override
							public ItemStack getDefaultStack()
							{
								ItemStack stack = super.getDefaultStack();
								ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial));
								return stack;
							}

							@Override
							public AlloygeryArmorMaterial getDefaultBaseMaterial()
							{
								return baseMaterial;
							}
						},
				ArmorPartModelBuilder.createArmorItemModelJson(ArmorType.LEGGINGS, baseMaterial, HIDDEN, HIDDEN)
		);

		//boots
		registerGeneratedItem(
				"dynamic_" + baseMaterial.getMaterialName() + "_boots",
				baseMaterial.isDyeable() ?
						new DyeableDynamicArmorItem(ArmorType.BOOTS, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)){
							@Override
							public ItemStack getDefaultStack()
							{
								ItemStack stack = super.getDefaultStack();
								ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial));
								return stack;
							}

							@Override
							public AlloygeryArmorMaterial getDefaultBaseMaterial()
							{
								return baseMaterial;
							}
						} :
						new DynamicArmorItem(ArmorType.BOOTS, new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP)){
							@Override
							public ItemStack getDefaultStack()
							{
								ItemStack stack = super.getDefaultStack();
								ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial));
								return stack;
							}

							@Override
							public AlloygeryArmorMaterial getDefaultBaseMaterial()
							{
								return baseMaterial;
							}
						},
				ArmorPartModelBuilder.createArmorItemModelJson(ArmorType.BOOTS, baseMaterial, HIDDEN, HIDDEN)
		);
	}

	private static void createArmorSetWithBase(String setName, AlloygeryArmorMaterial baseMaterial, AlloygeryArmorMaterial armorMaterial)
	{
		//helmet
		registerGeneratedItem(
				"dynamic_" + setName + "_helmet",
				new DynamicArmorItem(ArmorType.HELMET){
					@Override
					public ItemStack getDefaultStack()
					{
						ItemStack stack = super.getDefaultStack();
						ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial, armorMaterial));
						return stack;
					}

					@Override
					public AlloygeryArmorMaterial getDefaultBaseMaterial()
					{
						return baseMaterial;
					}

					@Override
					public AlloygeryArmorMaterial getDefaultPlateMaterial()
					{
						return armorMaterial;
					}
				},
				ArmorPartModelBuilder.createArmorItemModelJson(ArmorType.HELMET, baseMaterial, armorMaterial, HIDDEN)
		);

		//chestplate
		registerGeneratedItem(
				"dynamic_" + setName + "_chestplate",
				new DynamicArmorItem(ArmorType.CHESTPLATE){
					@Override
					public ItemStack getDefaultStack()
					{
						ItemStack stack = super.getDefaultStack();
						ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial, armorMaterial));
						return stack;
					}

					@Override
					public AlloygeryArmorMaterial getDefaultBaseMaterial()
					{
						return baseMaterial;
					}

					@Override
					public AlloygeryArmorMaterial getDefaultPlateMaterial()
					{
						return armorMaterial;
					}
				},
				ArmorPartModelBuilder.createArmorItemModelJson(ArmorType.CHESTPLATE, baseMaterial, armorMaterial, HIDDEN)
		);

		//leggings
		registerGeneratedItem(
				"dynamic_" + setName + "_leggings",
				new DynamicArmorItem(ArmorType.LEGGINGS){
					@Override
					public ItemStack getDefaultStack()
					{
						ItemStack stack = super.getDefaultStack();
						ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial, armorMaterial));
						return stack;
					}

					@Override
					public AlloygeryArmorMaterial getDefaultBaseMaterial()
					{
						return baseMaterial;
					}

					@Override
					public AlloygeryArmorMaterial getDefaultPlateMaterial()
					{
						return armorMaterial;
					}
				},
				ArmorPartModelBuilder.createArmorItemModelJson(ArmorType.LEGGINGS, baseMaterial, armorMaterial, HIDDEN)
		);

		//boots
		registerGeneratedItem(
				"dynamic_" + setName + "_boots",
				new DynamicArmorItem(ArmorType.BOOTS){
					@Override
					public ItemStack getDefaultStack()
					{
						ItemStack stack = super.getDefaultStack();
						ArmorNBTHelper.addAlloygeryNBTToArmorStack(stack, ArmorNBTHelper.createArmorNBT(baseMaterial, armorMaterial));
						return stack;
					}

					@Override
					public AlloygeryArmorMaterial getDefaultBaseMaterial()
					{
						return baseMaterial;
					}

					@Override
					public AlloygeryArmorMaterial getDefaultPlateMaterial()
					{
						return armorMaterial;
					}
				},
				ArmorPartModelBuilder.createArmorItemModelJson(ArmorType.BOOTS, baseMaterial, armorMaterial, HIDDEN)
		);
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

	private static Item registerGeneratedItem(String path, Item item, Supplier<String> modelJsonSupplier)
	{
		Identifier identifier = Alloygery.identifier("item/" + path);
		ArmorPartModelBuilder.register(identifier, modelJsonSupplier);
		return register(path, item);
	}

	private static Item register(String path, Item item)
	{
		ITEMS.put(path, item);
		return Registry.register(Registry.ITEM, Alloygery.identifier(path), item);
	}
}
