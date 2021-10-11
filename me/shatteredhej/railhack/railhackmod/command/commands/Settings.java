//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.*;

public class Settings extends Command
{
    public Settings() {
        super("settings", "To save/load settings.");
    }
    
    public boolean getMessage(final String[] message) {
        String msg = "null";
        if (message.length > 1) {
            msg = message[1];
        }
        if (msg.equals("null")) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "settings <save/load>");
            return true;
        }
        final ChatFormatting c = ChatFormatting.GRAY;
        if (msg.equalsIgnoreCase("save")) {
            RailHack.get_config_manager().save_settings();
            MessageUtil.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "saved!");
        }
        else {
            if (!msg.equalsIgnoreCase("load")) {
                MessageUtil.send_client_error_message(this.currentPrefix() + "settings <save/load>");
                return true;
            }
            RailHack.get_config_manager().load_settings();
            MessageUtil.send_client_message(ChatFormatting.GREEN + "Successfully " + c + "loaded!");
        }
        return true;
    }
}
