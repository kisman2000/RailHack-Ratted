//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event;

import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import net.minecraft.network.play.server.*;
import java.util.function.*;
import net.minecraft.util.math.*;
import java.util.*;

public class HandleEvent implements Listenable
{
    public static HandleEvent INSTANCE;
    static final float[] ticks;
    private long last_update_tick;
    private int next_index;
    @EventHandler
    private Listener<EventPacket.ReceivePacket> receive_event_packet;
    
    public HandleEvent() {
        this.next_index = 0;
        this.receive_event_packet = new Listener<EventPacket.ReceivePacket>(event -> {
            if (event.getPacket() instanceof SPacketTimeUpdate) {
                HandleEvent.INSTANCE.update_time();
            }
            return;
        }, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        Event.EVENT_BUS.subscribe(this);
        this.reset_tick();
    }
    
    public float get_tick_rate() {
        float num_ticks = 0.0f;
        float sum_ticks = 0.0f;
        for (final float tick : HandleEvent.ticks) {
            if (tick > 0.0f) {
                sum_ticks += tick;
                ++num_ticks;
            }
        }
        return MathHelper.clamp(sum_ticks / num_ticks, 0.0f, 20.0f);
    }
    
    public void reset_tick() {
        this.next_index = 0;
        this.last_update_tick = -1L;
        Arrays.fill(HandleEvent.ticks, 0.0f);
    }
    
    public void update_time() {
        if (this.last_update_tick != -1L) {
            final float time = (System.currentTimeMillis() - this.last_update_tick) / 1000.0f;
            HandleEvent.ticks[this.next_index % HandleEvent.ticks.length] = MathHelper.clamp(20.0f / time, 0.0f, 20.0f);
            ++this.next_index;
        }
        this.last_update_tick = System.currentTimeMillis();
    }
    
    static {
        ticks = new float[20];
    }
}
