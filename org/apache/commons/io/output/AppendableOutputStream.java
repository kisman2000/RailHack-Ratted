//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.output;

import java.io.*;

public class AppendableOutputStream<T extends Appendable> extends OutputStream
{
    private final T appendable;
    
    public AppendableOutputStream(final T appendable) {
        this.appendable = appendable;
    }
    
    @Override
    public void write(final int b) throws IOException {
        this.appendable.append((char)b);
    }
    
    public T getAppendable() {
        return this.appendable;
    }
}
