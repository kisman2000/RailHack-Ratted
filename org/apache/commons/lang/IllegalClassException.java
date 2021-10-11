//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang;

public class IllegalClassException extends IllegalArgumentException
{
    private static final long serialVersionUID = 8063272569377254819L;
    
    public IllegalClassException(final Class expected, final Object actual) {
        super("Expected: " + safeGetClassName(expected) + ", actual: " + ((actual == null) ? "null" : actual.getClass().getName()));
    }
    
    public IllegalClassException(final Class expected, final Class actual) {
        super("Expected: " + safeGetClassName(expected) + ", actual: " + safeGetClassName(actual));
    }
    
    public IllegalClassException(final String message) {
        super(message);
    }
    
    private static final String safeGetClassName(final Class cls) {
        return (cls == null) ? null : cls.getName();
    }
}
