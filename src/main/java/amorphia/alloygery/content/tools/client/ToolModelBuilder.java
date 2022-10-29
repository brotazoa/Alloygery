package amorphia.alloygery.content.tools.client;

import com.google.common.collect.Maps;
import net.minecraft.item.Item;
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
}
