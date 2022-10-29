package amorphia.alloygery.content.tools.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.MiningToolItem;
import net.minecraft.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MiningToolItem.class)
public interface MiningToolItemAccessor
{
	@Accessor("effectiveBlocks")
	TagKey<Block> getEffectiveBlocks();
}
