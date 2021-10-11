//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.module.*;
import java.util.stream.*;
import com.google.common.collect.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.render.*;
import java.util.*;
import net.minecraft.util.math.*;

public class WurstplusArrayList extends WurstplusPinnable
{
    boolean flag;
    private int scaled_width;
    private int scaled_height;
    private int scale_factor;
    
    public WurstplusArrayList() {
        super("Array List", "ArrayList", 1.0f, 0, 0);
        this.flag = true;
    }
    
    @Override
    public void render() {
        this.updateResolution();
        int position_update_y = 2;
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        String string;
        List<Module> pretty_modules = RailHack.get_hack_manager().get_array_active_hacks().stream().sorted(Comparator.comparing(modules -> {
            if (modules.array_detail() == null) {
                string = modules.get_tag();
            }
            else {
                string = modules.get_tag() + RailHack.g + " [" + RailHack.r + modules.array_detail() + RailHack.g + "]" + RailHack.r;
            }
            return Integer.valueOf(this.get(string, "width"));
        })).collect((Collector<? super Object, ?, List<Module>>)Collectors.toList());
        int count = 0;
        if (RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDArrayList").in("Top R") || RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDArrayList").in("Top L")) {
            pretty_modules = (List<Module>)Lists.reverse((List)pretty_modules);
        }
        for (final Module modules2 : pretty_modules) {
            this.flag = true;
            if (modules2.get_category().get_tag().equals("Gui")) {
                continue;
            }
            for (final String s : DrawnUtil.hidden_tags) {
                if (modules2.get_tag().equalsIgnoreCase(s)) {
                    this.flag = false;
                    break;
                }
                if (!this.flag) {
                    break;
                }
            }
            if (!this.flag) {
                continue;
            }
            final String module_name = (modules2.array_detail() == null) ? modules2.get_tag() : (modules2.get_tag() + RailHack.g + " [" + RailHack.r + modules2.array_detail() + RailHack.g + "]" + RailHack.r);
            if (RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDArrayList").in("Free")) {
                this.create_line(module_name, this.docking(2, module_name), position_update_y, nl_r, nl_g, nl_b, nl_a);
                position_update_y += this.get(module_name, "height") + 1;
                if (this.get(module_name, "width") > this.get_width()) {
                    this.set_width(this.get(module_name, "width") + 1);
                }
                this.set_height(position_update_y);
            }
            else {
                if (RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDArrayList").in("Top R")) {
                    this.mc.fontRenderer.drawStringWithShadow(module_name, (float)(this.scaled_width - 2 - this.mc.fontRenderer.getStringWidth(module_name)), (float)(3 + count * 10), new WurstplusDraw.TravisColor(nl_r, nl_g, nl_b, nl_a).hex());
                    ++count;
                }
                if (RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDArrayList").in("Top L")) {
                    this.mc.fontRenderer.drawStringWithShadow(module_name, 2.0f, (float)(3 + count * 10), new WurstplusDraw.TravisColor(nl_r, nl_g, nl_b, nl_a).hex());
                    ++count;
                }
                if (RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDArrayList").in("Bottom R")) {
                    this.mc.fontRenderer.drawStringWithShadow(module_name, (float)(this.scaled_width - 2 - this.mc.fontRenderer.getStringWidth(module_name)), (float)(this.scaled_height - count * 10), new WurstplusDraw.TravisColor(nl_r, nl_g, nl_b, nl_a).hex());
                    ++count;
                }
                if (!RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDArrayList").in("Bottom L")) {
                    continue;
                }
                this.mc.fontRenderer.drawStringWithShadow(module_name, 2.0f, (float)(this.scaled_height - count * 10), new WurstplusDraw.TravisColor(nl_r, nl_g, nl_b, nl_a).hex());
                ++count;
            }
        }
    }
    
    public void updateResolution() {
        this.scaled_width = this.mc.displayWidth;
        this.scaled_height = this.mc.displayHeight;
        this.scale_factor = 1;
        final boolean flag = this.mc.isUnicode();
        int i = this.mc.gameSettings.guiScale;
        if (i == 0) {
            i = 1000;
        }
        while (this.scale_factor < i && this.scaled_width / (this.scale_factor + 1) >= 320 && this.scaled_height / (this.scale_factor + 1) >= 240) {
            ++this.scale_factor;
        }
        if (flag && this.scale_factor % 2 != 0 && this.scale_factor != 1) {
            --this.scale_factor;
        }
        final double scaledWidthD = this.scaled_width / (double)this.scale_factor;
        final double scaledHeightD = this.scaled_height / (double)this.scale_factor;
        this.scaled_width = MathHelper.ceil(scaledWidthD);
        this.scaled_height = MathHelper.ceil(scaledHeightD);
    }
}
