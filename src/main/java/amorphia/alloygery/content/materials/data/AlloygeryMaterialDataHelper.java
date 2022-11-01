package amorphia.alloygery.content.materials.data;

import amorphia.alloygery.content.materials.data.packet.AlloygeryMaterialDataPacket;
import amorphia.alloygery.content.materials.data.packet.AlloygeryMaterialDataValidatorPacket;
import amorphia.alloygery.content.materials.data.v1.AlloygeryMaterialDataV1;
import amorphia.alloygery.content.materials.data.v1.AlloygeryMaterialDataValidatorV1;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Optional;
import java.util.Set;

public class AlloygeryMaterialDataHelper
{
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(AlloygeryMaterialDataV1.class, new AlloygeryMaterialDataV1.Serializer())
			.registerTypeAdapter(AlloygeryMaterialDataPacket.class, AlloygeryMaterialDataPacket.Serializer.INSTANCE)
			.registerTypeAdapter(AlloygeryMaterial.class, AlloygeryMaterialDataPacket.Serializer.INSTANCE)
			.setPrettyPrinting()
			.create();

	private static final Set<IAlloygeryMaterialDataValidator> DATA_VALIDATORS = Set.of(
			new AlloygeryMaterialDataValidatorV1(),
			new AlloygeryMaterialDataValidatorPacket()
	);

	public static Optional<IAlloygeryMaterialData> getToolMaterialDataFromJson(JsonObject jsonObject)
	{
		IAlloygeryMaterialDataValidator validator = DATA_VALIDATORS.stream().filter(v -> v.validate(jsonObject)).findFirst().orElse(null);
		if (validator == null)
		{
			return Optional.empty();
		}

		return Optional.ofNullable(GSON.fromJson(jsonObject, validator.getDataVersionClass()));
	}

	public static Optional<String> getJsonStringFromToolMaterial(AlloygeryMaterial material)
	{
		return Optional.ofNullable(GSON.toJson(material));
	}
}
