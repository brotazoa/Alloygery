package amorphia.alloygery.content.machines.screen;

import amorphia.alloygery.Alloygery;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AlloyKilnScreen extends HandledScreen<AlloyKilnScreenHandler>
{
	private static final Identifier TEXTURE = Alloygery.identifier("textures/gui/alloy_kiln.png");

	public AlloyKilnScreen(AlloyKilnScreenHandler handler, PlayerInventory inventory, Text title)
	{
		super(handler, inventory, title);
		this.backgroundWidth = 176;
		this.backgroundHeight = 184;
		this.x = 0;
		this.y = 0;
		this.playerInventoryTitleY = backgroundHeight - 94;
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
	{
		this.renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		this.drawMouseoverTooltip(matrices, mouseX, mouseY);
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY)
	{
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int i = (this.width - this.backgroundWidth) / 2;
		int j = (this.height - this.backgroundHeight) / 2;
		this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
		this.renderProgress(matrices);
		this.renderLit(matrices);
	}

	private void renderLit(MatrixStack matrices)
	{
		int lit = super.handler.litTime();
		if (lit > 0)
		{
			this.drawTexture(matrices, this.x + 47, this.y + 54 + 12 - lit, 176, 12 - lit, 14, lit + 1);
		}
	}

	private void renderProgress(MatrixStack matrices)
	{
		int smeltingTime = super.handler.smeltingTime();
		this.drawTexture(matrices, this.x + 81, this.y + 26, 176, 14, smeltingTime + 1, 16);
	}
}
