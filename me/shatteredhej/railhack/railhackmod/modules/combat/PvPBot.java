//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.block.state.*;

public class PvPBot extends Module
{
    Setting swordInHole;
    Setting swordInHoleHP;
    Setting autoSwitchSword;
    Setting gappleOnShP;
    Setting autoCrystal;
    Setting autoCrystalHP;
    Setting autoStep;
    Setting autoStepHP;
    Setting packetFill;
    Setting packetFillWait;
    Setting packetFillHealth;
    Setting packetFillDistance;
    Setting chorusOnTrap;
    Setting autoTrapOnChorus;
    private int aCH;
    private int sIHHP;
    private int aSHP;
    private int aPFW;
    private int aPFHP;
    private Timer timer;
    private boolean isEating;
    
    public PvPBot() {
        super(Category.Combat);
        this.swordInHole = this.register("SwordInHole", "PvPBotSword", false);
        this.swordInHoleHP = this.register("SwordInHoleHP", "PvPBotSwordInHoleHP", 0, 0, 36);
        this.autoSwitchSword = this.register("AutoSwitchSword", "PvPBotAutoSwitchSword", false);
        this.gappleOnShP = this.register("GappleOnSwrdHP", "PvPBotGappleOnSwrdHP", false);
        this.autoCrystal = this.register("AutoCrystal", "PvPBotAc", false);
        this.autoCrystalHP = this.register("AutoCrystalHP", "PvPBotAcHP", 0, 0, 36);
        this.autoStep = this.register("AutoStep", "AutoStep", false);
        this.autoStepHP = this.register("AutoStepHP", "AutoStepHP", 0, 0, 36);
        this.packetFill = this.register("AutoPacketFill", "PvPBotAPF", false);
        this.packetFillWait = this.register("APFWait", "PvPBotAPFWait", 0, 0, 1000);
        this.packetFillHealth = this.register("APFHealth", "PvPBotAPFHealth", 0, 0, 36);
        this.packetFillDistance = this.register("APFDistance", "PvPBotAPFDistance", 0, 0, 15);
        this.chorusOnTrap = this.register("ChorusWhenTrapped", "PvPBotChorusWhenTrapped", false);
        this.autoTrapOnChorus = this.register("TrapWhenTrapped", "PvPBotAutoTrapOnChorus", false);
        this.aCH = this.autoCrystalHP.getValue(1);
        this.sIHHP = this.swordInHoleHP.getValue(1);
        this.aSHP = this.autoStepHP.getValue(1);
        this.aPFW = this.packetFillWait.getValue(1);
        this.aPFHP = this.packetFillHealth.getValue(1);
        this.timer = new Timer();
        this.name = "PvPBot";
        this.tag = "PvPBot";
    }
    
    public void onUpdate() {
        final float hp = PvPBot.mc.player.getHealth() + PvPBot.mc.player.getAbsorptionAmount();
        if (this.autoCrystal.getValue(true) && hp > this.aCH && !RailHack.getModuleManager().getModuleWithTag("PacketFill").isActive()) {
            if (!RailHack.getModuleManager().getModuleWithTag("AutoCrystal").isActive()) {
                RailHack.getModuleManager().getModuleWithTag("AutoCrystal").setActive(true);
            }
            if (RailHack.getModuleManager().getModuleWithTag("PacketFill").isActive()) {
                RailHack.getModuleManager().getModuleWithTag("AutoCrystal").setDisable();
            }
        }
        else if (hp <= this.aCH) {
            RailHack.getModuleManager().getModuleWithTag("AutoCrystal").setDisable();
        }
        if (this.chorusOnTrap.getValue(true)) {
            if (this.autoTrapOnChorus.getValue(true) && isPlayerTrapped()) {
                RailHack.getModuleManager().getModuleWithTag("AutoTrap").setActive(true);
            }
            final boolean isTrapped = isPlayerTrapped();
            if (isPlayerTrapped()) {
                final int slot = PlayerUtil.getItemInHotbar(isTrapped ? Items.CHORUS_FRUIT : Items.GOLDEN_APPLE);
                if (slot != -1) {
                    PvPBot.mc.player.inventory.currentItem = slot;
                    PvPBot.mc.playerController.updateController();
                    if (PvPBot.mc.rightClickDelayTimer != 0) {
                        return;
                    }
                    PvPBot.mc.rightClickDelayTimer = 4;
                    PvPBot.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                    this.isEating = true;
                }
            }
        }
        else {
            this.isEating = false;
        }
        if (this.swordInHole.getValue(true) && this.isInHole() && hp > this.sIHHP) {
            if (!RailHack.getModuleManager().getModuleWithTag("Aura").isActive()) {
                if (RailHack.getModuleManager().getModuleWithTag("PacketFill").isActive()) {
                    RailHack.getModuleManager().getModuleWithTag("Aura").setDisable();
                }
                if (this.autoSwitchSword.getValue(true) && !(PvPBot.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && !RailHack.getModuleManager().getModuleWithTag("PacketFill").isActive()) {
                    this.findSwordHotbar();
                }
                RailHack.getModuleManager().getModuleWithTag("Aura").setActive(true);
            }
        }
        else if (hp <= this.sIHHP) {
            RailHack.getModuleManager().getModuleWithTag("Aura").setDisable();
            if (this.gappleOnShP.getValue(true)) {
                this.findGappleHotbar();
            }
        }
        if (this.packetFill.getValue(true) && this.isInHole() && hp > this.aPFHP && this.timer.passedMss(this.aPFW)) {
            for (final Entity e : PvPBot.mc.world.loadedEntityList) {
                if (e instanceof EntityOtherPlayerMP && PvPBot.mc.player.getDistance(e) <= this.packetFillDistance.getValue(1)) {
                    RailHack.getModuleManager().getModuleWithTag("PacketFill").setActive(true);
                }
            }
        }
        if (this.autoStep.getValue(true) && PvPBot.mc.player.onGround && PvPBot.mc.player.collidedHorizontally && hp > this.aSHP) {
            RailHack.getModuleManager().getModuleWithTag("Step").setActive(true);
        }
        else if (hp <= this.aSHP) {
            RailHack.getModuleManager().getModuleWithTag("Step").setDisable();
        }
    }
    
    private boolean isInHole() {
        final BlockPos playerBlock = PlayerUtil.GetLocalPlayerPosFloored();
        return PvPBot.mc.world.getBlockState(playerBlock.east()).getBlock() != Blocks.AIR && PvPBot.mc.world.getBlockState(playerBlock.west()).getBlock() != Blocks.AIR && PvPBot.mc.world.getBlockState(playerBlock.north()).getBlock() != Blocks.AIR && PvPBot.mc.world.getBlockState(playerBlock.south()).getBlock() != Blocks.AIR;
    }
    
    private int findSwordHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (PvPBot.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemSword) {
                return i;
            }
        }
        return -1;
    }
    
    private int findGappleHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (PvPBot.mc.player.inventory.getStackInSlot(i).getItem() == Items.GOLDEN_APPLE) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean isPlayerTrapped() {
        final BlockPos playerPos = PlayerUtil.GetLocalPlayerPosFloored();
        final BlockPos[] array;
        final BlockPos[] l_TrapPositions = array = new BlockPos[] { playerPos.down(), playerPos.up().up(), playerPos.north(), playerPos.south(), playerPos.east(), playerPos.west(), playerPos.north().up(), playerPos.south().up(), playerPos.east().up(), playerPos.west().up() };
        for (final BlockPos lPos : array) {
            final IBlockState lState = PvPBot.mc.world.getBlockState(lPos);
            if (lState.getBlock() != Blocks.OBSIDIAN && PvPBot.mc.world.getBlockState(lPos).getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
}
