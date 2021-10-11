//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.output;

import java.io.*;

public class ClosedOutputStream extends OutputStream
{
    public static final ClosedOutputStream CLOSED_OUTPUT_STREAM;
    
    @Override
    public void write(final int b) throws IOException {
        throw new IOException("write(" + b + ") failed: stream is closed");
    }
    
    @Override
    public void flush() throws IOException {
        throw new IOException("flush() failed: stream is closed");
    }
    
    static {
        CLOSED_OUTPUT_STREAM = new ClosedOutputStream();
    }
}
