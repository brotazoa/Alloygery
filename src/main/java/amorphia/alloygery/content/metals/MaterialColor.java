package amorphia.alloygery.content.metals;

public enum MaterialColor
{
	FLINT("flint", 3026221),
	TIN("tin", 14547455),
	COPPER("copper", 15433553),
	BRONZE("bronze", 7556410),
	IRON("iron", 15198183),
	GOLD("gold", 16573743),
	EMERALD("emerald", 1564002),
	ANTANIUM("antanium", 14329677),
	DIAMOND("diamond", 3402699),
	STEEL("steel", 4408907),
	NETHERITE("netherite", 5192766),
	NICKEL("nickel", 6314062),
	INVAR("invar", 10789019),
	CONSTANTAN("constantan", 11558984),
	CUPRONICKEL("cupronickel", 5257772),
	TITANIUM("titanium", 5990506),
	TITANIUM_GOLD("titanium_gold", 13086590),
	NITINOL("nitinol", 6185051),
	WOODEN("wooden", 6835742),
	LEATHER("leather", 10511680),
	WHITE_WOOL("white_wool", 16383998),
	;

	private final String name;
	private final int color;

	MaterialColor(String name, int color)
	{
		this.name = name;
		this.color = color;
	}

	public String getName()
	{
		return name;
	}

	public int getColor()
	{
		return color;
	}
}
