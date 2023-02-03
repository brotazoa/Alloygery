package amorphia.alloygery.content.tools.mixin;

import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ToolItemStackMixin
{
	@Shadow
	public abstract Item getItem();

	@Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
	public void tool_getMaxDamage(CallbackInfoReturnable<Integer> cir)
	{
		if(getItem() instanceof IDynamicTool dynamicTool)
		{
			cir.setReturnValue(dynamicTool.getMaxDurability(((ItemStack) (Object) this)));
			cir.cancel();
		}
	}

	@Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
	public void tool_getMiningSpeedMultiplier(BlockState blockState, CallbackInfoReturnable<Float> cir)
	{
		if (getItem() instanceof IDynamicTool dynamicTool)
		{
			cir.setReturnValue(dynamicTool.getMiningSpeedMultiplier(blockState, ((ItemStack) (Object) this)));
			cir.cancel();
		}
	}

	@Inject(method = "isSuitableFor", at = @At("HEAD"), cancellable = true)
	public void tool_isSuitableFor(BlockState blockState, CallbackInfoReturnable<Boolean> cir)
	{
		if (getItem() instanceof IDynamicTool dynamicTool && getItem() instanceof MiningToolItem)
		{
			cir.setReturnValue(dynamicTool.isSuitableFor(blockState, ((ItemStack) (Object) this)));
			cir.cancel();
		}
	}

	@ModifyVariable(method = "getAttributeModifiers", at = @At(value = "RETURN", shift = At.Shift.BEFORE))
	public Multimap<EntityAttribute, EntityAttributeModifier> tool_modifyAttributeModifiersMap(Multimap<EntityAttribute, EntityAttributeModifier> multimap, EquipmentSlot slot)
	{
		if (getItem() instanceof IDynamicTool dynamicTool)
		{
			Multimap<EntityAttribute, EntityAttributeModifier> dynamicAttributes = dynamicTool.getDynamicAttributeModifiers(slot,((ItemStack) (Object) this));

			LinkedListMultimap<EntityAttribute, EntityAttributeModifier> attributes = LinkedListMultimap.create();
			attributes.putAll(dynamicAttributes);
			attributes.putAll(multimap);

			return attributes;
		}

		return multimap;
	}
}
