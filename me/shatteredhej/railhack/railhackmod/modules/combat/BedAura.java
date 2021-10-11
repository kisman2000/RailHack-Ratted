//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;

public class BedAura extends Module
{
    Setting delay;
    Setting range;
    Setting hard;
    Setting swing;
    private BlockPos render_pos;
    private int counter;
    private spoof_face spoof_looking;
    
    public BedAura() {
        super(Category.Combat);
        this.delay = this.register("Delay", "BedAuraDelay", 6, 0, 20);
        this.range = this.register("Range", "BedAuraRange", 5, 0, 6);
        this.hard = this.register("Hard Rotate", "BedAuraRotate", false);
        this.swing = this.register("Swing", "BedAuraSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.name = "BedAura";
        this.tag = "BedAura";
        this.description = "fucking endcrystal.me";
    }
    
    protected void onEnable() {
        this.render_pos = null;
        this.counter = 0;
    }
    
    protected void whenDisabled() {
        this.render_pos = null;
    }
    
    public void onUpdate() {
        if (BedAura.mc.player == null) {
            return;
        }
        if (this.counter > this.delay.getValue(1)) {
            this.counter = 0;
            this.place_bed();
            this.break_bed();
            this.refill_bed();
        }
        ++this.counter;
    }
    
    public void refill_bed() {
        if (!(BedAura.mc.currentScreen instanceof GuiContainer) && this.is_space()) {
            for (int i = 9; i < 35; ++i) {
                if (BedAura.mc.player.inventory.getStackInSlot(i).getItem() == Items.BED) {
                    BedAura.mc.playerController.windowClick(BedAura.mc.player.inventoryContainer.windowId, i, 0, ClickType.QUICK_MOVE, (EntityPlayer)BedAura.mc.player);
                    break;
                }
            }
        }
    }
    
    private boolean is_space() {
        for (int i = 0; i < 9; ++i) {
            if (BedAura.mc.player.inventoryContainer.getSlot(i).getHasStack()) {
                return true;
            }
        }
        return false;
    }
    
    public void place_bed() {
        if (this.find_bed() == -1) {
            return;
        }
        final int bed_slot = this.find_bed();
        BlockPos best_pos = null;
        EntityPlayer best_target = null;
        float best_distance = (float)this.range.getValue(1);
        for (final EntityPlayer player : (List)BedAura.mc.world.playerEntities.stream().filter(entityPlayer -> !FriendUtil.isFriend(entityPlayer.getName())).collect(Collectors.toList())) {
            if (player == BedAura.mc.player) {
                continue;
            }
            if (best_distance < BedAura.mc.player.getDistance((Entity)player)) {
                continue;
            }
            boolean face_place = true;
            final BlockPos pos = get_pos_floor(player).down();
            final BlockPos pos2 = this.check_side_block(pos);
            if (pos2 != null) {
                best_pos = pos2.up();
                best_target = player;
                best_distance = BedAura.mc.player.getDistance((Entity)player);
                face_place = false;
            }
            if (!face_place) {
                continue;
            }
            final BlockPos upos = get_pos_floor(player);
            final BlockPos upos2 = this.check_side_block(upos);
            if (upos2 == null) {
                continue;
            }
            best_pos = upos2.up();
            best_target = player;
            best_distance = BedAura.mc.player.getDistance((Entity)player);
        }
        if (best_target == null) {
            return;
        }
        this.render_pos = best_pos;
        if (this.spoof_looking == spoof_face.NORTH) {
            if (this.hard.getValue(true)) {
                BedAura.mc.player.rotationYaw = 180.0f;
            }
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(180.0f, 0.0f, BedAura.mc.player.onGround));
        }
        else if (this.spoof_looking == spoof_face.SOUTH) {
            if (this.hard.getValue(true)) {
                BedAura.mc.player.rotationYaw = 0.0f;
            }
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(0.0f, 0.0f, BedAura.mc.player.onGround));
        }
        else if (this.spoof_looking == spoof_face.WEST) {
            if (this.hard.getValue(true)) {
                BedAura.mc.player.rotationYaw = 90.0f;
            }
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(90.0f, 0.0f, BedAura.mc.player.onGround));
        }
        else if (this.spoof_looking == spoof_face.EAST) {
            if (this.hard.getValue(true)) {
                BedAura.mc.player.rotationYaw = -90.0f;
            }
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(-90.0f, 0.0f, BedAura.mc.player.onGround));
        }
        BlockUtil.placeBlock(best_pos, bed_slot, false, false, this.swing);
    }
    
    public void break_bed() {
        for (final BlockPos pos : BlockInteractHelper.getSphere(get_pos_floor((EntityPlayer)BedAura.mc.player), (float)this.range.getValue(1), this.range.getValue(1), false, true, 0).stream().filter((Predicate<? super Object>)BedAura::is_bed).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList())) {
            if (BedAura.mc.player.isSneaking()) {
                BedAura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BedAura.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            BlockUtil.openBlock(pos);
        }
    }
    
    public int find_bed() {
        for (int i = 0; i < 9; ++i) {
            if (BedAura.mc.player.inventory.getStackInSlot(i).getItem() == Items.BED) {
                return i;
            }
        }
        return -1;
    }
    
    public BlockPos check_side_block(final BlockPos pos) {
        if (BedAura.mc.world.getBlockState(pos.east()).getBlock() != Blocks.AIR && BedAura.mc.world.getBlockState(pos.east().up()).getBlock() == Blocks.AIR) {
            this.spoof_looking = spoof_face.WEST;
            return pos.east();
        }
        if (BedAura.mc.world.getBlockState(pos.north()).getBlock() != Blocks.AIR && BedAura.mc.world.getBlockState(pos.north().up()).getBlock() == Blocks.AIR) {
            this.spoof_looking = spoof_face.SOUTH;
            return pos.north();
        }
        if (BedAura.mc.world.getBlockState(pos.west()).getBlock() != Blocks.AIR && BedAura.mc.world.getBlockState(pos.west().up()).getBlock() == Blocks.AIR) {
            this.spoof_looking = spoof_face.EAST;
            return pos.west();
        }
        if (BedAura.mc.world.getBlockState(pos.south()).getBlock() != Blocks.AIR && BedAura.mc.world.getBlockState(pos.south().up()).getBlock() == Blocks.AIR) {
            this.spoof_looking = spoof_face.NORTH;
            return pos.south();
        }
        return null;
    }
    
    public static BlockPos get_pos_floor(final EntityPlayer player) {
        return new BlockPos(Math.floor(player.posX), Math.floor(player.posY), Math.floor(player.posZ));
    }
    
    public static boolean is_bed(final BlockPos pos) {
        final Block block = BedAura.mc.world.getBlockState(pos).getBlock();
        return block == Blocks.BED;
    }
    
    public void render(final EventRender event) {
        if (this.render_pos == null) {
            return;
        }
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)this.render_pos.getX(), (float)this.render_pos.getY(), (float)this.render_pos.getZ(), 1.0f, 0.2f, 1.0f, 255, 20, 20, 180, "all");
        RenderHelp.release();
    }
    
    enum spoof_face
    {
        EAST, 
        WEST, 
        NORTH, 
        SOUTH;
    }
}
