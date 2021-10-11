//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.render;

import net.minecraft.client.gui.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.helper.task.*;
import me.shatteredhej.railhack.railhackmod.helper.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;
import net.minecraft.client.*;
import java.awt.*;

public class WurstplusDraw
{
    private static final FontRenderer font_renderer;
    private static final FontRenderer custom_font;
    private final float size;
    
    public WurstplusDraw(final float size) {
        this.size = size;
    }
    
    public static void draw_rect(final int x, final int y, final int w, final int h, final int r, final int g, final int b, final int a) {
        Gui.drawRect(x, y, w, h, new TravisColor(r, g, b, a).hex());
    }
    
    public static void draw_rect(final int x, final int y, final int w, final int h, final int r, final int g, final int b, final int a, final int size, final String type) {
        if (Arrays.asList(type.split("-")).contains("up")) {
            draw_rect(x, y, x + w, y + size, r, g, b, a);
        }
        if (Arrays.asList(type.split("-")).contains("down")) {
            draw_rect(x, y + h - size, x + w, y + h, r, g, b, a);
        }
        if (Arrays.asList(type.split("-")).contains("left")) {
            draw_rect(x, y, x + size, y + h, r, g, b, a);
        }
        if (Arrays.asList(type.split("-")).contains("right")) {
            draw_rect(x + w - size, y, x + w, y + h, r, g, b, a);
        }
    }
    
    public static void draw_rect(final Rect rect, final int r, final int g, final int b, final int a) {
        Gui.drawRect(rect.get_x(), rect.get_y(), rect.get_screen_width(), rect.get_screen_height(), new TravisColor(r, g, b, a).hex());
    }
    
    public static void draw_string(final String string, final int x, final int y, final int r, final int g, final int b, final int a) {
        WurstplusDraw.font_renderer.drawStringWithShadow(string, (float)x, (float)y, new TravisColor(r, g, b, a).hex());
    }
    
    public void draw_string_gl(final String string, final int x, final int y, final int r, final int g, final int b) {
        final Turok resize_gl = new Turok("Resize");
        resize_gl.resize(x, y, this.size);
        WurstplusDraw.font_renderer.drawString(string, x, y, new TravisColor(r, g, b).hex());
        resize_gl.resize(x, y, this.size, "end");
        GL11.glPushMatrix();
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        GlStateManager.enableBlend();
        GL11.glPopMatrix();
        RenderHelp.release_gl();
    }
    
    public int get_string_height() {
        final FontRenderer fontRenderer = WurstplusDraw.font_renderer;
        return (int)(fontRenderer.FONT_HEIGHT * this.size);
    }
    
    public int get_string_width(final String string) {
        final FontRenderer fontRenderer = WurstplusDraw.font_renderer;
        return (int)(fontRenderer.getStringWidth(string) * this.size);
    }
    
    static {
        font_renderer = Minecraft.getMinecraft().fontRenderer;
        custom_font = Minecraft.getMinecraft().fontRenderer;
    }
    
    public static class TravisColor extends Color
    {
        public TravisColor(final int r, final int g, final int b, final int a) {
            super(r, g, b, a);
        }
        
        public TravisColor(final int r, final int g, final int b) {
            super(r, g, b);
        }
        
        public int hex() {
            return this.getRGB();
        }
    }
}
