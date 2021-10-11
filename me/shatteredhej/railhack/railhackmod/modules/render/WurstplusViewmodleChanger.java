//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class WurstplusViewmodleChanger extends Module
{
    Setting custom_fov;
    Setting items;
    Setting viewmodle_fov;
    Setting normal_offset;
    Setting offset;
    Setting offset_x;
    Setting offset_y;
    Setting main_x;
    Setting main_y;
    private float fov;
    
    public WurstplusViewmodleChanger() {
        super(Category.Render);
        this.custom_fov = this.register("FOV", "FOVSlider", 130, 110, 170);
        this.items = this.register("Items", "FOVItems", false);
        this.viewmodle_fov = this.register("Items FOV", "ItemsFOVSlider", 130, 110, 170);
        this.normal_offset = this.register("Offset Offhand", "FOVOffset", true);
        this.offset = this.register("Offset Mainhand", "FOVOffsetMain", 0.7, 0.0, 1.0);
        this.offset_x = this.register("Offset X", "FOVOffsetX", 0.0, -1.0, 1.0);
        this.offset_y = this.register("Offset Y", "FOVOffsetY", 0.0, -1.0, 1.0);
        this.main_x = this.register("Main X", "FOVMainX", 0.0, -1.0, 1.0);
        this.main_y = this.register("Main Y", "FOVMainY", 0.0, -1.0, 1.0);
        this.name = "OldViewModel";
        this.tag = "CustomViewmodel";
        this.description = "anti chad";
    }
    
    protected void onEnable() {
        this.fov = WurstplusViewmodleChanger.mc.gameSettings.fovSetting;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    protected void whenDisabled() {
        WurstplusViewmodleChanger.mc.gameSettings.fovSetting = this.fov;
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public void onUpdate() {
        WurstplusViewmodleChanger.mc.gameSettings.fovSetting = (float)this.custom_fov.getValue(1);
    }
    
    @SubscribeEvent
    public void fov_event(final EntityViewRenderEvent.FOVModifier m) {
        if (this.items.getValue(true)) {
            m.setFOV((float)this.viewmodle_fov.getValue(1));
        }
    }
}
