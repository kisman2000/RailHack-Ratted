//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event;

import net.minecraftforge.common.*;
import me.shatteredhej.railhack.railhackmod.manager.*;

public class RegisterEvent
{
    public static void register_command_manager(final CommandManager manager) {
        MinecraftForge.EVENT_BUS.register((Object)manager);
    }
    
    public static void register_module_manager(final EventManager manager) {
        MinecraftForge.EVENT_BUS.register((Object)manager);
    }
}
