package amorphia.alloygery.content.material;

import amorphia.alloygery.content.item.IMaterialItem;
import com.google.common.collect.Maps;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.Objects;

public class AlloygeryMaterialHelper
{
	public static final Map<AlloygeryMaterial, Ingredient> REPAIR_INGREDIENT_MAP = Maps.newIdentityHashMap();

	public static Ingredient getRepairIngredient(ItemStack stack)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(stack);
		return REPAIR_INGREDIENT_MAP.getOrDefault(headMaterial, Ingredient.EMPTY);
	}

	public static int getMaxDurability(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final int base = (int) (headMaterial.head_durability * bindingMaterial.durability_multiplier);
		final int upgrade = (int) (base * upgradeMaterial.durability_multiplier + upgradeMaterial.head_durability);

		return upgrade;
	}

	public static int getEnchantability(NbtCompound compound)
	{
		ItemStack itemStack = ItemStack.fromNbt(compound);
		boolean armor = itemStack.getItem() instanceof ArmorItem;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = (armor ? headMaterial.armor_enchantability : headMaterial.tool_enchantability) * bindingMaterial.enchantability_multiplier;
		final float upgrade = base * upgradeMaterial.enchantability_multiplier + (armor ? upgradeMaterial.armor_enchantability : upgradeMaterial.tool_enchantability);

		return (int) upgrade;
	}

	public static float getMiningSpeed(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.speed * handleMaterial.speed_multiplier;
		final float upgrade = base * upgradeMaterial.speed_multiplier + upgradeMaterial.speed;

		return upgrade;
	}

	public static float getAttackDamage(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.damage * handleMaterial.damage_multiplier;
		final float upgrade = base * upgradeMaterial.damage_multiplier + upgradeMaterial.damage;

		return upgrade;
	}

	public static int getMiningLevel(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final int base = headMaterial.level;
		final int upgrade = base + upgradeMaterial.level;

		return upgrade;
	}

	public static AlloygeryMaterial getMaterial(ItemStack stack, NBT_KEYS key, boolean setMaterialNbtIfAbsent)
	{
		NbtCompound compound = stack.getNbt();

		if (compound != null && compound.contains(key.value))
		{
			return AlloygeryMaterial.getById(compound.getString(key.value));
		}
		else
		{
			AlloygeryMaterial material = stack.getItem() instanceof IMaterialItem materialItem ? materialItem.getAlloygeryMaterial() : getMaterialFromIdentifier(stack);
			if(setMaterialNbtIfAbsent && material != AlloygeryMaterials.UNKNOWN)
				setMaterial(stack, material, key);

			return material;
		}
	}

	public static AlloygeryMaterial getMaterial(ItemStack stack, NBT_KEYS key)
	{
		return getMaterial(stack, key, false);
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

	public static AlloygeryMaterial getUpgradeMaterial(ItemStack stack)
	{
		return getUpgradeMaterial(stack.getNbt());
	}

	public static AlloygeryMaterial getUpgradeMaterial(NbtCompound compound)
	{
		return compound == null ? AlloygeryMaterials.UNKNOWN : AlloygeryMaterial.getById(compound.getString(NBT_KEYS.UPGRADE_MATERIAL.value));
	}

	public static ItemStack setUpgradeMaterial(ItemStack stack, AlloygeryMaterial material)
	{
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.getId(material);
		if(material == AlloygeryMaterials.UNKNOWN)
		{
			stack.removeSubNbt(NBT_KEYS.UPGRADE_MATERIAL.value);
		}
		else
		{
			stack.getOrCreateNbt().putString(NBT_KEYS.UPGRADE_MATERIAL.value, identifier.toString());
		}
		return stack;
	}

	public static AlloygeryMaterial getMaterialFromIdentifier(ItemStack stack)
	{
		Item item = stack.getItem();
		Identifier identifier = Registry.ITEM.getId(item);
		String namespace = identifier.getNamespace();
		String path = identifier.getPath();

		do
		{
			AlloygeryMaterial material = AlloygeryMaterial.getById(namespace + ":materials/" + path);
			if(material != AlloygeryMaterials.UNKNOWN)
				return material;

			if(!path.contains("_"))
				return AlloygeryMaterials.UNKNOWN;

			int indexOfUnderscore = path.lastIndexOf("_");
			path = path.substring(0, indexOfUnderscore == -1 ? 0 : indexOfUnderscore);
		}
		while (!Objects.equals(path, ""));

		return AlloygeryMaterials.UNKNOWN;
	}

	//TODO: move this out of here
	public enum NBT_KEYS
	{
		HEAD_MATERIAL("HeadMaterial"),
		HANDLE_MATERIAL("HandleMaterial"),
		BINDING_MATERIAL("BindingMaterial"),
		UPGRADE_MATERIAL("UpgradeMaterial"),
		PART_MATERIAL("PartMaterial"),
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
