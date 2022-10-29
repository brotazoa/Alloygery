package amorphia.alloygery.content.tools.data;

import amorphia.alloygery.content.tools.data.packet.ToolMaterialDataPacket;
import amorphia.alloygery.content.tools.data.packet.ToolMaterialDataValidatorPacket;
import amorphia.alloygery.content.tools.data.v1.ToolMaterialDataV1;
import amorphia.alloygery.content.tools.data.v1.ToolMaterialDataValidatorV1;
import amorphia.alloygery.content.tools.material.ToolMaterial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Optional;
import java.util.Set;

public class ToolMaterialDataHelper
{
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(ToolMaterialDataV1.class, new ToolMaterialDataV1.Serializer())
			.registerTypeAdapter(ToolMaterialDataPacket.class, ToolMaterialDataPacket.Serializer.INSTANCE)
			.registerTypeAdapter(ToolMaterial.class, ToolMaterialDataPacket.Serializer.INSTANCE)
			.setPrettyPrinting()
			.create();

	private static final Set<IToolMaterialDataValidator> DATA_VALIDATORS = Set.of(
			new ToolMaterialDataValidatorV1(),
			new ToolMaterialDataValidatorPacket()
	);

	public static Optional<IToolMaterialData> getToolMaterialDataFromJson(JsonObject jsonObject)
	{
		IToolMaterialDataValidator validator = DATA_VALIDATORS.stream().filter(v -> v.validate(jsonObject)).findFirst().orElse(null);
		if (validator == null)
		{
			return Optional.empty();
		}

		return Optional.ofNullable(GSON.fromJson(jsonObject, validator.getDataVersionClass()));
	}

	public static Optional<String> getJsonStringFromToolMaterial(ToolMaterial material)
	{
		return Optional.ofNullable(GSON.toJson(material));
	}
}
