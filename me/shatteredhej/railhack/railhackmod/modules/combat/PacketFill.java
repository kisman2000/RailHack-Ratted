//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.block.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.util.*;

public class PacketFill extends Module
{
    Setting mode;
    Setting rotate;
    Setting offset;
    Setting onlyHole;
    Setting stopSneak;
    Setting range;
    Setting rangeDistance;
    private BlockPos originalPos;
    private int oldSlot;
    
    public PacketFill() {
        super(Category.Combat);
        this.mode = this.register("Mode: ", "Mode", "Obsidian", this.combobox(new String[] { "Obsidian", "EChest" }));
        this.rotate = this.register("Rotate", "Rotate", false);
        this.offset = this.register("Offset", "Offset", -20.0, -20.0, 20.0);
        this.onlyHole = this.register("Only Hole", "PacketFillOnlyHole", false);
        this.stopSneak = this.register("Stop Sneak", "StopSneakPacketFill", true);
        this.range = this.register("Range", "PacketFillRange", false);
        this.rangeDistance = this.register("Range Distance", "PacketFIllRangeDistance", 0, 0, 15);
        this.oldSlot = -1;
        this.name = "PacketFill";
        this.tag = "PacketFill";
    }
    
    public void onEnable() {
        this.originalPos = new BlockPos(PacketFill.mc.player.posX, PacketFill.mc.player.posY, PacketFill.mc.player.posZ);
        if (PacketFill.mc.world.getBlockState(new BlockPos(PacketFill.mc.player.posX, PacketFill.mc.player.posY, PacketFill.mc.player.posZ)).getBlock().equals(Blocks.OBSIDIAN) || this.intersectsWithEntity(this.originalPos)) {
            this.toggle();
            return;
        }
        this.oldSlot = PacketFill.mc.player.inventory.currentItem;
    }
    
    public void onUpdate() {
        if (this.range.getValue(true)) {
            for (final Entity e : PacketFill.mc.world.loadedEntityList) {
                if (e instanceof EntityOtherPlayerMP && PacketFill.mc.player.getDistance(e) > this.rangeDistance.getValue(1)) {
                    return;
                }
            }
        }
        if (this.onlyHole.getValue(true) && !this.isInHole()) {
            this.toggle();
            return;
        }
        if (this.mode.in("Obsidian") && PacketFillUtil.findHotbarBlock(BlockObsidian.class) == -1) {
            MessageUtil.send_client_message("Can't find obsidian in hotbar!");
            this.toggle();
            return;
        }
        if (this.mode.in("EChest") && PacketFillUtil.findHotbarBlock(BlockEnderChest.class) == -1) {
            MessageUtil.send_client_message("Can't find ender chest in hotbar!");
            this.toggle();
            return;
        }
        if (this.mode.in("Obsidian")) {
            PacketFillUtil.switchToSlot(PacketFillUtil.findHotbarBlock(BlockObsidian.class));
        }
        if (this.mode.in("EChest")) {
            PacketFillUtil.switchToSlot(PacketFillUtil.findHotbarBlock(BlockEnderChest.class));
        }
        PacketFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PacketFill.mc.player.posX, PacketFill.mc.player.posY + 0.41999998688698, PacketFill.mc.player.posZ, true));
        PacketFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PacketFill.mc.player.posX, PacketFill.mc.player.posY + 0.7531999805211997, PacketFill.mc.player.posZ, true));
        PacketFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PacketFill.mc.player.posX, PacketFill.mc.player.posY + 1.00133597911214, PacketFill.mc.player.posZ, true));
        PacketFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PacketFill.mc.player.posX, PacketFill.mc.player.posY + 1.16610926093821, PacketFill.mc.player.posZ, true));
        PacketFillUtil.placeBlock(this.originalPos, EnumHand.MAIN_HAND, this.rotate.getValue(true), true, false);
        PacketFill.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PacketFill.mc.player.posX, PacketFill.mc.player.posY + this.offset.getValue(1), PacketFill.mc.player.posZ, false));
        PacketFillUtil.switchToSlot(this.oldSlot);
        if (this.stopSneak.getValue(true)) {
            PacketFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PacketFill.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        MessageUtil.send_client_message("PacketFill completed");
        this.setDisable();
    }
    
    private boolean intersectsWithEntity(final BlockPos pos) {
        for (final Entity entity : PacketFill.mc.world.loadedEntityList) {
            if (entity.equals((Object)PacketFill.mc.player)) {
                continue;
            }
            if (entity instanceof EntityItem) {
                continue;
            }
            if (new AxisAlignedBB(pos).intersects(entity.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isInHole() {
        final BlockPos playerBlock = PlayerUtil.GetLocalPlayerPosFloored();
        return PacketFill.mc.world.getBlockState(playerBlock.east()).getBlock() != Blocks.AIR && PacketFill.mc.world.getBlockState(playerBlock.west()).getBlock() != Blocks.AIR && PacketFill.mc.world.getBlockState(playerBlock.north()).getBlock() != Blocks.AIR && PacketFill.mc.world.getBlockState(playerBlock.south()).getBlock() != Blocks.AIR;
    }
}
