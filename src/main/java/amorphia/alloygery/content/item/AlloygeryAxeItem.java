package amorphia.alloygery.content.item;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import amorphia.alloygery.registry.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlloygeryAxeItem extends AxeItem implements IAlloygeryTool
{
	private final AlloygeryMaterial alloygeryMaterial;
	private int calculatedEnchantability = 0;

	public AlloygeryAxeItem(AlloygeryMaterial material, Item.Settings settings)
	{
		super(new DynamicToolMaterial(material), 5.0f, -3.0f, settings);
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
		return this.calculatedEnchantability;
	}

	@Override
	public String getToolTypeString()
	{
		return "axe";
	}

	@Override
	public String getHeadTypeString()
	{
		return "head";
	}

	@Override
	public float getAttackDamageModifier()
	{
		return 5.0f;
	}

	@Override
	public float getAttackSpeedModifier()
	{
		return -3.0f;
	}
}
