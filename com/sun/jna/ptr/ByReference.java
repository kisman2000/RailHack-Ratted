//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna.ptr;

import com.sun.jna.*;

public abstract class ByReference extends PointerType
{
    protected ByReference(final int dataSize) {
        this.setPointer((Pointer)new Memory((long)dataSize));
    }
}
