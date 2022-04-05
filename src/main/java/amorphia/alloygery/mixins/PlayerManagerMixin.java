package amorphia.alloygery.mixins;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin
{
	//TODO: is this possible without a mixin?
	@Inject(method = "onPlayerConnect", at = @At("TAIL"))
	public void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo info)
	{
		PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
		NbtCompound dataTag = new NbtCompound();

		NbtCompound materialsTag = new NbtCompound();
		AlloygeryMaterials.ALLOYGERY_MATERIALS.values().forEach(material -> {
			if(material == AlloygeryMaterials.UNKNOWN) return;

			Identifier id = AlloygeryMaterials.ALLOYGERY_MATERIALS.inverse().get(material);
			String jsonString = AlloygeryMaterial.GSON.toJson(material);
			materialsTag.putString(id.toString(), jsonString);
		});

		dataTag.put("AlloygeryMaterials", materialsTag);

		data.writeNbt(dataTag);
		ServerPlayNetworking.send(player, Alloygery.identifier("connect_packet"), data);
	}
}
