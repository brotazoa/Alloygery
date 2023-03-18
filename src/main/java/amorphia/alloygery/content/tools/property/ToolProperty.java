package amorphia.alloygery.content.tools.property;

import amorphia.alloygery.content.tools.item.part.ToolPartType;

public record ToolProperty(ToolPartType partType, ToolPropertyType type, ToolPropertyOperation operation, float value) {}
