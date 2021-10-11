//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.chat.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.*;

public class EzMessage extends Command
{
    public EzMessage() {
        super("ezmessage", "Set ez mode");
    }
    
    public boolean getMessage(final String[] message) {
        if (message.length == 1) {
            MessageUtil.send_client_error_message("message needed");
            return true;
        }
        if (message.length >= 2) {
            final StringBuilder ez = new StringBuilder();
            boolean flag = true;
            for (final String word : message) {
                if (flag) {
                    flag = false;
                }
                else {
                    ez.append(word).append(" ");
                }
            }
            EzMessageUtil.set_message(ez.toString());
            MessageUtil.send_client_message("ez message changed to " + ChatFormatting.BOLD + ez.toString());
            RailHack.get_config_manager().save_settings();
            return true;
        }
        return false;
    }
}
