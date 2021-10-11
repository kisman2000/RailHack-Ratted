//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna.ptr;

public class ShortByReference extends ByReference
{
    public ShortByReference() {
        this((short)0);
    }
    
    public ShortByReference(final short value) {
        super(2);
        this.setValue(value);
    }
    
    public void setValue(final short value) {
        this.getPointer().setShort(0L, value);
    }
    
    public short getValue() {
        return this.getPointer().getShort(0L);
    }
}
