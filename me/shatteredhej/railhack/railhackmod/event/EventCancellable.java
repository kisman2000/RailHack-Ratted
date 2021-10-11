//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event;

import me.zero.alpine.fork.event.type.*;
import net.minecraft.client.*;

public class EventCancellable extends Cancellable
{
    private final Era era_switch;
    private final float partial_ticks;
    
    public EventCancellable() {
        this.era_switch = Era.EVENT_PRE;
        this.partial_ticks = Minecraft.getMinecraft().getRenderPartialTicks();
    }
    
    public Era get_era() {
        return this.era_switch;
    }
    
    public float get_partial_ticks() {
        return this.partial_ticks;
    }
    
    public enum Era
    {
        EVENT_PRE, 
        EVENT_PERI, 
        EVENT_POST;
    }
}
