//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.client.*;
import java.util.function.*;

public class GhostHand extends Module
{
    @EventHandler
    private Listener<EventPacket.SendPacket> listener;
    
    public GhostHand() {
        super(Category.Combat);
        this.listener = new Listener<EventPacket.SendPacket>(event -> {
            if (event.getPacket() instanceof CPacketHeldItemChange) {
                event.cancel();
            }
            return;
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "GhostHand";
        this.tag = "GhostHand";
    }
}
