//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.output;

import java.io.*;

public class ChunkedWriter extends FilterWriter
{
    private static final int DEFAULT_CHUNK_SIZE = 4096;
    private final int chunkSize;
    
    public ChunkedWriter(final Writer writer, final int chunkSize) {
        super(writer);
        if (chunkSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.chunkSize = chunkSize;
    }
    
    public ChunkedWriter(final Writer writer) {
        this(writer, 4096);
    }
    
    @Override
    public void write(final char[] data, final int srcOffset, final int length) throws IOException {
        int chunk;
        for (int bytes = length, dstOffset = srcOffset; bytes > 0; bytes -= chunk, dstOffset += chunk) {
            chunk = Math.min(bytes, this.chunkSize);
            this.out.write(data, dstOffset, chunk);
        }
    }
}
