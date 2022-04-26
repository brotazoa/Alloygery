package amorphia.alloygery.mixins;

import amorphia.alloygery.content.material.AlloygeryArmorMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(PiglinBrain.class)
public class PiglinBrainMixin
{
	@Inject(method = "isGoldenItem", at = @At("HEAD"), cancellable = true)
	private static void isGoldenItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
	{
		boolean piglinLoved = AlloygeryToolMaterialHelper.isPiglinLoved(stack.getNbt());
		if (piglinLoved)
		{
			cir.setReturnValue(true);
			cir.cancel();
		}
	}

	@Inject(method = "wearsGoldArmor", at = @At("HEAD"), cancellable = true)
	private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir)
	{
		Iterator<ItemStack> armorStacks = entity.getArmorItems().iterator();
		boolean wearingGold = false;
		while (armorStacks.hasNext())
		{
			ItemStack stack = armorStacks.next();
			if(stack.getItem() instanceof ArmorItem && AlloygeryArmorMaterialHelper.isPiglinLoved(stack.getNbt()))
			{
				wearingGold = true;
				break;
			}
		}
		if(wearingGold)
		{
			cir.setReturnValue(true);
			cir.cancel();
		}
	}
}
