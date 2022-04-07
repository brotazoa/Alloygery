package amorphia.alloygery.content.material;

import amorphia.alloygery.content.item.IMaterialItem;
import com.google.common.collect.Maps;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.Objects;

public class AlloygeryToolMaterialHelper
{
	public static final Map<AlloygeryMaterial, Ingredient> REPAIR_INGREDIENT_MAP = Maps.newIdentityHashMap();

	public static Ingredient getRepairIngredient(ItemStack stack)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(stack);
		return REPAIR_INGREDIENT_MAP.computeIfAbsent(headMaterial, material -> Ingredient.fromJson(material.repair_ingredient));
	}

	public static int getMiningLevel(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final int base = headMaterial.tool_base.mining_level;
		final int binding = base + bindingMaterial.tool_binding.mining_level;
		final int handle = binding + handleMaterial.tool_handle.mining_level;
		final int upgrade = handle + upgradeMaterial.tool_upgrade.mining_level;

		return upgrade;
	}

	public static int getMiningLevelByPart(NbtCompound compound, int[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final int base = headMaterial.tool_base.mining_level;
		final int binding = base + bindingMaterial.tool_binding.mining_level;
		final int handle = binding + handleMaterial.tool_handle.mining_level;
		final int upgrade = handle + upgradeMaterial.tool_upgrade.mining_level;

		returnArray[0] = base;
		returnArray[1] = binding;
		returnArray[2] = handle;
		returnArray[3] = upgrade;

		return upgrade;
	}

	public static int getMaxDurability(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.durability * bindingMaterial.tool_binding.durability_multiplier + bindingMaterial.tool_binding.durability;
		final float handle = base * handleMaterial.tool_handle.durability_multiplier + handleMaterial.tool_handle.durability;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.durability_multiplier + upgradeMaterial.tool_upgrade.durability;

		return (int) upgrade;
	}

	public static int getMaxDurabilityByPart(NbtCompound compound, int[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.durability;
		final float binding = base * bindingMaterial.tool_binding.durability_multiplier + bindingMaterial.tool_binding.durability;
		final float handle = binding * handleMaterial.tool_handle.durability_multiplier + handleMaterial.tool_handle.durability;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.durability_multiplier + upgradeMaterial.tool_upgrade.durability;

		returnArray[0] = (int) base;
		returnArray[1] = (int) binding;
		returnArray[2] = (int) handle;
		returnArray[3] = (int) upgrade;

		return (int) upgrade;
	}

	public static int getEnchantability(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.enchantability * bindingMaterial.tool_binding.enchantability_multiplier + bindingMaterial.tool_binding.enchantability;
		final float handle = base * handleMaterial.tool_handle.enchantability_multiplier + handleMaterial.tool_handle.enchantability;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.enchantability_multiplier + upgradeMaterial.tool_upgrade.enchantability;

		return (int) upgrade;
	}

	public static int getEnchantabilityByPart(NbtCompound compound, int[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.enchantability;
		final float binding = base * bindingMaterial.tool_binding.enchantability_multiplier + bindingMaterial.tool_binding.enchantability;
		final float handle = binding * handleMaterial.tool_handle.enchantability_multiplier + handleMaterial.tool_handle.enchantability;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.enchantability_multiplier + upgradeMaterial.tool_upgrade.enchantability;

		returnArray[0] = (int) base;
		returnArray[1] = (int) binding;
		returnArray[2] = (int) handle;
		returnArray[3] = (int) upgrade;

		return (int) upgrade;
	}

	public static float getMiningSpeed(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.mining_speed * bindingMaterial.tool_binding.mining_speed_multiplier + bindingMaterial.tool_binding.mining_speed;
		final float handle = base * handleMaterial.tool_handle.mining_speed_multiplier + handleMaterial.tool_handle.mining_speed;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.mining_speed_multiplier + upgradeMaterial.tool_upgrade.mining_speed;

		return upgrade;
	}

	public static float getMiningSpeedByPart(NbtCompound compound, float[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.mining_speed;
		final float binding = base * bindingMaterial.tool_binding.mining_speed_multiplier + bindingMaterial.tool_binding.mining_speed;
		final float handle = binding * handleMaterial.tool_handle.mining_speed_multiplier + handleMaterial.tool_handle.mining_speed;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.mining_speed_multiplier + upgradeMaterial.tool_upgrade.mining_speed;

		returnArray[0] = base;
		returnArray[1] = binding;
		returnArray[2] = handle;
		returnArray[3] = upgrade;

		return upgrade;
	}

	public static float getAttackSpeed(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.attack_speed * bindingMaterial.tool_binding.attack_speed_multiplier + bindingMaterial.tool_binding.attack_speed;
		final float handle = base * handleMaterial.tool_handle.attack_speed_multiplier + handleMaterial.tool_handle.attack_speed;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.attack_speed_multiplier + upgradeMaterial.tool_upgrade.attack_speed;

		return upgrade;
	}

	public static float getAttackSpeedByPart(NbtCompound compound, float[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.attack_speed;
		final float binding = base * bindingMaterial.tool_binding.attack_speed_multiplier + bindingMaterial.tool_binding.attack_speed;
		final float handle = binding * handleMaterial.tool_handle.attack_speed_multiplier + handleMaterial.tool_handle.attack_speed;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.attack_speed_multiplier + upgradeMaterial.tool_upgrade.attack_speed;

		returnArray[0] = base;
		returnArray[1] = binding;
		returnArray[2] = handle;
		returnArray[3] = upgrade;

		return upgrade;
	}

	public static float getAttackDamage(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.attack_damage * bindingMaterial.tool_binding.attack_damage_multiplier + bindingMaterial.tool_binding.attack_damage;
		final float handle = base * handleMaterial.tool_handle.attack_damage_multiplier + handleMaterial.tool_handle.attack_damage;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.attack_damage_multiplier + upgradeMaterial.tool_upgrade.attack_damage;

		return upgrade;
	}

	public static float getAttackDamageByPart(NbtCompound compound, float[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.attack_damage;
		final float binding = base * bindingMaterial.tool_binding.attack_damage_multiplier + bindingMaterial.tool_binding.attack_damage;
		final float handle = binding * handleMaterial.tool_handle.attack_damage_multiplier + handleMaterial.tool_handle.attack_damage;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.attack_damage_multiplier + upgradeMaterial.tool_upgrade.attack_damage;

		returnArray[0] = base;
		returnArray[1] = binding;
		returnArray[2] = handle;
		returnArray[3] = upgrade;

		return upgrade;
	}

	public static float getLuck(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.luck * bindingMaterial.tool_binding.luck_multiplier + bindingMaterial.tool_binding.luck;
		final float handle = base * handleMaterial.tool_handle.luck_multiplier + handleMaterial.tool_handle.luck;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.luck_multiplier + upgradeMaterial.tool_upgrade.luck;

		return upgrade;
	}

	public static float getLuckByPart(NbtCompound compound, float[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		final float base = headMaterial.tool_base.luck;
		final float binding = base * bindingMaterial.tool_binding.luck_multiplier + bindingMaterial.tool_binding.luck;
		final float handle = binding * handleMaterial.tool_handle.luck_multiplier + handleMaterial.tool_handle.luck;
		final float upgrade = handle * upgradeMaterial.tool_upgrade.luck_multiplier + upgradeMaterial.tool_upgrade.luck;

		returnArray[0] = base;
		returnArray[1] = binding;
		returnArray[2] = handle;
		returnArray[3] = upgrade;

		return upgrade;
	}

	public static boolean isFireproof(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		return headMaterial.tool_base.fireproof || bindingMaterial.tool_binding.fireproof || handleMaterial.tool_handle.fireproof || upgradeMaterial.tool_upgrade.fireproof;
	}

	public static boolean isFireproofByPart(NbtCompound compound, boolean[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		returnArray[0] = headMaterial.tool_base.fireproof;
		returnArray[1] = bindingMaterial.tool_binding.fireproof;
		returnArray[2] = handleMaterial.tool_handle.fireproof;
		returnArray[3] = upgradeMaterial.tool_upgrade.fireproof;

		return headMaterial.tool_base.fireproof || bindingMaterial.tool_binding.fireproof || handleMaterial.tool_handle.fireproof || upgradeMaterial.tool_upgrade.fireproof;
	}

	public static boolean isPiglinLoved(NbtCompound compound)
	{
		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		return headMaterial.tool_base.piglin_loved || bindingMaterial.tool_binding.piglin_loved || handleMaterial.tool_handle.piglin_loved || upgradeMaterial.tool_upgrade.piglin_loved;
	}

	public static boolean isPiglinLovedByPart(NbtCompound compound, boolean[] returnArray)
	{
		assert returnArray.length == 4;

		AlloygeryMaterial headMaterial = getHeadMaterial(compound);
		AlloygeryMaterial bindingMaterial = getBindingMaterial(compound);
		AlloygeryMaterial handleMaterial = getHandleMaterial(compound);
		AlloygeryMaterial upgradeMaterial = getUpgradeMaterial(compound);

		returnArray[0] = headMaterial.tool_base.piglin_loved;
		returnArray[1] = bindingMaterial.tool_binding.piglin_loved;
		returnArray[2] = handleMaterial.tool_handle.piglin_loved;
		returnArray[3] = upgradeMaterial.tool_upgrade.piglin_loved;

		return returnArray[0] || returnArray[1] || returnArray[2] || returnArray[3];
	}

	public static AlloygeryMaterial getMaterial(NbtCompound compound, NBT_KEYS keys)
	{
		return getMaterial(ItemStack.fromNbt(compound), keys);
	}

	public static AlloygeryMaterial getMaterial(ItemStack stack, NBT_KEYS key)
	{
		NbtCompound compound = stack.getNbt();

		if (compound != null && compound.contains(key.value))
		{
			return AlloygeryMaterial.getById(compound.getString(key.value));
		}
		else
		{
			return stack.getItem() instanceof IMaterialItem materialItem ? materialItem.getAlloygeryMaterial() : getMaterialFromIdentifier(stack);
		}
	}

	public static ItemStack setMaterial(ItemStack stack, AlloygeryMaterial material, NBT_KEYS key)
	{
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.inverse().get(material);
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
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.inverse().get(material);
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
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.inverse().get(material);
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
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.inverse().get(material);
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
		Identifier identifier = AlloygeryMaterials.ALLOYGERY_MATERIALS.inverse().get(material);
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

		if(item instanceof IMaterialItem materialItem)
			return materialItem.getAlloygeryMaterial();

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
