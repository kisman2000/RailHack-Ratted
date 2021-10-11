//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

import java.lang.reflect.*;

abstract class VarArgsChecker
{
    private VarArgsChecker() {
    }
    
    static VarArgsChecker create() {
        try {
            final Method isVarArgsMethod = Method.class.getMethod("isVarArgs", (Class<?>[])new Class[0]);
            if (isVarArgsMethod != null) {
                return new RealVarArgsChecker();
            }
            return new NoVarArgsChecker();
        }
        catch (NoSuchMethodException e) {
            return new NoVarArgsChecker();
        }
        catch (SecurityException e2) {
            return new NoVarArgsChecker();
        }
    }
    
    abstract boolean isVarArgs(final Method p0);
    
    abstract int fixedArgs(final Method p0);
    
    private static final class RealVarArgsChecker extends VarArgsChecker
    {
        private RealVarArgsChecker() {
            super(null);
        }
        
        @Override
        boolean isVarArgs(final Method m) {
            return m.isVarArgs();
        }
        
        @Override
        int fixedArgs(final Method m) {
            return m.isVarArgs() ? (m.getParameterTypes().length - 1) : 0;
        }
    }
    
    private static final class NoVarArgsChecker extends VarArgsChecker
    {
        private NoVarArgsChecker() {
            super(null);
        }
        
        @Override
        boolean isVarArgs(final Method m) {
            return false;
        }
        
        @Override
        int fixedArgs(final Method m) {
            return 0;
        }
    }
}
