//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.init.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.*;
import java.util.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class WurstplusVoidESP extends Module
{
    Setting void_radius;
    public final List<BlockPos> void_blocks;
    private final ICamera camera;
    
    public WurstplusVoidESP() {
        super(Category.Render);
        this.void_radius = this.register("Range", "VoidESPRange", 6, 1, 10);
        this.void_blocks = new ArrayList<BlockPos>();
        this.camera = (ICamera)new Frustum();
        this.name = "VoidESP";
        this.tag = "VoidESP";
        this.description = "OH FUCK A DEEP HOLE";
    }
    
    public void onUpdate() {
        if (WurstplusVoidESP.mc.player == null) {
            return;
        }
        this.void_blocks.clear();
        final Vec3i player_pos = new Vec3i(WurstplusVoidESP.mc.player.posX, WurstplusVoidESP.mc.player.posY, WurstplusVoidESP.mc.player.posZ);
        for (int x = player_pos.getX() - this.void_radius.getValue(1); x < player_pos.getX() + this.void_radius.getValue(1); ++x) {
            for (int z = player_pos.getZ() - this.void_radius.getValue(1); z < player_pos.getZ() + this.void_radius.getValue(1); ++z) {
                for (int y = player_pos.getY() + this.void_radius.getValue(1); y > player_pos.getY() - this.void_radius.getValue(1); --y) {
                    final BlockPos blockPos = new BlockPos(x, y, z);
                    if (this.is_void_hole(blockPos)) {
                        this.void_blocks.add(blockPos);
                    }
                }
            }
        }
    }
    
    public boolean is_void_hole(final BlockPos blockPos) {
        return blockPos.getY() == 0 && WurstplusVoidESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR;
    }
    
    public void render(final EventRender event) {
        final int r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final AxisAlignedBB bb;
        new ArrayList(this.void_blocks).forEach(pos -> {
            bb = new AxisAlignedBB(pos.getX() - WurstplusVoidESP.mc.getRenderManager().viewerPosX, pos.getY() - WurstplusVoidESP.mc.getRenderManager().viewerPosY, pos.getZ() - WurstplusVoidESP.mc.getRenderManager().viewerPosZ, pos.getX() + 1 - WurstplusVoidESP.mc.getRenderManager().viewerPosX, pos.getY() + 1 - WurstplusVoidESP.mc.getRenderManager().viewerPosY, pos.getZ() + 1 - WurstplusVoidESP.mc.getRenderManager().viewerPosZ);
            this.camera.setPosition(WurstplusVoidESP.mc.getRenderViewEntity().posX, WurstplusVoidESP.mc.getRenderViewEntity().posY, WurstplusVoidESP.mc.getRenderViewEntity().posZ);
            if (this.camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + WurstplusVoidESP.mc.getRenderManager().viewerPosX, bb.minY + WurstplusVoidESP.mc.getRenderManager().viewerPosY, bb.minZ + WurstplusVoidESP.mc.getRenderManager().viewerPosZ, bb.maxX + WurstplusVoidESP.mc.getRenderManager().viewerPosX, bb.maxY + WurstplusVoidESP.mc.getRenderManager().viewerPosY, bb.maxZ + WurstplusVoidESP.mc.getRenderManager().viewerPosZ))) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask(false);
                GL11.glEnable(2848);
                GL11.glHint(3154, 4354);
                GL11.glLineWidth(1.5f);
                RenderGlobal.drawBoundingBox(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ, 255.0f, 20.0f, 30.0f, 0.5f);
                RenderGlobal.renderFilledBox(bb.minX, bb.minY, bb.minZ, bb.maxX, bb.maxY, bb.maxZ, 255.0f, 20.0f, 30.0f, 0.22f);
                GL11.glDisable(2848);
                GlStateManager.depthMask(true);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        });
    }
}
