package amorphia.alloygery.content.item.tool;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlloygeryShovelItem extends ShovelItem implements IAlloygeryTool
{
	public static final float ATTACK_DAMAGE = 1.5f;
	public static final float ATTACK_SPEED = -3.0f;

	private int calculatedEnchantability = 0;
	private final float attackDamage;
	private final float attackSpeed;

	public AlloygeryShovelItem(float attackDamage, float attackSpeed, Item.Settings settings)
	{
		super(ToolMaterials.WOOD, attackDamage, attackSpeed, settings);
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		TOOL_ITEMS.add(this);
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
			AlloygeryMaterials.ALLOYGERY_MATERIALS.values().forEach(material -> {
				if (material.make_tool_heads)
				{
					ItemStack stack = new ItemStack(this);
					AlloygeryToolMaterialHelper.setHeadMaterial(stack, material);
					AlloygeryToolMaterialHelper.setBindingMaterial(stack, AlloygeryMaterials.LEATHER);
					AlloygeryToolMaterialHelper.setHandleMaterial(stack, AlloygeryMaterials.VANILLA_STICK);
					stacks.add(stack);
				}
			});
		}
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient)
	{
		return AlloygeryToolMaterialHelper.getRepairIngredient(stack).test(ingredient);
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
		return "shovel";
	}

	@Override
	public String getHeadTypeString()
	{
		return "head";
	}

	@Override
	public float getAttackDamageModifier()
	{
		return attackDamage;
	}

	@Override
	public float getAttackSpeedModifier()
	{
		return attackSpeed;
	}
}
