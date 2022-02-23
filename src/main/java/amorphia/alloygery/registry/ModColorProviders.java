package amorphia.alloygery.registry;

import amorphia.alloygery.content.block.AlloygeryTintedBlock;
import amorphia.alloygery.content.item.*;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ModColorProviders
{
	public static void registerClient()
	{
		AlloygeryPartItem.PART_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});

		AlloygeryCraftingItem.CRAFTING_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? item.getAlloygeryMaterial().color : -1;
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

		AlloygeryAxeItem.AXE_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 1 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});

		AlloygeryHoeItem.HOE_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 1 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});

		AlloygeryPickaxeItem.PICKAXE_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 1 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});

		AlloygeryShovelItem.SHOVEL_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 1 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});

		AlloygerySwordItem.SWORD_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 1 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});

		AlloygeryArmorItem.ARMOR_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});
	}
}
