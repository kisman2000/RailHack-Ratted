//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

public class FromNativeContext
{
    private Class<?> type;
    
    FromNativeContext(final Class<?> javaType) {
        this.type = javaType;
    }
    
    public Class<?> getTargetType() {
        return this.type;
    }
}
