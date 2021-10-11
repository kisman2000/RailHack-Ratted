//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;
import java.text.*;
import java.util.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import java.util.function.*;

public final class ChatModifications extends Module
{
    Setting timestamps;
    Setting dateformat;
    Setting name_highlight;
    @EventHandler
    private Listener<EventPacket.ReceivePacket> PacketEvent;
    
    public ChatModifications() {
        super(Category.Chat);
        this.timestamps = this.register("TimeStamps", "ChatModsTimeStamps", true);
        this.dateformat = this.register("Date Format", "ChatModsDateFormat", "24HR", this.combobox(new String[] { "24HR", "12HR" }));
        this.name_highlight = this.register("Name Highlight", "ChatModsNameHighlight", true);
        SPacketChat packet;
        TextComponentString component;
        String date;
        String text;
        this.PacketEvent = new Listener<EventPacket.ReceivePacket>(event -> {
            if (event.getPacket() instanceof SPacketChat) {
                packet = (SPacketChat)event.getPacket();
                if (packet.getChatComponent() instanceof TextComponentString) {
                    component = (TextComponentString)packet.getChatComponent();
                    if (this.timestamps.getValue(true)) {
                        date = "";
                        if (this.dateformat.in("12HR")) {
                            date = new SimpleDateFormat("h:mm a").format(new Date());
                        }
                        if (this.dateformat.in("24HR")) {
                            date = new SimpleDateFormat("k:mm").format(new Date());
                        }
                        component.text = "§7[" + date + "]§r " + component.text;
                    }
                    text = component.getFormattedText();
                    if (!text.contains("combat for")) {
                        if (this.name_highlight.getValue(true) && ChatModifications.mc.player != null && text.toLowerCase().contains(ChatModifications.mc.player.getName().toLowerCase())) {
                            text = text.replaceAll("(?i)" + ChatModifications.mc.player.getName(), ChatFormatting.GOLD + ChatModifications.mc.player.getName() + ChatFormatting.RESET);
                        }
                        event.cancel();
                        MessageUtil.client_message(text);
                    }
                }
            }
            return;
        }, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.name = "ChatModifications";
        this.tag = "ChatModifications";
        this.description = "this breaks things";
    }
}
