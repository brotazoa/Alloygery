package amorphia.alloygery.mixins;

import amorphia.alloygery.Alloygery;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin
{
	@Shadow
	private NbtCompound nbt;

	@Shadow
	public abstract Item getItem();

	@Shadow
	@Nullable
	public abstract NbtCompound getSubNbt(String key);

	@Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
	public void getMaxDamage(CallbackInfoReturnable<Integer> cir)
	{
		if (nbt != null && nbt.contains(Alloygery.NBT_KEY))
		{
			int maxDamage = getItem().getMaxDamage();

			NbtCompound tag = getSubNbt(Alloygery.NBT_KEY);
			if (tag != null)
			{
				if (tag.contains(Alloygery.DIAMOND_TIPPED_KEY))
				{
					maxDamage += 500;
				}
				if (tag.contains(Alloygery.EMERALD_EMBOSSED_KEY))
				{
					maxDamage += (int) (getItem().getMaxDamage() * 0.5f);
				}
				if (tag.contains(Alloygery.NETHERITE_PLATTED))
				{
					maxDamage += (int) (getItem().getMaxDamage() * 0.2f);
				}
			}

			cir.setReturnValue(maxDamage);
			cir.cancel();
		}
	}

	@Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
	public void getMiningSpeedMultiplier(BlockState state, CallbackInfoReturnable<Float> cir)
	{
		float mineSpeed = 1f;
		if (getItem() instanceof MiningToolItem && ((MiningToolItemAccessor) getItem()).getEffectiveBlocks().contains(state.getBlock()))
		{
			mineSpeed = ((MiningToolItemAccessor) getItem()).getMiningSpeed();

			if (nbt != null && nbt.contains(Alloygery.NBT_KEY))
			{
				NbtCompound tag = getSubNbt(Alloygery.NBT_KEY);
				if(tag != null)
				{
					if (tag.contains(Alloygery.NETHERITE_PLATTED))
					{
						mineSpeed += ((MiningToolItemAccessor) getItem()).getMiningSpeed() * 0.1f;
					}
				}
			}
			cir.setReturnValue(mineSpeed);
			cir.cancel();
		}
	}

	@Inject(method = "isSuitableFor", at = @At("HEAD"), cancellable = true)
	public void isSuitableFor(BlockState state, CallbackInfoReturnable<Boolean> cir)
	{
		if(nbt != null && nbt.contains(Alloygery.NBT_KEY) && getItem() instanceof MiningToolItem tool)
		{
			int level = tool.getMaterial().getMiningLevel();
			NbtCompound tag = getSubNbt(Alloygery.NBT_KEY);
			if (tag != null)
			{
				if (tag.contains(Alloygery.DIAMOND_TIPPED_KEY))
				{
					level += 1;
				}
			}
			cir.setReturnValue(level >= MiningLevelManager.getRequiredMiningLevel(state));
			cir.cancel();
		}
	}
}
