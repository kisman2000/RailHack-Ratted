//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal;

import com.squareup.okhttp.*;
import java.io.*;
import com.squareup.okhttp.internal.http.*;

public interface InternalCache
{
    Response get(final Request p0) throws IOException;
    
    CacheRequest put(final Response p0) throws IOException;
    
    void remove(final Request p0) throws IOException;
    
    void update(final Response p0, final Response p1) throws IOException;
    
    void trackConditionalCacheHit();
    
    void trackResponse(final CacheStrategy p0);
}
