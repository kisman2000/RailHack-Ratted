//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class Surround extends Module
{
    Setting rotate;
    Setting hybrid;
    Setting triggerable;
    Setting center;
    Setting block_head;
    Setting tick_for_place;
    Setting tick_timeout;
    Setting swing;
    Setting iron;
    private int y_level;
    private int tick_runs;
    private int offset_step;
    private Vec3d center_block;
    Vec3d[] surround_targets;
    Vec3d[] surround_targets_face;
    
    public Surround() {
        super(Category.Combat);
        this.rotate = this.register("Rotate", "SurroundSmoth", true);
        this.hybrid = this.register("Hybrid", "SurroundHybrid", true);
        this.triggerable = this.register("Toggle", "SurroundToggle", true);
        this.center = this.register("Center", "SurroundCenter", false);
        this.block_head = this.register("Block Face", "SurroundBlockFace", false);
        this.tick_for_place = this.register("Blocks per tick", "SurroundTickToPlace", 2, 1, 8);
        this.tick_timeout = this.register("Ticks til timeout", "SurroundTicks", 20, 10, 50);
        this.swing = this.register("Swing", "SurroundSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.iron = this.register("Iron", "Iron", false);
        this.y_level = 0;
        this.tick_runs = 0;
        this.offset_step = 0;
        this.center_block = Vec3d.ZERO;
        this.surround_targets = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(0.0, -1.0, 0.0) };
        this.surround_targets_face = new Vec3d[] { new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(0.0, -1.0, 0.0) };
        this.name = "Surround";
        this.tag = "Surround";
        this.description = "surround urself with obi and such";
    }
    
    public void onEnable() {
        if (this.find_in_hotbar() == -1) {
            this.setDisable();
            return;
        }
        if (Surround.mc.player != null) {
            this.y_level = (int)Math.round(Surround.mc.player.posY);
            this.center_block = this.get_center(Surround.mc.player.posX, Surround.mc.player.posY, Surround.mc.player.posZ);
            if (this.center.getValue(true)) {
                Surround.mc.player.motionX = 0.0;
                Surround.mc.player.motionZ = 0.0;
            }
        }
    }
    
    public void onUpdate() {
        if (Surround.mc.player != null) {
            if (this.center_block != Vec3d.ZERO && this.center.getValue(true)) {
                final double x_diff = Math.abs(this.center_block.x - Surround.mc.player.posX);
                final double z_diff = Math.abs(this.center_block.z - Surround.mc.player.posZ);
                if (x_diff <= 0.1 && z_diff <= 0.1) {
                    this.center_block = Vec3d.ZERO;
                }
                else {
                    final double motion_x = this.center_block.x - Surround.mc.player.posX;
                    final double motion_z = this.center_block.z - Surround.mc.player.posZ;
                    Surround.mc.player.motionX = motion_x / 2.0;
                    Surround.mc.player.motionZ = motion_z / 2.0;
                }
            }
            if ((int)Math.round(Surround.mc.player.posY) != this.y_level && this.hybrid.getValue(true)) {
                this.setDisable();
                return;
            }
            if (!this.triggerable.getValue(true) && this.tick_runs >= this.tick_timeout.getValue(1)) {
                this.tick_runs = 0;
                this.setDisable();
                return;
            }
            int blocks_placed = 0;
            while (blocks_placed < this.tick_for_place.getValue(1)) {
                if (this.offset_step >= (this.block_head.getValue(true) ? this.surround_targets_face.length : this.surround_targets.length)) {
                    this.offset_step = 0;
                    break;
                }
                final BlockPos offsetPos = new BlockPos(this.block_head.getValue(true) ? this.surround_targets_face[this.offset_step] : this.surround_targets[this.offset_step]);
                final BlockPos targetPos = new BlockPos(Surround.mc.player.getPositionVector()).add(offsetPos.getX(), offsetPos.getY(), offsetPos.getZ());
                boolean try_to_place = true;
                if (!Surround.mc.world.getBlockState(targetPos).getMaterial().isReplaceable()) {
                    try_to_place = false;
                }
                for (final Entity entity : Surround.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(targetPos))) {
                    if (!(entity instanceof EntityItem)) {
                        if (entity instanceof EntityXPOrb) {
                            continue;
                        }
                        try_to_place = false;
                        break;
                    }
                }
                if (try_to_place && BlockUtil.placeBlock(targetPos, this.find_in_hotbar(), this.rotate.getValue(true), this.rotate.getValue(true), this.swing)) {
                    ++blocks_placed;
                }
                if (this.iron.getValue(true)) {}
                ++this.offset_step;
            }
            ++this.tick_runs;
        }
    }
    
    private int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = Surround.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stack.getItem()).getBlock();
                if (block instanceof BlockEnderChest) {
                    return i;
                }
                if (block instanceof BlockObsidian) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static void rightClickBlock(final BlockPos pos, final Vec3d vec, final EnumHand hand, final EnumFacing direction, final boolean packet) {
        if (packet) {
            final float f = (float)(vec.x - pos.getX());
            final float f2 = (float)(vec.y - pos.getY());
            final float f3 = (float)(vec.z - pos.getZ());
            Surround.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f2, f3));
        }
        else {
            Surround.mc.playerController.processRightClickBlock(Surround.mc.player, Surround.mc.world, pos, direction, vec, hand);
        }
        Surround.mc.player.swingArm(EnumHand.MAIN_HAND);
        Surround.mc.rightClickDelayTimer = 4;
    }
    
    public Vec3d get_center(final double posX, final double posY, final double posZ) {
        final double x = Math.floor(posX) + 0.5;
        final double y = Math.floor(posY);
        final double z = Math.floor(posZ) + 0.5;
        return new Vec3d(x, y, z);
    }
}
