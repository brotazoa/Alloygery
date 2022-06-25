package amorphia.alloygery.content.material;

import net.minecraft.nbt.NbtCompound;

public class AlloygeryArmorMaterialHelper
{
	public static boolean isFireproof(NbtCompound compound)
	{
		return AlloygeryToolMaterialHelper.getHeadMaterial(compound).armor.fireproof;
	}

	public static boolean isPiglinLoved(NbtCompound compound)
	{
		return AlloygeryToolMaterialHelper.getHeadMaterial(compound).armor.piglin_loved;
	}
}
