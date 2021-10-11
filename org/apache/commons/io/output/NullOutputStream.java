//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.output;

import java.io.*;

public class NullOutputStream extends OutputStream
{
    public static final NullOutputStream NULL_OUTPUT_STREAM;
    
    @Override
    public void write(final byte[] b, final int off, final int len) {
    }
    
    @Override
    public void write(final int b) {
    }
    
    @Override
    public void write(final byte[] b) throws IOException {
    }
    
    static {
        NULL_OUTPUT_STREAM = new NullOutputStream();
    }
}
