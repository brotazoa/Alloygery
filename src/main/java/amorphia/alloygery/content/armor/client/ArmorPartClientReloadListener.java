package amorphia.alloygery.content.armor.client;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.item.ArmorPartItem;
import amorphia.alloygery.content.armor.item.IArmorPart;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class ArmorPartClientReloadListener implements SimpleSynchronousResourceReloadListener
{
	public static final ArmorPartClientReloadListener INSTANCE = new ArmorPartClientReloadListener();
	public static final Identifier ID = Alloygery.identifier("armor_part_reload_listener");

	private ArmorPartClientReloadListener() {} //no op

	@Override
	public Identifier getFabricId()
	{
		return ID;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		IArmorPart.ARMOR_PART_ITEMS.forEach(item -> {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 1 ? getMaterialColorFromPartStack(stack, tintIndex) : -1;
			}, item);
		});
	}

	private static int getMaterialColorFromPartStack(ItemStack partStack, int tintIndex)
	{
		if(partStack == null || partStack.isEmpty())
			return -1;

		if (partStack.getItem() instanceof ArmorPartItem partItem)
		{
			return partItem instanceof DyeableItem dyeablePart ? dyeablePart.getColor(partStack) : partItem.getArmorMaterial().getMaterialColor();
		}

		return -1;
	}
}
