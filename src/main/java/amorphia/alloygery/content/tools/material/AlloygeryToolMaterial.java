package amorphia.alloygery.content.tools.material;

import amorphia.alloygery.content.tools.data.IAlloygeryToolMaterialData;
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

public class AlloygeryToolMaterial
{
	private static final List<ToolProperty> EMPTY_TOOL_PROPERTIES = List.of();

	private String materialName = "unknown";
	private int materialColor = 16253176;

	private Ingredient repairIngredient = Ingredient.EMPTY;

	private Ingredient upgradeIngredient = Ingredient.EMPTY;

	private final List<ToolProperty> toolProperties = Lists.newArrayList();
	private final Map<ToolPartType, List<ToolProperty>> toolPropertiesByPart = Maps.newHashMap();

	private AlloygeryToolMaterial(){} //no op

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

	public Ingredient getUpgradeIngredient()
	{
		return upgradeIngredient;
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

	private AlloygeryToolMaterial copy()
	{
		AlloygeryToolMaterial copy = new AlloygeryToolMaterial();
		copy.materialName = this.materialName;
		AlloygeryToolMaterialMerger.override(copy, this);
		return copy;
	}

	public static IAlloygeryToolMaterialData createAlloygeryMaterialDataFromAlloygeryMaterial(AlloygeryToolMaterial material)
	{
		return new IAlloygeryToolMaterialData() {

			final AlloygeryToolMaterial dataHolder = material.copy();
			@Override
			public AlloygeryToolMaterial apply(AlloygeryToolMaterial applyToMaterial)
			{
				return AlloygeryToolMaterialMerger.override(applyToMaterial, dataHolder);
			}
		};
	}

	public static class AlloygeryToolMaterialMerger
	{
		public static AlloygeryToolMaterial override(AlloygeryToolMaterial original, AlloygeryToolMaterial other)
		{
			if(other == null || original == other)
				return original;

//			if(!original.materialName.equals(other.materialName))
//				return original;

			original.toolProperties.clear();
			original.toolPropertiesByPart.clear();

			original.materialName = other.materialName;
			original.materialColor = other.materialColor;
			original.repairIngredient = other.repairIngredient;
			original.upgradeIngredient = other.upgradeIngredient;

			other.toolProperties.forEach(original::addToolProperty);

			return original;
		}

		public static AlloygeryToolMaterial setData(AlloygeryToolMaterial original, AlloygeryToolMaterial data)
		{
			if(data == null || original == data)
				return original;

			original.materialName = data.materialName;

			//FIXME: material color is not data, and should be handled on client
			original.materialColor = data.materialColor;

			original.repairIngredient = data.repairIngredient;
			original.upgradeIngredient = data.upgradeIngredient;

			original.toolProperties.clear();
			original.toolPropertiesByPart.clear();

			data.toolProperties.forEach(original::addToolProperty);

			return original;
		}

		public static AlloygeryToolMaterial merge(AlloygeryToolMaterial original, AlloygeryToolMaterial other)
		{
			if(other == null || original == other)
				return original;

			if(!original.materialName.equals(other.materialName))
				return original;

			other.toolProperties.forEach(original::addToolProperty);

			return original;
		}
	}

	public static class AlloygeryToolMaterialBuilder
	{
		private final AlloygeryToolMaterial material;

		public AlloygeryToolMaterialBuilder(IAlloygeryToolMaterialData materialData)
		{
			this.material = materialData.apply(new AlloygeryToolMaterial());
		}

		public AlloygeryToolMaterialBuilder(String name)
		{
			this.material = new AlloygeryToolMaterial();
			this.material.materialName = name;
		}

		public AlloygeryToolMaterialBuilder color(int color)
		{
			this.material.materialColor = color;
			return this;
		}

		public AlloygeryToolMaterialBuilder repairIngredientFromTag(String tagString)
		{
			JsonObject tag = new JsonObject();
			tag.addProperty("tag", tagString);
			return repairIngredientFromIngredient(Ingredient.fromJson(tag));
		}

		public AlloygeryToolMaterialBuilder repairIngredientFromTag(TagKey<Item> tag)
		{
			return repairIngredientFromIngredient(Ingredient.fromTag(tag));
		}

		public AlloygeryToolMaterialBuilder repairIngredientFromItem(String itemString)
		{
			JsonObject item = new JsonObject();
			item.addProperty("item", itemString);
			return repairIngredientFromIngredient(Ingredient.fromJson(item));
		}

		public AlloygeryToolMaterialBuilder repairIngredientFromItem(ItemConvertible item)
		{
			return repairIngredientFromIngredient(Ingredient.ofItems(item));
		}

		public AlloygeryToolMaterialBuilder repairIngredientFromIngredient(Ingredient ingredient)
		{
			this.material.repairIngredient = ingredient == null ? Ingredient.EMPTY : ingredient;
			return this;
		}

		public AlloygeryToolMaterialBuilder upgradeIngredientFromTag(String tagString)
		{
			JsonObject tag = new JsonObject();
			tag.addProperty("tag", tagString);
			return upgradeIngredientFromIngredient(Ingredient.fromJson(tag));
		}

		public AlloygeryToolMaterialBuilder upgradeIngredientFromTag(TagKey<Item> tag)
		{
			return upgradeIngredientFromIngredient(Ingredient.fromTag(tag));
		}

		public AlloygeryToolMaterialBuilder upgradeIngredientFromItem(String itemString)
		{
			JsonObject item = new JsonObject();
			item.addProperty("item", itemString);
			return upgradeIngredientFromIngredient(Ingredient.fromJson(item));
		}

		public AlloygeryToolMaterialBuilder upgradeIngredientFromItem(ItemConvertible item)
		{
			return upgradeIngredientFromIngredient(Ingredient.ofItems(item));
		}

		public AlloygeryToolMaterialBuilder upgradeIngredientFromIngredient(Ingredient ingredient)
		{
			this.material.upgradeIngredient = ingredient == null ? Ingredient.EMPTY : ingredient;
			return this;
		}

		public ToolPropertyBuilder toolProperty()
		{
			return new ToolPropertyBuilder(this);
		}

		public AlloygeryToolMaterial build()
		{
			return material;
		}
	}

	public static class ToolPropertyBuilder
	{
		private final AlloygeryToolMaterialBuilder materialBuilder;

		private ToolPartType partType = null;
		private ToolPropertyType propertyType = null;
		private ToolPropertyOperation operation = ToolPropertyOperation.ADDITION;
		private float value = 0.0f;

		ToolPropertyBuilder(AlloygeryToolMaterialBuilder materialBuilder)
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

		public AlloygeryToolMaterialBuilder build()
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
}
