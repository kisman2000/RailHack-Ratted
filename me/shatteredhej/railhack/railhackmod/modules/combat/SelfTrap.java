//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.damage.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import net.minecraft.item.*;
import net.minecraft.block.*;

public class SelfTrap extends Module
{
    Setting oneDotThirteen;
    Setting toggle;
    Setting rotate;
    Setting swing;
    private BlockPos trap_pos;
    
    public SelfTrap() {
        super(Category.Combat);
        this.oneDotThirteen = this.register("1.13Mode", "SelfTrapThirteen", false);
        this.toggle = this.register("Toggle", "SelfTrapToggle", false);
        this.rotate = this.register("Rotate", "SelfTrapRotate", false);
        this.swing = this.register("Swing", "SelfTrapSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.name = "SelfTrap";
        this.tag = "SelfTrap";
        this.description = "oh 'eck, ive trapped me sen again";
    }
    
    protected void onEnable() {
        if (this.find_in_hotbar() == -1) {
            this.setDisable();
        }
    }
    
    public void onUpdate() {
        final Vec3d pos = MathUtil.interpolateEntity((Entity)SelfTrap.mc.player, SelfTrap.mc.getRenderPartialTicks());
        this.trap_pos = new BlockPos(pos.x, pos.y + 2.0, pos.z);
        if (this.is_trapped() && !this.toggle.getValue(true)) {
            this.toggle();
            return;
        }
        final BlockInteractHelper.ValidResult result = BlockInteractHelper.valid(this.trap_pos);
        if (result == BlockInteractHelper.ValidResult.AlreadyBlockThere && !SelfTrap.mc.world.getBlockState(this.trap_pos).getMaterial().isReplaceable()) {
            return;
        }
        if (result == BlockInteractHelper.ValidResult.NoNeighbors) {
            final BlockPos[] array;
            final BlockPos[] tests = array = new BlockPos[] { this.trap_pos.north(), this.trap_pos.south(), this.trap_pos.east(), this.trap_pos.west(), this.trap_pos.up(), this.trap_pos.down().west() };
            for (final BlockPos pos_ : array) {
                final BlockInteractHelper.ValidResult result_ = BlockInteractHelper.valid(pos_);
                if (result_ != BlockInteractHelper.ValidResult.NoNeighbors) {
                    if (result_ != BlockInteractHelper.ValidResult.NoEntityCollision) {
                        if (BlockUtil.placeBlock(pos_, this.find_in_hotbar(), this.rotate.getValue(true), this.rotate.getValue(true), this.swing)) {
                            return;
                        }
                    }
                }
            }
            return;
        }
        BlockUtil.placeBlock(this.trap_pos, this.find_in_hotbar(), this.rotate.getValue(true), this.rotate.getValue(true), this.swing);
    }
    
    public boolean is_trapped() {
        if (this.trap_pos == null) {
            return false;
        }
        final IBlockState state = SelfTrap.mc.world.getBlockState(this.trap_pos);
        return state.getBlock() != Blocks.AIR && state.getBlock() != Blocks.WATER && state.getBlock() != Blocks.LAVA;
    }
    
    private int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = SelfTrap.mc.player.inventory.getStackInSlot(i);
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
