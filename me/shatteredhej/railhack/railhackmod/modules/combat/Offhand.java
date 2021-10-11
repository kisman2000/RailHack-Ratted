//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class Offhand extends Module
{
    Setting mode;
    Setting totemHP;
    Setting disable;
    Setting disableHP;
    Setting swordGap;
    Setting steps;
    Setting desyncCheck;
    private boolean switching;
    private int last_slot;
    
    public Offhand() {
        super(Category.Combat);
        this.mode = this.register("Mode: ", "Mode: ", "Crystal", this.combobox(new String[] { "Crystal", "Gapple", "Totem" }));
        this.totemHP = this.register("TotemHealth", "TotemHealth", 16, 1, 20);
        this.disable = this.register("Disable", "Disable", false);
        this.disableHP = this.register("DisableHealth", "DisableHealth", 16, 1, 20);
        this.swordGap = this.register("SwordGap", "SwordGap", true);
        this.steps = this.register("Steps", "Steps", 1, 1, 3);
        this.desyncCheck = this.register("DesyncCheck", "DesyncCheck", false);
        this.switching = false;
        this.last_slot = -1;
        this.name = "Offhand";
        this.tag = "Offhand";
    }
    
    public void onUpdate() {
        if (Offhand.mc.player == null || Offhand.mc.world == null) {
            return;
        }
        if (Offhand.mc.currentScreen == null || Offhand.mc.currentScreen instanceof GuiInventory) {
            if (this.switching) {
                this.swapItems(this.last_slot, 2);
                return;
            }
            final float hp = Offhand.mc.player.getHealth() + Offhand.mc.player.getAbsorptionAmount();
            final int step = this.steps.getValue(1) - 1;
            if (hp > this.totemHP.getValue(1)) {
                if (this.shouldSwordGap()) {
                    if (this.needsSwitch(Items.GOLDEN_APPLE)) {
                        this.swapItems(this.getItemSlot(Items.GOLDEN_APPLE), step);
                    }
                }
                else if (this.mode.in("Crystal") && this.needsSwitch(Items.END_CRYSTAL)) {
                    this.swapItems(this.getItemSlot(Items.END_CRYSTAL), step);
                }
                else if (this.mode.in("Gapple") && this.needsSwitch(Items.GOLDEN_APPLE)) {
                    this.swapItems(this.getItemSlot(Items.GOLDEN_APPLE), step);
                }
                else if (this.mode.in("Totem") && this.needsSwitch(Items.TOTEM_OF_UNDYING)) {
                    this.swapItems(this.getItemSlot(Items.TOTEM_OF_UNDYING), step);
                }
            }
            else if (this.needsSwitch(Items.TOTEM_OF_UNDYING)) {
                this.swapItems(this.getItemSlot(Items.TOTEM_OF_UNDYING), step);
            }
            if (hp <= this.disableHP.getValue(1) && this.disable.getValue(true)) {
                MessageUtil.send_client_message("Disabling due to offhand requirement...");
                this.swapItems(this.getItemSlot(Items.TOTEM_OF_UNDYING), step);
                this.setDisable();
                MessageUtil.toggle_message(this);
            }
            if (this.desyncCheck.getValue(true)) {
                final Item mainhandItem = Offhand.mc.player.getHeldItemMainhand().getItem();
                final Item offhandItem = Offhand.mc.player.getHeldItemOffhand().getItem();
                MessageUtil.send_client_message(mainhandItem + "is in your mainhand, and " + offhandItem + "is in your offhand.");
                this.desyncCheck.setValue(false);
            }
        }
    }
    
    private void swapItems(final int slot, final int step) {
        if (slot < 0) {
            return;
        }
        if (step == 0) {
            Offhand.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
        }
        else if (step == 1) {
            Offhand.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            this.switching = true;
            this.last_slot = slot;
        }
        else if (step == 2) {
            Offhand.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            Offhand.mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, (EntityPlayer)Offhand.mc.player);
            this.switching = false;
            this.last_slot = -1;
        }
        Offhand.mc.playerController.updateController();
    }
    
    private int getItemSlot(final Item input) {
        if (input == Offhand.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        for (int i = 36; i >= 0; --i) {
            final Item item = Offhand.mc.player.inventory.getStackInSlot(i).getItem();
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
    
    private boolean needsSwitch(final Item item) {
        return Offhand.mc.player.getHeldItemOffhand().getItem() != item;
    }
    
    private boolean shouldSwordGap() {
        return this.swordGap.getValue(true) && Offhand.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD && Offhand.mc.gameSettings.keyBindUseItem.isKeyDown();
    }
}
