package amorphia.alloygery.mixins;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin
{
	@Shadow
	@Final
	private static UUID[] MODIFIERS;

	@Shadow
	@Final
	private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

	@Shadow @Final protected EquipmentSlot slot;

	@Shadow public abstract ArmorMaterial getMaterial();

	@Inject(method = "getAttributeModifiers", at = @At("HEAD"), cancellable = true)
	private void getAttributeModifiers(EquipmentSlot slot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> cir)
	{
		if(slot == this.slot)
		{
			ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
			builder.putAll(attributeModifiers);
			builder.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
					new EntityAttributeModifier(MODIFIERS[slot.getEntitySlotId()], "Armor knockback resistance",
							getMaterial().getKnockbackResistance(), EntityAttributeModifier.Operation.ADDITION));
			cir.setReturnValue(builder.build());
			cir.cancel();
		}
	}
}
