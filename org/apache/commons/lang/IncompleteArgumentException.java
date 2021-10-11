//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang;

import java.util.*;

public class IncompleteArgumentException extends IllegalArgumentException
{
    private static final long serialVersionUID = 4954193403612068178L;
    
    public IncompleteArgumentException(final String argName) {
        super(argName + " is incomplete.");
    }
    
    public IncompleteArgumentException(final String argName, final String[] items) {
        super(argName + " is missing the following items: " + safeArrayToString(items));
    }
    
    private static final String safeArrayToString(final Object[] array) {
        return (array == null) ? null : Arrays.asList(array).toString();
    }
}
