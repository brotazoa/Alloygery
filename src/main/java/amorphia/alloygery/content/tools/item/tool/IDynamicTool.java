package amorphia.alloygery.content.tools.item.tool;

import amorphia.alloygery.content.tools.ToolPropertyHelper;
import amorphia.alloygery.content.tools.attribute.ToolAttributes;
import amorphia.alloygery.content.tools.mixin.MiningToolItemAccessor;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.util.math.MathHelper;

import java.util.List;

// TODO: separate into multiple interfaces
//stack dependent operations for tools
public interface IDynamicTool
{
	List<Item> TOOL_ITEMS = Lists.newArrayList();

	Multimap<EntityAttribute, EntityAttributeModifier> EMPTY = ImmutableMultimap.of();

	default Multimap<EntityAttribute, EntityAttributeModifier> getDynamicAttributeModifiers(EquipmentSlot slot, ItemStack tool)
	{
		if (slot == EquipmentSlot.MAINHAND)
		{
			ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
			builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,
					new EntityAttributeModifier(ToolAttributes.ATTACK_DAMAGE_MODIFIER, "Tool Modifier",
							getAttackDamage(tool), EntityAttributeModifier.Operation.ADDITION));
			builder.put(EntityAttributes.GENERIC_ATTACK_SPEED,
					new EntityAttributeModifier(ToolAttributes.ATTACK_SPEED_MODIFIER, "Tool Modifier",
							getAttackSpeed(tool), EntityAttributeModifier.Operation.ADDITION));
			return builder.build();
		}
		else
		{
			return EMPTY;
		}
	}

	default int getMaxDurability(ItemStack stack)
	{
		return ToolPropertyHelper.getMaxDurability(stack);
	}

	default int getEnchantability(ItemStack stack)
	{
		return ToolPropertyHelper.getEnchantability(stack);
	}

	default float getMiningSpeedMultiplier(BlockState blockState, ItemStack tool)
	{
		if (tool.getItem() instanceof MiningToolItem miningToolItem)
		{
			return blockState.isIn(((MiningToolItemAccessor) miningToolItem).getEffectiveBlocks()) ? ToolPropertyHelper.getMiningSpeed(tool) : 1.0f;
		}
		else
		{
			return 1.0f;
		}
	}

	default float getAttackDamage(ItemStack tool)
	{
		return ToolPropertyHelper.getAttackDamage(tool);
	}

	default float getAttackSpeed(ItemStack tool)
	{
		return ToolPropertyHelper.getAttackSpeed(tool);
	}

	default boolean isSuitableFor(BlockState blockState, ItemStack tool)
	{
		return ToolPropertyHelper.getMiningLevel(tool) >= MiningLevelManager.getRequiredMiningLevel(blockState);
	}

	default boolean isPiglinLoved(ItemStack tool)
	{
		return ToolPropertyHelper.isPiglinLoved(tool);
	}

	default boolean isFireproof(ItemStack tool)
	{
		return ToolPropertyHelper.isFireproof(tool);
	}

	static int getItemBarStep(ItemStack tool)
	{
		return Math.round(13.0f - (float) tool.getDamage() * 13.0f / (float) ToolPropertyHelper.getMaxDurability(tool));
	}

	static int getItemBarColor(ItemStack tool)
	{
		final float maxDamage = (float) ToolPropertyHelper.getMaxDurability(tool);
		final float f = Math.max(0.0f, (maxDamage - tool.getDamage()) / maxDamage);
		return MathHelper.hsvToRgb(f / 3.0f, 1.0f, 1.0f);
	}
}
