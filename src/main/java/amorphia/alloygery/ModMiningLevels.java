package amorphia.alloygery;

import net.fabricmc.yarn.constants.MiningLevels;

import java.util.Locale;

public final class ModMiningLevels
{
	public static final int HAND = MiningLevels.HAND;
	public static final int WOOD = MiningLevels.WOOD;
	public static final int STONE = MiningLevels.STONE;
	public static final int BRONZE = MiningLevels.IRON;
	public static final int IRON = MiningLevels.IRON;
	public static final int DIAMOND = MiningLevels.DIAMOND;
	public static final int STEEL = MiningLevels.DIAMOND;
	public static final int NETHERITE = MiningLevels.NETHERITE;
	public static final int NICKEL = MiningLevels.NETHERITE;
	public static final int TITANIUM = MiningLevels.NETHERITE + 1;

	public static int levelFromString(String level)
	{
		return switch (level.toLowerCase(Locale.ROOT))
		{
			case "hand" -> HAND;
			case "wood" -> WOOD;
			case "stone" -> STONE;
			case "bronze" -> BRONZE;
			case "iron" -> IRON;
			case "diamond" -> DIAMOND;
			case "steel" -> STEEL;
			case "netherite" -> NETHERITE;
			case "nickel" -> NICKEL;
			case "titanium" -> TITANIUM;
			default -> -2;
		};
	}
}
