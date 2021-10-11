//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;

public class CustomFov extends Module
{
    Setting fov;
    private float OldFOV;
    
    public CustomFov() {
        super(Category.Render);
        this.fov = this.register("Fov", "CustomFovFov", 0, 0, 180);
        this.name = "CustomFov";
        this.tag = "CustomFov";
    }
    
    public void onEnable() {
        this.OldFOV = CustomFov.mc.gameSettings.fovSetting;
    }
    
    public void onUpdate() {
        CustomFov.mc.gameSettings.fovSetting = (float)this.fov.getValue(1);
    }
    
    public void whenDisabled() {
        CustomFov.mc.gameSettings.fovSetting = this.OldFOV;
    }
}
