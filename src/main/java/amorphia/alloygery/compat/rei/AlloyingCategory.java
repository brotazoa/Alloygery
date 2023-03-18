package amorphia.alloygery.compat.rei;

import amorphia.alloygery.content.machines.registry.MachineBlockRegistry;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.ArrayList;
import java.util.List;

public class AlloyingCategory implements DisplayCategory<AlloyingDisplay>
{
	public static final TranslatableText TITLE = new TranslatableText("rei.alloygery.alloying");
	public static final EntryStack<ItemStack> ICON = EntryStacks.of(MachineBlockRegistry.ALLOY_KILN);

	@Override
	public Renderer getIcon()
	{
		return ICON;
	}

	@Override
	public Text getTitle()
	{
		return TITLE;
	}

	@Override
	public CategoryIdentifier<? extends AlloyingDisplay> getCategoryIdentifier()
	{
		return AlloygeryPlugin.ALLOYING_CATEGORY;
	}

	@Override
	public List<Widget> setupDisplay(AlloyingDisplay display, Rectangle bounds)
	{
		List<Widget> widgets = new ArrayList<>();

		Point startPoint = new Point(bounds.getCenterX() - 64, bounds.getCenterY() - 16);
		Point outputPoint = new Point(startPoint.x + 90, startPoint.y + 8);

		widgets.add(Widgets.createRecipeBase(bounds));
		widgets.add(Widgets.createArrow(new Point(startPoint.x + 53, startPoint.y + 8)));

		List<EntryIngredient> inputs = display.getInputEntries();
		widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y)).entries(inputs.get(0)).markInput());
		if (inputs.size() > 1) {
			widgets.add(Widgets.createSlot(new Point(startPoint.x + 28, startPoint.y)).entries(inputs.get(1)).markInput());
			if (inputs.size() > 2) {
				widgets.add(Widgets.createSlot(new Point(startPoint.x + 10, startPoint.y + 18)).entries(inputs.get(2)).markInput());
				if (inputs.size() > 3) {
					widgets.add(Widgets.createSlot(new Point(startPoint.x + 28, startPoint.y + 18)).entries(inputs.get(3)).markInput());
				}
			}
		}

		widgets.add(Widgets.createResultSlotBackground(outputPoint));
		widgets.add(Widgets.createSlot(outputPoint).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

		return widgets;
	}
}
