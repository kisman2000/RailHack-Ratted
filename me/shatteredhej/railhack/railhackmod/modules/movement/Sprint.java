//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;

public class Sprint extends Module
{
    Setting rage;
    
    public Sprint() {
        super(Category.Movement);
        this.rage = this.register("Rage", "SprintRage", true);
        this.name = "Sprint";
        this.tag = "Sprint";
        this.description = "ZOOOOOOOOM";
    }
    
    public void onUpdate() {
        if (Sprint.mc.player == null) {
            return;
        }
        if (this.rage.getValue(true) && (Sprint.mc.player.moveForward != 0.0f || Sprint.mc.player.moveStrafing != 0.0f)) {
            Sprint.mc.player.setSprinting(true);
        }
        else {
            Sprint.mc.player.setSprinting(Sprint.mc.player.moveForward > 0.0f || Sprint.mc.player.moveStrafing > 0.0f);
        }
    }
}
