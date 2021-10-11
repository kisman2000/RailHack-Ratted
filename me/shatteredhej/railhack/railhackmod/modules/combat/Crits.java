//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.util.function.*;

public class Crits extends Module
{
    Setting mode;
    Setting packets;
    @EventHandler
    private Listener<EventPacket.SendPacket> listener;
    
    public Crits() {
        super(Category.Combat);
        this.mode = this.register("Mode", "CriticalsMode", "Packet", this.combobox(new String[] { "Packet", "Jump" }));
        this.packets = this.register("Packets", "Packets", 1, 1, 6);
        CPacketUseEntity event_entity;
        this.listener = new Listener<EventPacket.SendPacket>(event -> {
            if (event.getPacket() instanceof CPacketUseEntity) {
                event_entity = (CPacketUseEntity)event.getPacket();
                if (event_entity.getAction() == CPacketUseEntity.Action.ATTACK && Crits.mc.player.onGround) {
                    if (this.mode.in("Packet")) {
                        if (this.packets.getValue(1) == 1) {
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.10000000149011612, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        }
                        if (this.packets.getValue(1) == 2) {
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.10000000149011612, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        }
                        if (this.packets.getValue(1) == 3) {
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.10000000149011612, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        }
                        if (this.packets.getValue(1) == 4) {
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.10000000149011612, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        }
                        if (this.packets.getValue(1) == 5) {
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.10000000149011612, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        }
                        if (this.packets.getValue(1) == 6) {
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY + 0.10000000149011612, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                            Crits.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Crits.mc.player.posX, Crits.mc.player.posY, Crits.mc.player.posZ, false));
                        }
                    }
                    else if (this.mode.in("Jump")) {
                        Crits.mc.player.jump();
                    }
                }
            }
            return;
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Crits";
        this.tag = "Crits";
        this.description = "You can hit with criticals when attack.";
    }
    
    public String array_detail() {
        return this.mode.get_current_value();
    }
}
