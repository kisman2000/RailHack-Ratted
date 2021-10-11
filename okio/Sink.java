//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package okio;

import java.io.*;

public interface Sink extends Closeable, Flushable
{
    void write(final Buffer p0, final long p1) throws IOException;
    
    void flush() throws IOException;
    
    Timeout timeout();
    
    void close() throws IOException;
}
