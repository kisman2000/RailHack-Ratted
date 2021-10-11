//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.input;

import java.io.*;

public class BrokenInputStream extends InputStream
{
    private final IOException exception;
    
    public BrokenInputStream(final IOException exception) {
        this.exception = exception;
    }
    
    public BrokenInputStream() {
        this(new IOException("Broken input stream"));
    }
    
    @Override
    public int read() throws IOException {
        throw this.exception;
    }
    
    @Override
    public int available() throws IOException {
        throw this.exception;
    }
    
    @Override
    public long skip(final long n) throws IOException {
        throw this.exception;
    }
    
    @Override
    public synchronized void reset() throws IOException {
        throw this.exception;
    }
    
    @Override
    public void close() throws IOException {
        throw this.exception;
    }
}
