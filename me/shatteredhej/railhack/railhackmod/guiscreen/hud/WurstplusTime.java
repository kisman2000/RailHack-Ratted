//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.util.*;

public class WurstplusTime extends WurstplusPinnable
{
    public WurstplusTime() {
        super("Time", "Time", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        String line = "";
        line += ((TimeUtil.get_hour() < 10) ? ("0" + TimeUtil.get_hour()) : Integer.valueOf(TimeUtil.get_hour()));
        line += ":";
        line += ((TimeUtil.get_minuite() < 10) ? ("0" + TimeUtil.get_minuite()) : Integer.valueOf(TimeUtil.get_minuite()));
        line += ":";
        line += ((TimeUtil.get_second() < 10) ? ("0" + TimeUtil.get_second()) : Integer.valueOf(TimeUtil.get_second()));
        this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
}
