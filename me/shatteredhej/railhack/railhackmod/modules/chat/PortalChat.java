//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.client.*;
import java.util.function.*;

public class PortalChat extends Module
{
    @EventHandler
    private Listener<EventPacket.SendPacket> listener;
    
    public PortalChat() {
        super(Category.Chat);
        this.listener = new Listener<EventPacket.SendPacket>(event -> {
            if (event.getPacket() instanceof CPacketConfirmTeleport) {
                event.cancel();
            }
            return;
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "PortalChat";
        this.tag = "PortalChat";
    }
}
