//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.output;

import java.util.*;
import org.apache.commons.io.*;
import java.io.*;

public class TaggedOutputStream extends ProxyOutputStream
{
    private final Serializable tag;
    
    public TaggedOutputStream(final OutputStream proxy) {
        super(proxy);
        this.tag = UUID.randomUUID();
    }
    
    public boolean isCauseOf(final Exception exception) {
        return TaggedIOException.isTaggedWith(exception, this.tag);
    }
    
    public void throwIfCauseOf(final Exception exception) throws IOException {
        TaggedIOException.throwCauseIfTaggedWith(exception, this.tag);
    }
    
    protected void handleIOException(final IOException e) throws IOException {
        throw new TaggedIOException(e, this.tag);
    }
}
