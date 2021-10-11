//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.math;

import java.util.*;

public class RandomUtils
{
    public static final Random JVM_RANDOM;
    
    public static int nextInt() {
        return nextInt(RandomUtils.JVM_RANDOM);
    }
    
    public static int nextInt(final Random random) {
        return random.nextInt();
    }
    
    public static int nextInt(final int n) {
        return nextInt(RandomUtils.JVM_RANDOM, n);
    }
    
    public static int nextInt(final Random random, final int n) {
        return random.nextInt(n);
    }
    
    public static long nextLong() {
        return nextLong(RandomUtils.JVM_RANDOM);
    }
    
    public static long nextLong(final Random random) {
        return random.nextLong();
    }
    
    public static boolean nextBoolean() {
        return nextBoolean(RandomUtils.JVM_RANDOM);
    }
    
    public static boolean nextBoolean(final Random random) {
        return random.nextBoolean();
    }
    
    public static float nextFloat() {
        return nextFloat(RandomUtils.JVM_RANDOM);
    }
    
    public static float nextFloat(final Random random) {
        return random.nextFloat();
    }
    
    public static double nextDouble() {
        return nextDouble(RandomUtils.JVM_RANDOM);
    }
    
    public static double nextDouble(final Random random) {
        return random.nextDouble();
    }
    
    static {
        JVM_RANDOM = (Random)new JVMRandom();
    }
}
