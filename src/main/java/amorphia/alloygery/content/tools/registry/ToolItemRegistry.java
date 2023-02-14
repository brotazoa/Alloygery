package amorphia.alloygery.content.tools.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.client.ToolModelBuilder;
import amorphia.alloygery.content.tools.item.part.*;
import amorphia.alloygery.content.tools.item.part.head.*;
import amorphia.alloygery.content.tools.item.tool.*;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static amorphia.alloygery.content.tools.material.AlloygeryToolMaterials.*;

public class ToolItemRegistry
{
	public static final Map<String, Item> ITEMS = new LinkedHashMap<>();

	public static void init()
	{
		makeToolSetForMaterial(FLINT, ToolUpgradeType.NONE, EnumSet.of(ToolType.AXE, ToolType.PICKAXE, ToolType.SHOVEL, ToolType.HOE), Alloygery.ALLOYGERY_TAB_GROUP);

		makeToolSetForMaterial(COPPER, ToolUpgradeType.NONE, Alloygery.ALLOYGERY_TAB_GROUP);
		makeToolSetForMaterial(COPPER, ToolUpgradeType.EMBOSSED);
		makeToolSetForMaterial(COPPER, ToolUpgradeType.PLATED);
		makeToolSetForMaterial(COPPER, ToolUpgradeType.TIPPED);

		makeToolSetForMaterial(BRONZE, ToolUpgradeType.NONE, Alloygery.ALLOYGERY_TAB_GROUP);
		makeToolSetForMaterial(BRONZE, ToolUpgradeType.EMBOSSED);
		makeToolSetForMaterial(BRONZE, ToolUpgradeType.PLATED);
		makeToolSetForMaterial(BRONZE, ToolUpgradeType.TIPPED);

		makeToolSetsForMaterial(IRON);
		makeToolSetsForMaterial(GOLD);
		makeToolSetsForMaterial(ANTANIUM);
		makeToolSetsForMaterial(DIAMOND);
		makeToolSetsForMaterial(STEEL);
		makeToolSetsForMaterial(NETHERITE);
		makeToolSetsForMaterial(NICKEL);
		makeToolSetsForMaterial(INVAR);
		makeToolSetsForMaterial(CONSTANTAN);
		makeToolSetsForMaterial(CUPRONICKEL);
		makeToolSetsForMaterial(TITANIUM);
		makeToolSetsForMaterial(TITANIUM_GOLD);
		makeToolSetsForMaterial(NITINOL);

		makeToolPartsForMaterial(FLINT, EnumSet.of(ToolType.AXE, ToolType.HOE, ToolType.PICKAXE, ToolType.SHOVEL), false, false, false);
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

//		makeToolInlayUpgradePartItemsFromMaterials(Set.of(EMERALD));
//		makeToolPlatingUpgradePartItemsForMaterials(Set.of(GOLD, NETHERITE));
//		makeToolTipplingUpgradePartItemsForMaterials(Set.of(DIAMOND));
	}

	private static void makeToolSetsForMaterial(AlloygeryToolMaterial material)
	{
		makeToolSetsForMaterial(material, (ItemGroup) null);
	}

	private static void makeToolSetsForMaterial(AlloygeryToolMaterial material, ItemGroup group)
	{
		makeToolSetForMaterial(material, ToolUpgradeType.NONE, group);
		makeToolSetForMaterial(material, ToolUpgradeType.EMBOSSED, group);
		makeToolSetForMaterial(material, ToolUpgradeType.PLATED, group);
		makeToolSetForMaterial(material, ToolUpgradeType.TIPPED, group);
	}

	private static void makeToolSetForMaterial(AlloygeryToolMaterial material, ToolUpgradeType upgradeType)
	{
		makeToolSetForMaterial(material, upgradeType, EnumSet.of(ToolType.AXE, ToolType.HOE, ToolType.PICKAXE, ToolType.SHOVEL, ToolType.SWORD));
	}

	private static void makeToolSetForMaterial(AlloygeryToolMaterial material, ToolUpgradeType upgradeType, ItemGroup group)
	{
		makeToolSetForMaterial(material, upgradeType, EnumSet.allOf(ToolType.class), group);
	}

	private static void makeToolSetForMaterial(AlloygeryToolMaterial material, ToolUpgradeType upgradeType, EnumSet<ToolType> toolTypes)
	{
		makeToolSetForMaterial(material, upgradeType, toolTypes, null);
	}

	private static void makeToolSetForMaterial(AlloygeryToolMaterial material, ToolUpgradeType upgradeType, EnumSet<ToolType> toolTypes, ItemGroup group)
	{
		if (toolTypes.contains(ToolType.AXE))
		{
			final String path = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + material.getMaterialName() + "_axe";
			final String modelParent = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + "dynamic_axe";
			final ToolType type = ToolType.AXE;

			registerGeneratedItem(
					path,
					new DynamicAxeItem(new Item.Settings().group(group), upgradeType){
						@Override
						public ItemStack getDefaultStack()
						{
							ItemStack stack = super.getDefaultStack();
							ToolNBTHelper.addAlloygeryNBTToToolStack(stack, upgradeType == ToolUpgradeType.NONE ?
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, type) :
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, getDefaultMaterialForUpgrade(upgradeType), type)
							);
							return stack;
						}

						@Override
						public AlloygeryToolMaterial getDefaultHeadMaterial()
						{
							return material;
						}
					},
					() -> ToolModelBuilder.createToolItemModelJson(modelParent)
			);
		}

		if (toolTypes.contains(ToolType.HOE))
		{
			final String path = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + material.getMaterialName() + "_hoe";
			final String modelParent = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + "dynamic_hoe";
			final ToolType type = ToolType.HOE;

			registerGeneratedItem(
					path,
					new DynamicHoeItem(new Item.Settings().group(group), upgradeType){
						@Override
						public ItemStack getDefaultStack()
						{
							ItemStack stack = super.getDefaultStack();
							ToolNBTHelper.addAlloygeryNBTToToolStack(stack, upgradeType == ToolUpgradeType.NONE ?
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, type) :
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, getDefaultMaterialForUpgrade(upgradeType), type)
							);
							return stack;
						}

						@Override
						public AlloygeryToolMaterial getDefaultHeadMaterial()
						{
							return material;
						}
					},
					() -> ToolModelBuilder.createToolItemModelJson(modelParent)
			);
		}

		if (toolTypes.contains(ToolType.PICKAXE))
		{
			final String path = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + material.getMaterialName() + "_pickaxe";
			final String modelParent = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + "dynamic_pickaxe";
			final ToolType type = ToolType.PICKAXE;

			registerGeneratedItem(
					path,
					new DynamicPickaxeItem(new Item.Settings().group(group), upgradeType){
						@Override
						public ItemStack getDefaultStack()
						{
							ItemStack stack = super.getDefaultStack();
							ToolNBTHelper.addAlloygeryNBTToToolStack(stack, upgradeType == ToolUpgradeType.NONE ?
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, type) :
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, getDefaultMaterialForUpgrade(upgradeType), type)
							);
							return stack;
						}

						@Override
						public AlloygeryToolMaterial getDefaultHeadMaterial()
						{
							return material;
						}
					},
					() -> ToolModelBuilder.createToolItemModelJson(modelParent)
			);
		}

		if (toolTypes.contains(ToolType.SHOVEL))
		{
			final String path = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + material.getMaterialName() + "_shovel";
			final String modelParent = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + "dynamic_shovel";
			final ToolType type = ToolType.SHOVEL;

			registerGeneratedItem(
					path,
					new DynamicShovelItem(new Item.Settings().group(group), upgradeType){
						@Override
						public ItemStack getDefaultStack()
						{
							ItemStack stack = super.getDefaultStack();
							ToolNBTHelper.addAlloygeryNBTToToolStack(stack, upgradeType == ToolUpgradeType.NONE ?
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, type) :
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, getDefaultMaterialForUpgrade(upgradeType), type)
							);
							return stack;
						}

						@Override
						public AlloygeryToolMaterial getDefaultHeadMaterial()
						{
							return material;
						}
					},
					() -> ToolModelBuilder.createToolItemModelJson(modelParent)
			);
		}

		if (toolTypes.contains(ToolType.SWORD))
		{
			final String path = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + material.getMaterialName() + "_sword";
			final String modelParent = (upgradeType == ToolUpgradeType.NONE ? "" : upgradeType.getName() + "_") + "dynamic_sword";
			final ToolType type = ToolType.SWORD;

			registerGeneratedItem(
					path,
					new DynamicSwordItem(new Item.Settings().group(group), upgradeType){
						@Override
						public ItemStack getDefaultStack()
						{
							ItemStack stack = super.getDefaultStack();
							ToolNBTHelper.addAlloygeryNBTToToolStack(stack, upgradeType == ToolUpgradeType.NONE ?
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, type) :
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, getDefaultMaterialForUpgrade(upgradeType), type)
							);
							return stack;
						}

						@Override
						public AlloygeryToolMaterial getDefaultHeadMaterial()
						{
							return material;
						}
					},
					() -> ToolModelBuilder.createToolItemModelJson(modelParent)
			);
		}
	}

	private static void makeToolPartsForMaterial(AlloygeryToolMaterial material)
	{
		makeToolPartsForMaterial(material, true, true, true, true);
	}

	private static void makeToolPartsForMaterial(AlloygeryToolMaterial material, boolean makeHeads, boolean makeBindings, boolean makeGuards, boolean makeHandles)
	{
		makeToolPartsForMaterial(material, makeHeads ? EnumSet.allOf(ToolType.class) : EnumSet.noneOf(ToolType.class), makeBindings, makeGuards, makeHandles);
	}

	private static void makeToolPartsForMaterial(AlloygeryToolMaterial material, EnumSet<ToolType> headTypes, boolean makeBindings, boolean makeGuards, boolean makeHandles)
	{
		if (headTypes.contains(ToolType.AXE))
		{
			registerGeneratedItem(material.getMaterialName() + "_axe_head",
					new AxeHeadItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("axe_head"));
		}

		if (headTypes.contains(ToolType.HOE))
		{
			registerGeneratedItem(material.getMaterialName() + "_hoe_head",
					new HoeHeadItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("hoe_head"));
		}

		if (headTypes.contains(ToolType.PICKAXE))
		{
			registerGeneratedItem(material.getMaterialName() + "_pickaxe_head",
					new PickaxeHeadItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("pickaxe_head"));
		}

		if (headTypes.contains(ToolType.SHOVEL))
		{
			registerGeneratedItem(material.getMaterialName() + "_shovel_head",
					new ShovelHeadItem(new Item.Settings().group(Alloygery.ALLOYGERY_TAB_GROUP), material),
					() -> ToolModelBuilder.createPartItemModelJson("shovel_head"));
		}

		if (headTypes.contains(ToolType.SWORD))
		{
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

	private static void makeToolInlayUpgradePartItemsFromMaterials(Set<AlloygeryToolMaterial> materials)
	{
		materials.forEach(material -> registerGeneratedItem(material.getMaterialName() + "_tool_inlay", new ToolUpgradePartItem(material), () -> ToolModelBuilder.createPartItemModelJson("tool_inlay")));
	}

	private static void makeToolPlatingUpgradePartItemsForMaterials(Set<AlloygeryToolMaterial> materials)
	{
		materials.forEach(material -> registerGeneratedItem(material.getMaterialName() + "_tool_plating", new ToolUpgradePartItem(material), () -> ToolModelBuilder.createPartItemModelJson("tool_plating")));
	}

	private static void makeToolTipplingUpgradePartItemsForMaterials(Set<AlloygeryToolMaterial> materials)
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

	//FIXME: move this out of here
	public static AlloygeryToolMaterial getDefaultMaterialForUpgrade(ToolUpgradeType upgradeType)
	{
		return switch (upgradeType)
		{
			case EMBOSSED -> EMERALD;
			case PLATED -> NETHERITE;
			case TIPPED -> DIAMOND;
			default -> UNKNOWN;
		};
	}
}
