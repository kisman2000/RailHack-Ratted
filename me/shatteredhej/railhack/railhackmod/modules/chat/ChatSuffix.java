//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.client.*;
import java.util.function.*;
import me.shatteredhej.railhack.*;

public class ChatSuffix extends Module
{
    Setting ignore;
    Setting type;
    boolean accept_suffix;
    boolean suffix_default;
    boolean suffix_egyptian;
    StringBuilder suffix;
    @EventHandler
    private Listener<EventPacket.SendPacket> listener;
    
    public ChatSuffix() {
        super(Category.Chat);
        this.ignore = this.register("Ignore", "ChatSuffixIgnore", true);
        this.type = this.register("Type", "ChatSuffixType", "Default", this.combobox(new String[] { "Default", "Egyptian" }));
        boolean ignore_prefix;
        String message;
        this.listener = new Listener<EventPacket.SendPacket>(event -> {
            if (!(event.getPacket() instanceof CPacketChatMessage)) {
                return;
            }
            else {
                this.accept_suffix = true;
                ignore_prefix = this.ignore.getValue(true);
                message = ((CPacketChatMessage)event.getPacket()).getMessage();
                if (message.startsWith("/") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith("\\") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith("!") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith(":") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith(";") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith(".") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith(",") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith("@") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith("&") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith("*") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith("$") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith("#") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith("(") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (message.startsWith(")") && ignore_prefix) {
                    this.accept_suffix = false;
                }
                if (this.type.in("Default")) {
                    this.suffix_default = true;
                    this.suffix_egyptian = false;
                }
                if (this.type.in("Egyptian")) {
                    this.suffix_default = false;
                    this.suffix_egyptian = true;
                }
                if (this.accept_suffix) {
                    if (this.suffix_default) {
                        message = message + " " + this.convert_base(" | Root");
                    }
                    if (this.suffix_egyptian) {
                        message = message + " " + this.convert_base(" | \u00e1µ?\u00e2\u201a\ufffd\u00e2\u201a\u2014\u00e2\u201a\u2019\u00e2\u201a\u2022\u00e2\u201a\ufffdc\u00e2\u201a\u2013 \u00e1µ?\u00e2\u201a\ufffd.\u00e2\u201a\u201a\n");
                    }
                    if (message.length() >= 256) {
                        message.substring(0, 256);
                    }
                }
                ((CPacketChatMessage)event.getPacket()).message = message;
                return;
            }
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "ChatSuffix";
        this.tag = "ChatSuffix";
        this.description = "show off how cool u are";
    }
    
    public String convert_base(final String base) {
        return RailHack.smoth(base);
    }
    
    public String array_detail() {
        return this.type.get_current_value();
    }
}
