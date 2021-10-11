//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna.ptr;

public class IntByReference extends ByReference
{
    public IntByReference() {
        this(0);
    }
    
    public IntByReference(final int value) {
        super(4);
        this.setValue(value);
    }
    
    public void setValue(final int value) {
        this.getPointer().setInt(0L, value);
    }
    
    public int getValue() {
        return this.getPointer().getInt(0L);
    }
}
