//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import net.minecraft.client.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.module.*;
import net.minecraft.util.text.event.*;
import net.minecraft.util.text.*;
import java.util.regex.*;

public class MessageUtil
{
    public static final Minecraft mc;
    public static ChatFormatting g;
    public static ChatFormatting b;
    public static ChatFormatting a;
    public static ChatFormatting r;
    public static ChatFormatting l;
    public static String opener;
    
    public static void toggle_message(final Module module) {
        if (module.isActive()) {
            if (module.get_tag().equals("AutoCrystal")) {
                client_message_simple(MessageUtil.opener + "AutoCrystal" + ChatFormatting.DARK_GREEN + " enabled ");
            }
            else {
                client_message_simple(MessageUtil.opener + MessageUtil.r + module.get_name() + ChatFormatting.DARK_GREEN + " enabled");
            }
        }
        else if (module.get_tag().equals("AutoCrystal")) {
            client_message_simple(MessageUtil.opener + "AutoCrystal" + ChatFormatting.RED + " disabled");
        }
        else {
            client_message_simple(MessageUtil.opener + MessageUtil.r + module.get_name() + ChatFormatting.RED + " disabled");
        }
    }
    
    public static void client_message_simple(final String message) {
        if (MessageUtil.mc.player != null) {
            final ITextComponent itc = new TextComponentString(message).setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString("Enable/Disable Message"))));
            MessageUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(itc, 5936);
        }
    }
    
    public static void client_message(final String message) {
        if (MessageUtil.mc.player != null) {
            MessageUtil.mc.player.sendMessage((ITextComponent)new ChatMessage(message));
        }
    }
    
    public static void send_client_message_simple(final String message) {
        client_message(ChatFormatting.DARK_GREEN + "[Root_Client]" + " " + MessageUtil.r + message);
    }
    
    public static void send_client_message(final String message) {
        client_message(ChatFormatting.DARK_GREEN + "[Root_Client]" + " " + MessageUtil.r + message);
    }
    
    public static void send_client_error_message(final String message) {
        client_message(ChatFormatting.DARK_GREEN + "[Root_Client]" + " " + MessageUtil.r + message);
    }
    
    static {
        mc = Minecraft.getMinecraft();
        MessageUtil.g = ChatFormatting.GOLD;
        MessageUtil.b = ChatFormatting.BLUE;
        MessageUtil.a = ChatFormatting.DARK_AQUA;
        MessageUtil.r = ChatFormatting.RESET;
        MessageUtil.l = ChatFormatting.DARK_GREEN;
        MessageUtil.opener = MessageUtil.l + "[Root_Client]" + ChatFormatting.GRAY + " | " + MessageUtil.r;
    }
    
    public static class ChatMessage extends TextComponentBase
    {
        String message_input;
        
        public ChatMessage(final String message) {
            final Pattern p = Pattern.compile("&[0123456789abcdefrlosmk]");
            final Matcher m = p.matcher(message);
            final StringBuffer sb = new StringBuffer();
            while (m.find()) {
                final String replacement = "§" + m.group().substring(1);
                m.appendReplacement(sb, replacement);
            }
            m.appendTail(sb);
            this.message_input = sb.toString();
        }
        
        public String getUnformattedComponentText() {
            return this.message_input;
        }
        
        public ITextComponent createCopy() {
            return (ITextComponent)new ChatMessage(this.message_input);
        }
    }
}
