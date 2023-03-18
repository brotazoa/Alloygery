package amorphia.alloygery.content.tools.mixin;

import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrain.class)
public class ToolPiglinLovedMixin
{
	@Inject(method = "isGoldenItem", at = @At("HEAD"), cancellable = true)
	private static void isToolGolden(ItemStack tool, CallbackInfoReturnable<Boolean> cir)
	{
		if(tool.getItem() instanceof IDynamicTool dynamicTool && dynamicTool.isPiglinLoved(tool))
		{
			cir.setReturnValue(true);
			cir.cancel();
		}
	}
}
