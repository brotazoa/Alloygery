package amorphia.alloygery.content.armor.client;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.ArmorMaterialHelper;
import amorphia.alloygery.content.armor.ArmorNBTHelper;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.IDyeableItemWithDefaultColor;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class ArmorClientReloadListener implements SimpleSynchronousResourceReloadListener
{
	public static final ArmorClientReloadListener INSTANCE = new ArmorClientReloadListener();
	public static final Identifier ID = Alloygery.identifier("armor_client_reload_listener");

	private ArmorClientReloadListener() {} //no op

	@Override
	public Identifier getFabricId()
	{
		return ArmorClientReloadListener.ID;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		IDynamicArmor.ARMOR_ITEMS.forEach(item -> ColorProviderRegistry.ITEM.register(ArmorClientReloadListener::getMaterialColorFromArmorStack, item));
	}

	private static int getMaterialColorFromArmorStack(ItemStack armorStack, int tintIndex)
	{
		if(armorStack == null || armorStack.isEmpty())
			return -1;

		if (ArmorNBTHelper.isAlloygeryDataNBT(armorStack.getNbt()))
		{
			return switch (tintIndex)
			{
				case 1 -> ArmorMaterialHelper.getLayerColor(armorStack, ArmorLayer.BASE);
				case 2 -> ArmorMaterialHelper.getLayerColor(armorStack, ArmorLayer.PLATE);
				case 3 -> ArmorMaterialHelper.getLayerColor(armorStack, ArmorLayer.UPGRADE);
				default -> -1;
			};
		}
		else
		{
			return switch (tintIndex)
			{
				case 1 -> armorStack.getItem() instanceof IDynamicArmor dynamicArmor ?
						dynamicArmor instanceof IDyeableItemWithDefaultColor dyeableArmor ?
								dyeableArmor.getColor(armorStack) :
								dynamicArmor.getDefaultBaseMaterial().getMaterialColor() :
						AlloygeryArmorMaterials.HIDDEN.getMaterialColor();

				case 2 -> armorStack.getItem() instanceof IDynamicArmor dynamicArmor ? dynamicArmor.getDefaultPlateMaterial().getMaterialColor() : AlloygeryArmorMaterials.HIDDEN.getMaterialColor();
				case 3 -> armorStack.getItem() instanceof IDynamicArmor dynamicArmor ? dynamicArmor.getDefaultUpgradeMaterial().getMaterialColor() : AlloygeryArmorMaterials.HIDDEN.getMaterialColor();
				default -> -1;
			};
		}
	}
}
