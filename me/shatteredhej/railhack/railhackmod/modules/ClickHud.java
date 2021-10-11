//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.*;
import net.minecraft.client.gui.*;
import java.awt.*;

public class ClickHud extends Module
{
    Setting frame_view;
    Setting strings_r;
    Setting strings_g;
    Setting strings_b;
    Setting strings_a;
    Setting rainbow;
    Setting rainbowSat;
    Setting rainbowBrightness;
    Setting rainbowSpeed;
    Setting compass_scale;
    Setting arraylist_mode;
    Setting show_all_pots;
    Setting max_player_list;
    
    public ClickHud() {
        super(Category.Gui);
        this.frame_view = this.register("info", "HUDStringsList", "Strings");
        this.strings_r = this.register("Color R", "HUDStringsColorR", 255, 0, 255);
        this.strings_g = this.register("Color G", "HUDStringsColorG", 255, 0, 255);
        this.strings_b = this.register("Color B", "HUDStringsColorB", 255, 0, 255);
        this.strings_a = this.register("Alpha", "HUDStringsColorA", 230, 0, 255);
        this.rainbow = this.register("Rainbow", "HudRainbow", false);
        this.rainbowSat = this.register("Sat", "", 0.0, 0.0, 1.0);
        this.rainbowBrightness = this.register("Brightness", "HudBrightness", 0.0, 0.0, 1.0);
        this.rainbowSpeed = this.register("Rgb Speed", "HudRgbSpeed", 0, 0, 256);
        this.compass_scale = this.register("Compass Scale", "HUDCompassScale", 16, 1, 60);
        this.arraylist_mode = this.register("ArrayList", "HUDArrayList", "Free", this.combobox(new String[] { "Free", "Top R", "Top L", "Bottom R", "Bottom L" }));
        this.show_all_pots = this.register("All Potions", "HUDAllPotions", false);
        this.max_player_list = this.register("Max Players", "HUDMaxPlayers", 24, 1, 64);
        this.name = "HudEditor";
        this.tag = "HUD";
        this.description = "gui for pinnables";
    }
    
    public void onEnable() {
        if (ClickHud.mc.world != null && ClickHud.mc.player != null) {
            RailHack.get_hack_manager().getModuleWithTag("GUI").setActive(false);
            RailHack.clickHud.back = false;
            ClickHud.mc.displayGuiScreen((GuiScreen)RailHack.clickHud);
        }
    }
    
    public void whenDisabled() {
        if (this.rainbow.getValue(true)) {
            this.cycle_rainbow();
        }
    }
    
    public void cycle_rainbow() {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / (360.0f * this.rainbowSpeed.getValue(1)) };
        final int color_rgb_o = Color.HSBtoRGB(tick_color[0], (float)this.rainbowSat.getValue(1), (float)this.rainbowBrightness.getValue(1));
        this.strings_r.setValue(color_rgb_o >> 16 & 0xFF);
        this.strings_g.setValue(color_rgb_o >> 8 & 0xFF);
        this.strings_b.setValue(color_rgb_o & 0xFF);
    }
}
