//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;

public class KeyLog extends Module
{
    public KeyLog() {
        super(Category.Combat);
        this.name = "KeyLog";
        this.tag = "KeyLog";
        this.description = "I swear to god df its not what you think it is";
    }
    
    public void onEnable() {
        final float hp = KeyLog.mc.player.getHealth() + KeyLog.mc.player.getAbsorptionAmount();
        KeyLog.mc.player.connection.handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("Logged out at " + hp + " health.")));
        this.setDisable();
    }
}
