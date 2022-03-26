package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
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
		AlloygeryMaterial partMaterial = AlloygeryToolMaterialHelper.getMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL);

		if (!Screen.hasShiftDown())
		{
			tooltip.add(new TranslatableText("tooltip.alloygery.shift_for_info").formatted(Formatting.DARK_GRAY));
			return;
		}

		tooltip.add(new TranslatableText("tooltip.alloygery.when_used_in_craft"));

		AlloygeryMaterial.ToolPartSettings settings = null;

		if (stack.getTranslationKey().endsWith("head") || stack.getTranslationKey().endsWith("sword_blade"))
		{
			settings = partMaterial.tool_base;
		}
		else if(stack.getTranslationKey().endsWith("binding") || stack.getTranslationKey().endsWith("sword_guard"))
		{
			settings = partMaterial.tool_binding;
		}
		else if(stack.getTranslationKey().endsWith("handle"))
		{
			settings = partMaterial.tool_handle;
		}

		if (settings != null)
		{
			if(settings.mining_level != 0)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_level").append(new LiteralText(": " + settings.mining_level)).formatted(Formatting.GRAY));

			if(settings.durability != 0)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.durability").append(new LiteralText(": " + settings.durability)).formatted(Formatting.GRAY));

			if(settings.mining_speed != 0)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_speed").append(new LiteralText(": " + settings.mining_speed)).formatted(Formatting.GRAY));

			if(settings.attack_speed != 0)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_speed").append(new LiteralText(": " + settings.attack_speed)).formatted(Formatting.GRAY));

			if(settings.attack_damage != 0)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_damage").append(new LiteralText(": " + settings.attack_damage)).formatted(Formatting.GRAY));

			if(settings.luck != 0)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.luck").append(new LiteralText(": " + settings.luck)).formatted(Formatting.GRAY));

			if(settings.durability_multiplier != 1.0f)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.durability_multiplier").append(new LiteralText(": " + settings.durability_multiplier)).formatted(Formatting.GRAY));

			if(settings.mining_speed_multiplier != 1.0f)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_speed_multiplier").append(new LiteralText(": " + settings.mining_speed_multiplier)).formatted(Formatting.GRAY));

			if(settings.attack_speed_multiplier != 1.0f)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_speed_multiplier").append(new LiteralText(": " + settings.attack_speed_multiplier)).formatted(Formatting.GRAY));

			if(settings.attack_damage_multiplier != 1.0f)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_damage_multiplier").append(new LiteralText(": " + settings.attack_damage_multiplier)).formatted(Formatting.GRAY));

			if(settings.enchantability_multiplier != 1.0f)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability_multiplier").append(new LiteralText(": " + settings.enchantability_multiplier)).formatted(Formatting.GRAY));

			if(settings.luck_multiplier != 1.0f)
				tooltip.add(new TranslatableText("tooltip.alloygery.info.luck_multiplier").append(new LiteralText(": " + settings.luck_multiplier)).formatted(Formatting.GRAY));

			if(settings.fireproof) tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
		}
	}
}
