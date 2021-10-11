//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.input;

import java.io.*;

public class InfiniteCircularInputStream extends InputStream
{
    private final byte[] repeatedContent;
    private int position;
    
    public InfiniteCircularInputStream(final byte[] repeatedContent) {
        this.position = -1;
        this.repeatedContent = repeatedContent;
    }
    
    @Override
    public int read() {
        this.position = (this.position + 1) % this.repeatedContent.length;
        return this.repeatedContent[this.position] & 0xFF;
    }
}
