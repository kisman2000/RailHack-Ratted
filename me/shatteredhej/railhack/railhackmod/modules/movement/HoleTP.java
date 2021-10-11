//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;

public class HoleTP extends Module
{
    public HoleTP() {
        super(Category.Movement);
        this.name = "HoleTP";
        this.tag = "HoleTP";
    }
    
    public void onUpdate() {
        if (HoleTP.mc.player != null && this.isAboveHole()) {
            HoleTP.mc.player.motionY = -1.0;
        }
    }
    
    private boolean isAboveHole() {
        final BlockPos playerBlock = PlayerUtil.GetLocalPlayerPosFloored();
        return HoleTP.mc.world.getBlockState(playerBlock.east()).getBlock() != Blocks.AIR && HoleTP.mc.world.getBlockState(playerBlock.west()).getBlock() != Blocks.AIR && HoleTP.mc.world.getBlockState(playerBlock.north()).getBlock() != Blocks.AIR && HoleTP.mc.world.getBlockState(playerBlock.south()).getBlock() != Blocks.AIR;
    }
}
