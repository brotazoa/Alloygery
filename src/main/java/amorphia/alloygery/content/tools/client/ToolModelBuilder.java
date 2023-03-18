package amorphia.alloygery.content.tools.client;

import amorphia.alloygery.content.tools.item.part.ToolType;
import amorphia.alloygery.content.tools.item.part.ToolUpgradeType;
import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ToolModelBuilder
{
	private static final Map<Identifier, Supplier<String>> MODEL_SUPPLIER_FOR_IDENTIFIER = Maps.newIdentityHashMap();

	public static void register(Identifier identifier, Supplier<String> modelSupplier)
	{
		MODEL_SUPPLIER_FOR_IDENTIFIER.put(identifier, modelSupplier);
	}

	public static Optional<Map.Entry<Identifier, Supplier<String>>> getModelSupplierForIdentifier(Identifier identifier)
	{
		return MODEL_SUPPLIER_FOR_IDENTIFIER.entrySet().stream().filter(entry -> entry.getKey().equals(identifier)).findFirst();
	}

	public static String createPartItemModelJson(String partType)
	{
		return "{\"parent\": \"alloygery:item/part_" + partType + "_template\"}";
	}

	public static String createToolItemModelJson(String parent)
	{
		return "{\"parent\": \"alloygery:item/" + parent + "\"}";
	}

	public static Supplier<String> createToolHeadItemModelJson(ToolType toolType)
	{
		return () -> createPartItemModelJson(toolType.getName() + (toolType.equals(ToolType.SWORD) ? "_blade" : "_head"));
	}

	public static Supplier<String> createToolBindingItemModelJson()
	{
		return () -> createPartItemModelJson("binding");
	}

	public static Supplier<String> createToolHandleItemModelJson()
	{
		return () -> createPartItemModelJson("handle");
	}

	public static Supplier<String> createToolItemModelJson(ToolType toolType, ToolUpgradeType upgradeType)
	{
		return () -> createToolItemModelJson((upgradeType.equals(ToolUpgradeType.NONE) ? "" : upgradeType.getName()) + "_dynamic_" + toolType.getName());
	}
}
