//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.*;

public class WurstplusCoordinates extends WurstplusPinnable
{
    ChatFormatting dg;
    ChatFormatting db;
    ChatFormatting dr;
    
    public WurstplusCoordinates() {
        super("Coordinates", "Coordinates", 1.0f, 0, 0);
        this.dg = ChatFormatting.DARK_GRAY;
        this.db = ChatFormatting.DARK_BLUE;
        this.dr = ChatFormatting.DARK_RED;
    }
    
    @Override
    public void render() {
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final String x = RailHack.g + "[" + RailHack.r + Integer.toString((int)this.mc.player.posX) + RailHack.g + "]" + RailHack.r;
        final String y = RailHack.g + "[" + RailHack.r + Integer.toString((int)this.mc.player.posY) + RailHack.g + "]" + RailHack.r;
        final String z = RailHack.g + "[" + RailHack.r + Integer.toString((int)this.mc.player.posZ) + RailHack.g + "]" + RailHack.r;
        final String x_nether = RailHack.g + "[" + RailHack.r + Long.toString(Math.round((this.mc.player.dimension != -1) ? (this.mc.player.posX / 8.0) : (this.mc.player.posX * 8.0))) + RailHack.g + "]" + RailHack.r;
        final String z_nether = RailHack.g + "[" + RailHack.r + Long.toString(Math.round((this.mc.player.dimension != -1) ? (this.mc.player.posZ / 8.0) : (this.mc.player.posZ * 8.0))) + RailHack.g + "]" + RailHack.r;
        final String line = "XYZ " + x + y + z + " XZ " + x_nether + z_nether;
        this.create_line(line, this.docking(1, line), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(line, "width"));
        this.set_height(this.get(line, "height") + 2);
    }
}
