//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;

public class PacketFillUtil
{
    public static final Minecraft mc;
    
    public static boolean placeBlock(final BlockPos pos, final EnumHand hand, final boolean rotate, final boolean packet, final boolean isSneaking) {
        boolean sneaking = false;
        final EnumFacing side = getFirstFacing(pos);
        if (side == null) {
            return isSneaking;
        }
        final BlockPos neighbour = pos.offset(side);
        final EnumFacing opposite = side.getOpposite();
        final Vec3d hitVec = new Vec3d((Vec3i)neighbour).add(0.5, 0.5, 0.5).add(new Vec3d(opposite.getDirectionVec()).scale(0.5));
        final Block neighbourBlock = PacketFillUtil.mc.world.getBlockState(neighbour).getBlock();
        if (!PacketFillUtil.mc.player.isSneaking()) {
            PacketFillUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PacketFillUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            PacketFillUtil.mc.player.setSneaking(true);
            sneaking = true;
        }
        if (rotate) {
            faceVector(hitVec, true);
        }
        rightClickBlock(neighbour, hitVec, hand, opposite, packet);
        PacketFillUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        PacketFillUtil.mc.rightClickDelayTimer = 4;
        return sneaking || isSneaking;
    }
    
    public static List<EnumFacing> getPossibleSides(final BlockPos pos) {
        final List<EnumFacing> facings = new ArrayList<EnumFacing>();
        for (final EnumFacing side : EnumFacing.values()) {
            final BlockPos neighbour = pos.offset(side);
            if (PacketFillUtil.mc.world.getBlockState(neighbour).getBlock().canCollideCheck(PacketFillUtil.mc.world.getBlockState(neighbour), false)) {
                final IBlockState blockState = PacketFillUtil.mc.world.getBlockState(neighbour);
                if (!blockState.getMaterial().isReplaceable()) {
                    facings.add(side);
                }
            }
        }
        return facings;
    }
    
    public static EnumFacing getFirstFacing(final BlockPos pos) {
        final Iterator<EnumFacing> iterator = getPossibleSides(pos).iterator();
        if (iterator.hasNext()) {
            final EnumFacing facing = iterator.next();
            return facing;
        }
        return null;
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(PacketFillUtil.mc.player.posX, PacketFillUtil.mc.player.posY + PacketFillUtil.mc.player.getEyeHeight(), PacketFillUtil.mc.player.posZ);
    }
    
    public static float[] getLegitRotations(final Vec3d vec) {
        final Vec3d eyesPos = getEyesPos();
        final double diffX = vec.x - eyesPos.x;
        final double diffY = vec.y - eyesPos.y;
        final double diffZ = vec.z - eyesPos.z;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[] { PacketFillUtil.mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - PacketFillUtil.mc.player.rotationYaw), PacketFillUtil.mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - PacketFillUtil.mc.player.rotationPitch) };
    }
    
    public static void faceVector(final Vec3d vec, final boolean normalizeAngle) {
        final float[] rotations = getLegitRotations(vec);
        PacketFillUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(rotations[0], normalizeAngle ? ((float)MathHelper.normalizeAngle((int)rotations[1], 360)) : rotations[1], PacketFillUtil.mc.player.onGround));
    }
    
    public static void rightClickBlock(final BlockPos pos, final Vec3d vec, final EnumHand hand, final EnumFacing direction, final boolean packet) {
        if (packet) {
            final float f = (float)(vec.x - pos.getX());
            final float f2 = (float)(vec.y - pos.getY());
            final float f3 = (float)(vec.z - pos.getZ());
            PacketFillUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f2, f3));
        }
        else {
            PacketFillUtil.mc.playerController.processRightClickBlock(PacketFillUtil.mc.player, PacketFillUtil.mc.world, pos, direction, vec, hand);
        }
        PacketFillUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        PacketFillUtil.mc.rightClickDelayTimer = 4;
    }
    
    public static int findHotbarBlock(final Class clazz) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = PacketFillUtil.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY) {
                if (clazz.isInstance(stack.getItem())) {
                    return i;
                }
                if (stack.getItem() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.getItem()).getBlock();
                    if (clazz.isInstance(block)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static void switchToSlot(final int slot) {
        PacketFillUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
        PacketFillUtil.mc.player.inventory.currentItem = slot;
        PacketFillUtil.mc.playerController.updateController();
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
