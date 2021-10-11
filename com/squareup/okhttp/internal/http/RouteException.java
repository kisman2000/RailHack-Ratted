//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.http;

import java.io.*;
import java.lang.reflect.*;

public final class RouteException extends Exception
{
    private static final Method addSuppressedExceptionMethod;
    private IOException lastException;
    
    public RouteException(final IOException cause) {
        super(cause);
        this.lastException = cause;
    }
    
    public IOException getLastConnectException() {
        return this.lastException;
    }
    
    public void addConnectException(final IOException e) {
        this.addSuppressedIfPossible(e, this.lastException);
        this.lastException = e;
    }
    
    private void addSuppressedIfPossible(final IOException e, final IOException suppressed) {
        if (RouteException.addSuppressedExceptionMethod != null) {
            try {
                RouteException.addSuppressedExceptionMethod.invoke(e, suppressed);
            }
            catch (InvocationTargetException ex) {}
            catch (IllegalAccessException ex2) {}
        }
    }
    
    static {
        Method m;
        try {
            m = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        }
        catch (Exception e) {
            m = null;
        }
        addSuppressedExceptionMethod = m;
    }
}
