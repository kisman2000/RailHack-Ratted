//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.awt.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.block.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;

public class WurstplusHoleESP extends Module
{
    Setting mode;
    Setting off_set;
    Setting range;
    Setting hide_own;
    Setting bedrock_view;
    Setting bedrock_enable;
    Setting rgb_b;
    Setting rb;
    Setting gb;
    Setting bb;
    Setting ab;
    Setting obsidian_view;
    Setting obsidian_enable;
    Setting rgb_o;
    Setting ro;
    Setting go;
    Setting bo;
    Setting ao;
    Setting dual_view;
    Setting dual_enable;
    Setting line_a;
    ArrayList<WurstplusPair<BlockPos, Boolean>> holes;
    boolean outline;
    boolean solid;
    boolean docking;
    int color_r_o;
    int color_g_o;
    int color_b_o;
    int color_r_b;
    int color_g_b;
    int color_b_b;
    int color_r;
    int color_g;
    int color_b;
    int color_a;
    int safe_sides;
    
    public WurstplusHoleESP() {
        super(Category.Render);
        this.mode = this.register("Mode", "HoleESPMode", "Pretty", this.combobox(new String[] { "Pretty", "Solid", "Outline" }));
        this.off_set = this.register("Height", "HoleESPOffSetSide", 0.2, 0.0, 1.0);
        this.range = this.register("Range", "HoleESPRange", 6, 1, 12);
        this.hide_own = this.register("Hide Own", "HoleESPHideOwn", true);
        this.bedrock_view = this.register("info", "HoleESPbedrock", "Bedrock");
        this.bedrock_enable = this.register("Bedrock Holes", "HoleESPBedrockHoles", true);
        this.rgb_b = this.register("Bedrock Rainbow", "HoleColorRGBEffectB", true);
        this.rb = this.register("R", "HoleESPRb", 0, 0, 255);
        this.gb = this.register("G", "HoleESPGb", 255, 0, 255);
        this.bb = this.register("B", "HoleESPBb", 0, 0, 255);
        this.ab = this.register("A", "HoleESPAb", 50, 0, 255);
        this.obsidian_view = this.register("info", "HoleESPObsidian", "Obsidian");
        this.obsidian_enable = this.register("Obsidian Holes", "HoleESPObsidianHoles", true);
        this.rgb_o = this.register("Obsidian Rainbow", "HoleColorRGBEffectO", true);
        this.ro = this.register("R", "HoleESPRo", 255, 0, 255);
        this.go = this.register("G", "HoleESPGo", 0, 0, 255);
        this.bo = this.register("B", "HoleESPBo", 0, 0, 255);
        this.ao = this.register("A", "HoleESPAo", 50, 0, 255);
        this.dual_view = this.register("info", "HoleESPDual", "Double");
        this.dual_enable = this.register("Dual Holes", "HoleESPTwoHoles", false);
        this.line_a = this.register("Outline A", "HoleESPLineOutlineA", 255, 0, 255);
        this.holes = new ArrayList<WurstplusPair<BlockPos, Boolean>>();
        this.outline = false;
        this.solid = false;
        this.docking = false;
        this.name = "HoleESP";
        this.tag = "HoleESP";
    }
    
    public void onUpdate() {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int color_rgb_o = Color.HSBtoRGB(tick_color[0], 1.0f, 1.0f);
        final int color_rgb_b = Color.HSBtoRGB(tick_color[0], 1.0f, 1.0f);
        if (this.rgb_o.getValue(true)) {
            this.color_r_o = (color_rgb_o >> 16 & 0xFF);
            this.color_g_o = (color_rgb_o >> 8 & 0xFF);
            this.color_b_o = (color_rgb_o & 0xFF);
            this.ro.setValue(this.color_r_o);
            this.go.setValue(this.color_g_o);
            this.bo.setValue(this.color_b_o);
        }
        else {
            this.color_r_o = this.ro.getValue(1);
            this.color_g_o = this.go.getValue(2);
            this.color_b_o = this.bo.getValue(3);
        }
        if (this.rgb_b.getValue(true)) {
            this.color_r_b = (color_rgb_b >> 16 & 0xFF);
            this.color_g_b = (color_rgb_b >> 8 & 0xFF);
            this.color_b_b = (color_rgb_b & 0xFF);
            this.rb.setValue(this.color_r_b);
            this.gb.setValue(this.color_g_b);
            this.bb.setValue(this.color_b_b);
        }
        else {
            this.color_r_b = this.rb.getValue(1);
            this.color_g_b = this.gb.getValue(2);
            this.color_b_b = this.bb.getValue(3);
        }
        this.color_r_b = this.rb.getValue(1);
        this.color_g_b = this.gb.getValue(1);
        this.color_b_b = this.bb.getValue(1);
        this.color_r_o = this.ro.getValue(1);
        this.color_g_o = this.go.getValue(1);
        this.color_b_o = this.bo.getValue(1);
        this.holes.clear();
        if (WurstplusHoleESP.mc.player != null || WurstplusHoleESP.mc.world != null) {
            if (this.mode.in("Pretty")) {
                this.outline = true;
                this.solid = true;
            }
            if (this.mode.in("Solid")) {
                this.outline = false;
                this.solid = true;
            }
            if (this.mode.in("Outline")) {
                this.outline = true;
                this.solid = false;
            }
            final int colapso_range = (int)Math.ceil(this.range.getValue(1));
            final List<BlockPos> spheres = this.sphere(this.playerAsBlockpos(), (float)colapso_range, colapso_range);
            for (final BlockPos pos : spheres) {
                if (!WurstplusHoleESP.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                    continue;
                }
                if (!WurstplusHoleESP.mc.world.getBlockState(pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                    continue;
                }
                if (!WurstplusHoleESP.mc.world.getBlockState(pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                    continue;
                }
                boolean possible = true;
                this.safe_sides = 0;
                int air_orient = -1;
                int counter = 0;
                for (final BlockPos seems_blocks : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
                    final Block block = WurstplusHoleESP.mc.world.getBlockState(pos.add((Vec3i)seems_blocks)).getBlock();
                    if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
                        possible = false;
                        if (counter == 0) {
                            break;
                        }
                        if (air_orient != -1) {
                            air_orient = -1;
                            break;
                        }
                        if (!block.equals(Blocks.AIR)) {
                            break;
                        }
                        air_orient = counter;
                    }
                    if (block == Blocks.BEDROCK) {
                        ++this.safe_sides;
                    }
                    ++counter;
                }
                if (possible) {
                    if (this.safe_sides == 5) {
                        if (!this.bedrock_enable.getValue(true)) {
                            continue;
                        }
                        this.holes.add(new WurstplusPair<BlockPos, Boolean>(pos, true));
                    }
                    else {
                        if (!this.obsidian_enable.getValue(true)) {
                            continue;
                        }
                        this.holes.add(new WurstplusPair<BlockPos, Boolean>(pos, false));
                    }
                }
                else {
                    if (!this.dual_enable.getValue(true)) {
                        continue;
                    }
                    if (air_orient < 0) {
                        continue;
                    }
                    final BlockPos second_pos = pos.add((Vec3i)orientConv(air_orient));
                    if (!this.checkDual(second_pos, air_orient)) {
                        continue;
                    }
                    final boolean low_ceiling_hole = WurstplusHoleESP.mc.world.getBlockState(second_pos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) && !WurstplusHoleESP.mc.world.getBlockState(second_pos.add(0, 2, 0)).getBlock().equals(Blocks.AIR);
                    if (this.safe_sides == 8) {
                        this.holes.add(new WurstplusPair<BlockPos, Boolean>(pos, true));
                        if (!low_ceiling_hole) {
                            continue;
                        }
                        this.holes.add(new WurstplusPair<BlockPos, Boolean>(second_pos, true));
                    }
                    else {
                        this.holes.add(new WurstplusPair<BlockPos, Boolean>(pos, false));
                        if (!low_ceiling_hole) {
                            continue;
                        }
                        this.holes.add(new WurstplusPair<BlockPos, Boolean>(second_pos, false));
                    }
                }
            }
        }
    }
    
    private static BlockPos orientConv(final int orient_count) {
        BlockPos converted = null;
        switch (orient_count) {
            case 0: {
                converted = new BlockPos(0, -1, 0);
                break;
            }
            case 1: {
                converted = new BlockPos(0, 0, -1);
                break;
            }
            case 2: {
                converted = new BlockPos(1, 0, 0);
                break;
            }
            case 3: {
                converted = new BlockPos(0, 0, 1);
                break;
            }
            case 4: {
                converted = new BlockPos(-1, 0, 0);
                break;
            }
            case 5: {
                converted = new BlockPos(0, 1, 0);
                break;
            }
        }
        return converted;
    }
    
    private static int oppositeIntOrient(final int orient_count) {
        int opposite = 0;
        switch (orient_count) {
            case 0: {
                opposite = 5;
                break;
            }
            case 1: {
                opposite = 3;
                break;
            }
            case 2: {
                opposite = 4;
                break;
            }
            case 3: {
                opposite = 1;
                break;
            }
            case 4: {
                opposite = 2;
                break;
            }
        }
        return opposite;
    }
    
    private boolean checkDual(final BlockPos second_block, final int counter) {
        int i = -1;
        for (final BlockPos seems_blocks : new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) }) {
            ++i;
            if (counter != oppositeIntOrient(i)) {
                final Block block = WurstplusHoleESP.mc.world.getBlockState(second_block.add((Vec3i)seems_blocks)).getBlock();
                if (block != Blocks.BEDROCK && block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
                    return false;
                }
                if (block == Blocks.BEDROCK) {
                    ++this.safe_sides;
                }
            }
        }
        return true;
    }
    
    public void render(final EventRender event) {
        float off_set_h = 0.0f;
        if (!this.holes.isEmpty()) {
            off_set_h = (float)this.off_set.getValue(1.0);
            for (final WurstplusPair<BlockPos, Boolean> hole : this.holes) {
                if (hole.getValue()) {
                    this.color_r = this.color_r_b;
                    this.color_g = this.color_g_b;
                    this.color_b = this.color_b_b;
                    this.color_a = this.ab.getValue(1);
                }
                else {
                    if (hole.getValue()) {
                        continue;
                    }
                    this.color_r = this.color_r_o;
                    this.color_g = this.color_g_o;
                    this.color_b = this.color_b_o;
                    this.color_a = this.ao.getValue(1);
                }
                if (this.hide_own.getValue(true) && hole.getKey().equals((Object)new BlockPos(WurstplusHoleESP.mc.player.posX, WurstplusHoleESP.mc.player.posY, WurstplusHoleESP.mc.player.posZ))) {
                    continue;
                }
                if (this.solid) {
                    RenderHelp.prepare("quads");
                    RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)hole.getKey().getX(), (float)hole.getKey().getY(), (float)hole.getKey().getZ(), 1.0f, off_set_h, 1.0f, this.color_r, this.color_g, this.color_b, this.color_a, "all");
                    RenderHelp.release();
                }
                if (!this.outline) {
                    continue;
                }
                RenderHelp.prepare("lines");
                RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)hole.getKey().getX(), (float)hole.getKey().getY(), (float)hole.getKey().getZ(), 1.0f, off_set_h, 1.0f, this.color_r, this.color_g, this.color_b, this.line_a.getValue(1), "all");
                RenderHelp.release();
            }
        }
    }
    
    public List<BlockPos> sphere(final BlockPos pos, final float r, final int h) {
        final boolean hollow = false;
        final boolean sphere = true;
        final int plus_y = 0;
        final List<BlockPos> sphere_block = new ArrayList<BlockPos>();
        final int cx = pos.getX();
        final int cy = pos.getY();
        final int cz = pos.getZ();
        for (int x = cx - (int)r; x <= cx + r; ++x) {
            for (int z = cz - (int)r; z <= cz + r; ++z) {
                for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        final BlockPos spheres = new BlockPos(x, y + plus_y, z);
                        sphere_block.add(spheres);
                    }
                }
            }
        }
        return sphere_block;
    }
    
    public BlockPos playerAsBlockpos() {
        return new BlockPos(Math.floor(WurstplusHoleESP.mc.player.posX), Math.floor(WurstplusHoleESP.mc.player.posY), Math.floor(WurstplusHoleESP.mc.player.posZ));
    }
}
