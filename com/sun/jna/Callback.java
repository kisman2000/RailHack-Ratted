//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

import java.util.*;

public interface Callback
{
    public static final String METHOD_NAME = "callback";
    public static final List<String> FORBIDDEN_NAMES = Collections.unmodifiableList((List<? extends String>)Arrays.asList("hashCode", "equals", "toString"));
    
    public interface UncaughtExceptionHandler
    {
        void uncaughtException(final Callback p0, final Throwable p1);
    }
}
