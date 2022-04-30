package amorphia.alloygery.content.material;

import amorphia.alloygery.Alloygery;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class AlloygeryMaterialRegistry
{
	private static final BiMap<Identifier, AlloygeryMaterial> MATERIALS = HashBiMap.create();

	private static final Map<Identifier, AlloygeryMaterialData> REGISTERED_MATERIAL_DATA = Maps.newHashMap();
	private static final List<Identifier> LOADED_AFTER_MATERIAL_IDENTIFIERS = Lists.newArrayList();

	private static final Identifier DEFAULT_IDENTIFIER = Alloygery.identifier("materials/unknown");
	private static AlloygeryMaterial DEFAULT_MATERIAL = null;

	public static AlloygeryMaterial register(Identifier identifier, AlloygeryMaterial material)
	{
		Alloygery.LOGGER.info("[AlloygeryMaterialRegistry] Registering Material: " + identifier);

		//overriding materials is not allowed
		if(MATERIALS.containsKey(identifier))
		{
			if(MATERIALS.get(identifier) == material)
				throw new IllegalArgumentException("Tried to register material twice: " + identifier);
			throw new IllegalArgumentException("Tried to register an already registered material: " + identifier);
		}

		//save originally registered material data
		REGISTERED_MATERIAL_DATA.put(identifier, AlloygeryMaterialData.fromAlloygeryMaterial(material));

		MATERIALS.put(identifier, material);

		if(DEFAULT_IDENTIFIER.equals(identifier))
			DEFAULT_MATERIAL = material;

		return get(identifier);
	}

	public static AlloygeryMaterial load(Identifier identifier, AlloygeryMaterialData materialData)
	{
		if (MATERIALS.containsKey(identifier))
		{
			Alloygery.LOGGER.info("[AlloygeryMaterialRegistry] Updating Existing Material: " + identifier);

			//apply the new material data to the existing material
			AlloygeryMaterialData.apply(MATERIALS.get(identifier), materialData);
		}
		else
		{
			Alloygery.LOGGER.info("[AlloygeryMaterialRegistry] Loading New Material: " + identifier);

			//add a new material
			LOADED_AFTER_MATERIAL_IDENTIFIERS.add(identifier);
			AlloygeryMaterial material = new AlloygeryMaterial();
			AlloygeryMaterialData.apply(material, materialData);
			MATERIALS.put(identifier, material);
		}

		return get(identifier);
	}

	public static AlloygeryMaterial get(Identifier identifier)
	{
		return MATERIALS.getOrDefault(identifier, DEFAULT_MATERIAL);
	}

	public static Identifier identify(AlloygeryMaterial material)
	{
		return MATERIALS.inverse().getOrDefault(material, DEFAULT_IDENTIFIER);
	}

	public static void forEach(BiConsumer<Identifier, AlloygeryMaterial> forEachConsumer)
	{
		MATERIALS.forEach(forEachConsumer);
	}

	public static void resetToRegisteredValues()
	{
		//remove all entries that were not registered, but were loaded from datapacks or server packets
		LOADED_AFTER_MATERIAL_IDENTIFIERS.forEach(MATERIALS::remove);
		LOADED_AFTER_MATERIAL_IDENTIFIERS.clear();

		//restore the originally registered data values for all registered materials
		REGISTERED_MATERIAL_DATA.forEach((identifier, data) -> AlloygeryMaterialData.apply(get(identifier), data));
	}
}
