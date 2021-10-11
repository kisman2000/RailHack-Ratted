//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.render.*;
import net.minecraft.util.math.*;

public class WurstplusUser extends WurstplusPinnable
{
    private int scaled_width;
    private int scaled_height;
    private int scale_factor;
    
    public WurstplusUser() {
        super("User", "User", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        this.updateResolution();
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final int time = TimeUtil.get_hour();
        String line;
        if (time >= 0 && time < 12) {
            line = "Morning, " + ChatFormatting.GOLD + ChatFormatting.BOLD + this.mc.player.getName() + ChatFormatting.RESET + " you smell good today :)";
        }
        else if (time >= 12 && time < 16) {
            line = "Afternoon, " + ChatFormatting.GOLD + ChatFormatting.BOLD + this.mc.player.getName() + ChatFormatting.RESET + " you're looking good today :)";
        }
        else if (time >= 16 && time < 24) {
            line = "Evening, " + ChatFormatting.GOLD + ChatFormatting.BOLD + this.mc.player.getName() + ChatFormatting.RESET + " you smell good today :)";
        }
        else {
            line = "Welcome, " + ChatFormatting.GOLD + ChatFormatting.BOLD + this.mc.player.getName() + ChatFormatting.RESET + " you're looking fine today :)";
        }
        this.mc.fontRenderer.drawStringWithShadow(line, this.scaled_width / 2.0f - this.mc.fontRenderer.getStringWidth(line) / 2.0f, 20.0f, new WurstplusDraw.TravisColor(nl_r, nl_g, nl_b, nl_a).hex());
        this.set_width(this.get(line, "width") + 2);
        this.set_height(this.get(line, "height") + 2);
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
