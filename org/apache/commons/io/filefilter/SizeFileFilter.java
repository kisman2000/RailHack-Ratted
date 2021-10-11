//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;

public class SizeFileFilter extends AbstractFileFilter implements Serializable
{
    private static final long serialVersionUID = 7388077430788600069L;
    private final long size;
    private final boolean acceptLarger;
    
    public SizeFileFilter(final long size) {
        this(size, true);
    }
    
    public SizeFileFilter(final long size, final boolean acceptLarger) {
        if (size < 0L) {
            throw new IllegalArgumentException("The size must be non-negative");
        }
        this.size = size;
        this.acceptLarger = acceptLarger;
    }
    
    public boolean accept(final File file) {
        final boolean smaller = file.length() < this.size;
        return this.acceptLarger ? (!smaller) : smaller;
    }
    
    public String toString() {
        final String condition = this.acceptLarger ? ">=" : "<";
        return super.toString() + "(" + condition + this.size + ")";
    }
}
