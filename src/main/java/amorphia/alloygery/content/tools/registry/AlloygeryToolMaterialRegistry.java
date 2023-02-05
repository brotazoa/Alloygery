package amorphia.alloygery.content.tools.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.data.IAlloygeryToolMaterialData;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class AlloygeryToolMaterialRegistry
{
	private static final BiMap<Identifier, AlloygeryToolMaterial> MATERIALS = HashBiMap.create();

	private static final Map<Identifier, IAlloygeryToolMaterialData> REGISTERED_MATERIAL_DATA = Maps.newHashMap();
	private static final List<Identifier> LOADED_MATERIAL_DATA = Lists.newArrayList();

	private static final Identifier DEFAULT_IDENTIFIER = Alloygery.identifier("tool_materials/unknown");
	private static AlloygeryToolMaterial DEFAULT_MATERIAL = null;

	public static AlloygeryToolMaterial register(Identifier identifier, AlloygeryToolMaterial material)
	{
		if(identifier == null || material == null) throw new IllegalArgumentException("[AlloygeryToolMaterialRegistry] Can not register null values");

		Alloygery.LOGGER.info("[AlloygeryToolMaterialRegistry] Registering Material: " + identifier.toString());

		//overriding materials is not allowed
		if (MATERIALS.containsKey(identifier))
		{
			if(MATERIALS.get(identifier) == material)
			{
				Alloygery.LOGGER.error("[AlloygeryToolMaterialRegistry] Tried to register a material twice " + identifier.toString());
				throw new IllegalArgumentException("Can not register same material twice: " + identifier.toString());
			}
			else
			{
				Alloygery.LOGGER.error("[AlloygeryToolMaterialRegistry] Can not override a registered material: " + identifier.toString() + " load updated material data instead.");
				throw new IllegalArgumentException("Tried to override an already registered material: " + identifier.toString());
			}
		}

		MATERIALS.put(identifier, material);

		//save original data
		REGISTERED_MATERIAL_DATA.put(identifier, AlloygeryToolMaterial.createAlloygeryMaterialDataFromAlloygeryMaterial(material));
		if(DEFAULT_IDENTIFIER.equals(identifier))
			DEFAULT_MATERIAL = material;

		return get(identifier);
	}

	public static AlloygeryToolMaterial load(Identifier identifier, IAlloygeryToolMaterialData materialData)
	{
		if(identifier == null || materialData == null) throw new IllegalArgumentException("[AlloygeryToolMaterialRegistry] Can not load null values");

		if (MATERIALS.containsKey(identifier))
		{
			Alloygery.LOGGER.info("[AlloygeryToolMaterialRegistry] Updating existing material: " + identifier.toString());
			materialData.apply(get(identifier));
		}
		else
		{
			Alloygery.LOGGER.warn("[AlloygeryToolMaterialRegistry] Loaded material " + identifier.toString() + " was never registered.");
			LOADED_MATERIAL_DATA.add(identifier);
			MATERIALS.put(identifier, new AlloygeryToolMaterial.AlloygeryToolMaterialBuilder(materialData).build());
		}

		return get(identifier);
	}

	public static AlloygeryToolMaterial get(Identifier identifier)
	{
		if(identifier == null)
			return DEFAULT_MATERIAL;

		return MATERIALS.getOrDefault(identifier, DEFAULT_MATERIAL);
	}

	public static Identifier identify(AlloygeryToolMaterial material)
	{
		if(material == null)
			return DEFAULT_IDENTIFIER;

		return MATERIALS.inverse().getOrDefault(material, DEFAULT_IDENTIFIER);
	}

	public static Identifier getDefaultIdentifier()
	{
		return DEFAULT_IDENTIFIER;
	}

	public static void forEach(BiConsumer<Identifier, AlloygeryToolMaterial> forEachConsumer)
	{
		MATERIALS.forEach(forEachConsumer);
	}

	public static Stream<AlloygeryToolMaterial> stream()
	{
		return MATERIALS.values().stream();
	}

	public static void resetToRegisteredValues()
	{
		LOADED_MATERIAL_DATA.forEach(MATERIALS::remove);
		LOADED_MATERIAL_DATA.clear();
		REGISTERED_MATERIAL_DATA.forEach((identifier, iToolMaterialData) -> iToolMaterialData.apply(get(identifier)));
	}
}
