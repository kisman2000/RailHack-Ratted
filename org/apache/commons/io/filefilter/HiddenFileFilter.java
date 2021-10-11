//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public class HiddenFileFilter extends AbstractFileFilter implements Serializable
{
    private static final long serialVersionUID = 8930842316112759062L;
    public static final IOFileFilter HIDDEN;
    public static final IOFileFilter VISIBLE;
    
    protected HiddenFileFilter() {
    }
    
    public boolean accept(final File file) {
        return file.isHidden();
    }
    
    static {
        HIDDEN = (IOFileFilter)new HiddenFileFilter();
        VISIBLE = (IOFileFilter)new NotFileFilter(HiddenFileFilter.HIDDEN);
    }
}
