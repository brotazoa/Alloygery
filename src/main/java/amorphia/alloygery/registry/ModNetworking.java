package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialData;
import amorphia.alloygery.content.material.AlloygeryMaterialRegistry;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import com.google.gson.JsonObject;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

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

			AlloygeryMaterialRegistry.forEach((identifier, material) -> {
				if(material == AlloygeryMaterials.UNKNOWN)
					return;

				String jsonString = AlloygeryMaterial.GSON.toJson(material);
				materialsTag.putString(identifier.toString(), jsonString);
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

			AlloygeryMaterialRegistry.resetToRegisteredValues();

			Optional<NbtCompound> materialsOptional = Optional.ofNullable(dataTagFromPacket.getCompound("AlloygeryMaterials"));
			if (materialsOptional.isPresent())
			{
				NbtCompound materials = materialsOptional.get();
				for (String materialName : materials.getKeys())
				{
					String jsonString = materials.getString(materialName);
					Identifier id = Identifier.tryParse(materialName);

					Alloygery.LOGGER.info("Reading material from server packet: " + id);

					JsonObject json = JsonHelper.deserialize(new StringReader(jsonString));

					AlloygeryMaterial material = AlloygeryMaterial.GSON.fromJson(json, AlloygeryMaterial.class);
					AlloygeryMaterialData materialData = AlloygeryMaterialData.fromAlloygeryMaterial(material);
					AlloygeryMaterialRegistry.load(id, materialData);
				}

				StringBuilder builder = new StringBuilder("Material Registry contains the following entries after receiving server packet: [").append('\n');
				AlloygeryMaterialRegistry.forEach((identifier, material) -> builder.append('\t').append(identifier).append('\n'));
				builder.append("]");
				Alloygery.LOGGER.info(builder.toString());
			}
		});
	}
}
