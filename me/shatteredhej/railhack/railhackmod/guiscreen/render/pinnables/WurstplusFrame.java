//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.*;
import net.minecraft.client.*;
import me.shatteredhej.railhack.*;
import java.util.*;
import java.awt.*;

public class WurstplusFrame
{
    private final ArrayList<WurstplusPinnableButton> pinnable_button;
    private final String name;
    private final String tag;
    private int x;
    private int y;
    private int move_x;
    private int move_y;
    private int width;
    private int height;
    private boolean move;
    private boolean can;
    private final int border_size = 2;
    private final WurstplusDraw font;
    public static int nc_r;
    public static int nc_g;
    public static int nc_b;
    public static int nc_a;
    public static int bg_r;
    public static int bg_g;
    public static int bg_b;
    public static int bg_a;
    public static int bd_r;
    public static int bd_g;
    public static int bd_b;
    public static int bd_a;
    public static int bdw_r;
    public static int bdw_g;
    public static int bdw_b;
    public static int bdw_a;
    public final Minecraft mc;
    
    public WurstplusFrame(final String name, final String tag, final int initial_x, final int initial_y) {
        this.font = new WurstplusDraw(1.0f);
        this.mc = Minecraft.getMinecraft();
        this.pinnable_button = new ArrayList<WurstplusPinnableButton>();
        this.name = name;
        this.tag = tag;
        this.x = initial_x;
        this.y = initial_y;
        this.move_x = 0;
        this.move_y = 0;
        this.width = 100;
        this.height = 25;
        this.can = true;
        final int size = RailHack.get_hud_manager().get_array_huds().size();
        int count = 0;
        for (final WurstplusPinnable pinnables : RailHack.get_hud_manager().get_array_huds()) {
            final WurstplusPinnableButton pinnables_buttons = new WurstplusPinnableButton(this, pinnables.get_title(), pinnables.get_tag());
            pinnables_buttons.set_y(this.height);
            this.pinnable_button.add(pinnables_buttons);
            if (++count >= size) {
                this.height += 5;
            }
            else {
                this.height += 12;
            }
        }
    }
    
    public void set_move(final boolean value) {
        this.move = value;
    }
    
    public void does_can(final boolean value) {
        this.can = value;
    }
    
    public void set_x(final int x) {
        this.x = x;
    }
    
    public void set_y(final int y) {
        this.y = y;
    }
    
    public void set_move_x(final int x) {
        this.move_x = x;
    }
    
    public void set_move_y(final int y) {
        this.move_y = y;
    }
    
    public void set_width(final int width) {
        this.width = width;
    }
    
    public void set_height(final int height) {
        this.height = height;
    }
    
    public String get_name() {
        return this.name;
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public boolean is_moving() {
        return this.move;
    }
    
    public boolean can() {
        return this.can;
    }
    
    public int get_x() {
        return this.x;
    }
    
    public int get_y() {
        return this.y;
    }
    
    public int get_width() {
        return this.width;
    }
    
    public int get_height() {
        return this.height;
    }
    
    public boolean motion(final int mx, final int my) {
        return mx >= this.get_x() && my >= this.get_y() && mx <= this.get_x() + this.get_width() && my <= this.get_y() + this.get_height();
    }
    
    public void crush(final int mx, final int my) {
        final int screen_x = this.mc.displayWidth / 2;
        final int screen_y = this.mc.displayHeight / 2;
        this.set_x(mx - this.move_x);
        this.set_y(my - this.move_y);
        if (this.x + this.width >= screen_x) {
            this.x = screen_x - this.width - 1;
        }
        if (this.x <= 0) {
            this.x = 1;
        }
        if (this.y + this.height >= screen_y) {
            this.y = screen_y - this.height - 1;
        }
        if (this.y <= 0) {
            this.y = 1;
        }
        if (this.x % 2 != 0) {
            this.x += this.x % 2;
        }
        if (this.y % 2 != 0) {
            this.y += this.y % 2;
        }
    }
    
    public void mouse(final int mx, final int my, final int mouse) {
        for (final WurstplusPinnableButton pinnables_buttons : this.pinnable_button) {
            pinnables_buttons.click(mx, my, mouse);
        }
    }
    
    public void release(final int mx, final int my, final int mouse) {
        for (final WurstplusPinnableButton pinnables_buttons : this.pinnable_button) {
            pinnables_buttons.release(mx, my, mouse);
        }
        this.set_move(false);
    }
    
    public void render(final int mx, final int my, final int separate) {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        int color;
        final int color_b = color = Color.HSBtoRGB(tick_color[0], 1.0f, 1.0f);
        if (color_b <= 50) {
            color = 50;
        }
        else {
            color = Math.min(color_b, 120);
        }
        WurstplusFrame.bd_a = color;
        WurstplusFrame.bdw_a = 255;
        WurstplusDraw.draw_rect(this.x, this.y, this.x + this.width, this.y + this.height, WurstplusFrame.bg_r, WurstplusFrame.bg_g, WurstplusFrame.bg_b, WurstplusFrame.bg_a);
        final int x = this.x - 1;
        final int y = this.y;
        final int w = this.width + 1;
        final int height = this.height;
        final int bd_r = WurstplusFrame.bd_r;
        final int bd_g = WurstplusFrame.bd_g;
        final int bd_b = WurstplusFrame.bd_b;
        final int bd_a = WurstplusFrame.bd_a;
        this.getClass();
        WurstplusDraw.draw_rect(x, y, w, height, bd_r, bd_g, bd_b, bd_a, 2, "left-right");
        WurstplusDraw.draw_string(this.name, this.x + 4, this.y + 4, WurstplusFrame.nc_r, WurstplusFrame.nc_g, WurstplusFrame.nc_b, WurstplusFrame.nc_a);
        if (this.is_moving()) {
            this.crush(mx, my);
        }
        for (final WurstplusPinnableButton pinnables_buttons : this.pinnable_button) {
            pinnables_buttons.set_x(this.x + separate);
            pinnables_buttons.render(mx, my, separate);
            if (pinnables_buttons.motion(mx, my)) {
                final int x2 = this.get_x() - 1;
                final int get_save_y = pinnables_buttons.get_save_y();
                final int w2 = this.get_width() + 1;
                final int get_height = pinnables_buttons.get_height();
                final int bdw_r = WurstplusFrame.bdw_r;
                final int bdw_g = WurstplusFrame.bdw_g;
                final int bdw_b = WurstplusFrame.bdw_b;
                final int bdw_a = WurstplusFrame.bdw_a;
                this.getClass();
                WurstplusDraw.draw_rect(x2, get_save_y, w2, get_height, bdw_r, bdw_g, bdw_b, bdw_a, 2, "right-left");
            }
        }
    }
    
    static {
        WurstplusFrame.nc_r = 0;
        WurstplusFrame.nc_g = 0;
        WurstplusFrame.nc_b = 0;
        WurstplusFrame.nc_a = 0;
        WurstplusFrame.bg_r = 0;
        WurstplusFrame.bg_g = 0;
        WurstplusFrame.bg_b = 0;
        WurstplusFrame.bg_a = 0;
        WurstplusFrame.bd_r = 0;
        WurstplusFrame.bd_g = 0;
        WurstplusFrame.bd_b = 0;
        WurstplusFrame.bd_a = 0;
        WurstplusFrame.bdw_r = 0;
        WurstplusFrame.bdw_g = 0;
        WurstplusFrame.bdw_b = 0;
        WurstplusFrame.bdw_a = 255;
    }
}
