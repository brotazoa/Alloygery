package amorphia.alloygery.content.tools.item.part;

import amorphia.alloygery.content.tools.ToolDescriptionHelper;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import amorphia.alloygery.content.tools.property.ToolProperty;
import com.google.common.collect.Lists;
import net.minecraft.text.Text;

import java.util.List;

public interface IToolPart
{
	List<ToolPartItem> TOOL_PART_ITEMS = Lists.newArrayList();

	ToolPartType getToolPartType();

	AlloygeryToolMaterial getMaterial();

	default List<ToolProperty> getToolPartProperties()
	{
		return getMaterial().getToolPropertiesByPart(getToolPartType());
	}

	default void writeTooltip(List<Text> tooltip)
	{
		ToolDescriptionHelper.writeToolPartDescription(tooltip, this);
	}
}
