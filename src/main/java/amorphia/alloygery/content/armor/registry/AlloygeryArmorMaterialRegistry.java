package amorphia.alloygery.content.armor.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.data.IAlloygeryArmorMaterialData;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class AlloygeryArmorMaterialRegistry
{
	private static final BiMap<Identifier, AlloygeryArmorMaterial> MATERIALS = HashBiMap.create();

	private static final Map<Identifier, IAlloygeryArmorMaterialData> REGISTERED_MATERIAL_DATA = Maps.newHashMap();
	private static final List<Identifier> LOADED_MATERIAL_DATA = Lists.newArrayList();

	private static final Identifier DEFAULT_IDENTIFIER = Alloygery.identifier("armor_materials/unknown");
	private static AlloygeryArmorMaterial DEFAULT_MATERIAL = null;

	public static AlloygeryArmorMaterial register(Identifier identifier, AlloygeryArmorMaterial material)
	{
		if(identifier == null || material == null)
			throw new IllegalArgumentException("[AlloygeryArmorMaterialRegistry] Can not register null values");

		Alloygery.LOGGER.info("[AlloygeryArmorMaterialRegistry] Registering Material: " + identifier.toString());

		if (MATERIALS.containsKey(identifier))
		{
			if (MATERIALS.get(identifier) == material)
			{
				Alloygery.LOGGER.error("[AlloygeryArmorMaterialRegistry] Tried to register a material twice " + identifier.toString());
				throw new IllegalArgumentException("Can not register same material twice: " + identifier.toString());
			}
			else
			{
				Alloygery.LOGGER.error("[AlloygeryArmorMaterialRegistry] Can not override a registered material: " + identifier.toString() + " load undated material data instead.");
				throw new IllegalArgumentException("Tried to override an already registered material: " + identifier.toString());
			}
		}

		MATERIALS.put(identifier, material);

		REGISTERED_MATERIAL_DATA.put(identifier, AlloygeryArmorMaterial.createArmorMaterialDataFromArmorMaterial(material));
		if(DEFAULT_IDENTIFIER.equals(identifier))
			DEFAULT_MATERIAL = material;

		return get(identifier);
	}

	public static AlloygeryArmorMaterial load(Identifier identifier, IAlloygeryArmorMaterialData materialData)
	{
		if(identifier == null || materialData == null)
			throw new IllegalArgumentException("[AlloygeryArmorMaterialRegistry] Can not load null values");

		if (MATERIALS.containsKey(identifier))
		{
			Alloygery.LOGGER.info("[AlloygeryArmorMaterialRegistry] Updating existing material: " + identifier.toString());
			materialData.apply(get(identifier));
		}
		else
		{
			Alloygery.LOGGER.warn("[AlloygeryArmorMaterialRegistry] Loaded material " + identifier.toString() + " was never registered.");
			LOADED_MATERIAL_DATA.add(identifier);
			MATERIALS.put(identifier, new AlloygeryArmorMaterial.AlloygeryArmorMaterialBuilder(materialData).build());
		}

		return get(identifier);
	}

	public static AlloygeryArmorMaterial get(Identifier identifier)
	{
		if(identifier == null)
			return DEFAULT_MATERIAL;

		return MATERIALS.getOrDefault(identifier, DEFAULT_MATERIAL);
	}

	public static Identifier identify(AlloygeryArmorMaterial material)
	{
		if(material == null)
			return DEFAULT_IDENTIFIER;

		return MATERIALS.inverse().getOrDefault(material, DEFAULT_IDENTIFIER);
	}

	public static Identifier getDefaultIdentifier()
	{
		return DEFAULT_IDENTIFIER;
	}

	public static void forEach(BiConsumer<Identifier, AlloygeryArmorMaterial> forEachConsumer)
	{
		MATERIALS.forEach(forEachConsumer);
	}

	public static void resetToRegisteredValues()
	{
		LOADED_MATERIAL_DATA.forEach(MATERIALS::remove);
		LOADED_MATERIAL_DATA.clear();
		REGISTERED_MATERIAL_DATA.forEach((identifier, materialData) -> materialData.apply(get(identifier)));
	}
}
