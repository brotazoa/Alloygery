package amorphia.alloygery.compat.create;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.client.ToolModelBuilder;
import amorphia.alloygery.content.tools.item.part.ToolBindingItem;
import amorphia.alloygery.content.tools.item.part.ToolHandleItem;
import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.tools.item.part.ToolUpgradeType;
import amorphia.alloygery.content.tools.item.part.head.*;
import amorphia.alloygery.content.tools.item.tool.*;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import amorphia.alloygery.content.tools.registry.ToolItemRegistry;
import com.google.common.collect.Maps;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.EnumSet;
import java.util.Map;
import java.util.function.Supplier;

import static amorphia.alloygery.content.tools.material.AlloygeryToolMaterials.VANILLA_STICK;

public class CreateToolItemRegistry
{
	public static final Map<String, Item> ITEMS = Maps.newLinkedHashMap();

	static void init()
	{
		makeToolSetsForMaterial(CreateToolMaterials.ANDESITE_ALLOY, null);
		makeToolSetsForMaterial(CreateToolMaterials.BRASS, null);

		makeToolPartsForMaterial(CreateToolMaterials.ANDESITE_ALLOY, EnumSet.allOf(ToolType.class), true, true, true);
		makeToolPartsForMaterial(CreateToolMaterials.BRASS, EnumSet.allOf(ToolType.class), true, true, true);
	}

	private static void makeToolSetsForMaterial(AlloygeryToolMaterial material, ItemGroup group)
	{
		makeToolSetForMaterial(material, ToolUpgradeType.NONE, EnumSet.allOf(ToolType.class), group);
		makeToolSetForMaterial(material, ToolUpgradeType.EMBOSSED, EnumSet.allOf(ToolType.class), group);
		makeToolSetForMaterial(material, ToolUpgradeType.PLATED, EnumSet.allOf(ToolType.class), group);
		makeToolSetForMaterial(material, ToolUpgradeType.TIPPED, EnumSet.allOf(ToolType.class), group);
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
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, ToolItemRegistry.getDefaultMaterialForUpgrade(upgradeType), type)
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
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, ToolItemRegistry.getDefaultMaterialForUpgrade(upgradeType), type)
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
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, ToolItemRegistry.getDefaultMaterialForUpgrade(upgradeType), type)
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
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, ToolItemRegistry.getDefaultMaterialForUpgrade(upgradeType), type)
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
									ToolNBTHelper.createToolNBTFromMaterials(material, VANILLA_STICK, ToolItemRegistry.getDefaultMaterialForUpgrade(upgradeType), type)
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

	private static Item registerGeneratedItem(String path, Item item, Supplier<String> jsonModelSupplier)
	{
		Identifier identifier = CreateModule.identify("item/" + path);
		ToolModelBuilder.register(identifier, jsonModelSupplier);
		return register(path, item);
	}

	private static Item register(String path, Item item)
	{
		ITEMS.put(path, item);
		return Registry.register(Registry.ITEM, CreateModule.identify(path), item);
	}
}
