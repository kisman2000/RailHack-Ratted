//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.mixins;

import org.spongepowered.asm.mixin.*;
import io.netty.channel.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    private void receive(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callback) {
        final EventPacket event_packet = new EventPacket.ReceivePacket(packet);
        Event.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void send(final Packet<?> packet, final CallbackInfo callback) {
        final EventPacket event_packet = new EventPacket.SendPacket(packet);
        Event.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "exceptionCaught" }, at = { @At("HEAD") }, cancellable = true)
    private void exception(final ChannelHandlerContext exc, final Throwable exc_, final CallbackInfo callback) {
        if (exc_ instanceof Exception) {
            callback.cancel();
        }
    }
}
