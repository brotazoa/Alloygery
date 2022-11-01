package amorphia.alloygery.content.materials.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.materials.data.IAlloygeryMaterialData;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class AlloygeryMaterialRegistry
{
	private static final BiMap<Identifier, AlloygeryMaterial> MATERIALS = HashBiMap.create();

	private static final Map<Identifier, IAlloygeryMaterialData> REGISTERED_MATERIAL_DATA = Maps.newHashMap();
	private static final List<Identifier> LOADED_MATERIAL_DATA = Lists.newArrayList();

	private static final Identifier DEFAULT_IDENTIFIER = Alloygery.identifier("materials/unknown");
	private static AlloygeryMaterial DEFAULT_MATERIAL = null;

	public static AlloygeryMaterial register(Identifier identifier, AlloygeryMaterial material)
	{
		if(identifier == null || material == null) throw new IllegalArgumentException("[AlloygeryMaterialRegistry] Can not register null values");

		Alloygery.LOGGER.info("[AlloygeryMaterialRegistry] Registering Material: " + identifier.toString());

		//overriding materials is not allowed
		if (MATERIALS.containsKey(identifier))
		{
			if(MATERIALS.get(identifier) == material)
			{
				Alloygery.LOGGER.error("[AlloygeryMaterialRegistry] Tried to register a material twice " + identifier.toString());
				throw new IllegalArgumentException("Can not register same material twice: " + identifier.toString());
			}
			else
			{
				Alloygery.LOGGER.error("[AlloygeryMaterialRegistry] Can not override a registered material: " + identifier.toString() + " load updated material data instead.");
				throw new IllegalArgumentException("Tried to override and already registered material: " + identifier.toString());
			}
		}

		MATERIALS.put(identifier, material);

		//save original data
		REGISTERED_MATERIAL_DATA.put(identifier, AlloygeryMaterial.createAlloygeryMaterialDataFromAlloygeryMaterial(material));
		if(DEFAULT_IDENTIFIER.equals(identifier))
			DEFAULT_MATERIAL = material;

		return get(identifier);
	}

	public static AlloygeryMaterial load(Identifier identifier, IAlloygeryMaterialData materialData)
	{
		if(identifier == null || materialData == null) throw new IllegalArgumentException("[AlloygeryMaterialRegistry] Can not load null values");

		if (MATERIALS.containsKey(identifier))
		{
			Alloygery.LOGGER.info("[AlloygeryMaterialRegistry] Updating existing material: " + identifier.toString());
			materialData.apply(get(identifier));
		}
		else
		{
			Alloygery.LOGGER.warn("[AlloygeryMaterialRegistry] Loaded material " + identifier.toString() + " was never registered.");
			LOADED_MATERIAL_DATA.add(identifier);
			MATERIALS.put(identifier, new AlloygeryMaterial.AlloygeryMaterialBuilder(materialData).build());
		}

		return get(identifier);
	}

	public static AlloygeryMaterial get(Identifier identifier)
	{
		if(identifier == null)
			return DEFAULT_MATERIAL;

		return MATERIALS.getOrDefault(identifier, DEFAULT_MATERIAL);
	}

	public static Identifier identify(AlloygeryMaterial material)
	{
		if(material == null)
			return DEFAULT_IDENTIFIER;

		return MATERIALS.inverse().getOrDefault(material, DEFAULT_IDENTIFIER);
	}

	public static Identifier getDefaultIdentifier()
	{
		return DEFAULT_IDENTIFIER;
	}

	public static void forEach(BiConsumer<Identifier, AlloygeryMaterial> forEachConsumer)
	{
		MATERIALS.forEach(forEachConsumer);
	}

	public static void resetToRegisteredValues()
	{
		LOADED_MATERIAL_DATA.forEach(MATERIALS::remove);
		LOADED_MATERIAL_DATA.clear();
		REGISTERED_MATERIAL_DATA.forEach((identifier, iToolMaterialData) -> iToolMaterialData.apply(get(identifier)));
	}
}
