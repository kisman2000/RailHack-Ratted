//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;

public class ElytraFly extends Module
{
    Setting speed;
    
    public ElytraFly() {
        super(Category.Movement);
        this.speed = this.register("Speed", "Speed", 1.16, 0.0, 5.0);
        this.name = "ElytraFly";
        this.tag = "ElytraFly";
    }
    
    public void onUpdate() {
        if (ElytraFly.mc.player.capabilities.isFlying || ElytraFly.mc.player.isElytraFlying()) {
            ElytraFly.mc.player.setSprinting(false);
        }
        if (ElytraFly.mc.player.capabilities.isFlying) {
            ElytraFly.mc.player.setVelocity(0.0, 0.0, 0.0);
            ElytraFly.mc.player.setPosition(ElytraFly.mc.player.posX, ElytraFly.mc.player.posY - 0.0, ElytraFly.mc.player.posZ);
            ElytraFly.mc.player.capabilities.setFlySpeed((float)this.speed.getValue(1));
            ElytraFly.mc.player.setSprinting(false);
        }
        if (ElytraFly.mc.player.onGround) {
            ElytraFly.mc.player.capabilities.allowFlying = false;
        }
        if (ElytraFly.mc.player.isElytraFlying()) {
            ElytraFly.mc.player.capabilities.setFlySpeed(0.915f);
            ElytraFly.mc.player.capabilities.isFlying = true;
            if (!ElytraFly.mc.player.capabilities.isCreativeMode) {
                ElytraFly.mc.player.capabilities.allowFlying = true;
            }
        }
    }
    
    protected void whenDisabled() {
        ElytraFly.mc.player.capabilities.isFlying = false;
        ElytraFly.mc.player.capabilities.setFlySpeed(0.05f);
        if (!ElytraFly.mc.player.capabilities.isCreativeMode) {
            ElytraFly.mc.player.capabilities.allowFlying = false;
        }
    }
}
