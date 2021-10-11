//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.*;

public class Config extends Command
{
    public Config() {
        super("config", "changes which config is loaded");
    }
    
    public boolean getMessage(final String[] message) {
        if (message.length == 1) {
            MessageUtil.send_client_error_message("config needed");
            return true;
        }
        if (message.length == 2) {
            final String config = message[1];
            if (RailHack.get_config_manager().set_active_config_folder(config + "/")) {
                MessageUtil.send_client_message("new config folder set as " + config);
            }
            else {
                MessageUtil.send_client_error_message("cannot set folder to " + config);
            }
            return true;
        }
        MessageUtil.send_client_error_message("config path may only be one word");
        return true;
    }
}
