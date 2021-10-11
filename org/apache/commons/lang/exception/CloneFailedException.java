//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.exception;

public class CloneFailedException extends NestableRuntimeException
{
    private static final long serialVersionUID = 20091223L;
    
    public CloneFailedException(final String message) {
        super(message);
    }
    
    public CloneFailedException(final Throwable cause) {
        super(cause);
    }
    
    public CloneFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
