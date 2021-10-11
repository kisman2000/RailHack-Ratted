//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.crystal.speed;

import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.damage.*;
import net.minecraft.entity.*;

public class RotationUtil
{
    private static final Minecraft mc;
    private static float yaw;
    private static float pitch;
    
    public static void updateRotations() {
        RotationUtil.yaw = RotationUtil.mc.player.rotationYaw;
        RotationUtil.pitch = RotationUtil.mc.player.rotationPitch;
    }
    
    public static Vec3d getEyesPos() {
        return new Vec3d(RotationUtil.mc.player.posX, RotationUtil.mc.player.posY + RotationUtil.mc.player.getEyeHeight(), RotationUtil.mc.player.posZ);
    }
    
    public static float[] getLegitRotations(final Vec3d vec) {
        final Vec3d eyesPos = getEyesPos();
        final double diffX = vec.x - eyesPos.x;
        final double diffY = vec.y - eyesPos.y;
        final double diffZ = vec.z - eyesPos.z;
        final double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        final float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[] { RotationUtil.mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - RotationUtil.mc.player.rotationYaw), RotationUtil.mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - RotationUtil.mc.player.rotationPitch) };
    }
    
    public static void faceVector(final Vec3d vec, final boolean normalizeAngle) {
        final float[] rotations = getLegitRotations(vec);
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(rotations[0], normalizeAngle ? ((float)MathHelper.normalizeAngle((int)rotations[1], 360)) : rotations[1], RotationUtil.mc.player.onGround));
    }
    
    public static void restoreRotations() {
        RotationUtil.mc.player.rotationYaw = RotationUtil.yaw;
        RotationUtil.mc.player.rotationYawHead = RotationUtil.yaw;
        RotationUtil.mc.player.rotationPitch = RotationUtil.pitch;
    }
    
    public static void setPlayerRotations(final float yaw, final float pitch) {
        RotationUtil.mc.player.rotationYaw = yaw;
        RotationUtil.mc.player.rotationYawHead = yaw;
        RotationUtil.mc.player.rotationPitch = pitch;
    }
    
    public void setPlayerYaw(final float yaw) {
        RotationUtil.mc.player.rotationYaw = yaw;
        RotationUtil.mc.player.rotationYawHead = yaw;
    }
    
    public void lookAtPos(final BlockPos pos) {
        final float[] angle = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), new Vec3d((double)(pos.getX() + 0.5f), (double)(pos.getY() + 0.5f), (double)(pos.getZ() + 0.5f)));
        setPlayerRotations(angle[0], angle[1]);
    }
    
    public void lookAtVec3d(final Vec3d vec3d) {
        final float[] angle = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        setPlayerRotations(angle[0], angle[1]);
    }
    
    public void lookAtVec3d(final double x, final double y, final double z) {
        final Vec3d vec3d = new Vec3d(x, y, z);
        this.lookAtVec3d(vec3d);
    }
    
    public void lookAtEntity(final Entity entity) {
        final float[] angle = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), entity.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()));
        setPlayerRotations(angle[0], angle[1]);
    }
    
    public void setPlayerPitch(final float pitch) {
        RotationUtil.mc.player.rotationPitch = pitch;
    }
    
    public float getYaw() {
        return RotationUtil.yaw;
    }
    
    public void setYaw(final float yaw) {
        RotationUtil.yaw = yaw;
    }
    
    public float getPitch() {
        return RotationUtil.pitch;
    }
    
    public void setPitch(final float pitch) {
        RotationUtil.pitch = pitch;
    }
    
    public int getDirection4D() {
        return this.getDirection4D();
    }
    
    public String getDirection4D(final boolean northRed) {
        return this.getDirection4D(northRed);
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
