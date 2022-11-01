package amorphia.alloygery.content.materials.data.packet;

import amorphia.alloygery.content.materials.data.IAlloygeryMaterialData;
import amorphia.alloygery.content.materials.data.IAlloygeryMaterialDataValidator;
import com.google.gson.JsonObject;

public class AlloygeryMaterialDataValidatorPacket implements IAlloygeryMaterialDataValidator
{
	@Override
	public boolean validate(JsonObject jsonObject)
	{
		if(jsonObject == null || jsonObject.isJsonNull())
			return false;

		return jsonObject.has("alloygery_packet") && jsonObject.get("alloygery_packet").getAsString().equals("alloygery_material");
	}

	@Override
	public Class<? extends IAlloygeryMaterialData> getDataVersionClass()
	{
		return AlloygeryMaterialDataPacket.class;
	}
}
