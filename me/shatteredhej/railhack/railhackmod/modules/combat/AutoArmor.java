//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.init.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;

public class AutoArmor extends Module
{
    Setting delay;
    Setting smart_mode;
    Setting put_back;
    Setting player_range;
    Setting crystal_range;
    Setting boot_percent;
    Setting chest_percent;
    private int delay_count;
    
    public AutoArmor() {
        super(Category.Combat);
        this.delay = this.register("Delay", "AADelay", 2, 0, 5);
        this.smart_mode = this.register("Smart Mode", "AASmartMode", true);
        this.put_back = this.register("Equip Armour", "AAEquipArmour", true);
        this.player_range = this.register("Player Range", "AAPlayerRange", 8, 0, 20);
        this.crystal_range = this.register("Crystal Range", "AACrystalRange", 13, 0, 20);
        this.boot_percent = this.register("Boot Percent", "AATBootPercent", 80, 0, 100);
        this.chest_percent = this.register("Chest Percent", "AATChestPercent", 80, 0, 100);
        this.name = "AutoArmor";
        this.tag = "AutoArmor";
        this.description = "WATCH UR BOOTS";
    }
    
    protected void onEnable() {
        this.delay_count = 0;
    }
    
    public void onUpdate() {
        if (AutoArmor.mc.player.ticksExisted % 2 == 0) {
            return;
        }
        boolean flag = false;
        if (this.delay_count < this.delay.getValue(0)) {
            ++this.delay_count;
            return;
        }
        this.delay_count = 0;
        if (this.smart_mode.getValue(true) && !this.is_crystal_in_range(this.crystal_range.getValue(1)) && !this.is_player_in_range(this.player_range.getValue(1))) {
            flag = true;
        }
        if (flag) {
            if (AutoArmor.mc.gameSettings.keyBindUseItem.isKeyDown() && AutoArmor.mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE) {
                this.take_off();
            }
            return;
        }
        if (!this.put_back.getValue(true)) {
            return;
        }
        if (AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof InventoryEffectRenderer)) {
            return;
        }
        final int[] bestArmorSlots = new int[4];
        final int[] bestArmorValues = new int[4];
        for (int armorType = 0; armorType < 4; ++armorType) {
            final ItemStack oldArmor = AutoArmor.mc.player.inventory.armorItemInSlot(armorType);
            if (oldArmor.getItem() instanceof ItemArmor) {
                bestArmorValues[armorType] = ((ItemArmor)oldArmor.getItem()).damageReduceAmount;
            }
            bestArmorSlots[armorType] = -1;
        }
        for (int slot = 0; slot < 36; ++slot) {
            final ItemStack stack = AutoArmor.mc.player.inventory.getStackInSlot(slot);
            if (stack.getCount() <= 1) {
                if (stack.getItem() instanceof ItemArmor) {
                    final ItemArmor armor = (ItemArmor)stack.getItem();
                    final int armorType2 = armor.armorType.ordinal() - 2;
                    if (armorType2 != 2 || !AutoArmor.mc.player.inventory.armorItemInSlot(armorType2).getItem().equals(Items.ELYTRA)) {
                        final int armorValue = armor.damageReduceAmount;
                        if (armorValue > bestArmorValues[armorType2]) {
                            bestArmorSlots[armorType2] = slot;
                            bestArmorValues[armorType2] = armorValue;
                        }
                    }
                }
            }
        }
        for (int armorType = 0; armorType < 4; ++armorType) {
            int slot2 = bestArmorSlots[armorType];
            if (slot2 != -1) {
                final ItemStack oldArmor2 = AutoArmor.mc.player.inventory.armorItemInSlot(armorType);
                if (oldArmor2 != ItemStack.EMPTY || AutoArmor.mc.player.inventory.getFirstEmptyStack() != -1) {
                    if (slot2 < 9) {
                        slot2 += 36;
                    }
                    AutoArmor.mc.playerController.windowClick(0, 8 - armorType, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
                    AutoArmor.mc.playerController.windowClick(0, slot2, 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
                    break;
                }
            }
        }
    }
    
    public boolean is_player_in_range(final int range) {
        for (final Entity player : (List)AutoArmor.mc.world.playerEntities.stream().filter(entityPlayer -> !FriendUtil.isFriend(entityPlayer.getName())).collect(Collectors.toList())) {
            if (player == AutoArmor.mc.player) {
                continue;
            }
            if (AutoArmor.mc.player.getDistance(player) < range) {
                return true;
            }
        }
        return false;
    }
    
    public boolean is_crystal_in_range(final int range) {
        for (final Entity c : (List)AutoArmor.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).collect(Collectors.toList())) {
            if (AutoArmor.mc.player.getDistance(c) < range) {
                return true;
            }
        }
        return false;
    }
    
    public void take_off() {
        if (!this.is_space()) {
            return;
        }
        for (final Map.Entry<Integer, ItemStack> armorSlot : get_armour().entrySet()) {
            final ItemStack stack = armorSlot.getValue();
            if (this.is_healed(stack)) {
                AutoArmor.mc.playerController.windowClick(0, (int)armorSlot.getKey(), 0, ClickType.QUICK_MOVE, (EntityPlayer)AutoArmor.mc.player);
            }
        }
    }
    
    public boolean is_space() {
        for (final Map.Entry<Integer, ItemStack> invSlot : get_inv().entrySet()) {
            final ItemStack stack = invSlot.getValue();
            if (stack.getItem() == Items.AIR) {
                return true;
            }
        }
        return false;
    }
    
    private static Map<Integer, ItemStack> get_inv() {
        return get_inv_slots(9, 44);
    }
    
    private static Map<Integer, ItemStack> get_armour() {
        return get_inv_slots(5, 8);
    }
    
    private static Map<Integer, ItemStack> get_inv_slots(int current, final int last) {
        final Map<Integer, ItemStack> fullInventorySlots = new HashMap<Integer, ItemStack>();
        while (current <= last) {
            fullInventorySlots.put(current, (ItemStack)AutoArmor.mc.player.inventoryContainer.getInventory().get(current));
            ++current;
        }
        return fullInventorySlots;
    }
    
    public boolean is_healed(final ItemStack item) {
        if (item.getItem() == Items.DIAMOND_BOOTS || item.getItem() == Items.DIAMOND_HELMET) {
            final double max_dam = item.getMaxDamage();
            final double dam_left = item.getMaxDamage() - item.getItemDamage();
            final double percent = dam_left / max_dam * 100.0;
            return percent >= this.boot_percent.getValue(1);
        }
        final double max_dam = item.getMaxDamage();
        final double dam_left = item.getMaxDamage() - item.getItemDamage();
        final double percent = dam_left / max_dam * 100.0;
        return percent >= this.chest_percent.getValue(1);
    }
}
