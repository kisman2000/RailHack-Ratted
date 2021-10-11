//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.module;

import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.*;
import org.lwjgl.input.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;

public abstract class Module implements Listenable
{
    public Category category;
    public String name;
    public String tag;
    public String description;
    public int bind;
    public boolean state_module;
    public boolean toggle_message;
    public boolean widget_usage;
    public static final Minecraft mc;
    
    public Module(final Category category) {
        this.name = "";
        this.tag = "";
        this.description = "";
        this.bind = -1;
        this.toggle_message = true;
        this.widget_usage = false;
        this.category = category;
    }
    
    public void set_bind(final int key) {
        this.bind = key;
    }
    
    public void set_if_can_send_message_toggle(final boolean value) {
        this.toggle_message = value;
    }
    
    public boolean isActive() {
        return this.state_module;
    }
    
    public boolean using_widget() {
        return this.widget_usage;
    }
    
    public String get_name() {
        return this.name;
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public String get_description() {
        return this.description;
    }
    
    public static boolean fullNullCheck() {
        return Module.mc.player == null || Module.mc.world == null;
    }
    
    public int get_bind(final int type) {
        return this.bind;
    }
    
    public String get_bind(final String type) {
        String converted_bind = "null";
        if (this.get_bind(0) < 0) {
            converted_bind = "NONE";
        }
        if (!converted_bind.equals("NONE")) {
            final String key = Keyboard.getKeyName(this.get_bind(0));
            converted_bind = Character.toUpperCase(key.charAt(0)) + ((key.length() != 1) ? key.substring(1).toLowerCase() : "");
        }
        else {
            converted_bind = "None";
        }
        return converted_bind;
    }
    
    public Category get_category() {
        return this.category;
    }
    
    public boolean can_send_message_when_toggle() {
        return this.toggle_message;
    }
    
    public void setDisable() {
        this.state_module = false;
        this.whenDisabled();
        Event.EVENT_BUS.unsubscribe(this);
    }
    
    public void set_enable() {
        this.state_module = true;
        this.onEnable();
        Event.EVENT_BUS.subscribe(this);
    }
    
    public void setActive(final boolean value) {
        if (this.state_module != value) {
            if (value) {
                this.set_enable();
            }
            else {
                this.setDisable();
            }
        }
        if (!this.tag.equals("GUI") && !this.tag.equals("HUD") && this.toggle_message) {
            MessageUtil.toggle_message(this);
        }
    }
    
    public void toggle() {
        this.setActive(!this.isActive());
    }
    
    protected Setting register(final String name, final String tag, final int value, final int min, final int max) {
        RailHack.getSettingManager().register(new Setting(this, name, tag, value, min, max));
        return RailHack.getSettingManager().get_setting_with_tag(this, tag);
    }
    
    protected Setting register(final String name, final String tag, final double value, final double min, final double max) {
        RailHack.getSettingManager().register(new Setting(this, name, tag, value, min, max));
        return RailHack.getSettingManager().get_setting_with_tag(this, tag);
    }
    
    protected Setting register(final String name, final String tag, final boolean value) {
        RailHack.getSettingManager().register(new Setting(this, name, tag, value));
        return RailHack.getSettingManager().get_setting_with_tag(this, tag);
    }
    
    protected Setting register(final String name, final String tag, final String value) {
        RailHack.getSettingManager().register(new Setting(this, name, tag, value));
        return RailHack.getSettingManager().get_setting_with_tag(this, tag);
    }
    
    protected Setting register(final String name, final String tag, final String value, final List<String> values) {
        RailHack.getSettingManager().register(new Setting(this, name, tag, (List)values, value));
        return RailHack.getSettingManager().get_setting_with_tag(this, tag);
    }
    
    protected List<String> combobox(final String... item) {
        return new ArrayList<String>(Arrays.asList(item));
    }
    
    public void render(final EventRender event) {
    }
    
    public void render() {
    }
    
    public void onUpdate() {
    }
    
    public void event_widget() {
    }
    
    protected void whenDisabled() {
    }
    
    protected void onEnable() {
    }
    
    public String array_detail() {
        return null;
    }
    
    public void on_render_model(final EventRenderEntityModel event) {
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
