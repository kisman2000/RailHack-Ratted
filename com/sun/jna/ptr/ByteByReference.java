//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna.ptr;

public class ByteByReference extends ByReference
{
    public ByteByReference() {
        this((byte)0);
    }
    
    public ByteByReference(final byte value) {
        super(1);
        this.setValue(value);
    }
    
    public void setValue(final byte value) {
        this.getPointer().setByte(0L, value);
    }
    
    public byte getValue() {
        return this.getPointer().getByte(0L);
    }
}
