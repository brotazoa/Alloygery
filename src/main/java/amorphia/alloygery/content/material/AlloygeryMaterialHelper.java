package amorphia.alloygery.content.material;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.rmi.registry.Registry;
import java.util.Map;

public class AlloygeryMaterialHelper
{
	public static final Map<AlloygeryMaterial, Ingredient> REPAIR_INGREDIENT_MAP = Maps.newIdentityHashMap();

	public static Ingredient getRepairIngredient(ItemStack stack)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(stack);
		return REPAIR_INGREDIENT_MAP.getOrDefault(headMaterial, Ingredient.EMPTY);
	}

	public static int getMaxDurability(ItemStack stack)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(stack);
		return headMaterial.head_durability;
	}

	public static int getEnchantability(ItemStack stack)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(stack);
		return headMaterial.enchantability;
	}

	public static float getMiningSpeed(ItemStack stack)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(stack);
		return headMaterial.speed;
	}

	public static float getAttackDamage(ItemStack stack)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(stack);
		return headMaterial.damage;
	}

	public static int getMiningLevel(ItemStack stack)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(stack);
		return headMaterial.level;
	}

	public static AlloygeryMaterial getMaterial(ItemStack stack, NBT_KEYS key)
	{
		return getMaterial(stack.getNbt(), key);
	}

	public static AlloygeryMaterial getMaterial(NbtCompound compound, NBT_KEYS key)
	{
		return (compound == null || key == null) ? AlloygeryMaterials.UNKNOWN : AlloygeryMaterial.getById(compound.getString(key.value));
	}

	public static ItemStack setMaterial(ItemStack stack, AlloygeryMaterial material, NBT_KEYS key)
	{
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.getId(material);
		if (material == AlloygeryMaterials.UNKNOWN)
		{
			stack.removeSubNbt(key.value);
		}
		else
		{
			stack.getOrCreateNbt().putString(key.value, identifier.toString());
		}
		return stack;
	}

	public static AlloygeryMaterial getHeadMaterial(ItemStack stack)
	{
		return getHeadMaterial(stack.getNbt());
	}

	public static AlloygeryMaterial getHeadMaterial(NbtCompound compound)
	{
		return compound == null ? AlloygeryMaterials.UNKNOWN : AlloygeryMaterial.getById(compound.getString(NBT_KEYS.HEAD_MATERIAL.value));
	}

	public static ItemStack setHeadMaterial(ItemStack stack, AlloygeryMaterial material)
	{
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.getId(material);
		if (material == AlloygeryMaterials.UNKNOWN)
		{
			stack.removeSubNbt(NBT_KEYS.HEAD_MATERIAL.value);
		}
		else
		{
			stack.getOrCreateNbt().putString(NBT_KEYS.HEAD_MATERIAL.value, identifier.toString());
		}
		return stack;
	}

	public static AlloygeryMaterial getHandleMaterial(ItemStack stack)
	{
		return getHandleMaterial(stack.getNbt());
	}

	public static AlloygeryMaterial getHandleMaterial(NbtCompound compound)
	{
		return compound == null ? AlloygeryMaterials.UNKNOWN : AlloygeryMaterial.getById(compound.getString(NBT_KEYS.HANDLE_MATERIAL.value));
	}

	public static ItemStack setHandleMaterial(ItemStack stack, AlloygeryMaterial material)
	{
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.getId(material);
		if (material == AlloygeryMaterials.UNKNOWN)
		{
			stack.removeSubNbt(NBT_KEYS.HANDLE_MATERIAL.value);
		}
		else
		{
			stack.getOrCreateNbt().putString(NBT_KEYS.HANDLE_MATERIAL.value, identifier.toString());
		}
		return stack;
	}

	public static AlloygeryMaterial getBindingMaterial(ItemStack stack)
	{
		return getBindingMaterial(stack.getNbt());
	}

	public static AlloygeryMaterial getBindingMaterial(NbtCompound compound)
	{
		return compound == null ? AlloygeryMaterials.UNKNOWN : AlloygeryMaterial.getById(compound.getString(NBT_KEYS.BINDING_MATERIAL.value));
	}

	public static ItemStack setBindingMaterial(ItemStack stack, AlloygeryMaterial material)
	{
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.getId(material);
		if (material == AlloygeryMaterials.UNKNOWN)
		{
			stack.removeSubNbt(NBT_KEYS.BINDING_MATERIAL.value);
		}
		else
		{
			stack.getOrCreateNbt().putString(NBT_KEYS.BINDING_MATERIAL.value, identifier.toString());
		}
		return stack;
	}

	public enum NBT_KEYS
	{
		HEAD_MATERIAL("HeadMaterial"),
		HANDLE_MATERIAL("HandleMaterial"),
		BINDING_MATERIAL("BindingMaterial"),
		;

		final String value;

		NBT_KEYS(String value)
		{
			this.value = value;
		}

		public static String getValue(NBT_KEYS key)
		{
			return key.value;
		}
	}
}
