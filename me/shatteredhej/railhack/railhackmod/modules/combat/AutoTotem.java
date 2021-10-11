//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class AutoTotem extends Module
{
    Setting delay;
    private boolean switching;
    private int last_slot;
    
    public AutoTotem() {
        super(Category.Combat);
        this.delay = this.register("Delay", "TotemDelay", false);
        this.switching = false;
        this.name = "AutoTotem";
        this.tag = "AutoTotem";
        this.description = "put totem in offhand";
    }
    
    public void onUpdate() {
        if (AutoTotem.mc.currentScreen == null || AutoTotem.mc.currentScreen instanceof GuiInventory) {
            if (this.switching) {
                this.swap_items(this.last_slot, 2);
                return;
            }
            if (AutoTotem.mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
                this.swap_items(this.get_item_slot(), this.delay.getValue(true) ? 1 : 0);
            }
        }
    }
    
    private int get_item_slot() {
        if (Items.TOTEM_OF_UNDYING == AutoTotem.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        int i = 36;
        while (i >= 0) {
            final Item item = AutoTotem.mc.player.inventory.getStackInSlot(i).getItem();
            if (item == Items.TOTEM_OF_UNDYING) {
                if (i < 9) {
                    return -1;
                }
                return i;
            }
            else {
                --i;
            }
        }
        return -1;
    }
    
    public void swap_items(final int slot, final int step) {
        if (slot == -1) {
            return;
        }
        if (step == 0) {
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
        }
        if (step == 1) {
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            this.switching = true;
            this.last_slot = slot;
        }
        if (step == 2) {
            AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            this.switching = false;
        }
        AutoTotem.mc.playerController.updateController();
    }
}
