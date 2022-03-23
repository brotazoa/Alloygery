package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.mixins.ItemAccessor;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public interface IAlloygeryTool
{
	List<Item> ITEMS = Lists.newArrayList();

	static void appendToTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context)
	{
		if (stack.getItem() instanceof IAlloygeryTool alloygeryTool)
		{
			NbtCompound compound = stack.getNbt();
			if (compound != null)
			{
				AlloygeryMaterial headMaterial = AlloygeryMaterialHelper.getHeadMaterial(compound);
				AlloygeryMaterial bindingMaterial = AlloygeryMaterialHelper.getBindingMaterial(compound);
				AlloygeryMaterial handleMaterial = AlloygeryMaterialHelper.getHandleMaterial(compound);
				AlloygeryMaterial upgradeMaterial = AlloygeryMaterialHelper.getUpgradeMaterial(compound);

				if (Screen.hasShiftDown() && !Screen.hasControlDown())
				{
					tooltip.add(new TranslatableText("tooltip.alloygery.info.uses").append(new LiteralText(": " + AlloygeryMaterialHelper.getMaxDurability(compound))).formatted(Formatting.GRAY));

					if(alloygeryTool instanceof MiningToolItem)
						tooltip.add(new TranslatableText("tooltip.alloygery.info.level").append(new LiteralText(": ").append(new TranslatableText("tooltip.alloygery.info.level.enum_" + Math.min(AlloygeryMaterialHelper.getMiningLevel(compound), 5)))).formatted(Formatting.GRAY));

					tooltip.add(new TranslatableText("tooltip.alloygery.info.speed").append(new LiteralText(": " + AlloygeryMaterialHelper.getMiningSpeed(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.damage").append(new LiteralText(": " + AlloygeryMaterialHelper.getAttackDamage(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability").append(new LiteralText(": " + AlloygeryMaterialHelper.getEnchantability(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.ctrl_for_details").formatted(Formatting.DARK_GRAY));
				}
				else if (Screen.hasShiftDown() && Screen.hasControlDown())
				{
					final String tool_head = alloygeryTool.getToolTypeString() + "_" + alloygeryTool.getHeadTypeString();

					//tool stats from head
					tooltip.add(new TranslatableText("tooltip.alloygery.info.head").append(new LiteralText(": ").append(new TranslatableText("item.alloygery." + headMaterial.name + "_" + tool_head))));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.uses").append(new LiteralText(": " + headMaterial.head_durability)).formatted(Formatting.GRAY));

					if(alloygeryTool instanceof MiningToolItem)
						tooltip.add(new TranslatableText("tooltip.alloygery.info.level").append(new LiteralText(": " + headMaterial.level)).formatted(Formatting.GRAY));

					tooltip.add(new TranslatableText("tooltip.alloygery.info.speed").append(new LiteralText(": " + headMaterial.speed)).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.damage").append(new LiteralText(": " + headMaterial.damage)).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability").append(new LiteralText(": " + headMaterial.tool_enchantability)).formatted(Formatting.GRAY));

					//tool stats from binding
					tooltip.add(new TranslatableText("tooltip.alloygery.info.binding").append(new LiteralText(": ").append(new TranslatableText("item.alloygery." + bindingMaterial.name + "_binding"))));

					final int modifiedDurability = (int) ((headMaterial.head_durability * bindingMaterial.durability_multiplier) - headMaterial.head_durability);
					tooltip.add(new TranslatableText("tooltip.alloygery.info.uses").append(new LiteralText(": " + modifiedDurability)).formatted(Formatting.GRAY));

					final int modifiedEnchantability = (int) ((headMaterial.tool_enchantability * bindingMaterial.enchantability_multiplier) - headMaterial.tool_enchantability);
					tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability").append(new LiteralText(": " + modifiedEnchantability)).formatted(Formatting.GRAY));

					//tool stats from handle
					tooltip.add(new TranslatableText("tooltip.alloygery.info.handle").append(new LiteralText(": ")).append(new TranslatableText("item.alloygery." + handleMaterial.name + "_handle")));

					final float modifiedSpeed = headMaterial.speed * handleMaterial.speed_multiplier - headMaterial.speed;
					tooltip.add(new TranslatableText("tooltip.alloygery.info.speed").append(new LiteralText(": " + modifiedSpeed)).formatted(Formatting.GRAY));

					final float modifiedDamage = headMaterial.damage * handleMaterial.damage_multiplier - headMaterial.damage;
					tooltip.add(new TranslatableText("tooltip.alloygery.info.damage").append(new LiteralText(": " + modifiedDamage)).formatted(Formatting.GRAY));

					if (upgradeMaterial != AlloygeryMaterials.UNKNOWN && upgradeMaterial.category.equals("upgrade"))
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.upgrade").append(new LiteralText(": ").append(new TranslatableText("tooltip.alloygery.upgrade." + upgradeMaterial.name))));

						if (upgradeMaterial.head_durability != 0 || upgradeMaterial.durability_multiplier != 1.0f)
						{
							final int base = (int) (headMaterial.head_durability * bindingMaterial.durability_multiplier + headMaterial.head_durability);
							final int upgrade = (int) (base * upgradeMaterial.durability_multiplier + upgradeMaterial.head_durability) - base;
							tooltip.add(new TranslatableText("tooltip.alloygery.info.uses").append(new LiteralText(": " + upgrade)).formatted(Formatting.GRAY));
						}

						if (upgradeMaterial.level != 0 && alloygeryTool instanceof MiningToolItem)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.level").append(new LiteralText(": " + upgradeMaterial.level)).formatted(Formatting.GRAY));
						}

						if (upgradeMaterial.speed != 0 || upgradeMaterial.speed_multiplier != 1.0f)
						{
							final float base = headMaterial.speed * handleMaterial.speed_multiplier + headMaterial.speed;
							final float upgrade = (base * upgradeMaterial.speed_multiplier + upgradeMaterial.speed) - base;
							tooltip.add(new TranslatableText("tooltip.alloygery.info.speed").append(new LiteralText(": " + upgrade)).formatted(Formatting.GRAY));
						}

						if (upgradeMaterial.damage != 0 || upgradeMaterial.damage_multiplier != 1.0f)
						{
							final float base = headMaterial.damage * handleMaterial.damage_multiplier + headMaterial.damage;
							final float upgrade = (base * upgradeMaterial.damage_multiplier + upgradeMaterial.damage) - base;
							tooltip.add(new TranslatableText("tooltip.alloygery.info.speed").append(new LiteralText(": " + upgrade)).formatted(Formatting.GRAY));
						}

						if (upgradeMaterial.tool_enchantability != 0 || upgradeMaterial.enchantability_multiplier != 1.0f)
						{
							final int base = (int) (headMaterial.tool_enchantability * bindingMaterial.enchantability_multiplier + headMaterial.tool_enchantability);
							final int upgrade = (int) (base * upgradeMaterial.enchantability_multiplier + upgradeMaterial.tool_enchantability) - base;
							tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability").append(new LiteralText(": " + upgrade)).formatted(Formatting.GRAY));
						}
					}
				}

				if (!Screen.hasShiftDown()) tooltip.add(new TranslatableText("tooltip.alloygery.shift_for_info").formatted(Formatting.DARK_GRAY));
			}
		}
	}

	static Text getUpgradedName(ItemStack stack, String postfix)
	{
		AlloygeryMaterial head = AlloygeryMaterialHelper.getHeadMaterial(stack);
		AlloygeryMaterial upgrade = AlloygeryMaterialHelper.getUpgradeMaterial(stack);

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
