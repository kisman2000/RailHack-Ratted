//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.zero.alpine.fork.event.type.*;

public class MinecraftEvent extends Cancellable
{
    private Era era;
    private final float partialTicks;
    
    public MinecraftEvent() {
        this.era = Era.PRE;
        this.partialTicks = WrapperEvent.GetMC().getRenderPartialTicks();
    }
    
    public MinecraftEvent(final Era p_Era) {
        this.era = Era.PRE;
        this.partialTicks = WrapperEvent.GetMC().getRenderPartialTicks();
        this.era = p_Era;
    }
    
    public Era getEra() {
        return this.era;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public enum Era
    {
        PRE, 
        PERI, 
        POST;
    }
}
