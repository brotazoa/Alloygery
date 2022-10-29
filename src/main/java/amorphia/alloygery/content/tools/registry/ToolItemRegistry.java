package amorphia.alloygery.content.tools.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.client.ToolModelBuilder;
import amorphia.alloygery.content.tools.item.part.*;
import amorphia.alloygery.content.tools.item.part.head.*;
import amorphia.alloygery.content.tools.item.tool.*;
import amorphia.alloygery.content.tools.material.ToolMaterial;
import amorphia.alloygery.content.tools.material.ToolMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static amorphia.alloygery.content.tools.material.ToolMaterials.*;

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
		makeToolSet("");
		makeToolSet("embossed_");
		makeToolSet("plated_");
		//makeToolSet("tipped_");
		makeTippedToolSet();
	}

	private static void makeToolSet(String setPrefix)
	{
		register(setPrefix + "dynamic_axe", new DynamicAxeItem(new Item.Settings()));
		register(setPrefix + "dynamic_hoe", new DynamicHoeItem(new Item.Settings()));
		register(setPrefix + "dynamic_pickaxe", new DynamicPickaxeItem(new Item.Settings()));
		register(setPrefix + "dynamic_shovel", new DynamicShovelItem(new Item.Settings()));
		register(setPrefix + "dynamic_sword", new DynamicSwordItem(new Item.Settings()));
	}

	private static void makeTippedToolSet()
	{
		register("tipped_dynamic_axe", new DynamicAxeItem(new Item.Settings()){
			@Override
			public ItemStack getDefaultStack()
			{
				ItemStack tool = new ItemStack(this);
				ToolNBTHelper.addAlloygeryNBTToToolStack(tool, ToolNBTHelper.createToolNBTFromMaterials(IRON, VANILLA_STICK, DIAMOND, ToolType.AXE));
				return tool;
			}
		});

		register("tipped_dynamic_hoe", new DynamicAxeItem(new Item.Settings()){
			@Override
			public ItemStack getDefaultStack()
			{
				ItemStack tool = new ItemStack(this);
				ToolNBTHelper.addAlloygeryNBTToToolStack(tool, ToolNBTHelper.createToolNBTFromMaterials(IRON, VANILLA_STICK, DIAMOND, ToolType.HOE));
				return tool;
			}
		});

		register("tipped_dynamic_pickaxe", new DynamicAxeItem(new Item.Settings()){
			@Override
			public ItemStack getDefaultStack()
			{
				ItemStack tool = new ItemStack(this);
				ToolNBTHelper.addAlloygeryNBTToToolStack(tool, ToolNBTHelper.createToolNBTFromMaterials(IRON, VANILLA_STICK, DIAMOND, ToolType.PICKAXE));
				return tool;
			}
		});

		register("tipped_dynamic_shovel", new DynamicAxeItem(new Item.Settings()){
			@Override
			public ItemStack getDefaultStack()
			{
				ItemStack tool = new ItemStack(this);
				ToolNBTHelper.addAlloygeryNBTToToolStack(tool, ToolNBTHelper.createToolNBTFromMaterials(IRON, VANILLA_STICK, DIAMOND, ToolType.SHOVEL));
				return tool;
			}
		});

		register("tipped_dynamic_sword", new DynamicAxeItem(new Item.Settings()){
			@Override
			public ItemStack getDefaultStack()
			{
				ItemStack tool = new ItemStack(this);
				ToolNBTHelper.addAlloygeryNBTToToolStack(tool, ToolNBTHelper.createToolNBTFromMaterials(IRON, VANILLA_STICK, DIAMOND, ToolType.SWORD));
				return tool;
			}
		});
	}

	private static void makeToolPartsForMaterial(ToolMaterial material)
	{
		makeToolPartsForMaterial(material, true, true, true, true);
	}

	private static void makeToolPartsForMaterial(ToolMaterial material, boolean makeHeads, boolean makeBindings, boolean makeGuards, boolean makeHandles)
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

	private static void makeToolInlayUpgradePartItemsFromMaterials(Set<ToolMaterial> materials)
	{
		materials.forEach(material -> registerGeneratedItem(material.getMaterialName() + "_tool_inlay", new UpgradeItem(material), () -> ToolModelBuilder.createPartItemModelJson("tool_inlay")));
	}

	private static void makeToolPlatingUpgradePartItemsForMaterials(Set<ToolMaterial> materials)
	{
		materials.forEach(material -> registerGeneratedItem(material.getMaterialName() + "_tool_plating", new UpgradeItem(material), () -> ToolModelBuilder.createPartItemModelJson("tool_plating")));
	}

	private static void makeToolTipplingUpgradePartItemsForMaterials(Set<ToolMaterial> materials)
	{
		materials.forEach(material -> registerGeneratedItem(material.getMaterialName() + "_tool_tipping", new UpgradeItem(material), () -> ToolModelBuilder.createPartItemModelJson("tool_tipping")));
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
