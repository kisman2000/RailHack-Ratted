//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang;

import org.apache.commons.lang.exception.*;

public class UnhandledException extends NestableRuntimeException
{
    private static final long serialVersionUID = 1832101364842773720L;
    
    public UnhandledException(final Throwable cause) {
        super(cause);
    }
    
    public UnhandledException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
