package amorphia.alloygery.content.metals.item;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolMaterialHelper;
import amorphia.alloygery.content.tools.ToolPropertyHelper;
import amorphia.alloygery.content.tools.item.part.ToolPartType;
import amorphia.alloygery.content.materials.AlloygeryMaterial;
import amorphia.alloygery.content.tools.property.ToolPropertyType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CraftingHammer extends CraftingItem implements CraftingTool
{
	private final int tier;

	public CraftingHammer(AlloygeryMaterial material, int tier)
	{
		super(material, new Item.Settings().maxCount(1).maxDamage(1).group(Alloygery.ALLOYGERY_TAB_GROUP));
		this.tier = tier;
	}

	@Override
	public int getTier()
	{
		return this.tier;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		tooltip.add(Text.translatable("tooltip.alloygery.info.crafting_tier").append(Text.literal(": " + tier)));
	}

	@Override
	public int getEnchantability()
	{
		return (int) ToolPropertyHelper.computePropertyValue(getAlloygeryMaterial().getToolPropertiesByPart(ToolPartType.HEAD), ToolPropertyType.ENCHANTABILITY);
	}

	@Override
	public int getItemBarColor(ItemStack stack)
	{
		return CraftingTool.getItemBarColor(stack);
	}

	@Override
	public int getItemBarStep(ItemStack stack)
	{
		return CraftingTool.getItemBarStep(stack);
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient)
	{
		return ToolMaterialHelper.getRepairIngredient(getAlloygeryMaterial()).test(ingredient);
	}
}
