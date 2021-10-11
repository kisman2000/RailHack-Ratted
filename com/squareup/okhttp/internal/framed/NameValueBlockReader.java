//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.framed;

import java.io.*;
import java.util.zip.*;
import java.util.*;
import okio.*;

class NameValueBlockReader
{
    private final InflaterSource inflaterSource;
    private int compressedLimit;
    private final BufferedSource source;
    
    public NameValueBlockReader(final BufferedSource source) {
        final Source throttleSource = (Source)new ForwardingSource(source) {
            public long read(final Buffer sink, final long byteCount) throws IOException {
                if (NameValueBlockReader.this.compressedLimit == 0) {
                    return -1L;
                }
                final long read = super.read(sink, Math.min(byteCount, NameValueBlockReader.this.compressedLimit));
                if (read == -1L) {
                    return -1L;
                }
                NameValueBlockReader.this.compressedLimit -= read;
                return read;
            }
        };
        final Inflater inflater = new Inflater() {
            @Override
            public int inflate(final byte[] buffer, final int offset, final int count) throws DataFormatException {
                int result = super.inflate(buffer, offset, count);
                if (result == 0 && this.needsDictionary()) {
                    this.setDictionary(Spdy3.DICTIONARY);
                    result = super.inflate(buffer, offset, count);
                }
                return result;
            }
        };
        this.inflaterSource = new InflaterSource(throttleSource, inflater);
        this.source = Okio.buffer((Source)this.inflaterSource);
    }
    
    public List<Header> readNameValueBlock(final int length) throws IOException {
        this.compressedLimit += length;
        final int numberOfPairs = this.source.readInt();
        if (numberOfPairs < 0) {
            throw new IOException("numberOfPairs < 0: " + numberOfPairs);
        }
        if (numberOfPairs > 1024) {
            throw new IOException("numberOfPairs > 1024: " + numberOfPairs);
        }
        final List<Header> entries = new ArrayList<Header>(numberOfPairs);
        for (int i = 0; i < numberOfPairs; ++i) {
            final ByteString name = this.readByteString().toAsciiLowercase();
            final ByteString values = this.readByteString();
            if (name.size() == 0) {
                throw new IOException("name.size == 0");
            }
            entries.add(new Header(name, values));
        }
        this.doneReading();
        return entries;
    }
    
    private ByteString readByteString() throws IOException {
        final int length = this.source.readInt();
        return this.source.readByteString((long)length);
    }
    
    private void doneReading() throws IOException {
        if (this.compressedLimit > 0) {
            this.inflaterSource.refill();
            if (this.compressedLimit != 0) {
                throw new IOException("compressedLimit > 0: " + this.compressedLimit);
            }
        }
    }
    
    public void close() throws IOException {
        this.source.close();
    }
}
