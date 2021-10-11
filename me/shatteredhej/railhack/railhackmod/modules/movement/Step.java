//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.block.material.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class Step extends Module
{
    Setting vanilla;
    Setting stepHeightVanilla;
    Setting stepHeight;
    Setting spoof;
    Setting ticks;
    Setting turnOff;
    Setting check;
    Setting small;
    private final double[] oneblockPositions;
    private final double[] twoblockPositions;
    private final double[] futurePositions;
    final double[] twoFiveOffset;
    private final double[] threeBlockPositions;
    private final double[] fourBlockPositions;
    private double[] selectedPositions;
    private int packets;
    private static Step instance;
    
    public Step() {
        super(Category.Movement);
        this.vanilla = this.register("Vanilla", "StepVanilla", false);
        this.stepHeightVanilla = this.register("Vanilla Height", "VHeight", 2.0, 0.0, 4.0);
        this.stepHeight = this.register("Height", "StepHeight", 1, 0, 4);
        this.spoof = this.register("Spoof", "StepSpoof", false);
        this.ticks = this.register("Delay", "StepDelay", 3, 0, 25);
        this.turnOff = this.register("Disable", "StepDisable", false);
        this.check = this.register("Check", "StepCheck", false);
        this.small = this.register("Small", "StepSmall", false);
        this.oneblockPositions = new double[] { 0.42, 0.75 };
        this.twoblockPositions = new double[] { 0.4, 0.75, 0.5, 0.41, 0.83, 1.16, 1.41, 1.57, 1.58, 1.42 };
        this.futurePositions = new double[] { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
        this.twoFiveOffset = new double[] { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
        this.threeBlockPositions = new double[] { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43, 1.78, 1.63, 1.51, 1.9, 2.21, 2.45, 2.43 };
        this.fourBlockPositions = new double[] { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43, 1.78, 1.63, 1.51, 1.9, 2.21, 2.45, 2.43, 2.78, 2.63, 2.51, 2.9, 3.21, 3.45, 3.43 };
        this.selectedPositions = new double[0];
        this.name = "Step";
        this.tag = "Step";
    }
    
    public static Step getInstance() {
        if (Step.instance == null) {
            Step.instance = new Step();
        }
        return Step.instance;
    }
    
    public void whenDisabled() {
        Step.mc.player.stepHeight = 0.6f;
    }
    
    public void onUpdate() {
        if (this.vanilla.getValue(true)) {
            Step.mc.player.stepHeight = (float)this.stepHeightVanilla.getValue(1);
            return;
        }
        switch (this.stepHeight.getValue(1)) {
            case 1: {
                this.selectedPositions = this.oneblockPositions;
                break;
            }
            case 2: {
                this.selectedPositions = (this.small.getValue(true) ? this.twoblockPositions : this.futurePositions);
                break;
            }
            case 3: {
                this.selectedPositions = this.twoFiveOffset;
            }
            case 4: {
                this.selectedPositions = this.fourBlockPositions;
                break;
            }
        }
        if (Step.mc.player.collidedHorizontally && Step.mc.player.onGround) {
            ++this.packets;
        }
        final AxisAlignedBB bb = Step.mc.player.getEntityBoundingBox();
        if (this.check.getValue(true)) {
            for (int x = MathHelper.floor(bb.minX); x < MathHelper.floor(bb.maxX + 1.0); ++x) {
                for (int z = MathHelper.floor(bb.minZ); z < MathHelper.floor(bb.maxZ + 1.0); ++z) {
                    final Block block = Step.mc.world.getBlockState(new BlockPos((double)x, bb.maxY + 1.0, (double)z)).getBlock();
                    if (!(block instanceof BlockAir)) {
                        return;
                    }
                }
            }
        }
        if (Step.mc.player.onGround && !Step.mc.player.isInsideOfMaterial(Material.WATER) && !Step.mc.player.isInsideOfMaterial(Material.LAVA) && Step.mc.player.collidedVertically && Step.mc.player.fallDistance == 0.0f && !Step.mc.gameSettings.keyBindJump.pressed && Step.mc.player.collidedHorizontally && !Step.mc.player.isOnLadder() && (this.packets > this.selectedPositions.length - 2 || (this.spoof.getValue(true) && this.packets > this.ticks.getValue(1)))) {
            for (final double position : this.selectedPositions) {
                Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + position, Step.mc.player.posZ, true));
            }
            Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + this.selectedPositions[this.selectedPositions.length - 1], Step.mc.player.posZ);
            this.packets = 0;
            if (this.turnOff.getValue(true)) {
                this.whenDisabled();
            }
        }
    }
}
