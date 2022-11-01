package amorphia.alloygery.content.tools.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.client.ToolModelBuilder;
import amorphia.alloygery.content.tools.item.part.*;
import amorphia.alloygery.content.tools.item.part.head.*;
import amorphia.alloygery.content.tools.item.tool.*;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static amorphia.alloygery.content.materials.AlloygeryMaterials.*;

public class ToolItemRegistry
{
	public static final Map<String, Item> ITEMS = new LinkedHashMap<>();

	public static void init()
	{
		makeTools();

		makeToolPartsForMaterial(COPPER);
		makeToolPartsForMaterial(BRONZE);
		makeToolPartsForMaterial(IRON);
		makeToolPartsForMaterial(GOLD);
		makeToolPartsForMaterial(ANTANIUM);
		makeToolPartsForMaterial(DIAMOND, true, false, false, false);
		makeToolPartsForMaterial(STEEL);
		makeToolPartsForMaterial(NETHERITE, true, false, false, false);
		makeToolPartsForMaterial(NICKEL);
		makeToolPartsForMaterial(INVAR);
		makeToolPartsForMaterial(CONSTANTAN);
		makeToolPartsForMaterial(CUPRONICKEL);
		makeToolPartsForMaterial(TITANIUM);
		makeToolPartsForMaterial(TITANIUM_GOLD);
		makeToolPartsForMaterial(NITINOL);

		makeToolPartsForMaterial(LEATHER, false, true, false, false);
		makeToolPartsForMaterial(VANILLA_STICK, false, false, false, true);

		makeToolInlayUpgradePartItemsFromMaterials(Set.of(EMERALD));
		makeToolPlatingUpgradePartItemsForMaterials(Set.of(GOLD, NETHERITE));
		makeToolTipplingUpgradePartItemsForMaterials(Set.of(DIAMOND));
	}

	private static void makeTools()
	{
		makeToolSet("", ToolUpgradeType.NONE);
		makeToolSet("embossed_", ToolUpgradeType.EMBOSSED);
		makeToolSet("plated_", ToolUpgradeType.PLATED);
		makeToolSet("tipped_", ToolUpgradeType.TIPPED);
	}

	private static void makeToolSet(String setPrefix, ToolUpgradeType upgradeType)
	{
		register(setPrefix + "dynamic_axe", new DynamicAxeItem(upgradeType));
		register(setPrefix + "dynamic_hoe", new DynamicHoeItem(upgradeType));
		register(setPrefix + "dynamic_pickaxe", new DynamicPickaxeItem(upgradeType));
		register(setPrefix + "dynamic_shovel", new DynamicShovelItem(upgradeType));
		register(setPrefix + "dynamic_sword", new DynamicSwordItem(upgradeType));
	}

	private static void makeToolPartsForMaterial(AlloygeryMaterial material)
	{
		makeToolPartsForMaterial(material, true, true, true, true);
	}

	private static void makeToolPartsForMaterial(AlloygeryMaterial material, boolean makeHeads, boolean makeBindings, boolean makeGuards, boolean makeHandles)
	{
		if (makeHeads)
		{
			registerGeneratedItem(material.getMaterialName() + "_axe_head",
					new AxeHeadItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("axe_head"));

			registerGeneratedItem(material.getMaterialName() + "_hoe_head",
					new HoeHeadItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("hoe_head"));

			registerGeneratedItem(material.getMaterialName() + "_pickaxe_head",
					new PickaxeHeadItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("pickaxe_head"));

			registerGeneratedItem(material.getMaterialName() + "_shovel_head",
					new ShovelHeadItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("shovel_head"));

			registerGeneratedItem(material.getMaterialName() + "_sword_blade",
					new SwordBladeItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("sword_blade"));
		}

		if (makeBindings)
		{
			registerGeneratedItem(material.getMaterialName() + "_binding",
					new ToolBindingItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("binding"));
		}

		if (makeGuards)
		{
			registerGeneratedItem(material.getMaterialName() + "_sword_guard",
					new ToolBindingItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("sword_guard"));
		}

		if (makeHandles)
		{
			registerGeneratedItem(material.getMaterialName() + "_handle",
					new ToolHandleItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("handle"));
		}
	}

	private static void makeToolInlayUpgradePartItemsFromMaterials(Set<AlloygeryMaterial> materials)
	{
		materials.forEach(material -> registerGeneratedItem(material.getMaterialName() + "_tool_inlay", new ToolUpgradePartItem(material), () -> ToolModelBuilder.createPartItemModelJson("tool_inlay")));
	}

	private static void makeToolPlatingUpgradePartItemsForMaterials(Set<AlloygeryMaterial> materials)
	{
		materials.forEach(material -> registerGeneratedItem(material.getMaterialName() + "_tool_plating", new ToolUpgradePartItem(material), () -> ToolModelBuilder.createPartItemModelJson("tool_plating")));
	}

	private static void makeToolTipplingUpgradePartItemsForMaterials(Set<AlloygeryMaterial> materials)
	{
		materials.forEach(material -> registerGeneratedItem(material.getMaterialName() + "_tool_tipping", new ToolUpgradePartItem(material), () -> ToolModelBuilder.createPartItemModelJson("tool_tipping")));
	}

	private static Item registerGeneratedItem(String path, Item item, Supplier<String> modelJsonSupplier)
	{
		Identifier identifier = Alloygery.identifier("item/" + path);
		ToolModelBuilder.register(identifier, modelJsonSupplier);
		return register(path, item);
	}

	private static Item register(String path, Item item)
	{
		ITEMS.put(path, item);
		return Registry.register(Registry.ITEM, Alloygery.identifier(path), item);
	}
}
