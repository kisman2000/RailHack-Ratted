//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.mixins;

import org.spongepowered.asm.mixin.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;

@Mixin({ Entity.class })
public class MixinEntity
{
    @Shadow
    public double motionX;
    @Shadow
    public double motionY;
    @Shadow
    public double motionZ;
    
    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void velocity(final Entity entity, final double x, final double y, final double z) {
        final EventEntity.EventColision event = new EventEntity.EventColision(entity, x, y, z);
        Event.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            return;
        }
        entity.motionX += x;
        entity.motionY += y;
        entity.motionZ += z;
        entity.isAirBorne = true;
    }
    
    @Shadow
    public void move(final MoverType type, final double x, final double y, final double z) {
    }
}
