//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

public class FunctionParameterContext extends ToNativeContext
{
    private Function function;
    private Object[] args;
    private int index;
    
    FunctionParameterContext(final Function f, final Object[] args, final int index) {
        this.function = f;
        this.args = args;
        this.index = index;
    }
    
    public Function getFunction() {
        return this.function;
    }
    
    public Object[] getParameters() {
        return this.args;
    }
    
    public int getParameterIndex() {
        return this.index;
    }
}
