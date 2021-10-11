//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.event.*;

public class WurstplusTPS extends WurstplusPinnable
{
    public WurstplusTPS() {
        super("TPS", "TPS", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final String line = "TPS: " + this.getTPS();
        this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
    
    public String getTPS() {
        try {
            final int tps = Math.round(HandleEvent.INSTANCE.get_tick_rate());
            if (tps >= 16) {
                return "§a" + Integer.toString(tps);
            }
            if (tps >= 10) {
                return "§3" + Integer.toString(tps);
            }
            return "§4" + Integer.toString(tps);
        }
        catch (Exception e) {
            return "oh no " + e;
        }
    }
}
