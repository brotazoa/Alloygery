package amorphia.alloygery.content.armor.client;

import amorphia.alloygery.content.armor.ArmorMaterialHelper;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.ArmorType;
import amorphia.alloygery.content.armor.item.DynamicArmorItem;
import com.github.clevernucleus.armorrenderlib.api.ArmorRenderLib;
import com.github.clevernucleus.armorrenderlib.api.ArmorRenderProvider;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArmorRenderLibRenderers
{
	private static ArmorRenderProvider renderBaseLayer(ItemStack armorStack, LivingEntity entity, EquipmentSlot slot)
	{
		ArmorType type = getArmorTypeFromEquipmentSlot(slot);
		String texture = ArmorMaterialHelper.getMaterialForLayer(armorStack, ArmorLayer.BASE).getModelTexture(type).toString();
		int color = ArmorMaterialHelper.getLayerColor(armorStack, ArmorLayer.BASE);

		return armorRenderData -> armorRenderData.accept(texture, color, armorStack.hasGlint());
	}

	private static ArmorRenderProvider renderSecondLayer(ItemStack armorStack, LivingEntity entity, EquipmentSlot slot)
	{
		ArmorType type = getArmorTypeFromEquipmentSlot(slot);
		String texture = ArmorMaterialHelper.getMaterialForLayer(armorStack, ArmorLayer.PLATE).getModelTexture(type).toString();
		int color = ArmorMaterialHelper.getLayerColor(armorStack, ArmorLayer.PLATE);

		return armorRenderData -> armorRenderData.accept(texture, color, armorStack.hasGlint());
	}

	private static ArmorRenderProvider renderUpgradeLayer(ItemStack armorStack, LivingEntity entity, EquipmentSlot slot)
	{
		ArmorType type = getArmorTypeFromEquipmentSlot(slot);
		String texture = ArmorMaterialHelper.getMaterialForLayer(armorStack, ArmorLayer.UPGRADE).getModelTexture(type).toString();
		int color = ArmorMaterialHelper.getLayerColor(armorStack, ArmorLayer.UPGRADE);

		return armorRenderData -> armorRenderData.accept(texture, color, armorStack.hasGlint());
	}

	private static void registerLayerRenderers(Item item)
	{
		ArmorRenderLib.register(ArmorRenderLibRenderers::renderBaseLayer, item);
		ArmorRenderLib.register(ArmorRenderLibRenderers::renderSecondLayer, item);
		ArmorRenderLib.register(ArmorRenderLibRenderers::renderUpgradeLayer, item);
	}

	public static void initClient()
	{
		DynamicArmorItem.ARMOR_ITEMS.forEach(ArmorRenderLibRenderers::registerLayerRenderers);
	}

	private static ArmorType getArmorTypeFromEquipmentSlot(EquipmentSlot slot)
	{
		return switch (slot)
		{
			case HEAD -> ArmorType.HELMET;
			case CHEST -> ArmorType.CHESTPLATE;
			case LEGS -> ArmorType.LEGGINGS;
			case FEET -> ArmorType.BOOTS;
			default -> throw new EnumConstantNotPresentException(ArmorType.class, slot.getName());
		};
	}
}
