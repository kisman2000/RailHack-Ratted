//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.*;

public class AutoTrap extends Module
{
    Setting place_mode;
    Setting blocks_per_tick;
    Setting rotate;
    Setting chad_mode;
    Setting range;
    Setting swing;
    Setting danielMode;
    private final Vec3d[] offsets_default;
    private final Vec3d[] offsets_face;
    private final Vec3d[] offsets_feet;
    private final Vec3d[] offsets_extra;
    private String last_tick_target_name;
    private int offset_step;
    private int timeout_ticker;
    private int y_level;
    private boolean first_run;
    
    public AutoTrap() {
        super(Category.Combat);
        this.place_mode = this.register("Place Mode", "TrapPlaceMode", "Extra", this.combobox(new String[] { "Extra", "Face", "Normal", "Feet" }));
        this.blocks_per_tick = this.register("Speed", "TrapSpeed", 4, 0, 8);
        this.rotate = this.register("Rotation", "TrapRotation", true);
        this.chad_mode = this.register("Chad Mode", "TrapChadMode", true);
        this.range = this.register("Range", "TrapRange", 4, 1, 6);
        this.swing = this.register("Swing", "TrapSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.danielMode = this.register("Daniel Mode", "TrapDanielMode", false);
        this.offsets_default = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
        this.offsets_face = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
        this.offsets_feet = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0) };
        this.offsets_extra = new Vec3d[] { new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0), new Vec3d(0.0, 4.0, 0.0) };
        this.last_tick_target_name = "";
        this.offset_step = 0;
        this.timeout_ticker = 0;
        this.first_run = true;
        this.name = "AutoTrap";
        this.tag = "AutoTrap";
        this.description = "cover people in obsidian :o";
    }
    
    public void onEnable() {
        this.timeout_ticker = 0;
        this.y_level = (int)Math.round(AutoTrap.mc.player.posY);
        this.first_run = true;
        if (this.find_obi_in_hotbar() == -1) {
            this.setDisable();
        }
    }
    
    public void onUpdate() {
        final int timeout_ticks = 20;
        if (this.timeout_ticker > timeout_ticks && this.chad_mode.getValue(true)) {
            this.timeout_ticker = 0;
            this.setDisable();
            return;
        }
        final EntityPlayer closest_target = this.find_closest_target();
        if (closest_target == null) {
            this.setDisable();
            MessageUtil.toggle_message(this);
            return;
        }
        if (this.chad_mode.getValue(true) && (int)Math.round(AutoTrap.mc.player.posY) != this.y_level) {
            this.setDisable();
            MessageUtil.toggle_message(this);
            return;
        }
        if (this.first_run) {
            this.first_run = false;
            this.last_tick_target_name = closest_target.getName();
        }
        else if (!this.last_tick_target_name.equals(closest_target.getName())) {
            this.last_tick_target_name = closest_target.getName();
            this.offset_step = 0;
        }
        final List<Vec3d> place_targets = new ArrayList<Vec3d>();
        if (this.place_mode.in("Normal")) {
            Collections.addAll(place_targets, this.offsets_default);
        }
        else if (this.place_mode.in("Extra")) {
            Collections.addAll(place_targets, this.offsets_extra);
        }
        else if (this.place_mode.in("Feet")) {
            Collections.addAll(place_targets, this.offsets_feet);
        }
        else {
            Collections.addAll(place_targets, this.offsets_face);
        }
        int blocks_placed = 0;
        while (blocks_placed < this.blocks_per_tick.getValue(1)) {
            if (this.offset_step >= place_targets.size()) {
                this.offset_step = 0;
                break;
            }
            final BlockPos offset_pos = new BlockPos((Vec3d)place_targets.get(this.offset_step));
            final BlockPos target_pos = new BlockPos(closest_target.getPositionVector()).down().add(offset_pos.getX(), offset_pos.getY(), offset_pos.getZ());
            boolean should_try_place = true;
            if (!AutoTrap.mc.world.getBlockState(target_pos).getMaterial().isReplaceable()) {
                should_try_place = false;
            }
            for (final Entity entity : AutoTrap.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(target_pos))) {
                if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb)) {
                    should_try_place = false;
                    break;
                }
            }
            if (should_try_place && BlockUtil.placeBlock(target_pos, this.find_obi_in_hotbar(), this.rotate.getValue(true), this.rotate.getValue(true), this.swing)) {
                ++blocks_placed;
            }
            ++this.offset_step;
        }
        ++this.timeout_ticker;
    }
    
    private int find_obi_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = AutoTrap.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stack.getItem()).getBlock();
                if (block instanceof BlockObsidian) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public EntityPlayer find_closest_target() {
        if (AutoTrap.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        EntityPlayer closestTarget = null;
        for (final EntityPlayer target : AutoTrap.mc.world.playerEntities) {
            if (target == AutoTrap.mc.player) {
                continue;
            }
            if (FriendUtil.isFriend(target.getName())) {
                continue;
            }
            if (!EntityUtil.isLiving((Entity)target)) {
                continue;
            }
            if (target.getHealth() <= 0.0f) {
                continue;
            }
            if (closestTarget != null && AutoTrap.mc.player.getDistance((Entity)target) > AutoTrap.mc.player.getDistance((Entity)closestTarget)) {
                continue;
            }
            closestTarget = target;
        }
        return closestTarget;
    }
}
