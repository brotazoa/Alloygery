package amorphia.alloygery.content.tools.data.v1;

import amorphia.alloygery.content.tools.data.IToolMaterialData;
import amorphia.alloygery.content.tools.data.IToolMaterialDataValidator;
import com.google.gson.JsonObject;

public class ToolMaterialDataValidatorV1 implements IToolMaterialDataValidator
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
	public Class<? extends IToolMaterialData> getDataVersionClass()
	{
		return ToolMaterialDataV1.class;
	}
}
