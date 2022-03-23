package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Scanner;

public class AlloygeryPartItem extends Item implements IMaterialItem
{
	public static final List<AlloygeryPartItem> PART_ITEMS = Lists.newArrayList();

	private final AlloygeryMaterial material;

	public AlloygeryPartItem(AlloygeryMaterial material, Item.Settings settings)
	{
		super(settings);
		this.material = material;
		PART_ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return material;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		AlloygeryMaterial partMaterial = AlloygeryMaterialHelper.getMaterial(stack, AlloygeryMaterialHelper.NBT_KEYS.PART_MATERIAL);

		if (!Screen.hasShiftDown())
		{
			tooltip.add(new TranslatableText("tooltip.alloygery.shift_for_info").formatted(Formatting.DARK_GRAY));
			return;
		}

		tooltip.add(new TranslatableText("tooltip.alloygery.when_used_in_craft"));

		if (stack.getTranslationKey().endsWith("head") || stack.getTranslationKey().endsWith("sword_blade"))
		{
			tooltip.add(new TranslatableText("tooltip.alloygery.info.uses").append(new LiteralText(": " + partMaterial.head_durability)).formatted(Formatting.GRAY));
			tooltip.add(new TranslatableText("tooltip.alloygery.info.level").append(new LiteralText(": " + partMaterial.level)).formatted(Formatting.GRAY));
			tooltip.add(new TranslatableText("tooltip.alloygery.info.speed").append(new LiteralText(": " + partMaterial.speed)).formatted(Formatting.GRAY));
			tooltip.add(new TranslatableText("tooltip.alloygery.info.damage").append(new LiteralText(": " + partMaterial.damage)).formatted(Formatting.GRAY));
			tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability").append(new LiteralText(": " + partMaterial.tool_enchantability)).formatted(Formatting.GRAY));
		}
		else if(stack.getTranslationKey().endsWith("binding") || stack.getTranslationKey().endsWith("sword_guard"))
		{
			tooltip.add(new TranslatableText("tooltip.alloygery.info.uses_multiplier").append(new LiteralText(": " + partMaterial.durability_multiplier)).formatted(Formatting.GRAY));
			tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability_multiplier").append(new LiteralText(": " + partMaterial.enchantability_multiplier)).formatted(Formatting.GRAY));
		}
		else if(stack.getTranslationKey().endsWith("handle"))
		{
			tooltip.add(new TranslatableText("tooltip.alloygery.info.speed_multiplier").append(new LiteralText(": " + partMaterial.speed_multiplier)).formatted(Formatting.GRAY));
			tooltip.add(new TranslatableText("tooltip.alloygery.info.damage_multiplier").append(new LiteralText(": " + partMaterial.damage_multiplier)).formatted(Formatting.GRAY));
		}
	}
}
