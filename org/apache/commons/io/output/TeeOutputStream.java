//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.output;

import java.io.*;

public class TeeOutputStream extends ProxyOutputStream
{
    protected OutputStream branch;
    
    public TeeOutputStream(final OutputStream out, final OutputStream branch) {
        super(out);
        this.branch = branch;
    }
    
    public synchronized void write(final byte[] b) throws IOException {
        super.write(b);
        this.branch.write(b);
    }
    
    public synchronized void write(final byte[] b, final int off, final int len) throws IOException {
        super.write(b, off, len);
        this.branch.write(b, off, len);
    }
    
    public synchronized void write(final int b) throws IOException {
        super.write(b);
        this.branch.write(b);
    }
    
    public void flush() throws IOException {
        super.flush();
        this.branch.flush();
    }
    
    public void close() throws IOException {
        try {
            super.close();
        }
        finally {
            this.branch.close();
        }
    }
}
