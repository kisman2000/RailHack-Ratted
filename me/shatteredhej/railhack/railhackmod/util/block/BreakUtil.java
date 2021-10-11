//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.block;

import net.minecraft.client.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;

public class BreakUtil
{
    private static final Minecraft mc;
    private static BlockPos current_block;
    private static boolean is_mining;
    
    public static void set_current_block(final BlockPos pos) {
        BreakUtil.current_block = pos;
    }
    
    private static boolean is_done(final IBlockState state) {
        return state.getBlock() == Blocks.BEDROCK || state.getBlock() == Blocks.AIR || state.getBlock() instanceof BlockLiquid;
    }
    
    public static boolean update(final float range, final boolean ray_trace) {
        if (BreakUtil.current_block == null) {
            return false;
        }
        final IBlockState state = BreakUtil.mc.world.getBlockState(BreakUtil.current_block);
        if (is_done(state) || BreakUtil.mc.player.getDistanceSq(BreakUtil.current_block) > range * range) {
            BreakUtil.current_block = null;
            return false;
        }
        BreakUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        EnumFacing facing = EnumFacing.UP;
        if (ray_trace) {
            final RayTraceResult r = BreakUtil.mc.world.rayTraceBlocks(new Vec3d(BreakUtil.mc.player.posX, BreakUtil.mc.player.posY + BreakUtil.mc.player.getEyeHeight(), BreakUtil.mc.player.posZ), new Vec3d(BreakUtil.current_block.getX() + 0.5, BreakUtil.current_block.getY() - 0.5, BreakUtil.current_block.getZ() + 0.5));
            if (r != null && r.sideHit != null) {
                facing = r.sideHit;
            }
        }
        if (!BreakUtil.is_mining) {
            BreakUtil.is_mining = true;
            BreakUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, BreakUtil.current_block, facing));
        }
        else {
            BreakUtil.mc.playerController.onPlayerDamageBlock(BreakUtil.current_block, facing);
        }
        return true;
    }
    
    static {
        mc = Minecraft.getMinecraft();
        BreakUtil.current_block = null;
        BreakUtil.is_mining = false;
    }
}
