//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.framed;

public enum HeadersMode
{
    SPDY_SYN_STREAM, 
    SPDY_REPLY, 
    SPDY_HEADERS, 
    HTTP_20_HEADERS;
    
    public boolean failIfStreamAbsent() {
        return this == HeadersMode.SPDY_REPLY || this == HeadersMode.SPDY_HEADERS;
    }
    
    public boolean failIfStreamPresent() {
        return this == HeadersMode.SPDY_SYN_STREAM;
    }
    
    public boolean failIfHeadersAbsent() {
        return this == HeadersMode.SPDY_HEADERS;
    }
    
    public boolean failIfHeadersPresent() {
        return this == HeadersMode.SPDY_REPLY;
    }
}
