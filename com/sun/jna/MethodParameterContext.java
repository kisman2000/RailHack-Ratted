//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

import java.lang.reflect.*;

public class MethodParameterContext extends FunctionParameterContext
{
    private Method method;
    
    MethodParameterContext(final Function f, final Object[] args, final int index, final Method m) {
        super(f, args, index);
        this.method = m;
    }
    
    public Method getMethod() {
        return this.method;
    }
}
