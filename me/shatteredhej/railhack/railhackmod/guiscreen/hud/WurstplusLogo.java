//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import me.shatteredhej.railhack.railhackmod.util.*;

public class WurstplusLogo extends WurstplusPinnable
{
    ResourceLocation r;
    
    public WurstplusLogo() {
        super("Logo", "Logo", 1.0f, 0, 0);
        this.r = new ResourceLocation("custom/wurst.png");
    }
    
    @Override
    public void render() {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.get_x(), (float)this.get_y(), 0.0f);
        WurstplusTextureHelper.drawTexture(this.r, (float)this.get_x(), (float)this.get_y(), 100.0f, 25.0f);
        GL11.glPopMatrix();
        this.set_width(100);
        this.set_height(25);
    }
}
