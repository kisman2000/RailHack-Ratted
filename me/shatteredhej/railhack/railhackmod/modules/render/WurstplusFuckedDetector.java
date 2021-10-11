//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.*;
import net.minecraft.init.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;

public class WurstplusFuckedDetector extends Module
{
    Setting draw_own;
    Setting draw_friends;
    Setting render_mode;
    Setting r;
    Setting g;
    Setting b;
    Setting a;
    private boolean solid;
    private boolean outline;
    public Set<BlockPos> fucked_players;
    
    public WurstplusFuckedDetector() {
        super(Category.Render);
        this.draw_own = this.register("Draw Own", "FuckedDrawOwn", false);
        this.draw_friends = this.register("Draw Friends", "FuckedDrawFriends", false);
        this.render_mode = this.register("Render Mode", "FuckedRenderMode", "Pretty", this.combobox(new String[] { "Pretty", "Solid", "Outline" }));
        this.r = this.register("R", "FuckedR", 255, 0, 255);
        this.g = this.register("G", "FuckedG", 255, 0, 255);
        this.b = this.register("B", "FuckedB", 255, 0, 255);
        this.a = this.register("A", "FuckedA", 100, 0, 255);
        this.fucked_players = new HashSet<BlockPos>();
        this.name = "FuckedDetector";
        this.tag = "FuckedDetector";
        this.description = "see if people are hecked";
    }
    
    protected void onEnable() {
        this.fucked_players.clear();
    }
    
    public void onUpdate() {
        if (WurstplusFuckedDetector.mc.world == null) {
            return;
        }
        this.set_fucked_players();
    }
    
    public void set_fucked_players() {
        this.fucked_players.clear();
        for (final EntityPlayer player : WurstplusFuckedDetector.mc.world.playerEntities) {
            if (EntityUtil.isLiving((Entity)player)) {
                if (player.getHealth() <= 0.0f) {
                    continue;
                }
                if (!this.is_fucked(player)) {
                    continue;
                }
                if (FriendUtil.isFriend(player.getName()) && !this.draw_friends.getValue(true)) {
                    continue;
                }
                if (player == WurstplusFuckedDetector.mc.player && !this.draw_own.getValue(true)) {
                    continue;
                }
                this.fucked_players.add(new BlockPos(player.posX, player.posY, player.posZ));
            }
        }
    }
    
    public boolean is_fucked(final EntityPlayer player) {
        final BlockPos pos = new BlockPos(player.posX, player.posY - 1.0, player.posZ);
        return CrystalUtil.canPlaceCrystal(pos.south()) || (CrystalUtil.canPlaceCrystal(pos.south().south()) && WurstplusFuckedDetector.mc.world.getBlockState(pos.add(0, 1, 1)).getBlock() == Blocks.AIR) || (CrystalUtil.canPlaceCrystal(pos.east()) || (CrystalUtil.canPlaceCrystal(pos.east().east()) && WurstplusFuckedDetector.mc.world.getBlockState(pos.add(1, 1, 0)).getBlock() == Blocks.AIR)) || (CrystalUtil.canPlaceCrystal(pos.west()) || (CrystalUtil.canPlaceCrystal(pos.west().west()) && WurstplusFuckedDetector.mc.world.getBlockState(pos.add(-1, 1, 0)).getBlock() == Blocks.AIR)) || (CrystalUtil.canPlaceCrystal(pos.north()) || (CrystalUtil.canPlaceCrystal(pos.north().north()) && WurstplusFuckedDetector.mc.world.getBlockState(pos.add(0, 1, -1)).getBlock() == Blocks.AIR));
    }
    
    public void render(final EventRender event) {
        if (this.render_mode.in("Pretty")) {
            this.outline = true;
            this.solid = true;
        }
        if (this.render_mode.in("Solid")) {
            this.outline = false;
            this.solid = true;
        }
        if (this.render_mode.in("Outline")) {
            this.outline = true;
            this.solid = false;
        }
        for (final BlockPos render_block : this.fucked_players) {
            if (render_block == null) {
                return;
            }
            if (this.solid) {
                RenderHelp.prepare("quads");
                RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)render_block.getX(), (float)render_block.getY(), (float)render_block.getZ(), 1.0f, 1.0f, 1.0f, this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1), "all");
                RenderHelp.release();
            }
            if (!this.outline) {
                continue;
            }
            RenderHelp.prepare("lines");
            RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)render_block.getX(), (float)render_block.getY(), (float)render_block.getZ(), 1.0f, 1.0f, 1.0f, this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1), "all");
            RenderHelp.release();
        }
    }
}
