package amorphia.alloygery.content.materials;

import amorphia.alloygery.content.armor.item.ArmorPartType;
import amorphia.alloygery.content.armor.property.ArmorProperty;
import amorphia.alloygery.content.armor.property.ArmorPropertyOperation;
import amorphia.alloygery.content.armor.property.ArmorPropertyType;
import amorphia.alloygery.content.materials.data.IAlloygeryMaterialData;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.tools.property.ToolProperty;
import amorphia.alloygery.content.tools.property.ToolPropertyOperation;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.TagKey;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AlloygeryMaterial
{
	private static final List<ToolProperty> EMPTY_TOOL_PROPERTIES = List.of();
	private static final List<ArmorProperty> EMPTY_ARMOR_PROPERTIES = List.of();

	private String materialName = "unknown";
	private int materialColor = 16253176;

	private Ingredient repairIngredient = Ingredient.EMPTY;

	private final List<ToolProperty> toolProperties = Lists.newArrayList();
	private final Map<ToolPartType, List<ToolProperty>> toolPropertiesByPart = Maps.newHashMap();

	private final List<ArmorProperty> armorProperties = Lists.newArrayList();
	private final Map<ArmorPartType, List<ArmorProperty>> armorPropertiesByPart = Maps.newHashMap();

	private AlloygeryMaterial(){} //no op

	public String getMaterialName()
	{
		return materialName;
	}

	public int getMaterialColor()
	{
		return materialColor;
	}

	public Ingredient getRepairIngredient()
	{
		return repairIngredient;
	}

	public List<ToolProperty> getToolProperties()
	{
		return Collections.unmodifiableList(toolProperties);
	}

	public List<ToolProperty> getToolPropertiesByPart(ToolPartType partType)
	{
		final List<ToolProperty> properties = toolPropertiesByPart.get(partType);

		return Collections.unmodifiableList(properties == null ? EMPTY_TOOL_PROPERTIES : properties);
	}

	public List<ArmorProperty> getArmorProperties()
	{
		return Collections.unmodifiableList(armorProperties);
	}

	public List<ArmorProperty> getArmorPropertiesByPart(ArmorPartType partType)
	{
		final List<ArmorProperty> properties = armorPropertiesByPart.get(partType);

		return Collections.unmodifiableList(properties == null ? EMPTY_ARMOR_PROPERTIES : properties);
	}

	private void addToolProperty(ToolProperty property)
	{
		if(property == null)
			return;

		//check for duplicate part and operation combination
		ToolProperty duplicate = this.getToolProperties().stream().filter(toolProperty -> toolProperty.partType().equals(property.partType()) && toolProperty.type().equals(property.type())).findFirst().orElse(null);
		if(duplicate != null)
		{
			this.toolProperties.remove(duplicate);
			List<ToolProperty> propertiesForPart = this.toolPropertiesByPart.computeIfAbsent(property.partType(), list -> Lists.newArrayList());
			propertiesForPart.remove(duplicate);
		}

		this.toolProperties.add(property);

		List<ToolProperty> propertiesForPart = this.toolPropertiesByPart.computeIfAbsent(property.partType(), list -> Lists.newArrayList());
		propertiesForPart.add(property);
	}

	private void addToolProperty(ToolPartType partType, ToolPropertyType propertyType, ToolPropertyOperation operation, float value)
	{
		assert partType != null && propertyType != null && operation != null;
		addToolProperty(new ToolProperty(partType, propertyType, operation, value));
	}

	private void addArmorProperty(ArmorProperty property)
	{
		if(property == null)
			return;

		//check for duplicate part and operation combination
		final ArmorProperty duplicate = this.armorProperties.stream().filter(armorProperty -> armorProperty.partType().equals(property.partType()) && armorProperty.type().equals(property.type())).findFirst().orElse(null);
		if(duplicate != null)
		{
			this.armorProperties.remove(duplicate);
			List<ArmorProperty> propertiesForPart = this.armorPropertiesByPart.computeIfAbsent(property.partType(), list -> Lists.newArrayList());
			propertiesForPart.remove(duplicate);
		}

		this.armorProperties.add(property);

		List<ArmorProperty> propertiesForPart = armorPropertiesByPart.computeIfAbsent(property.partType(), list -> Lists.newArrayList());
		propertiesForPart.add(property);
	}

	private void addArmorProperty(ArmorPartType partType, ArmorPropertyType propertyType, ArmorPropertyOperation operation, float value)
	{
		assert partType != null && propertyType != null && operation != null;
		addArmorProperty(new ArmorProperty(partType, propertyType, operation, value));
	}

	private AlloygeryMaterial copy()
	{
		AlloygeryMaterial copy = new AlloygeryMaterial();
		copy.materialName = this.materialName;
		AlloygeryMaterialMerger.override(copy, this);
		return copy;
	}

	public static IAlloygeryMaterialData createAlloygeryMaterialDataFromAlloygeryMaterial(AlloygeryMaterial material)
	{
		return new IAlloygeryMaterialData() {

			final AlloygeryMaterial dataHolder = material.copy();
			@Override
			public AlloygeryMaterial apply(AlloygeryMaterial applyToMaterial)
			{
				return AlloygeryMaterialMerger.override(applyToMaterial, dataHolder);
			}
		};
	}

	public static class AlloygeryMaterialMerger
	{
		public static AlloygeryMaterial override(AlloygeryMaterial original, AlloygeryMaterial other)
		{
			if(other == null || original == other)
				return original;

			if(!original.materialName.equals(other.materialName))
				return original;

			original.toolProperties.clear();
			original.toolPropertiesByPart.clear();

			original.armorProperties.clear();
			original.armorPropertiesByPart.clear();

			original.materialColor = other.materialColor;
			original.repairIngredient = other.repairIngredient;

			other.toolProperties.forEach(original::addToolProperty);

			other.armorProperties.forEach(original::addArmorProperty);

			return original;
		}

		public static AlloygeryMaterial merge(AlloygeryMaterial original, AlloygeryMaterial other)
		{
			if(other == null || original == other)
				return original;

			if(!original.materialName.equals(other.materialName))
				return original;

			other.toolProperties.forEach(original::addToolProperty);
			other.armorProperties.forEach(original::addArmorProperty);

			return original;
		}
	}

	public static class AlloygeryMaterialBuilder
	{
		private final AlloygeryMaterial material;

		public AlloygeryMaterialBuilder(IAlloygeryMaterialData materialData)
		{
			this.material = materialData.apply(new AlloygeryMaterial());
		}

		public AlloygeryMaterialBuilder(String name)
		{
			this.material = new AlloygeryMaterial();
			this.material.materialName = name;
		}

		public AlloygeryMaterialBuilder color(int color)
		{
			this.material.materialColor = color;
			return this;
		}

		public AlloygeryMaterialBuilder repairIngredientFromTag(String tagString)
		{
			JsonObject tag = new JsonObject();
			tag.addProperty("tag", tagString);
			return repairIngredientFromIngredient(Ingredient.fromJson(tag));
		}

		public AlloygeryMaterialBuilder repairIngredientFromTag(TagKey<Item> tag)
		{
			return repairIngredientFromIngredient(Ingredient.fromTag(tag));
		}

		public AlloygeryMaterialBuilder repairIngredientFromItem(String itemString)
		{
			JsonObject item = new JsonObject();
			item.addProperty("item", itemString);
			return repairIngredientFromIngredient(Ingredient.fromJson(item));
		}

		public AlloygeryMaterialBuilder repairIngredientFromItem(ItemConvertible item)
		{
			return repairIngredientFromIngredient(Ingredient.ofItems(item));
		}

		public AlloygeryMaterialBuilder repairIngredientFromIngredient(Ingredient ingredient)
		{
			this.material.repairIngredient = ingredient == null ? Ingredient.EMPTY : ingredient;
			return this;
		}

		public ToolPropertyBuilder toolProperty()
		{
			return new ToolPropertyBuilder(this);
		}

		public ArmorPropertyBuilder armorProperty()
		{
			return new ArmorPropertyBuilder(this);
		}

		public AlloygeryMaterial build()
		{
			return material;
		}
	}

	public static class ToolPropertyBuilder
	{
		private final AlloygeryMaterialBuilder materialBuilder;

		private ToolPartType partType = null;
		private ToolPropertyType propertyType = null;
		private ToolPropertyOperation operation = ToolPropertyOperation.ADDITION;
		private float value = 0.0f;

		ToolPropertyBuilder(AlloygeryMaterialBuilder materialBuilder)
		{
			this.materialBuilder = materialBuilder;
		}

		public ToolPropertyBuilder forPart(ToolPartType partType)
		{
			this.partType = partType;
			return this;
		}

		public ToolPropertyBuilder property(ToolPropertyType propertyType, float value)
		{
			property(propertyType);
			value(value);
			return this;
		}

		public ToolPropertyBuilder property(ToolPropertyType propertyType)
		{
			this.propertyType = propertyType;
			return this;
		}

		public ToolPropertyBuilder operation(ToolPropertyOperation operation)
		{
			this.operation = operation;
			return this;
		}

		public ToolPropertyBuilder value(float value)
		{
			this.value = value;
			return this;
		}

		//bulk build, adds a property to the material and retains all the values in the builder
		public ToolPropertyBuilder next()
		{
			if(propertyType == null || partType == null)
				return this;

			if((operation == ToolPropertyOperation.MULTIPLY_BASE || operation == ToolPropertyOperation.MULTIPLY_TOTAL) && value == 1.0f)
				return this;

			if((operation == ToolPropertyOperation.ADDITION || operation == ToolPropertyOperation.BASE) && value == 0.0f)
				return this;

			materialBuilder.material.addToolProperty(partType, propertyType, operation, value);

			return this;
		}

		public AlloygeryMaterialBuilder build()
		{
			if(propertyType == null || partType == null)
				return materialBuilder;

			if((operation == ToolPropertyOperation.MULTIPLY_BASE || operation == ToolPropertyOperation.MULTIPLY_TOTAL) && value == 1.0f)
				return materialBuilder;

			if(operation == ToolPropertyOperation.ADDITION && value == 0.0f)
				return materialBuilder;

			materialBuilder.material.addToolProperty(partType, propertyType, operation, value);

			return materialBuilder;
		}
	}

	public static class ArmorPropertyBuilder
	{
		private final AlloygeryMaterialBuilder materialBuilder;

		private ArmorPartType partType = null;
		private ArmorPropertyType propertyType = null;
		private ArmorPropertyOperation operation = ArmorPropertyOperation.ADDITION;
		private float value = 0.0f;

		ArmorPropertyBuilder(AlloygeryMaterialBuilder materialBuilder)
		{
			this.materialBuilder = materialBuilder;
		}

		public ArmorPropertyBuilder forPart(ArmorPartType partType)
		{
			this.partType = partType;
			return this;
		}

		public ArmorPropertyBuilder property(ArmorPropertyType propertyType, float value)
		{
			property(propertyType);
			value(value);
			return this;
		}

		public ArmorPropertyBuilder property(ArmorPropertyType propertyType)
		{
			this.propertyType = propertyType;
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

		public ArmorPropertyBuilder next()
		{
			if(propertyType == null || partType == null)
				return this;

			if((operation == ArmorPropertyOperation.MULTIPLY_BASE || operation == ArmorPropertyOperation.MULTIPLY_TOTAL) && value == 1.0f)
				return this;

			if((operation == ArmorPropertyOperation.ADDITION || operation == ArmorPropertyOperation.BASE) && value == 0.0f)
				return this;

			materialBuilder.material.addArmorProperty(partType, propertyType, operation, value);

			return this;
		}

		public AlloygeryMaterialBuilder build()
		{
			return next().materialBuilder;
		}
	}
}
