package amorphia.alloygery.content.armor.mixin;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.client.ArmorPartModelBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.commons.io.IOUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Mixin(ModelLoader.class)
public class ArmorModelLoaderMixin
{
	@Shadow @Final private ResourceManager resourceManager;

	@Environment(EnvType.CLIENT)
	@Inject(method = "loadModelFromJson", at = @At("HEAD"), cancellable = true)
	private void loadModelFromSupplier(Identifier identifier, CallbackInfoReturnable<JsonUnbakedModel> cir)
	{
		ArmorPartModelBuilder.getModelSupplierForIdentifier(identifier).ifPresent(supplierEntry -> {
			Resource resource = null;
			Reader reader = null;
			JsonUnbakedModel model = null;

			try
			{
				resource = resourceManager.getResource(new Identifier(identifier.getNamespace(), "models/" + identifier.getPath() + ".json")).orElse(null);
				if (resource != null)
				{
					reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
					model = JsonUnbakedModel.deserialize(reader);
				}
			}
			catch(IOException e)
			{
//				Alloygery.LOGGER.error(e);
			}
			finally
			{
				IOUtils.closeQuietly(reader);
			}

			if (model == null)
			{
				model = JsonUnbakedModel.deserialize(supplierEntry.getValue().get());
			}

			if (model != null)
			{
				model.id = identifier.toString();
				cir.setReturnValue(model);
				cir.cancel();
			}
		});
	}
}
