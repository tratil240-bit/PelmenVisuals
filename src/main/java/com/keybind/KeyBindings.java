package com.keybind;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.features.NightVision;
import com.gui.GuiManager;

@Environment(EnvType.CLIENT)
public class KeyBindings {
    
    public static KeyBinding NIGHTVISION_KEY;
    public static KeyBinding GUI_KEY;
    
    public static void register() {
        NIGHTVISION_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.pelmen.nightvision",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "category.pelmen.keys"
        ));
        
        GUI_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.pelmen.gui",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            "category.pelmen.keys"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (NIGHTVISION_KEY.wasPressed()) {
                NightVision.getInstance().toggle();
            }
            
            while (GUI_KEY.wasPressed()) {
                GuiManager gui = GuiManager.getInstance();
                if (gui.isOpen()) {
                    gui.close();
                } else {
                    gui.open();
                }
            }
        });
    }
}
