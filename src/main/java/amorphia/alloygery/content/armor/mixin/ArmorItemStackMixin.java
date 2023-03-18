package amorphia.alloygery.content.armor.mixin;

import amorphia.alloygery.content.armor.item.IDynamicArmor;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ArmorItemStackMixin
{
	@Shadow public abstract Item getItem();

	@Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
	public void armor_getMaxDamage(CallbackInfoReturnable<Integer> cir)
	{
		if (getItem() instanceof IDynamicArmor dynamicArmor)
		{
			cir.setReturnValue(dynamicArmor.getMaxDurability(((ItemStack) (Object) this)));
			cir.cancel();
		}
	}

	@ModifyVariable(method = "getAttributeModifiers", at = @At(value = "RETURN", shift = At.Shift.BEFORE))
	public Multimap<EntityAttribute, EntityAttributeModifier> armor_modifyAttributeModifiersMap(Multimap<EntityAttribute, EntityAttributeModifier> multimap, EquipmentSlot slot)
	{
		if (getItem() instanceof IDynamicArmor dynamicArmor)
		{
			Multimap<EntityAttribute, EntityAttributeModifier> dynamicAttributes = dynamicArmor.getDynamicAttributeModifiers(slot, ((ItemStack) (Object) this));

			LinkedListMultimap<EntityAttribute, EntityAttributeModifier> attributes = LinkedListMultimap.create();
			attributes.putAll(dynamicAttributes);
			attributes.putAll(multimap);

			return attributes;
		}

		return multimap;
	}
}
