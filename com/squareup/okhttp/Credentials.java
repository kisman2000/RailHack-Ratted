//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp;

import okio.*;
import java.io.*;

public final class Credentials
{
    private Credentials() {
    }
    
    public static String basic(final String userName, final String password) {
        try {
            final String usernameAndPassword = userName + ":" + password;
            final byte[] bytes = usernameAndPassword.getBytes("ISO-8859-1");
            final String encoded = ByteString.of(bytes).base64();
            return "Basic " + encoded;
        }
        catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }
}
