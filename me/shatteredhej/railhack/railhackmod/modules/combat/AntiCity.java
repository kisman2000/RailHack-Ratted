//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class AntiCity extends Module
{
    Setting rotate;
    Setting swing;
    
    public AntiCity() {
        super(Category.Combat);
        this.rotate = this.register("Rotate", "SocksRotate", false);
        this.swing = this.register("Swing", "SocksSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.name = "AntiCity";
        this.tag = "AntiCity";
        this.description = "Protects your feet";
    }
    
    protected void onEnable() {
        if (this.find_in_hotbar() == -1) {
            this.setDisable();
        }
    }
    
    public void onUpdate() {
        final int slot = this.find_in_hotbar();
        if (slot == -1) {
            return;
        }
        final BlockPos center_pos = PlayerUtil.GetLocalPlayerPosFloored();
        final ArrayList<BlockPos> blocks_to_fill = new ArrayList<BlockPos>();
        switch (PlayerUtil.GetFacing()) {
            case East: {
                blocks_to_fill.add(center_pos.east().east());
                blocks_to_fill.add(center_pos.east().east().up());
                blocks_to_fill.add(center_pos.east().east().east());
                blocks_to_fill.add(center_pos.east().east().east().up());
                break;
            }
            case North: {
                blocks_to_fill.add(center_pos.north().north());
                blocks_to_fill.add(center_pos.north().north().up());
                blocks_to_fill.add(center_pos.north().north().north());
                blocks_to_fill.add(center_pos.north().north().north().up());
                break;
            }
            case South: {
                blocks_to_fill.add(center_pos.south().south());
                blocks_to_fill.add(center_pos.south().south().up());
                blocks_to_fill.add(center_pos.south().south().south());
                blocks_to_fill.add(center_pos.south().south().south().up());
                break;
            }
            case West: {
                blocks_to_fill.add(center_pos.west().west());
                blocks_to_fill.add(center_pos.west().west().up());
                blocks_to_fill.add(center_pos.west().west().west());
                blocks_to_fill.add(center_pos.west().west().west().up());
                break;
            }
        }
        BlockPos pos_to_fill = null;
        for (final BlockPos pos : blocks_to_fill) {
            final BlockInteractHelper.ValidResult result = BlockInteractHelper.valid(pos);
            if (result != BlockInteractHelper.ValidResult.Ok) {
                continue;
            }
            if (pos == null) {
                continue;
            }
            pos_to_fill = pos;
            break;
        }
        BlockUtil.placeBlock(pos_to_fill, this.find_in_hotbar(), this.rotate.getValue(true), this.rotate.getValue(true), this.swing);
    }
    
    private int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = AntiCity.mc.player.inventory.getStackInSlot(i);
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
}
