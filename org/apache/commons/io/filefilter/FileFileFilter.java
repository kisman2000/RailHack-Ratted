//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public class FileFileFilter extends AbstractFileFilter implements Serializable
{
    private static final long serialVersionUID = 5345244090827540862L;
    public static final IOFileFilter FILE;
    
    protected FileFileFilter() {
    }
    
    public boolean accept(final File file) {
        return file.isFile();
    }
    
    static {
        FILE = (IOFileFilter)new FileFileFilter();
    }
}
