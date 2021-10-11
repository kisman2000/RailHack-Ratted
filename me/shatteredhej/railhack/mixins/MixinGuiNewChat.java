//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.mixins;

import org.spongepowered.asm.mixin.*;
import me.shatteredhej.railhack.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiNewChat.class })
public class MixinGuiNewChat
{
    @Redirect(method = { "drawChat" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V", ordinal = 0))
    private void overrideChatBackgroundColour(final int left, final int top, final int right, final int bottom, final int color) {
        if (!RailHack.get_hack_manager().getModuleWithTag("ClearChatbox").isActive()) {
            Gui.drawRect(left, top, right, bottom, color);
        }
    }
}
