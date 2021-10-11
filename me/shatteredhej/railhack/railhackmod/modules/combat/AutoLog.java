//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;

public class AutoLog extends Module
{
    Setting health;
    
    public AutoLog() {
        super(Category.Combat);
        this.health = this.register("Health", "Health", 10, 0, 36);
        this.name = "AutoLog";
        this.tag = "AutoLog";
        this.description = "automatic tactical disconnect";
    }
    
    public void onEnable() {
        final float hp = AutoLog.mc.player.getHealth() + AutoLog.mc.player.getAbsorptionAmount();
        if (hp <= this.health.getValue(1)) {
            Minecraft.getMinecraft().getConnection().handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("Logged out at " + hp + " health.")));
            this.setDisable();
        }
    }
}
