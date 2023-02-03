package amorphia.alloygery.content.tools.data;

import com.google.gson.JsonObject;

public interface IAlloygeryToolMaterialDataValidator
{
	boolean validate(JsonObject jsonObject);

	Class<? extends IAlloygeryToolMaterialData> getDataVersionClass();
}
