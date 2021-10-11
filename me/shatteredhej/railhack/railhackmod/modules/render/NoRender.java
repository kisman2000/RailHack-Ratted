//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoRender extends Module
{
    private static NoRender INSTANCE;
    Setting fire;
    
    public NoRender() {
        super(Category.Render);
        this.fire = this.register("Fire", "NoRenderFire", false);
        this.name = "NoRender";
        this.tag = "NoRender";
        NoRender.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onRenderOverlay(final RenderBlockOverlayEvent event) {
        if (this.fire.getValue(true) && event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) {
            event.setCanceled(true);
        }
    }
    
    public static boolean shouldDisable() {
        return NoRender.INSTANCE != null && NoRender.INSTANCE.isActive();
    }
}
