package amorphia.alloygery.content.client;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.block.AlloygeryTintedBlock;
import amorphia.alloygery.content.item.AlloygeryArmorItem;
import amorphia.alloygery.content.item.AlloygeryCraftingItem;
import amorphia.alloygery.content.item.AlloygeryTintedBlockItem;
import amorphia.alloygery.content.item.part.AlloygeryPartItem;
import amorphia.alloygery.content.item.tool.IAlloygeryTool;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class AlloygeryColorProviderReloadListener implements SimpleSynchronousResourceReloadListener
{
	public static final AlloygeryColorProviderReloadListener INSTANCE = new AlloygeryColorProviderReloadListener();

	public static final Identifier ALLOYGERY_COLOR_PROVIDER_RELOAD_LISTENER = Alloygery.identifier("color_provider_reload_listener");

	private AlloygeryColorProviderReloadListener() {}

	@Override
	public Identifier getFabricId()
	{
		return ALLOYGERY_COLOR_PROVIDER_RELOAD_LISTENER;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		AlloygeryPartItem.PART_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? getMaterialColorFromPartStack(stack) : -1;
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

		IAlloygeryTool.TOOL_ITEMS.forEach(item -> ColorProviderRegistry.ITEM.register(AlloygeryColorProviderReloadListener::getMaterialColorFromToolStack, item));

		AlloygeryArmorItem.ARMOR_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? item.getAlloygeryMaterial().color : -1;
			}, item);
		});
	}

	static int getMaterialColorFromToolStack(ItemStack stack, int tintIndex)
	{
		if(AlloygeryToolMaterialHelper.isInfo(stack))
			return AlloygeryMaterials.INFO.color;

		return switch (tintIndex)
		{
			case 0 -> AlloygeryToolMaterialHelper.getHandleMaterial(stack).color;
			case 1 -> AlloygeryToolMaterialHelper.getHeadMaterial(stack).color;
			case 2 -> AlloygeryToolMaterialHelper.getBindingMaterial(stack) == AlloygeryMaterials.HIDDEN ? AlloygeryToolMaterialHelper.getHeadMaterial(stack).color : AlloygeryToolMaterialHelper.getBindingMaterial(stack).color;
			case 3 -> AlloygeryToolMaterialHelper.getUpgradeMaterial(stack).color;
			default -> -1;
		};
	}

	static int getMaterialColorFromPartStack(ItemStack stack)
	{
		boolean hasMaterialKey = AlloygeryToolMaterialHelper.hasMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL);
		AlloygeryMaterial material = AlloygeryToolMaterialHelper.getMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL);

		if(!hasMaterialKey && material == AlloygeryMaterials.UNKNOWN)
			return AlloygeryMaterials.INFO.color;

		return material.color;
	}
}
