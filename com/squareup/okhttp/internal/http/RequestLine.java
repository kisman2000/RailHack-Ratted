//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.http;

import java.net.*;
import com.squareup.okhttp.*;

public final class RequestLine
{
    private RequestLine() {
    }
    
    static String get(final Request request, final Proxy.Type proxyType) {
        final StringBuilder result = new StringBuilder();
        result.append(request.method());
        result.append(' ');
        if (includeAuthorityInRequestLine(request, proxyType)) {
            result.append(request.httpUrl());
        }
        else {
            result.append(requestPath(request.httpUrl()));
        }
        result.append(" HTTP/1.1");
        return result.toString();
    }
    
    private static boolean includeAuthorityInRequestLine(final Request request, final Proxy.Type proxyType) {
        return !request.isHttps() && proxyType == Proxy.Type.HTTP;
    }
    
    public static String requestPath(final HttpUrl url) {
        final String path = url.encodedPath();
        final String query = url.encodedQuery();
        return (query != null) ? (path + '?' + query) : path;
    }
}
