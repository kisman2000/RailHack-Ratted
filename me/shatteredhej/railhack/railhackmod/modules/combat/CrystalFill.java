//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.init.*;

public class CrystalFill extends Module
{
    Setting crystalfill_toggle;
    Setting crystalfill_rotate;
    Setting crystalfill_range;
    Setting swing;
    private final ArrayList<BlockPos> holes;
    
    public CrystalFill() {
        super(Category.Combat);
        this.crystalfill_toggle = this.register("Toggle", "CrystalFillToggle", true);
        this.crystalfill_rotate = this.register("Rotate", "CrystalFillRotate", true);
        this.crystalfill_range = this.register("Range", "CrystalFillRange", 4, 1, 6);
        this.swing = this.register("Swing", "HoleFillSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.holes = new ArrayList<BlockPos>();
        this.name = "CrystalFill";
        this.tag = "CrystalFill";
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
        if (this.find_in_hotbar() == -1) {
            this.whenDisabled();
            return;
        }
        if (this.holes.isEmpty()) {
            if (!this.crystalfill_toggle.getValue(true)) {
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
        if (pos_to_fill != null && BlockUtil.placeBlock(pos_to_fill, this.find_in_hotbar(), this.crystalfill_rotate.getValue(true), this.crystalfill_rotate.getValue(true), this.swing)) {
            this.holes.remove(pos_to_fill);
        }
    }
    
    public void find_new_holes() {
        this.holes.clear();
        for (final BlockPos pos : BlockInteractHelper.getSphere(PlayerUtil.GetLocalPlayerPosFloored(), (float)this.crystalfill_range.getValue(1), this.crystalfill_range.getValue(1), false, true, 0)) {
            if (!CrystalFill.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!CrystalFill.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            if (!CrystalFill.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                continue;
            }
            boolean possible = true;
            for (final BlockPos seems_blocks : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
                final Block block = CrystalFill.mc.world.getBlockState(pos.add((Vec3i)seems_blocks)).getBlock();
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
            if (CrystalFill.mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
    }
}
