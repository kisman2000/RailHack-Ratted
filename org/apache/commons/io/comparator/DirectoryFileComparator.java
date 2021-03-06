//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.comparator;

import java.io.*;
import java.util.*;

public class DirectoryFileComparator extends AbstractFileComparator implements Serializable
{
    private static final long serialVersionUID = 296132640160964395L;
    public static final Comparator<File> DIRECTORY_COMPARATOR;
    public static final Comparator<File> DIRECTORY_REVERSE;
    
    public int compare(final File file1, final File file2) {
        return this.getType(file1) - this.getType(file2);
    }
    
    private int getType(final File file) {
        if (file.isDirectory()) {
            return 1;
        }
        return 2;
    }
    
    static {
        DIRECTORY_COMPARATOR = (Comparator)new DirectoryFileComparator();
        DIRECTORY_REVERSE = (Comparator)new ReverseComparator(DirectoryFileComparator.DIRECTORY_COMPARATOR);
    }
}
