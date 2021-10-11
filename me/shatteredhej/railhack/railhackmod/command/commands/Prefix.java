//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.manager.*;

public class Prefix extends Command
{
    public Prefix() {
        super("prefix", "Change prefix.");
    }
    
    public boolean getMessage(final String[] message) {
        String prefix = "null";
        if (message.length > 1) {
            prefix = message[1];
        }
        if (message.length > 2) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "prefix <character>");
            return true;
        }
        if (prefix.equals("null")) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "prefix <character>");
            return true;
        }
        CommandManager.setPrefix(prefix);
        MessageUtil.send_client_message("The new prefix is " + prefix);
        return true;
    }
}
