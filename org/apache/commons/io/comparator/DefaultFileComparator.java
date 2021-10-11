//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.comparator;

import java.io.*;
import java.util.*;

public class DefaultFileComparator extends AbstractFileComparator implements Serializable
{
    private static final long serialVersionUID = 3260141861365313518L;
    public static final Comparator<File> DEFAULT_COMPARATOR;
    public static final Comparator<File> DEFAULT_REVERSE;
    
    public int compare(final File file1, final File file2) {
        return file1.compareTo(file2);
    }
    
    static {
        DEFAULT_COMPARATOR = (Comparator)new DefaultFileComparator();
        DEFAULT_REVERSE = (Comparator)new ReverseComparator(DefaultFileComparator.DEFAULT_COMPARATOR);
    }
}
