//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.module.*;

public class Alert extends Command
{
    public Alert() {
        super("alert", "if the module should spam chat or not");
    }
    
    public boolean getMessage(final String[] message) {
        String module = "null";
        String state = "null";
        if (message.length > 1) {
            module = message[1];
        }
        if (message.length > 2) {
            state = message[2];
        }
        if (message.length > 3) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "t <ModuleName> <True/On/False/Off>");
            return true;
        }
        if (module.equals("null")) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "t <ModuleName> <True/On/False/Off>");
            return true;
        }
        if (state.equals("null")) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "t <ModuleName> <True/On/False/Off>");
            return true;
        }
        module = module.toLowerCase();
        state = state.toLowerCase();
        final Module module_requested = RailHack.get_hack_manager().getModuleWithTag(module);
        if (module_requested == null) {
            MessageUtil.send_client_error_message("This module does not exist.");
            return true;
        }
        boolean value = true;
        if (state.equals("true") || state.equals("on")) {
            value = true;
        }
        else {
            if (!state.equals("false") && !state.equals("off")) {
                MessageUtil.send_client_error_message("This value does not exist. <True/On/False/Off>");
                return true;
            }
            value = false;
        }
        module_requested.set_if_can_send_message_toggle(value);
        MessageUtil.send_client_message("The actual value of " + module_requested.get_name() + " is " + Boolean.toString(module_requested.can_send_message_when_toggle()) + ".");
        return true;
    }
}
