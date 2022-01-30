package amorphia.alloygery.mixins;

import amorphia.alloygery.Alloygery;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin
{
	@Environment(EnvType.CLIENT)
	@Inject(method = "appendTooltip", at = @At("HEAD"))
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci)
	{
		NbtCompound nbt = stack.getNbt();
		if (nbt != null && nbt.contains(Alloygery.NBT_KEY) && world.isClient)
		{
			NbtCompound tag = nbt.getCompound(Alloygery.NBT_KEY);
			if (tag != null)
			{
				if (tag.contains(Alloygery.DIAMOND_TIPPED_KEY))
				{
					tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.diamond").formatted(Formatting.AQUA));

					if (Screen.hasShiftDown())
					{
						if (stack.getItem() instanceof PickaxeItem miningTool)
						{
							final int level = miningTool.getMaterial().getMiningLevel() + 1;
							tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.diamond.mining_level_" + level).formatted(Formatting.AQUA));
						}

						if (stack.getItem() instanceof ToolItem)
							tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.diamond.durability").formatted(Formatting.AQUA));
					}
				}
				if (tag.contains(Alloygery.EMERALD_EMBOSSED_KEY))
				{
					tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.emerald").formatted(Formatting.GREEN));

					if (Screen.hasShiftDown())
					{
						if(stack.getItem() instanceof ToolItem)
							tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.emerald.durability").formatted(Formatting.GREEN));
					}
				}
				if (tag.contains(Alloygery.NETHERITE_PLATTED))
				{
					tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.netherite").formatted(Formatting.DARK_RED));

					if (Screen.hasShiftDown())
					{
						if(stack.getItem() instanceof MiningToolItem)
							tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.netherite.mining_speed").formatted(Formatting.DARK_RED));

						if(stack.getItem() instanceof ToolItem)
							tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.netherite.durability").formatted(Formatting.DARK_RED));

						tooltip.add(new TranslatableText("tooltip.alloygery.modifiers.netherite.fireproof").formatted(Formatting.DARK_RED));
					}
				}

				if(!Screen.hasShiftDown())
					tooltip.add(new TranslatableText("tooltip.alloygery.shift_for_info").formatted(Formatting.GRAY));
			}
		}
	}
}
