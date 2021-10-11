//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.client.network.*;
import org.spongepowered.asm.mixin.*;
import javax.annotation.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.util.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ AbstractClientPlayer.class })
public abstract class MixinAbstractClientPlayer
{
    @Shadow
    @Nullable
    protected abstract NetworkPlayerInfo getPlayerInfo();
    
    @Inject(method = { "getLocationCape" }, at = { @At("HEAD") }, cancellable = true)
    public void getLocationCape(final CallbackInfoReturnable<ResourceLocation> callbackInfoReturnable) {
        if (RailHack.get_hack_manager().getModuleWithTag("Capes").isActive()) {
            final NetworkPlayerInfo info = this.getPlayerInfo();
            assert info != null;
            if (!CapeUtil.is_uuid_valid(info.getGameProfile().getId())) {
                return;
            }
            final ResourceLocation r = new ResourceLocation("custom/cape.png");
            callbackInfoReturnable.setReturnValue((Object)r);
        }
    }
}
