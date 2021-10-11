//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.misc;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import java.util.function.*;

public class Logger extends Module
{
    Setting received;
    Setting sent;
    @EventHandler
    private Listener<EventPacket.ReceivePacket> listener;
    @EventHandler
    private Listener<EventPacket.SendPacket> sendPacketListener;
    
    public Logger() {
        super(Category.Misc);
        this.received = this.register("Received", "LoggerRecevied", false);
        this.sent = this.register("Sent", "LoggerSent", false);
        this.listener = new Listener<EventPacket.ReceivePacket>(event -> {
            if (this.received.getValue(true)) {
                MessageUtil.send_client_message(event.getPacket().toString());
            }
            return;
        }, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.sendPacketListener = new Listener<EventPacket.SendPacket>(event -> MessageUtil.send_client_message(event.getPacket().toString()), (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Logger";
        this.tag = "Logger";
    }
}
