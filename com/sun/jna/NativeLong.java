//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

public class NativeLong extends IntegerType
{
    private static final long serialVersionUID = 1L;
    public static final int SIZE;
    
    public NativeLong() {
        this(0L);
    }
    
    public NativeLong(final long value) {
        this(value, false);
    }
    
    public NativeLong(final long value, final boolean unsigned) {
        super(NativeLong.SIZE, value, unsigned);
    }
    
    static {
        SIZE = Native.LONG_SIZE;
    }
}
