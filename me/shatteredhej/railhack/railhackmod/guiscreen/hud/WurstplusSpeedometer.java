//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;
import java.text.*;
import net.minecraft.util.math.*;

public class WurstplusSpeedometer extends WurstplusPinnable
{
    public WurstplusSpeedometer() {
        super("Speedometer", "Speedometer", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final double x = this.mc.player.posX - this.mc.player.prevPosX;
        final double z = this.mc.player.posZ - this.mc.player.prevPosZ;
        final float tr = this.mc.timer.tickLength / 1000.0f;
        final String bps = "M/s: " + new DecimalFormat("#.#").format(MathHelper.sqrt(x * x + z * z) / tr);
        this.create_line(bps, this.docking(1, bps), 2, nl_r, nl_g, nl_b, nl_a);
        this.set_width(this.get(bps, "width") + 2);
        this.set_height(this.get(bps, "height") + 2);
    }
}
