package amorphia.alloygery.advancement;

import amorphia.alloygery.Alloygery;
import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.AdvancementEntityPredicateSerializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class BlockBrokenCriterion extends AbstractCriterion<BlockBrokenCriterion.Conditions>
{
	public static final Identifier ID = Alloygery.identifier("block_broken");

	public BlockBrokenCriterion() {}

	@Override
	public Identifier getId()
	{
		return BlockBrokenCriterion.ID;
	}

	@Override
	protected Conditions conditionsFromJson(JsonObject jsonObject, EntityPredicate.Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer)
	{
		LocationPredicate locationPredicate = LocationPredicate.fromJson(jsonObject.get("location"));
		ItemPredicate itemPredicate = ItemPredicate.fromJson(jsonObject.get("item"));
		return new Conditions(playerPredicate, locationPredicate, itemPredicate);
	}

	public void trigger(ServerPlayerEntity player, BlockPos pos, ItemStack stack)
	{
		BlockState blockState = player.getWorld().getBlockState(pos);
		this.trigger(player, conditions -> {
			return conditions.test(blockState, player.getWorld(), pos, stack);
		});
	}

	public static class Conditions extends AbstractCriterionConditions
	{
		private final LocationPredicate location;
		private final ItemPredicate item;

		public Conditions(EntityPredicate.Extended player, LocationPredicate location, ItemPredicate item)
		{
			super(BlockBrokenCriterion.ID, player);
			this.location = location;
			this.item = item;
		}

		public static Conditions create(LocationPredicate.Builder location, ItemPredicate.Builder itemPredicateBuilder)
		{
			return new Conditions(EntityPredicate.Extended.EMPTY, location.build(), itemPredicateBuilder.build());
		}

		public boolean test(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack)
		{
			return !this.location.test(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5) ? false : this.item.test(stack);
		}

		@Override
		public JsonObject toJson(AdvancementEntityPredicateSerializer predicateSerializer)
		{
			JsonObject jsonObject = super.toJson(predicateSerializer);
			jsonObject.add("location", this.location.toJson());
			jsonObject.add("item", this.item.toJson());
			return jsonObject;
		}
	}
}
