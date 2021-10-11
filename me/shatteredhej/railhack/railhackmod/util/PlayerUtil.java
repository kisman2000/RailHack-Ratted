//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import net.minecraft.client.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;

public class PlayerUtil
{
    private static final Minecraft mc;
    
    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    public static int getItemInHotbar(final Item pItem) {
        for (int l_I = 0; l_I < 9; ++l_I) {
            final ItemStack l_Stack = PlayerUtil.mc.player.inventory.getStackInSlot(l_I);
            if (l_Stack != ItemStack.EMPTY && l_Stack.getItem() == pItem) {
                return l_I;
            }
        }
        return -1;
    }
    
    public static FacingDirection GetFacing() {
        switch (MathHelper.floor(PlayerUtil.mc.player.rotationYaw * 8.0f / 360.0f + 0.5) & 0x7) {
            case 0:
            case 1: {
                return FacingDirection.South;
            }
            case 2:
            case 3: {
                return FacingDirection.West;
            }
            case 4:
            case 5: {
                return FacingDirection.North;
            }
            case 6:
            case 7: {
                return FacingDirection.East;
            }
            default: {
                return FacingDirection.North;
            }
        }
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public enum FacingDirection
    {
        North, 
        South, 
        East, 
        West;
    }
}
