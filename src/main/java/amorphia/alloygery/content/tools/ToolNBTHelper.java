package amorphia.alloygery.content.tools;

import amorphia.alloygery.content.tools.item.part.*;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterials;
import amorphia.alloygery.content.tools.registry.AlloygeryToolMaterialRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static amorphia.alloygery.content.tools.AlloygeryToolNBT.*;

public class ToolNBTHelper
{
	public static AlloygeryToolNBT getToolPartNBTIdentifier(IToolPart part)
	{
		return getToolPartNBTIdentifier(part.getToolPartType());
	}

	public static AlloygeryToolNBT getToolPartNBTIdentifier(ToolPartType partType)
	{
		return switch (partType)
		{
			case HEAD -> TOOL_PART_HEAD;
			case BINDING -> TOOL_PART_BINDING;
			case HANDLE -> TOOL_PART_HANDLE;
			case UPGRADE -> TOOL_PART_UPGRADE;
		};
	}

	public static ToolPartType getToolPartTypeFromNBT(AlloygeryToolNBT nbt)
	{
		return switch (nbt)
		{
			case TOOL_PART_HEAD -> ToolPartType.HEAD;
			case TOOL_PART_BINDING -> ToolPartType.BINDING;
			case TOOL_PART_HANDLE -> ToolPartType.HANDLE;
			case TOOL_PART_UPGRADE -> ToolPartType.UPGRADE;
			default -> throw new EnumConstantNotPresentException(AlloygeryToolNBT.class, nbt.getName());
		};
	}

	public static AlloygeryToolNBT getToolTypeNBTIdentifier(IToolPartHead head)
	{
		return getToolTypeNBTIdentifier(head.getToolType());
	}

	public static AlloygeryToolNBT getToolTypeNBTIdentifier(ToolType toolType)
	{
		return switch (toolType)
		{
			case AXE -> TOOL_TYPE_AXE;
			case HOE -> TOOL_TYPE_HOE;
			case PICKAXE -> TOOL_TYPE_PICKAXE;
			case SHOVEL -> TOOL_TYPE_SHOVEL;
			case SWORD -> TOOL_TYPE_SWORD;
		};
	}

	public static ToolType getToolTypeFromNBT(AlloygeryToolNBT nbt)
	{
		return switch (nbt)
		{
			case TOOL_TYPE_AXE -> ToolType.AXE;
			case TOOL_TYPE_HOE -> ToolType.HOE;
			case TOOL_TYPE_PICKAXE -> ToolType.PICKAXE;
			case TOOL_TYPE_SHOVEL -> ToolType.SHOVEL;
			case TOOL_TYPE_SWORD -> ToolType.SWORD;
			default -> throw new EnumConstantNotPresentException(AlloygeryToolNBT.class, nbt.getName());
		};
	}

	public static ItemStack addAlloygeryNBTToToolStack(ItemStack toolStack, NbtCompound alloygeryNbtCompound)
	{
		if(toolStack == null || toolStack.isEmpty() || alloygeryNbtCompound == null || alloygeryNbtCompound.isEmpty())
			return ItemStack.EMPTY;

		if(!isToolNBT(alloygeryNbtCompound))
			return ItemStack.EMPTY;

		toolStack.getOrCreateNbt().put(ALLOYGERY_NBT_IDENTIFIER.getName(), alloygeryNbtCompound);
		return toolStack;
	}

	public static NbtCompound createToolNBTFromToolPartItems(ToolPartItem head, ToolPartItem handle)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), TOOL_IDENTIFIER.getName());
		compound.put(TOOL_PART_HEAD.getName(), createNBTFromToolPartItem(head));

		compound.put(TOOL_PART_BINDING.getName(), createHiddenBindingNbt());

		compound.put(TOOL_PART_HANDLE.getName(), createNBTFromToolPartItem(handle));
		return compound;
	}

	public static NbtCompound createToolNBTFromToolPartItems(ToolPartItem head, ToolPartItem binding, ToolPartItem handle)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), TOOL_IDENTIFIER.getName());
		compound.put(TOOL_PART_HEAD.getName(), createNBTFromToolPartItem(head));
		compound.put(TOOL_PART_BINDING.getName(), binding == null ? createHiddenBindingNbt() : createNBTFromToolPartItem(binding));
		compound.put(TOOL_PART_HANDLE.getName(), createNBTFromToolPartItem(handle));
		return compound;
	}

	public static NbtCompound createToolNBTFromToolPartItems(ToolPartItem head, ToolPartItem binding, ToolPartItem handle, ToolPartItem upgrade)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), TOOL_IDENTIFIER.getName());
		compound.put(TOOL_PART_HEAD.getName(), createNBTFromToolPartItem(head));
		compound.put(TOOL_PART_BINDING.getName(), binding == null ? createHiddenBindingNbt() : createNBTFromToolPartItem(binding));
		compound.put(TOOL_PART_HANDLE.getName(), createNBTFromToolPartItem(handle));
		compound.put(TOOL_PART_UPGRADE.getName(), createNBTFromToolPartItem(upgrade));
		return compound;
	}

	public static NbtCompound createNBTFromToolPartItem(ToolPartItem toolPartItem)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(AlloygeryToolNBT.TYPE.getName(), AlloygeryToolNBT.TOOL_PART_IDENTIFIER.getName());
		compound.putString(AlloygeryToolNBT.TOOL_PART_TYPE_IDENTIFIER.getName(), getToolPartNBTIdentifier(toolPartItem).getName());
		compound.putString(AlloygeryToolNBT.TOOL_PART_ITEM_IDENTIFIER.getName(), Registry.ITEM.getId(toolPartItem).toString());
		compound.putString(AlloygeryToolNBT.MATERIAL_IDENTIFIER.getName(), AlloygeryToolMaterialRegistry.identify(toolPartItem.getMaterial()).toString());
		if(toolPartItem instanceof IToolPartHead toolHead)
		{
			compound.putString(AlloygeryToolNBT.TOOL_TYPE_IDENTIFIER.getName(), getToolTypeNBTIdentifier(toolHead).getName());
		}
		return compound;
	}

	public static NbtCompound createToolNBTFromMaterials(AlloygeryToolMaterial head, AlloygeryToolMaterial handle, ToolType toolType)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), TOOL_IDENTIFIER.getName());
		compound.put(TOOL_PART_HEAD.getName(), createToolPartNBTFromMaterial(head, ToolPartType.HEAD, toolType));
		compound.put(TOOL_PART_BINDING.getName(), createHiddenBindingNbt());
		compound.put(TOOL_PART_HANDLE.getName(), createToolPartNBTFromMaterial(handle, ToolPartType.HANDLE, toolType));
		return compound;
	}

	public static NbtCompound createToolNBTFromMaterials(AlloygeryToolMaterial head, AlloygeryToolMaterial handle, AlloygeryToolMaterial upgrade, ToolType toolType)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), TOOL_IDENTIFIER.getName());
		compound.put(TOOL_PART_HEAD.getName(), createToolPartNBTFromMaterial(head, ToolPartType.HEAD, toolType));
		compound.put(TOOL_PART_BINDING.getName(), createHiddenBindingNbt());
		compound.put(TOOL_PART_HANDLE.getName(), createToolPartNBTFromMaterial(handle, ToolPartType.HANDLE, toolType));
		compound.put(TOOL_PART_UPGRADE.getName(), createToolPartNBTFromMaterial(upgrade, ToolPartType.UPGRADE, toolType));
		return compound;
	}

	public static NbtCompound createToolPartNBTFromMaterial(AlloygeryToolMaterial material, ToolPartType partType, ToolType toolType)
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), TOOL_PART_IDENTIFIER.getName());
		compound.putString(TOOL_PART_TYPE_IDENTIFIER.getName(), getToolPartNBTIdentifier(partType).getName());
		compound.putString(MATERIAL_IDENTIFIER.getName(), AlloygeryToolMaterialRegistry.identify(material).toString());
		if(partType == ToolPartType.HEAD)
			compound.putString(TOOL_TYPE_IDENTIFIER.getName(), getToolTypeNBTIdentifier(toolType).getName());

		return compound;
	}

	private static NbtCompound createHiddenBindingNbt()
	{
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), TOOL_PART_IDENTIFIER.getName());
		compound.putString(TOOL_PART_TYPE_IDENTIFIER.getName(), getToolPartNBTIdentifier(ToolPartType.BINDING).getName());
		compound.putString(TOOL_PART_ITEM_IDENTIFIER.getName(), Registry.ITEM.getId(Items.AIR).toString());
		compound.putString(MATERIAL_IDENTIFIER.getName(), AlloygeryToolMaterialRegistry.identify(AlloygeryToolMaterials.HIDDEN).toString());
		return compound;
	}

	public static NbtCompound modifyToolStackNBTWithUpgradePartItem(ItemStack toolStack, ToolPartItem upgradePart)
	{
		NbtCompound compound = getAlloygeryDataNBTFromItemStack(toolStack);
		compound.put(TOOL_PART_UPGRADE.getName(), createNBTFromToolPartItem(upgradePart));
		return compound;
	}

	public static NbtCompound modifyToolStackNBTWithUpgradeMaterial(ItemStack toolStack, AlloygeryToolMaterial upgradeMaterial)
	{
		NbtCompound toolNbt = getAlloygeryDataNBTFromItemStack(toolStack);
		NbtCompound compound = new NbtCompound();
		compound.putString(TYPE.getName(), TOOL_PART_IDENTIFIER.getName());
		compound.putString(TOOL_PART_TYPE_IDENTIFIER.getName(), getToolPartNBTIdentifier(ToolPartType.UPGRADE).getName());
		compound.putString(MATERIAL_IDENTIFIER.getName(), AlloygeryToolMaterialRegistry.identify(upgradeMaterial).toString());
		toolNbt.put(TOOL_PART_UPGRADE.getName(), compound);
		return toolNbt;
	}

	public static NbtCompound removeUpgradeNBTFromToolStackNBT(ItemStack toolStack)
	{
		NbtCompound compound = getAlloygeryDataNBTFromItemStack(toolStack);
		compound.remove(TOOL_PART_UPGRADE.getName());
		return compound;
	}

	public static NbtCompound getAlloygeryDataNBTFromItemStack(ItemStack itemStack)
	{
		if(itemStack == null || itemStack.isEmpty() || itemStack.getNbt() == null || itemStack.getNbt().isEmpty())
			return new NbtCompound();

		return getAlloygeryDataNBT(itemStack.getNbt());
	}

	public static NbtCompound getAlloygeryDataNBT(NbtCompound compound)
	{
		return isAlloygeryDataNBT(compound) ? compound.getCompound(ALLOYGERY_NBT_IDENTIFIER.getName()) : new NbtCompound();
	}

	public static NbtCompound getHeadPartNBTFromToolNBT(NbtCompound toolCompound)
	{
		if (isToolNBT(toolCompound))
		{
			NbtCompound compound = toolCompound.getCompound(TOOL_PART_HEAD.getName());
			if(isToolHeadPartNBT(compound))
				return compound;
		}

		return new NbtCompound();
	}

	public static NbtCompound getBindingPartNBTFromToolNBT(NbtCompound toolCompound)
	{
		if (isToolNBT(toolCompound))
		{
			NbtCompound compound = toolCompound.getCompound(TOOL_PART_BINDING.getName());
			if(isToolBindingPartNBT(compound))
				return compound;
		}

		return new NbtCompound();
	}

	public static NbtCompound getHandlePartNBTFromToolNBT(NbtCompound toolCompound)
	{
		if (isToolNBT(toolCompound))
		{
			NbtCompound compound = toolCompound.getCompound(TOOL_PART_HANDLE.getName());
			if(isToolHandlePartNBT(compound))
				return compound;
		}

		return new NbtCompound();
	}

	public static NbtCompound getUpgradePartNBTFromToolNBT(NbtCompound toolCompound)
	{
		if (isToolNBT(toolCompound))
		{
			NbtCompound compound = toolCompound.getCompound(TOOL_PART_UPGRADE.getName());
			if(isToolUpgradeNBT(compound))
				return compound;
		}

		return new NbtCompound();
	}

	public static ToolType getToolTypeFromToolNBT(NbtCompound compound)
	{
		return getToolTypeFromNBT(AlloygeryToolNBT.getByName(getHeadPartNBTFromToolNBT(compound).getString(TOOL_TYPE_IDENTIFIER.getName())));
	}

	public static ItemStack createToolPartItemStackFromNBT(NbtCompound compound)
	{
		if(!isToolPartNBT(compound))
			return ItemStack.EMPTY;

		Identifier toolPartItemIdentifier = Identifier.tryParse(compound.getString(AlloygeryToolNBT.TOOL_PART_ITEM_IDENTIFIER.getName()));
		if(toolPartItemIdentifier == null)
			return ItemStack.EMPTY;

		return new ItemStack(Registry.ITEM.get(toolPartItemIdentifier));
	}

	public static Identifier getMaterialIdentifierFromToolPartNBT(NbtCompound compound)
	{
		return isToolPartNBT(compound) ? Identifier.tryParse(compound.getString(MATERIAL_IDENTIFIER.getName())) : AlloygeryToolMaterialRegistry.getDefaultIdentifier();
	}

	public static boolean isAlloygeryDataNBT(NbtCompound compound)
	{
		return compound != null && !compound.isEmpty() && compound.contains(ALLOYGERY_NBT_IDENTIFIER.getName());
	}

	public static boolean isToolNBT(NbtCompound compound)
	{
		return compound != null && !compound.isEmpty() && compound.getString(TYPE.getName()).equals(TOOL_IDENTIFIER.getName());
	}

	public static boolean isToolPartNBT(NbtCompound compound)
	{
		return compound != null && !compound.isEmpty() && compound.getString(TYPE.getName()).equals(TOOL_PART_IDENTIFIER.getName());
	}

	public static boolean isToolHeadPartNBT(NbtCompound compound)
	{
		return isToolPartNBT(compound) && compound.getString(TOOL_PART_TYPE_IDENTIFIER.getName()).equals(TOOL_PART_HEAD.getName());
	}

	public static boolean isToolBindingPartNBT(NbtCompound compound)
	{
		return isToolPartNBT(compound) && compound.getString(TOOL_PART_TYPE_IDENTIFIER.getName()).equals(TOOL_PART_BINDING.getName());
	}

	public static boolean isToolHandlePartNBT(NbtCompound compound)
	{
		return isToolPartNBT(compound) && compound.getString(TOOL_PART_TYPE_IDENTIFIER.getName()).equals(TOOL_PART_HANDLE.getName());
	}

	public static boolean isToolUpgradeNBT(NbtCompound compound)
	{
		return isToolPartNBT(compound) && compound.getString(TOOL_PART_TYPE_IDENTIFIER.getName()).equals(TOOL_PART_UPGRADE.getName());
	}
}
