//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.output;

import java.io.*;

public class DemuxOutputStream extends OutputStream
{
    private final InheritableThreadLocal<OutputStream> outputStreamThreadLocal;
    
    public DemuxOutputStream() {
        this.outputStreamThreadLocal = new InheritableThreadLocal<OutputStream>();
    }
    
    public OutputStream bindStream(final OutputStream output) {
        final OutputStream stream = this.outputStreamThreadLocal.get();
        this.outputStreamThreadLocal.set(output);
        return stream;
    }
    
    @Override
    public void close() throws IOException {
        final OutputStream output = this.outputStreamThreadLocal.get();
        if (null != output) {
            output.close();
        }
    }
    
    @Override
    public void flush() throws IOException {
        final OutputStream output = this.outputStreamThreadLocal.get();
        if (null != output) {
            output.flush();
        }
    }
    
    @Override
    public void write(final int ch) throws IOException {
        final OutputStream output = this.outputStreamThreadLocal.get();
        if (null != output) {
            output.write(ch);
        }
    }
}
