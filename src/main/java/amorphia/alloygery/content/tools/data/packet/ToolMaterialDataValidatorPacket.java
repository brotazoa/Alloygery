package amorphia.alloygery.content.tools.data.packet;

import amorphia.alloygery.content.tools.data.IToolMaterialData;
import amorphia.alloygery.content.tools.data.IToolMaterialDataValidator;
import com.google.gson.JsonObject;

public class ToolMaterialDataValidatorPacket implements IToolMaterialDataValidator
{
	@Override
	public boolean validate(JsonObject jsonObject)
	{
		if(jsonObject == null || jsonObject.isJsonNull())
			return false;

		return jsonObject.has("alloygery_packet") && jsonObject.get("alloygery_packet").getAsString().equals("tool_material");
	}

	@Override
	public Class<? extends IToolMaterialData> getDataVersionClass()
	{
		return ToolMaterialDataPacket.class;
	}
}
