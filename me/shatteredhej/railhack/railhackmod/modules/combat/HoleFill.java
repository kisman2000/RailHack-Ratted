//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class HoleFill extends Module
{
    Setting hole_toggle;
    Setting hole_rotate;
    Setting hole_range;
    Setting range;
    Setting rangeDistance;
    Setting swing;
    private final ArrayList<BlockPos> holes;
    
    public HoleFill() {
        super(Category.Combat);
        this.hole_toggle = this.register("Toggle", "HoleFillToggle", true);
        this.hole_rotate = this.register("Rotate", "HoleFillRotate", true);
        this.hole_range = this.register("Range", "HoleFillRange", 4, 1, 6);
        this.range = this.register("Range", "HoleFillRangeBoolean", false);
        this.rangeDistance = this.register("Range Distance", "HoleFillRangeDistance", 0, 0, 15);
        this.swing = this.register("Swing", "HoleFillSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.holes = new ArrayList<BlockPos>();
        this.name = "HoleFill";
        this.tag = "HoleFill";
        this.description = "Turn holes into floors";
    }
    
    public void onEnable() {
        if (this.find_in_hotbar() == -1) {
            this.setDisable();
        }
        this.find_new_holes();
    }
    
    public void whenDisabled() {
        this.holes.clear();
    }
    
    public void onUpdate() {
        if (this.range.getValue(true)) {
            for (final Entity e : HoleFill.mc.world.loadedEntityList) {
                if (e instanceof EntityOtherPlayerMP && HoleFill.mc.player.getDistance(e) > this.rangeDistance.getValue(1)) {
                    return;
                }
            }
        }
        if (this.find_in_hotbar() == -1) {
            this.whenDisabled();
            return;
        }
        if (this.holes.isEmpty()) {
            if (!this.hole_toggle.getValue(true)) {
                this.setDisable();
                MessageUtil.toggle_message(this);
                return;
            }
            this.find_new_holes();
        }
        BlockPos pos_to_fill = null;
        for (final BlockPos pos : new ArrayList<BlockPos>(this.holes)) {
            if (pos == null) {
                continue;
            }
            final BlockInteractHelper.ValidResult result = BlockInteractHelper.valid(pos);
            if (result == BlockInteractHelper.ValidResult.Ok) {
                pos_to_fill = pos;
                break;
            }
            this.holes.remove(pos);
        }
        if (this.find_in_hotbar() == -1) {
            this.whenDisabled();
            return;
        }
        if (pos_to_fill != null && BlockUtil.placeBlock(pos_to_fill, this.find_in_hotbar(), this.hole_rotate.getValue(true), this.hole_rotate.getValue(true), this.swing)) {
            this.holes.remove(pos_to_fill);
        }
    }
    
    public void find_new_holes() {
        this.holes.clear();
        for (final BlockPos pos : BlockInteractHelper.getSphere(PlayerUtil.GetLocalPlayerPosFloored(), (float)this.hole_range.getValue(1), this.hole_range.getValue(1), false, true, 0)) {
            if (!HoleFill.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleFill.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!HoleFill.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            boolean possible = true;
            for (final BlockPos seems_blocks : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
                final Block block = HoleFill.mc.world.getBlockState(pos.add((Vec3i)seems_blocks)).getBlock();
                if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
                    possible = false;
                    break;
                }
            }
            if (!possible) {
                continue;
            }
            this.holes.add(pos);
        }
    }
    
    private int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = HoleFill.mc.player.inventory.getStackInSlot(i);
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
