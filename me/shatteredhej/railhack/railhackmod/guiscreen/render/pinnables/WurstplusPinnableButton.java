//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.*;
import me.shatteredhej.railhack.*;

public class WurstplusPinnableButton
{
    private final WurstplusPinnable pinnable;
    private final WurstplusFrame master;
    private final String name;
    private final String tag;
    private int x;
    private int y;
    private int save_y;
    private int width;
    private int height;
    private final boolean first;
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
    
    public WurstplusPinnableButton(final WurstplusFrame master, final String name, final String tag) {
        this.font = new WurstplusDraw(1.0f);
        this.master = master;
        this.name = name;
        this.tag = tag;
        this.pinnable = RailHack.get_hud_manager().get_pinnable_with_tag(tag);
        this.x = master.get_x();
        this.y = master.get_y();
        this.save_y = this.y;
        this.width = this.master.get_width();
        this.height = this.font.get_string_height();
        this.first = true;
    }
    
    public void set_x(final int x) {
        this.x = x;
    }
    
    public void set_y(final int y) {
        this.y = y;
    }
    
    public void set_save_y(final int y) {
        this.save_y = y;
    }
    
    public void set_width(final int width) {
        this.width = width;
    }
    
    public void set_height(final int height) {
        this.height = height;
    }
    
    public int get_x() {
        return this.x;
    }
    
    public int get_y() {
        return this.y;
    }
    
    public int get_save_y() {
        return this.save_y;
    }
    
    public int get_width() {
        return this.width;
    }
    
    public int get_height() {
        return this.height;
    }
    
    public boolean motion(final int mx, final int my, final int p_x, final int p_y, final int p_w, final int p_h) {
        return mx >= p_x && my >= p_y && mx <= p_x + p_w && my <= p_y + p_h;
    }
    
    public boolean motion(final int mx, final int my) {
        return mx >= this.get_x() && my >= this.get_save_y() && mx <= this.get_x() + this.get_width() && my <= this.get_save_y() + this.get_height();
    }
    
    public void click(final int mx, final int my, final int mouse) {
        this.pinnable.click(mx, my, mouse);
        if (mouse == 0 && this.motion(mx, my)) {
            this.master.does_can(false);
            this.pinnable.set_active(!this.pinnable.is_active());
        }
    }
    
    public void release(final int mx, final int my, final int mouse) {
        this.pinnable.release(mx, my, mouse);
        this.master.does_can(true);
    }
    
    public void render(final int mx, final int my, final int separate) {
        this.set_width(this.master.get_width() - separate);
        this.save_y = this.y + this.master.get_y() - 9;
        if (this.pinnable.is_active()) {
            WurstplusDraw.draw_rect(this.x, this.save_y, this.x + this.width - separate, this.save_y + this.height, WurstplusPinnableButton.bg_r, WurstplusPinnableButton.bg_g, WurstplusPinnableButton.bg_b, WurstplusPinnableButton.bg_a);
            WurstplusDraw.draw_string(this.pinnable.get_title(), this.x + separate, this.save_y, WurstplusPinnableButton.nc_r, WurstplusPinnableButton.nc_g, WurstplusPinnableButton.nc_b, WurstplusPinnableButton.nc_a);
        }
        else {
            WurstplusDraw.draw_string(this.pinnable.get_title(), this.x + separate, this.save_y, WurstplusPinnableButton.nc_r, WurstplusPinnableButton.nc_g, WurstplusPinnableButton.nc_b, WurstplusPinnableButton.nc_a);
        }
        this.pinnable.render(mx, my, 0);
    }
    
    static {
        WurstplusPinnableButton.nc_r = 0;
        WurstplusPinnableButton.nc_g = 0;
        WurstplusPinnableButton.nc_b = 0;
        WurstplusPinnableButton.nc_a = 0;
        WurstplusPinnableButton.bg_r = 0;
        WurstplusPinnableButton.bg_g = 0;
        WurstplusPinnableButton.bg_b = 0;
        WurstplusPinnableButton.bg_a = 0;
        WurstplusPinnableButton.bd_r = 0;
        WurstplusPinnableButton.bd_g = 0;
        WurstplusPinnableButton.bd_b = 0;
    }
}
