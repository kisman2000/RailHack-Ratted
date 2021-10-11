//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp;

import com.squareup.okhttp.internal.*;
import java.io.*;
import java.nio.charset.*;
import okio.*;

public abstract class ResponseBody implements Closeable
{
    private Reader reader;
    
    public abstract MediaType contentType();
    
    public abstract long contentLength() throws IOException;
    
    public final InputStream byteStream() throws IOException {
        return this.source().inputStream();
    }
    
    public abstract BufferedSource source() throws IOException;
    
    public final byte[] bytes() throws IOException {
        final long contentLength = this.contentLength();
        if (contentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + contentLength);
        }
        final BufferedSource source = this.source();
        byte[] bytes;
        try {
            bytes = source.readByteArray();
        }
        finally {
            Util.closeQuietly((Closeable)source);
        }
        if (contentLength != -1L && contentLength != bytes.length) {
            throw new IOException("Content-Length and stream length disagree");
        }
        return bytes;
    }
    
    public final Reader charStream() throws IOException {
        final Reader r = this.reader;
        return (r != null) ? r : (this.reader = new InputStreamReader(this.byteStream(), this.charset()));
    }
    
    public final String string() throws IOException {
        return new String(this.bytes(), this.charset().name());
    }
    
    private Charset charset() {
        final MediaType contentType = this.contentType();
        return (contentType != null) ? contentType.charset(Util.UTF_8) : Util.UTF_8;
    }
    
    @Override
    public void close() throws IOException {
        this.source().close();
    }
    
    public static ResponseBody create(MediaType contentType, final String content) {
        Charset charset = Util.UTF_8;
        if (contentType != null) {
            charset = contentType.charset();
            if (charset == null) {
                charset = Util.UTF_8;
                contentType = MediaType.parse(contentType + "; charset=utf-8");
            }
        }
        final Buffer buffer = new Buffer().writeString(content, charset);
        return create(contentType, buffer.size(), (BufferedSource)buffer);
    }
    
    public static ResponseBody create(final MediaType contentType, final byte[] content) {
        final Buffer buffer = new Buffer().write(content);
        return create(contentType, content.length, (BufferedSource)buffer);
    }
    
    public static ResponseBody create(final MediaType contentType, final long contentLength, final BufferedSource content) {
        if (content == null) {
            throw new NullPointerException("source == null");
        }
        return new ResponseBody() {
            @Override
            public MediaType contentType() {
                return contentType;
            }
            
            @Override
            public long contentLength() {
                return contentLength;
            }
            
            @Override
            public BufferedSource source() {
                return content;
            }
        };
    }
}
