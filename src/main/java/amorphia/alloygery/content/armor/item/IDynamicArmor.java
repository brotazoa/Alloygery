package amorphia.alloygery.content.armor.item;

import amorphia.alloygery.content.armor.ArmorPropertyHelper;
import amorphia.alloygery.content.armor.attribute.ArmorAttributes;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.UUID;

public interface IDynamicArmor
{
	List<Item> ARMOR_ITEMS = Lists.newArrayList();

	Multimap<EntityAttribute, EntityAttributeModifier> EMPTY = ImmutableMultimap.of();

	default Multimap<EntityAttribute, EntityAttributeModifier> getDynamicAttributeModifiers(EquipmentSlot slot, ItemStack armorStack)
	{
		if(!armorStack.isEmpty() && armorStack.getItem() instanceof ArmorItem armorItem && armorItem.getSlotType().equals(slot))
		{
			ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
			UUID modifierUUID = ArmorAttributes.ARMOR_SLOT_MODIFIERS[slot.getEntitySlotId()];

			builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(modifierUUID, "Armor Modifier", getProtectionAmount(armorStack), EntityAttributeModifier.Operation.ADDITION));
			builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(modifierUUID, "Armor Modifier", getToughnessAmount(armorStack), EntityAttributeModifier.Operation.ADDITION));
			builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(modifierUUID, "Armor Modifier", getKnockbackResistance(armorStack), EntityAttributeModifier.Operation.ADDITION));
			builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(modifierUUID, "Armor Modifier", getMovementSpeedModifier(armorStack), EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

			return builder.build();
		}
		else
		{
			return EMPTY;
		}
	}

	default int getMaxDurability(ItemStack armor)
	{
		return ArmorPropertyHelper.getMaxDurability(armor);
	}

	default int getEnchantability(ItemStack armor)
	{
		return ArmorPropertyHelper.getEnchantability(armor);
	}

	default int getProtectionAmount(ItemStack armor)
	{
		return ArmorPropertyHelper.getProtectionAmount(armor);
	}

	default float getToughnessAmount(ItemStack armor)
	{
		return ArmorPropertyHelper.getToughnessAmount(armor);
	}

	default float getKnockbackResistance(ItemStack armor)
	{
		return ArmorPropertyHelper.getKnockbackResistance(armor);
	}

	default float getMovementSpeedModifier(ItemStack armor)
	{
		return ArmorPropertyHelper.getMovementSpeedMultiplier(armor);
	}

	default boolean isFireproof(ItemStack armor)
	{
		return ArmorPropertyHelper.isFireproof(armor);
	}

	default boolean isPiglinLoved(ItemStack armor)
	{
		return ArmorPropertyHelper.isPiglinLoved(armor);
	}

	default AlloygeryArmorMaterial getDefaultBaseMaterial()
	{
		return AlloygeryArmorMaterials.HIDDEN;
	}

	default AlloygeryArmorMaterial getDefaultPlateMaterial()
	{
		return AlloygeryArmorMaterials.HIDDEN;
	}

	default AlloygeryArmorMaterial getDefaultUpgradeMaterial()
	{
		return AlloygeryArmorMaterials.HIDDEN;
	}

	ArmorType getArmorType();

	void calculateEnchantability(ItemStack stack);

	static int getItemBarStep(ItemStack armor)
	{
		return Math.round(13.0f - (float) armor.getDamage() * 13.0f / (float) ArmorPropertyHelper.getMaxDurability(armor));
	}

	static int getItemBarColor(ItemStack armor)
	{
		final float maxDamage = (float) ArmorPropertyHelper.getMaxDurability(armor);
		final float f = Math.max(0.0f, (maxDamage - armor.getDamage()) / maxDamage);
		return MathHelper.hsvToRgb(f / 3.0f, 1.0f, 1.0f);
	}
}
