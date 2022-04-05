package amorphia.alloygery.content.item.part;

import amorphia.alloygery.config.AlloygeryModMenu;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlloygeryToolHead extends AlloygeryPartItem
{
	private final String headType;

	public AlloygeryToolHead(String headType, Settings settings)
	{
		super(settings);
		this.headType = headType;
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks)
	{
		if (this.isIn(group))
		{
			AlloygeryMaterials.ALLOYGERY_MATERIALS.values().forEach(material -> {
				if (material.make_tool_heads)
				{
					ItemStack stack = new ItemStack(this);
					AlloygeryToolMaterialHelper.setMaterial(stack, material, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL);
					stacks.add(stack);
				}
			});
		}
	}

	@Override
	public Text getName(ItemStack stack)
	{
		AlloygeryMaterial material = AlloygeryToolMaterialHelper.getMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL);

		return new TranslatableText("item.alloygery." + material.name + "_" + headType);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		if (!Screen.hasShiftDown())
		{
			tooltip.add(new TranslatableText("tooltip.alloygery.shift_for_info").formatted(Formatting.DARK_GRAY));
		}
		else
		{
			AlloygeryMaterial material = AlloygeryToolMaterialHelper.getMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL);
			AlloygeryMaterial.ToolPartSettings settings = material.tool_base;

			tooltip.add(new TranslatableText("tooltip.alloygery.when_used_in_craft"));

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

			if(settings.fireproof) tooltip.add(new TranslatableText("tooltip.alloygery.info.fireproof").formatted(Formatting.RED));
		}
	}
}
