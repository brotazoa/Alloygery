package amorphia.alloygery.content.metals.mixin;

import amorphia.alloygery.content.metals.item.CraftingTool;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class CraftingToolItemStackMixin
{
	@Shadow public abstract Item getItem();

	@Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
	public void getMaxDamage(CallbackInfoReturnable<Integer> cir)
	{
		if (getItem() instanceof CraftingTool)
		{
			cir.setReturnValue(CraftingTool.getMaxDurability(((ItemStack) (Object) this)));
			cir.cancel();
		}
	}
}
