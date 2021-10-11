//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.renderer.*;
import java.util.function.*;

public class WurstplusAntifog extends Module
{
    @EventHandler
    private Listener<EventSetupFog> setup_fog;
    
    public WurstplusAntifog() {
        super(Category.Render);
        this.setup_fog = new Listener<EventSetupFog>(event -> {
            event.cancel();
            WurstplusAntifog.mc.entityRenderer.setupFogColor(false);
            GlStateManager.glNormal3f(0.0f, -1.0f, 0.0f);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.colorMaterial(1028, 4608);
            return;
        }, (Predicate<EventSetupFog>[])new Predicate[0]);
        this.name = "AntiFog";
        this.tag = "AntiFog";
        this.description = "see even more";
    }
}
