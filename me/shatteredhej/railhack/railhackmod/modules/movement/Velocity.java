//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.network.play.server.*;
import java.util.function.*;

public class Velocity extends Module
{
    Setting noPush;
    @EventHandler
    private Listener<EventPacket.ReceivePacket> damage;
    @EventHandler
    private Listener<EventEntity.EventColision> explosion;
    @EventHandler
    private Listener<EventPlayerPushOutOfBlocks> PushOutOfBlocks;
    @EventHandler
    private Listener<EventPlayerApplyCollision> ApplyCollision;
    
    public Velocity() {
        super(Category.Movement);
        this.noPush = this.register("NoPush", "VelocityNoPush", false);
        SPacketEntityVelocity knockback;
        final SPacketEntityVelocity sPacketEntityVelocity;
        final SPacketEntityVelocity sPacketEntityVelocity2;
        final SPacketEntityVelocity sPacketEntityVelocity3;
        SPacketExplosion sPacketExplosion;
        SPacketExplosion knockback2;
        final SPacketExplosion sPacketExplosion2;
        final SPacketExplosion sPacketExplosion3;
        this.damage = new Listener<EventPacket.ReceivePacket>(event -> {
            if (event.get_era() == EventCancellable.Era.EVENT_PRE) {
                if (event.getPacket() instanceof SPacketEntityVelocity) {
                    knockback = (SPacketEntityVelocity)event.getPacket();
                    if (knockback.getEntityID() == Velocity.mc.player.getEntityId()) {
                        event.cancel();
                        sPacketEntityVelocity.motionX *= (int)0.0f;
                        sPacketEntityVelocity2.motionY *= (int)0.0f;
                        sPacketEntityVelocity3.motionZ *= (int)0.0f;
                    }
                }
                else if (event.getPacket() instanceof SPacketExplosion) {
                    event.cancel();
                    knockback2 = (sPacketExplosion = (SPacketExplosion)event.getPacket());
                    sPacketExplosion.motionX *= 0.0f;
                    sPacketExplosion2.motionY *= 0.0f;
                    sPacketExplosion3.motionZ *= 0.0f;
                }
            }
            return;
        }, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.explosion = new Listener<EventEntity.EventColision>(event -> {
            if (event.get_entity() == Velocity.mc.player) {
                event.cancel();
            }
            return;
        }, (Predicate<EventEntity.EventColision>[])new Predicate[0]);
        this.PushOutOfBlocks = new Listener<EventPlayerPushOutOfBlocks>(event -> {
            if (!this.noPush.getValue(true)) {
                return;
            }
            else {
                event.cancel();
                return;
            }
        }, (Predicate<EventPlayerPushOutOfBlocks>[])new Predicate[0]);
        this.ApplyCollision = new Listener<EventPlayerApplyCollision>(event -> {
            if (!this.noPush.getValue(true)) {
                return;
            }
            else {
                event.cancel();
                return;
            }
        }, (Predicate<EventPlayerApplyCollision>[])new Predicate[0]);
        this.name = "Velocity";
        this.tag = "Velocity";
        this.description = "No kockback";
    }
}
