package com.features;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

@Environment(EnvType.CLIENT)
public class NightVision {
    private static NightVision instance;
    private MinecraftClient client;
    private boolean enabled = false;
    private static final int DURATION = 200;
    
    private NightVision() {
        this.client = MinecraftClient.getInstance();
    }
    
    public static NightVision getInstance() {
        if (instance == null) {
            instance = new NightVision();
        }
        return instance;
    }
    
    public void toggle() {
        if (enabled) {
            disable();
        } else {
            enable();
        }
    }
    
    public void enable() {
        if (client.player == null) return;
        
        enabled = true;
        
        StatusEffectInstance nightVision = new StatusEffectInstance(
            StatusEffects.NIGHT_VISION,
            DURATION,
            0,
            false,
            false
        );
        
        client.player.addStatusEffect(nightVision);
    }
    
    public void disable() {
        if (client.player == null) return;
        
        enabled = false;
        client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }
    
    public void update() {
        if (enabled && client.player != null) {
            if (!client.player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
                enable();
            }
        }
    }
    
    public boolean isEnabled() {
        return enabled;
    }
}
