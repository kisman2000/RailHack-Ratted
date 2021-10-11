//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public class DirectoryFileFilter extends AbstractFileFilter implements Serializable
{
    private static final long serialVersionUID = -5148237843784525732L;
    public static final IOFileFilter DIRECTORY;
    public static final IOFileFilter INSTANCE;
    
    protected DirectoryFileFilter() {
    }
    
    public boolean accept(final File file) {
        return file.isDirectory();
    }
    
    static {
        DIRECTORY = (IOFileFilter)new DirectoryFileFilter();
        INSTANCE = DirectoryFileFilter.DIRECTORY;
    }
}
