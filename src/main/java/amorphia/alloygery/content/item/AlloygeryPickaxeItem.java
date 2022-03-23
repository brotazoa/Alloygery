package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.registry.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlloygeryPickaxeItem extends PickaxeItem implements IAlloygeryTool
{
	private final AlloygeryMaterial alloygeryMaterial;
	private int calculatedEnchantability = 15;

	public AlloygeryPickaxeItem(AlloygeryMaterial material, Item.Settings settings)
	{
		super(new DynamicToolMaterial(material), 1, -2.8f, settings);
		this.alloygeryMaterial = material;
		ITEMS.add(this);
	}

	public AlloygeryMaterial getAlloygeryMaterial()
	{
		return alloygeryMaterial;
	}

	@Override
	public Text getName(ItemStack stack)
	{
		return IAlloygeryTool.getUpgradedName(stack, getToolTypeString());
	}

	@Override
	public ItemStack getDefaultStack()
	{
		return ItemStack.EMPTY;
	}

	@Override
	public int getItemBarStep(ItemStack stack)
	{
		return IAlloygeryTool.getModifiedItemBarStep(stack);
	}

	@Override
	public int getItemBarColor(ItemStack stack)
	{
		return IAlloygeryTool.getModifierItemBarColor(stack);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		IAlloygeryTool.appendToTooltip(stack, world, tooltip, context);
	}

	@Override
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks)
	{
		if (this.isIn(group))
		{
			ModItems.MAKE_TOOL_HEADS_FOR_MATERIAL.forEach(material -> {
				ItemStack stack = new ItemStack(this);
				AlloygeryMaterialHelper.setHeadMaterial(stack, material);
				AlloygeryMaterialHelper.setBindingMaterial(stack, material);
				AlloygeryMaterialHelper.setHandleMaterial(stack, AlloygeryMaterials.VANILLA_STICK);
				stacks.add(stack);
			});
		}
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient)
	{
		return AlloygeryMaterialHelper.getRepairIngredient(stack).test(ingredient) || super.canRepair(stack, ingredient);
	}

	@Override
	public void setCalculatedEnchantability(int enchantability)
	{
		this.calculatedEnchantability = enchantability;
	}

	@Override
	public int getEnchantability()
	{
		return calculatedEnchantability;
	}

	@Override
	public String getToolTypeString()
	{
		return "pickaxe";
	}

	@Override
	public String getHeadTypeString()
	{
		return "head";
	}

	@Override
	public float getAttackDamageModifier()
	{
		return 1.0f;
	}

	@Override
	public float getAttackSpeedModifier()
	{
		return -2.8f;
	}
}
