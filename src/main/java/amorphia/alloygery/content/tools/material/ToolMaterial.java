package amorphia.alloygery.content.tools.material;

import amorphia.alloygery.content.tools.data.IToolMaterialData;
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

public class ToolMaterial
{
	private static final List<ToolProperty> EMPTY = List.of();

	private String materialName = "unknown";
	private int materialColor = 16253176;

	private Ingredient repairIngredient = Ingredient.EMPTY;

	private final List<ToolProperty> toolProperties = Lists.newArrayList();
	private final Map<ToolPartType, List<ToolProperty>> toolPropertiesByPart = Maps.newHashMap();

	private ToolMaterial(){} //no op

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

	public List<ToolProperty> getProperties()
	{
		return Collections.unmodifiableList(toolProperties);
	}

	public List<ToolProperty> getPropertiesByPart(ToolPartType partType)
	{
		final List<ToolProperty> properties = toolPropertiesByPart.get(partType);

		return Collections.unmodifiableList(properties == null ? EMPTY : properties);
	}

	private void addProperty(ToolProperty property)
	{
		if(property == null)
			return;

		this.toolProperties.add(property);

		List<ToolProperty> propertiesForPart = toolPropertiesByPart.computeIfAbsent(property.partType(), list -> Lists.newArrayList());
		propertiesForPart.add(property);
	}

	private void addProperty(ToolPartType partType, ToolPropertyType propertyType, ToolPropertyOperation operation, float value)
	{
		assert partType != null && propertyType != null && operation != null;
		addProperty(new ToolProperty(partType, propertyType, operation, value));
	}

	private ToolMaterial copy()
	{
		ToolMaterial copy = new ToolMaterial();
		copy.materialName = this.materialName;
		ToolMaterialMerger.override(copy, this);
		return copy;
	}

	public static IToolMaterialData createToolMaterialDataFromToolMaterial(ToolMaterial material)
	{
		return new IToolMaterialData() {

			final ToolMaterial dataHolder = material.copy();
			@Override
			public ToolMaterial apply(ToolMaterial applyToMaterial)
			{
				return ToolMaterial.ToolMaterialMerger.override(applyToMaterial, dataHolder);
			}
		};
	}

	public static class ToolMaterialMerger
	{
		public static ToolMaterial override(ToolMaterial original, ToolMaterial other)
		{
			if(other == null || original == other)
				return original;

			if(!original.materialName.equals(other.materialName))
				return original;

			original.toolProperties.clear();
			original.toolPropertiesByPart.clear();

			original.materialColor = other.materialColor;
			original.repairIngredient = other.repairIngredient;
			other.toolProperties.forEach(original::addProperty);

			return original;
		}
	}

	public static class ToolMaterialBuilder
	{
		private final ToolMaterial material;

		public ToolMaterialBuilder(IToolMaterialData materialData)
		{
			this.material = materialData.apply(new ToolMaterial());
		}

		public ToolMaterialBuilder(String name)
		{
			this.material = new ToolMaterial();
			this.material.materialName = name;
		}

		public ToolMaterialBuilder color(int color)
		{
			this.material.materialColor = color;
			return this;
		}

		public ToolMaterialBuilder repairIngredientFromTag(String tagString)
		{
			JsonObject tag = new JsonObject();
			tag.addProperty("tag", tagString);
			return repairIngredientFromIngredient(Ingredient.fromJson(tag));
		}

		public ToolMaterialBuilder repairIngredientFromTag(TagKey<Item> tag)
		{
			return repairIngredientFromIngredient(Ingredient.fromTag(tag));
		}

		public ToolMaterialBuilder repairIngredientFromItem(String itemString)
		{
			JsonObject item = new JsonObject();
			item.addProperty("item", itemString);
			return repairIngredientFromIngredient(Ingredient.fromJson(item));
		}

		public ToolMaterialBuilder repairIngredientFromItem(ItemConvertible item)
		{
			return repairIngredientFromIngredient(Ingredient.ofItems(item));
		}

		public ToolMaterialBuilder repairIngredientFromIngredient(Ingredient ingredient)
		{
			this.material.repairIngredient = ingredient == null ? Ingredient.EMPTY : ingredient;
			return this;
		}

		public ToolPropertyBuilder toolProperty()
		{
			return new ToolPropertyBuilder(this);
		}

		public ToolMaterial build()
		{
			return material;
		}
	}

	public static class ToolPropertyBuilder
	{
		private final ToolMaterialBuilder materialBuilder;

		private ToolPartType partType = null;
		private ToolPropertyType propertyType = null;
		private ToolPropertyOperation operation = ToolPropertyOperation.ADDITION;
		private float value = 0.0f;

		ToolPropertyBuilder(ToolMaterialBuilder materialBuilder)
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

			materialBuilder.material.addProperty(partType, propertyType, operation, value);

			return this;
		}

		public ToolMaterialBuilder build()
		{
			if(propertyType == null || partType == null)
				return materialBuilder;

			if((operation == ToolPropertyOperation.MULTIPLY_BASE || operation == ToolPropertyOperation.MULTIPLY_TOTAL) && value == 1.0f)
				return materialBuilder;

			if(operation == ToolPropertyOperation.ADDITION && value == 0.0f)
				return materialBuilder;

			materialBuilder.material.addProperty(partType, propertyType, operation, value);

			return materialBuilder;
		}
	}
}
