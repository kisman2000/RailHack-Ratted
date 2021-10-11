//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.function.*;

public class WurstplusAlwaysNight extends Module
{
    @EventHandler
    private Listener<EventRender> on_render;
    
    public WurstplusAlwaysNight() {
        super(Category.Render);
        this.on_render = new Listener<EventRender>(event -> {
            if (WurstplusAlwaysNight.mc.world == null) {
                return;
            }
            else {
                WurstplusAlwaysNight.mc.world.setWorldTime(18000L);
                return;
            }
        }, (Predicate<EventRender>[])new Predicate[0]);
        this.name = "AlwaysNight";
        this.tag = "AlwaysNight";
        this.description = "see even less";
    }
    
    public void onUpdate() {
        if (WurstplusAlwaysNight.mc.world == null) {
            return;
        }
        WurstplusAlwaysNight.mc.world.setWorldTime(18000L);
    }
}
