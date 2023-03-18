package amorphia.alloygery.content.tools.client;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.tools.ToolMaterialHelper;
import amorphia.alloygery.content.tools.ToolNBTHelper;
import amorphia.alloygery.content.tools.item.part.ToolUpgradeType;
import amorphia.alloygery.content.tools.item.tool.IDynamicTool;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterial;
import amorphia.alloygery.content.tools.material.AlloygeryToolMaterials;
import amorphia.alloygery.content.tools.registry.AlloygeryToolMaterialRegistry;
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
				case 2 -> ToolMaterialHelper.getBindingMaterial(tool) == AlloygeryToolMaterials.HIDDEN ? getMaterialColor(ToolMaterialHelper.getHeadMaterial(tool)) : getMaterialColor(ToolMaterialHelper.getBindingMaterial(tool));
				case 3 -> getMaterialColor(ToolMaterialHelper.getUpgradeMaterial(tool));
				default -> -1;
			};
		}
		else
		{
			return switch (tintIndex)
			{
				case 0 -> getMaterialColor(AlloygeryToolMaterials.VANILLA_STICK);
				case 1, 2 -> tool.getItem() instanceof IDynamicTool dynamicTool ? getMaterialColor(dynamicTool.getDefaultHeadMaterial()) : getMaterialColor(
						AlloygeryToolMaterials.HIDDEN);
				case 3 -> tool.getItem() instanceof IDynamicTool dynamicTool ? getMaterialColorFromUpgradeType(dynamicTool.getToolUpgradeType()) : getMaterialColor(
						AlloygeryToolMaterials.HIDDEN);
				default -> -1;
			};
		}
	}

	private static int getMaterialColor(AlloygeryToolMaterial material)
	{
		return material == null ? AlloygeryToolMaterialRegistry.get(AlloygeryToolMaterialRegistry.getDefaultIdentifier()).getMaterialColor() : material.getMaterialColor();
	}

	private static int getMaterialColorFromUpgradeType(ToolUpgradeType upgradeType)
	{
		if(upgradeType == null)
			return -1;

		return switch (upgradeType)
		{
			case EMBOSSED -> getMaterialColor(AlloygeryToolMaterials.EMERALD);
			case PLATED -> getMaterialColor(AlloygeryToolMaterials.NETHERITE);
			case TIPPED -> getMaterialColor(AlloygeryToolMaterials.DIAMOND);
			case NONE -> -1;
		};
	}
}
