package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import com.google.gson.stream.JsonReader;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import java.io.StringReader;
import java.util.Optional;

public class ModNetworking
{
	public static void register()
	{

	}

	public static void registerClient()
	{
		ClientPlayNetworking.registerGlobalReceiver(Alloygery.identifier("connect_packet"), (client, handler, buf, responseSender) -> {
			if(MinecraftClient.getInstance().isInSingleplayer())
				return;

			NbtCompound dataTagFromPacket = buf.readNbt();
			if(dataTagFromPacket == null)
				return;

			Optional<NbtCompound> materialsOptional = Optional.ofNullable(dataTagFromPacket.getCompound("AlloygeryMaterials"));
			if (materialsOptional.isPresent())
			{
				NbtCompound materials = materialsOptional.get();
				for (String materialName : materials.getKeys())
				{
					String jsonString = materials.getString(materialName);
					Identifier id = Identifier.tryParse(materialName);

					Alloygery.LOGGER.info("Reading material from server packet: " + id);

					AlloygeryMaterial material = AlloygeryMaterial.GSON.fromJson(new JsonReader(new StringReader(jsonString)), AlloygeryMaterial.class);
					AlloygeryMaterial registeredMaterial = AlloygeryMaterials.ALLOYGERY_MATERIALS.get(id);

					if (registeredMaterial != AlloygeryMaterials.UNKNOWN && material != null)
					{
						AlloygeryMaterial.merge(registeredMaterial, material);
					}
				}
			}
		});
	}
}
