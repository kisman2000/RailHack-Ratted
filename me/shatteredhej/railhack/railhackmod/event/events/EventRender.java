//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;

public class EventRender extends EventCancellable
{
    private final ScaledResolution res;
    private final Tessellator tessellator;
    private final Vec3d render_pos;
    
    public EventRender(final Tessellator tessellator, final Vec3d pos) {
        this.res = new ScaledResolution(Minecraft.getMinecraft());
        this.tessellator = tessellator;
        this.render_pos = pos;
    }
    
    public Tessellator get_tessellator() {
        return this.tessellator;
    }
    
    public Vec3d get_render_pos() {
        return this.render_pos;
    }
    
    public BufferBuilder get_buffer_build() {
        return this.tessellator.getBuffer();
    }
    
    public void set_translation(final Vec3d pos) {
        this.get_buffer_build().setTranslation(-pos.x, -pos.y, -pos.z);
    }
    
    public void reset_translation() {
        this.set_translation(this.render_pos);
    }
    
    public double get_screen_width() {
        return this.res.getScaledWidth_double();
    }
    
    public double get_screen_height() {
        return this.res.getScaledHeight_double();
    }
    
    public float getPartialTicks() {
        final float partialTicks = 0.0f;
        final float _partialTicks = 0.0f;
        return _partialTicks;
    }
}
