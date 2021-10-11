//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.shatteredhej.railhack.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ItemRenderer.class })
public abstract class MixinItemRenderer
{
    private boolean injection;
    
    public MixinItemRenderer() {
        this.injection = true;
    }
    
    @Shadow
    public abstract void renderItemInFirstPerson(final AbstractClientPlayer p0, final float p1, final float p2, final EnumHand p3, final float p4, final ItemStack p5, final float p6);
    
    @Inject(method = { "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V" }, at = { @At("HEAD") }, cancellable = true)
    public void renderItemInFirstPersonHook(final AbstractClientPlayer player, final float p_187457_2_, final float p_187457_3_, final EnumHand hand, final float p_187457_5_, final ItemStack stack, final float p_187457_7_, final CallbackInfo info) {
        if (this.injection) {
            info.cancel();
            float xOffset = 0.0f;
            float yOffset = 0.0f;
            this.injection = false;
            if (hand == EnumHand.MAIN_HAND) {
                if (RailHack.get_hack_manager().getModuleWithTag("CustomViewmodel").isActive()) {
                    xOffset = (float)RailHack.getSettingManager().get_setting_with_tag("CustomViewmodel", "FOVMainX").getValue(1);
                    yOffset = (float)RailHack.getSettingManager().get_setting_with_tag("CustomViewmodel", "FOVMainY").getValue(1);
                }
            }
            else if (RailHack.getSettingManager().get_setting_with_tag("CustomViewmodel", "FOVOffset").getValue(true) && RailHack.get_hack_manager().getModuleWithTag("CustomViewmodel").isActive()) {
                xOffset = (float)RailHack.getSettingManager().get_setting_with_tag("CustomViewmodel", "FOVOffsetX").getValue(1);
                yOffset = (float)RailHack.getSettingManager().get_setting_with_tag("CustomViewmodel", "FOVOffsetY").getValue(1);
            }
            this.renderItemInFirstPerson(player, p_187457_2_, p_187457_3_, hand, p_187457_5_ + xOffset, stack, p_187457_7_ + yOffset);
            this.injection = true;
        }
    }
    
    @Redirect(method = { "renderArmFirstPerson" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal = 0))
    public void translateHook(final float x, final float y, final float z) {
        GlStateManager.translate(x + (RailHack.get_hack_manager().getModuleWithTag("CustomViewmodel").isActive() ? ((float)RailHack.getSettingManager().get_setting_with_tag("CustomViewmodel", "FOVMainX").getValue(1)) : 0.0f), y + (RailHack.get_hack_manager().getModuleWithTag("CustomViewmodel").isActive() ? ((float)RailHack.getSettingManager().get_setting_with_tag("CustomViewmodel", "FOVMainX").getValue(1)) : 0.0f), z);
    }
}
