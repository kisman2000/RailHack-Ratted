//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util;

import java.net.*;
import java.util.*;
import java.security.*;

public final class HWIDUtil
{
    public static boolean blacklisted() {
        try {
            final String s = new Scanner(new URL("https://pastebin.com/raw/ZrMLRRar").openStream(), "UTF-8").useDelimiter("\\A").next();
            return s.contains(getID());
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public static String getID() {
        try {
            final MessageDigest hash = MessageDigest.getInstance("MD5");
            final String s = System.getProperty("os.name") + System.getProperty("os.arch") + System.getProperty("os.version") + Runtime.getRuntime().availableProcessors() + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_ARCHITECTURE") + System.getenv("PROCESSOR_ARCHITEW6432") + System.getenv("NUMBER_OF_PROCESSORS");
            return bytesToHex(hash.digest(s.getBytes()));
        }
        catch (Exception e) {
            return "######################";
        }
    }
    
    private static String bytesToHex(final byte[] bytes) {
        final char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; ++j) {
            final int v = bytes[j] & 0xFF;
            hexChars[j * 2] = "0123456789ABCDEF".toCharArray()[v >>> 4];
            hexChars[j * 2 + 1] = "0123456789ABCDEF".toCharArray()[v & 0xF];
        }
        return new String(hexChars);
    }
}
