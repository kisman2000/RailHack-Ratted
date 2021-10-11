//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import java.awt.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;
import net.minecraft.util.math.*;

public class WurstplusHighlight extends Module
{
    Setting mode;
    Setting rgb;
    Setting r;
    Setting g;
    Setting b;
    Setting a;
    Setting l_a;
    int color_r;
    int color_g;
    int color_b;
    boolean outline;
    boolean solid;
    
    public WurstplusHighlight() {
        super(Category.Render);
        this.mode = this.register("Mode", "HighlightMode", "Pretty", this.combobox(new String[] { "Pretty", "Solid", "Outline" }));
        this.rgb = this.register("RGB Effect", "HighlightRGBEffect", true);
        this.r = this.register("R", "HighlightR", 255, 0, 255);
        this.g = this.register("G", "HighlightG", 255, 0, 255);
        this.b = this.register("B", "HighlightB", 255, 0, 255);
        this.a = this.register("A", "HighlightA", 100, 0, 255);
        this.l_a = this.register("Outline A", "HighlightLineA", 255, 0, 255);
        this.outline = false;
        this.solid = false;
        this.name = "BlockHighlight";
        this.tag = "BlockHighlight";
        this.description = "see what block ur targeting";
    }
    
    public void whenDisabled() {
        this.outline = false;
        this.solid = false;
    }
    
    public void render(final EventRender event) {
        if (WurstplusHighlight.mc.player != null && WurstplusHighlight.mc.world != null) {
            final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
            final int color_rgb = Color.HSBtoRGB(tick_color[0], 1.0f, 1.0f);
            if (this.rgb.getValue(true)) {
                this.color_r = (color_rgb >> 16 & 0xFF);
                this.color_g = (color_rgb >> 8 & 0xFF);
                this.color_b = (color_rgb & 0xFF);
                this.r.setValue(this.color_r);
                this.g.setValue(this.color_g);
                this.b.setValue(this.color_b);
            }
            else {
                this.color_r = this.r.getValue(1);
                this.color_g = this.g.getValue(2);
                this.color_b = this.b.getValue(3);
            }
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
            final RayTraceResult result = WurstplusHighlight.mc.objectMouseOver;
            if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
                final BlockPos block_pos = result.getBlockPos();
                if (this.solid) {
                    RenderHelp.prepare("quads");
                    RenderHelp.draw_cube(block_pos, this.color_r, this.color_g, this.color_b, this.a.getValue(1), "all");
                    RenderHelp.release();
                }
                if (this.outline) {
                    RenderHelp.prepare("lines");
                    RenderHelp.draw_cube_line(block_pos, this.color_r, this.color_g, this.color_b, this.l_a.getValue(1), "all");
                    RenderHelp.release();
                }
            }
        }
    }
}
