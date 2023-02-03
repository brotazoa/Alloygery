package amorphia.alloygery.content.armor.item;

import amorphia.alloygery.content.armor.ArmorDescriptionHelper;
import amorphia.alloygery.content.armor.ArmorMaterialHelper;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DynamicArmorItem extends ArmorItem implements IDynamicArmor
{
	private final ArmorType type;

	public DynamicArmorItem(ArmorType type)
	{
		this(type, new Settings());
	}

	public DynamicArmorItem(ArmorType type, Settings settings)
	{
		super(EmptyArmorMaterial.INSTANCE, type.getEquipmentSlot(), settings);
		ARMOR_ITEMS.add(this);
		this.type = type;
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient)
	{
		return ArmorMaterialHelper.getRepairIngredientForStack(stack).test(ingredient);
	}

	@Override
	public Text getName(ItemStack stack)
	{
		return ArmorDescriptionHelper.getArmorStackName(stack);
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		ArmorDescriptionHelper.writeArmorDescription(tooltip, stack, context);
	}

	@Override
	public ArmorType getArmorType()
	{
		return this.type;
	}

	@Override
	public int getItemBarColor(ItemStack stack)
	{
		return IDynamicArmor.getItemBarColor(stack);
	}

	@Override
	public int getItemBarStep(ItemStack stack)
	{
		return IDynamicArmor.getItemBarStep(stack);
	}

	protected int calculatedEnchantability = 0;

	@Override
	public int getEnchantability()
	{
		return this.calculatedEnchantability;
	}

	@Override
	public void calculateEnchantability(ItemStack stack)
	{
		this.calculatedEnchantability = getEnchantability(stack);
	}
}
