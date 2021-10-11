//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.util.function.*;

public class FeetXP extends Module
{
    @EventHandler
    private Listener<EventPacket.SendPacket> listener;
    
    public FeetXP() {
        super(Category.Combat);
        this.listener = new Listener<EventPacket.SendPacket>(event -> {
            if (event.getPacket() instanceof CPacketPlayerTryUseItem && FeetXP.mc.player.getHeldItemMainhand().getItem() instanceof ItemExpBottle) {
                FeetXP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(FeetXP.mc.player.rotationYaw, 90.0f, FeetXP.mc.player.onGround));
            }
            return;
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "FeetXP";
        this.tag = "FeetXP";
    }
}
