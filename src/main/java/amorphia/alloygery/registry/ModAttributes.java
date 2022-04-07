package amorphia.alloygery.registry;

import amorphia.alloygery.Alloygery;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class ModAttributes
{
	public static final UUID ALLOYGERY_LUCK_MODIFIER = UUID.fromString("7c170574-80c5-474b-9904-870348aff0e1");

	//unused
	public static final EntityAttribute CRIT_CHANCE;

	public static void init() {}

	static EntityAttribute register(EntityAttribute attribute)
	{
		return Registry.register(Registry.ATTRIBUTE, Alloygery.identifier(attribute.getTranslationKey()), attribute);
	}

	static
	{
		CRIT_CHANCE = register(new ClampedEntityAttribute("generic.crit_chance", 0.0d, 0.0d, 1.0d).setTracked(true));
	}
}
