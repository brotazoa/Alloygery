package amorphia.alloygery.content.tools.mixin;

import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Random;

@Mixin(EnchantmentHelper.class)
public class ToolEnchantabilityMixin
{
	@Inject(method = "generateEnchantments", at = @At("HEAD"))
	private static void alloygery_tools_generateEnchantmentsInject(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir)
	{
		if(stack.getItem() instanceof IDynamicTool dynamicTool)
			dynamicTool.calculateEnchantability(stack);
	}

	@Inject(method = "calculateRequiredExperienceLevel", at = @At("HEAD"))
	private static void alloygery_tools_calculateLevelInject(Random random, int slotIndex, int bookshelfCount, ItemStack stack, CallbackInfoReturnable<Integer> cir)
	{
		if(stack.getItem() instanceof IDynamicTool dynamicTool)
			dynamicTool.calculateEnchantability(stack);
	}

}
