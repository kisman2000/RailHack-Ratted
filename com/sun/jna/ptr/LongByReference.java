//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna.ptr;

public class LongByReference extends ByReference
{
    public LongByReference() {
        this(0L);
    }
    
    public LongByReference(final long value) {
        super(8);
        this.setValue(value);
    }
    
    public void setValue(final long value) {
        this.getPointer().setLong(0L, value);
    }
    
    public long getValue() {
        return this.getPointer().getLong(0L);
    }
}
