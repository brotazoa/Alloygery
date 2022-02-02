package amorphia.alloygery.config;

public class ConfigValue
{
	private final Object defaultValue;
	private Object value;
	private final Type type;

	public ConfigValue(Type type, Object value)
	{
		this.type = type;
		this.defaultValue = value;
		this.value = value;
	}

	public ConfigValue(boolean value)
	{
		this.type = Type.BOOLEAN;
		this.defaultValue = value;
		this.value = value;
	}

	public ConfigValue(float value)
	{
		this.type = Type.FLOAT;
		this.defaultValue = value;
		this.value = value;
	}

	public ConfigValue(ConfigGroup value)
	{
		this.type = Type.GROUP;
		this.defaultValue = value;
		this.value = value;
	}

	public ConfigValue(int value)
	{
		this.type = Type.INTEGER;
		this.defaultValue = value;
		this.value = value;
	}

	public ConfigValue()
	{
		this.type = Type.NULL;
		this.defaultValue = null;
		this.value = null;
	}

	public <T> T getDefaultValue()
	{
		return (T) defaultValue;
	}

	public <T> T getValue()
	{
		return (T) value;
	}

	public Type getType()
	{
		return this.type;
	}

	public boolean isDefault()
	{
		return this.defaultValue == this.value;
	}

	public void set(boolean value)
	{
		assert this.type == Type.BOOLEAN;
		this.value = value;
	}

	public void set(float value)
	{
		assert this.type == Type.FLOAT;
		this.value = value;
	}

	public void set(int value)
	{
		assert this.type == Type.INTEGER;
		this.value = value;
	}

	public enum Type
	{
		BOOLEAN,
		FLOAT,
		GROUP,
		INTEGER,
		NULL,
		;
	}
}
