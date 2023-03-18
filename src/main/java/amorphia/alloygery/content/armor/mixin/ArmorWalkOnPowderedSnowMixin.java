package amorphia.alloygery.content.armor.mixin;

import amorphia.alloygery.content.armor.registry.ArmorTagRegistry;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class ArmorWalkOnPowderedSnowMixin
{
	@Inject(method = "canWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
	private static void alloygery_armor_canWalkOnPowderedSnowInject(Entity entity, CallbackInfoReturnable<Boolean> cir)
	{
		cir.setReturnValue(entity instanceof LivingEntity livingEntity && livingEntity.getEquippedStack(EquipmentSlot.FEET).isIn(ArmorTagRegistry.POWDER_WALK_BOOTS));
		if(cir.getReturnValue())
			cir.cancel();
	}
}
