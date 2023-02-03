package amorphia.alloygery.content.armor.mixin;

import amorphia.alloygery.content.armor.item.IDynamicArmor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class ArmorEnchantablilityMixin
{
	@Inject(method = "generateEnchantments", at = @At("HEAD"))
	private static void alloygery_armor_generateEnchantmentsInject(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir)
	{
		if(stack.getItem() instanceof IDynamicArmor dynamicArmor)
			dynamicArmor.calculateEnchantability(stack);
	}

	@Inject(method = "calculateRequiredExperienceLevel", at = @At("HEAD"))
	private static void alloygery_armor_calculateLevelInject(Random random, int slotIndex, int bookshelfCount, ItemStack stack, CallbackInfoReturnable<Integer> cir)
	{
		if(stack.getItem() instanceof IDynamicArmor dynamicArmor)
			dynamicArmor.calculateEnchantability(stack);
	}
}
