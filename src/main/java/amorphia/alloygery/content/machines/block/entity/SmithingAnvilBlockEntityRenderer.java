package amorphia.alloygery.content.machines.block.entity;

import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Vec3f;

public class SmithingAnvilBlockEntityRenderer implements BlockEntityRenderer<SmithingAnvilBlockEntity>
{
	public SmithingAnvilBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {} //no op

	@Override
	public void render(SmithingAnvilBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
	{
		if(entity == null || entity.getWorld() == null || entity.getWorld().getBlockState(entity.getPos()).getBlock() != MachineBlockRegistry.SMITHING_ANVIL)
			return;

		ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
		final int surfaceLight = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());

		matrices.push();
		matrices.translate(0.5, 0.578125, 0.5);
		final float rotation = entity.getWorld().getBlockState(entity.getPos()).get(Properties.HORIZONTAL_FACING).getOpposite().asRotation();
		matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotation));
		matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180));

		//render hammer
		ItemStack hammerStack = entity.getStack(0);
		if (!hammerStack.isEmpty())
		{
			matrices.push();

			matrices.translate(0.0, 0.0, -0.175);
			matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90f));
			matrices.scale(0.4f, 0.4f, 0.4f);
			itemRenderer.renderItem(hammerStack, ModelTransformation.Mode.GUI, surfaceLight, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);

			matrices.pop();
		}

		ItemStack materialStack = entity.getStack(1);
		if (!materialStack.isEmpty())
		{
			matrices.push();

			matrices.translate(0.0, 0.0, 0.175);

			if (!itemRenderer.getModels().getModel(materialStack).hasDepth())
			{
				matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90f));
				matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90f));
			}
			else
			{
				matrices.translate(0.0, -0.046875, 0.0);
			}

			matrices.scale(0.5f, 0.5f, 0.5f);
			itemRenderer.renderItem(materialStack, ModelTransformation.Mode.GROUND, surfaceLight, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);

			matrices.pop();
		}

		matrices.pop();
	}
}
