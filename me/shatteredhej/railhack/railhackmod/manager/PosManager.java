//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.manager;

import net.minecraft.client.*;

public class PosManager
{
    private static double x;
    private static double y;
    private static double z;
    private static boolean onground;
    private static final Minecraft mc;
    
    public static void updatePosition() {
        PosManager.x = PosManager.mc.player.posX;
        PosManager.y = PosManager.mc.player.posY;
        PosManager.z = PosManager.mc.player.posZ;
        PosManager.onground = PosManager.mc.player.onGround;
    }
    
    public static void restorePosition() {
        PosManager.mc.player.posX = PosManager.x;
        PosManager.mc.player.posY = PosManager.y;
        PosManager.mc.player.posZ = PosManager.z;
        PosManager.mc.player.onGround = PosManager.onground;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
