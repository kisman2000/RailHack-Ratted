//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.entity.player.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

public class FeetWeb extends Module
{
    Setting always_on;
    Setting rotate;
    Setting range;
    int new_slot;
    boolean sneak;
    
    public FeetWeb() {
        super(Category.Combat);
        this.always_on = this.register("Always On", "AlwaysOn", true);
        this.rotate = this.register("Rotate", "AutoWebRotate", true);
        this.range = this.register("Enemy Range", "AutoWebRange", 4, 0, 8);
        this.new_slot = -1;
        this.sneak = false;
        this.name = "FeetWeb";
        this.tag = "FeetWeb";
        this.description = "places fuckin webs at ur feet";
    }
    
    public void onEnable() {
        if (FeetWeb.mc.player != null) {
            this.new_slot = this.find_in_hotbar();
            if (this.new_slot == -1) {
                MessageUtil.send_client_error_message("cannot find webs in hotbar");
                this.setActive(false);
            }
        }
    }
    
    public void whenDisabled() {
        if (FeetWeb.mc.player != null && this.sneak) {
            FeetWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)FeetWeb.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneak = false;
        }
    }
    
    public void onUpdate() {
        if (FeetWeb.mc.player == null) {
            return;
        }
        if (this.always_on.getValue(true)) {
            final EntityPlayer target = this.find_closest_target();
            if (target == null) {
                return;
            }
            if (FeetWeb.mc.player.getDistance((Entity)target) < this.range.getValue(1) && this.is_surround()) {
                final int last_slot = FeetWeb.mc.player.inventory.currentItem;
                FeetWeb.mc.player.inventory.currentItem = this.new_slot;
                FeetWeb.mc.playerController.updateController();
                this.place_blocks(PlayerUtil.GetLocalPlayerPosFloored());
                FeetWeb.mc.player.inventory.currentItem = last_slot;
            }
        }
        else {
            final int last_slot2 = FeetWeb.mc.player.inventory.currentItem;
            FeetWeb.mc.player.inventory.currentItem = this.new_slot;
            FeetWeb.mc.playerController.updateController();
            this.place_blocks(PlayerUtil.GetLocalPlayerPosFloored());
            FeetWeb.mc.player.inventory.currentItem = last_slot2;
            this.setDisable();
        }
    }
    
    public EntityPlayer find_closest_target() {
        if (FeetWeb.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        EntityPlayer closestTarget = null;
        for (final EntityPlayer target : FeetWeb.mc.world.playerEntities) {
            if (target == FeetWeb.mc.player) {
                continue;
            }
            if (FriendUtil.isFriend(target.getName())) {
                continue;
            }
            if (!EntityUtil.isLiving((Entity)target)) {
                continue;
            }
            if (target.getHealth() <= 0.0f) {
                continue;
            }
            if (closestTarget != null && FeetWeb.mc.player.getDistance((Entity)target) > FeetWeb.mc.player.getDistance((Entity)closestTarget)) {
                continue;
            }
            closestTarget = target;
        }
        return closestTarget;
    }
    
    private int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = FeetWeb.mc.player.inventory.getStackInSlot(i);
            if (stack.getItem() == Item.getItemById(30)) {
                return i;
            }
        }
        return -1;
    }
    
    private boolean is_surround() {
        final BlockPos player_block = PlayerUtil.GetLocalPlayerPosFloored();
        return FeetWeb.mc.world.getBlockState(player_block.east()).getBlock() != Blocks.AIR && FeetWeb.mc.world.getBlockState(player_block.west()).getBlock() != Blocks.AIR && FeetWeb.mc.world.getBlockState(player_block.north()).getBlock() != Blocks.AIR && FeetWeb.mc.world.getBlockState(player_block.south()).getBlock() != Blocks.AIR && FeetWeb.mc.world.getBlockState(player_block).getBlock() == Blocks.AIR;
    }
    
    private void place_blocks(final BlockPos pos) {
        if (!FeetWeb.mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            return;
        }
        if (!BlockInteractHelper.checkForNeighbours(pos)) {
            return;
        }
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbor = pos.offset(side);
            final EnumFacing side2 = side.getOpposite();
            if (BlockInteractHelper.canBeClicked(neighbor)) {
                if (BlockInteractHelper.blackList.contains(FeetWeb.mc.world.getBlockState(neighbor).getBlock())) {
                    FeetWeb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)FeetWeb.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    this.sneak = true;
                }
                final Vec3d hitVec = new Vec3d((Vec3i)neighbor).add(0.5, 0.5, 0.5).add(new Vec3d(side2.getDirectionVec()).scale(0.5));
                if (this.rotate.getValue(true)) {
                    BlockInteractHelper.faceVectorPacketInstant(hitVec);
                }
                FeetWeb.mc.playerController.processRightClickBlock(FeetWeb.mc.player, FeetWeb.mc.world, neighbor, side2, hitVec, EnumHand.MAIN_HAND);
                FeetWeb.mc.player.swingArm(EnumHand.MAIN_HAND);
                return;
            }
        }
    }
}
