//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;

@Mixin({ EntityPlayerSP.class })
public class MixinEntitySP extends MixinEntity
{
    @Inject(method = { "move" }, at = { @At("HEAD") }, cancellable = true)
    private void move(final MoverType type, final double x, final double y, final double z, final CallbackInfo info) {
        final EventMove event = new EventMove(type, x, y, z);
        Event.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            super.move(type, event.get_x(), event.get_y(), event.get_z());
            info.cancel();
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    public void OnPreUpdateWalkingPlayer(final CallbackInfo p_Info) {
        final EventMotionUpdate l_Event = new EventMotionUpdate(0);
        Event.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") }, cancellable = true)
    public void OnPostUpdateWalkingPlayer(final CallbackInfo p_Info) {
        final EventMotionUpdate l_Event = new EventMotionUpdate(1);
        Event.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "swingArm" }, at = { @At("RETURN") }, cancellable = true)
    public void swingArm(final EnumHand p_Hand, final CallbackInfo p_Info) {
        final EventSwing l_Event = new EventSwing(p_Hand);
        Event.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "pushOutOfBlocks(DDD)Z" }, at = { @At("HEAD") }, cancellable = true)
    public void pushOutOfBlocks(final double x, final double y, final double z, final CallbackInfoReturnable<Boolean> callbackInfo) {
        final EventPlayerPushOutOfBlocks l_Event = new EventPlayerPushOutOfBlocks(x, y, z);
        Event.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            callbackInfo.setReturnValue((Object)false);
        }
    }
}
