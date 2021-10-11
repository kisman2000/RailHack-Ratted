//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.client.gui.*;

public class EventGameOverlay extends EventCancellable
{
    public float partial_ticks;
    private ScaledResolution scaled_resolution;
    
    public EventGameOverlay(final float partial_ticks, final ScaledResolution scaled_resolution) {
        this.partial_ticks = partial_ticks;
        this.scaled_resolution = scaled_resolution;
    }
    
    public ScaledResolution get_scaled_resoltion() {
        return this.scaled_resolution;
    }
}
