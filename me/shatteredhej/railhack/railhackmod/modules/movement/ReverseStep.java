//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.entity.*;

public class ReverseStep extends Module
{
    Setting yMotion;
    
    public ReverseStep() {
        super(Category.Movement);
        this.yMotion = this.register("MotionY", "MotionYReverseStep", 1, 1, 10);
        this.name = "ReverseStep";
        this.tag = "ReverseStep";
    }
    
    public void onUpdate() {
        final EntityPlayerSP player = ReverseStep.mc.player;
        player.motionY -= this.yMotion.getValue(1);
    }
}
