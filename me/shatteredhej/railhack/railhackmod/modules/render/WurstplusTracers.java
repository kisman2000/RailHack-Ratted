//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import java.awt.*;

public class WurstplusTracers extends Module
{
    Setting friends;
    Setting range;
    Setting width;
    Setting offset;
    
    public WurstplusTracers() {
        super(Category.Render);
        this.friends = this.register("Friends", "TracerFriends", false);
        this.range = this.register("Range", "TracerRange", 50, 0, 250);
        this.width = this.register("Width", "TracerWidth", 1.0, 0.0, 5.0);
        this.offset = this.register("Offset", "TracerOffset", 0.0, -4.0, 4.0);
        this.name = "Tracers";
        this.tag = "Tracers";
        this.description = "DRAWS LINES";
    }
    
    public void render(final EventRender event) {
        if (WurstplusTracers.mc.world == null) {
            return;
        }
        GlStateManager.pushMatrix();
        final float[][] colour = new float[1][1];
        EntityPlayer player;
        final Object o;
        WurstplusTracers.mc.world.loadedEntityList.forEach(entity -> {
            if (!(entity instanceof EntityPlayer) || entity == WurstplusTracers.mc.player) {
                return;
            }
            else {
                player = entity;
                if (WurstplusTracers.mc.player.getDistance((Entity)player) > this.range.getValue(1)) {
                    return;
                }
                else if (FriendUtil.isFriend(player.getName()) && !this.friends.getValue(true)) {
                    return;
                }
                else {
                    o[0] = this.getColorByDistance((Entity)player);
                    this.drawLineToEntity((Entity)player, o[0][0], o[0][1], o[0][2], o[0][3]);
                    return;
                }
            }
        });
        GlStateManager.popMatrix();
    }
    
    public double interpolate(final double now, final double then) {
        return then + (now - then) * WurstplusTracers.mc.getRenderPartialTicks();
    }
    
    public double[] interpolate(final Entity entity) {
        final double posX = this.interpolate(entity.posX, entity.lastTickPosX) - WurstplusTracers.mc.getRenderManager().renderPosX;
        final double posY = this.interpolate(entity.posY, entity.lastTickPosY) - WurstplusTracers.mc.getRenderManager().renderPosY;
        final double posZ = this.interpolate(entity.posZ, entity.lastTickPosZ) - WurstplusTracers.mc.getRenderManager().renderPosZ;
        return new double[] { posX, posY, posZ };
    }
    
    public void drawLineToEntity(final Entity e, final float red, final float green, final float blue, final float opacity) {
        final double[] xyz = this.interpolate(e);
        this.drawLine(xyz[0], xyz[1], xyz[2], e.height, red, green, blue, opacity);
    }
    
    public void drawLine(final double posx, final double posy, final double posz, final double up, final float red, final float green, final float blue, final float opacity) {
        final Vec3d eyes = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-(float)Math.toRadians(WurstplusTracers.mc.player.rotationPitch)).rotateYaw(-(float)Math.toRadians(WurstplusTracers.mc.player.rotationYaw));
        this.drawLineFromPosToPos(eyes.x, eyes.y + WurstplusTracers.mc.player.getEyeHeight() + (float)this.offset.getValue(1), eyes.z, posx, posy, posz, up, red, green, blue, opacity);
    }
    
    public void drawLineFromPosToPos(final double posx, final double posy, final double posz, final double posx2, final double posy2, final double posz2, final double up, final float red, final float green, final float blue, final float opacity) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth((float)this.width.getValue(1));
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4f(red, green, blue, opacity);
        GlStateManager.disableLighting();
        GL11.glLoadIdentity();
        WurstplusTracers.mc.entityRenderer.orientCamera(WurstplusTracers.mc.getRenderPartialTicks());
        GL11.glBegin(1);
        GL11.glVertex3d(posx, posy, posz);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glVertex3d(posx2, posy2, posz2);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glColor3d(1.0, 1.0, 1.0);
        GlStateManager.enableLighting();
    }
    
    public float[] getColorByDistance(final Entity entity) {
        if (entity instanceof EntityPlayer && FriendUtil.isFriend(entity.getName())) {
            return new float[] { 0.0f, 0.5f, 1.0f, 1.0f };
        }
        final Color col = new Color(Color.HSBtoRGB((float)(Math.max(0.0, Math.min(WurstplusTracers.mc.player.getDistanceSq(entity), 2500.0) / 2500.0) / 3.0), 1.0f, 0.8f) | 0xFF000000);
        return new float[] { col.getRed() / 255.0f, col.getGreen() / 255.0f, col.getBlue() / 255.0f, 1.0f };
    }
}
