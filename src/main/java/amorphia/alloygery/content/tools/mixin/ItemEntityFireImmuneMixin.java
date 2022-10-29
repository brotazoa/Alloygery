package amorphia.alloygery.content.tools.mixin;

import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public abstract class ItemEntityFireImmuneMixin extends Entity
{
	@Shadow public abstract ItemStack getStack();

	ItemEntityFireImmuneMixin(EntityType<?> type, World world)
	{
		super(type, world);
	}

	@Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
	public void isFireImmune(CallbackInfoReturnable<Boolean> cir)
	{
		if(getStack().getItem() instanceof IDynamicTool dynamicTool && dynamicTool.isFireproof(getStack()))
		{
			cir.setReturnValue(true);
			cir.cancel();
		}
	}
}
