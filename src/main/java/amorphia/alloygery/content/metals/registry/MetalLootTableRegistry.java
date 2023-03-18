package amorphia.alloygery.content.metals.registry;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class MetalLootTableRegistry
{
	private static final Identifier HUSK_LOOT_TABLE_ID = new Identifier("entities/husk");

	public static void init()
	{
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (source.isBuiltin() && id.equals(HUSK_LOOT_TABLE_ID))
			{
				LootPool.Builder builder = LootPool.builder();

				builder.rolls(ConstantLootNumberProvider.create(1.0f));

				builder.with(ItemEntry.builder(MetalItemRegistry.ITEMS.get("tin_ingot")));

				builder.conditionally(KilledByPlayerLootCondition.builder());
				builder.conditionally(RandomChanceWithLootingLootCondition.builder(0.11f, 0.02f));

				tableBuilder.pool(builder);
			}
		});
	}
}
