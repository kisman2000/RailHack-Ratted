//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.http;

import java.io.*;

public final class RequestException extends Exception
{
    public RequestException(final IOException cause) {
        super(cause);
    }
    
    @Override
    public IOException getCause() {
        return (IOException)super.getCause();
    }
}
