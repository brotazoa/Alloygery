package amorphia.alloygery.advancement.mixin;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.advancement.BlockBrokenCriterion;
import amorphia.alloygery.registry.ModAdvancements;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Block.class)
public class BlockBrokenAdvancementTriggerMixin
{
	@Inject(method = "onBreak", at = @At("HEAD"))
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci)
	{
		Alloygery.LOGGER.info("on break mixin");

		if(!world.isClient)
		{
			ModAdvancements.BLOCK_BROKEN_CRITERION.trigger((ServerPlayerEntity) player, pos, player.getMainHandStack());
		}
	}
}
