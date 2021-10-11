//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

public class EventBlock extends EventCancellable
{
    public BlockPos pos;
    public EnumFacing facing;
    private int stage;
    
    public EventBlock(final int stage, final BlockPos pos, final EnumFacing facing) {
        this.pos = pos;
        this.facing = facing;
        this.stage = stage;
    }
    
    public void set_stage(final int stage) {
        this.stage = stage;
    }
    
    public int get_stage() {
        return this.stage;
    }
}
