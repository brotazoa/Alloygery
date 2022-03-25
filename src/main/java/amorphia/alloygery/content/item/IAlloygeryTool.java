package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public interface IAlloygeryTool
{
	List<Item> ITEMS = Lists.newArrayList();

	static int getModifiedItemBarStep(ItemStack stack)
	{
		return Math.round(13.0f - (float) stack.getDamage() * 13.0f / (float) AlloygeryToolMaterialHelper.getMaxDurability(stack.getNbt()));
	}

	static int getModifierItemBarColor(ItemStack stack)
	{
		final float maxDamage = (float) AlloygeryToolMaterialHelper.getMaxDurability(stack.getNbt());
		float f = Math.max(0.0f, (maxDamage - stack.getDamage()) / maxDamage);
		return MathHelper.hsvToRgb(f / 3.0f, 1.0f, 1.0f);
	}

	static void appendToTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context)
	{
		if (stack.getItem() instanceof IAlloygeryTool alloygeryTool)
		{
			NbtCompound compound = stack.getNbt();
			if (compound != null)
			{
				if (Screen.hasShiftDown())
				{
					if(alloygeryTool instanceof MiningToolItem)
						tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_level").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getMiningLevel(compound))).formatted(Formatting.GRAY));

					tooltip.add(new TranslatableText("tooltip.alloygery.info.durability").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getMaxDurability(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_speed").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getMiningSpeed(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_speed").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getAttackSpeed(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_damage").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getAttackDamage(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getEnchantability(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.luck").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getLuck(compound))).formatted(Formatting.GRAY));

					if(AlloygeryToolMaterialHelper.isFireproof(compound))
						tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));

					tooltip.add(new LiteralText(""));
				}
				else
				{
					if(AlloygeryToolMaterialHelper.isFireproof(compound))
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
						tooltip.add(new LiteralText(""));
					}

					tooltip.add(new TranslatableText("tooltip.alloygery.shift_for_info").formatted(Formatting.DARK_GRAY));
				}
			}
		}
	}

	static Text getUpgradedName(ItemStack stack, String postfix)
	{
		AlloygeryMaterial head = AlloygeryToolMaterialHelper.getHeadMaterial(stack);
		AlloygeryMaterial upgrade = AlloygeryToolMaterialHelper.getUpgradeMaterial(stack);

		TranslatableText base = new TranslatableText("item.alloygery." + head.name + "_" + postfix);
		TranslatableText withUpgrade = new TranslatableText("tooltip.alloygery.upgrade." + upgrade.name);

		return upgrade != AlloygeryMaterials.UNKNOWN ? withUpgrade.append(new LiteralText(" ")).append(base) : base;
	}

	void setCalculatedEnchantability(int enchantability);

	String getToolTypeString();

	String getHeadTypeString();

	float getAttackDamageModifier();

	float getAttackSpeedModifier();
}
