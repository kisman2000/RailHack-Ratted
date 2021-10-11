//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang;

public class NullArgumentException extends IllegalArgumentException
{
    private static final long serialVersionUID = 1174360235354917591L;
    
    public NullArgumentException(final String argName) {
        super(((argName == null) ? "Argument" : argName) + " must not be null.");
    }
}
