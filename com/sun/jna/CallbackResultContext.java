//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.sun.jna;

import java.lang.reflect.*;

public class CallbackResultContext extends ToNativeContext
{
    private Method method;
    
    CallbackResultContext(final Method callbackMethod) {
        this.method = callbackMethod;
    }
    
    public Method getMethod() {
        return this.method;
    }
}
