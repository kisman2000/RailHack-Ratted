//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.entity.*;

public class EventPlayerTravel extends EventCancellable
{
    public MoverType Type;
    public double X;
    public double Y;
    public double Z;
    public float Strafe;
    public float Vertical;
    public float Forward;
    
    public EventPlayerTravel(final MoverType p_Type, final double p_X, final double p_Y, final double p_Z) {
        this.Type = p_Type;
        this.X = p_X;
        this.Y = p_Y;
        this.Z = p_Z;
    }
    
    public EventPlayerTravel(final float p_Strafe, final float p_Vertical, final float p_Forward) {
        this.Strafe = p_Strafe;
        this.Vertical = p_Vertical;
        this.Forward = p_Forward;
    }
}
