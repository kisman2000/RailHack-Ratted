//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;

public class WurstplusPing extends WurstplusPinnable
{
    public WurstplusPing() {
        super("Ping", "Ping", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final String line = "Ping: " + this.get_ping();
        this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
    
    public String get_ping() {
        try {
            final int ping = this.mc.getConnection().getPlayerInfo(this.mc.player.getUniqueID()).getResponseTime();
            if (ping <= 50) {
                return "§a" + Integer.toString(ping);
            }
            if (ping <= 150) {
                return "§3" + Integer.toString(ping);
            }
            return "§4" + Integer.toString(ping);
        }
        catch (Exception e) {
            return "oh no";
        }
    }
}
