//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen;

import net.minecraft.client.gui.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;

public class Hud extends GuiScreen
{
    private final WurstplusFrame frame;
    private int frame_height;
    public boolean on_gui;
    public boolean back;
    
    public Hud() {
        this.frame = new WurstplusFrame("Root Client HudEditor", "Hud", 40, 40);
        this.back = false;
        this.on_gui = false;
    }
    
    public WurstplusFrame get_frame_hud() {
        return this.frame;
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void initGui() {
        this.on_gui = true;
        WurstplusFrame.nc_r = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUINameFrameR").getValue(1);
        WurstplusFrame.nc_g = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUINameFrameG").getValue(1);
        WurstplusFrame.nc_b = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUINameFrameB").getValue(1);
        WurstplusFrame.bg_r = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameR").getValue(1);
        WurstplusFrame.bg_g = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameG").getValue(1);
        WurstplusFrame.bg_b = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameB").getValue(1);
        WurstplusFrame.bg_a = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBackgroundFrameA").getValue(1);
        WurstplusFrame.bd_r = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderFrameR").getValue(1);
        WurstplusFrame.bd_g = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderFrameG").getValue(1);
        WurstplusFrame.bd_b = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderFrameB").getValue(1);
        WurstplusFrame.bd_a = 0;
        WurstplusFrame.bdw_r = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetR").getValue(1);
        WurstplusFrame.bdw_g = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetG").getValue(1);
        WurstplusFrame.bdw_b = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetB").getValue(1);
        WurstplusFrame.bdw_a = 255;
        WurstplusPinnableButton.nc_r = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUINameWidgetR").getValue(1);
        WurstplusPinnableButton.nc_g = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUINameWidgetG").getValue(1);
        WurstplusPinnableButton.nc_b = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUINameWidgetB").getValue(1);
        WurstplusPinnableButton.bg_r = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetR").getValue(1);
        WurstplusPinnableButton.bg_g = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetG").getValue(1);
        WurstplusPinnableButton.bg_b = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetB").getValue(1);
        WurstplusPinnableButton.bg_a = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBackgroundWidgetA").getValue(1);
        WurstplusPinnableButton.bd_r = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetR").getValue(1);
        WurstplusPinnableButton.bd_g = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetG").getValue(1);
        WurstplusPinnableButton.bd_b = RailHack.getSettingManager().get_setting_with_tag("GUI", "ClickGUIBorderWidgetB").getValue(1);
    }
    
    public void onGuiClosed() {
        if (this.back) {
            RailHack.get_hack_manager().getModuleWithTag("GUI").setActive(true);
            RailHack.get_hack_manager().getModuleWithTag("HUD").setActive(false);
        }
        else {
            RailHack.get_hack_manager().getModuleWithTag("HUD").setActive(false);
            RailHack.get_hack_manager().getModuleWithTag("GUI").setActive(false);
        }
        this.on_gui = false;
        RailHack.get_config_manager().save_settings();
    }
    
    protected void mouseClicked(final int mx, final int my, final int mouse) {
        this.frame.mouse(mx, my, mouse);
        if (mouse == 0 && this.frame.motion(mx, my) && this.frame.can()) {
            this.frame.set_move(true);
            this.frame.set_move_x(mx - this.frame.get_x());
            this.frame.set_move_y(my - this.frame.get_y());
        }
    }
    
    protected void mouseReleased(final int mx, final int my, final int state) {
        this.frame.release(mx, my, state);
        this.frame.set_move(false);
    }
    
    public void drawScreen(final int mx, final int my, final float tick) {
        this.drawDefaultBackground();
        this.frame.render(mx, my, 2);
    }
}
