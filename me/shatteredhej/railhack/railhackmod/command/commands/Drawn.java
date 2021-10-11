//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.module.*;
import java.util.*;

public class Drawn extends Command
{
    public Drawn() {
        super("drawn", "Hide elements of the array list");
    }
    
    public boolean getMessage(final String[] message) {
        if (message.length == 1) {
            MessageUtil.send_client_error_message("module name needed");
            return true;
        }
        if (message.length == 2) {
            if (this.is_module(message[1])) {
                DrawnUtil.add_remove_item(message[1]);
                RailHack.get_config_manager().save_settings();
            }
            else {
                MessageUtil.send_client_error_message("cannot find module by name: " + message[1]);
            }
            return true;
        }
        return false;
    }
    
    public boolean is_module(final String s) {
        final List<Module> modules = RailHack.get_hack_manager().get_array_hacks();
        for (final Module module : modules) {
            if (module.get_tag().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
}
