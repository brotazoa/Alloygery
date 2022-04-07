package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import com.google.gson.stream.JsonReader;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.io.StringReader;
import java.util.Optional;

public class ModNetworking
{
	public static void register()
	{
		ServerPlayConnectionEvents.JOIN.register((network, packetSender, server) -> {
			PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
			NbtCompound compound = new NbtCompound();

			NbtCompound materialsTag = new NbtCompound();
			AlloygeryMaterials.ALLOYGERY_MATERIALS.values().forEach(material -> {
				if(material == AlloygeryMaterials.UNKNOWN)
					return;

				Identifier id = AlloygeryMaterials.ALLOYGERY_MATERIALS.inverse().get(material);
				String jsonString = AlloygeryMaterial.GSON.toJson(material);
				materialsTag.putString(id.toString(), jsonString);
			});

			compound.put("AlloygeryMaterials", materialsTag);

			packet.writeNbt(compound);
			packetSender.sendPacket(Alloygery.identifier("connect_packet"), packet);
		});
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
					AlloygeryMaterial registeredMaterial = AlloygeryMaterials.ALLOYGERY_MATERIALS.getOrDefault(id, AlloygeryMaterials.UNKNOWN);

					if(material == null)
						continue;

					if (registeredMaterial != AlloygeryMaterials.UNKNOWN)
					{
						AlloygeryMaterial.merge(registeredMaterial, material);
					}
					else
					{
						AlloygeryMaterials.register(id, material);
					}
				}
			}
		});
	}
}
