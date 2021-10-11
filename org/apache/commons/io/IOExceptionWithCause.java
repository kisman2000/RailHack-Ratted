//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io;

import java.io.*;

@Deprecated
public class IOExceptionWithCause extends IOException
{
    private static final long serialVersionUID = 1L;
    
    public IOExceptionWithCause(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public IOExceptionWithCause(final Throwable cause) {
        super(cause);
    }
}
