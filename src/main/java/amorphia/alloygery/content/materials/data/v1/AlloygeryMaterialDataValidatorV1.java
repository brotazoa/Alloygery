package amorphia.alloygery.content.materials.data.v1;

import amorphia.alloygery.content.materials.data.IAlloygeryMaterialData;
import amorphia.alloygery.content.materials.data.IAlloygeryMaterialDataValidator;
import com.google.gson.JsonObject;

public class AlloygeryMaterialDataValidatorV1 implements IAlloygeryMaterialDataValidator
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
	public Class<? extends IAlloygeryMaterialData> getDataVersionClass()
	{
		return AlloygeryMaterialDataV1.class;
	}
}
