//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.misc;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.gui.*;

public class AutoRespawn extends Module
{
    public AutoRespawn() {
        super(Category.Misc);
        this.name = "AutoRespawn";
        this.tag = "AutoRespawn";
        this.description = "respawning automatically i guess";
    }
    
    public void onUpdate() {
        if (this.isActive() && AutoRespawn.mc.currentScreen instanceof GuiGameOver && AutoRespawn.mc.player.getHealth() >= 0.0f) {
            AutoRespawn.mc.player.respawnPlayer();
            AutoRespawn.mc.displayGuiScreen((GuiScreen)null);
        }
    }
}
