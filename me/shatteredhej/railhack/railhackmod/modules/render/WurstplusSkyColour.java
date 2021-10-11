//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;
import java.awt.*;

public class WurstplusSkyColour extends Module
{
    Setting r;
    Setting g;
    Setting b;
    Setting rainbow_mode;
    
    public WurstplusSkyColour() {
        super(Category.Render);
        this.r = this.register("R", "SkyColourR", 255, 0, 255);
        this.g = this.register("G", "SkyColourG", 255, 0, 255);
        this.b = this.register("B", "SkyColourB", 255, 0, 255);
        this.rainbow_mode = this.register("Rainbow", "SkyColourRainbow", false);
        this.name = "SkyColor";
        this.tag = "SkyColor";
        this.description = "Changes the sky's colour";
    }
    
    @SubscribeEvent
    public void fog_colour(final EntityViewRenderEvent.FogColors event) {
        event.setRed(this.r.getValue(1) / 255.0f);
        event.setGreen(this.g.getValue(1) / 255.0f);
        event.setBlue(this.b.getValue(1) / 255.0f);
    }
    
    @SubscribeEvent
    public void fog_density(final EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.0f);
        event.setCanceled(true);
    }
    
    protected void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    protected void whenDisabled() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public void onUpdate() {
        if (this.rainbow_mode.getValue(true)) {
            this.cycle_rainbow();
        }
    }
    
    public void cycle_rainbow() {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int color_rgb_o = Color.HSBtoRGB(tick_color[0], 0.8f, 0.8f);
        this.r.setValue(color_rgb_o >> 16 & 0xFF);
        this.g.setValue(color_rgb_o >> 8 & 0xFF);
        this.b.setValue(color_rgb_o & 0xFF);
    }
}
