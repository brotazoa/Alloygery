package amorphia.alloygery.mixins;

import amorphia.alloygery.content.item.tool.IAlloygeryTool;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin
{
	@Inject(method = "generateEnchantments", at = @At("HEAD"))
	private static void generateEnchantments(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir)
	{
		if(stack.getItem() instanceof IAlloygeryTool alloygeryTool)
		{
			alloygeryTool.setCalculatedEnchantability(AlloygeryToolMaterialHelper.getEnchantability(stack.getNbt()));
		}
	}

	@Inject(method = "calculateRequiredExperienceLevel", at = @At("HEAD"))
	private static void calculateRequiredExperienceLevel(Random random, int slotIndex, int bookshelfCount, ItemStack stack, CallbackInfoReturnable<Integer> cir)
	{
		if(stack.getItem() instanceof IAlloygeryTool alloygeryTool)
		{
			alloygeryTool.setCalculatedEnchantability(AlloygeryToolMaterialHelper.getEnchantability(stack.getNbt()));
		}
	}
}
