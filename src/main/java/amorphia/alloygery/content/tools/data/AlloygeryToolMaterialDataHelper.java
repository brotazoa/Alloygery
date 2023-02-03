package amorphia.alloygery.content.tools.data;

import amorphia.alloygery.content.tools.data.packet.AlloygeryToolMaterialDataPacket;
import amorphia.alloygery.content.tools.data.packet.AlloygeryToolMaterialDataValidatorPacket;
import amorphia.alloygery.content.tools.data.v1.AlloygeryToolMaterialDataV1;
import amorphia.alloygery.content.tools.data.v1.AlloygeryToolMaterialDataValidatorV1;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Optional;
import java.util.Set;

public class AlloygeryToolMaterialDataHelper
{
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(AlloygeryToolMaterialDataV1.class, new AlloygeryToolMaterialDataV1.Serializer())
			.registerTypeAdapter(AlloygeryToolMaterialDataPacket.class, AlloygeryToolMaterialDataPacket.Serializer.INSTANCE)
			.registerTypeAdapter(AlloygeryToolMaterial.class, AlloygeryToolMaterialDataPacket.Serializer.INSTANCE)
			.setPrettyPrinting()
			.create();

	private static final Set<IAlloygeryToolMaterialDataValidator> DATA_VALIDATORS = Set.of(
			new AlloygeryToolMaterialDataValidatorV1(),
			new AlloygeryToolMaterialDataValidatorPacket()
	);

	public static Optional<IAlloygeryToolMaterialData> getToolMaterialDataFromJson(JsonObject jsonObject)
	{
		IAlloygeryToolMaterialDataValidator validator = DATA_VALIDATORS.stream().filter(v -> v.validate(jsonObject)).findFirst().orElse(null);
		if (validator == null)
		{
			return Optional.empty();
		}

		return Optional.ofNullable(GSON.fromJson(jsonObject, validator.getDataVersionClass()));
	}

	public static Optional<String> getJsonStringFromToolMaterial(AlloygeryToolMaterial material)
	{
		return Optional.ofNullable(GSON.toJson(material));
	}
}
