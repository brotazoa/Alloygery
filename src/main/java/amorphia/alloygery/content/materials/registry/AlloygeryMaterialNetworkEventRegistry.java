package amorphia.alloygery.content.materials.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.materials.data.AlloygeryMaterialDataHelper;
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

public class AlloygeryMaterialNetworkEventRegistry
{
	private static final Identifier CONNECT_PACKET = Alloygery.identifier("connect_packet");

	public static void init()
	{
		ServerPlayConnectionEvents.JOIN.register((network, packetSender, server) -> {
			PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
			NbtCompound compound = new NbtCompound();

			NbtCompound materials = new NbtCompound();

			AlloygeryMaterialRegistry.forEach((identifier, material) -> AlloygeryMaterialDataHelper.getJsonStringFromToolMaterial(material).ifPresentOrElse(
					jsonString -> materials.putString(identifier.toString(), jsonString),
					() -> Alloygery.LOGGER.warn("Could not make packet for " + identifier.toString()))
			);

			compound.put("AlloygeryMaterials", materials);

			packet.writeNbt(compound);
			packetSender.sendPacket(CONNECT_PACKET, packet);
		});
	}

	public static void initClient()
	{
		ClientPlayNetworking.registerGlobalReceiver(CONNECT_PACKET, (client, handler, buf, responseSender) -> {
			if(MinecraftClient.getInstance().isInSingleplayer())
				return;

			NbtCompound dataTagFromPacket = buf.readNbt();
			if(dataTagFromPacket == null || dataTagFromPacket.isEmpty())
				return;

			AlloygeryMaterialRegistry.resetToRegisteredValues();

			Optional.ofNullable(dataTagFromPacket.getCompound("AlloygeryMaterials")).ifPresentOrElse(compound -> {
				for(String materialName : compound.getKeys())
				{
					String jsonString = compound.getString(materialName);
					Identifier id = Identifier.tryParse(materialName);

					Alloygery.LOGGER.info("Reading material from server packet: " + id);

					JsonObject json = JsonHelper.deserialize(new StringReader(jsonString));

					AlloygeryMaterialDataHelper.getToolMaterialDataFromJson(json).ifPresentOrElse(toolData -> AlloygeryMaterialRegistry.load(id, toolData),
							() -> Alloygery.LOGGER.warn("Could not validate resource " + id + ", it is either not an Alloygery Material, or is written using an unsupported data version.")
					);
				}
			}, () -> Alloygery.LOGGER.warn("Received " + CONNECT_PACKET.toString() + " with missing or empty material data"));

			StringBuilder builder = new StringBuilder("Alloygery Material Registry contains the following entries after receiving server packet: [").append('\n');
			AlloygeryMaterialRegistry.forEach((identifier, material) -> builder.append('\t').append(identifier).append('\n'));
			builder.append("]");
			Alloygery.LOGGER.info(builder.toString());
		});
	}
}
