//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import net.minecraft.client.settings.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.entity.*;

public class InventoryWalk extends Module
{
    public InventoryWalk() {
        super(Category.Movement);
        this.name = "InventoryWalk";
        this.tag = "InvWalk";
    }
    
    public void onUpdate() {
        if (InventoryWalk.mc.currentScreen instanceof GuiChat || InventoryWalk.mc.currentScreen == null) {
            return;
        }
        final int[] keys;
        final int[] array2;
        final int[] array = array2 = (keys = new int[] { InventoryWalk.mc.gameSettings.keyBindForward.getKeyCode(), InventoryWalk.mc.gameSettings.keyBindLeft.getKeyCode(), InventoryWalk.mc.gameSettings.keyBindRight.getKeyCode(), InventoryWalk.mc.gameSettings.keyBindBack.getKeyCode(), InventoryWalk.mc.gameSettings.keyBindJump.getKeyCode() });
        for (final int keyCode : array2) {
            if (Keyboard.isKeyDown(keyCode)) {
                KeyBinding.setKeyBindState(keyCode, true);
            }
            else {
                KeyBinding.setKeyBindState(keyCode, false);
            }
        }
        if (InventoryWalk.mc.currentScreen instanceof GuiContainer) {
            if (Keyboard.isKeyDown(200)) {
                final EntityPlayerSP player;
                final EntityPlayerSP player = player = InventoryWalk.mc.player;
                player.rotationPitch -= 7.0f;
            }
            if (Keyboard.isKeyDown(208)) {
                final EntityPlayerSP player2;
                final EntityPlayerSP player2 = player2 = InventoryWalk.mc.player;
                player2.rotationPitch += 7.0f;
            }
            if (Keyboard.isKeyDown(205)) {
                final EntityPlayerSP player3;
                final EntityPlayerSP player3 = player3 = InventoryWalk.mc.player;
                player3.rotationYaw += 7.0f;
            }
            if (Keyboard.isKeyDown(203)) {
                final EntityPlayerSP player4;
                final EntityPlayerSP player4 = player4 = InventoryWalk.mc.player;
                player4.rotationYaw -= 7.0f;
            }
            if (Keyboard.isKeyDown(InventoryWalk.mc.gameSettings.keyBindSprint.getKeyCode())) {
                InventoryWalk.mc.player.setSprinting(true);
            }
        }
    }
}
