package amorphia.alloygery.mixins;

import amorphia.alloygery.content.item.tool.IAlloygeryMeleeWeapon;
import amorphia.alloygery.content.item.tool.IAlloygeryTool;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import amorphia.alloygery.registry.ModAttributes;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.tag.TagKey;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

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

	@Shadow public abstract Stream<TagKey<Item>> streamTags();

	@Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
	public void getMaxDamage(CallbackInfoReturnable<Integer> cir)
	{
		if (getItem() instanceof IAlloygeryTool)
		{
			cir.setReturnValue(AlloygeryToolMaterialHelper.getMaxDurability(nbt));
			cir.cancel();
		}
	}

	@Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
	public void getMiningSpeedMultiplier(BlockState state, CallbackInfoReturnable<Float> cir)
	{
		if (getItem() instanceof IAlloygeryTool)
		{
			if(getItem() instanceof MiningToolItem && state.isIn(((MiningToolItemAccessor) getItem()).getEffectiveBlocks()))
			{
				cir.setReturnValue(AlloygeryToolMaterialHelper.getMiningSpeed(nbt));
				cir.cancel();
			}
		}
	}

	@Inject(method = "isSuitableFor", at = @At("HEAD"), cancellable = true)
	public void isSuitableFor(BlockState state, CallbackInfoReturnable<Boolean> cir)
	{
		if (getItem() instanceof IAlloygeryTool && getItem() instanceof MiningToolItem)
		{
			final int level = AlloygeryToolMaterialHelper.getMiningLevel(nbt);
			cir.setReturnValue(level >= MiningLevelManager.getRequiredMiningLevel(state));
			cir.cancel();
		}
	}

	@Inject(method = "getAttributeModifiers", at = @At("HEAD"), cancellable = true)
	public void getAttributeModifiers(EquipmentSlot slot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> cir)
	{
		if(getItem() instanceof IAlloygeryTool alloygeryTool && slot == EquipmentSlot.MAINHAND && nbt != null)
		{
			final float damage = Math.max(0.0f, AlloygeryToolMaterialHelper.getAttackDamage(nbt) + alloygeryTool.getAttackDamageModifier());
			final float speed = Math.max(-3.9f, AlloygeryToolMaterialHelper.getAttackSpeed(nbt) + alloygeryTool.getAttackSpeedModifier());
			final float luck = AlloygeryToolMaterialHelper.getLuck(nbt);

			ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
			builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(((ItemAccessor) getItem()).getAttackDamageModifierId(),
					getItem() instanceof IAlloygeryMeleeWeapon ? "Weapon Modifier" : "Tool Modifier", (double) damage,
					EntityAttributeModifier.Operation.ADDITION));
			builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(((ItemAccessor) getItem()).getAttackSpeedModifierId(),
					getItem() instanceof IAlloygeryMeleeWeapon ? "Weapon Modifier" : "Tool Modifier", (double) speed,
					EntityAttributeModifier.Operation.ADDITION));
			builder.put(EntityAttributes.GENERIC_LUCK, new EntityAttributeModifier(ModAttributes.ALLOYGERY_LUCK_MODIFIER,
					getItem() instanceof IAlloygeryMeleeWeapon ? "Weapon Modifier" : "Tool Modifier", (double) luck,
					EntityAttributeModifier.Operation.ADDITION));

			cir.setReturnValue(builder.build());
			cir.cancel();
		}
	}
}
