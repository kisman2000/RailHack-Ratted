//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.client.gui.*;

public class HellenKeller extends Module
{
    float masterLevel;
    
    public HellenKeller() {
        super(Category.Render);
        this.name = "HellenKeller";
        this.tag = "HellenKeller";
        this.description = "you go blind and deaf";
    }
    
    public void onEnable() {
        this.masterLevel = HellenKeller.mc.gameSettings.getSoundLevel(SoundCategory.MASTER);
        HellenKeller.mc.gameSettings.setSoundLevel(SoundCategory.MASTER, 0.0f);
    }
    
    public void render() {
        GlStateManager.pushMatrix();
        Gui.drawRect(0, 0, HellenKeller.mc.displayWidth, HellenKeller.mc.displayHeight, new Color(0, 0, 0, 255).getRGB());
        GlStateManager.popMatrix();
    }
    
    public void whenDisabled() {
        HellenKeller.mc.gameSettings.setSoundLevel(SoundCategory.MASTER, this.masterLevel);
    }
}
