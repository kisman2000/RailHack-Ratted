//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import net.minecraft.item.*;
import java.util.*;

public class WurstplusArmorDurabilityWarner extends WurstplusPinnable
{
    public WurstplusArmorDurabilityWarner() {
        super("Armor Warner", "ArmorWarner", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final String line = "ur armor is kinda low rn :/";
        if (this.is_damaged()) {
            this.create_line(line, this.docking(1, line), 2, 255, 20, 20, 255);
        }
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
    
    private boolean is_damaged() {
        for (final Map.Entry<Integer, ItemStack> armor_slot : this.get_armor().entrySet()) {
            if (armor_slot.getValue().isEmpty()) {
                continue;
            }
            final ItemStack stack = armor_slot.getValue();
            final double max_dam = stack.getMaxDamage();
            final double dam_left = stack.getMaxDamage() - stack.getItemDamage();
            final double percent = dam_left / max_dam * 100.0;
            if (percent < 30.0) {
                return true;
            }
        }
        return false;
    }
    
    private Map<Integer, ItemStack> get_armor() {
        return this.get_inv_slots(5, 8);
    }
    
    private Map<Integer, ItemStack> get_inv_slots(int current, final int last) {
        final Map<Integer, ItemStack> full_inv_slots = new HashMap<Integer, ItemStack>();
        while (current <= last) {
            full_inv_slots.put(current, (ItemStack)this.mc.player.inventoryContainer.getInventory().get(current));
            ++current;
        }
        return full_inv_slots;
    }
}
