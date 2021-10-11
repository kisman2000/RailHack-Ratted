//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.entity.*;

public class EventEntityRemoved extends EventCancellable
{
    private final Entity entity;
    
    public EventEntityRemoved(final Entity entity) {
        this.entity = entity;
    }
    
    public Entity get_entity() {
        return this.entity;
    }
}
