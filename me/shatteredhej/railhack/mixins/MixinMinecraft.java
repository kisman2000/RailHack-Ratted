//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import org.spongepowered.asm.mixin.injection.*;
import me.shatteredhej.railhack.*;

@Mixin({ Minecraft.class })
public class MixinMinecraft
{
    @Inject(method = { "displayGuiScreen" }, at = { @At("HEAD") })
    private void displayGuiScreen(final GuiScreen guiScreenIn, final CallbackInfo info) {
        final EventGUIScreen guiscreen = new EventGUIScreen(guiScreenIn);
        Event.EVENT_BUS.post(guiscreen);
    }
    
    @Inject(method = { "shutdown" }, at = { @At("HEAD") })
    private void shutdown(final CallbackInfo info) {
        RailHack.get_config_manager().save_settings();
    }
}
