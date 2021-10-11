//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;

public class SmallShield extends Module
{
    Setting mainhand;
    Setting offhand;
    
    public SmallShield() {
        super(Category.Render);
        this.mainhand = this.register("Mainhand", "MainhandSmallShield", 0.5, 0.0, 1.0);
        this.offhand = this.register("Offhand", "OffhandSmallShield", 0.5, 0.0, 1.0);
        this.name = "SmallShield";
        this.tag = "SmallShield";
    }
    
    public void onUpdate() {
        SmallShield.mc.entityRenderer.itemRenderer.equippedProgressMainHand = (float)this.mainhand.getValue(1);
        SmallShield.mc.entityRenderer.itemRenderer.equippedProgressOffHand = (float)this.offhand.getValue(1);
    }
}
