//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.modules.exploit.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.util.function.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.init.*;
import net.minecraft.client.entity.*;

public class Strafe extends Module
{
    Setting mode;
    Setting useTimer;
    Setting autoSprint;
    Setting speedInWater;
    Setting autoJump;
    Setting strict;
    private TimerModule Timer;
    @EventHandler
    private Listener<EventPlayerUpdate> OnPlayerUpdate;
    @EventHandler
    private Listener<EventPlayerJump> OnPlayerJump;
    @EventHandler
    private Listener<EventPlayerTravel> OnPlayerMove;
    
    public Strafe() {
        super(Category.Movement);
        this.mode = this.register("Mode: ", "ModeStrafeR", "Strafe", this.combobox(new String[] { "Strafe", "OnGround" }));
        this.useTimer = this.register("UseTimer", "UseTimerStrafeR", false);
        this.autoSprint = this.register("AutoSprint", "AutoSprintStrafeR", false);
        this.speedInWater = this.register("Liquid", "LiquidStrafeR", false);
        this.autoJump = this.register("AutoJump", "AutoJumpR", false);
        this.strict = this.register("Strict", "StrictStrafeR", false);
        this.Timer = new TimerModule();
        float yaw;
        EntityPlayerSP player;
        EntityPlayerSP player2;
        float yaw2;
        EntityPlayerSP player3;
        EntityPlayerSP player4;
        this.OnPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (Strafe.mc.player.isRiding()) {
                return;
            }
            else if ((Strafe.mc.player.isInWater() || Strafe.mc.player.isInLava()) && !this.speedInWater.getValue(true)) {
                return;
            }
            else {
                if (this.useTimer.getValue(true)) {
                    this.Timer.SetOverrideSpeed(1.088f);
                }
                if (Strafe.mc.player.moveForward != 0.0f || Strafe.mc.player.moveStrafing != 0.0f) {
                    if (this.autoSprint.getValue(true)) {
                        Strafe.mc.player.setSprinting(true);
                    }
                    if (Strafe.mc.player.onGround && this.mode.in("Strafe")) {
                        if (this.autoJump.getValue(true)) {
                            Strafe.mc.player.motionY = 0.4050000011920929;
                        }
                        yaw = this.GetRotationYawForCalc();
                        player = Strafe.mc.player;
                        player.motionX -= MathHelper.sin(yaw) * 0.2f;
                        player2 = Strafe.mc.player;
                        player2.motionZ += MathHelper.cos(yaw) * 0.2f;
                    }
                    else if (Strafe.mc.player.onGround && this.mode.in("OnGround")) {
                        yaw2 = this.GetRotationYawForCalc();
                        player3 = Strafe.mc.player;
                        player3.motionX -= MathHelper.sin(yaw2) * 0.2f;
                        player4 = Strafe.mc.player;
                        player4.motionZ += MathHelper.cos(yaw2) * 0.2f;
                        Strafe.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Strafe.mc.player.posX, Strafe.mc.player.posY + 0.4, Strafe.mc.player.posZ, false));
                    }
                }
                if (Strafe.mc.gameSettings.keyBindJump.isKeyDown() && Strafe.mc.player.onGround) {
                    Strafe.mc.player.motionY = 0.4050000011920929;
                }
                return;
            }
        }, (Predicate<EventPlayerUpdate>[])new Predicate[0]);
        this.OnPlayerJump = new Listener<EventPlayerJump>(p_Event -> {
            if (this.mode.in("Strafe")) {
                p_Event.cancel();
            }
            return;
        }, (Predicate<EventPlayerJump>[])new Predicate[0]);
        float playerSpeed;
        float moveForward;
        float moveStrafe;
        float rotationYaw;
        int amplifier;
        this.OnPlayerMove = new Listener<EventPlayerTravel>(p_Event -> {
            if (p_Event.get_era() != EventCancellable.Era.EVENT_PRE || this.mode.in("OnGround")) {
                return;
            }
            else if ((Strafe.mc.player.isInWater() || Strafe.mc.player.isInLava()) && !this.speedInWater.getValue(true)) {
                return;
            }
            else if (Strafe.mc.player.onGround) {
                return;
            }
            else {
                playerSpeed = 0.2896f;
                moveForward = Strafe.mc.player.movementInput.moveForward;
                moveStrafe = Strafe.mc.player.movementInput.moveStrafe;
                rotationYaw = Strafe.mc.player.rotationYaw;
                if (Strafe.mc.player.isPotionActive(MobEffects.SPEED)) {
                    amplifier = Strafe.mc.player.getActivePotionEffect(MobEffects.SPEED).getAmplifier();
                    playerSpeed *= 1.0f + 0.2f * (amplifier + 1);
                }
                if (!this.strict.getValue(true)) {
                    playerSpeed *= 1.0064f;
                }
                if (moveForward == 0.0f && moveStrafe == 0.0f) {
                    p_Event.X = 0.0;
                    p_Event.Z = 0.0;
                }
                else {
                    if (moveForward != 0.0f) {
                        if (moveStrafe > 0.0f) {
                            rotationYaw += ((moveForward > 0.0f) ? -45 : 45);
                        }
                        else if (moveStrafe < 0.0f) {
                            rotationYaw += ((moveForward > 0.0f) ? 45 : -45);
                        }
                        moveStrafe = 0.0f;
                        if (moveForward > 0.0f) {
                            moveForward = 1.0f;
                        }
                        else if (moveForward < 0.0f) {
                            moveForward = -1.0f;
                        }
                    }
                    p_Event.X = moveForward * playerSpeed * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + moveStrafe * playerSpeed * Math.sin(Math.toRadians(rotationYaw + 90.0f));
                    p_Event.Z = moveForward * playerSpeed * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - moveStrafe * playerSpeed * Math.cos(Math.toRadians(rotationYaw + 90.0f));
                }
                p_Event.cancel();
                return;
            }
        }, (Predicate<EventPlayerTravel>[])new Predicate[0]);
        this.name = "Strafe";
        this.tag = "Strafe";
    }
    
    private float GetRotationYawForCalc() {
        float rotationYaw = Strafe.mc.player.rotationYaw;
        if (Strafe.mc.player.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float n = 1.0f;
        if (Strafe.mc.player.moveForward < 0.0f) {
            n = -0.5f;
        }
        else if (Strafe.mc.player.moveForward > 0.0f) {
            n = 0.5f;
        }
        if (Strafe.mc.player.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * n;
        }
        if (Strafe.mc.player.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * n;
        }
        return rotationYaw * 0.017453292f;
    }
    
    public void whenDisabled() {
        if (this.useTimer.getValue(true)) {
            this.Timer.SetOverrideSpeed(1.0f);
        }
    }
}
