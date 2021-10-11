//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang;

import java.io.*;

public class CharEncoding
{
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String US_ASCII = "US-ASCII";
    public static final String UTF_16 = "UTF-16";
    public static final String UTF_16BE = "UTF-16BE";
    public static final String UTF_16LE = "UTF-16LE";
    public static final String UTF_8 = "UTF-8";
    
    public static boolean isSupported(final String name) {
        if (name == null) {
            return false;
        }
        try {
            new String(ArrayUtils.EMPTY_BYTE_ARRAY, name);
        }
        catch (UnsupportedEncodingException e) {
            return false;
        }
        return true;
    }
}
