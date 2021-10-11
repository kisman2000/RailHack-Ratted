//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.http;

import okio.*;
import java.io.*;
import com.squareup.okhttp.*;

public interface HttpStream
{
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;
    
    Sink createRequestBody(final Request p0, final long p1) throws IOException;
    
    void writeRequestHeaders(final Request p0) throws IOException;
    
    void writeRequestBody(final RetryableSink p0) throws IOException;
    
    void finishRequest() throws IOException;
    
    Response.Builder readResponseHeaders() throws IOException;
    
    ResponseBody openResponseBody(final Response p0) throws IOException;
    
    void setHttpEngine(final HttpEngine p0);
    
    void cancel();
}
