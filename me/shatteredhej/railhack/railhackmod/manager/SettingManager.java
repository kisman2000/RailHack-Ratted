//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.manager;

import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.module.*;
import java.util.*;

public class SettingManager
{
    public ArrayList<Setting> array_setting;
    
    public SettingManager() {
        this.array_setting = new ArrayList<Setting>();
    }
    
    public void register(final Setting setting) {
        this.array_setting.add(setting);
    }
    
    public ArrayList<Setting> get_array_settings() {
        return this.array_setting;
    }
    
    public Setting get_setting_with_tag(final Module module, final String tag) {
        Setting setting_requested = null;
        for (final Setting settings : this.get_array_settings()) {
            if (settings.get_master().equals(module) && settings.get_tag().equalsIgnoreCase(tag)) {
                setting_requested = settings;
            }
        }
        return setting_requested;
    }
    
    public Setting get_setting_with_tag(final String tag, final String tag_) {
        Setting setting_requested = null;
        for (final Setting settings : this.get_array_settings()) {
            if (settings.get_master().get_tag().equalsIgnoreCase(tag) && settings.get_tag().equalsIgnoreCase(tag_)) {
                setting_requested = settings;
                break;
            }
        }
        return setting_requested;
    }
    
    public ArrayList<Setting> get_settings_with_hack(final Module module) {
        final ArrayList<Setting> setting_requesteds = new ArrayList<Setting>();
        for (final Setting settings : this.get_array_settings()) {
            if (settings.get_master().equals(module)) {
                setting_requesteds.add(settings);
            }
        }
        return setting_requesteds;
    }
}
