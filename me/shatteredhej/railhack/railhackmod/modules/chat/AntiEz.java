//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;

public class AntiEz extends Module
{
    int diedTime;
    
    public AntiEz() {
        super(Category.Chat);
        this.diedTime = 0;
        this.name = "AntiEz";
        this.tag = "AntiEz";
    }
    
    public void onUpdate() {
        if (this.diedTime > 0) {
            --this.diedTime;
        }
        if (AntiEz.mc.player.isDead) {
            this.diedTime = 500;
        }
        if (AntiEz.mc.player.isDead && this.diedTime > 0) {
            AntiEz.mc.player.sendChatMessage("Say ez if gay");
        }
    }
}
