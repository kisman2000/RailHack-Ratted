//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.misc;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import java.util.*;

public class HotbarRestock extends Module
{
    Setting mode;
    Setting threshold;
    Setting tickdelay;
    private int delay_step;
    
    public HotbarRestock() {
        super(Category.Misc);
        this.mode = this.register("Mode", "AutoReplenishMode", "All", this.combobox(new String[] { "All", "Crystals", "Xp", "Both" }));
        this.threshold = this.register("Threshold", "AutoReplenishThreshold", 32, 1, 63);
        this.tickdelay = this.register("Delay", "AutoReplenishDelay", 2, 1, 10);
        this.delay_step = 0;
        this.name = "HotbarReplenish";
        this.tag = "HotbarReplenish";
        this.description = "chad this doesnt desync you i swear";
    }
    
    public void onUpdate() {
        if (HotbarRestock.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        if (this.delay_step < this.tickdelay.getValue(1)) {
            ++this.delay_step;
            return;
        }
        this.delay_step = 0;
        final WurstplusPair<Integer, Integer> slots = this.findReplenishableHotbarSlot();
        if (slots == null) {
            return;
        }
        final int inventorySlot = slots.getKey();
        final int hotbarSlot = slots.getValue();
        HotbarRestock.mc.playerController.windowClick(0, inventorySlot, 0, ClickType.PICKUP, (EntityPlayer)HotbarRestock.mc.player);
        HotbarRestock.mc.playerController.windowClick(0, hotbarSlot, 0, ClickType.PICKUP, (EntityPlayer)HotbarRestock.mc.player);
        HotbarRestock.mc.playerController.windowClick(0, inventorySlot, 0, ClickType.PICKUP, (EntityPlayer)HotbarRestock.mc.player);
        HotbarRestock.mc.playerController.updateController();
    }
    
    private WurstplusPair<Integer, Integer> findReplenishableHotbarSlot() {
        WurstplusPair<Integer, Integer> returnPair = null;
        for (final Map.Entry<Integer, ItemStack> hotbarSlot : this.get_hotbar().entrySet()) {
            final ItemStack stack = hotbarSlot.getValue();
            if (!stack.isEmpty) {
                if (stack.getItem() == Items.AIR) {
                    continue;
                }
                if (!stack.isStackable()) {
                    continue;
                }
                if (stack.stackSize >= stack.getMaxStackSize()) {
                    continue;
                }
                if (stack.stackSize > this.threshold.getValue(1)) {
                    continue;
                }
                final int inventorySlot = this.findCompatibleInventorySlot(stack);
                if (inventorySlot == -1) {
                    continue;
                }
                returnPair = new WurstplusPair<Integer, Integer>(inventorySlot, hotbarSlot.getKey());
            }
        }
        return returnPair;
    }
    
    private int findCompatibleInventorySlot(final ItemStack hotbarStack) {
        int inventorySlot = -1;
        int smallestStackSize = 999;
        for (final Map.Entry<Integer, ItemStack> entry : this.get_inventory().entrySet()) {
            final ItemStack inventoryStack = entry.getValue();
            if (!inventoryStack.isEmpty) {
                if (inventoryStack.getItem() == Items.AIR) {
                    continue;
                }
                if (!this.isCompatibleStacks(hotbarStack, inventoryStack)) {
                    continue;
                }
                final int currentStackSize = ((ItemStack)HotbarRestock.mc.player.inventoryContainer.getInventory().get((int)entry.getKey())).stackSize;
                if (smallestStackSize <= currentStackSize) {
                    continue;
                }
                smallestStackSize = currentStackSize;
                inventorySlot = entry.getKey();
            }
        }
        return inventorySlot;
    }
    
    private boolean isCompatibleStacks(final ItemStack stack1, final ItemStack stack2) {
        if (!stack1.getItem().equals(stack2.getItem())) {
            return false;
        }
        if (stack1.getItem() instanceof ItemBlock && stack2.getItem() instanceof ItemBlock) {
            final Block block1 = ((ItemBlock)stack1.getItem()).getBlock();
            final Block block2 = ((ItemBlock)stack2.getItem()).getBlock();
            if (!block1.material.equals(block2.material)) {
                return false;
            }
        }
        return stack1.getDisplayName().equals(stack2.getDisplayName()) && stack1.getItemDamage() == stack2.getItemDamage();
    }
    
    private Map<Integer, ItemStack> get_inventory() {
        return this.get_inv_slots(9, 35);
    }
    
    private Map<Integer, ItemStack> get_hotbar() {
        return this.get_inv_slots(36, 44);
    }
    
    private Map<Integer, ItemStack> get_inv_slots(int current, final int last) {
        final Map<Integer, ItemStack> fullInventorySlots = new HashMap<Integer, ItemStack>();
        while (current <= last) {
            fullInventorySlots.put(current, (ItemStack)HotbarRestock.mc.player.inventoryContainer.getInventory().get(current));
            ++current;
        }
        return fullInventorySlots;
    }
}
