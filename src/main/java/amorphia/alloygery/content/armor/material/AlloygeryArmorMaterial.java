package amorphia.alloygery.content.armor.material;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.data.IAlloygeryArmorMaterialData;
import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.ArmorType;
import amorphia.alloygery.content.armor.property.ArmorProperty;
import amorphia.alloygery.content.armor.property.ArmorPropertyOperation;
import amorphia.alloygery.content.armor.property.ArmorPropertyType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AlloygeryArmorMaterial
{
	private static final List<ArmorProperty> EMPTY_ARMOR_PROPERTIES = List.of();
	private static final Identifier EMPTY_ITEM_TEXTURE = Alloygery.identifier("armor/parts/empty");
	private static final Identifier EMPTY_MODEL_TEXTURE = Alloygery.identifier("textures/armor/model/empty.png");

	private String materialName = "unknown";
	private int materialColor = 16253176;

	private ArmorLayer layer = ArmorLayer.BASE;

	private boolean dyeable = false;

	private Ingredient repairIngredient = Ingredient.EMPTY;

	private final List<ArmorProperty> armorProperties = Lists.newArrayList();
	private final Map<ArmorLayer, List<ArmorProperty>> armorPropertiesByLayer = Maps.newHashMap();

	private final Map<ArmorType, Identifier> itemTextures = Maps.newEnumMap(ArmorType.class);
	private final Map<ArmorType, Identifier> modelTextures = Maps.newEnumMap(ArmorType.class);

	private AlloygeryArmorMaterial() {} //no op

	public String getMaterialName()
	{
		return this.materialName;
	}

	public int getMaterialColor()
	{
		return this.materialColor;
	}

	public ArmorLayer getLayer()
	{
		return layer;
	}

	public boolean isDyeable()
	{
		return this.dyeable;
	}

	public Ingredient getRepairIngredient()
	{
		return this.repairIngredient;
	}

	public Identifier getItemTexture(ArmorType armorType)
	{
		return itemTextures.getOrDefault(armorType, EMPTY_ITEM_TEXTURE);
	}

	public Identifier getModelTexture(ArmorType armorType)
	{
		return modelTextures.getOrDefault(armorType, EMPTY_MODEL_TEXTURE);
	}

	public List<ArmorProperty> getArmorProperties()
	{
		return Collections.unmodifiableList(armorProperties);
	}

	public List<ArmorProperty> getArmorPropertiesByLayer(ArmorLayer layer)
	{
		final List<ArmorProperty> properties = armorPropertiesByLayer.get(layer);
		return Collections.unmodifiableList(properties == null ? EMPTY_ARMOR_PROPERTIES : properties);
	}

	private void addArmorProperty(ArmorProperty property)
	{
		if(property == null)
			return;

		ArmorProperty duplicate = this.getArmorProperties().stream().filter(armorProperty -> armorProperty.layer().equals(property.layer()) && armorProperty.type().equals(property.type())).findFirst().orElse(null);
		if (duplicate != null)
		{
			this.armorProperties.remove(duplicate);
			List<ArmorProperty> propertiesForLayer = this.armorPropertiesByLayer.computeIfAbsent(property.layer(), list -> Lists.newArrayList());
			propertiesForLayer.remove(duplicate);
		}

		this.armorProperties.add(property);
		List<ArmorProperty> propertiesForLayer = this.armorPropertiesByLayer.computeIfAbsent(property.layer(), list -> Lists.newArrayList());
		propertiesForLayer.add(property);
	}

	private void addArmorProperty(ArmorLayer layer, ArmorPropertyType type, ArmorPropertyOperation operation, float value)
	{
		assert layer != null && type != null && operation != null;
		addArmorProperty(new ArmorProperty(layer, type, operation, value));
	}

	private AlloygeryArmorMaterial copy()
	{
		AlloygeryArmorMaterial copy = new AlloygeryArmorMaterial();
		copy.materialName = this.materialName;
		AlloygeryArmorMaterialMerger.override(copy, this);
		return copy;
	}

	public static IAlloygeryArmorMaterialData createArmorMaterialDataFromArmorMaterial(AlloygeryArmorMaterial material)
	{
		return new IAlloygeryArmorMaterialData() {

			final AlloygeryArmorMaterial dataHolder = material.copy();
			@Override
			public AlloygeryArmorMaterial apply(AlloygeryArmorMaterial applyToMaterial)
			{
				return AlloygeryArmorMaterialMerger.override(applyToMaterial, dataHolder);
			}
		};
	}

	public static class AlloygeryArmorMaterialMerger
	{
		public static AlloygeryArmorMaterial override(AlloygeryArmorMaterial original, AlloygeryArmorMaterial other)
		{
			if(other == null || original == other)
				return original;

			if(!original.materialName.equals(other.materialName))
				return original;

			original.armorProperties.clear();
			original.armorPropertiesByLayer.clear();
			original.itemTextures.clear();
			original.modelTextures.clear();

			original.materialColor = other.materialColor;
			original.dyeable = other.dyeable;
			original.repairIngredient = other.repairIngredient;
			original.layer = other.layer;

			original.itemTextures.putAll(other.itemTextures);
			original.modelTextures.putAll(other.modelTextures);

			other.armorProperties.forEach(original::addArmorProperty);

			return original;
		}

		public static AlloygeryArmorMaterial overrideData(AlloygeryArmorMaterial original, AlloygeryArmorMaterial other)
		{
			if(other == null || original == other)
				return original;

			if(!original.materialName.equals(other.materialName))
				return original;

			original.armorProperties.clear();
			original.armorPropertiesByLayer.clear();

			original.dyeable = other.dyeable;
			original.repairIngredient = other.repairIngredient;
			original.layer = other.layer;

			other.armorProperties.forEach(original::addArmorProperty);

			return original;
		}

		public static AlloygeryArmorMaterial merge(AlloygeryArmorMaterial original, AlloygeryArmorMaterial other)
		{
			if(other == null || original == other)
				return original;

			if(!original.materialName.equals(other.materialName))
				return original;

			other.armorProperties.forEach(original::addArmorProperty);

			return original;
		}
	}

	public static class AlloygeryArmorMaterialBuilder
	{
		private final AlloygeryArmorMaterial material;

		public static AlloygeryArmorMaterialBuilder fromMaterial(AlloygeryArmorMaterial material)
		{
			return new AlloygeryArmorMaterialBuilder(material);
		}

		public static AlloygeryArmorMaterialBuilder fromMaterialData(IAlloygeryArmorMaterialData materialData)
		{
			return new AlloygeryArmorMaterialBuilder(materialData);
		}

		public static AlloygeryArmorMaterialBuilder fromName(String name)
		{
			return new AlloygeryArmorMaterialBuilder(name);
		}

		public AlloygeryArmorMaterialBuilder(AlloygeryArmorMaterial material)
		{
			this.material = material.copy();
		}

		public AlloygeryArmorMaterialBuilder(IAlloygeryArmorMaterialData materialData)
		{
			this.material = materialData.apply(new AlloygeryArmorMaterial());
		}

		public AlloygeryArmorMaterialBuilder(String name)
		{
			this.material = new AlloygeryArmorMaterial();
			this.material.materialName = name;
		}

		public AlloygeryArmorMaterialBuilder name(String name)
		{
			this.material.materialName = name;
			return this;
		}

		public AlloygeryArmorMaterialBuilder layer(ArmorLayer layer)
		{
			this.material.layer = layer;
			return this;
		}

		public AlloygeryArmorMaterialBuilder color(int color)
		{
			this.material.materialColor = color;
			return this;
		}

		public AlloygeryArmorMaterialBuilder dyeable(boolean dyeable)
		{
			this.material.dyeable = dyeable;
			return this;
		}

		public AlloygeryArmorMaterialBuilder repairIngredientFromTag(String tagString)
		{
			JsonObject tag = new JsonObject();
			tag.addProperty("tag", tagString);
			return repairIngredientFromIngredient(Ingredient.fromJson(tag));
		}

		public AlloygeryArmorMaterialBuilder repairIngredientFromTag(TagKey<Item> tag)
		{
			return repairIngredientFromIngredient(Ingredient.fromTag(tag));
		}

		public AlloygeryArmorMaterialBuilder repairIngredientFromItem(String itemString)
		{
			JsonObject item = new JsonObject();
			item.addProperty("item", itemString);
			return repairIngredientFromIngredient(Ingredient.fromJson(item));
		}

		public AlloygeryArmorMaterialBuilder repairIngredientFromItem(ItemConvertible item)
		{
			return repairIngredientFromIngredient(Ingredient.ofItems(item));
		}

		public AlloygeryArmorMaterialBuilder repairIngredientFromIngredient(Ingredient ingredient)
		{
			this.material.repairIngredient = ingredient == null ? Ingredient.EMPTY : ingredient;
			return this;
		}

		public AlloygeryArmorMaterialBuilder itemTexture(ArmorType armorType, Identifier texture)
		{
			this.material.itemTextures.put(armorType, texture);
			return this;
		}

		public AlloygeryArmorMaterialBuilder modelTexture(ArmorType armorType, Identifier texture)
		{
			this.material.modelTextures.put(armorType, texture);
			return this;
		}

		public ArmorPropertyBuilder armorProperty()
		{
			return new ArmorPropertyBuilder(this);
		}

		public AlloygeryArmorMaterial build()
		{
			return material;
		}
	}

	public static class ArmorPropertyBuilder
	{
		private final AlloygeryArmorMaterialBuilder materialBuilder;

		private ArmorLayer layer = null;
		private ArmorPropertyType type = null;
		private ArmorPropertyOperation operation = ArmorPropertyOperation.ADDITION;
		private float value = 0.0f;

		ArmorPropertyBuilder(AlloygeryArmorMaterialBuilder materialBuilder)
		{
			this.materialBuilder = materialBuilder;
		}

		public ArmorPropertyBuilder forLayer(ArmorLayer layer)
		{
			this.layer = layer;
			return this;
		}

		public ArmorPropertyBuilder property(ArmorPropertyType type, float value)
		{
			property(type);
			value(value);
			return this;
		}

		public ArmorPropertyBuilder property(ArmorPropertyType type)
		{
			this.type = type;
			return this;
		}

		public ArmorPropertyBuilder operation(ArmorPropertyOperation operation)
		{
			this.operation = operation;
			return this;
		}

		public ArmorPropertyBuilder value(float value)
		{
			this.value = value;
			return this;
		}

		public ArmorPropertyBuilder makeProperty(ArmorPropertyType type, float value)
		{
			this.property(type, value);
			return next();
		}

		public ArmorPropertyBuilder next()
		{
			if(type == null || layer == null)
				return this;

			if((operation == ArmorPropertyOperation.MULTIPLY_BASE || operation == ArmorPropertyOperation.MULTIPLY_TOTAL) && value == 1.0f)
				return this;

			if((operation == ArmorPropertyOperation.ADDITION || operation == ArmorPropertyOperation.BASE) && value == 0.0f)
				return this;

			materialBuilder.material.addArmorProperty(layer, type, operation, value);

			type = null;
			value = 0.0f;

			return this;
		}

		public AlloygeryArmorMaterialBuilder build()
		{
			next();
			return materialBuilder;
		}
	}
}
