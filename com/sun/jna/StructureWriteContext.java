//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

import java.lang.reflect.*;

public class StructureWriteContext extends ToNativeContext
{
    private Structure struct;
    private Field field;
    
    StructureWriteContext(final Structure struct, final Field field) {
        this.struct = struct;
        this.field = field;
    }
    
    public Structure getStructure() {
        return this.struct;
    }
    
    public Field getField() {
        return this.field;
    }
}
