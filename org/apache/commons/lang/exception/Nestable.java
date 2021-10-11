//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.exception;

import java.io.*;

public interface Nestable
{
    Throwable getCause();
    
    String getMessage();
    
    String getMessage(final int p0);
    
    String[] getMessages();
    
    Throwable getThrowable(final int p0);
    
    int getThrowableCount();
    
    Throwable[] getThrowables();
    
    int indexOfThrowable(final Class p0);
    
    int indexOfThrowable(final Class p0, final int p1);
    
    void printStackTrace(final PrintWriter p0);
    
    void printStackTrace(final PrintStream p0);
    
    void printPartialStackTrace(final PrintWriter p0);
}
