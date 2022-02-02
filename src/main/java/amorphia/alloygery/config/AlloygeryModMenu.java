package amorphia.alloygery.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

public class AlloygeryModMenu implements ModMenuApi
{
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory()
	{
		return parent -> AlloygeryClothConfig.getConfigBuilder().setParentScreen(parent).setSavingRunnable(() -> {
			AlloygeryConfigSerializer.serialize(AlloygeryConfig.ALLOYGERY_CONFIG);
		}).build();
	}
}
