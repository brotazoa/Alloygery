package amorphia.alloygery.content.materials.data;

import com.google.gson.JsonObject;

public interface IAlloygeryMaterialDataValidator
{
	boolean validate(JsonObject jsonObject);

	Class<? extends IAlloygeryMaterialData> getDataVersionClass();
}
