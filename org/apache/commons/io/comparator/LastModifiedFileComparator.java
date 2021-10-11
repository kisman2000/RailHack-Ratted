//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.comparator;

import java.io.*;
import java.util.*;

public class LastModifiedFileComparator extends AbstractFileComparator implements Serializable
{
    private static final long serialVersionUID = 7372168004395734046L;
    public static final Comparator<File> LASTMODIFIED_COMPARATOR;
    public static final Comparator<File> LASTMODIFIED_REVERSE;
    
    public int compare(final File file1, final File file2) {
        final long result = file1.lastModified() - file2.lastModified();
        if (result < 0L) {
            return -1;
        }
        if (result > 0L) {
            return 1;
        }
        return 0;
    }
    
    static {
        LASTMODIFIED_COMPARATOR = (Comparator)new LastModifiedFileComparator();
        LASTMODIFIED_REVERSE = (Comparator)new ReverseComparator(LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
    }
}
