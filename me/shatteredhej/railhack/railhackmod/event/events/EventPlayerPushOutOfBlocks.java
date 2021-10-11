//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

public class EventPlayerPushOutOfBlocks extends MinecraftEvent
{
    public double X;
    public double Y;
    public double Z;
    
    public EventPlayerPushOutOfBlocks(final double p_X, final double p_Y, final double p_Z) {
        this.X = p_X;
        this.Y = p_Y;
        this.Z = p_Z;
    }
}
