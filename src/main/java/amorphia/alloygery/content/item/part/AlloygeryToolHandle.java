package amorphia.alloygery.content.item.part;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialRegistry;
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

public class AlloygeryToolHandle extends AlloygeryPartItem
{
	public AlloygeryToolHandle(Settings settings)
	{
		super(settings);
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks)
	{
		if (this.isIn(group))
		{
			AlloygeryMaterialRegistry.forEach((identifier, material) -> {
				if (material.make_tool_handles)
				{
					ItemStack stack = new ItemStack(this);
					AlloygeryToolMaterialHelper.setPartMaterial(stack, identifier);
					stacks.add(stack);
				}
			});
		}
	}

	@Override
	public Text getName(ItemStack stack)
	{
		boolean hasMaterial = AlloygeryToolMaterialHelper.hasMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL);
		AlloygeryMaterial material = AlloygeryToolMaterialHelper.getMaterial(stack, AlloygeryToolMaterialHelper.NBT_KEYS.PART_MATERIAL);

		return new TranslatableText("item.alloygery." + (!hasMaterial && material == AlloygeryMaterials.UNKNOWN ? AlloygeryMaterials.INFO.name : material.name) + "_handle");
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		if(AlloygeryToolMaterialHelper.isInfo(stack))
			return;

		AlloygeryMaterial material = AlloygeryToolMaterialHelper.getPartMaterial(stack);
		AlloygeryMaterial.ToolPartSettings settings = material.tool_handle;

		appendTooltipWithPartSettings(stack, world, tooltip, context, settings);
	}
}
