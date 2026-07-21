package com.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class GuiManager extends Screen {
    private MinecraftClient client;
    private boolean isOpen = false;
    
    private float posX = 10;
    private float posY = 10;
    private int width = 300;
    private int height = 200;
    
    public GuiManager() {
        super(Text.literal("Pelmen Visuals"));
        this.client = MinecraftClient.getInstance();
    }
    
    public static GuiManager getInstance() {
        return new GuiManager();
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill((int)posX, (int)posY, (int)(posX + width), (int)(posY + height), 0xAA000000);
        
        context.fill((int)posX, (int)posY, (int)(posX + width), (int)(posY + 2), 0xFFFFFFFF);
        context.fill((int)posX, (int)(posY + height - 2), (int)(posX + width), (int)(posY + height), 0xFFFFFFFF);
        context.fill((int)posX, (int)posY, (int)(posX + 2), (int)(posY + height), 0xFFFFFFFF);
        context.fill((int)(posX + width - 2), (int)posY, (int)(posX + width), (int)(posY + height), 0xFFFFFFFF);
        
        context.drawCenteredTextWithShadow(
            client.textRenderer,
            "PELMEN VISUALS",
            (int)(posX + width / 2),
            (int)(posY + 10),
            0xFFFFFF
        );
        
        super.render(context, mouseX, mouseY, delta);
    }
    
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }
    
    public void open() {
        isOpen = true;
        client.setScreen(this);
    }
    
    @Override
    public void close() {
        super.close();
        isOpen = false;
    }
    
    public boolean isOpen() {
        return isOpen;
    }
}
