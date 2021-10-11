//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

import java.lang.reflect.*;

public class MethodResultContext extends FunctionResultContext
{
    private final Method method;
    
    MethodResultContext(final Class<?> resultClass, final Function function, final Object[] args, final Method method) {
        super((Class)resultClass, function, args);
        this.method = method;
    }
    
    public Method getMethod() {
        return this.method;
    }
}
