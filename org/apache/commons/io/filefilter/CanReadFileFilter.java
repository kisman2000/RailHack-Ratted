//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public class CanReadFileFilter extends AbstractFileFilter implements Serializable
{
    private static final long serialVersionUID = 3179904805251622989L;
    public static final IOFileFilter CAN_READ;
    public static final IOFileFilter CANNOT_READ;
    public static final IOFileFilter READ_ONLY;
    
    protected CanReadFileFilter() {
    }
    
    public boolean accept(final File file) {
        return file.canRead();
    }
    
    static {
        CAN_READ = (IOFileFilter)new CanReadFileFilter();
        CANNOT_READ = (IOFileFilter)new NotFileFilter(CanReadFileFilter.CAN_READ);
        READ_ONLY = (IOFileFilter)new AndFileFilter(CanReadFileFilter.CAN_READ, CanWriteFileFilter.CANNOT_WRITE);
    }
}
