//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

public class Timer
{
    private long time;
    
    public Timer() {
        this.time = -1L;
    }
    
    public boolean passedSS(final double s) {
        return this.passedMss((long)s * 1000L);
    }
    
    public boolean passedDmss(final double dms) {
        return this.passedMss((long)dms * 10L);
    }
    
    public boolean passedDss(final double ds) {
        return this.passedMss((long)ds * 100L);
    }
    
    public boolean passedMss(final long ms) {
        return this.passedNSS(this.convertToNS(ms));
    }
    
    public void setMss(final long ms) {
        this.time = System.nanoTime() - this.convertToNS(ms);
    }
    
    public boolean passedNSS(final long ns) {
        return System.nanoTime() - this.time >= ns;
    }
    
    public long getPassedTimeMss() {
        return this.getMs(System.nanoTime() - this.time);
    }
    
    public Timer reset() {
        this.time = System.nanoTime();
        return this;
    }
    
    public long getMs(final long time) {
        return time / 1000000L;
    }
    
    public long convertToNS(final long time) {
        return time * 1000000L;
    }
}
