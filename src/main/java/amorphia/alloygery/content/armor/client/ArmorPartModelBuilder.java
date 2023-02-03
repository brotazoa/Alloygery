package amorphia.alloygery.content.armor.client;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.item.ArmorType;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class ArmorPartModelBuilder
{
	private static final Identifier HELMET_BASE_TEXTURE = Alloygery.identifier("armor/parts/helmet/helmet_base");
	private static final Identifier CHESTPLATE_BASE_TEXTURE = Alloygery.identifier("armor/parts/chestplate/chestplate_base");
	private static final Identifier LEGGINGS_BASE_TEXTURE = Alloygery.identifier("armor/parts/leggings/leggings_base");
	private static final Identifier BOOTS_BASE_TEXTURE = Alloygery.identifier("armor/parts/boots/boots_base");

	private static final Map<Identifier, Supplier<String>> MODEL_SUPPLIER_FOR_IDENTIFIER = Maps.newIdentityHashMap();

	public static void register(Identifier identifier, Supplier<String> modelSupplier)
	{
		MODEL_SUPPLIER_FOR_IDENTIFIER.put(identifier, modelSupplier);
	}

	public static Optional<Map.Entry<Identifier, Supplier<String>>> getModelSupplierForIdentifier(Identifier identifier)
	{
		return MODEL_SUPPLIER_FOR_IDENTIFIER.entrySet().stream().filter(entry -> entry.getKey().equals(identifier)).findFirst();
	}

	public static Supplier<String> createArmorItemModelJson(ArmorType armorType, AlloygeryArmorMaterial base, AlloygeryArmorMaterial armor, AlloygeryArmorMaterial upgrade)
	{
		return () -> "{"
				+ "\"parent\": \"minecraft:item/generated\","
				+ "\"textures\": {"
				+ "\"layer0\": \"" + getBaseTextureForArmorType(armorType).toString() + "\","
				+ "\"layer1\": \"" + base.getItemTexture(armorType).toString() + "\","
				+ "\"layer2\": \"" + armor.getItemTexture(armorType).toString() + "\","
				+ "\"layer3\": \"" + upgrade.getItemTexture(armorType).toString() + "\""
				+ "}"
				+ "}";
	}

	public static Supplier<String> createArmorPartItemModelJson(ArmorType armorType, AlloygeryArmorMaterial material)
	{
		return () -> "{"
				+ "\"parent\": \"minecraft:item/generated\","
				+ "\"textures\": {"
				+ "\"layer0\": \"" + getBaseTextureForArmorType(armorType).toString() + "\","
				+ "\"layer1\": \"" + material.getItemTexture(armorType).toString() + "\""
				+ "}"
				+ "}";
	}

	private static Identifier getBaseTextureForArmorType(ArmorType armorType)
	{
		return switch (armorType)
		{
			case HELMET -> HELMET_BASE_TEXTURE;
			case CHESTPLATE -> CHESTPLATE_BASE_TEXTURE;
			case LEGGINGS -> LEGGINGS_BASE_TEXTURE;
			case BOOTS -> BOOTS_BASE_TEXTURE;
		};
	}
}
