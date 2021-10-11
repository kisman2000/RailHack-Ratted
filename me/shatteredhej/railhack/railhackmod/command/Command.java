//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command;

import me.shatteredhej.railhack.railhackmod.manager.*;

public class Command
{
    String name;
    String description;
    
    public Command(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
    
    public boolean getMessage(final String[] message) {
        return false;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String currentPrefix() {
        return CommandManager.getPrefix();
    }
    
    public void execute(final String[] var1) {
    }
}
