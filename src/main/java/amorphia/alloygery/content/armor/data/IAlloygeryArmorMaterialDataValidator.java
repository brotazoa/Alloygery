package amorphia.alloygery.content.armor.data;

import com.google.gson.JsonObject;

public interface IAlloygeryArmorMaterialDataValidator
{
	boolean validate(JsonObject jsonObject);

	Class<? extends IAlloygeryArmorMaterialData> getDataVersionClass();
}
