package amorphia.alloygery.content.armor.mixin;

import amorphia.alloygery.content.armor.ArmorPropertyHelper;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public class ArmorPiglinLovedMixin
{
	@Inject(method = "isGoldenItem", at = @At("HEAD"), cancellable = true)
	private static void isArmorGolden(ItemStack armor, CallbackInfoReturnable<Boolean> cir)
	{
		if(armor.getItem() instanceof IDynamicArmor dynamicArmor && dynamicArmor.isPiglinLoved(armor))
		{
			cir.setReturnValue(true);
			cir.cancel();
		}
	}

	@Inject(method = "wearsGoldArmor", at = @At("HEAD"), cancellable = true)
	private static void isWearingGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir)
	{
		for (ItemStack stack : entity.getArmorItems())
		{
			if (stack.getItem() instanceof IDynamicArmor && ArmorPropertyHelper.isPiglinLoved(stack))
			{
				cir.setReturnValue(true);
				cir.cancel();
				return;
			}
		}
	}

}
