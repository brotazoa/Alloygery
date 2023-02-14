package amorphia.alloygery.content.armor.registry;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.data.AlloygeryArmorMaterialDataHelper;
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

public class ArmorNetworkEventRegistry
{
	private static final Identifier CONNECT_PACKET = Alloygery.identifier("sync_armor_materials_on_connect");

	public static void init()
	{
		ServerPlayConnectionEvents.JOIN.register((network, packetSender, server) -> {
			PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
			NbtCompound compound = new NbtCompound();

			NbtCompound materials = new NbtCompound();

			AlloygeryArmorMaterialRegistry.forEach((identifier, material) -> AlloygeryArmorMaterialDataHelper.getJsonStringFromArmorMaterial(material).ifPresentOrElse(
					jsonString -> materials.putString(identifier.toString(), jsonString),
					() -> Alloygery.LOGGER.warn("Could not make packet for " + identifier.toString())
			));

			compound.put("AlloygeryArmorMaterials", materials);

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

			AlloygeryArmorMaterialRegistry.resetToRegisteredValues();

			Optional.ofNullable(dataTagFromPacket.getCompound("AlloygeryArmorMaterials")).ifPresentOrElse(compound -> {
				for(String materialName : compound.getKeys())
				{
					String jsonString = compound.getString(materialName);
					Identifier id = Identifier.tryParse(materialName);

					Alloygery.LOGGER.info("Reading material from server packet: " + id);

					JsonObject json = JsonHelper.deserialize(new StringReader(jsonString));

					AlloygeryArmorMaterialDataHelper.getArmorMaterialDataFromJson(json).ifPresentOrElse(materialData -> AlloygeryArmorMaterialRegistry.load(id, materialData),
							() -> Alloygery.LOGGER.warn("Could not validate resource " + id + ", it is either not an Alloygery Material, or is written using an unsupported data version.")
					);
				}
			}, () -> Alloygery.LOGGER.warn("Received " + CONNECT_PACKET.toString() + " with missing or empty material data"));

			StringBuilder builder = new StringBuilder("Alloygery Armor Material Registry contains the following entries after receiving server packet: [").append('\n');
			AlloygeryArmorMaterialRegistry.forEach((identifier, material) -> builder.append('\t').append(identifier.toString()).append('\n'));
			builder.append("]");
			Alloygery.LOGGER.info(builder.toString());
		});
	}
}
