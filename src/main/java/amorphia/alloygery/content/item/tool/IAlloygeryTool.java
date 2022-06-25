package amorphia.alloygery.content.item.tool;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public interface IAlloygeryTool
{
	List<Item> TOOL_ITEMS = Lists.newArrayList();

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
			if (AlloygeryToolMaterialHelper.isInfo(stack))
			{
				tooltip.add(new TranslatableText("tooltip.alloygery.crafting_info." + alloygeryTool.getToolTypeString()).formatted(Formatting.DARK_GRAY));
				return;
			}

			NbtCompound compound = stack.getNbt();
			if (compound != null)
			{
				if (Screen.hasShiftDown() && !Screen.hasControlDown())
				{
					tooltip.add(new TranslatableText("tooltip.alloygery.ctrl_for_details").formatted(Formatting.DARK_GRAY));

					if(alloygeryTool instanceof MiningToolItem)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_level")
								.append(new LiteralText(": "))
								.append(new TranslatableText("tooltip.alloygery.info.level.enum_" +
										MathHelper.clamp(AlloygeryToolMaterialHelper.getMiningLevel(compound), -1, 5)))
								.formatted(Formatting.GRAY));
					}

					tooltip.add(new TranslatableText("tooltip.alloygery.info.durability").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getMaxDurability(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability").append(new LiteralText(": " + AlloygeryToolMaterialHelper.getEnchantability(compound))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_speed").append(new LiteralText(": " + String.format("%.2f", AlloygeryToolMaterialHelper.getMiningSpeed(compound)))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_speed").append(new LiteralText(": " + String.format("%.2f", AlloygeryToolMaterialHelper.getAttackSpeed(compound)))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_damage").append(new LiteralText(": " + String.format("%.2f", AlloygeryToolMaterialHelper.getAttackDamage(compound)))).formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.luck").append(new LiteralText(": " + String.format("%.2f", AlloygeryToolMaterialHelper.getLuck(compound)))).formatted(Formatting.GRAY));

					if(AlloygeryToolMaterialHelper.isFireproof(compound))
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
					}
					if (AlloygeryToolMaterialHelper.isPiglinLoved(compound))
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
					}

					if(stack.hasEnchantments())
						tooltip.add(new LiteralText(""));
				}
				else if (Screen.hasShiftDown() && Screen.hasControlDown())
				{
					AlloygeryMaterial headMaterial = AlloygeryToolMaterialHelper.getHeadMaterial(compound);
					AlloygeryMaterial bindingMaterial = AlloygeryToolMaterialHelper.getBindingMaterial(compound);
					AlloygeryMaterial handleMaterial = AlloygeryToolMaterialHelper.getHandleMaterial(compound);
					AlloygeryMaterial upgradeMaterial = AlloygeryToolMaterialHelper.getUpgradeMaterial(compound);

					final String head_type_string = alloygeryTool.getToolTypeString() + "_" + alloygeryTool.getHeadTypeString();

					final boolean miningTool = alloygeryTool instanceof MiningToolItem;

					final int[] miningLevelByParts = new int[4];
					final int[] durabilityByParts = new int[4];
					final int[] enchantabilityByParts = new int[4];
					final float[] miningSpeedByParts = new float[4];
					final float[] attackSpeedByParts = new float[4];
					final float[] attackDamageByParts = new float[4];
					final float[] luckByParts = new float[4];
					final boolean[] fireproofByParts = new boolean[4];
					final boolean[] piglinLovedByParts = new boolean[4];

					AlloygeryToolMaterialHelper.getMiningLevelByPart(compound, miningLevelByParts);
					AlloygeryToolMaterialHelper.getMaxDurabilityByPart(compound, durabilityByParts);
					AlloygeryToolMaterialHelper.getEnchantabilityByPart(compound, enchantabilityByParts);
					AlloygeryToolMaterialHelper.getMiningSpeedByPart(compound, miningSpeedByParts);
					AlloygeryToolMaterialHelper.getAttackSpeedByPart(compound, attackSpeedByParts);
					AlloygeryToolMaterialHelper.getAttackDamageByPart(compound, attackDamageByParts);
					AlloygeryToolMaterialHelper.getLuckByPart(compound, luckByParts);
					AlloygeryToolMaterialHelper.isFireproofByPart(compound, fireproofByParts);
					AlloygeryToolMaterialHelper.isPiglinLovedByPart(compound, piglinLovedByParts);

					//head
					tooltip.add(new TranslatableText("tooltip.alloygery.info.head")
							.append(new LiteralText(": ")
									.append(new TranslatableText("item.alloygery." + headMaterial.name + "_" + head_type_string)))
							.formatted(Formatting.WHITE));

					if(miningTool)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_level")
								.append(new LiteralText(": " + miningLevelByParts[0]))
								.formatted(Formatting.GRAY));
					}
					tooltip.add(new TranslatableText("tooltip.alloygery.info.durability")
							.append(new LiteralText(": " + durabilityByParts[0]))
							.formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability")
							.append(new LiteralText(": " + enchantabilityByParts[0]))
							.formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_speed")
							.append(new LiteralText(": " + String.format("%.2f", miningSpeedByParts[0])))
							.formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_speed")
							.append(new LiteralText(": " + String.format("%.2f", attackSpeedByParts[0])))
							.formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_damage")
							.append(new LiteralText(": " + String.format("%.2f", attackDamageByParts[0])))
							.formatted(Formatting.GRAY));
					tooltip.add(new TranslatableText("tooltip.alloygery.info.luck")
							.append(new LiteralText(": " + String.format("%.2f", luckByParts[0])))
							.formatted(Formatting.GRAY));

					if(fireproofByParts[0]) tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
					if(piglinLovedByParts[0]) tooltip.add(new TranslatableText("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));

					if (bindingMaterial != AlloygeryMaterials.HIDDEN)
					{
						//binding
						tooltip.add(new TranslatableText("tooltip.alloygery.info.binding")
								.append(new LiteralText(": ")
										.append(new TranslatableText("item.alloygery." + bindingMaterial.name + "_binding")))
								.formatted(Formatting.WHITE));

						final int binding_mining_level = miningLevelByParts[1] - miningLevelByParts[0];
						if(binding_mining_level != 0 && miningTool)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_level")
									.append(new LiteralText(": " + binding_mining_level))
									.formatted(Formatting.GRAY));
						}
						final int binding_durability = durabilityByParts[1] - durabilityByParts[0];
						if(binding_durability != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.durability")
									.append(new LiteralText(": " + binding_durability))
									.formatted(Formatting.GRAY));
						}
						final int binding_enchantability = enchantabilityByParts[1] - enchantabilityByParts[0];
						if(binding_enchantability != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability")
									.append(new LiteralText(": " + binding_enchantability))
									.formatted(Formatting.GRAY));
						}
						final float binding_mining_speed = miningSpeedByParts[1] - miningSpeedByParts[0];
						if(binding_mining_speed != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_speed")
									.append(new LiteralText(": " + String.format("%.2f", binding_mining_speed)))
									.formatted(Formatting.GRAY));
						}
						final float binding_attack_speed = attackSpeedByParts[1] - attackSpeedByParts[0];
						if(binding_attack_speed != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_speed")
									.append(new LiteralText(": " + String.format("%.2f", binding_attack_speed)))
									.formatted(Formatting.GRAY));
						}
						final float binding_attack_damage = attackDamageByParts[1] - attackDamageByParts[0];
						if(binding_attack_damage != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_damage")
									.append(new LiteralText(": " + String.format("%.2f", binding_attack_damage)))
									.formatted(Formatting.GRAY));
						}
						final float binding_luck = luckByParts[1] - luckByParts[0];
						if(binding_luck != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.luck")
									.append(new LiteralText(": " + String.format("%.2f", binding_luck)))
									.formatted(Formatting.GRAY));
						}

						if(fireproofByParts[1]) tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
						if(piglinLovedByParts[1]) tooltip.add(new TranslatableText("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));

					}

					//handle
					tooltip.add(new TranslatableText("tooltip.alloygery.info.handle")
							.append(new LiteralText(": ")
									.append(new TranslatableText("item.alloygery." + handleMaterial.name + "_handle")))
							.formatted(Formatting.WHITE));

					final int handle_mining_level = miningLevelByParts[2] - miningLevelByParts[1];
					if(handle_mining_level != 0 && miningTool)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_level")
								.append(new LiteralText(": " + handle_mining_level))
								.formatted(Formatting.GRAY));
					}
					final int handle_durability = durabilityByParts[2] - durabilityByParts[1];
					if(handle_durability != 0)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.durability")
								.append(new LiteralText(": " + handle_durability))
								.formatted(Formatting.GRAY));
					}
					final int handle_enchantability = enchantabilityByParts[2] - enchantabilityByParts[1];
					if(handle_enchantability != 0)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability")
								.append(new LiteralText(": " + handle_enchantability))
								.formatted(Formatting.GRAY));
					}
					final float handle_mining_speed = miningSpeedByParts[2] - miningSpeedByParts[1];
					if(handle_mining_speed != 0)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_speed")
								.append(new LiteralText(": " + String.format("%.2f", handle_mining_speed)))
								.formatted(Formatting.GRAY));
					}
					final float handle_attack_speed = attackSpeedByParts[2] - attackSpeedByParts[1];
					if(handle_attack_speed != 0)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_speed")
								.append(new LiteralText(": " + String.format("%.2f", handle_attack_speed)))
								.formatted(Formatting.GRAY));
					}
					final float handle_attack_damage = attackDamageByParts[2] - attackDamageByParts[1];
					if(handle_attack_damage != 0)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_damage")
								.append(new LiteralText(": " + String.format("%.2f", handle_attack_damage)))
								.formatted(Formatting.GRAY));
					}
					final float handle_luck = luckByParts[2] - luckByParts[1];
					if(handle_luck != 0)
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.luck")
								.append(new LiteralText(": " + String.format("%.2f", handle_luck)))
								.formatted(Formatting.GRAY));
					}

					if(fireproofByParts[2]) tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
					if(piglinLovedByParts[2]) tooltip.add(new TranslatableText("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));

					if (upgradeMaterial != AlloygeryMaterials.UNKNOWN)
					{
						//uppgrade
						tooltip.add(new TranslatableText("tooltip.alloygery.info.upgrade")
								.append(new LiteralText(": ")
										.append(new TranslatableText("tooltip.alloygery.upgrade." + upgradeMaterial.name)))
								.formatted(Formatting.WHITE));

						final int upgrade_mining_level = miningLevelByParts[3] - miningLevelByParts[2];
						if(upgrade_mining_level != 0 && miningTool)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_level")
									.append(new LiteralText(": " + upgrade_mining_level))
									.formatted(Formatting.GRAY));
						}
						final int upgrade_durability = durabilityByParts[3] - durabilityByParts[2];
						if(upgrade_durability != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.durability")
									.append(new LiteralText(": " + upgrade_durability))
									.formatted(Formatting.GRAY));
						}
						final int upgrade_enchantability = enchantabilityByParts[3] - enchantabilityByParts[2];
						if(upgrade_enchantability != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.enchantability")
									.append(new LiteralText(": " + upgrade_enchantability))
									.formatted(Formatting.GRAY));
						}
						final float upgrade_mining_speed = miningSpeedByParts[3] - miningSpeedByParts[2];
						if(upgrade_mining_speed != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.mining_speed")
									.append(new LiteralText(": " + String.format("%.2f", upgrade_mining_speed)))
									.formatted(Formatting.GRAY));
						}
						final float upgrade_attack_speed = attackSpeedByParts[3] - attackSpeedByParts[2];
						if(upgrade_attack_speed != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_speed")
									.append(new LiteralText(": " + String.format("%.2f", upgrade_attack_speed)))
									.formatted(Formatting.GRAY));
						}
						final float upgrade_attack_damage = attackDamageByParts[3] - attackDamageByParts[2];
						if(upgrade_attack_damage != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.attack_damage")
									.append(new LiteralText(": " + String.format("%.2f", upgrade_attack_damage)))
									.formatted(Formatting.GRAY));
						}
						final float upgrade_luck = luckByParts[3] - luckByParts[2];
						if(upgrade_luck != 0)
						{
							tooltip.add(new TranslatableText("tooltip.alloygery.info.luck")
									.append(new LiteralText(": " + String.format("%.2f", upgrade_luck)))
									.formatted(Formatting.GRAY));
						}

						if(fireproofByParts[3]) tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
						if(piglinLovedByParts[3]) tooltip.add(new TranslatableText("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
					}

					if(stack.hasEnchantments())
						tooltip.add(new LiteralText(""));
				}
				else
				{
					tooltip.add(new TranslatableText("tooltip.alloygery.shift_for_info").formatted(Formatting.DARK_GRAY));

					if (AlloygeryToolMaterialHelper.isFireproof(compound))
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
					}

					if (AlloygeryToolMaterialHelper.isPiglinLoved(compound))
					{
						tooltip.add(new TranslatableText("tooltip.alloygery.info.piglin_loved").formatted(Formatting.GOLD));
					}
				}
			}
		}
	}

	static Text getUpgradedName(ItemStack stack, String postfix)
	{
		if (AlloygeryToolMaterialHelper.isInfo(stack))
		{
			return new TranslatableText("item.alloygery.info_" + postfix);
		}

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
