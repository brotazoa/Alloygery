package amorphia.alloygery.content.armor.data;

import amorphia.alloygery.content.armor.data.packet.AlloygeryArmorMaterialDataPacket;
import amorphia.alloygery.content.armor.data.packet.AlloygeryArmorMaterialDataValidatorPacket;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Optional;
import java.util.Set;

public class AlloygeryArmorMaterialDataHelper
{
	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(AlloygeryArmorMaterialDataPacket.class, AlloygeryArmorMaterialDataPacket.Serializer.INSTANCE)
			.registerTypeAdapter(AlloygeryArmorMaterial.class, AlloygeryArmorMaterialDataPacket.Serializer.INSTANCE)
			.setPrettyPrinting()
			.create();

	private static final Set<IAlloygeryArmorMaterialDataValidator> DATA_VALIDATORS = Set.of(
			new AlloygeryArmorMaterialDataValidatorPacket()
	);

	public static Optional<IAlloygeryArmorMaterialData> getArmorMaterialDataFromJson(JsonObject jsonObject)
	{
		IAlloygeryArmorMaterialDataValidator validator = DATA_VALIDATORS.stream().filter(v -> v.validate(jsonObject)).findFirst().orElse(null);
		if(validator == null)
			return Optional.empty();

		return Optional.ofNullable(GSON.fromJson(jsonObject, validator.getDataVersionClass()));
	}

	public static Optional<String> getJsonStringFromArmorMaterial(AlloygeryArmorMaterial material)
	{
		return Optional.ofNullable(GSON.toJson(material));
	}
}
