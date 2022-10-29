package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.content.tools.ToolDescriptionHelper;
import amorphia.alloygery.content.tools.material.ToolMaterial;
import amorphia.alloygery.content.tools.property.ToolProperty;
import com.google.common.collect.Lists;
import net.minecraft.text.Text;

import java.util.List;

public interface IToolPart
{
	List<PartItem> TOOL_PART_ITEMS = Lists.newArrayList();

	ToolPartType getToolPartType();

	ToolMaterial getMaterial();

	default List<ToolProperty> getToolPartProperties()
	{
		return getMaterial().getPropertiesByPart(getToolPartType());
	}

	default void writeTooltip(List<Text> tooltip)
	{
		ToolDescriptionHelper.writeToolPartDescription(tooltip, this);
	}
}
