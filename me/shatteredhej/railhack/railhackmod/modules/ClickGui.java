//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.*;
import net.minecraft.client.gui.*;

public class ClickGui extends Module
{
    Setting label_frame;
    Setting name_frame_r;
    Setting name_frame_g;
    Setting name_frame_b;
    Setting background_frame_r;
    Setting background_frame_g;
    Setting background_frame_b;
    Setting background_frame_a;
    Setting border_frame_r;
    Setting border_frame_g;
    Setting border_frame_b;
    Setting label_widget;
    Setting name_widget_r;
    Setting name_widget_g;
    Setting name_widget_b;
    Setting background_widget_r;
    Setting background_widget_g;
    Setting background_widget_b;
    Setting background_widget_a;
    Setting border_widget_r;
    Setting border_widget_g;
    Setting border_widget_b;
    private static ClickGui INSTANCE;
    
    public ClickGui() {
        super(Category.Gui);
        this.label_frame = this.register("Frame", "ClickGUIInfoFrame", "Frames");
        this.name_frame_r = this.register("Name R", "ClickGUINameFrameR", 255, 0, 255);
        this.name_frame_g = this.register("Name G", "ClickGUINameFrameG", 255, 0, 255);
        this.name_frame_b = this.register("Name B", "ClickGUINameFrameB", 255, 0, 255);
        this.background_frame_r = this.register("Background R", "ClickGUIBackgroundFrameR", 230, 0, 255);
        this.background_frame_g = this.register("Background G", "ClickGUIBackgroundFrameG", 100, 0, 255);
        this.background_frame_b = this.register("Background B", "ClickGUIBackgroundFrameB", 50, 0, 255);
        this.background_frame_a = this.register("Background A", "ClickGUIBackgroundFrameA", 210, 0, 255);
        this.border_frame_r = this.register("Border R", "ClickGUIBorderFrameR", 255, 0, 255);
        this.border_frame_g = this.register("Border G", "ClickGUIBorderFrameG", 255, 0, 255);
        this.border_frame_b = this.register("Border B", "ClickGUIBorderFrameB", 255, 0, 255);
        this.label_widget = this.register("Widget", "ClickGUIInfoWidget", "Widgets");
        this.name_widget_r = this.register("Name R", "ClickGUINameWidgetR", 255, 0, 255);
        this.name_widget_g = this.register("Name G", "ClickGUINameWidgetG", 255, 0, 255);
        this.name_widget_b = this.register("Name B", "ClickGUINameWidgetB", 255, 0, 255);
        this.background_widget_r = this.register("Background R", "ClickGUIBackgroundWidgetR", 255, 0, 255);
        this.background_widget_g = this.register("Background G", "ClickGUIBackgroundWidgetG", 255, 0, 255);
        this.background_widget_b = this.register("Background B", "ClickGUIBackgroundWidgetB", 255, 0, 255);
        this.background_widget_a = this.register("Background A", "ClickGUIBackgroundWidgetA", 100, 0, 255);
        this.border_widget_r = this.register("Border R", "ClickGUIBorderWidgetR", 255, 0, 255);
        this.border_widget_g = this.register("Border G", "ClickGUIBorderWidgetG", 255, 0, 255);
        this.border_widget_b = this.register("Border B", "ClickGUIBorderWidgetB", 255, 0, 255);
        this.name = "ClickGui";
        this.tag = "GUI";
        this.set_bind(54);
    }
    
    public void onUpdate() {
        RailHack.clickGui.theme_frame_name_r = this.name_frame_r.getValue(1);
        RailHack.clickGui.theme_frame_name_g = this.name_frame_g.getValue(1);
        RailHack.clickGui.theme_frame_name_b = this.name_frame_b.getValue(1);
        RailHack.clickGui.theme_frame_background_r = this.background_frame_r.getValue(1);
        RailHack.clickGui.theme_frame_background_g = this.background_frame_g.getValue(1);
        RailHack.clickGui.theme_frame_background_b = this.background_frame_b.getValue(1);
        RailHack.clickGui.theme_frame_background_a = this.background_frame_a.getValue(1);
        RailHack.clickGui.theme_frame_border_r = this.border_frame_r.getValue(1);
        RailHack.clickGui.theme_frame_border_g = this.border_frame_g.getValue(1);
        RailHack.clickGui.theme_frame_border_b = this.border_frame_b.getValue(1);
        RailHack.clickGui.theme_widget_name_r = this.name_widget_r.getValue(1);
        RailHack.clickGui.theme_widget_name_g = this.name_widget_g.getValue(1);
        RailHack.clickGui.theme_widget_name_b = this.name_widget_b.getValue(1);
        RailHack.clickGui.theme_widget_background_r = this.background_widget_r.getValue(1);
        RailHack.clickGui.theme_widget_background_g = this.background_widget_g.getValue(1);
        RailHack.clickGui.theme_widget_background_b = this.background_widget_b.getValue(1);
        RailHack.clickGui.theme_widget_background_a = this.background_widget_a.getValue(1);
        RailHack.clickGui.theme_widget_border_r = this.border_widget_r.getValue(1);
        RailHack.clickGui.theme_widget_border_g = this.border_widget_g.getValue(1);
        RailHack.clickGui.theme_widget_border_b = this.border_widget_b.getValue(1);
    }
    
    public void onEnable() {
        if (ClickGui.mc.world != null && ClickGui.mc.player != null) {
            ClickGui.mc.displayGuiScreen((GuiScreen)RailHack.clickGui);
        }
    }
    
    public void whenDisabled() {
        if (ClickGui.mc.world != null && ClickGui.mc.player != null) {
            ClickGui.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    public static ClickGui getINSTANCE() {
        if (ClickGui.INSTANCE == null) {
            ClickGui.INSTANCE = new ClickGui();
        }
        return ClickGui.INSTANCE;
    }
    
    private void setInstance() {
        ClickGui.INSTANCE = this;
    }
    
    static {
        ClickGui.INSTANCE = new ClickGui();
    }
}
