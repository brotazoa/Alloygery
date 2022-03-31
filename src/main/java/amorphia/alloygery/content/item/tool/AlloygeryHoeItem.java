package amorphia.alloygery.content.item.tool;

import amorphia.alloygery.content.material.AlloygeryMaterial;
import amorphia.alloygery.content.material.AlloygeryToolMaterialHelper;
import amorphia.alloygery.content.material.AlloygeryMaterials;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

public class AlloygeryHoeItem extends HoeItem implements IAlloygeryTool
{
	public static final float ATTACK_DAMAGE = -2.0f;
	public static final float ATTACK_SPEED = -2.8f;

	private int calculatedEnchantability = 0;
	private final float attackDamage;
	private final float attackSpeed;

	public AlloygeryHoeItem(float attackDamage, float attackSpeed, Item.Settings settings)
	{
		super(ToolMaterials.WOOD, (int) attackDamage, attackSpeed, settings);
		this.attackDamage = attackDamage;
		this.attackSpeed = attackSpeed;
		TOOL_ITEMS.add(this);
	}

	@Override
	public ItemStack getDefaultStack()
	{
		return ItemStack.EMPTY;
	}

	@Override
	public Text getName(ItemStack stack)
	{
		return IAlloygeryTool.getUpgradedName(stack, getToolTypeString());
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
	public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks)
	{
		if (this.isIn(group))
		{
			AlloygeryMaterials.ALLOYGERY_MATERIALS.forEach(material -> {
				if (material.make_tool_heads)
				{
					ItemStack stack = new ItemStack(this);
					AlloygeryToolMaterialHelper.setHeadMaterial(stack, material);
					AlloygeryToolMaterialHelper.setBindingMaterial(stack, material);
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
		return "hoe";
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
