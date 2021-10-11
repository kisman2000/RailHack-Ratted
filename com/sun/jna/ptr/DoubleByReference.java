//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna.ptr;

public class DoubleByReference extends ByReference
{
    public DoubleByReference() {
        this(0.0);
    }
    
    public DoubleByReference(final double value) {
        super(8);
        this.setValue(value);
    }
    
    public void setValue(final double value) {
        this.getPointer().setDouble(0L, value);
    }
    
    public double getValue() {
        return this.getPointer().getDouble(0L);
    }
}
