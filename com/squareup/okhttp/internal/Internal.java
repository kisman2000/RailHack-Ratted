//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal;

import java.util.logging.*;
import com.squareup.okhttp.internal.http.*;
import com.squareup.okhttp.internal.io.*;
import javax.net.ssl.*;
import java.net.*;
import com.squareup.okhttp.*;

public abstract class Internal
{
    public static final Logger logger;
    public static Internal instance;
    
    public static void initializeInstanceForTests() {
        new OkHttpClient();
    }
    
    public abstract void addLenient(final Headers.Builder p0, final String p1);
    
    public abstract void addLenient(final Headers.Builder p0, final String p1, final String p2);
    
    public abstract void setCache(final OkHttpClient p0, final InternalCache p1);
    
    public abstract InternalCache internalCache(final OkHttpClient p0);
    
    public abstract RealConnection get(final ConnectionPool p0, final Address p1, final StreamAllocation p2);
    
    public abstract void put(final ConnectionPool p0, final RealConnection p1);
    
    public abstract boolean connectionBecameIdle(final ConnectionPool p0, final RealConnection p1);
    
    public abstract RouteDatabase routeDatabase(final ConnectionPool p0);
    
    public abstract void apply(final ConnectionSpec p0, final SSLSocket p1, final boolean p2);
    
    public abstract HttpUrl getHttpUrlChecked(final String p0) throws MalformedURLException, UnknownHostException;
    
    public abstract void callEnqueue(final Call p0, final Callback p1, final boolean p2);
    
    public abstract StreamAllocation callEngineGetStreamAllocation(final Call p0);
    
    static {
        logger = Logger.getLogger(OkHttpClient.class.getName());
    }
}
