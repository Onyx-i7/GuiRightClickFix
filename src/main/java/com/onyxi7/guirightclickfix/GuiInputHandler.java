package com.onyxi7.guirightclickfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.lang.reflect.Method;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiInputHandler {
    
    private boolean keyWasPressed = false;
    private static final Method HANDLE_MOUSE_CLICK_METHOD;

    static {
        Method method = null;
        try {
            method = ObfuscationReflectionHelper.findMethod(
                    GuiContainer.class,
                    "func_184098_a",    // Name SRG (obfuscated)
                    void.class,         // Method Return Type
                    Slot.class, int.class, int.class, ClickType.class // Types of Parameters
            );
            if (method != null) {
                method.setAccessible(true);
            }
        } catch (Exception e) {
            System.err.println("[GUI Right Click Fix] The method func_184098_a could not be found");
            e.printStackTrace();
        }
        HANDLE_MOUSE_CLICK_METHOD = method;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        Minecraft mc = Minecraft.getMinecraft();
        
        if (mc.currentScreen instanceof GuiContainer) {
            GuiContainer gui = (GuiContainer) mc.currentScreen;
            
            int useKeyCode = mc.gameSettings.keyBindUseItem.getKeyCode();
            boolean isKeyDown = false;

            if (useKeyCode >= 0) {
                isKeyDown = Keyboard.isKeyDown(useKeyCode);
            } else {
                isKeyDown = Mouse.isButtonDown(useKeyCode + 100);
            }

            if (isKeyDown && !keyWasPressed) {
                keyWasPressed = true;
                simulateRightClick(gui);
            } else if (!isKeyDown) {
                keyWasPressed = false;
            }
        } else {
            keyWasPressed = false;
        }
    }

    private void simulateRightClick(GuiContainer gui) {
        try {
            Slot slot = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, gui, "field_147006_u");

            if (slot != null && HANDLE_MOUSE_CLICK_METHOD != null) {
                // Parameters: (instance, slot, slotId, mouseButton, clickType)
                // mouseButton 1 = Right-click
                HANDLE_MOUSE_CLICK_METHOD.invoke(gui, slot, slot.slotNumber, 1, ClickType.PICKUP);
            }
        } catch (Exception e) {
            System.err.println("[GUI Right Click Fix] Error simulating the click:");
            e.printStackTrace();
        }
    }
}