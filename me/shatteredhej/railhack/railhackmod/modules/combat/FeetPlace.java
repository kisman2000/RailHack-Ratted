//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class FeetPlace extends Module
{
    Setting ticks;
    Setting test;
    
    public FeetPlace() {
        super(Category.Combat);
        this.ticks = this.register("Ticks", "Ticks", 0.0, 0.0, 20.0);
        this.test = this.register("Test", "Test", false);
        this.name = "FeetPlace";
        this.tag = "FeetPlace";
        this.description = "glitch you in a block";
    }
    
    public void onUpdate() {
        if (FeetPlace.mc.player != null) {
            final Vec3d vec3d = EntityUtil.getInterpolatedPos((Entity)FeetPlace.mc.player, (float)this.ticks.getValue(1));
            final BlockPos blockPos = new BlockPos(vec3d).down();
            final BlockPos belowBlockPos = blockPos.down();
            if (FeetPlace.mc.player.onGround) {
                FeetPlace.mc.player.jump();
            }
            if (Wrapper.getWorld().getBlockState(blockPos).getMaterial().isReplaceable()) {
                int newSlot = -1;
                for (int oldSlot = 0; oldSlot < 9; ++oldSlot) {
                    final ItemStack stack = Wrapper.getPlayer().inventory.getStackInSlot(oldSlot);
                    if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                        final Block block = ((ItemBlock)stack.getItem()).getBlock();
                        if (!BlockInteractionHelper.blackList.contains(block) && !(block instanceof BlockContainer) && Block.getBlockFromItem(stack.getItem()).getDefaultState().isFullBlock() && (!(((ItemBlock)stack.getItem()).getBlock() instanceof BlockFalling) || !Wrapper.getWorld().getBlockState(belowBlockPos).getMaterial().isReplaceable())) {
                            newSlot = oldSlot;
                            break;
                        }
                    }
                }
                if (newSlot != -1) {
                    final int oldSlot = Wrapper.getPlayer().inventory.currentItem;
                    Wrapper.getPlayer().inventory.currentItem = newSlot;
                    if (BlockInteractionHelper.checkForNeighbours(blockPos)) {
                        BlockInteractionHelper.placeBlockScaffold(blockPos);
                        Wrapper.getPlayer().inventory.currentItem = oldSlot;
                        if (this.test.getValue(true)) {
                            FeetPlace.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(FeetPlace.mc.player.posX, FeetPlace.mc.player.posY + 7.0, FeetPlace.mc.player.posZ, false));
                        }
                        MessageUtil.send_client_message(ChatFormatting.GOLD + "FeetPlace complete." + ChatFormatting.RESET);
                        this.setDisable();
                    }
                }
            }
        }
    }
    
    private int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = FeetPlace.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stack.getItem()).getBlock();
                if (block instanceof BlockObsidian) {
                    return i;
                }
                if (block instanceof BlockEnderChest) {
                    return i;
                }
            }
        }
        return -1;
    }
}
