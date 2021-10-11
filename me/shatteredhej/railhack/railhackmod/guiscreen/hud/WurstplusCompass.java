//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.render.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.damage.*;

public class WurstplusCompass extends WurstplusPinnable
{
    public WurstplusDraw font;
    private static final double half_pi = 1.5707963267948966;
    
    public WurstplusCompass() {
        super("Compass", "Compass", 1.0f, 0, 0);
        this.font = new WurstplusDraw(1.0f);
    }
    
    @Override
    public void render() {
        final int r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        for (final Direction dir : Direction.values()) {
            final double rad = this.get_pos_on_compass(dir);
            if (dir.name().equals("N")) {
                this.create_line(dir.name(), (int)(this.docking(1, dir.name()) + this.get_x(rad)), (int)this.get_y(rad), r, g, b, a);
            }
            else {
                this.create_line(dir.name(), (int)(this.docking(1, dir.name()) + this.get_x(rad)), (int)this.get_y(rad), 225, 225, 225, 225);
            }
        }
        this.set_width(50);
        this.set_height(50);
    }
    
    private double get_pos_on_compass(final Direction dir) {
        final double yaw = Math.toRadians(MathUtil.wrap(this.mc.getRenderViewEntity().rotationYaw));
        final int index = dir.ordinal();
        return yaw + index * 1.5707963267948966;
    }
    
    private double get_x(final double rad) {
        return Math.sin(rad) * RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDCompassScale").getValue(1);
    }
    
    private double get_y(final double rad) {
        final double epic_pitch = MathUtil.clamp2(this.mc.getRenderViewEntity().rotationPitch + 30.0f, -90.0f, 90.0f);
        final double pitch_radians = Math.toRadians(epic_pitch);
        return Math.cos(rad) * Math.sin(pitch_radians) * RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDCompassScale").getValue(1);
    }
    
    private enum Direction
    {
        N, 
        W, 
        S, 
        E;
    }
}
