package amorphia.alloygery.registry;

import amorphia.alloygery.content.block.AlloygeryTintedBlock;
import amorphia.alloygery.content.item.*;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.ItemStack;

public class ModColorProviders
{
	public static void registerClient()
	{
		AlloygeryPartItem.PART_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {

				return tintIndex == 0 ? AlloygeryToolMaterialHelper.getMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL, true).color : -1;

				//return tintIndex == 0 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});

		AlloygeryCraftingItem.CRAFTING_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? AlloygeryToolMaterialHelper.getMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL, true).color : -1;
			}, item);
		});

		AlloygeryTintedBlock.TINTED_BLOCKS.forEach(block -> {
			ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
				return tintIndex == 1 ? block.getAlloygeryMaterial().color : -1;
			}, block);
		});

		AlloygeryTintedBlockItem.TINTED_BLOCK_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 1 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});

		IAlloygeryTool.ITEMS.forEach(item -> ColorProviderRegistry.ITEM.register(ModColorProviders::getMaterialColorFromToolStack, item));

//		AlloygeryAxeItem.AXE_ITEMS.forEach(item -> {
//			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
//				return getMaterialColorFromToolStack(stack, tintIndex);
//			}, item);
//		});
//
//		AlloygeryHoeItem.HOE_ITEMS.forEach(item -> {
//			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
//				return getMaterialColorFromToolStack(stack, tintIndex);
//			}, item);
//		});
//
//		AlloygeryPickaxeItem.PICKAXE_ITEMS.forEach(item -> {
//			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
//				return getMaterialColorFromToolStack(stack, tintIndex);
//			}, item);
//		});
//
//		AlloygeryShovelItem.SHOVEL_ITEMS.forEach(item -> {
//			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
//				return getMaterialColorFromToolStack(stack, tintIndex);
//			}, item);
//		});
//
//		AlloygerySwordItem.SWORD_ITEMS.forEach(item -> {
//			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
//				return getMaterialColorFromToolStack(stack, tintIndex);
//			}, item);
//		});
//
		AlloygeryArmorItem.ARMOR_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});
	}

	static int getMaterialColorFromToolStack(ItemStack stack, int tintIndex)
	{
		return switch (tintIndex)
		{
			case 0 -> AlloygeryToolMaterialHelper.getHandleMaterial(stack).color;
			case 1 -> AlloygeryToolMaterialHelper.getHeadMaterial(stack).color;
			case 2 -> AlloygeryToolMaterialHelper.getBindingMaterial(stack).color;
			default -> -1;
		};
	}
}
