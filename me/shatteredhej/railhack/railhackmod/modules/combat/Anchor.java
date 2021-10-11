//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import java.util.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.function.*;
import net.minecraft.init.*;

public class Anchor extends Module
{
    Setting Pitch;
    Setting Pull;
    private final ArrayList<BlockPos> holes;
    int holeblocks;
    public static boolean AnchorING;
    private Vec3d Center;
    @EventHandler
    private Listener<EventMotionUpdate> OnClientTick;
    
    public Anchor() {
        super(Category.Combat);
        this.Pitch = this.register("Pitch", "AnchorPitch", 60, 0, 90);
        this.Pull = this.register("Pull", "AnchorPull", true);
        this.holes = new ArrayList<BlockPos>();
        this.Center = Vec3d.ZERO;
        double XDiff;
        double ZDiff;
        double MotionX;
        double MotionZ;
        this.OnClientTick = new Listener<EventMotionUpdate>(event -> {
            if (Anchor.mc.player.rotationPitch >= this.Pitch.getValue(60)) {
                if (this.isBlockHole(this.getPlayerPos().down(1)) || this.isBlockHole(this.getPlayerPos().down(2)) || this.isBlockHole(this.getPlayerPos().down(3)) || this.isBlockHole(this.getPlayerPos().down(4))) {
                    Anchor.AnchorING = true;
                    if (!this.Pull.getValue(true)) {
                        Anchor.mc.player.motionX = 0.0;
                        Anchor.mc.player.motionZ = 0.0;
                    }
                    else {
                        this.Center = this.GetCenter(Anchor.mc.player.posX, Anchor.mc.player.posY, Anchor.mc.player.posZ);
                        XDiff = Math.abs(this.Center.x - Anchor.mc.player.posX);
                        ZDiff = Math.abs(this.Center.z - Anchor.mc.player.posZ);
                        if (XDiff <= 0.1 && ZDiff <= 0.1) {
                            this.Center = Vec3d.ZERO;
                        }
                        else {
                            MotionX = this.Center.x - Anchor.mc.player.posX;
                            MotionZ = this.Center.z - Anchor.mc.player.posZ;
                            Anchor.mc.player.motionX = MotionX / 2.0;
                            Anchor.mc.player.motionZ = MotionZ / 2.0;
                        }
                    }
                }
                else {
                    Anchor.AnchorING = false;
                }
            }
            return;
        }, (Predicate<EventMotionUpdate>[])new Predicate[0]);
        this.name = "Anchor";
        this.tag = "WurstPlusAnchor";
        this.description = "Stops all movement if player is above a hole";
    }
    
    public boolean isBlockHole(final BlockPos blockpos) {
        this.holeblocks = 0;
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 3, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK) {
            ++this.holeblocks;
        }
        return this.holeblocks >= 9;
    }
    
    public Vec3d GetCenter(final double posX, final double posY, final double posZ) {
        final double x = Math.floor(posX) + 0.5;
        final double y = Math.floor(posY);
        final double z = Math.floor(posZ) + 0.5;
        return new Vec3d(x, y, z);
    }
    
    public void onDisable() {
        Anchor.AnchorING = false;
        this.holeblocks = 0;
    }
    
    public BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(Anchor.mc.player.posX), Math.floor(Anchor.mc.player.posY), Math.floor(Anchor.mc.player.posZ));
    }
}
