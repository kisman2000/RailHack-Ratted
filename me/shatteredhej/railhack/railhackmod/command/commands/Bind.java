//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.*;
import org.lwjgl.input.*;
import me.shatteredhej.railhack.railhackmod.module.*;

public class Bind extends Command
{
    public Bind() {
        super("bind", "bind module to key");
    }
    
    public boolean getMessage(final String[] message) {
        String module = "null;";
        String key = "null";
        if (message.length == 3) {
            module = message[1].toUpperCase();
            key = message[2].toUpperCase();
        }
        else if (message.length > 1) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "bind <ModuleTag> <key>");
            return true;
        }
        if (module.equals("null") || key.equals("null")) {
            MessageUtil.send_client_error_message(this.currentPrefix() + "bind <ModuleTag> <key>");
            return true;
        }
        final Module module_requested = RailHack.get_hack_manager().getModuleWithTag(module);
        if (module_requested == null) {
            MessageUtil.send_client_error_message("Module does not exist.");
            return true;
        }
        if (key.equalsIgnoreCase("NONE")) {
            module_requested.set_bind(0);
            MessageUtil.send_client_message(module_requested.get_tag() + " is bound to None.");
            return true;
        }
        final int new_bind = Keyboard.getKeyIndex(key.toUpperCase());
        if (new_bind == 0) {
            MessageUtil.send_client_error_message("Key does not exist.");
            return true;
        }
        if (new_bind == module_requested.get_bind(0)) {
            MessageUtil.send_client_error_message(module_requested.get_tag() + " is already bound to this key");
            return true;
        }
        module_requested.set_bind(new_bind);
        MessageUtil.send_client_message(module_requested.get_tag() + " is bound to " + module_requested.get_bind(""));
        return true;
    }
}
