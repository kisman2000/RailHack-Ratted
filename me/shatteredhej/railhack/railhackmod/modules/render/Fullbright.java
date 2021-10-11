//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;

public class Fullbright extends Module
{
    Setting mode;
    private float previousSetting;
    
    public Fullbright() {
        super(Category.Render);
        this.mode = this.register("Mode: ", "Mode", "Gamma", this.combobox(new String[] { "Gamma", "Potion" }));
        this.previousSetting = 1.0f;
        this.name = "Fullbright";
        this.tag = "Fullbright";
    }
    
    public void onEnable() {
        this.previousSetting = Fullbright.mc.gameSettings.gammaSetting;
    }
    
    public void onUpdate() {
        if (this.mode.in("Gamma")) {
            Fullbright.mc.gameSettings.gammaSetting = 1000.0f;
        }
        if (this.mode.in("Potion")) {
            Fullbright.mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5210));
        }
    }
    
    public void whenDisabled() {
        if (this.mode.in("Potion")) {
            Fullbright.mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
        }
        Fullbright.mc.gameSettings.gammaSetting = this.previousSetting;
    }
}
