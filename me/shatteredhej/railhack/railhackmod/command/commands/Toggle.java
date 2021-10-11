//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.manager.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.module.*;

public class Toggle extends Command
{
    public Toggle() {
        super("t", "turn on and off stuffs");
    }
    
    public boolean getMessage(final String[] message) {
        String module = "null";
        if (message.length > 1) {
            module = message[1];
        }
        if (message.length > 2) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "t <ModuleNameNoSpace>");
            return true;
        }
        if (module.equals("null")) {
            MessageUtil.send_client_error_message(CommandManager.getPrefix() + "t <ModuleNameNoSpace>");
            return true;
        }
        final Module module_requested = RailHack.getModuleManager().getModuleWithTag(module);
        if (module_requested != null) {
            module_requested.toggle();
            MessageUtil.send_client_message("[" + module_requested.get_tag() + "] - Toggled to " + module_requested.isActive() + ".");
        }
        else {
            MessageUtil.send_client_error_message("Module does not exist.");
        }
        return true;
    }
}
