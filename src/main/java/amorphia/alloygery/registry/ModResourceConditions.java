package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.config.AlloygeryConfig;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class ModResourceConditions
{
	public static final Identifier CONFIG_BOOLEAN_CONDITION = Alloygery.identifier("config_boolean_condition");
	public static final Identifier CONFIG_INTEGER_CONDITION = Alloygery.identifier("config_integer_condition");
	public static final Identifier CONFIG_FLOAT_CONDITION = Alloygery.identifier("config_float_condition");

	public static void register()
	{
		ResourceConditions.register(CONFIG_BOOLEAN_CONDITION, ModResourceConditions::configBooleanConditionImpl);
		ResourceConditions.register(CONFIG_INTEGER_CONDITION, ModResourceConditions::configIntegerConditionImpl);
		ResourceConditions.register(CONFIG_FLOAT_CONDITION, ModResourceConditions::configFloatConditionImpl);
	}

	/**
	 * @param json A {@link JsonObject} representing the resource load condition
	 * @return Returns the boolean value for the provided config
	 *
	 * <h3>Example Usage</h3>
	 * <pre>{@code
	 * {
	 *   ... // normal contents of the resource
	 *   "fabric:load_conditions": [ // array of condition objects
	 *     {
	 *       // the identifier of the condition
	 *       "condition": "alloygery:config_boolean_condition",
	 *       // the config to test. Note you can target values in inner classes by specifying the path
	 *       "config": "copper_gear.enable"
	 *       // this will make a condition where the resource is only loaded if copper gear is enabled in the config
	 *     }
	 *   ]
	 * }
	 * }</pre>
	 */
	public static boolean configBooleanConditionImpl(JsonObject json)
	{
		return AlloygeryConfig.getConfigValue(JsonHelper.getString(json, "config")).getValue();
	}

	/**
	 * @param json A {@link JsonObject} representing the resource load condition
	 * @return Returns true if the config matches the desired value
	 *
	 * <h3>Example Usage</h3>
	 * <pre>{@code
	 * {
	 *   ... // normal contents of the resource
	 *   "fabric:load_conditions": [ // array of condition objects
	 *     {
	 *       // the identifier of the condition
	 *       "condition": "alloygery:config_integer_condition",
	 *       // the config to test. Note you can target values in inner classes by specifying the path
	 *       "config": "copper_gear.mining_level",
	 *       // matches can be one of "equals", "=", "greaterThan", ">", "lessThan", ">", "greaterThanOrEqual", ">=", "lessThanOrEqual", or "<=".
	 *       "matches": "equals",
	 *       "value": 1
	 *       // this will make a condition where the resource is only loaded if copper mining level is equal to 1
	 *     }
	 *   ]
	 * }
	 * }</pre>
	 */
	public static boolean configIntegerConditionImpl(JsonObject json)
	{
		String configString = JsonHelper.getString(json, "config");
		String matchString = JsonHelper.getString(json, "matches");
		int valueInteger = JsonHelper.getInt(json, "value");

		int configValue = AlloygeryConfig.getConfigValue(configString).getValue();

		return switch (matchString)
		{
			case "equals", "=" -> configValue == valueInteger;
			case "greaterThan", ">" -> configValue > valueInteger;
			case "lessThan", "<" -> configValue < valueInteger;
			case "greaterThanOrEqual", ">=" -> configValue >= valueInteger;
			case "lessThanOrEqual", "<=" -> configValue <= valueInteger;
			default -> false;
		};
	}

	/**
	 * @param json A {@link JsonObject} representing the resource load condition
	 * @return Returns true if the config matches the desired value
	 *
	 * <h3>Example Usage</h3>
	 * <pre>{@code
	 * {
	 *   ... // normal contents of the resource
	 *   "fabric:load_conditions": [ // array of condition objects
	 *     {
	 *       // the identifier of the condition
	 *       "condition": "alloygery:config_float_condition",
	 *       // the config to test. Note you can target values in inner classes by specifying the path
	 *       "config": "copper_gear.knockback",
	 *       // matches can be one of "equals", "=", "greaterThan", ">", "lessThan", ">", "greaterThanOrEqual", ">=", "lessThanOrEqual", or "<=".
	 *       "matches": "<=",
	 *       "value": 4.0
	 *       // this will make a condition where the resource is only loaded if copper gear knockback is less than or equal to 4.0
	 *     }
	 *   ]
	 * }
	 * }</pre>
	 */
	public static boolean configFloatConditionImpl(JsonObject json)
	{
		String configString = JsonHelper.getString(json, "config");
		String matchString = JsonHelper.getString(json, "matches");
		float valueFloat = JsonHelper.getFloat(json, "value");

		float configValue = AlloygeryConfig.getConfigValue(configString).getValue();

		return switch (matchString)
		{
			case "equals", "=" -> configValue == valueFloat;
			case "greaterThan", ">" -> configValue > valueFloat;
			case "lessThan", "<" -> configValue < valueFloat;
			case "greaterThanOrEqual", ">=" -> configValue >= valueFloat;
			case "lessThanOrEqual", "<=" -> configValue <= valueFloat;
			default -> false;
		};
	}
}
