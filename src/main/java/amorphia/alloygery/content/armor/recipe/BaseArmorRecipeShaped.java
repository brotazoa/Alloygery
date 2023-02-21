package amorphia.alloygery.content.armor.recipe;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.armor.ArmorNBTHelper;
import amorphia.alloygery.content.armor.item.IDynamicArmor;
import amorphia.alloygery.content.armor.material.AlloygeryArmorMaterial;
import amorphia.alloygery.content.armor.registry.AlloygeryArmorMaterialRegistry;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;

public class BaseArmorRecipeShaped extends ShapedRecipe
{
	protected AlloygeryArmorMaterial material;
	protected Identifier material_identifier;

	public BaseArmorRecipeShaped(ShapedRecipe recipe, Identifier with_material)
	{
		super(recipe.getId(), recipe.getGroup(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getOutput());
		this.material = AlloygeryArmorMaterialRegistry.get(with_material);
		this.material_identifier = with_material == null ? new Identifier("alloygery:null_identifier") : with_material;
	}

	//apply nbt data
	@Override
	public ItemStack getOutput()
	{
		ItemStack outputStack = super.getOutput();
		if(outputStack.getItem() instanceof IDynamicArmor dynamicArmor)
		{
			AlloygeryArmorMaterial baseMaterial = AlloygeryArmorMaterialRegistry.get(material_identifier);
			if(AlloygeryArmorMaterialRegistry.identify(baseMaterial).equals(AlloygeryArmorMaterialRegistry.getDefaultIdentifier()))
				baseMaterial = dynamicArmor.getDefaultBaseMaterial();

			ArmorNBTHelper.addAlloygeryNBTToArmorStack(outputStack, ArmorNBTHelper.createArmorNBT(baseMaterial));
		}
		return outputStack;
	}

	public static class Type implements RecipeType<BaseArmorRecipeShaped>
	{
		public static final Identifier ID = Alloygery.identifier("base_armor_shaped");
		public static final Type INSTANCE = new Type();

		private Type() {} //no op
	}

	public static class Serializer extends ShapedRecipe.Serializer
	{
		public static final Serializer INSTANCE = new Serializer();

		protected Serializer() {}

		@Override
		public ShapedRecipe read(Identifier identifier, JsonObject jsonObject)
		{
			Identifier with_material = jsonObject.has("with_material") ? Identifier.tryParse(jsonObject.get("with_material").getAsString()) : new Identifier("alloygery:no_extra_material");
			return new BaseArmorRecipeShaped(super.read(identifier, jsonObject), with_material);
		}

		@Override
		public ShapedRecipe read(Identifier identifier, PacketByteBuf packetByteBuf)
		{
			return new BaseArmorRecipeShaped(super.read(identifier, packetByteBuf), packetByteBuf.readIdentifier());
		}

		@Override
		public void write(PacketByteBuf packetByteBuf, ShapedRecipe shapedRecipe)
		{
			super.write(packetByteBuf, shapedRecipe);
			packetByteBuf.writeIdentifier(((BaseArmorRecipeShaped)shapedRecipe).material_identifier);
		}
	}
}
