//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.http;

import okio.*;
import com.squareup.okhttp.*;

public final class RealResponseBody extends ResponseBody
{
    private final Headers headers;
    private final BufferedSource source;
    
    public RealResponseBody(final Headers headers, final BufferedSource source) {
        this.headers = headers;
        this.source = source;
    }
    
    @Override
    public MediaType contentType() {
        final String contentType = this.headers.get("Content-Type");
        return (contentType != null) ? MediaType.parse(contentType) : null;
    }
    
    @Override
    public long contentLength() {
        return OkHeaders.contentLength(this.headers);
    }
    
    @Override
    public BufferedSource source() {
        return this.source;
    }
}
