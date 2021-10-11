//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public class NotFileFilter extends AbstractFileFilter implements Serializable
{
    private static final long serialVersionUID = 6131563330944994230L;
    private final IOFileFilter filter;
    
    public NotFileFilter(final IOFileFilter filter) {
        if (filter == null) {
            throw new IllegalArgumentException("The filter must not be null");
        }
        this.filter = filter;
    }
    
    public boolean accept(final File file) {
        return !this.filter.accept(file);
    }
    
    public boolean accept(final File file, final String name) {
        return !this.filter.accept(file, name);
    }
    
    public String toString() {
        return super.toString() + "(" + this.filter.toString() + ")";
    }
}
