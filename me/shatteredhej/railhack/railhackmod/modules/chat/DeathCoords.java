//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.*;

public class DeathCoords extends Module
{
    public DeathCoords() {
        super(Category.Chat);
        this.name = "DeathCoords";
        this.tag = "DeathCoords";
        this.description = "d";
    }
    
    public void onUpdate() {
        if (DeathCoords.mc.player.isDead) {
            MessageUtil.send_client_message("You died at x" + DeathCoords.mc.player.posX + " y" + DeathCoords.mc.player.posY + " z" + DeathCoords.mc.player.posZ + ".");
        }
    }
}
