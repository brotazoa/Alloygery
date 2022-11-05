package amorphia.alloygery.content.machines.screen;

import amorphia.alloygery.Alloygery;
import amorphia.alloygery.content.machines.recipe.SmithingAnvilRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class SmithingAnvilScreen extends HandledScreen<SmithingAnvilScreenHandler>
{

	/*
	copied from StoneCutterScreen, this is all magic to me
	 */

	public static final Identifier TEXTURE = Alloygery.identifier("textures/gui/smithing_anvil_v3.png");

	public static final Text NOT_ENOUGH_MATERIALS = new TranslatableText("tooltip.alloygery.smithing_anvil.not_enough_material").formatted(Formatting.DARK_RED);
	public static final Text TIER_TOO_LOW = new TranslatableText("tooltip.alloygery.smithing_anvil.crafting_tier_too_low").formatted(Formatting.DARK_RED);

	private static final int SCROLLBAR_OFFSET_X = 119;
	private static final int SCROLLBAR_OFFSET_Y = 14;
	private static final int SCROLLBAR_SCROLLABLE_HEIGHT = 41;
	private static final int SCROLLBAR_THUMB_WIDTH = 12;
	private static final int SCROLLBAR_THUMB_HEIGHT = 15;
	private static final int RECIPE_LIST_ROWS = 3;
	private static final int RECIPE_ENTRY_WIDTH = 64;
	private static final int RECIPE_ENTRY_HEIGHT = 18;
	private static final int SCROLLBAR_AREA_HEIGHT = 54;
	private static final int RECIPE_LIST_OFFSET_X = 52;
	private static final int RECIPE_LIST_OFFSET_Y = 14;
	private float scrollAmount = 0.0f;
	private boolean mouseClicked = false;
	private int scrollOffset = 0;
	private boolean canCraft = false;

	public SmithingAnvilScreen(SmithingAnvilScreenHandler handler, PlayerInventory inventory, Text title)
	{
		super(handler, inventory, title);
		handler.setContentsChangedListener(this::onInventoryChange);
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{
		super.render(matrices, mouseX, mouseY, delta);
		this.drawMouseoverTooltip(matrices, mouseX, mouseY);
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY)
	{
		this.renderBackground(matrices);
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		RenderSystem.enableBlend();

		//draw background
		this.drawTexture(matrices, this.x, this.y, 0, 0, this.backgroundWidth, this.backgroundHeight);

		//draw scrollbar thumb
		int thumbPositionInScrollbar = (int) (((float) SCROLLBAR_SCROLLABLE_HEIGHT) * this.scrollAmount);
		this.drawTexture(matrices, this.x + SCROLLBAR_OFFSET_X, this.y + SCROLLBAR_OFFSET_Y + thumbPositionInScrollbar,
				this.backgroundWidth + (this.shouldScroll() ? 0 : SCROLLBAR_THUMB_WIDTH), 0,
				SCROLLBAR_THUMB_WIDTH, SCROLLBAR_THUMB_HEIGHT);

		int recipeListX = this.x + RECIPE_LIST_OFFSET_X;
		int recipeListY = this.y + RECIPE_LIST_OFFSET_Y;
		int indexOfLastVisibleRecipe = this.scrollOffset + RECIPE_LIST_ROWS;
		this.renderRecipeBackground(matrices, mouseX, mouseY, recipeListX, recipeListY, indexOfLastVisibleRecipe);
		this.renderRecipeIcons(recipeListX, recipeListY, indexOfLastVisibleRecipe);
	}

	@Override
	protected void drawMouseoverTooltip(MatrixStack matrices, int x, int y)
	{
		super.drawMouseoverTooltip(matrices, x, y);
		if (this.handler.canCraft())
		{
			int i = this.x + RECIPE_LIST_OFFSET_X;
			int j = this.y + RECIPE_LIST_OFFSET_Y;
			int k = this.scrollOffset + RECIPE_LIST_ROWS;
			List<SmithingAnvilRecipe> list = this.handler.getAvailableRecipes();
			for(int l = this.scrollOffset; l < k && l < this.handler.getAvailableRecipeCount(); l++)
			{
				int m = l - this.scrollOffset;
				int n = i;
				int o = j + m * RECIPE_ENTRY_HEIGHT + 2;
				if (x >= n && x < n + RECIPE_ENTRY_WIDTH && y >= o && y < o + RECIPE_ENTRY_HEIGHT)
				{
					boolean overOutput = x >= n + (RECIPE_ENTRY_WIDTH - (RECIPE_ENTRY_WIDTH / 3)) && x < n + RECIPE_ENTRY_WIDTH;
					final ItemStack outputStack = list.get(l).craft(this.handler.input);

					if (!this.handler.canAffordRecipe(l))
					{
						if(overOutput)
						{
							this.renderTooltip(matrices, outputStack, x, y);
						}
						else
						{
							this.renderTooltip(matrices, NOT_ENOUGH_MATERIALS, x, y);
						}
					}
					else if (!this.handler.suitableCraftingTier(l))
					{
						if(overOutput)
						{
							this.renderTooltip(matrices, outputStack, x, y);
						}
						else
						{
							this.renderTooltip(matrices, TIER_TOO_LOW, x, y);
						}
					}
					else
					{
						this.renderTooltip(matrices, outputStack, x, y);
					}
				}
			}
		}
	}

	private void renderRecipeBackground(MatrixStack matrices, int mouseX, int mouseY, int x, int y, int indexOfLastVisibleRecipe)
	{
		for(int i = this.scrollOffset; i < indexOfLastVisibleRecipe && i < this.handler.getAvailableRecipeCount(); i++)
		{
			int recipeButtonIndex = i - this.scrollOffset;
			int recipeButtonX = x;
			int recipeButtonY = y + (recipeButtonIndex * RECIPE_ENTRY_HEIGHT) + 2;
			int n = this.backgroundHeight;
			if (i == this.handler.getSelectedRecipe())
			{
				n += RECIPE_ENTRY_HEIGHT;
			}
			else if (mouseX >= recipeButtonX && mouseY >= recipeButtonY && mouseX < recipeButtonX + RECIPE_ENTRY_WIDTH && mouseY < recipeButtonY + RECIPE_ENTRY_HEIGHT)
			{
				n += RECIPE_ENTRY_HEIGHT * 2;
			}

			//render button
			this.drawTexture(matrices, recipeButtonX, recipeButtonY - 1, 0, n, RECIPE_ENTRY_WIDTH, RECIPE_ENTRY_HEIGHT);

			final boolean canAfford = this.handler.canAffordRecipe(i);
			final boolean suitableTier = this.handler.suitableCraftingTier(i);

			final int arrowOffset = (SCROLLBAR_THUMB_WIDTH + SCROLLBAR_THUMB_WIDTH) + (!canAfford ? SCROLLBAR_THUMB_WIDTH : !suitableTier ? SCROLLBAR_THUMB_WIDTH + SCROLLBAR_THUMB_WIDTH : 0);

			//render arrow
			drawTexture(matrices, recipeButtonX + 30, recipeButtonY - 1, 100,
					this.backgroundWidth + arrowOffset, 0,
					SCROLLBAR_THUMB_WIDTH, SCROLLBAR_THUMB_HEIGHT, 256, 256);
		}
	}

	private void renderRecipeIcons(int x, int y, int indexOfLastVisibleRecipe)
	{
		List<SmithingAnvilRecipe> list = this.handler.getAvailableRecipes();

		for(int i = this.scrollOffset; i < indexOfLastVisibleRecipe && i < this.handler.getAvailableRecipeCount(); i++)
		{
			int recipeButtonIndex = i - this.scrollOffset;
			int recipeButtonX = x;
			int recipeButtonY = y + (recipeButtonIndex * RECIPE_ENTRY_HEIGHT) + 2;

			//render output item
			final ItemStack outputStack = list.get(i).craft(this.handler.input);
			this.client.getItemRenderer().renderInGuiWithOverrides(outputStack, recipeButtonX + 46, recipeButtonY);
			this.client.getItemRenderer().renderGuiItemOverlay(this.textRenderer, outputStack, recipeButtonX + 46, recipeButtonY);

			//render material item
			final ItemStack materialStack = this.handler.materialSlot.getStack().copy();
			materialStack.setCount(list.get(i).getMaterialCost());
			this.client.getItemRenderer().renderInGuiWithOverrides(materialStack, recipeButtonX + 2, recipeButtonY - 1);
			this.client.getItemRenderer().renderGuiItemOverlay(this.textRenderer, materialStack, recipeButtonX + 2, recipeButtonY - 1, materialStack.getCount() == 1 ? "1" : null);
		}
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button)
	{
		this.mouseClicked = false;
		if (this.handler.canCraft())
		{
			int recipeListX = this.x + RECIPE_LIST_OFFSET_X;
			int recipeListY = this.y + RECIPE_LIST_OFFSET_Y;
			int indexOfLastVisibleRecipe = this.scrollOffset + RECIPE_LIST_ROWS;

			for(int i = this.scrollOffset; i < indexOfLastVisibleRecipe; ++i) {
				int recipeButtonIndex = i - this.scrollOffset;
				double d = mouseX - (double)(recipeListX);
				double e = mouseY - (double)(recipeListY + (recipeButtonIndex * RECIPE_ENTRY_HEIGHT));
				if (d >= 0.0D && e >= 0.0D && d < RECIPE_ENTRY_WIDTH && e < RECIPE_ENTRY_HEIGHT && this.handler.onButtonClick(this.client.player, i)) {
					MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
					this.client.interactionManager.clickButton(this.handler.syncId, i);
					return true;
				}
			}

			int scrollBarX = this.x + SCROLLBAR_OFFSET_X;
			int scrollBarY = this.y + 9;
			if (mouseX >= (double)scrollBarX && mouseX < (double)(scrollBarX + SCROLLBAR_THUMB_WIDTH) && mouseY >= (double)scrollBarY && mouseY < (double)(scrollBarY + SCROLLBAR_AREA_HEIGHT)) {
				this.mouseClicked = true;
			}
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		if (this.mouseClicked && this.shouldScroll()) {
			int i = this.y + RECIPE_LIST_OFFSET_Y;
			int j = i + SCROLLBAR_AREA_HEIGHT;
			this.scrollAmount = ((float)mouseY - (float)i - 7.5F) / ((float)(j - i) - SCROLLBAR_THUMB_HEIGHT);
			this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
			this.scrollOffset = (int)((double)(this.scrollAmount * (float)this.getMaxScroll()) + 0.5D);
			return true;
		} else {
			return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
		}
	}

	public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		if (this.shouldScroll()) {
			int i = this.getMaxScroll();
			this.scrollAmount = (float)((double)this.scrollAmount - amount / (double)i);
			this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
			this.scrollOffset = (int)((double)(this.scrollAmount * (float)i) + 0.5D);
		}

		return true;
	}

	private boolean shouldScroll()
	{
		return this.handler.canCraft() && this.handler.getAvailableRecipeCount() > RECIPE_LIST_ROWS;
	}

	protected int getMaxScroll()
	{
		return this.handler.getAvailableRecipeCount() - RECIPE_LIST_ROWS;
	}

	private void onInventoryChange()
	{
		this.scrollAmount = 0.0f;
		this.scrollOffset = 0;
	}
}
