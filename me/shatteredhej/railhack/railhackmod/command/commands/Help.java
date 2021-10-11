//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.command.*;
import java.util.*;

public class Help extends Command
{
    public Help() {
        super("help", "helps people");
    }
    
    public boolean getMessage(final String[] message) {
        String type = "null";
        if (message.length == 1) {
            type = "list";
        }
        if (message.length > 1) {
            type = message[1];
        }
        if (message.length > 2) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "help <List/NameCommand>");
            return true;
        }
        if (type.equals("null")) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "help <List/NameCommand>");
            return true;
        }
        if (type.equalsIgnoreCase("list")) {
            for (final Command commands : Commands.getPureCommandList()) {
                MessageUtil.send_client_message(commands.getName());
            }
            return true;
        }
        final Command command_requested = Commands.getCommandWithName(type);
        if (command_requested == null) {
            MessageUtil.send_client_error_message("This command does not exist.");
            return true;
        }
        MessageUtil.send_client_message(command_requested.getName() + " - " + command_requested.getDescription());
        return true;
    }
}
