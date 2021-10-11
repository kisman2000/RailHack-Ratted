//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;

public class NoWeather extends Module
{
    public NoWeather() {
        super(Category.Render);
        this.name = "NoWeather";
        this.tag = "NoWeather";
        this.description = "no weather";
    }
    
    public void onUpdate() {
        if (!this.isActive()) {
            return;
        }
        if (NoWeather.mc.world.isRaining()) {
            NoWeather.mc.world.setRainStrength(0.0f);
        }
    }
}
