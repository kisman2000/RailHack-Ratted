//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.gui.inventory.*;
import me.shatteredhej.railhack.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;

public class OffhandOld extends Module
{
    Setting switch_mode;
    Setting totem_switch;
    Setting gapple_in_hole;
    Setting gapple_hole_hp;
    Setting delay;
    private boolean switching;
    private int last_slot;
    
    public OffhandOld() {
        super(Category.Combat);
        this.switch_mode = this.register("Offhand", "OffhandOffhand", "Totem", this.combobox(new String[] { "Totem", "Crystal", "Gapple" }));
        this.totem_switch = this.register("Totem HP", "OffhandTotemHP", 16, 0, 36);
        this.gapple_in_hole = this.register("Gapple In Hole", "OffhandGapple", false);
        this.gapple_hole_hp = this.register("Gapple Hole HP", "OffhandGappleHP", 8, 0, 36);
        this.delay = this.register("Delay", "OffhandDelay", false);
        this.switching = false;
        this.name = "OffhandOld";
        this.tag = "OffhandOld";
        this.description = "offhand but its the stock one";
    }
    
    public void onUpdate() {
        if (OffhandOld.mc.currentScreen == null || OffhandOld.mc.currentScreen instanceof GuiInventory) {
            if (this.switching) {
                this.swap_items(this.last_slot, 2);
                return;
            }
            final float hp = OffhandOld.mc.player.getHealth() + OffhandOld.mc.player.getAbsorptionAmount();
            if (hp <= this.totem_switch.getValue(1)) {
                this.swap_items(this.get_item_slot(Items.TOTEM_OF_UNDYING), this.delay.getValue(true) ? 1 : 0);
                return;
            }
            if (this.switch_mode.in("Crystal") && RailHack.get_hack_manager().getModuleWithTag("AutoCrystal").isActive()) {
                this.swap_items(this.get_item_slot(Items.END_CRYSTAL), 0);
                return;
            }
            if (this.gapple_in_hole.getValue(true) && hp > this.gapple_hole_hp.getValue(1) && this.is_in_hole()) {
                this.swap_items(this.get_item_slot(Items.GOLDEN_APPLE), this.delay.getValue(true) ? 1 : 0);
                return;
            }
            if (this.switch_mode.in("Totem")) {
                this.swap_items(this.get_item_slot(Items.TOTEM_OF_UNDYING), this.delay.getValue(true) ? 1 : 0);
                return;
            }
            if (this.switch_mode.in("Gapple")) {
                this.swap_items(this.get_item_slot(Items.GOLDEN_APPLE), this.delay.getValue(true) ? 1 : 0);
                return;
            }
            if (this.switch_mode.in("Crystal") && !RailHack.get_hack_manager().getModuleWithTag("AutoCrystal").isActive()) {
                this.swap_items(this.get_item_slot(Items.TOTEM_OF_UNDYING), 0);
                return;
            }
            if (OffhandOld.mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
                this.swap_items(this.get_item_slot(Items.TOTEM_OF_UNDYING), this.delay.getValue(true) ? 1 : 0);
            }
        }
    }
    
    public void swap_items(final int slot, final int step) {
        if (slot == -1) {
            return;
        }
        if (step == 0) {
            OffhandOld.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)OffhandOld.mc.player);
            OffhandOld.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)OffhandOld.mc.player);
            OffhandOld.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)OffhandOld.mc.player);
        }
        if (step == 1) {
            OffhandOld.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)OffhandOld.mc.player);
            this.switching = true;
            this.last_slot = slot;
        }
        if (step == 2) {
            OffhandOld.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)OffhandOld.mc.player);
            OffhandOld.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)OffhandOld.mc.player);
            this.switching = false;
        }
        OffhandOld.mc.playerController.updateController();
    }
    
    private boolean is_in_hole() {
        final BlockPos player_block = PlayerUtil.GetLocalPlayerPosFloored();
        return OffhandOld.mc.world.getBlockState(player_block.east()).getBlock() != Blocks.AIR && OffhandOld.mc.world.getBlockState(player_block.west()).getBlock() != Blocks.AIR && OffhandOld.mc.world.getBlockState(player_block.north()).getBlock() != Blocks.AIR && OffhandOld.mc.world.getBlockState(player_block.south()).getBlock() != Blocks.AIR;
    }
    
    private int get_item_slot(final Item input) {
        if (input == OffhandOld.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        for (int i = 36; i >= 0; --i) {
            final Item item = OffhandOld.mc.player.inventory.getStackInSlot(i).getItem();
            if (item == input) {
                if (i < 9) {
                    if (input == Items.GOLDEN_APPLE) {
                        return -1;
                    }
                    i += 36;
                }
                return i;
            }
        }
        return -1;
    }
}
