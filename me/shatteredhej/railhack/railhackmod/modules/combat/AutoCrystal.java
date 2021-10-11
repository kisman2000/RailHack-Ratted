//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.concurrent.*;
import java.util.function.*;
import net.minecraft.network.play.client.*;
import me.shatteredhej.railhack.railhackmod.manager.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.speed.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import net.minecraft.world.*;
import me.shatteredhej.railhack.railhackmod.modules.chat.*;
import java.awt.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.*;
import net.minecraft.item.*;
import me.shatteredhej.railhack.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.damage.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;

public class AutoCrystal extends Module
{
    Setting debug;
    Setting placeCrystal;
    Setting placePredict;
    Setting placeDelay;
    Setting placementVerify;
    Setting speedPlace;
    Setting speedPlaceDelay;
    Setting breakCrystal;
    Setting breakPredict;
    Setting breakDelay;
    Setting speedBreak;
    Setting speedBreakDelay;
    Setting breakAttempts;
    Setting ghostSwing;
    Setting setDeadOld;
    Setting setDeadNew;
    Setting movementPredict;
    Setting range;
    Setting place_range;
    Setting hitRange;
    Setting hitRangeWall;
    Setting hitAll;
    Setting antiWeakness;
    Setting minPlayerPlace;
    Setting minPlayerBreak;
    Setting maxSelfDamage;
    Setting rotationType;
    Setting raytrace;
    Setting autoSwitch;
    Setting switchOnTotem;
    Setting antiSuicide;
    Setting fastMode;
    Setting clientSided;
    Setting antiStuck;
    Setting antiStuckTries;
    Setting autoTrapWait;
    Setting crystalFillWait;
    Setting surroundWait;
    Setting holeFillWait;
    Setting endcrystal;
    Setting faceplace;
    Setting faceplaceHP;
    Setting armorDestroy;
    Setting armorFaceplacePercent;
    Setting stopWhileMining;
    Setting facePlaceCheck;
    Setting swing;
    Setting render_mode;
    Setting old_render;
    Setting future_render;
    Setting top_block;
    Setting r;
    Setting g;
    Setting b;
    Setting a;
    Setting a_out;
    Setting rainbow_mode;
    Setting sat;
    Setting brightness;
    Setting height;
    Setting render_damage;
    Setting attempt_chain;
    Setting chain_length;
    public CPacketUseEntity.Action action;
    private final ConcurrentHashMap<EntityEnderCrystal, Integer> attacked_crystals;
    private final List<BlockPos> placePosList;
    private final List<BlockPos> breakPosList;
    private final TimerHelp remove_visual_timer;
    private final TimerHelp chain_timer;
    private final Timer timer;
    private EntityPlayer autoez_target;
    private String detail_name;
    private int detail_hp;
    private BlockPos render_block_init;
    private BlockPos render_block_old;
    private double render_damage_value;
    private float yaw;
    private float pitch;
    private boolean already_attacking;
    private boolean place_timeout_flag;
    private boolean is_rotating;
    private boolean did_anything;
    private boolean outline;
    private boolean solid;
    private int chain_step;
    private int current_chain_index;
    private int place_timeout;
    private int break_timeout;
    private int break_delay_counter;
    private int place_delay_counter;
    private int rangeInt;
    private int sPD;
    private int sBD;
    @EventHandler
    private Listener<EventEntityRemoved> on_entity_removed;
    @EventHandler
    private Listener<EventPacket.SendPacket> sendListener;
    @EventHandler
    private Listener<EventPacket.SendPacket> send_listener;
    @EventHandler
    private Listener<EventMotionUpdate> on_movement;
    @EventHandler
    private final Listener<EventPacket.ReceivePacket> receiveListener;
    
    public AutoCrystal() {
        super(Category.Combat);
        this.debug = this.register("Debug", "CaDebug", false);
        this.placeCrystal = this.register("Place", "CaPlace", false);
        this.placePredict = this.register("PlacePredict", "CaPlacePredict", false);
        this.placeDelay = this.register("PlaceDelay", "CaPlaceDelay", 0, 0, 20);
        this.placementVerify = this.register("VerifyPlace", "CaVerifyPlace", false);
        this.speedPlace = this.register("Speed Place", "CaSpeedPlace", false);
        this.speedPlaceDelay = this.register("SpeedPlaceDelay", "CaSpeedPlaceDelay", 0, 0, 1000);
        this.breakCrystal = this.register("Break", "CaBreak", false);
        this.breakPredict = this.register("BreakPredict", "CaBreakPredict", false);
        this.breakDelay = this.register("BreakDelay", "CaBreakDelay", 0, 0, 20);
        this.speedBreak = this.register("Speed Break", "CaSpeedBreak", false);
        this.speedBreakDelay = this.register("SpeedBreakDelay", "CaSpeedBreakDelay", 0, 0, 1000);
        this.breakAttempts = this.register("Breaks", "CaBreakAttempts", 2, 1, 10);
        this.ghostSwing = this.register("GhostSwing", "CaGhostSwing", false);
        this.setDeadOld = this.register("SetDeadOld", "CaSetDeadOld", false);
        this.setDeadNew = this.register("SetDeadNew", "CaSetDeadNew", false);
        this.movementPredict = this.register("MovementPredict", "CaMovementPredict", false);
        this.range = this.register("Range", "CaRange", 0, 0, 20);
        this.place_range = this.register("PlaceRange", "CaPlaceRange", 0.0, 1.0, 6.0);
        this.hitRange = this.register("HitRange", "CaHitRange", 0.0, 1.0, 6.0);
        this.hitRangeWall = this.register("RangeWall", "CaRangeWall", 0.0, 1.0, 6.0);
        this.hitAll = this.register("HitAll", "CaHitAll", false);
        this.antiWeakness = this.register("AntiWeakness", "CaAntiWeakness", false);
        this.minPlayerPlace = this.register("MinEnemyPlace", "CaMinEnemyPlace", 8, 0, 20);
        this.minPlayerBreak = this.register("MinEnemyBreak", "CaMinEnemyBreak", 6, 0, 20);
        this.maxSelfDamage = this.register("MaxSelfDamage", "CaMaxSelfDamage", 6, 0, 20);
        this.rotationType = this.register("Rotate: ", "CaRotateMode", "Off", this.combobox(new String[] { "Off", "Old", "Const", "Good" }));
        this.raytrace = this.register("Raytrace", "CaRaytrace", false);
        this.autoSwitch = this.register("AutoSwitch", "CaAutoSwitch", false);
        this.switchOnTotem = this.register("SwitchOnTotem", "CaSwitchOnTotem", false);
        this.antiSuicide = this.register("AntiSuicide", "CaAntiSuicide", false);
        this.fastMode = this.register("FastMode", "CaSpeed", false);
        this.clientSided = this.register("ClientSided", "CaClientSided", false);
        this.antiStuck = this.register("AntiStuck", "CaAntiStuck", false);
        this.antiStuckTries = this.register("AntiStuckTries", "CaAntiStuckTries", 0, 0, 15);
        this.autoTrapWait = this.register("AutoTrapWait", "CaAutoTrapWait", false);
        this.crystalFillWait = this.register("CrystalFillWait", "CaCrystalFillWait", false);
        this.surroundWait = this.register("SurroundWait", "CaSurroundWait", false);
        this.holeFillWait = this.register("HoleFillWait", "CaHoleFillWait", false);
        this.endcrystal = this.register("1.13Mode", "CaThirteen", false);
        this.faceplace = this.register("Faceplace", "CaFaceplaceCa", false);
        this.faceplaceHP = this.register("FaceplaceHP", "CaFaceplaceHealth", 0, 0, 36);
        this.armorDestroy = this.register("ArmorDestroy", "CaArmorDestory", false);
        this.armorFaceplacePercent = this.register("ArmorPercent", "CaArmorPercent", 0, 0, 100);
        this.stopWhileMining = this.register("StopWhileMining", "CaStopWhileMining", false);
        this.facePlaceCheck = this.register("NoSwordFP", "CaJumpyFaceMode", false);
        this.swing = this.register("Swing: ", "CaSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.render_mode = this.register("Render", "CaRenderMode", "Pretty", this.combobox(new String[] { "Pretty", "Solid", "Outline", "None" }));
        this.old_render = this.register("OldRender", "CaOldRender", false);
        this.future_render = this.register("FutureRender", "CaFutureRender", false);
        this.top_block = this.register("TopBlock", "CaTopBlock", false);
        this.r = this.register("R", "CaR", 255, 0, 255);
        this.g = this.register("G", "CaG", 255, 0, 255);
        this.b = this.register("B", "CaB", 255, 0, 255);
        this.a = this.register("A", "CaA", 100, 0, 255);
        this.a_out = this.register("OutlineA", "CaOutlineA", 255, 0, 255);
        this.rainbow_mode = this.register("Rainbow", "CaRainbow", false);
        this.sat = this.register("Satiation", "CaSatiation", 0.8, 0.0, 1.0);
        this.brightness = this.register("Brightness", "CaBrightness", 0.8, 0.0, 1.0);
        this.height = this.register("Height", "CaHeight", 1.0, 0.0, 1.0);
        this.render_damage = this.register("RenderDamage", "RenderDamage", true);
        this.attempt_chain = this.register("ChainMode", "CaChainMode", false);
        this.chain_length = this.register("ChainLength", "CaChainLength", 3, 1, 6);
        this.attacked_crystals = new ConcurrentHashMap<EntityEnderCrystal, Integer>();
        this.placePosList = new CopyOnWriteArrayList<BlockPos>();
        this.breakPosList = new CopyOnWriteArrayList<BlockPos>();
        this.remove_visual_timer = new TimerHelp();
        this.chain_timer = new TimerHelp();
        this.timer = new Timer();
        this.autoez_target = null;
        this.detail_name = null;
        this.detail_hp = 0;
        this.already_attacking = false;
        this.place_timeout_flag = false;
        this.chain_step = 0;
        this.current_chain_index = 0;
        this.rangeInt = this.range.getValue(1);
        this.sPD = this.speedPlaceDelay.getValue(1);
        this.sBD = this.speedBreakDelay.getValue(1);
        this.on_entity_removed = new Listener<EventEntityRemoved>(event -> {
            if (event.get_entity() instanceof EntityEnderCrystal) {
                this.attacked_crystals.remove(event.get_entity());
            }
            return;
        }, (Predicate<EventEntityRemoved>[])new Predicate[0]);
        this.sendListener = new Listener<EventPacket.SendPacket>(event -> {
            if (event.getPacket() instanceof CPacketUseEntity && this.ghostSwing.getValue(true)) {
                event.cancel();
            }
            return;
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        CPacketPlayer p;
        CPacketPlayerTryUseItemOnBlock p2;
        this.send_listener = new Listener<EventPacket.SendPacket>(event -> {
            if (event.getPacket() instanceof CPacketPlayer && this.is_rotating && this.rotationType.in("Old")) {
                if (this.debug.getValue(true)) {
                    MessageUtil.send_client_message("Rotating");
                }
                p = (CPacketPlayer)event.getPacket();
                p.yaw = this.yaw;
                p.pitch = this.pitch;
                this.is_rotating = false;
            }
            if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && this.is_rotating && this.rotationType.in("Old")) {
                if (this.debug.getValue(true)) {
                    MessageUtil.send_client_message("Rotating");
                }
                p2 = (CPacketPlayerTryUseItemOnBlock)event.getPacket();
                p2.facingX = (float)this.render_block_init.getX();
                p2.facingY = (float)this.render_block_init.getY();
                p2.facingZ = (float)this.render_block_init.getZ();
                this.is_rotating = false;
            }
            return;
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.on_movement = new Listener<EventMotionUpdate>(event -> {
            if (event.stage == 0 && (this.rotationType.in("Good") || this.rotationType.in("Const"))) {
                if (this.debug.getValue(true)) {
                    MessageUtil.send_client_message("updating rotation");
                }
                PosManager.updatePosition();
                RotationUtil.updateRotations();
                this.doCa();
            }
            if (event.stage == 1 && (this.rotationType.in("Good") || this.rotationType.in("Const"))) {
                if (this.debug.getValue(true)) {
                    MessageUtil.send_client_message("resetting rotation");
                }
                PosManager.restorePosition();
                RotationUtil.restoreRotations();
            }
            return;
        }, (Predicate<EventMotionUpdate>[])new Predicate[0]);
        SPacketSoundEffect packetSoundEffect;
        final Iterator<Entity> iterator;
        Entity entity;
        SPacketSoundEffect packet;
        final Iterator<Entity> iterator2;
        Entity e;
        SPacketSpawnObject spawnPacket;
        CPacketUseEntity useEntity;
        CPacketUseEntity cPacketUseEntity;
        Entity targetEntity;
        CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock;
        this.receiveListener = new Listener<EventPacket.ReceivePacket>(event -> {
            if (this.setDeadNew.getValue(true) && event.getPacket() instanceof SPacketSoundEffect && event.getPacket() instanceof SPacketSoundEffect) {
                packetSoundEffect = (SPacketSoundEffect)event.getPacket();
                if (packetSoundEffect.getCategory() == SoundCategory.BLOCKS && packetSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                    new ArrayList<Entity>(AutoCrystal.mc.world.loadedEntityList).iterator();
                    while (iterator.hasNext()) {
                        entity = iterator.next();
                        if (entity instanceof EntityEnderCrystal && entity.getDistanceSq(packetSoundEffect.getX(), packetSoundEffect.getY(), packetSoundEffect.getZ()) <= 6.0) {
                            entity.setDead();
                        }
                    }
                }
            }
            if (this.setDeadOld.getValue(true) && event.getPacket() instanceof SPacketSoundEffect) {
                packet = (SPacketSoundEffect)event.getPacket();
                if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                    AutoCrystal.mc.world.loadedEntityList.iterator();
                    while (iterator2.hasNext()) {
                        e = iterator2.next();
                        if (e instanceof EntityEnderCrystal && e.getDistance(packet.getX(), packet.getY(), packet.getZ()) <= 6.0) {
                            e.setDead();
                        }
                    }
                }
            }
            if (this.breakPredict.getValue(true) && event.getPacket() instanceof SPacketSpawnObject) {
                spawnPacket = (SPacketSpawnObject)event.getPacket();
                if (spawnPacket.getType() == 51) {
                    if (this.placePosList.contains(new BlockPos(spawnPacket.getX(), spawnPacket.getY(), spawnPacket.getZ()).down())) {
                        useEntity = new CPacketUseEntity();
                        useEntity.action = CPacketUseEntity.Action.ATTACK;
                        useEntity.entityId = spawnPacket.getEntityID();
                        AutoCrystal.mc.player.connection.sendPacket((Packet)useEntity);
                    }
                }
            }
            if (this.placePredict.getValue(true) && event.getPacket() instanceof CPacketUseEntity) {
                cPacketUseEntity = (CPacketUseEntity)event.getPacket();
                if (cPacketUseEntity.getAction().equals((Object)CPacketUseEntity.Action.ATTACK)) {
                    targetEntity = cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world);
                    if (targetEntity instanceof EntityEnderCrystal) {
                        cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)event.getPacket();
                        cPacketPlayerTryUseItemOnBlock.position = targetEntity.getPosition();
                        AutoCrystal.mc.player.connection.sendPacket((Packet)cPacketPlayerTryUseItemOnBlock);
                    }
                }
            }
            return;
        }, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.name = "AutoCrystal";
        this.tag = "AutoCrystal";
    }
    
    public void doCa() {
        this.did_anything = false;
        if (AutoCrystal.mc.player == null || AutoCrystal.mc.world == null) {
            return;
        }
        if (this.rainbow_mode.getValue(true)) {
            this.cycle_rainbow();
        }
        if (this.remove_visual_timer.passed(1000L)) {
            this.remove_visual_timer.reset();
            this.attacked_crystals.clear();
        }
        if (this.check_pause()) {
            return;
        }
        if (this.placeCrystal.getValue(true) && this.place_delay_counter > this.place_timeout) {
            this.placeCrystal();
        }
        if (this.breakCrystal.getValue(true) && this.break_delay_counter > this.break_timeout) {
            this.breakCrystal();
        }
        if (this.speedPlace.getValue(true) && this.timer.passedMss(this.sPD)) {
            this.timer.reset();
            this.placeCrystal();
        }
        if (this.speedBreak.getValue(true) && this.timer.passedMss(this.sBD)) {
            this.timer.reset();
            this.breakCrystal();
        }
        if (!this.did_anything) {
            if (this.old_render.getValue(true)) {
                this.render_block_init = null;
            }
            this.autoez_target = null;
            this.is_rotating = false;
        }
        if (this.autoez_target != null) {
            AutoEz.add_target(this.autoez_target.getName());
            this.detail_name = this.autoez_target.getName();
            this.detail_hp = Math.round(this.autoez_target.getHealth() + this.autoez_target.getAbsorptionAmount());
        }
        if (this.chain_timer.passed(1000L)) {
            this.chain_timer.reset();
            this.chain_step = 0;
        }
        this.render_block_old = this.render_block_init;
        ++this.break_delay_counter;
        ++this.place_delay_counter;
    }
    
    public void onUpdate() {
        if (this.rotationType.in("Off") || this.rotationType.in("Old")) {
            this.doCa();
        }
    }
    
    public void cycle_rainbow() {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int color_rgb_o = Color.HSBtoRGB(tick_color[0], (float)this.sat.getValue(1), (float)this.brightness.getValue(1));
        this.r.setValue(color_rgb_o >> 16 & 0xFF);
        this.g.setValue(color_rgb_o >> 8 & 0xFF);
        this.b.setValue(color_rgb_o & 0xFF);
    }
    
    public EntityEnderCrystal get_best_crystal() {
        double best_damage = 0.0;
        final double maximum_damage_self = this.maxSelfDamage.getValue(1);
        double best_distance = 0.0;
        EntityEnderCrystal best_crystal = null;
        for (final Entity c : AutoCrystal.mc.world.loadedEntityList) {
            if (!(c instanceof EntityEnderCrystal)) {
                continue;
            }
            final EntityEnderCrystal crystal = (EntityEnderCrystal)c;
            if (AutoCrystal.mc.player.getDistance((Entity)crystal) > (AutoCrystal.mc.player.canEntityBeSeen((Entity)crystal) ? this.hitRange.getValue(1) : this.hitRangeWall.getValue(1))) {
                continue;
            }
            if (!AutoCrystal.mc.player.canEntityBeSeen((Entity)crystal) && this.raytrace.getValue(true)) {
                continue;
            }
            if (crystal.isDead) {
                continue;
            }
            if (this.attacked_crystals.containsKey(crystal) && this.attacked_crystals.get(crystal) > this.antiStuckTries.getValue(1) && this.antiStuck.getValue(true)) {
                continue;
            }
            for (final Entity player : AutoCrystal.mc.world.playerEntities) {
                if (player != AutoCrystal.mc.player) {
                    if (!(player instanceof EntityPlayer)) {
                        continue;
                    }
                    if (FriendUtil.isFriend(player.getName())) {
                        continue;
                    }
                    if (player.getDistance((Entity)AutoCrystal.mc.player) >= this.range.getValue(this.rangeInt)) {
                        continue;
                    }
                    final EntityPlayer target = (EntityPlayer)player;
                    if (target.isDead) {
                        continue;
                    }
                    if (target.getHealth() <= 0.0f) {
                        continue;
                    }
                    final boolean no_place = this.facePlaceCheck.getValue(true) && AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD;
                    double minimum_damage;
                    if ((target.getHealth() < this.faceplaceHP.getValue(1) && this.faceplace.getValue(true) && !no_place) || (this.get_armor_fucker(target) && !no_place)) {
                        minimum_damage = 2.0;
                    }
                    else {
                        minimum_damage = this.minPlayerBreak.getValue(1);
                    }
                    final double target_damage = CrystalUtil.calculateDamage(crystal, (Entity)target);
                    if (target_damage < minimum_damage) {
                        continue;
                    }
                    final double self_damage = CrystalUtil.calculateDamage(crystal, (Entity)AutoCrystal.mc.player);
                    if (self_damage > maximum_damage_self) {
                        continue;
                    }
                    if (this.antiSuicide.getValue(true) && AutoCrystal.mc.player.getHealth() + AutoCrystal.mc.player.getAbsorptionAmount() - self_damage <= 0.5) {
                        continue;
                    }
                    if (target_damage <= best_damage || this.hitAll.getValue(true)) {
                        continue;
                    }
                    this.autoez_target = target;
                    best_damage = target_damage;
                    best_crystal = crystal;
                }
            }
            if (!this.hitAll.getValue(true) || AutoCrystal.mc.player.getDistanceSq((Entity)crystal) <= best_distance) {
                continue;
            }
            best_distance = AutoCrystal.mc.player.getDistanceSq((Entity)crystal);
            best_crystal = crystal;
        }
        return best_crystal;
    }
    
    public BlockPos get_best_block() {
        if (this.get_best_crystal() != null && !this.fastMode.getValue(true)) {
            this.place_timeout_flag = true;
            return null;
        }
        if (this.place_timeout_flag) {
            this.place_timeout_flag = false;
            return null;
        }
        List<WurstplusPair<Double, BlockPos>> damage_blocks = new ArrayList<WurstplusPair<Double, BlockPos>>();
        double best_damage = 0.0;
        final double maximum_damage_self = this.maxSelfDamage.getValue(1);
        BlockPos best_block = null;
        final List<BlockPos> blocks = CrystalUtil.possiblePlacePositions((float)this.place_range.getValue(1), this.endcrystal.getValue(true), true);
        for (final Entity player : AutoCrystal.mc.world.playerEntities) {
            if (FriendUtil.isFriend(player.getName())) {
                continue;
            }
            for (final BlockPos block : blocks) {
                if (player != AutoCrystal.mc.player) {
                    if (!(player instanceof EntityPlayer)) {
                        continue;
                    }
                    if (player.getDistance((Entity)AutoCrystal.mc.player) >= 11.0f) {
                        continue;
                    }
                    if (!BlockUtil.rayTracePlaceCheck(block, this.raytrace.getValue(true))) {
                        continue;
                    }
                    if (!BlockUtil.canSeeBlock(block) && AutoCrystal.mc.player.getDistance((double)block.getX(), (double)block.getY(), (double)block.getZ()) > this.hitRangeWall.getValue(1)) {
                        continue;
                    }
                    final EntityPlayer target = (EntityPlayer)player;
                    if (target.isDead) {
                        continue;
                    }
                    if (target.getHealth() <= 0.0f) {
                        continue;
                    }
                    final boolean no_place = this.facePlaceCheck.getValue(true) && AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD;
                    double minimum_damage;
                    if ((target.getHealth() < this.faceplaceHP.getValue(1) && this.faceplace.getValue(true) && !no_place) || (this.get_armor_fucker(target) && !no_place)) {
                        minimum_damage = 2.0;
                    }
                    else {
                        minimum_damage = this.minPlayerPlace.getValue(1);
                    }
                    final double target_damage = CrystalUtil.calculateDamage(block.getX() + 0.5, block.getY() + 1.0, block.getZ() + 0.5, (Entity)target);
                    if (target_damage < minimum_damage) {
                        continue;
                    }
                    final double self_damage = CrystalUtil.calculateDamage(block.getX() + 0.5, block.getY() + 1.0, block.getZ() + 0.5, (Entity)AutoCrystal.mc.player);
                    if (self_damage > maximum_damage_self) {
                        continue;
                    }
                    if (this.antiSuicide.getValue(true) && AutoCrystal.mc.player.getHealth() + AutoCrystal.mc.player.getAbsorptionAmount() - self_damage <= 0.5) {
                        continue;
                    }
                    if (this.attempt_chain.getValue(true) && this.chain_step > 0) {
                        damage_blocks.add(new WurstplusPair<Double, BlockPos>(best_damage, best_block));
                        this.autoez_target = target;
                    }
                    else {
                        if (target_damage <= best_damage) {
                            continue;
                        }
                        best_damage = target_damage;
                        best_block = block;
                        this.autoez_target = target;
                    }
                }
            }
        }
        blocks.clear();
        if (this.chain_step == 1) {
            this.current_chain_index = this.chain_length.getValue(1);
        }
        else if (this.chain_step > 1) {
            --this.current_chain_index;
        }
        this.render_damage_value = best_damage;
        this.render_block_init = best_block;
        damage_blocks = this.sort_best_blocks(damage_blocks);
        if (!this.attempt_chain.getValue(true)) {
            return best_block;
        }
        if (damage_blocks.size() == 0) {
            return null;
        }
        if (damage_blocks.size() < this.current_chain_index) {
            return damage_blocks.get(0).getValue();
        }
        return damage_blocks.get(this.current_chain_index).getValue();
    }
    
    public List<WurstplusPair<Double, BlockPos>> sort_best_blocks(final List<WurstplusPair<Double, BlockPos>> list) {
        final List<WurstplusPair<Double, BlockPos>> new_list = new ArrayList<WurstplusPair<Double, BlockPos>>();
        double damage_cap = 1000.0;
        for (int i = 0; i < list.size(); ++i) {
            final double biggest_dam = 0.0;
            WurstplusPair<Double, BlockPos> best_pair = null;
            for (final WurstplusPair<Double, BlockPos> pair : list) {
                if (pair.getKey() > biggest_dam && pair.getKey() < damage_cap) {
                    best_pair = pair;
                }
            }
            if (best_pair != null) {
                damage_cap = best_pair.getKey();
                new_list.add(best_pair);
            }
        }
        return new_list;
    }
    
    public void placeCrystal() {
        final BlockPos target_block = this.get_best_block();
        if (target_block == null) {
            return;
        }
        this.place_delay_counter = 0;
        this.already_attacking = false;
        boolean offhand_check = false;
        if (AutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            if (AutoCrystal.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && this.autoSwitch.getValue(true)) {
                if (this.findCrystalsHotbar() == -1) {
                    return;
                }
                AutoCrystal.mc.player.inventory.currentItem = this.findCrystalsHotbar();
                return;
            }
            else if (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING && AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING && this.switchOnTotem.getValue(true)) {
                if (this.findCrystalsHotbar() == -1) {
                    return;
                }
                AutoCrystal.mc.player.inventory.currentItem = this.findCrystalsHotbar();
                return;
            }
        }
        else {
            offhand_check = true;
        }
        if (this.debug.getValue(true)) {
            MessageUtil.send_client_message("placing");
        }
        ++this.chain_step;
        this.did_anything = true;
        this.rotate_to_pos(target_block);
        this.chain_timer.reset();
        if (this.placementVerify.getValue(true) && AutoCrystal.mc.player.getDistanceSq(target_block) > Math.pow(this.hitRange.getValue(1), 2.0)) {
            BlockUtil.placeCrystalOnBlock(target_block, offhand_check ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
        }
        BlockUtil.placeCrystalOnBlock(target_block, offhand_check ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
        this.placePosList.add(target_block);
    }
    
    public boolean get_armor_fucker(final EntityPlayer p) {
        for (final ItemStack stack : p.getArmorInventoryList()) {
            if (stack == null || stack.getItem() == Items.AIR) {
                return true;
            }
            final float armor_percent = (stack.getMaxDamage() - stack.getItemDamage()) / (float)stack.getMaxDamage() * 100.0f;
            if (this.armorDestroy.getValue(true) && this.armorFaceplacePercent.getValue(1) >= armor_percent) {
                return true;
            }
        }
        return false;
    }
    
    public void breakCrystal() {
        final EntityEnderCrystal crystal = this.get_best_crystal();
        if (crystal == null) {
            return;
        }
        if (this.antiWeakness.getValue(true) && AutoCrystal.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
            boolean should_weakness = true;
            if (AutoCrystal.mc.player.isPotionActive(MobEffects.STRENGTH) && Objects.requireNonNull(AutoCrystal.mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                should_weakness = false;
            }
            if (should_weakness) {
                if (!this.already_attacking) {
                    this.already_attacking = true;
                }
                int new_slot = -1;
                for (int i = 0; i < 9; ++i) {
                    final ItemStack stack = AutoCrystal.mc.player.inventory.getStackInSlot(i);
                    if (stack.getItem() instanceof ItemSword || stack.getItem() instanceof ItemTool) {
                        new_slot = i;
                        AutoCrystal.mc.playerController.updateController();
                        break;
                    }
                }
                if (new_slot != -1) {
                    AutoCrystal.mc.player.inventory.currentItem = new_slot;
                }
            }
        }
        if (this.debug.getValue(true)) {
            MessageUtil.send_client_message("attacking");
        }
        this.did_anything = true;
        this.rotate_to((Entity)crystal);
        for (int j = 0; j < this.breakAttempts.getValue(1); ++j) {
            EntityUtil.attackEntityOne((Entity)crystal, true, this.swing);
        }
        this.add_attacked_crystal(crystal);
        if (this.clientSided.getValue(true)) {
            crystal.setDead();
            this.breakCrystal();
        }
        this.break_delay_counter = 0;
    }
    
    public boolean check_pause() {
        if (this.findCrystalsHotbar() == -1 && AutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            return true;
        }
        if (this.stopWhileMining.getValue(true) && AutoCrystal.mc.gameSettings.keyBindAttack.isKeyDown() && AutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
            if (this.old_render.getValue(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        if (this.surroundWait.getValue(true) && RailHack.get_hack_manager().getModuleWithTag("Surround").isActive()) {
            if (this.old_render.getValue(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        if (this.holeFillWait.getValue(true) && RailHack.get_hack_manager().getModuleWithTag("HoleFill").isActive()) {
            if (this.old_render.getValue(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        if (this.crystalFillWait.getValue(true) && RailHack.get_hack_manager().getModuleWithTag("CrystalFill").isActive()) {
            if (this.old_render.getValue(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        if (this.autoTrapWait.getValue(true) && RailHack.get_hack_manager().getModuleWithTag("AutoTrap").isActive()) {
            if (this.old_render.getValue(true)) {
                this.render_block_init = null;
            }
            return true;
        }
        return false;
    }
    
    private int findCrystalsHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (AutoCrystal.mc.player.inventory.getStackInSlot(i).getItem() == Items.END_CRYSTAL) {
                return i;
            }
        }
        return -1;
    }
    
    private void add_attacked_crystal(final EntityEnderCrystal crystal) {
        if (this.attacked_crystals.containsKey(crystal)) {
            final int value = this.attacked_crystals.get(crystal);
            this.attacked_crystals.put(crystal, value + 1);
        }
        else {
            this.attacked_crystals.put(crystal, 1);
        }
    }
    
    public void rotate_to_pos(final BlockPos pos) {
        float[] angle;
        if (this.rotationType.in("Const")) {
            angle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), new Vec3d((double)(pos.getX() + 0.5f), (double)(pos.getY() + 0.5f), (double)(pos.getZ() + 0.5f)));
        }
        else {
            angle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), new Vec3d((double)(pos.getX() + 0.5f), (double)(pos.getY() - 0.5f), (double)(pos.getZ() + 0.5f)));
        }
        if (this.rotationType.in("Off")) {
            this.is_rotating = false;
        }
        if (this.rotationType.in("Good") || this.rotationType.in("Const")) {
            RotationUtil.setPlayerRotations(angle[0], angle[1]);
        }
        if (this.rotationType.in("Old")) {
            this.yaw = angle[0];
            this.pitch = angle[1];
            this.is_rotating = true;
        }
    }
    
    public void rotate_to(final Entity entity) {
        final float[] angle = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), entity.getPositionVector());
        if (this.rotationType.in("Off")) {
            this.is_rotating = false;
        }
        if (this.rotationType.in("Good")) {
            RotationUtil.setPlayerRotations(angle[0], angle[1]);
        }
        if (this.rotationType.in("Old") || this.rotationType.in("Cont")) {
            this.yaw = angle[0];
            this.pitch = angle[1];
            this.is_rotating = true;
        }
    }
    
    public void render(final EventRender event) {
        if (this.render_block_init == null) {
            return;
        }
        if (this.render_mode.in("None")) {
            return;
        }
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
        this.render_block(this.render_block_init);
        if (this.future_render.getValue(true) && this.render_block_old != null) {
            this.render_block(this.render_block_old);
        }
        if (this.render_damage.getValue(true)) {
            WurstplusRenderUtil.drawText(this.render_block_init, ((Math.floor(this.render_damage_value) == this.render_damage_value) ? Integer.valueOf((int)this.render_damage_value) : String.format("%.2f", this.render_damage_value)) + "");
        }
    }
    
    public void render_block(final BlockPos pos) {
        final BlockPos render_block = this.top_block.getValue(true) ? pos.up() : pos;
        final float h = (float)this.height.getValue(1.0);
        if (this.solid) {
            RenderHelp.prepare("quads");
            RenderHelp.draw_cube(RenderHelp.get_buffer_build(), (float)render_block.getX(), (float)render_block.getY(), (float)render_block.getZ(), 1.0f, h, 1.0f, this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a.getValue(1), "all");
            RenderHelp.release();
        }
        if (this.outline) {
            RenderHelp.prepare("lines");
            RenderHelp.draw_cube_line(RenderHelp.get_buffer_build(), (float)render_block.getX(), (float)render_block.getY(), (float)render_block.getZ(), 1.0f, h, 1.0f, this.r.getValue(1), this.g.getValue(1), this.b.getValue(1), this.a_out.getValue(1), "all");
            RenderHelp.release();
        }
    }
    
    public void onEnable() {
        this.place_timeout = this.placeDelay.getValue(1);
        this.break_timeout = this.breakDelay.getValue(1);
        this.place_timeout_flag = false;
        this.is_rotating = false;
        this.autoez_target = null;
        this.chain_step = 0;
        this.current_chain_index = 0;
        this.chain_timer.reset();
        this.remove_visual_timer.reset();
        this.detail_name = null;
        this.detail_hp = 20;
    }
    
    public void whenDisabled() {
        this.render_block_init = null;
        this.autoez_target = null;
    }
    
    public String array_detail() {
        return (this.detail_name != null) ? (this.detail_name + " | " + this.detail_hp) : "None";
    }
}
