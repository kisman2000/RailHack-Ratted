//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

public class TimerHelp
{
    private long time;
    
    public TimerHelp() {
        this.time = -1L;
    }
    
    public boolean passed(final long ms) {
        return this.getTime(System.nanoTime() - this.time) >= ms;
    }
    
    public void reset() {
        this.time = System.nanoTime();
    }
    
    public long getTime(final long time) {
        return time / 1000000L;
    }
}
