package com.pelmenvisuals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import com.events.ClientTickEvent;
import com.events.HudRenderEvent;
import com.keybind.KeyBindings;

@Environment(EnvType.CLIENT)
public class PelmenVisualsClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        KeyBindings.register();
        ClientTickEvent.register();
        HudRenderEvent.register();
    }
}
