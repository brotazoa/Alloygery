package amorphia.alloygery.content.tools.mixin;

import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

@Mixin(EnchantmentHelper.class)
public class ToolEnchantabilityMixin
{
	@ModifyVariable(method = "generateEnchantments", ordinal = 1, at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/Item;getEnchantability()I"))
	private static int calcEnchantability(int i, Random random, ItemStack stack, int level, boolean treasureAllowed)
	{
		return stack.getItem() instanceof IDynamicTool dynamicTool ? dynamicTool.getEnchantability(stack) : stack.getItem().getEnchantability();
	}

	@ModifyVariable(method = "calculateRequiredExperienceLevel", ordinal = 2, at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/Item;getEnchantability()I"))
	private static int calcEnchantabilityForRequiredExperienceLevel(int i, Random random, int slotIndex, int bookshelfCount, ItemStack stack)
	{
		return stack.getItem() instanceof IDynamicTool dynamicTool ? dynamicTool.getEnchantability(stack) : stack.getItem().getEnchantability();
	}
}
