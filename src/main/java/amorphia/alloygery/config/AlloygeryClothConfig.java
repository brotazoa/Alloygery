package amorphia.alloygery.config;

import amorphia.alloygery.Alloygery;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder;
import me.shedaniel.clothconfig2.impl.builders.FloatFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.IntFieldBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.text.Text;

public class AlloygeryClothConfig extends AlloygeryConfig
{
	private static final String localizationString = "text.config." + Alloygery.MOD_ID;

	public static ConfigBuilder getConfigBuilder() {
		ConfigBuilder builder = ConfigBuilder.create().setTitle(Text.translatable(localizationString + ".title"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		ConfigCategory category = builder.getOrCreateCategory(Text.translatable(localizationString + ".title"));

		ALLOYGERY_CONFIG.forEach((name, value) -> {
			String translationKey = localizationString + ".option." + name;
			if (value.getType() == ConfigValue.Type.GROUP)
			{
				category.addEntry(buildSubCategory(entryBuilder, translationKey, (ConfigGroup) value.getValue()).build());
			}
			else if (value.getType() == ConfigValue.Type.BOOLEAN)
			{
				category.addEntry(buildBooleanToggle(entryBuilder, translationKey, (ConfigValue) value).build());
			}
			else if (value.getType() == ConfigValue.Type.FLOAT)
			{
				category.addEntry(buildFloatField(entryBuilder, translationKey, (ConfigValue) value).build());
			}
			else if (value.getType() == ConfigValue.Type.INTEGER)
			{
				category.addEntry(buildIntField(entryBuilder, translationKey, (ConfigValue) value).build());
			}
		});

		return builder;
	}

	static SubCategoryBuilder buildSubCategory(ConfigEntryBuilder entryBuilder, String groupName, ConfigGroup group)
	{
		SubCategoryBuilder category = entryBuilder.startSubCategory(Text.translatable(groupName));
		group.forEach((name, value) -> {
			String translationKey = groupName + '.' + name;
			if (value.getType() == ConfigValue.Type.GROUP)
			{
				category.add(buildSubCategory(entryBuilder, translationKey, (ConfigGroup) value.getValue()).build());
			}
			else if (value.getType() == ConfigValue.Type.BOOLEAN)
			{
				category.add(buildBooleanToggle(entryBuilder, translationKey, (ConfigValue) value).build());
			}
			else if (value.getType() == ConfigValue.Type.FLOAT)
			{
				category.add(buildFloatField(entryBuilder, translationKey, (ConfigValue) value).build());
			}
			else if (value.getType() == ConfigValue.Type.INTEGER)
			{
				category.add(buildIntField(entryBuilder, translationKey, (ConfigValue) value).build());
			}
		});
		return category;
	}

	static BooleanToggleBuilder buildBooleanToggle(ConfigEntryBuilder entryBuilder, String name, ConfigValue value)
	{
		return entryBuilder.startBooleanToggle(Text.translatable(name), value.getValue()).setDefaultValue(value::getDefaultValue).setSaveConsumer(value::set).requireRestart();
	}

	static FloatFieldBuilder buildFloatField(ConfigEntryBuilder entryBuilder, String name, ConfigValue value)
	{
		return entryBuilder.startFloatField(Text.translatable(name), value.getValue()).setDefaultValue(value::getDefaultValue).setSaveConsumer(value::set).requireRestart();
	}

	static IntFieldBuilder buildIntField(ConfigEntryBuilder entryBuilder, String name, ConfigValue value)
	{
		return entryBuilder.startIntField(Text.translatable(name), value.getValue()).setDefaultValue(value::getDefaultValue).setSaveConsumer(value::set).requireRestart();
	}
}
