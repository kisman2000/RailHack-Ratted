//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.potion.*;

public class XormiosModule extends Module
{
    Setting strMultiplier;
    
    public XormiosModule() {
        super(Category.Combat);
        this.strMultiplier = this.register("Multiplier", "XormiosModuleMultiplier", 1, 0, 2);
        this.name = "XormiosModule";
        this.tag = "XormiosModule";
    }
    
    public void onUpdate() {
        final PotionEffect effect = new PotionEffect(Potion.getPotionById(5), 10000000, this.strMultiplier.getValue(1));
        effect.setPotionDurationMax(true);
        XormiosModule.mc.player.addPotionEffect(effect);
    }
    
    public void whenDisabled() {
        XormiosModule.mc.player.removePotionEffect(Potion.getPotionById(5));
    }
}
