package amorphia.alloygery.content.armor;

import amorphia.alloygery.content.armor.item.ArmorLayer;
import amorphia.alloygery.content.armor.item.ArmorPartItem;
import amorphia.alloygery.content.armor.item.IArmorPart;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterials;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static amorphia.alloygery.content.armor.AlloygeryArmorNBT.*;

public class ArmorNBTHelper
{
	public static AlloygeryArmorNBT getArmorLayerNBTIdentifier(ArmorLayer layer)
	{
		return switch (layer)
		{
			case BASE -> ARMOR_LAYER_BASE;
			case PLATE -> ARMOR_LAYER_PLATE;
			case UPGRADE -> ARMOR_LAYER_UPGRADE;
		};
	}

	public static ArmorLayer getArmorLayerFromNBT(AlloygeryArmorNBT nbt)
	{
		return switch (nbt)
		{
			case ARMOR_LAYER_BASE -> ArmorLayer.BASE;
			case ARMOR_LAYER_PLATE -> ArmorLayer.PLATE;
			case ARMOR_LAYER_UPGRADE -> ArmorLayer.UPGRADE;
			default -> throw new EnumConstantNotPresentException(AlloygeryArmorNBT.class, nbt.getName());
		};
	}

	public static ItemStack addAlloygeryNBTToArmorStack(ItemStack armorStack, NbtCompound alloygeryNbtCompound)
	{
		if(armorStack == null || armorStack.isEmpty() || alloygeryNbtCompound == null || alloygeryNbtCompound.isEmpty())
			return ItemStack.EMPTY;

		if(!isArmorNBT(alloygeryNbtCompound))
			return ItemStack.EMPTY;

		armorStack.getOrCreateNbt().put(ALLOYGERY_NBT_IDENTIFIER.getName(), alloygeryNbtCompound);
		return armorStack;
	}

	public static NbtCompound createArmorNBT(AlloygeryArmorMaterial base)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), ARMOR_IDENTIFIER.getName());
		compound.put(ARMOR_LAYER_BASE.getName(), createArmorLayerNBTFromMaterial(base, ArmorLayer.BASE));
		return compound;
	}

	public static NbtCompound createArmorNBT(AlloygeryArmorMaterial base, AlloygeryArmorMaterial armor)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), ARMOR_IDENTIFIER.getName());
		compound.put(ARMOR_LAYER_BASE.getName(), createArmorLayerNBTFromMaterial(base, ArmorLayer.BASE));
		compound.put(ARMOR_LAYER_PLATE.getName(), createArmorLayerNBTFromMaterial(armor, ArmorLayer.PLATE));
		return compound;
	}

	public static NbtCompound createArmorNBT(AlloygeryArmorMaterial base, AlloygeryArmorMaterial armor, AlloygeryArmorMaterial upgrade)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), ARMOR_IDENTIFIER.getName());
		compound.put(ARMOR_LAYER_BASE.getName(), createArmorLayerNBTFromMaterial(base, ArmorLayer.BASE));
		compound.put(ARMOR_LAYER_PLATE.getName(), createArmorLayerNBTFromMaterial(armor, ArmorLayer.PLATE));
		compound.put(ARMOR_LAYER_UPGRADE.getName(), createArmorLayerNBTFromMaterial(upgrade, ArmorLayer.UPGRADE));
		return compound;
	}

	public static NbtCompound createArmorLayerNBTFromMaterial(AlloygeryArmorMaterial layerMaterial, ArmorLayer layer)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), ARMOR_LAYER_IDENTIFIER.getName());
		compound.putString(ARMOR_LAYER_IDENTIFIER.getName(), getArmorLayerNBTIdentifier(layer).getName());
		compound.putString(MATERIAL_IDENTIFIER.getName(), AlloygeryArmorMaterialRegistry.identify(layerMaterial).toString());
		return compound;
	}

	public static NbtCompound getAlloygeryDataNBT(ItemStack armorStack)
	{
		if(armorStack == null || armorStack.isEmpty() || armorStack.getNbt() == null || armorStack.getNbt().isEmpty())
			return new NbtCompound();

		return getAlloygeryDataNBT(armorStack.getNbt());
	}

	public static NbtCompound getAlloygeryDataNBT(NbtCompound compound)
	{
		return isAlloygeryDataNBT(compound) ? compound.getCompound(ALLOYGERY_NBT_IDENTIFIER.getName()) : new NbtCompound();
	}

	public static NbtCompound getArmorLayerNBT(ItemStack armorStack, ArmorLayer layer)
	{
		if(armorStack == null || armorStack.isEmpty() || armorStack.getNbt() == null || armorStack.getNbt().isEmpty())
			return new NbtCompound();

		NbtCompound alloygeryCompound = getAlloygeryDataNBT(armorStack);
		if(!isArmorNBT(alloygeryCompound))
			return new NbtCompound();

		NbtCompound layerCompound = getArmorLayerNBTFromArmorNBT(alloygeryCompound, layer);
		return isArmorLayerNBT(layerCompound) ? layerCompound : new NbtCompound();
	}

	public static NbtCompound getArmorLayerNBTFromArmorNBT(NbtCompound compound, ArmorLayer layer)
	{
		return isArmorNBT(compound) && compound.contains(getArmorLayerNBTIdentifier(layer).getName()) ? compound.getCompound(getArmorLayerNBTIdentifier(layer).getName()) : new NbtCompound();
	}

	public static Identifier getMaterialIdentifierFromArmorLayerNBT(NbtCompound compound)
	{
		return isArmorLayerNBT(compound) ? Identifier.tryParse(compound.getString(MATERIAL_IDENTIFIER.getName())) : AlloygeryArmorMaterialRegistry.getDefaultIdentifier();
	}

	public static Identifier getMaterialIdentifierFromArmorLayer(ItemStack armorStack, ArmorLayer layer)
	{
		return getMaterialIdentifierFromArmorLayerNBT(getArmorLayerNBT(armorStack, layer));
	}

	public static void setArmorLayerColor(ItemStack armorStack, ArmorLayer layer, int color)
	{
		NbtCompound compound = getArmorLayerNBT(armorStack, layer);
		if(compound != null)
			compound.putInt(ARMOR_DYE_COLOR.getName(), color);
	}

	public static void removeArmorLayerColor(ItemStack armorStack, ArmorLayer layer)
	{
		NbtCompound compound = getArmorLayerNBT(armorStack, layer);
		if(compound != null)
			compound.remove(ARMOR_DYE_COLOR.getName());
	}

	public static int getLayerColor(ItemStack armorStack, ArmorLayer layer)
	{
		return armorLayerHasColor(armorStack, layer) ? getArmorLayerNBT(armorStack, layer).getInt(ARMOR_DYE_COLOR.getName()) :
				armorStack.getItem() instanceof DyeableItem dyeableItem ? dyeableItem.getColor(armorStack) : getDefaultColor();
	}

	public static boolean hasArmorLayer(ItemStack armorStack, ArmorLayer layer)
	{
		return isArmorLayerNBT(getArmorLayerNBT(armorStack, layer), layer);
	}

	public static boolean armorLayerHasColor(ItemStack armorStack, ArmorLayer layer)
	{
		NbtCompound layerCompound = getArmorLayerNBT(armorStack, layer);
		return isArmorLayerNBT(layerCompound, layer) && layerCompound.contains(ARMOR_DYE_COLOR.getName());
	}

	public static boolean isAlloygeryDataNBT(NbtCompound compound)
	{
		return compound != null && !compound.isEmpty() && compound.contains(ALLOYGERY_NBT_IDENTIFIER.getName());
	}

	public static boolean isArmorNBT(NbtCompound compound)
	{
		return compound != null && !compound.isEmpty() && compound.getString(TYPE.getName()).equals(ARMOR_IDENTIFIER.getName());
	}

	public static boolean isArmorLayerNBT(NbtCompound compound, ArmorLayer layer)
	{
		return isArmorLayerNBT(compound) && compound.getString(ARMOR_LAYER_IDENTIFIER.getName()).equals(getArmorLayerNBTIdentifier(layer).getName());
	}

	public static boolean isArmorLayerNBT(NbtCompound compound)
	{
		return compound != null && !compound.isEmpty() && compound.getString(TYPE.getName()).equals(ARMOR_LAYER_IDENTIFIER.getName());
	}

	static int getDefaultColor()
	{
		return AlloygeryArmorMaterials.UNKNOWN.getMaterialColor();
	}
}
