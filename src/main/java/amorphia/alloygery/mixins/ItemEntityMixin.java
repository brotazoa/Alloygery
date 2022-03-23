package amorphia.alloygery.mixins;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
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
		AlloygeryMaterial upgradeMaterial = AlloygeryMaterialHelper.getUpgradeMaterial(getStack());
		if(upgradeMaterial == AlloygeryMaterials.NETHERITE_UPGRADE)
		{
			cir.setReturnValue(true);
			cir.cancel();
		}
	}
}
