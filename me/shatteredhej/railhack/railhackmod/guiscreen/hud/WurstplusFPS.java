//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;
import net.minecraft.client.*;

public class WurstplusFPS extends WurstplusPinnable
{
    public WurstplusFPS() {
        super("Fps", "Fps", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final String line = "FPS: " + this.get_fps();
        this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
    }
    
    public String get_fps() {
        final Minecraft mc = this.mc;
        final int fps = Minecraft.getDebugFPS();
        if (fps >= 60) {
            return "§a" + Integer.toString(fps);
        }
        if (fps >= 30) {
            return "§3" + Integer.toString(fps);
        }
        return "§4" + Integer.toString(fps);
    }
}
