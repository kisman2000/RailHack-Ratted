//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import net.minecraft.util.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.entity.*;
import me.shatteredhej.railhack.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import java.util.stream.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.*;
import net.minecraft.nbt.*;

public class Aura extends Module
{
    Setting mode;
    Setting autoSwitch;
    Setting player;
    Setting hostile;
    Setting sword;
    Setting sync_tps;
    Setting range;
    Setting delay;
    boolean start_verify;
    EnumHand actual_hand;
    double tick;
    
    public Aura() {
        super(Category.Combat);
        this.mode = this.register("Mode", "KillAuraMode", "A32k", this.combobox(new String[] { "A32k", "Normal" }));
        this.autoSwitch = this.register("AutoSwitch", "KillAuraSwitch", false);
        this.player = this.register("Player", "KillAuraPlayer", true);
        this.hostile = this.register("Hostile", "KillAuraHostile", false);
        this.sword = this.register("Sword", "KillAuraSword", true);
        this.sync_tps = this.register("Sync TPS", "KillAuraSyncTps", true);
        this.range = this.register("Range", "KillAuraRange", 5.0, 0.5, 6.0);
        this.delay = this.register("Delay", "KillAuraDelay", 2, 0, 10);
        this.start_verify = true;
        this.actual_hand = EnumHand.MAIN_HAND;
        this.tick = 0.0;
        this.name = "Aura";
        this.tag = "Aura";
        this.description = "To able hit enemies in a range.";
    }
    
    protected void onEnable() {
        this.tick = 0.0;
    }
    
    public void onUpdate() {
        if (Aura.mc.player != null && Aura.mc.world != null) {
            ++this.tick;
            if (Aura.mc.player.isDead | Aura.mc.player.getHealth() <= 0.0f) {
                return;
            }
            for (final Entity entity : Aura.mc.world.getLoadedEntityList()) {
                if (entity instanceof EntityOtherPlayerMP && Aura.mc.player.getDistance(entity) <= this.range.getValue(1) && this.autoSwitch.getValue(true)) {
                    Aura.mc.player.inventory.currentItem = this.findSwordInHotbar();
                }
            }
            if (this.mode.in("Normal")) {
                if (!(Aura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && this.sword.getValue(true)) {
                    this.start_verify = false;
                }
                else if (Aura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && this.sword.getValue(true)) {
                    this.start_verify = true;
                }
                else if (!this.sword.getValue(true)) {
                    this.start_verify = true;
                }
                final Entity entity2 = this.find_entity();
                if (entity2 != null && this.start_verify) {
                    final float tick_to_hit = 20.0f - RailHack.get_event_handler().get_tick_rate();
                    final boolean is_possible_attack = Aura.mc.player.getCooledAttackStrength(this.sync_tps.getValue(true) ? (-tick_to_hit) : 0.0f) >= 1.0f;
                    if (is_possible_attack) {
                        this.attack_entity(entity2);
                    }
                }
            }
            else {
                if (!(Aura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword)) {
                    return;
                }
                if (this.tick < this.delay.getValue(1)) {
                    return;
                }
                this.tick = 0.0;
                final Entity entity2 = this.find_entity();
                if (entity2 != null) {
                    this.attack_entity(entity2);
                }
            }
        }
    }
    
    public void attack_entity(final Entity entity) {
        if (this.mode.in("A32k")) {
            int newSlot = -1;
            for (int i = 0; i < 9; ++i) {
                final ItemStack stack = Aura.mc.player.inventory.getStackInSlot(i);
                if (stack != ItemStack.EMPTY) {
                    if (this.checkSharpness(stack)) {
                        newSlot = i;
                        break;
                    }
                }
            }
            if (newSlot != -1) {
                Aura.mc.player.inventory.currentItem = newSlot;
            }
        }
        final ItemStack off_hand_item = Aura.mc.player.getHeldItemOffhand();
        if (off_hand_item.getItem() == Items.SHIELD) {
            Aura.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Aura.mc.player.getHorizontalFacing()));
        }
        Aura.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        Aura.mc.player.swingArm(this.actual_hand);
        Aura.mc.player.resetCooldown();
    }
    
    public Entity find_entity() {
        Entity entity_requested = null;
        for (final Entity player : (List)Aura.mc.world.playerEntities.stream().filter(entityPlayer -> !FriendUtil.isFriend(entityPlayer.getName())).collect(Collectors.toList())) {
            if (player != null && this.is_compatible(player) && Aura.mc.player.getDistance(player) <= this.range.getValue(1.0)) {
                entity_requested = player;
            }
        }
        return entity_requested;
    }
    
    public boolean is_compatible(final Entity entity) {
        if (this.player.getValue(true) && entity instanceof EntityPlayer && entity != Aura.mc.player && !entity.getName().equals(Aura.mc.player.getName())) {
            return true;
        }
        if (this.hostile.getValue(true) && entity instanceof IMob) {
            return true;
        }
        if (entity instanceof EntityLivingBase) {
            final EntityLivingBase entity_living_base = (EntityLivingBase)entity;
            if (entity_living_base.getHealth() <= 0.0f) {
                return false;
            }
        }
        return false;
    }
    
    private boolean checkSharpness(final ItemStack stack) {
        if (stack.getTagCompound() == null) {
            return false;
        }
        final NBTTagList enchants = (NBTTagList)stack.getTagCompound().getTag("ench");
        if (enchants == null) {
            return false;
        }
        int i = 0;
        while (i < enchants.tagCount()) {
            final NBTTagCompound enchant = enchants.getCompoundTagAt(i);
            if (enchant.getInteger("id") == 16) {
                final int lvl = enchant.getInteger("lvl");
                if (lvl > 5) {
                    return true;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return false;
    }
    
    private int findSwordInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (Aura.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemSword) {
                slot = i;
                break;
            }
        }
        return slot;
    }
}
