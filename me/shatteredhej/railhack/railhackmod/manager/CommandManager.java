//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.manager;

import me.shatteredhej.railhack.railhackmod.command.*;
import net.minecraft.util.text.*;

public class CommandManager
{
    public static Commands commandList;
    
    public CommandManager() {
        CommandManager.commandList = new Commands(new Style().setColor(TextFormatting.BLUE));
    }
    
    public static void setPrefix(final String newPrefix) {
        CommandManager.commandList.setPrefix(newPrefix);
    }
    
    public static String getPrefix() {
        return CommandManager.commandList.getPrefix();
    }
}
