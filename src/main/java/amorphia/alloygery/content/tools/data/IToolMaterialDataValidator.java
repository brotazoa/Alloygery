package amorphia.alloygery.content.tools.data;

import com.google.gson.JsonObject;

public interface IToolMaterialDataValidator
{
	boolean validate(JsonObject jsonObject);

	Class<? extends IToolMaterialData> getDataVersionClass();
}
