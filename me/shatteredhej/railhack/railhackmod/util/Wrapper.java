//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import net.minecraft.client.gui.*;
import me.shatteredhej.railhack.railhackmod.modules.*;
import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import org.lwjgl.input.*;

public class Wrapper
{
    private static FontRenderer fontRenderer;
    
    public static void init() {
        Wrapper.fontRenderer = ClickGui.mc.fontRenderer;
    }
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().player;
    }
    
    public static World getWorld() {
        return (World)getMinecraft().world;
    }
    
    public static int getKey(final String keyname) {
        return Keyboard.getKeyIndex(keyname.toUpperCase());
    }
    
    public static FontRenderer getFontRenderer() {
        return Wrapper.fontRenderer;
    }
}
