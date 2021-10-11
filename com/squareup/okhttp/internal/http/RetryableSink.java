//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.http;

import java.net.*;
import java.io.*;
import com.squareup.okhttp.internal.*;
import okio.*;

public final class RetryableSink implements Sink
{
    private boolean closed;
    private final int limit;
    private final Buffer content;
    
    public RetryableSink(final int limit) {
        this.content = new Buffer();
        this.limit = limit;
    }
    
    public RetryableSink() {
        this(-1);
    }
    
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        if (this.content.size() < this.limit) {
            throw new ProtocolException("content-length promised " + this.limit + " bytes, but received " + this.content.size());
        }
    }
    
    public void write(final Buffer source, final long byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Util.checkOffsetAndCount(source.size(), 0L, byteCount);
        if (this.limit != -1 && this.content.size() > this.limit - byteCount) {
            throw new ProtocolException("exceeded content-length limit of " + this.limit + " bytes");
        }
        this.content.write(source, byteCount);
    }
    
    public void flush() throws IOException {
    }
    
    public Timeout timeout() {
        return Timeout.NONE;
    }
    
    public long contentLength() throws IOException {
        return this.content.size();
    }
    
    public void writeToSocket(final Sink socketOut) throws IOException {
        final Buffer buffer = new Buffer();
        this.content.copyTo(buffer, 0L, this.content.size());
        socketOut.write(buffer, buffer.size());
    }
}
