//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import java.util.function.*;

public class WurstplusPvpHud extends WurstplusPinnable
{
    public WurstplusPvpHud() {
        super("PVP Hud", "pvphud", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final String totem = "Totems: " + this.get_totems();
        final String trap = "Trap: " + this.trap_enabled();
        final String aura = "Aura: " + this.aura_enabled();
        final String surround = "Surround: " + this.surround_enabled();
        final String holefill = "Holefill: " + this.holefill_enabled();
        final String socks = "Socks: " + this.socks_enabled();
        final String selftrap = "Self Trap: " + this.selftrap_enabled();
        this.create_line(totem, this.docking(1, totem), 2, nl_r, nl_g, nl_b, nl_a);
        this.create_line(aura, this.docking(1, aura), 13, nl_r, nl_g, nl_b, nl_a);
        this.create_line(trap, this.docking(1, trap), 24, nl_r, nl_g, nl_b, nl_a);
        this.create_line(surround, this.docking(1, surround), 34, nl_r, nl_g, nl_b, nl_a);
        this.create_line(holefill, this.docking(1, holefill), 45, nl_r, nl_g, nl_b, nl_a);
        this.create_line(socks, this.docking(1, socks), 56, nl_r, nl_g, nl_b, nl_a);
        this.create_line(selftrap, this.docking(1, selftrap), 67, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(surround, "width") + 2);
        this.set_height(this.get(surround, "height") * 5);
    }
    
    public String selftrap_enabled() {
        try {
            if (RailHack.get_hack_manager().getModuleWithTag("SelfTrap").isActive()) {
                return "§a 1";
            }
            return "§4 0";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String trap_enabled() {
        try {
            if (RailHack.get_hack_manager().getModuleWithTag("Trap").isActive()) {
                return "§a 1";
            }
            return "§4 0";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String aura_enabled() {
        try {
            if (RailHack.get_hack_manager().getModuleWithTag("AutoCrystal").isActive()) {
                return "§a 1";
            }
            return "§4 0";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String socks_enabled() {
        try {
            if (RailHack.get_hack_manager().getModuleWithTag("Socks").isActive()) {
                return "§a 1";
            }
            return "§4 0";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String surround_enabled() {
        try {
            if (RailHack.get_hack_manager().getModuleWithTag("Surround").isActive()) {
                return "§a 1";
            }
            return "§4 0";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String holefill_enabled() {
        try {
            if (RailHack.get_hack_manager().getModuleWithTag("HoleFill").isActive()) {
                return "§a 1";
            }
            return "§4 0";
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public String get_totems() {
        try {
            final int totems = this.offhand() + this.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
            if (totems > 1) {
                return "§a " + totems;
            }
            return "§4 " + totems;
        }
        catch (Exception e) {
            return "0";
        }
    }
    
    public int offhand() {
        if (this.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            return 1;
        }
        return 0;
    }
}
