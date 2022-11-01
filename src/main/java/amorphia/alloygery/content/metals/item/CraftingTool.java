package amorphia.alloygery.content.metals.item;

import amorphia.alloygery.content.tools.ToolPropertyHelper;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public interface CraftingTool
{
	default int getTier()
	{
		return 0;
	}

	AlloygeryMaterial getAlloygeryMaterial();

	static int getMaxDurability(ItemStack toolStack)
	{
		if (toolStack != null && !toolStack.isEmpty() && toolStack.getItem() instanceof CraftingTool craftingItem)
		{
			return (int) ToolPropertyHelper.computePropertyValue(craftingItem.getAlloygeryMaterial().getToolPropertiesByPart(ToolPartType.HEAD), ToolPropertyType.DURABILITY);
		}

		else return toolStack == null ? 0 : toolStack.getMaxDamage();
	}

	static int getItemBarStep(ItemStack tool)
	{
		return tool.getItem() instanceof CraftingTool craftingItem ? Math.round(13.0f - (float) tool.getDamage() * 13.0f / (float) ToolPropertyHelper.computePropertyValue(craftingItem.getAlloygeryMaterial().getToolPropertiesByPart(ToolPartType.HEAD), ToolPropertyType.DURABILITY)) : tool.getItemBarStep();
	}

	static int getItemBarColor(ItemStack tool)
	{
		final float max = (float) CraftingTool.getMaxDurability(tool);
		final float f = Math.max(0.0f, (max - tool.getDamage()) / max);
		return MathHelper.hsvToRgb(f / 3.0f, 1.0f, 1.0f);
	}
}
