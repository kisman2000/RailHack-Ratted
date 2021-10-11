//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

import java.lang.reflect.*;

public class CallbackParameterContext extends FromNativeContext
{
    private Method method;
    private Object[] args;
    private int index;
    
    CallbackParameterContext(final Class<?> javaType, final Method m, final Object[] args, final int index) {
        super(javaType);
        this.method = m;
        this.args = args;
        this.index = index;
    }
    
    public Method getMethod() {
        return this.method;
    }
    
    public Object[] getArguments() {
        return this.args;
    }
    
    public int getIndex() {
        return this.index;
    }
}
