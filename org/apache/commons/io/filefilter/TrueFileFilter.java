//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public class TrueFileFilter implements IOFileFilter, Serializable
{
    private static final long serialVersionUID = 8782512160909720199L;
    public static final IOFileFilter TRUE;
    public static final IOFileFilter INSTANCE;
    
    protected TrueFileFilter() {
    }
    
    public boolean accept(final File file) {
        return true;
    }
    
    public boolean accept(final File dir, final String name) {
        return true;
    }
    
    static {
        TRUE = (IOFileFilter)new TrueFileFilter();
        INSTANCE = TrueFileFilter.TRUE;
    }
}
