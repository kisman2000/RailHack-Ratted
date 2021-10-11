//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;

public class WrapperEvent
{
    static final Minecraft mc;
    
    public static Minecraft GetMC() {
        return WrapperEvent.mc;
    }
    
    public static EntityPlayerSP GetPlayer() {
        return WrapperEvent.mc.player;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
