//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.shatteredhej.railhack.railhackmod.event.*;

public class EventSetupFog extends EventCancellable
{
    public int start_coords;
    public float partial_ticks;
    
    public EventSetupFog(final int coords, final float ticks) {
        this.start_coords = coords;
        this.partial_ticks = ticks;
    }
}
