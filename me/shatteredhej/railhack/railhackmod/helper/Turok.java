//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.helper;

import me.shatteredhej.railhack.railhackmod.helper.task.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;

public class Turok
{
    private String tag;
    private Font font_manager;
    
    public Turok(final String tag) {
        this.tag = tag;
    }
    
    public void resize(final int x, final int y, final float size) {
        GL.resize(x, y, size);
    }
    
    public void resize(final int x, final int y, final float size, final String tag) {
        GL.resize(x, y, size, "end");
    }
    
    public Font get_font_manager() {
        return this.font_manager;
    }
}
