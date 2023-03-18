package amorphia.alloygery.content.tools.data.packet;

import amorphia.alloygery.content.tools.data.IAlloygeryToolMaterialData;
import amorphia.alloygery.content.tools.data.IAlloygeryToolMaterialDataValidator;
import com.google.gson.JsonObject;

public class AlloygeryToolMaterialDataValidatorPacket implements IAlloygeryToolMaterialDataValidator
{
	@Override
	public boolean validate(JsonObject jsonObject)
	{
		if(jsonObject == null || jsonObject.isJsonNull())
			return false;

		return jsonObject.has("alloygery_packet") && jsonObject.get("alloygery_packet").getAsString().equals("alloygery_tool_material");
	}

	@Override
	public Class<? extends IAlloygeryToolMaterialData> getDataVersionClass()
	{
		return AlloygeryToolMaterialDataPacket.class;
	}
}
