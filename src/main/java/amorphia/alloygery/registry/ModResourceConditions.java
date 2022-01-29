package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class ModResourceConditions
{
	public static final Identifier CONFIG_BOOLEAN_CONDITION = Alloygery.identifier("config_boolean_condition");

	public static void register()
	{
		ResourceConditions.register(CONFIG_BOOLEAN_CONDITION, ModResourceConditions::configBooleanConditionImpl);
	}

	public static boolean configBooleanConditionImpl(JsonObject json)
	{
		return Alloygery.CONFIG.getBooleanValue(JsonHelper.getString(json, "config"), false);
	}
}
