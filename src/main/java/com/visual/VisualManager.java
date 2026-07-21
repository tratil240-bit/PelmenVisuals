package com.visual;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class VisualManager {
    private static VisualManager instance;
    private MinecraftClient client;
    
    private VisualManager() {
        this.client = MinecraftClient.getInstance();
    }
    
    public static VisualManager getInstance() {
        if (instance == null) {
            instance = new VisualManager();
        }
        return instance;
    }
    
    public void setupGL() {
        com.mojang.blaze3d.systems.RenderSystem.enableBlend();
        com.mojang.blaze3d.systems.RenderSystem.blendFunc(
            com.mojang.blaze3d.systems.RenderSystem.SrcAlpha.BLEND,
            com.mojang.blaze3d.systems.RenderSystem.DstAlpha.BLEND
        );
        com.mojang.blaze3d.systems.RenderSystem.disableDepthTest();
    }
    
    public void restoreGL() {
        com.mojang.blaze3d.systems.RenderSystem.disableBlend();
        com.mojang.blaze3d.systems.RenderSystem.enableDepthTest();
    }
}
