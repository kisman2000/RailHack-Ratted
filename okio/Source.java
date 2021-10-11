//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package okio;

import java.io.*;

public interface Source extends Closeable
{
    long read(final Buffer p0, final long p1) throws IOException;
    
    Timeout timeout();
    
    void close() throws IOException;
}
