//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class Australia extends Module
{
    public Australia() {
        super(Category.Render);
        this.name = "Australia";
        this.tag = "Australia";
        this.description = "fire";
    }
    
    public void onUpdate() {
        if (OpenGlHelper.shadersSupported && Australia.mc.getRenderViewEntity() instanceof EntityPlayer) {
            if (Australia.mc.entityRenderer.getShaderGroup() != null) {
                Australia.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
            try {
                Australia.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/flip.json"));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (Australia.mc.entityRenderer.getShaderGroup() != null && Australia.mc.currentScreen == null) {
            Australia.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
        Australia.mc.player.setFire(1);
    }
}
