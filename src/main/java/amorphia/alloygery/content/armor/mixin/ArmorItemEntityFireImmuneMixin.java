package amorphia.alloygery.content.armor.mixin;

import amorphia.alloygery.content.armor.item.IDynamicArmor;
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
public abstract class ArmorItemEntityFireImmuneMixin extends Entity
{
	public ArmorItemEntityFireImmuneMixin(EntityType<?> type, World world)
	{
		super(type, world);
	}

	@Shadow public abstract ItemStack getStack();

	@Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
	public void armor_isFireImmune(CallbackInfoReturnable<Boolean> cir)
	{
		if(getStack().getItem() instanceof IDynamicArmor dynamicArmor && dynamicArmor.isFireproof(getStack()))
		{
			cir.setReturnValue(true);
			cir.cancel();
		}
	}
}
