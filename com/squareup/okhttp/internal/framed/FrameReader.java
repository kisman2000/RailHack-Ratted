//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.framed;

import java.io.*;
import java.util.*;
import okio.*;

public interface FrameReader extends Closeable
{
    void readConnectionPreface() throws IOException;
    
    boolean nextFrame(final Handler p0) throws IOException;
    
    public interface Handler
    {
        void data(final boolean p0, final int p1, final BufferedSource p2, final int p3) throws IOException;
        
        void headers(final boolean p0, final boolean p1, final int p2, final int p3, final List<Header> p4, final HeadersMode p5);
        
        void rstStream(final int p0, final ErrorCode p1);
        
        void settings(final boolean p0, final Settings p1);
        
        void ackSettings();
        
        void ping(final boolean p0, final int p1, final int p2);
        
        void goAway(final int p0, final ErrorCode p1, final ByteString p2);
        
        void windowUpdate(final int p0, final long p1);
        
        void priority(final int p0, final int p1, final int p2, final boolean p3);
        
        void pushPromise(final int p0, final int p1, final List<Header> p2) throws IOException;
        
        void alternateService(final int p0, final String p1, final ByteString p2, final String p3, final int p4, final long p5);
    }
}
