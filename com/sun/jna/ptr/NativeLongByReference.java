//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna.ptr;

import com.sun.jna.*;

public class NativeLongByReference extends ByReference
{
    public NativeLongByReference() {
        this(new NativeLong(0L));
    }
    
    public NativeLongByReference(final NativeLong value) {
        super(NativeLong.SIZE);
        this.setValue(value);
    }
    
    public void setValue(final NativeLong value) {
        this.getPointer().setNativeLong(0L, value);
    }
    
    public NativeLong getValue() {
        return this.getPointer().getNativeLong(0L);
    }
}
