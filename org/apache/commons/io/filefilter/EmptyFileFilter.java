//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public class EmptyFileFilter extends AbstractFileFilter implements Serializable
{
    private static final long serialVersionUID = 3631422087512832211L;
    public static final IOFileFilter EMPTY;
    public static final IOFileFilter NOT_EMPTY;
    
    protected EmptyFileFilter() {
    }
    
    public boolean accept(final File file) {
        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            return files == null || files.length == 0;
        }
        return file.length() == 0L;
    }
    
    static {
        EMPTY = (IOFileFilter)new EmptyFileFilter();
        NOT_EMPTY = (IOFileFilter)new NotFileFilter(EmptyFileFilter.EMPTY);
    }
}
