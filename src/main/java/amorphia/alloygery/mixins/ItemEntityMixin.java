package amorphia.alloygery.mixins;

import amorphia.alloygery.Alloygery;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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
		NbtCompound nbt = getStack().getNbt();
		if (nbt != null && nbt.contains(Alloygery.NBT_KEY))
		{
			NbtCompound tag = nbt.getCompound(Alloygery.NBT_KEY);
			if (tag != null && tag.contains(Alloygery.NETHERITE_PLATTED))
			{
				cir.setReturnValue(true);
				cir.cancel();
			}
		}
	}
}
