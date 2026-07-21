package com.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

@Environment(EnvType.CLIENT)
public class HUDRenderer {
    private static HUDRenderer instance;
    private MinecraftClient client;
    
    private HUDRenderer() {
        this.client = MinecraftClient.getInstance();
    }
    
    public static HUDRenderer getInstance() {
        if (instance == null) {
            instance = new HUDRenderer();
        }
        return instance;
    }
    
    public void renderHUD(DrawContext context) {
        if (client.player == null) return;
        
        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        
        renderCoordinates(context, screenWidth, screenHeight);
        renderFPS(context, screenWidth, screenHeight);
        renderHealth(context, screenWidth, screenHeight);
        renderArmor(context, screenWidth, screenHeight);
    }
    
    private void renderCoordinates(DrawContext context, int screenWidth, int screenHeight) {
        if (client.player == null) return;
        
        double x = client.player.getX();
        double y = client.player.getY();
        double z = client.player.getZ();
        
        String coords = String.format("X: %.1f Y: %.1f Z: %.1f", x, y, z);
        context.drawText(client.textRenderer, coords, 10, 10, 0xFFFFFF, true);
    }
    
    private void renderFPS(DrawContext context, int screenWidth, int screenHeight) {
        int fps = MinecraftClient.getInstance().getCurrentFps();
        String fpsText = "FPS: " + fps;
        
        int color = fps >= 60 ? 0x00FF00 : (fps >= 30 ? 0xFFFF00 : 0xFF0000);
        context.drawText(client.textRenderer, fpsText, 10, 25, color, true);
    }
    
    private void renderHealth(DrawContext context, int screenWidth, int screenHeight) {
        if (client.player == null) return;
        
        float health = client.player.getHealth();
        float maxHealth = client.player.getMaxHealth();
        float healthPercent = health / maxHealth;
        
        int x = screenWidth / 2 - 100;
        int y = screenHeight - 50;
        int width = 200;
        int height = 20;
        
        context.fill(x, y, x + width, y + height, 0xFF000000);
        int healthColor = (int) (0xFF0000 + (0xFF0000 * (1 - healthPercent)));
        context.fill(x + 2, y + 2, x + 2 + (int)(width - 4) * healthPercent, y + height - 2, healthColor);
        
        String healthText = String.format("%.1f / %.1f", health, maxHealth);
        context.drawText(client.textRenderer, healthText, x + width / 2 - 30, y + 6, 0xFFFFFF, true);
    }
    
    private void renderArmor(DrawContext context, int screenWidth, int screenHeight) {
        if (client.player == null) return;
        
        int armorValue = client.player.getArmor();
        
        int x = screenWidth / 2 - 100;
        int y = screenHeight - 75;
        
        String armorText = "Броня: " + armorValue;
        context.drawText(client.textRenderer, armorText, x, y, 0xFFFFFF, true);
    }
    
    public void drawText(DrawContext context, String text, int x, int y, int color) {
        context.drawText(client.textRenderer, text, x, y, color, true);
    }
    
    public void drawRectangle(DrawContext context, int x, int y, int width, int height, int color) {
        context.fill(x, y, x + width, y + height, color);
    }
}
