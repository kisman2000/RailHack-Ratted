//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ PlayerControllerMP.class })
public class MixinPlayerControllerMP
{
    @Redirect(method = { "onPlayerDamageBlock" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
    private float onPlayerDamageBlockSpeed(final IBlockState state, final EntityPlayer player, final World world, final BlockPos pos) {
        return state.getPlayerRelativeBlockHardness(player, world, pos) * (RailHack.get_event_handler().get_tick_rate() / 20.0f);
    }
    
    @Inject(method = { "onPlayerDamageBlock" }, at = { @At("HEAD") }, cancellable = true)
    public void onPlayerDamageBlock(final BlockPos posBlock, final EnumFacing directionFacing, final CallbackInfoReturnable<Boolean> info) {
        final EventDamageBlock event_packet = new EventDamageBlock(posBlock, directionFacing);
        Event.EVENT_BUS.post(event_packet);
        if (event_packet.isCancelled()) {
            info.setReturnValue((Object)false);
            info.cancel();
        }
        final EventBlock event = new EventBlock(4, posBlock, directionFacing);
        Event.EVENT_BUS.post(event);
    }
    
    @Inject(method = { "clickBlock" }, at = { @At("HEAD") }, cancellable = true)
    private void clickBlockHook(final BlockPos pos, final EnumFacing face, final CallbackInfoReturnable<Boolean> info) {
        final EventBlock event = new EventBlock(3, pos, face);
        Event.EVENT_BUS.post(event);
    }
}
