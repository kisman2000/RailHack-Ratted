//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.monitor;

import java.io.*;

public interface FileAlterationListener
{
    void onStart(final FileAlterationObserver p0);
    
    void onDirectoryCreate(final File p0);
    
    void onDirectoryChange(final File p0);
    
    void onDirectoryDelete(final File p0);
    
    void onFileCreate(final File p0);
    
    void onFileChange(final File p0);
    
    void onFileDelete(final File p0);
    
    void onStop(final FileAlterationObserver p0);
}
