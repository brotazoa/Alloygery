package amorphia.alloygery.config;

import java.util.LinkedHashMap;

public class ConfigGroup extends LinkedHashMap<String, ConfigValue>
{
	public <T extends ConfigValue> T register(String name, T value)
	{

		//TODO: String validation
		put(name, value);
		return value;
	}

	public <T extends ConfigGroup> T register(String name, T group)
	{
		put(name, new ConfigValue(group));
		return group;
	}
}
