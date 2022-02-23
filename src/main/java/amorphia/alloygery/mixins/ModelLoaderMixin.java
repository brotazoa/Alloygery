package amorphia.alloygery.mixins;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.data.GeneratedModelBuilder;
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
import java.util.Map;
import java.util.Optional;

@Mixin(ModelLoader.class)
public class ModelLoaderMixin
{
	@Shadow @Final private ResourceManager resourceManager;

	@Inject(method = "loadModelFromJson", at = @At("HEAD"), cancellable = true)
	public void loadModelFromJson(Identifier id, CallbackInfoReturnable<JsonUnbakedModel> cir) throws IOException
	{
		//TODO: is this possible without a mixin?
		if (id.getNamespace().equals(Alloygery.MOD_ID))
		{
			Optional<Map.Entry<Identifier, String>> modelSupplier = GeneratedModelBuilder.MODEL_SUPPLIER_FOR_IDENTIFIER.entrySet().stream().filter(entry -> entry.getKey().equals(id)).findFirst();

			//only generate models for identifiers that are registered
			if(modelSupplier.isPresent())
			{
				//System.out.println(id);

				Resource resource = null;
				Reader reader = null;

				JsonUnbakedModel model = null;

				try
				{
					//System.out.println("trying resource");
					resource = resourceManager.getResource(new Identifier(id.getNamespace(), "models/" + id.getPath() + ".json"));
					if (resource != null) //prefer hard resource
					{
						//System.out.println("found resource");
						reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
						model = JsonUnbakedModel.deserialize(reader);
					}
				}
				catch (IOException thrown)
				{
					//dirty consume
				}
				finally
				{
					IOUtils.closeQuietly(resource);
					IOUtils.closeQuietly(reader);
				}

				if (model == null)
				{
					//System.out.println("resource failed, loading from supplier");
					model = JsonUnbakedModel.deserialize(modelSupplier.get().getValue());
				}

				if (model != null)
				{
					//System.out.println("setting model");
					model.id = id.toString();
					cir.setReturnValue(model);
					cir.cancel();
				}
			}
		}
	}
}
