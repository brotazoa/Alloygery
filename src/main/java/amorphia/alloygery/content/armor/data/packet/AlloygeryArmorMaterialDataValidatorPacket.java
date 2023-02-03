package amorphia.alloygery.content.armor.data.packet;

import amorphia.alloygery.content.armor.data.IAlloygeryArmorMaterialData;
import amorphia.alloygery.content.armor.data.IAlloygeryArmorMaterialDataValidator;
import com.google.gson.JsonObject;

public class AlloygeryArmorMaterialDataValidatorPacket implements IAlloygeryArmorMaterialDataValidator
{
	@Override
	public boolean validate(JsonObject jsonObject)
	{
		if(jsonObject == null || jsonObject.isJsonNull())
			return false;

		return jsonObject.has("alloygery_packet") && jsonObject.get("alloygery_packet").getAsString().equals("alloygery_armor_material");
	}

	@Override
	public Class<? extends IAlloygeryArmorMaterialData> getDataVersionClass()
	{
		return null;
	}
}
