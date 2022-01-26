package amorphia.alloygery.mixins;

import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.tag.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MiningToolItem.class)
public interface MiningToolItemAccessor
{
	@Accessor("effectiveBlocks")
	Tag<Block> getEffectiveBlocks();

	@Accessor("miningSpeed")
	float getMiningSpeed();
}
