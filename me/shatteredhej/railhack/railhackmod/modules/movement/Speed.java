//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.function.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;

public class Speed extends Module
{
    Setting speed_mode;
    Setting auto_sprint;
    Setting on_water;
    Setting auto_jump;
    Setting jumpHeight;
    Setting backward;
    Setting bypass;
    private final int jumpY;
    @EventHandler
    private Listener<EventPlayerJump> on_jump;
    @EventHandler
    private Listener<EventMove> player_move;
    
    public Speed() {
        super(Category.Movement);
        this.speed_mode = this.register("Mode: ", "StrafeMode", "OnGround", this.combobox(new String[] { "Strafe", "On Ground" }));
        this.auto_sprint = this.register("AutoSprint", "StrafeSprint", true);
        this.on_water = this.register("OnWater", "StrafeOnWater", true);
        this.auto_jump = this.register("AutoJump", "StrafeAutoJump", true);
        this.jumpHeight = this.register("JumpHeight", "StrafeJumpHeight", 0, 0, 420);
        this.backward = this.register("Backwards", "StrafeBackwards", true);
        this.bypass = this.register("Bypass", "StrafeBypass", false);
        this.jumpY = this.jumpHeight.getValue(1) / 1000;
        this.on_jump = new Listener<EventPlayerJump>(event -> {
            if (this.speed_mode.in("Strafe")) {
                event.cancel();
            }
            return;
        }, (Predicate<EventPlayerJump>[])new Predicate[0]);
        float player_speed;
        float move_forward;
        float move_strafe;
        float rotation_yaw;
        int amp;
        this.player_move = new Listener<EventMove>(event -> {
            if (this.speed_mode.in("On Ground")) {
                return;
            }
            else if ((Speed.mc.player.isInWater() || Speed.mc.player.isInLava()) && !this.speed_mode.getValue(true)) {
                return;
            }
            else if (Speed.mc.player.isSneaking() || Speed.mc.player.isOnLadder() || Speed.mc.player.isInWeb || Speed.mc.player.isInLava() || Speed.mc.player.isInWater() || Speed.mc.player.capabilities.isFlying) {
                return;
            }
            else {
                player_speed = 0.2873f;
                move_forward = Speed.mc.player.movementInput.moveForward;
                move_strafe = Speed.mc.player.movementInput.moveStrafe;
                rotation_yaw = Speed.mc.player.rotationYaw;
                if (Speed.mc.player.isPotionActive(MobEffects.SPEED)) {
                    amp = Speed.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
                    player_speed *= 1.2f * (amp + 1);
                }
                if (!this.bypass.getValue(true)) {
                    player_speed *= 1.0064f;
                }
                if (move_forward == 0.0f && move_strafe == 0.0f) {
                    event.set_x(0.0);
                    event.set_z(0.0);
                }
                else {
                    if (move_forward != 0.0f) {
                        if (move_strafe > 0.0f) {
                            rotation_yaw += ((move_forward > 0.0f) ? -45 : 45);
                        }
                        else if (move_strafe < 0.0f) {
                            rotation_yaw += ((move_forward > 0.0f) ? 45 : -45);
                        }
                        move_strafe = 0.0f;
                        if (move_forward > 0.0f) {
                            move_forward = 1.0f;
                        }
                        else if (move_forward < 0.0f) {
                            move_forward = -1.0f;
                        }
                    }
                    event.set_x(move_forward * player_speed * Math.cos(Math.toRadians(rotation_yaw + 90.0f)) + move_strafe * player_speed * Math.sin(Math.toRadians(rotation_yaw + 90.0f)));
                    event.set_z(move_forward * player_speed * Math.sin(Math.toRadians(rotation_yaw + 90.0f)) - move_strafe * player_speed * Math.cos(Math.toRadians(rotation_yaw + 90.0f)));
                }
                event.cancel();
                return;
            }
        }, (Predicate<EventMove>[])new Predicate[0]);
        this.name = "Speed";
        this.tag = "Speed";
    }
    
    public void onUpdate() {
        if (Speed.mc.player.isRiding()) {
            return;
        }
        if ((Speed.mc.player.isInWater() || Speed.mc.player.isInLava()) && !this.on_water.getValue(true)) {
            return;
        }
        if (Speed.mc.player.moveForward != 0.0f || Speed.mc.player.moveStrafing != 0.0f) {
            if (Speed.mc.player.moveForward < 0.0f && !this.backward.getValue(true)) {
                return;
            }
            if (this.auto_sprint.getValue(true)) {
                Speed.mc.player.setSprinting(true);
            }
            if (Speed.mc.player.onGround && this.speed_mode.in("Strafe")) {
                if (this.auto_jump.getValue(true)) {
                    Speed.mc.player.motionY = this.jumpY;
                }
                final float yaw = this.get_rotation_yaw() * 0.017453292f;
                final EntityPlayerSP player = Speed.mc.player;
                player.motionX -= MathHelper.sin(yaw) * 0.2f;
                final EntityPlayerSP player2 = Speed.mc.player;
                player2.motionZ += MathHelper.cos(yaw) * 0.2f;
            }
            else if (Speed.mc.player.onGround && this.speed_mode.in("On Ground")) {
                final float yaw = this.get_rotation_yaw();
                final EntityPlayerSP player3 = Speed.mc.player;
                player3.motionX -= MathHelper.sin(yaw) * 0.2f;
                final EntityPlayerSP player4 = Speed.mc.player;
                player4.motionZ += MathHelper.cos(yaw) * 0.2f;
                Speed.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Speed.mc.player.posX, Speed.mc.player.posY + 0.4, Speed.mc.player.posZ, false));
            }
        }
        if (Speed.mc.gameSettings.keyBindJump.isKeyDown() && Speed.mc.player.onGround) {
            Speed.mc.player.motionY = 0.4050000011920929;
        }
    }
    
    private float get_rotation_yaw() {
        float rotation_yaw = Speed.mc.player.rotationYaw;
        if (Speed.mc.player.moveForward < 0.0f) {
            rotation_yaw += 180.0f;
        }
        float n = 1.0f;
        if (Speed.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (Speed.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (Speed.mc.player.moveStrafing > 0.0f) {
            rotation_yaw -= 90.0f * n;
        }
        if (Speed.mc.player.moveStrafing < 0.0f) {
            rotation_yaw += 90.0f * n;
        }
        return rotation_yaw * 0.017453292f;
    }
}
