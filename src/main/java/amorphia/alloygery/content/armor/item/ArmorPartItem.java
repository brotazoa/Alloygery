package amorphia.alloygery.content.armor.item;

import amorphia.alloygery.content.armor.ArmorDescriptionHelper;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArmorPartItem extends Item implements IArmorPart
{
	protected AlloygeryArmorMaterial armorMaterial;
	protected ArmorLayer layer;
	protected ArmorType type;

	public ArmorPartItem(Settings settings, AlloygeryArmorMaterial armorMaterial, ArmorLayer layer, ArmorType type)
	{
		super(settings);
		this.armorMaterial = armorMaterial;
		this.layer = layer;
		this.type = type;

		IArmorPart.ARMOR_PART_ITEMS.add(this);
	}

	@Override
	public ArmorLayer getArmorLayer()
	{
		return this.layer;
	}

	@Override
	public ArmorType getArmorType()
	{
		return this.type;
	}

	@Override
	public AlloygeryArmorMaterial getArmorMaterial()
	{
		return this.armorMaterial;
	}

	@Override
	public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context)
	{
		ArmorDescriptionHelper.writeArmorPartDescription(tooltip, stack, this);
	}
}
