package amorphia.alloygery.content.tools.data.v1;

import amorphia.alloygery.content.tools.data.IAlloygeryToolMaterialData;
import amorphia.alloygery.content.tools.data.IAlloygeryToolMaterialDataValidator;
import com.google.gson.JsonObject;

public class AlloygeryToolMaterialDataValidatorV1 implements IAlloygeryToolMaterialDataValidator
{
	@Override
	public boolean validate(JsonObject jsonObject)
	{
		if(jsonObject == null || jsonObject.isJsonNull())
			return false;

		if (jsonObject.has("alloygery_data_version"))
		{
			int version = jsonObject.get("alloygery_data_version").getAsInt();
			return version == 1;
		}

		return jsonObject.has("name") &&
				jsonObject.has("category") &&
				jsonObject.has("color") &&
				jsonObject.has("repair_ingredient") &&
				jsonObject.has("make_tool_heads");
	}

	@Override
	public Class<? extends IAlloygeryToolMaterialData> getDataVersionClass()
	{
		return AlloygeryToolMaterialDataV1.class;
	}
}
