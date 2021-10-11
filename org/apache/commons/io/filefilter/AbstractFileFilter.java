//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public abstract class AbstractFileFilter implements IOFileFilter
{
    @Override
    public boolean accept(final File file) {
        return this.accept(file.getParentFile(), file.getName());
    }
    
    @Override
    public boolean accept(final File dir, final String name) {
        return this.accept(new File(dir, name));
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
