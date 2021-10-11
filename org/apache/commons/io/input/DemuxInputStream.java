//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.input;

import java.io.*;

public class DemuxInputStream extends InputStream
{
    private final InheritableThreadLocal<InputStream> m_streams;
    
    public DemuxInputStream() {
        this.m_streams = new InheritableThreadLocal<InputStream>();
    }
    
    public InputStream bindStream(final InputStream input) {
        final InputStream oldValue = this.m_streams.get();
        this.m_streams.set(input);
        return oldValue;
    }
    
    @Override
    public void close() throws IOException {
        final InputStream input = this.m_streams.get();
        if (null != input) {
            input.close();
        }
    }
    
    @Override
    public int read() throws IOException {
        final InputStream input = this.m_streams.get();
        if (null != input) {
            return input.read();
        }
        return -1;
    }
}
