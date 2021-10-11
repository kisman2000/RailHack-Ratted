//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.*;
import java.util.*;
import java.awt.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;

public class ESP extends Module
{
    Setting mode;
    Setting players;
    Setting mobs;
    Setting self;
    Setting items;
    Setting xporbs;
    Setting xpbottles;
    Setting pearl;
    Setting top;
    Setting scale;
    Setting r;
    Setting g;
    Setting b;
    Setting a;
    Setting box_a;
    Setting width;
    Setting rainbow_mode;
    Setting sat;
    Setting brightness;
    
    public ESP() {
        super(Category.Render);
        this.mode = this.register("Mode", "ChamsMode", "Outline", this.combobox(new String[] { "Glow", "Outline", "Wireframe" }));
        this.players = this.register("Players", "ChamsPlayers", true);
        this.mobs = this.register("Mobs", "ChamsMobs", true);
        this.self = this.register("Self", "ChamsSelf", true);
        this.items = this.register("Items", "ChamsItems", true);
        this.xporbs = this.register("Xp Orbs", "ChamsXPO", true);
        this.xpbottles = this.register("Xp Bottles", "ChamsBottles", true);
        this.pearl = this.register("Pearls", "ChamsPearls", true);
        this.top = this.register("Top", "ChamsTop", true);
        this.scale = this.register("Factor", "ChamsFactor", 0.0, -1.0, 1.0);
        this.r = this.register("R", "ChamsR", 255, 0, 255);
        this.g = this.register("G", "ChamsG", 255, 0, 255);
        this.b = this.register("B", "ChamsB", 255, 0, 255);
        this.a = this.register("A", "ChamsA", 100, 0, 255);
        this.box_a = this.register("Box A", "ChamsABox", 100, 0, 255);
        this.width = this.register("Width", "ChamsWdith", 2.0, 0.5, 5.0);
        this.rainbow_mode = this.register("Rainbow", "ChamsRainbow", false);
        this.sat = this.register("Satiation", "ChamsSatiation", 0.8, 0.0, 1.0);
        this.brightness = this.register("Brightness", "ChamsBrightness", 0.8, 0.0, 1.0);
        this.name = "Chams";
        this.tag = "Chams";
    }
    
    public void onUpdate() {
        if (this.rainbow_mode.getValue(true)) {
            this.cycle_rainbow();
        }
        if (this.mode.in("Glow") && this.isActive()) {
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityEnderCrystal) {
                    entity.setGlowing(true);
                }
            }
        }
        else {
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityEnderCrystal) {
                    entity.setGlowing(false);
                }
            }
        }
    }
    
    public void cycle_rainbow() {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int color_rgb_o = Color.HSBtoRGB(tick_color[0], (float)this.sat.getValue(1), (float)this.brightness.getValue(1));
        this.r.setValue(color_rgb_o >> 16 & 0xFF);
        this.g.setValue(color_rgb_o >> 8 & 0xFF);
        this.b.setValue(color_rgb_o & 0xFF);
    }
    
    public void render(final EventRender event) {
        if (this.items.getValue(true)) {
            int i = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityItem && ESP.mc.player.getDistanceSq(entity) < 2500.0) {
                    final Vec3d interp = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB bb = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interp.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interp.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interp.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interp.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(bb.grow((double)this.scale.getValue(1)), this.r.getValue(1) / 255.0f, this.g.getValue(1) / 255.0f, this.b.getValue(1) / 255.0f, this.box_a.getValue(1) / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    WurstplusRenderUtil.drawBlockOutline(bb.grow((double)this.scale.getValue(1)), new Color(this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1)), 1.0f);
                    if (++i >= 50) {
                        break;
                    }
                    continue;
                }
            }
        }
        if (this.xporbs.getValue(true)) {
            int i = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityXPOrb && ESP.mc.player.getDistanceSq(entity) < 2500.0) {
                    final Vec3d interp = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB bb = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interp.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interp.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interp.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interp.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(bb.grow((double)this.scale.getValue(1)), this.r.getValue(1) / 255.0f, this.g.getValue(1) / 255.0f, this.b.getValue(1) / 255.0f, this.box_a.getValue(1) / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    WurstplusRenderUtil.drawBlockOutline(bb.grow((double)this.scale.getValue(1)), new Color(this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1)), 1.0f);
                    if (++i >= 50) {
                        break;
                    }
                    continue;
                }
            }
        }
        if (this.pearl.getValue(true)) {
            int i = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityEnderPearl && ESP.mc.player.getDistanceSq(entity) < 2500.0) {
                    final Vec3d interp = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB bb = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interp.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interp.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interp.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interp.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(bb.grow((double)this.scale.getValue(1)), this.r.getValue(1) / 255.0f, this.g.getValue(1) / 255.0f, this.b.getValue(1) / 255.0f, this.box_a.getValue(1) / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    WurstplusRenderUtil.drawBlockOutline(bb.grow((double)this.scale.getValue(1)), new Color(this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1)), 1.0f);
                    if (++i >= 50) {
                        break;
                    }
                    continue;
                }
            }
        }
        if (this.xpbottles.getValue(true)) {
            int i = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityExpBottle && ESP.mc.player.getDistanceSq(entity) < 2500.0) {
                    final Vec3d interp = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB bb = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interp.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interp.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interp.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interp.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interp.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(bb.grow((double)this.scale.getValue(1)), this.r.getValue(1) / 255.0f, this.g.getValue(1) / 255.0f, this.b.getValue(1) / 255.0f, this.box_a.getValue(1) / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    WurstplusRenderUtil.drawBlockOutline(bb.grow((double)this.scale.getValue(1)), new Color(this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1)), 1.0f);
                    if (++i >= 50) {
                        break;
                    }
                    continue;
                }
            }
        }
    }
    
    public void on_render_model(final EventRenderEntityModel event) {
        if (event.stage != 0 || event.entity == null || (!this.self.getValue(true) && event.entity.equals((Object)ESP.mc.player)) || (!this.players.getValue(true) && event.entity instanceof EntityPlayer) || (!this.mobs.getValue(true) && event.entity instanceof EntityMob)) {
            return;
        }
        final Color color = new Color(this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1));
        final boolean fancyGraphics = ESP.mc.gameSettings.fancyGraphics;
        ESP.mc.gameSettings.fancyGraphics = false;
        final float gamma = ESP.mc.gameSettings.gammaSetting;
        ESP.mc.gameSettings.gammaSetting = 10000.0f;
        if (this.top.getValue(true)) {
            event.modelBase.render(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
        }
        if (this.mode.in("outline")) {
            WurstplusRenderUtil.renderOne((float)this.width.getValue(1));
            event.modelBase.render(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GlStateManager.glLineWidth((float)this.width.getValue(1));
            WurstplusRenderUtil.renderTwo();
            event.modelBase.render(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GlStateManager.glLineWidth((float)this.width.getValue(1));
            WurstplusRenderUtil.renderThree();
            WurstplusRenderUtil.renderFour(color);
            event.modelBase.render(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GlStateManager.glLineWidth((float)this.width.getValue(1));
            WurstplusRenderUtil.renderFive();
        }
        else {
            GL11.glPushMatrix();
            GL11.glPushAttrib(1048575);
            GL11.glPolygonMode(1028, 6913);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)color.getAlpha());
            GlStateManager.glLineWidth((float)this.width.getValue(1));
            event.modelBase.render(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
        if (!this.top.getValue(true)) {
            event.modelBase.render(event.entity, event.limbSwing, event.limbSwingAmount, event.age, event.headYaw, event.headPitch, event.scale);
        }
        try {
            ESP.mc.gameSettings.fancyGraphics = fancyGraphics;
            ESP.mc.gameSettings.gammaSetting = gamma;
        }
        catch (Exception ex) {}
        event.cancel();
    }
}
