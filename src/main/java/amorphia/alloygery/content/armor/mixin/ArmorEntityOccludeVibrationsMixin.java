package amorphia.alloygery.content.armor.mixin;

import amorphia.alloygery.content.armor.registry.ArmorTagRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class ArmorEntityOccludeVibrationsMixin
{
	@Inject(method = "occludeVibrationSignals", at = @At("HEAD"), cancellable = true)
	public void alloygery_armor_occludeVibrations(CallbackInfoReturnable<Boolean> cir)
	{
		Entity entity = (Entity) (Object) this;
		if (entity instanceof PlayerEntity player)
		{
			ItemStack feetStack = player.getEquippedStack(EquipmentSlot.FEET);
			if (feetStack != null && feetStack.isIn(ArmorTagRegistry.OCCLUDES_VIBRATION_SIGNALS_WHEN_WORN))
			{
				cir.setReturnValue(true);
			}
		}
	}
}
