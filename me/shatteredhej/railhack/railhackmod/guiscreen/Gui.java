//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen;

import net.minecraft.client.gui.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.render.components.*;
import net.minecraft.client.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.*;
import java.util.*;

public class Gui extends GuiScreen
{
    private final ArrayList<WurstplusFrame> frame;
    private int frame_x;
    private WurstplusFrame current;
    private final boolean event_start;
    private final boolean event_finish;
    public int theme_frame_name_r;
    public int theme_frame_name_g;
    public int theme_frame_name_b;
    public int theme_frame_name_a;
    public int theme_frame_background_r;
    public int theme_frame_background_g;
    public int theme_frame_background_b;
    public int theme_frame_background_a;
    public int theme_frame_border_r;
    public int theme_frame_border_g;
    public int theme_frame_border_b;
    public int theme_widget_name_r;
    public int theme_widget_name_g;
    public int theme_widget_name_b;
    public int theme_widget_name_a;
    public int theme_widget_background_r;
    public int theme_widget_background_g;
    public int theme_widget_background_b;
    public int theme_widget_background_a;
    public int theme_widget_border_r;
    public int theme_widget_border_g;
    public int theme_widget_border_b;
    private final Minecraft mc;
    
    public Gui() {
        this.theme_frame_name_r = 0;
        this.theme_frame_name_g = 0;
        this.theme_frame_name_b = 0;
        this.theme_frame_name_a = 0;
        this.theme_frame_background_r = 0;
        this.theme_frame_background_g = 0;
        this.theme_frame_background_b = 0;
        this.theme_frame_background_a = 0;
        this.theme_frame_border_r = 0;
        this.theme_frame_border_g = 0;
        this.theme_frame_border_b = 0;
        this.theme_widget_name_r = 0;
        this.theme_widget_name_g = 0;
        this.theme_widget_name_b = 0;
        this.theme_widget_name_a = 0;
        this.theme_widget_background_r = 0;
        this.theme_widget_background_g = 0;
        this.theme_widget_background_b = 0;
        this.theme_widget_background_a = 0;
        this.theme_widget_border_r = 0;
        this.theme_widget_border_g = 0;
        this.theme_widget_border_b = 0;
        this.mc = Minecraft.getMinecraft();
        this.frame = new ArrayList<WurstplusFrame>();
        this.frame_x = 10;
        this.event_start = true;
        this.event_finish = false;
        for (final Category categorys : Category.values()) {
            if (!categorys.is_hidden()) {
                final WurstplusFrame frames = new WurstplusFrame(categorys);
                frames.set_x(this.frame_x);
                this.frame.add(frames);
                this.frame_x += frames.get_width() + 5;
                this.current = frames;
            }
        }
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void onGuiClosed() {
        RailHack.get_hack_manager().getModuleWithTag("GUI").setActive(false);
        RailHack.get_config_manager().save_settings();
    }
    
    protected void keyTyped(final char char_, final int key) {
        for (final WurstplusFrame frame : this.frame) {
            frame.bind(char_, key);
            if (key == 1 && !frame.is_binding()) {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            if (key == 208 || key == 200) {
                frame.set_y(frame.get_y() - 1);
            }
            if (key == 200 || key == 208) {
                frame.set_y(frame.get_y() + 1);
            }
        }
    }
    
    protected void mouseClicked(final int mx, final int my, final int mouse) {
        for (final WurstplusFrame frames : this.frame) {
            frames.mouse(mx, my, mouse);
            if (mouse == 0 && frames.motion(mx, my) && frames.can()) {
                frames.does_button_for_do_widgets_can(false);
                (this.current = frames).set_move(true);
                this.current.set_move_x(mx - this.current.get_x());
                this.current.set_move_y(my - this.current.get_y());
            }
        }
    }
    
    protected void mouseReleased(final int mx, final int my, final int state) {
        for (final WurstplusFrame frames : this.frame) {
            frames.does_button_for_do_widgets_can(true);
            frames.mouse_release(mx, my, state);
            frames.set_move(false);
        }
        this.set_current(this.current);
    }
    
    public void drawScreen(final int mx, final int my, final float tick) {
        this.drawDefaultBackground();
        for (final WurstplusFrame frames : this.frame) {
            frames.render(mx, my);
        }
    }
    
    public void set_current(final WurstplusFrame current) {
        this.frame.remove(current);
        this.frame.add(current);
    }
    
    public WurstplusFrame get_current() {
        return this.current;
    }
    
    public ArrayList<WurstplusFrame> get_array_frames() {
        return this.frame;
    }
    
    public WurstplusFrame get_frame_with_tag(final String tag) {
        WurstplusFrame frame_requested = null;
        for (final WurstplusFrame frames : this.get_array_frames()) {
            if (frames.get_tag().equals(tag)) {
                frame_requested = frames;
            }
        }
        return frame_requested;
    }
}
