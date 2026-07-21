package com.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.DrawContext;
import com.hud.HUDRenderer;

@Environment(EnvType.CLIENT)
public class HudRenderEvent {
    
    public static void register() {
        HudRenderCallback.EVENT.register((context, tickDeltaTime) -> {
            HUDRenderer.getInstance().renderHUD(context);
        });
    }
}
