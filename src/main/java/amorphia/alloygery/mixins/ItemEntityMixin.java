package amorphia.alloygery.mixins;

import amorphia.alloygery.content.item.tool.IAlloygeryTool;
import amorphia.alloygery.content.material.AlloygeryArmorMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin
{
	@Shadow
	public abstract ItemStack getStack();

	@Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
	public void isFireImmune(CallbackInfoReturnable<Boolean> cir)
	{
		if(getStack().getItem() instanceof IAlloygeryTool && AlloygeryToolMaterialHelper.isFireproof(getStack().getNbt()))
		{
			cir.setReturnValue(true);
			cir.cancel();
		}

		if(getStack().getItem() instanceof ArmorItem && AlloygeryArmorMaterialHelper.isFireproof(getStack().getNbt()))
		{
			cir.setReturnValue(true);
			cir.cancel();
		}
	}
}
