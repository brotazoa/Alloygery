package amorphia.alloygery.content.tools.client;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolMaterialHelper;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.part.ToolUpgradeType;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import amorphia.alloygery.content.tools.material.ToolMaterial;
import amorphia.alloygery.content.tools.material.ToolMaterials;
import amorphia.alloygery.content.tools.registry.ToolMaterialRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public class ToolClientReloadListener implements SimpleSynchronousResourceReloadListener
{
	public static final ToolClientReloadListener INSTANCE = new ToolClientReloadListener();
	public static final Identifier ID = Alloygery.identifier("tool_client_reload_listener");

	private ToolClientReloadListener() {} //no op

	@Override
	public Identifier getFabricId()
	{
		return ToolClientReloadListener.ID;
	}

	@Override
	public void reload(ResourceManager manager)
	{
		IDynamicTool.TOOL_ITEMS.forEach(item -> ColorProviderRegistry.ITEM.register(ToolClientReloadListener::getMaterialColorFromToolStack, item));
	}

	private static int getMaterialColorFromToolStack(ItemStack tool, int tintIndex)
	{
		if(tool == null || tool.isEmpty())
			return -1;

		if(ToolNBTHelper.isAlloygeryDataNBT(tool.getNbt()))
		{
			return switch (tintIndex)
			{
				case 0 -> getMaterialColor(ToolMaterialHelper.getHandleMaterial(tool));
				case 1 -> getMaterialColor(ToolMaterialHelper.getHeadMaterial(tool));
				case 2 -> ToolMaterialHelper.getBindingMaterial(tool) == ToolMaterials.HIDDEN ? getMaterialColor(ToolMaterialHelper.getHeadMaterial(tool)) : getMaterialColor(ToolMaterialHelper.getBindingMaterial(tool));
				case 3 -> getMaterialColor(ToolMaterialHelper.getUpgradeMaterial(tool));
				default -> -1;
			};
		}
		else
		{
			return switch (tintIndex)
			{
				//case 0 -> getMaterialColor(ToolMaterials.VANILLA_STICK);
				case 0, 1, 2 -> getMaterialColor(ToolMaterials.HIDDEN);
				case 3 -> tool.getItem() instanceof IDynamicTool dynamicTool ? getMaterialColorFromUpgradeType(dynamicTool.getToolUpgradeType()) : getMaterialColor(ToolMaterials.HIDDEN);
				default -> -1;
			};
		}
	}

	private static int getMaterialColor(ToolMaterial material)
	{
		return material == null ? ToolMaterialRegistry.get(ToolMaterialRegistry.getDefaultIdentifier()).getMaterialColor() : material.getMaterialColor();
	}

	private static int getMaterialColorFromUpgradeType(ToolUpgradeType upgradeType)
	{
		if(upgradeType == null)
			return -1;

		return switch (upgradeType)
		{
			case EMBOSSED -> getMaterialColor(ToolMaterials.EMERALD);
			case PLATED -> getMaterialColor(ToolMaterials.NETHERITE);
			case TIPPED -> getMaterialColor(ToolMaterials.DIAMOND);
			case NONE -> -1;
		};
	}
}
