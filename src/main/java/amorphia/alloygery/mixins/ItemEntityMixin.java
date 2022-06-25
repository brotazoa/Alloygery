package amorphia.alloygery.mixins;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.item.tool.IAlloygeryTool;
import amorphia.alloygery.content.material.AlloygeryArmorMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity
{
	ItemEntityMixin(EntityType<?> type, World world)
	{
		super(type, world);
	}

	@Shadow
	public abstract ItemStack getStack();

	@Shadow public abstract Text getName();

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

//	@Inject(method = "tick", at = @At("TAIL"))
//	private void fireMending(CallbackInfo info)
//	{
//		if (getStack().getItem() instanceof IAlloygeryTool && AlloygeryToolMaterialHelper.getHeadMaterial(getStack().getNbt()) == AlloygeryMaterials.NITINOL && getStack().isDamaged())
//		{
//			Block in = this.world.getBlockState(new BlockPos(this.getX(), this.getY(), this.getZ())).getBlock();
//			if (in == Blocks.FIRE || in == Blocks.SOUL_FIRE || in == Blocks.LAVA || in == Blocks.LAVA_CAULDRON || in == Blocks.CAMPFIRE || in == Blocks.SOUL_CAMPFIRE)
//			{
//				getStack().damage((int) -1, this.world.getRandom(), null);
//
//				if (this.world.isClient)
//				{
//					this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)this.getX() + 0.5D, (double)this.getY() + 0.5D, (double)this.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
//				}
//
//				Alloygery.LOGGER.info("repaired");
//			}
//		}
//	}
}
