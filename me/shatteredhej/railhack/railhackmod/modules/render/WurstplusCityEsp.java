//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;

public class WurstplusCityEsp extends Module
{
    Setting endcrystal_mode;
    Setting mode;
    Setting off_set;
    Setting range;
    Setting r;
    Setting g;
    Setting b;
    Setting a;
    List<BlockPos> blocks;
    boolean outline;
    boolean solid;
    
    public WurstplusCityEsp() {
        super(Category.Render);
        this.endcrystal_mode = this.register("EndCrystal", "CityEndCrystal", false);
        this.mode = this.register("Mode", "CityMode", "Pretty", this.combobox(new String[] { "Pretty", "Solid", "Outline" }));
        this.off_set = this.register("Height", "CityOffSetSide", 0.2, 0.0, 1.0);
        this.range = this.register("Range", "CityRange", 6, 1, 12);
        this.r = this.register("R", "CityR", 0, 0, 255);
        this.g = this.register("G", "CityG", 255, 0, 255);
        this.b = this.register("B", "CityB", 0, 0, 255);
        this.a = this.register("A", "CityA", 50, 0, 255);
        this.blocks = new ArrayList<BlockPos>();
        this.outline = false;
        this.solid = false;
        this.name = "CityESP";
        this.tag = "CityESP";
        this.description = "jumpy isnt gonna be happy about this";
    }
    
    public void onUpdate() {
        this.blocks.clear();
        for (final EntityPlayer player : WurstplusCityEsp.mc.world.playerEntities) {
            if (WurstplusCityEsp.mc.player.getDistance((Entity)player) <= this.range.getValue(1)) {
                if (WurstplusCityEsp.mc.player == player) {
                    continue;
                }
                final BlockPos p = EntityUtil.is_cityable(player, this.endcrystal_mode.getValue(true));
                if (p == null) {
                    continue;
                }
                this.blocks.add(p);
            }
        }
    }
    
    public void render(final EventRender event) {
        final float off_set_h = (float)this.off_set.getValue(1.0);
        for (final BlockPos pos : this.blocks) {
            if (this.mode.in("Pretty")) {
                this.outline = true;
                this.solid = true;
            }
            if (this.mode.in("Solid")) {
                this.outline = false;
                this.solid = true;
            }
            if (this.mode.in("Outline")) {
                this.outline = true;
                this.solid = false;
            }
            if (this.solid) {
                RenderHelp.prepare("quads");
                RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)pos.getX(), (float)pos.getY(), (float)pos.getZ(), 1.0f, off_set_h, 1.0f, this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1), "all");
                RenderHelp.release();
            }
            if (this.outline) {
                RenderHelp.prepare("lines");
                RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)pos.getX(), (float)pos.getY(), (float)pos.getZ(), 1.0f, off_set_h, 1.0f, this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1), "all");
                RenderHelp.release();
            }
        }
    }
}
